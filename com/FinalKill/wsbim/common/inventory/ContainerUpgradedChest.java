package com.FinalKill.wsbim.common.inventory;

import com.FinalKill.wsbim.util.IUpgradedChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUpgradedChest extends Container{

	
	private IUpgradedChest chest;
	
	public ContainerUpgradedChest(InventoryPlayer player, IUpgradedChest chest) {
		
		
		this.chest = chest;
		chest.openInventory();
		for(int i = 0; i<chest.getChestType().getRows(); i++){
			for(int j = 0; j<chest.getChestType().getColumns(); j++){
				this.addSlotToContainer(new Slot(chest,j+i*chest.getChestType().getRows() * chest.getChestType().getColumns(), chest.getChestType().getInv_start_x()+j*18, chest.getChestType().getInv_start_y()+i*18));
			}
		}
		
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<9; j++){
				this.addSlotToContainer(new Slot(player,9+j+i*9, chest.getChestType().getPlayer_inv_start_x()+j*18, chest.getChestType().getPlayer_inv_start_y()+i*18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(player, i, chest.getChestType().getPlayer_inv_start_x() + i*18 ,chest.getChestType().getPlayer_inv_start_y() + 58));
		}
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return this.chest.isUseableByPlayer(p_75145_1_);
	}
	
	public void onContainerClosed(EntityPlayer fodhpvdhoiu){
		this.chest.closeInventory();
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

            if (p_82846_2_ < this.chest.getChestType().getRows() * this.chest.getChestType().getColumns())
            {
                if (!this.mergeItemStack(itemstack1, this.chest.getChestType().getRows() * this.chest.getChestType().getColumns(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.chest.getChestType().getRows() * this.chest.getChestType().getColumns(), false))
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
