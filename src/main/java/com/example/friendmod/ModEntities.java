package com.example.friendmod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
	public static EntityType<FriendEntity> FRIEND;

	public static void register() {
		Identifier id = FriendMod.id("friend");
		RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);

		FRIEND = Registry.register(
				Registries.ENTITY_TYPE,
				key,
				EntityType.Builder.create(FriendEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 1.8f)
						.build(key)
		);
	}
}
