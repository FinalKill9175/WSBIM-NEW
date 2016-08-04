package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerSmallCrafting extends Container {
	
	private final EntityPlayer thePlayer;

	 /** The crafting matrix inventory. */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public IInventory craftResult = new InventoryCraftResult();
    /** Determines if inventory manipulation should be handled. */
    public boolean isLocalWorld;
  
    
	public ContainerSmallCrafting(EntityPlayer player) {
		this.thePlayer = player;
		 this.addSlotToContainer(new SlotCrafting(player, this.craftMatrix, this.craftResult, 0, (176 / 2 - (88 / 2) + 64), 36));

		
		for (int i = 0; i < 2; ++i)
	       {
	          for (int j = 0; j < 2; ++j)
	           {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, (176 / 2 - (88 / 2) + 8) + j * 18, 26 + i * 18));
	           }
	        }
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
        
        this.onCraftMatrixChanged(this.craftMatrix);
	}
	
    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.worldObj));
    }
    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

            if (itemstack != null)
            {
                p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
            }
        }

        this.craftResult.setInventorySlotContents(0, (ItemStack)null);
    }

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	public ItemStack transferStackInSlot(EntityPlayer player, int slot){
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                
                stack = stackInSlot.copy();

                
               if(slot < 5){
            	   if(!this.mergeItemStack(stackInSlot, 5, this.inventorySlots.size(), true)){
            		   return null;
            	   }
               }
    

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
	}
}
