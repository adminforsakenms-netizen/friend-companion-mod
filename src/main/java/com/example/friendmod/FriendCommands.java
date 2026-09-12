package com.example.friendmod;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;

import java.util.List;

public class FriendCommands {

	public static void register() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("friend")
				.then(CommandManager.literal("duel")
					.then(CommandManager.literal("on").executes(ctx -> setDuel(ctx, true)))
					.then(CommandManager.literal("off").executes(ctx -> setDuel(ctx, false)))
				)
			);
		});
	}

	private static int setDuel(CommandContext<ServerCommandSource> ctx, boolean enabled) {
		ServerCommandSource source = ctx.getSource();
		if (source.getPlayer() == null) {
			source.sendError(Text.literal("Command ini cuma bisa dipakai pemain in-game."));
			return 0;
		}

		PlayerEntity player = source.getPlayer();
		Box searchArea = player.getBoundingBox().expand(100.0);
		List<FriendEntity> nearbyFriends = player.getEntityWorld().getEntitiesByClass(
				FriendEntity.class, searchArea, friend -> friend.isOwner(player));

		if (nearbyFriends.isEmpty()) {
			source.sendError(Text.literal("Tiada Friend kamu ditemui berdekatan."));
			return 0;
		}

		for (FriendEntity friend : nearbyFriends) {
			friend.setDuelMode(enabled);
		}

		String pesan = enabled
				? "§eMod duel diaktifkan. Friend kamu akan serang balik kalau kamu pukul dia."
				: "§aMod duel dimatikan. Friend kamu selamat, takkan serang balik kamu.";

		source.sendFeedback(() -> Text.literal(pesan), false);
		return 1;
	}
}
