package com.example.friendmod;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
	public static Item FRIEND_TOTEM;

	public static void register() {
		FRIEND_TOTEM = Registry.register(
				Registries.ITEM,
				FriendMod.id("friend_totem"),
				new FriendTotemItem(new Item.Settings().maxCount(1))
		);
	}
}
