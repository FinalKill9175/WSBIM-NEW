package com.FinalKill.wsbim.common.inventory;

import com.FinalKill.wsbim.common.inventory.slot.SlotUncraftingMatrix;
import com.FinalKill.wsbim.common.inventory.slot.SlotUncraftingResult;
import com.FinalKill.wsbim.util.UncraftingRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerUncraftingTable extends Container {
	
	private final World worldObj;
	private final int x;
	private final int y;
	private final int z;
	

	public InventoryUncraftResult craftResult = new InventoryUncraftResult(this);
	public InventoryUncraftMatrix craftMatrix = new InventoryUncraftMatrix(this, craftResult);
		
	public ContainerUncraftingTable(InventoryPlayer inventory, World world, int x, int y, int z) {
		this.worldObj = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.addSlotToContainer(new SlotUncraftingResult(this.craftMatrix, this.craftMatrix, 0, 34, 35));

	     for (int l = 0; l < 3; ++l)
	        {
	            for (int i1 = 0; i1 < 3; ++i1)
	            {
	               this.addSlotToContainer(new SlotUncraftingMatrix(inventory.player, this.craftMatrix, this.craftResult, i1 + l * 3, 94 + i1 * 18, 18 + l * 18));
	            }
	        }

		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(inventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
        }
		this.onCraftMatrixChanged(craftMatrix);
		}
   
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	 /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftResult.getStackInSlotOnClosing(i);

                if (itemstack != null && this.craftMatrix.getStackInSlot(0) == null)
                {
                    p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
                }
                
            }
           
           ItemStack[] stacks = this.craftResult.stackList; 
           if(stacks[0] == null && stacks[1] == null && stacks[2] == null && stacks[3] == null && stacks[4] == null && stacks[5] == null && stacks[6] == null && stacks[7] == null && stacks[8] == null){
        	   if(this.craftMatrix.getStackInSlot(0) !=null){
        		   p_75134_1_.dropPlayerItemWithRandomChoice(this.craftMatrix.getStackInSlot(0), false);
        	   }
           }
        }
    }
    
    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
    	super.onCraftMatrixChanged(p_75130_1_);
        if(this.craftMatrix.getStackInSlot(0) !=null){
        	ItemStack[] rec = UncraftingRecipes.getCraftingGridForResult(this.craftMatrix.getStackInSlot(0));
        	if(rec !=null){
        		for(int i = 0; i < rec.length; i++){
        			if(rec[i] !=null){
        				this.craftResult.setInventorySlotContents(i, rec[i].copy());
        			}
        		}
        	}
        }
    	
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

            if (p_82846_2_ == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, this.inventorySlots.size(), true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 10 && p_82846_2_ < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 14 && p_82846_2_ < 13+(9*4))
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

}
