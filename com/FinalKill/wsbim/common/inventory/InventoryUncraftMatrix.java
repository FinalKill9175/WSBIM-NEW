
package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryUncraftMatrix implements IInventory
{
    /** List of the stacks in the crafting matrix. */
    public ItemStack[] stackResult = new ItemStack[1];
    /** the width of the crafting inventory */
    private int inventoryWidth;
     private static final String __OBFID = "CL_00001743";
     public InventoryUncraftResult res;
     public Container eventHandler;

    public InventoryUncraftMatrix(Container p_i1807_1_, InventoryUncraftResult r)
    {
        int k = 3 * 3;
        this.inventoryWidth = 3;
        this.eventHandler = p_i1807_1_;
        res = r;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 1;
    }
    /**
    * Returns the stack in slot i
    */
   public ItemStack getStackInSlot(int p_70301_1_)
   {
       return this.stackResult[0];
   }

  

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return "container.crafting";
    }
    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        if (this.stackResult[0] != null)
        {
            ItemStack itemstack = this.stackResult[0];
            this.stackResult[0] = null;
           // this.eventHandler.onCraftMatrixChanged(this);
            return itemstack;
            
        }
        else
        {
        	 //this.eventHandler.onCraftMatrixChanged(this);

            return null;
        }
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.stackResult[0] != null)
        {
        	System.out.println("Derp");
            
            ItemStack itemstack = this.stackResult[0];
            this.stackResult[0] = null;
            this.eventHandler.onCraftMatrixChanged(this);
            return itemstack;
        }
        else
        {
        	return null;
        }
    }

   

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.stackResult[0] = p_70299_2_;
        this.eventHandler.onCraftMatrixChanged(this);
    }
    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    public void markDirty() {}

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }
}
