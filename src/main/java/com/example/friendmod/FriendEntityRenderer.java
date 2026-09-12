package com.example.friendmod;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class FriendEntityRenderer extends MobEntityRenderer<FriendEntity, ZombieEntityRenderState, BipedEntityModel<ZombieEntityRenderState>> {

	private static final Identifier TEXTURE = FriendMod.id("textures/entity/friend.png");

	public FriendEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.PLAYER)), 0.5f);
	}

	@Override
	public ZombieEntityRenderState createRenderState() {
		return new ZombieEntityRenderState();
	}

	@Override
	public Identifier getTexture(ZombieEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public void updateRenderState(FriendEntity friendEntity, ZombieEntityRenderState state, float tickDelta) {
		super.updateRenderState(friendEntity, state, tickDelta);
		BipedEntityRenderer.updateBipedRenderState(friendEntity, state, tickDelta);
	}
}
