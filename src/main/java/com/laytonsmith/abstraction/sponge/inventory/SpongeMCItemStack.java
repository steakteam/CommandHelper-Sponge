package com.laytonsmith.abstraction.sponge.inventory;

import com.laytonsmith.abstraction.MCEnchantment;
import com.laytonsmith.abstraction.MCItemMeta;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCMaterialData;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;

public class SpongeMCItemStack implements MCItemStack {

	ItemStack item;

	public SpongeMCItemStack(ItemStack itemStack) {
		this.item = itemStack;
	}

	public SpongeMCItemStack(ItemType itemType) {
		this(ItemStack.builder().itemType(itemType).build());
	}

	@Override
	public MCMaterialData getData() {
		return null;
	}

	@Override
	public short getDurability() {
		return 0;
	}

	@Override
	public int getTypeId() {
		return 0;
	}

	@Override
	public void setDurability(short i) {

	}

	@Override
	public void addEnchantment(MCEnchantment mcEnchantment, int i) {

	}

	@Override
	public void addUnsafeEnchantment(MCEnchantment mcEnchantment, int i) {

	}

	@Override
	public Map<MCEnchantment, Integer> getEnchantments() {
		return null;
	}

	@Override
	public void removeEnchantment(MCEnchantment mcEnchantment) {

	}

	@Override
	public MCMaterial getType() {
		return null;
	}

	@Override
	public void setTypeId(int i) {

	}

	@Override
	public int maxStackSize() {
		return 0;
	}

	@Override
	public int getAmount() {
		return 0;
	}

	@Override
	public void setData(int i) {

	}

	@Override
	public boolean hasItemMeta() {
		return false;
	}

	@Override
	public MCItemMeta getItemMeta() {
		return null;
	}

	@Override
	public void setItemMeta(MCItemMeta mcItemMeta) {

	}

	@Override
	public Object getHandle() {
		return null;
	}
}
