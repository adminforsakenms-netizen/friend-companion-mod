package com.example.friendmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FriendTotemItem extends Item {
	public FriendTotemItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient()) {
			FriendEntity friend = new FriendEntity(ModEntities.FRIEND, world);
			Vec3d spawnPos = player.position().add(player.getRotationVector().multiply(2));
			friend.refreshPositionAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, player.getYaw(), 0);
			friend.setOwner(player);
			friend.setTamed(true, true);
			world.spawnEntity(friend);

			if (!player.getAbilities().creativeMode) {
				stack.decrement(1);
			}
		}

		return ActionResult.SUCCESS;
	}
}
