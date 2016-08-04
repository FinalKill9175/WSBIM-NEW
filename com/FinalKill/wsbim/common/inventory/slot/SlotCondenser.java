package com.FinalKill.wsbim.common.inventory.slot;

import com.FinalKill.condenservalues.CondenserItemValues;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCondenser extends Slot {

	public SlotCondenser(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);

		
		
		
	}
	/**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return CondenserItemValues.doesItemStackHaveValue(par1ItemStack);
    }
}
