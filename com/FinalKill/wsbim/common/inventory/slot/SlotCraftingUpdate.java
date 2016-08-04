package com.FinalKill.wsbim.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.inventory.ContainerAdvancedCraftingTable;

public class SlotCraftingUpdate extends SlotCrafting {

	ContainerAdvancedCraftingTable c;
	public SlotCraftingUpdate(ContainerAdvancedCraftingTable c, EntityPlayer p_i1823_1_, IInventory p_i1823_2_,
			IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_,
			int p_i1823_6_) {
		super(p_i1823_1_, p_i1823_2_, p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
		this.c = c;
		// TODO Auto-generated constructor stub
	}
	
	public void onSlotChanged(){
		super.onSlotChanged();
		
		this.c.closeContainer();
		this.c.onContainerOpened();
	}
	public void onSlotChange(ItemStack s, ItemStack s1){
		super.onSlotChange(s, s1);
		this.c.closeContainer();
		this.c.onContainerOpened();
	}
	

	
	public void onCrafting(ItemStack p_75210_1_, int p_75210_2_){
		super.onCrafting(p_75210_1_, p_75210_2_);
		
		this.c.closeContainer();
		this.c.onContainerOpened();
	}
	
	public void onPickupFromSlot(EntityPlayer p, ItemStack stack){
		super.onPickupFromSlot(p, stack);
		this.c.closeContainer();
		this.c.onContainerOpened();
	}
}
