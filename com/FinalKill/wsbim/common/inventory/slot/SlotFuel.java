package com.FinalKill.wsbim.common.inventory.slot;

import com.FinalKill.wsbim.util.recipe.AntimatterWorkbenchCrafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFuel extends Slot {

	public SlotFuel(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_,
			int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	
	}

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return AntimatterWorkbenchCrafting.isItemFuel(p_75214_1_.getItem());
    }
}
