package com.FinalKill.Machinery_Craft.inventory;

import com.FinalKill.Machinery_Craft.tile.TileEntityIronFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerIronFurnace extends Container {

	private TileEntityIronFurnace furnace;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	public ContainerIronFurnace(InventoryPlayer player, TileEntityIronFurnace tileentity) {
		furnace = tileentity;
for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(player, i, 8 + i*18 ,142));
		}
		for(int i = 0; i<3; i++){
			
			for(int j = 0; j<9; j++){
				
				this.addSlotToContainer(new Slot(player,9+j+i*9, 8+j*18, 84+i*18));
				
			}
			
			
		}
	
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 53));
        
		 this.addSlotToContainer(new Slot(tileentity, 1, 56, 17));
	        this.addSlotToContainer(new SlotFurnace(player.player, tileentity, 2, 116, 35));

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

            if (par2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 1 && par2 != 0)
            {
               
                 if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 3, 39, false))
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

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
	 public void addCraftingToCrafters(ICrafting par1ICrafting)
	    {
	        super.addCraftingToCrafters(par1ICrafting);
	        par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
	        par1ICrafting.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
	        par1ICrafting.sendProgressBarUpdate(this, 2, this.furnace.itemBurnTime);

	   	    }
	 

	    /**
	     * Looks for changes made in the container, sends them to every listener.
	     */
	    public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
	        {
	        	
	        	
	            ICrafting var2 = (ICrafting)this.crafters.get(var1);

	            if (this.lastCookTime != this.furnace.cookTime)
	            {
	                var2.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
	            }

	            if (this.lastBurnTime != this.furnace.burnTime)
	            {
	                var2.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
	            }

	            
	            if (this.lastItemBurnTime != this.furnace.itemBurnTime)
	            {
	                var2.sendProgressBarUpdate(this, 2, this.furnace.itemBurnTime);
	            }
	            
	        }

	        this.lastCookTime = this.furnace.cookTime;
	        this.lastBurnTime = this.furnace.burnTime;
	        this.lastItemBurnTime = this.furnace.itemBurnTime;
	    }
	    
	    public void updateProgressBar(int par1, int par2)
	    {
	        if (par1 == 0)
	        {
	            this.furnace.cookTime = par2;
	        }

	        if (par1 == 1)
	        {
	            this.furnace.burnTime = par2;
	        }

	        if (par1 == 2)
	        {
	            this.furnace.itemBurnTime = par2;
	        }
	    }
}
