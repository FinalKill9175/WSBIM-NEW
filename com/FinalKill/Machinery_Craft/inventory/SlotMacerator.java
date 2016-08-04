package com.FinalKill.Machinery_Craft.inventory;

import com.FinalKill.MachineryCraftAPI.MachineryCraftAPI;
import com.FinalKill.Machinery_Craft.items.ItemFurnaceUpgrade;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMacerator extends Slot {

	public SlotMacerator(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	
	/**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }	
	
}
