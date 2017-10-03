package com.laytonsmith.abstraction.sponge.entities;

import com.laytonsmith.PureUtilities.Vector3D;
import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCEntityEquipment;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.MCProjectile;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.enums.MCProjectileType;
import com.laytonsmith.core.constructs.Target;
import org.spongepowered.api.entity.living.Living;

import java.util.HashSet;
import java.util.List;

public class SpongeMCLiving extends SpongeMCEntity implements MCLivingEntity {

	Living ent;
	public SpongeMCLiving(Living entity) {
		super(entity);
		this.ent = entity;
	}

	@Override
	public Living getHandle() {
		return ent;
	}

	@Override
	public void addEffect(int i, int i1, int i2, boolean b, boolean b1, Target target) {

	}

	@Override
	public boolean removeEffect(int i) {
		return false;
	}

	@Override
	public int getMaxEffect() {
		return 0;
	}

	@Override
	public List<MCEffect> getEffects() {
		return null;
	}

	@Override
	public void damage(double v) {

	}

	@Override
	public void damage(double v, MCEntity mcEntity) {

	}

	@Override
	public boolean getCanPickupItems() {
		return false;
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return false;
	}

	@Override
	public MCEntityEquipment getEquipment() {
		return null;
	}

	@Override
	public double getEyeHeight() {
		return 0;
	}

	@Override
	public double getEyeHeight(boolean b) {
		return 0;
	}

	@Override
	public MCLocation getEyeLocation() {
		return null;
	}

	@Override
	public double getHealth() {
		return 0;
	}

	@Override
	public MCPlayer getKiller() {
		return null;
	}

	@Override
	public double getLastDamage() {
		return 0;
	}

	@Override
	public MCEntity getLeashHolder() {
		return null;
	}

	@Override
	public MCLivingEntity getTarget(Target target) {
		return null;
	}

	@Override
	public MCBlock getTargetBlock(HashSet<Short> hashSet, int i, boolean b) {
		return null;
	}

	@Override
	public MCBlock getTargetBlock(HashSet<Byte> hashSet, int i) {
		return null;
	}

	@Override
	public List<MCBlock> getLastTwoTargetBlocks(HashSet<Byte> hashSet, int i) {
		return null;
	}

	@Override
	public List<MCBlock> getLineOfSight(HashSet<Byte> hashSet, int i) {
		return null;
	}

	@Override
	public boolean hasLineOfSight(MCEntity mcEntity) {
		return false;
	}

	@Override
	public double getMaxHealth() {
		return 0;
	}

	@Override
	public int getMaximumAir() {
		return 0;
	}

	@Override
	public int getMaximumNoDamageTicks() {
		return 0;
	}

	@Override
	public int getNoDamageTicks() {
		return 0;
	}

	@Override
	public int getRemainingAir() {
		return 0;
	}

	@Override
	public boolean isGliding() {
		return false;
	}

	@Override
	public boolean isLeashed() {
		return false;
	}

	@Override
	public boolean hasAI() {
		return false;
	}

	@Override
	public void resetMaxHealth() {

	}

	@Override
	public void setCanPickupItems(boolean b) {

	}

	@Override
	public void setRemoveWhenFarAway(boolean b) {

	}

	@Override
	public void setHealth(double v) {

	}

	@Override
	public void setLastDamage(double v) {

	}

	@Override
	public void setLeashHolder(MCEntity mcEntity) {

	}

	@Override
	public void setMaxHealth(double v) {

	}

	@Override
	public void setMaximumAir(int i) {

	}

	@Override
	public void setMaximumNoDamageTicks(int i) {

	}

	@Override
	public void setNoDamageTicks(int i) {

	}

	@Override
	public void setRemainingAir(int i) {

	}

	@Override
	public void setTarget(MCLivingEntity mcLivingEntity, Target target) {

	}

	@Override
	public void setGliding(Boolean aBoolean) {

	}

	@Override
	public void setAI(Boolean aBoolean) {

	}

	@Override
	public void kill() {

	}

	@Override
	public MCProjectile launchProjectile(MCProjectileType mcProjectileType) {
		return null;
	}

	@Override
	public MCProjectile launchProjectile(MCProjectileType mcProjectileType, Vector3D vector3D) {
		return null;
	}
}
