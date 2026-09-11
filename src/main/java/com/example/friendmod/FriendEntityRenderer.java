package com.example.friendmod;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class FriendEntityRenderer extends BipedEntityRenderer<FriendEntity, PlayerEntityModel<FriendEntity>> {

	private static final Identifier TEXTURE = FriendMod.id("textures/entity/friend.png");

	public FriendEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
	}

	@Override
	public Identifier getTexture(FriendEntity entity) {
		return TEXTURE;
	}
}
