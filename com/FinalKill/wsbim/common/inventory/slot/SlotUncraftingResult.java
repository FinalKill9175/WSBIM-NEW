package com.FinalKill.wsbim.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.inventory.ContainerUncraftingTable;
import com.FinalKill.wsbim.common.inventory.InventoryUncraftMatrix;
import com.FinalKill.wsbim.util.UncraftingRecipes;

public class SlotUncraftingResult extends Slot {

	private InventoryUncraftMatrix inv;
	
	public SlotUncraftingResult(InventoryUncraftMatrix cont, IInventory p_i1824_1_, int p_i1824_2_,
			int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	inv = cont;
	}
	
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack){
		ItemStack[] stacks = inv.res.stackList;
		if(stacks !=null){
				for(int i = 0; i < stacks.length; i++){
					inv.res.stackList[i] = null;
				}	
			
		}
	}
	
	public boolean isItemValid(ItemStack item){
		return UncraftingRecipes.isItemStackUncraftable(item);
	}
    /**
     * Called when the stack in a Slot changes
     */
    public void onSlotChanged()
    {
      super.onSlotChanged();
      this.inv.eventHandler.onCraftMatrixChanged(inv);
    }
}
