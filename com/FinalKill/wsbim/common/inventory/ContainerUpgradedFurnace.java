package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.FinalKill.wsbim.common.tileentity.TileEntityUpgradedFurnace;
import com.FinalKill.wsbim.util.EnumFurnaceFunction;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerUpgradedFurnace extends Container {
	
	private final IUpgradedFurnace furnace;
	
	private int lastBurnTime;
	private int lastCookTime;
	private int lastCookTime2;
	private int lastCookTime3;
	private int lastCookTime4;
	
	private int lastItemBurnTime;

	public ContainerUpgradedFurnace(InventoryPlayer player, IUpgradedFurnace furn) {
	furnace = furn;
	
	
	if(furnace.isDoubleFurnace()){
		this.addSlotToContainer(new Slot(furn, furnace.fuel, 56, 53));

		this.addSlotToContainer(new Slot(furn, furnace.input, 47, 17));
				this.addSlotToContainer(new Slot(furn, furnace.input_2, 65, 17));
				this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output, 112+4, 31+4));

		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output_2, 138+4, 31+4));

	}
	if((furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.NORMAL)){
		this.addSlotToContainer(new Slot(furn, furnace.fuel, 56, 53));

		this.addSlotToContainer(new Slot(furn, furnace.input, 56, 17));
		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output, 112+4, 31+4));

		
	}
	if((furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE_FUNC)){
		this.addSlotToContainer(new Slot(furn, furnace.fuel, 8, 57));

		this.addSlotToContainer(new Slot(furn, furnace.input, 47, 22));
				this.addSlotToContainer(new Slot(furn, furnace.input_2, 47, 57));
				this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output, 111+4, 19+4));

		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output_2, 111+4, 54+4));

	
	}
	
	if(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
		this.addSlotToContainer(new Slot(furn, furnace.fuel, 8, 127));

		this.addSlotToContainer(new Slot(furn, furnace.input, 47, 22));
				this.addSlotToContainer(new Slot(furn, furnace.input_2, 47, 57));
				this.addSlotToContainer(new Slot(furn, furnace.input_3, 47, 92));
				this.addSlotToContainer(new Slot(furn, furnace.input_4, 47, 127));
				this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output, 111+4, 19+4));

		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output_2, 111+4, 54+4));
		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output_3, 111+4, 89+4));
		this.addSlotToContainer(new SlotFurnace(player.player, furn, furnace.output_4, 111+4, 124+4));
	}
	
	if(!(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC)){
	for(int i = 0; i<3; i++){
		for(int j = 0; j<9; j++){
			this.addSlotToContainer(new Slot(player,9+j+i*9, 8+j*18, 84+i*18));
		}
	}
	
	for(int i = 0; i < 9; i++){
		this.addSlotToContainer(new Slot(player, i, 8 + i*18 ,142));
	}
	}
	
	else{
		for(int i = 0; i<3; i++){
			for(int j = 0; j<9; j++){
				this.addSlotToContainer(new Slot(player,9+j+i*9, 8+j*18, 160+i*18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(player, i, 8 + i*18 ,218));
		}
		
	}
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return furnace.isUseableByPlayer(p_75145_1_);
	}
	 public void addCraftingToCrafters(ICrafting p_75132_1_)
	    {
	        super.addCraftingToCrafters(p_75132_1_);
	        p_75132_1_.sendProgressBarUpdate(this, 0, this.furnace.getCookTime());
	        p_75132_1_.sendProgressBarUpdate(this, 1, this.furnace.getBurnTime());
	        p_75132_1_.sendProgressBarUpdate(this, 2, this.furnace.getItemBurnTime());
	        p_75132_1_.sendProgressBarUpdate(this, 3, this.furnace.getSecondCookTime());
	        
	        if(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
	            p_75132_1_.sendProgressBarUpdate(this, 4, this.furnace.getThirdCookTime());
		        p_75132_1_.sendProgressBarUpdate(this, 5, this.furnace.getFourthCookTime());

	        }
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

	            if (this.lastCookTime != this.furnace.getCookTime())
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.furnace.getCookTime());
	            }

	            if (this.lastBurnTime != this.furnace.getBurnTime())
	            {
	                icrafting.sendProgressBarUpdate(this, 1, this.furnace.getBurnTime());
	            }

	            if (this.lastItemBurnTime != this.furnace.getItemBurnTime())
	            {
	                icrafting.sendProgressBarUpdate(this, 2, this.furnace.getItemBurnTime());
	            }
	            if(furnace.getFurnaceType().getFurnaceFunction() != EnumFurnaceFunction.NORMAL){
	            if(this.lastCookTime2 != this.furnace.getSecondCookTime()){
	            	icrafting.sendProgressBarUpdate(this, 3, this.furnace.getSecondCookTime());
	            }
	            
	            
	            }
	            
	            if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
	            	icrafting.sendProgressBarUpdate(this, 4, this.furnace.getThirdCookTime());
	            	icrafting.sendProgressBarUpdate(this, 5, this.furnace.getFourthCookTime());

	            }
	        }

	        this.lastCookTime = this.furnace.getCookTime();
	        this.lastBurnTime = this.furnace.getBurnTime();
	        this.lastItemBurnTime = this.furnace.getItemBurnTime();
	        this.lastCookTime2 = this.furnace.getSecondCookTime();
	        this.lastCookTime3 = this.furnace.getThirdCookTime();
	        this.lastCookTime4 = this.furnace.getFourthCookTime();
	    }

	    @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	    {
	        if (p_75137_1_ == 0)
	        {
	            this.furnace.setCookTime(p_75137_2_);
	        }

	        if (p_75137_1_ == 1)
	        {
	            this.furnace.setBurnTime(p_75137_2_);
	        }

	        if (p_75137_1_ == 2)
	        {
	            this.furnace.setItemBurnTime(p_75137_2_);
	        }
	        if(furnace.getFurnaceType().getFurnaceFunction() != EnumFurnaceFunction.NORMAL){
	        if(p_75137_1_ == 3){
	        	this.furnace.setSecondCookTime(p_75137_2_);
	        }
	        
	        if(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
	        	if(p_75137_1_ == 4){
	        		this.furnace.setThirdCookTime(p_75137_2_);
	        	}
	        	if(p_75137_1_ == 5){
	        		this.furnace.setFourthCookTime(p_75137_2_);
	        	}
	        	
	        }
	        }
	    }
	    
	    /**
	     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	     */
	    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	    {
	    if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE || furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE_FUNC){
	    	  ItemStack stack = null;
              Slot slotObject = (Slot) inventorySlots.get(slot);

              //null checks and checks if the item can be stacked (maxStackSize > 1)
              if (slotObject != null && slotObject.getHasStack()) {
                      ItemStack stackInSlot = slotObject.getStack();
                      
                      stack = stackInSlot.copy();

                      //merges the item into player inventory since its in the tileEntity
                      if (slot < 5)
                      {
                          if (!this.mergeItemStack(stackInSlot, 5, 40, true))
                          {
                              return null;
                          }
                         
                         slotObject.onSlotChange(stackInSlot, stack);
                      }
                      //places it into the tileEntity is possible since its in the player inventory
                      else{
                    	  if(TileEntityFurnace.isItemFuel(stackInSlot)){
                    		  if(!this.mergeItemStack(stackInSlot, 0, 1, false)){
                    			  return null;
                    		  }
                    	  }
                    	  if(FurnaceRecipes.smelting().getSmeltingResult(stackInSlot) !=null){
                    		  if(!this.mergeItemStack(stackInSlot, 1, 3, false)){
                    			  return null;
                    		  }
                    		  
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
	    if(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.NORMAL){
	    	
	    	 ItemStack stack = null;
             Slot slotObject = (Slot) inventorySlots.get(slot);

             //null checks and checks if the item can be stacked (maxStackSize > 1)
             if (slotObject != null && slotObject.getHasStack()) {
                     ItemStack stackInSlot = slotObject.getStack();
                     stack = stackInSlot.copy();

                     //merges the item into player inventory since its in the tileEntity
                     if (slot < 3) {
                             if (!this.mergeItemStack(stackInSlot, 3, 39, true)) {
                                     return null;
                             }
                             slotObject.onSlotChange(stackInSlot, stack);
                           
                     }
                     //places it into the tileEntity is possible since its in the player inventory
                     else{
                   	  if(TileEntityFurnace.isItemFuel(stackInSlot)){
                   		  if(!this.mergeItemStack(stackInSlot, 0, 1, false)){
                   			  return null;
                   		  }
                   	  }
                   	  if(FurnaceRecipes.smelting().getSmeltingResult(stackInSlot) !=null){
                   		  if(!this.mergeItemStack(stackInSlot, 1, 2, false)){
                   			  return null;
                   		  }
                   		  
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
	    
	    if(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
	    	
	    	 ItemStack stack = null;
             Slot slotObject = (Slot) inventorySlots.get(slot);

             //null checks and checks if the item can be stacked (maxStackSize > 1)
             if (slotObject != null && slotObject.getHasStack()) {
                     ItemStack stackInSlot = slotObject.getStack();
                     
                     stack = stackInSlot.copy();

                     //merges the item into player inventory since its in the tileEntity
                     if (slot < 9)
                     {
                         if (!this.mergeItemStack(stackInSlot, 9, 45, true))
                         {
                             return null;
                         }
                        
                        slotObject.onSlotChange(stackInSlot, stack);
                     }
                     //places it into the tileEntity is possible since its in the player inventory
                     else{
                   	  if(TileEntityFurnace.isItemFuel(stackInSlot)){
                   		  if(!this.mergeItemStack(stackInSlot, 0, 1, false)){
                   			  return null;
                   		  }
                   	  }
                   	  if(FurnaceRecipes.smelting().getSmeltingResult(stackInSlot) !=null){
                   		  if(!this.mergeItemStack(stackInSlot, 1, 5, false)){
                   			  return null;
                   		  }
                   		  
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
		return null;
	    }
}
