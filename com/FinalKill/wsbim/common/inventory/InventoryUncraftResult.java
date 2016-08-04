package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryUncraftResult implements IInventory
{
    /** A list of one item containing the result of the crafting formula */
    public ItemStack[] stackList = new ItemStack[9];
    private static final String __OBFID = "CL_00001760";
    public Container eventHandler;
    
    public InventoryUncraftResult(Container cont){
    	eventHandler = cont;
    }
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 9;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int p_70301_1_)
    {
        return this.stackList[p_70301_1_];
    }
    /**
   

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return null;
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return false;
    }
    /**
     * Returns the itemstack in the slot specified (Top left is 0, 0). Args: row, column
     */
    public ItemStack getStackInRowAndColumn(int p_70463_1_, int p_70463_2_)
    {
        if (p_70463_1_ >= 0 && p_70463_1_ < 3)
        {
            int k = p_70463_1_ + p_70463_2_ * 3;
            return this.getStackInSlot(k);
        }
        else
        {
            return null;
        }
    }
    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        if (this.stackList[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.stackList[p_70298_1_].stackSize <= p_70298_2_)
            {
                itemstack = this.stackList[p_70298_1_];
                this.stackList[p_70298_1_] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = this.stackList[p_70298_1_].splitStack(p_70298_2_);

                if (this.stackList[p_70298_1_].stackSize == 0)
                {
                    this.stackList[p_70298_1_] = null;
                }

                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.stackList[p_70304_1_] != null)
        {
            ItemStack itemstack = this.stackList[p_70304_1_];
            this.stackList[p_70304_1_] = null;
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
        this.stackList[p_70299_1_] = p_70299_2_;

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
