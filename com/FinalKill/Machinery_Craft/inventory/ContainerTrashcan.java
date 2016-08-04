package com.FinalKill.Machinery_Craft.inventory;

import java.util.List;

import com.FinalKill.Machinery_Craft.tile.TileEntityTrashcan;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrashcan extends Container {
	private TileEntityTrashcan t;
	private int numRows = 0;

	public ContainerTrashcan(InventoryPlayer inventory,
			TileEntityTrashcan tileentity) {
		t = tileentity;
		
for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(inventory, i, 8 + i*18 ,86));
		}
		for(int i = 0; i<3; i++){
			
			for(int j = 0; j<9; j++){
				
				this.addSlotToContainer(new Slot(inventory,9+j+i*9, 8+j*18, 28+i*18));
				
			}
			
			
		}
		
		this.addSlotToContainer(new Slot(tileentity, 1, 80, 7));
		
			}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return t.isUseableByPlayer(var1);
	}
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
	 public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	    {
	        ItemStack var3 = null;
	        Slot var4 = (Slot)((List) this.inventorySlots).get(par2);

	        if (var4 != null && var4.getHasStack())
	        {
	            ItemStack var5 = var4.getStack();
	            var3 = var5.copy();

	            if (par2 < this.numRows * 9)
	            {
	                if (!this.mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(var5, 0, this.numRows * 9, false))
	            {
	                return null;
	            }

	            if (var5.stackSize == 0)
	            {
	                var4.putStack((ItemStack)null);
	            }
	            else
	            {
	                var4.onSlotChanged();
	            }
	        }

	        return var3;
	    }
}
