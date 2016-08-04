package com.FinalKill.wsbim.common.inventory;

import com.FinalKill.wsbim.common.inventory.slot.SlotFuel;
import com.FinalKill.wsbim.common.tileentity.TileEntityAntimatterWorkbench;
import com.FinalKill.wsbim.util.EnumFurnaceFunction;
import com.FinalKill.wsbim.util.recipe.AntimatterWorkbenchCrafting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAntimatterWorkbench extends Container {

	private TileEntityAntimatterWorkbench tile;
	private int lastProgressTime;
	private int lastFuelTime;
	
	public ContainerAntimatterWorkbench(InventoryPlayer player, TileEntityAntimatterWorkbench tile) {
		this.tile = tile;
		
		this.addSlotToContainer(new Slot(tile,0, 34+0*18, 17+0*18));
		this.addSlotToContainer(new Slot(tile,1, 34+1*18, 17+0*18));
		this.addSlotToContainer(new Slot(tile,2, 34+2*18, 17+0*18));
		this.addSlotToContainer(new Slot(tile,3, 34+3*18, 17));
		this.addSlotToContainer(new Slot(tile,4, 34+0*18, 17+1*18));
		this.addSlotToContainer(new Slot(tile,5, 34+1*18, 17+1*18));
		this.addSlotToContainer(new Slot(tile,6, 34+2*18, 17+1*18));
		this.addSlotToContainer(new Slot(tile,7, 34+3*18, 17+1*18));
		this.addSlotToContainer(new Slot(tile,8, 34+0*18, 17+2*18));
		this.addSlotToContainer(new Slot(tile,9, 34+1*18, 17+2*18));
		this.addSlotToContainer(new Slot(tile,10, 34+2*18, 17+2*18));
		this.addSlotToContainer(new Slot(tile,11, 34+3*18, 17+2*18));
		
		this.addSlotToContainer(new SlotFuel(tile, 12, 8, 53));
		
		this.addSlotToContainer(new SlotResultAntimatterWorkbench(tile, 13, 144 + 4, 32 + 4));
		
		
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(player, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(player, l, 8 + l * 18, 142));
        }
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	 public void addCraftingToCrafters(ICrafting p_75132_1_)
	    {
	        super.addCraftingToCrafters(p_75132_1_);
	        p_75132_1_.sendProgressBarUpdate(this, 0, this.tile.progressTime);
	        p_75132_1_.sendProgressBarUpdate(this, 1, this.tile.fuelTime);
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

	            if (this.lastProgressTime != this.tile.progressTime)
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.tile.progressTime);
	            }

	            if (this.lastFuelTime != this.tile.fuelTime)
	            {
	                icrafting.sendProgressBarUpdate(this, 1, this.tile.fuelTime);
	            }
	        }
	           
	        this.lastProgressTime = this.tile.progressTime;
	        this.lastFuelTime = this.tile.fuelTime;
	      
	    }

	    @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	    {
	        if (p_75137_1_ == 0)
	        {
	            this.tile.progressTime = p_75137_2_;
	        }

	        if (p_75137_1_ == 1)
	        {
	            this.tile.fuelTime = p_75137_2_;
	        }

	       
	        
	    }
	    /**
	     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	     */
	    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int slot)
	    {
	    	 ItemStack stack = null;
	         Slot slotObject = (Slot) inventorySlots.get(slot);

	         //null checks and checks if the item can be stacked (maxStackSize > 1)
	         if (slotObject != null && slotObject.getHasStack()) {
	                 ItemStack stackInSlot = slotObject.getStack();
	                 stack = stackInSlot.copy();

	                 //merges the item into player inventory since its in the tileEntity
	                 if (slot < 14) {
	                         if (!this.mergeItemStack(stackInSlot, 14, this.inventorySlots.size(), true)) {
	                                 return null;
	                         }
	                 }
	                 //places it into the tileEntity is possible since its in the player inventory
	               //  else if (!this.mergeItemStack(stackInSlot, 0, 12, false)) {
	               //          return null;
	                // }

	                 else if(AntimatterWorkbenchCrafting.isItemFuel(stackInSlot.getItem())){
	                	 if(!this.mergeItemStack(stackInSlot, 12, 13, false)){
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
	                 slotObject.onPickupFromSlot(p_82846_1_, stackInSlot);
	         }
	         return stack;
	    }
}
