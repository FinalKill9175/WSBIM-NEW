package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFreezer extends Container {
	
	private final TileEntityFreezer freezer;
	
	private int lastLoopTime;
	private int lastTemp;

	public ContainerFreezer(InventoryPlayer player, TileEntityFreezer furn) {
	freezer = furn;
	for (int l = 0; l < 3; ++l)
    {
        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(player, i1 + l * 9 + 9, 8 + i1 * 18, 85 + l * 18));
        }
    }

    for (int l = 0; l < 9; ++l)
    {
        this.addSlotToContainer(new Slot(player, l, 8 + l * 18, 143));
    }
    
    this.addSlotToContainer(new Slot(furn, 0, 8, 53));
    
    
    for(int y = 0; y < 3; y++){
    	for(int x = 0; x < 7; x++){
    		this.addSlotToContainer(new Slot(furn, 1 + y + x * 7, 33 + x * 18, 17 + y * 18));
    	}
    }
	this.freezer.openInventory();
	}
	
	public void onContainerClosed(EntityPlayer player){
		super.onContainerClosed(player);
		this.freezer.closeInventory();
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return freezer.isUseableByPlayer(p_75145_1_);
	}
	 public void addCraftingToCrafters(ICrafting p_75132_1_)
	    {
	        super.addCraftingToCrafters(p_75132_1_);
	        p_75132_1_.sendProgressBarUpdate(this, 0, this.freezer.freezeLoop);
	        p_75132_1_.sendProgressBarUpdate(this, 1, (int) this.freezer.temp_int);
	       
	    }

	    /**
	     * Looks for changes made in the container, sends them to every listener.
	     */
	    public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int i = 0; i < this.crafters.size(); ++i)
	        {
	            ICrafting icrafting = (ICrafting)this.crafters.get(i);

	            if (this.lastLoopTime != this.freezer.freezeLoop)
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.freezer.freezeLoop);
	            }
	            if(this.lastTemp != (int) this.freezer.temp_int){
	                icrafting.sendProgressBarUpdate(this, 1, (int)this.freezer.temp_int);
	            }

	           
	        }

	        this.lastLoopTime = freezer.freezeLoop;
	        this.lastTemp = (int)freezer.temp_int;
	     
	    }

	    @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	    {
	        if (p_75137_1_ == 0)
	        {
	        	this.freezer.freezeLoop = p_75137_2_;
	        }

	        if(p_75137_1_ == 1){
	        	this.freezer.temp_int = p_75137_2_;
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

	            if (p_82846_2_ < (3 * 7) + 1)
	            {
	                if (!this.mergeItemStack(itemstack1, (3 * 7) + 1, this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 1, 3 * 7, false))
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
