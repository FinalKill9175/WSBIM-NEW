package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.item.ItemBackpack;

public class ContainerBackpack extends Container {

	public IInventory backpackInventory;
	private final ItemBackpack backpackItem;
	
	public ContainerBackpack(ItemStack backpack, EntityPlayer player) {
		this.backpackItem = (ItemBackpack)backpack.getItem();
		
		this.backpackInventory = new InventoryBackpack(backpack, backpackItem, player);
		
		int ySize = (18 + (backpackItem.backpackSize.rows * 18)) + 95;
		
		for(int i = 0; i<backpackItem.backpackSize.rows; i++){
			for(int j = 0; j<backpackItem.backpackSize.colums; j++){
				this.addSlotToContainer(new Slot(this.backpackInventory,j+i*backpackItem.backpackSize.rows *backpackItem.backpackSize.colums, 8+j*18, 18+i*18));
			}
		}
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18,  (ySize - 82) + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, ySize - 24));
        }
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	public void onContainerClosed(EntityPlayer p){
		super.onContainerClosed(p);
			this.backpackInventory.closeInventory();
		
	}
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ < this.backpackItem.backpackSize.rows * this.backpackItem.backpackSize.colums)
            {
                if (!this.mergeItemStack(itemstack1, this.backpackItem.backpackSize.rows * this.backpackItem.backpackSize.colums, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.backpackItem.backpackSize.rows * this.backpackItem.backpackSize.colums, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
