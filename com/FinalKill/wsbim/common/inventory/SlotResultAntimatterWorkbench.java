package com.FinalKill.wsbim.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResultAntimatterWorkbench extends Slot {

	public SlotResultAntimatterWorkbench(IInventory p_i1824_1_, int p_i1824_2_,
			int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	
	public boolean isItemValid(ItemStack stack){
		return false;
	}
}
