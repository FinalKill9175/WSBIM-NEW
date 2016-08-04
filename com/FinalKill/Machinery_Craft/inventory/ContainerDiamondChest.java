package com.FinalKill.Machinery_Craft.inventory;

import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDiamondChest extends Container {
	public TileEntityDiamondChest tileEntity;
	private int numRows;
	public ContainerDiamondChest(InventoryPlayer player, TileEntityDiamondChest tileentity){
		tileEntity = tileentity;
		this.numRows =tileentity.getSizeInventory() / 13;
for(int i = 0; i<8; i++){
			
			for(int j = 0; j<13; j++){
				
				this.addSlotToContainer(new Slot(tileentity, j+i*13,12+j*18, 16+i*18));
			}
			
			
		}

		for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(player, i, 49 + i*18 ,233));
		}
		for(int i = 0; i<3; i++){
					
					for(int j = 0; j<9; j++){
						
						this.addSlotToContainer(new Slot(player,9+j+i*9, 49+j*18, 175+i*18));
						
					}
					
					
				}

		
	}

	
	@Override
	public boolean canInteractWith(EntityPlayer arg0) {

		return tileEntity.isUseableByPlayer(arg0);
	}
	 /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

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
