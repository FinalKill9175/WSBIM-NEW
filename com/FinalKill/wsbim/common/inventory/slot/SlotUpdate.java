package com.FinalKill.wsbim.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.inventory.ContainerAdvancedCraftingTable;

public class SlotUpdate extends Slot {
	ContainerAdvancedCraftingTable craft;
	
	public SlotUpdate(ContainerAdvancedCraftingTable craft, IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_,
			int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.craft = craft;
	
	}

	
	public void onSlotChanged(){
		super.onSlotChanged();
		this.craft.closeContainer();
		this.craft.onContainerOpened();
	}
	
	public void onSlotChange(ItemStack s, ItemStack s1){
		super.onSlotChange(s, s1);
		this.craft.closeContainer();
		this.craft.onContainerOpened();
	}
	
	public void onCrafting(ItemStack p_75210_1_, int p_75210_2_){
		super.onCrafting(p_75210_1_, p_75210_2_);
		
		this.craft.closeContainer();
		this.craft.onContainerOpened();
		
	}
	
	public void onPickupFromSlot(EntityPlayer p, ItemStack stack){
		super.onPickupFromSlot(p, stack);
		this.craft.closeContainer();
		this.craft.onContainerOpened();
	}
}
