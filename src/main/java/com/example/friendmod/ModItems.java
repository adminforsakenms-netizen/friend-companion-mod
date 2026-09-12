package com.example.friendmod;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
	public static Item FRIEND_TOTEM;

	public static void register() {
		Identifier id = FriendMod.id("friend_totem");
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

		FRIEND_TOTEM = Registry.register(
				Registries.ITEM,
				key,
				new FriendTotemItem(new Item.Settings().maxCount(1).registryKey(key))
		);
	}
}
