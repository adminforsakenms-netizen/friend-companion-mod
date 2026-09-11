package com.example.friendmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;

public class FriendMod implements ModInitializer {
	public static final String MOD_ID = "friendmod";

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();

		FabricDefaultAttributeRegistry.register(ModEntities.FRIEND, FriendEntity.createFriendAttributes());
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
