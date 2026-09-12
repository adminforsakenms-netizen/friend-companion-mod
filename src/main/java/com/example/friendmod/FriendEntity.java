package com.example.friendmod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class FriendEntity extends TameableEntity {

	private boolean duelMode = false;

	public FriendEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createFriendAttributes() {
		return TameableEntity.createMobAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.FOLLOW_RANGE, 32.0);
	}

	public boolean isDuelMode() {
		return this.duelMode;
	}

	public void setDuelMode(boolean duelMode) {
		this.duelMode = duelMode;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, true));
		this.goalSelector.add(3, new FollowOwnerGoal(this, 1.0, 10.0f, 2.0f));
		this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
		this.goalSelector.add(6, new LookAroundGoal(this));

		this.targetSelector.add(1, new FriendRevengeGoal(this).setGroupRevenge());
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, HostileEntity.class, true));
	}

	@Override
	public boolean canBreedWith(AnimalEntity other) {
		return false;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return null;
	}

	@Override
	public boolean canPickUpLoot() {
		return true;
	}

	private static class FriendRevengeGoal extends RevengeGoal {
		private final FriendEntity friend;

		public FriendRevengeGoal(FriendEntity friend) {
			super(friend);
			this.friend = friend;
		}

		@Override
		public boolean canStart() {
			if (!super.canStart()) {
				return false;
			}
			if (this.friend.getAttacker() == this.friend.getOwner() && !this.friend.isDuelMode()) {
				return false;
			}
			return true;
		}
	}
}
