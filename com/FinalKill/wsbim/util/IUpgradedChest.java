package com.FinalKill.wsbim.util;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IUpgradedChest extends IInventory {
	public EnumChestType getChestType();
	public ItemStack[] getInventory();
	public Block getBlockInWorld();
	public float getPrevLidAngle();
	public float getLidAngle();
}
