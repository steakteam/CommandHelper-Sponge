package com.laytonsmith.abstraction.sponge.entities;

import com.laytonsmith.PureUtilities.Vector3D;
import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCEntityEquipment;
import com.laytonsmith.abstraction.MCInventory;
import com.laytonsmith.abstraction.MCInventoryView;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCMetadataValue;
import com.laytonsmith.abstraction.MCNote;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.MCPlayerInventory;
import com.laytonsmith.abstraction.MCPlugin;
import com.laytonsmith.abstraction.MCProjectile;
import com.laytonsmith.abstraction.MCScoreboard;
import com.laytonsmith.abstraction.MCServer;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.enums.MCDamageCause;
import com.laytonsmith.abstraction.enums.MCEntityEffect;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.abstraction.enums.MCGameMode;
import com.laytonsmith.abstraction.enums.MCInstrument;
import com.laytonsmith.abstraction.enums.MCProjectileType;
import com.laytonsmith.abstraction.enums.MCSound;
import com.laytonsmith.abstraction.enums.MCTeleportCause;
import com.laytonsmith.abstraction.enums.MCWeather;
import com.laytonsmith.abstraction.events.MCEntityDamageEvent;
import com.laytonsmith.core.constructs.Target;
import org.spongepowered.api.entity.living.player.Player;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class SpongeMCPlayer implements MCPlayer {

	Player player;

	public SpongeMCPlayer(Player player) {
		this.player = player;
	}

	@Override
	public Player getHandle() {
		return player;
	}

	@Override
	public boolean canSee(MCPlayer mcPlayer) {
		return false;
	}

	@Override
	public void chat(String s) {

	}

	@Override
	public InetSocketAddress getAddress() {
		return getHandle().getConnection().getAddress();
	}

	@Override
	public boolean getAllowFlight() {
		return false;
	}

	@Override
	public MCLocation getCompassTarget() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public float getExp() {
		return 0;
	}

	@Override
	public float getFlySpeed() {
		return 0;
	}

	@Override
	public void setFlySpeed(float v) {

	}

	@Override
	public MCPlayerInventory getInventory() {
		return null;
	}

	@Override
	public MCItemStack getItemAt(Integer integer) {
		return null;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public String getPlayerListName() {
		return null;
	}

	@Override
	public long getPlayerTime() {
		return 0;
	}

	@Override
	public MCWeather getPlayerWeather() {
		return null;
	}

	@Override
	public int getRemainingFireTicks() {
		return 0;
	}

	@Override
	public MCScoreboard getScoreboard() {
		return null;
	}

	@Override
	public int getTotalExperience() {
		return 0;
	}

	@Override
	public int getExpToLevel() {
		return 0;
	}

	@Override
	public int getExpAtLevel() {
		return 0;
	}

	@Override
	public MCEntity getSpectatorTarget() {
		return null;
	}

	@Override
	public float getWalkSpeed() {
		return 0;
	}

	@Override
	public void setWalkSpeed(float v) {

	}

	@Override
	public void giveExp(int i) {

	}

	@Override
	public boolean isSneaking() {
		return false;
	}

	@Override
	public boolean isSprinting() {
		return false;
	}

	@Override
	public void kickPlayer(String s) {

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
	public boolean isLeashed() {
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
	public void kill() {

	}

	@Override
	public void resetPlayerTime() {

	}

	@Override
	public void resetPlayerWeather() {

	}

	@Override
	public void sendTexturePack(String s) {

	}

	@Override
	public void sendResourcePack(String s) {

	}

	@Override
	public void setAllowFlight(boolean b) {

	}

	@Override
	public void setCompassTarget(MCLocation mcLocation) {

	}

	@Override
	public void setDisplayName(String s) {

	}

	@Override
	public void setExp(float v) {

	}

	@Override
	public void setFlying(boolean b) {

	}

	@Override
	public void setLevel(int i) {

	}

	@Override
	public void setPlayerListName(String s) {

	}

	@Override
	public void setPlayerTime(Long aLong, boolean b) {

	}

	@Override
	public void setPlayerWeather(MCWeather mcWeather) {

	}

	@Override
	public void setRemainingFireTicks(int i) {

	}

	@Override
	public void setScoreboard(MCScoreboard mcScoreboard) {

	}

	@Override
	public void setSpectatorTarget(MCEntity mcEntity) {

	}

	@Override
	public void setTempOp(Boolean aBoolean) throws Exception {

	}

	@Override
	public void setTotalExperience(int i) {

	}

	@Override
	public void setVanished(boolean b, MCPlayer mcPlayer) {

	}

	@Override
	public boolean isNewPlayer() {
		return false;
	}

	@Override
	public String getHost() {
		return null;
	}

	@Override
	public void sendBlockChange(MCLocation mcLocation, int i, byte b) {

	}

	@Override
	public void sendSignTextChange(MCLocation mcLocation, String[] strings) {

	}

	@Override
	public boolean eject() {
		return false;
	}

	@Override
	public void fireEntityDamageEvent(MCDamageCause mcDamageCause) {

	}

	@Override
	public int getEntityId() {
		return 0;
	}

	@Override
	public float getFallDistance() {
		return 0;
	}

	@Override
	public int getFireTicks() {
		return 0;
	}

	@Override
	public MCEntityDamageEvent getLastDamageCause() {
		return null;
	}

	@Override
	public MCLocation getLocation() {
		return null;
	}

	@Override
	public MCLocation asyncGetLocation() {
		return null;
	}

	@Override
	public int getMaxFireTicks() {
		return 0;
	}

	@Override
	public List<MCEntity> getNearbyEntities(double v, double v1, double v2) {
		return null;
	}

	@Override
	public MCEntity getPassenger() {
		return null;
	}

	@Override
	public void playNote(MCLocation mcLocation, MCInstrument mcInstrument, MCNote mcNote) {

	}

	@Override
	public void playSound(MCLocation mcLocation, MCSound mcSound, float v, float v1) {

	}

	@Override
	public void playSound(MCLocation mcLocation, String s, float v, float v1) {

	}

	@Override
	public int getFoodLevel() {
		return 0;
	}

	@Override
	public void setFoodLevel(int i) {

	}

	@Override
	public float getSaturation() {
		return 0;
	}

	@Override
	public void setSaturation(float v) {

	}

	@Override
	public float getExhaustion() {
		return 0;
	}

	@Override
	public void setExhaustion(float v) {

	}

	@Override
	public void setBedSpawnLocation(MCLocation mcLocation, boolean b) {

	}

	@Override
	public void sendPluginMessage(String s, byte[] bytes) {

	}

	@Override
	public void sendMessage(String s) {

	}

	@Override
	public MCServer getServer() {
		return null;
	}

	@Override
	public int getTicksLived() {
		return 0;
	}

	@Override
	public MCEntityType getType() {
		return null;
	}

	@Override
	public UUID getUniqueId() {
		return null;
	}

	@Override
	public MCEntity getVehicle() {
		return null;
	}

	@Override
	public Vector3D getVelocity() {
		return null;
	}

	@Override
	public void setVelocity(Vector3D vector3D) {

	}

	@Override
	public MCWorld getWorld() {
		return null;
	}

	@Override
	public boolean isDead() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isInsideVehicle() {
		return false;
	}

	@Override
	public boolean isOnGround() {
		return false;
	}

	@Override
	public boolean leaveVehicle() {
		return false;
	}

	@Override
	public void playEffect(MCEntityEffect mcEntityEffect) {

	}

	@Override
	public void remove() {

	}

	@Override
	public void setFallDistance(float v) {

	}

	@Override
	public void setFireTicks(int i) {

	}

	@Override
	public void setLastDamageCause(MCEntityDamageEvent mcEntityDamageEvent) {

	}

	@Override
	public boolean setPassenger(MCEntity mcEntity) {
		return false;
	}

	@Override
	public void setTicksLived(int i) {

	}

	@Override
	public boolean teleport(MCEntity mcEntity) {
		return false;
	}

	@Override
	public boolean teleport(MCEntity mcEntity, MCTeleportCause mcTeleportCause) {
		return false;
	}

	@Override
	public boolean teleport(MCLocation mcLocation) {
		return false;
	}

	@Override
	public boolean teleport(MCLocation mcLocation, MCTeleportCause mcTeleportCause) {
		return false;
	}

	@Override
	public void setCustomName(String s) {

	}

	@Override
	public String getCustomName() {
		return null;
	}

	@Override
	public void setCustomNameVisible(boolean b) {

	}

	@Override
	public boolean isCustomNameVisible() {
		return false;
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public MCGameMode getGameMode() {
		return null;
	}

	@Override
	public MCItemStack getItemInHand() {
		return null;
	}

	@Override
	public MCItemStack getItemOnCursor() {
		return null;
	}

	@Override
	public long getFirstPlayed() {
		return 0;
	}

	@Override
	public long getLastPlayed() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public MCPlayer getPlayer() {
		return null;
	}

	@Override
	public boolean hasPlayedBefore() {
		return false;
	}

	@Override
	public boolean isBanned() {
		return false;
	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public boolean isWhitelisted() {
		return false;
	}

	@Override
	public void setBanned(boolean b) {

	}

	@Override
	public void setWhitelisted(boolean b) {

	}

	@Override
	public MCLocation getBedSpawnLocation() {
		return null;
	}

	@Override
	public UUID getUniqueID() {
		return null;
	}

	@Override
	public int getSleepTicks() {
		return 0;
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean isSleeping() {
		return false;
	}

	@Override
	public MCInventoryView openEnchanting(MCLocation mcLocation, boolean b) {
		return null;
	}

	@Override
	public MCInventoryView openInventory(MCInventory mcInventory) {
		return null;
	}

	@Override
	public MCInventoryView getOpenInventory() {
		return null;
	}

	@Override
	public MCInventory getEnderChest() {
		return null;
	}

	@Override
	public MCInventoryView openWorkbench(MCLocation mcLocation, boolean b) {
		return null;
	}

	@Override
	public void setGameMode(MCGameMode mcGameMode) {

	}

	@Override
	public void setItemInHand(MCItemStack mcItemStack) {

	}

	@Override
	public void setItemOnCursor(MCItemStack mcItemStack) {

	}

	@Override
	public boolean isOp() {
		return false;
	}

	@Override
	public boolean hasPermission(String s) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String s) {
		return false;
	}

	@Override
	public List<String> getGroups() {
		return null;
	}

	@Override
	public boolean inGroup(String s) {
		return false;
	}

	@Override
	public void setOp(boolean b) {

	}

	@Override
	public boolean isFlying() {
		return false;
	}

	@Override
	public void updateInventory() {

	}

	@Override
	public List<MCMetadataValue> getMetadata(String s) {
		return null;
	}

	@Override
	public boolean hasMetadata(String s) {
		return false;
	}

	@Override
	public void removeMetadata(String s, MCPlugin mcPlugin) {

	}

	@Override
	public void setMetadata(String s, MCMetadataValue mcMetadataValue) {

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
