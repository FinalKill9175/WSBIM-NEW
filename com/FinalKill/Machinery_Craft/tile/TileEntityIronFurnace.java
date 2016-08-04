package com.FinalKill.Machinery_Craft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityIronFurnace extends TileEntity implements IInventory {

		public int burnTime;
		public int itemBurnTime;
		public int cookTime;
		public int toltalCookTime = 280;
		public int burnTimeRatio = 2;
		
		public int fuelSlot = 0;
		public int cookSlot = 1;
		public int bakedSlot = 2;
	
		private boolean canSmelt;
	
        private ItemStack[] inv = new ItemStack[3];

        public TileEntityIronFurnace(){
            
        }
        public void updateEntity(){
        	/**
        	if(inv[this.fuelSlot] !=null){
        		
        		if(TileEntityFurnace.getFuelValue(inv[this.fuelSlot])>0){
        			
        			this.itemBurnTime = TileEntityFurnace.getFuelValue(inv[fuelSlot])*this.burnTimeRatio;
        			
        		}
        		
        	}
        	else{
        		
        		this.itemBurnTime = 0;
        	}
        	*/
        	if(this.worldObj.isRemote){
        	if(this.itemBurnTime >0  && this.isBurning()){
        		
        		--this.burnTime;
        	}
        	if(this.itemBurnTime > 0 && !this.isBurning()){
        		this.itemBurnTime = 0;
        		
        	}
       
        	
        	if(inv[this.cookSlot] !=null){
        		
        		if(FurnaceRecipes.smelting().getSmeltingResult(inv[this.cookSlot]) !=null){
        			
        			
        			if(inv[this.bakedSlot] == FurnaceRecipes.smelting().getSmeltingResult(inv[this.cookSlot])){
        				if(this.isBurning()){
        					++this.cookTime;
        					System.out.println(this.cookTime);
        					if(this.cookTime == this.toltalCookTime){
        						this.smeltItem();
        						this.cookTime = 0;
        						
        						
        					}
    						}
        				if(this.burnTime == 0 && inv[this.fuelSlot] !=null){
    						this.itemBurnTime  = TileEntityFurnace.getItemBurnTime(inv[fuelSlot]) * this.burnTimeRatio;
    						
    							
    							if(inv[fuelSlot].stackSize==1){
    							
    								inv[fuelSlot].stackSize = 0;
    								inv[fuelSlot] = null;
    							
    							}
    							else{
    									inv[fuelSlot].stackSize -=1;
    							}
    							
    						
    						
    						this.burnTime = this.itemBurnTime;
    					}
        					
        				
        				
        			}
        			else if(this.inv[this.bakedSlot] == null){
        				System.out.println(this.cookTime+"h");
    					
        						if(this.isBurning()){
            					++this.cookTime;
            					if(this.cookTime == this.toltalCookTime){
            						this.smeltItemInNewSlot();
            						this.cookTime = 0;
            						
            						
            					}
        						}
            					if(this.burnTime == 0 && inv[this.fuelSlot] !=null){
            						this.itemBurnTime  = TileEntityFurnace.getItemBurnTime(inv[fuelSlot]) * this.burnTimeRatio;
            						
            							
            							if(inv[fuelSlot].stackSize==1){
            							
            								inv[fuelSlot].stackSize = 0;
            								inv[fuelSlot] = null;
            							
            							}
            							else{
            									inv[fuelSlot].stackSize -=1;
            							}
            							
            						
            						
            						this.burnTime = this.itemBurnTime;
            					}
            					
            					
            				
        				
        			}
        		
        			
        		}
        		else{
    				this.cookTime = 0;
    			}
        		
        	}
        	else{
				this.cookTime = 0;
			}
        	
        	if(this.burnTime<0){
        		this.burnTime = 0;
        		
        	}
                System.out.println(this.burnTime);	
        	}
        }
        
        public boolean isSmelting(){
        	
        	return this.cookTime>0;
        }
        
        public boolean isBurning(){
        	
        	return this.burnTime >0;
        }
        
        public void smeltItemInNewSlot(){
        	
        	ItemStack recipe = FurnaceRecipes.smelting().getSmeltingResult(inv[this.cookSlot]);
        			inv[bakedSlot] = recipe;
                	
        	if(inv[cookSlot].stackSize == 1){
        		
        		inv[cookSlot].stackSize = 0;
        		inv[cookSlot] = null;
        	
        	}
        	else{
        			inv[cookSlot].stackSize-=1;
        	}
        	
        }
        public void smeltItem(){
        	
if(inv[cookSlot].stackSize == 1){
        		
        		inv[cookSlot].stackSize = 0;
        		inv[cookSlot] = null;
        	
        	}
        	else{
        			inv[cookSlot].stackSize-=1;
        	}
inv[this.cookSlot].stackSize +=1;
        }
        
        public int getBurnTimeScaled(int i){
        	if(this.isBurning())return (this.burnTime*i)/this.itemBurnTime;
        	else return 0;
        	
        	
        }
      
        public int getProgressScaled(int i){
        	if(this.isSmelting()) return (this.cookTime*i)/this.toltalCookTime;
        	else return 0;
        	
        }
        
        public int getSizeInventory() {
                return inv.length;
        }

        public ItemStack getStackInSlot(int slot) {
                return inv[slot];
        }
        
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }               
        }

         public ItemStack decrStackSize(int slot, int amt) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        if (stack.stackSize <= amt) {
                                setInventorySlotContents(slot, null);
                        } else {
                                stack = stack.splitStack(amt);
                                if (stack.stackSize == 0) {
                                        setInventorySlotContents(slot, null);
                                }
                        }
                }
                return stack;
        }

        public ItemStack getStackInSlotOnClosing(int slot) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                return stack;
        }
        
        public int getInventoryStackLimit() {
                return 64;
        }

        public boolean isUseableByPlayer(EntityPlayer player) {
                return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
        }

        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound);
                
                NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
                for (int i = 0; i < tagList.tagCount(); i++) {
                        NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                        byte slot = tag.getByte("Slot");
                        if (slot >= 0 && slot < inv.length) {
                                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                        }
                }
                this.cookTime = tagCompound.getInteger("CookTime");
                this.burnTime = tagCompound.getInteger("BurnTime");
                this.itemBurnTime = tagCompound.getInteger("ItemBurnTime");
        }

        public void writeToNBT(NBTTagCompound tagCompound) {
                super.writeToNBT(tagCompound);
                                
                NBTTagList itemList = new NBTTagList();
                for (int i = 0; i < inv.length; i++) {
                        ItemStack stack = inv[i];
                        if (stack != null) {
                                NBTTagCompound tag = new NBTTagCompound();
                                tag.setByte("Slot", (byte) i);
                                stack.writeToNBT(tag);
                                itemList.appendTag(tag);
                        }
                }
                tagCompound.setTag("Inventory", itemList);
                tagCompound.setInteger("CookTime", this.cookTime);
                tagCompound.setInteger("BurnTime", this.burnTime);
                tagCompound.setInteger("ItemBurnTime", this.itemBurnTime);
                
        }


				public void closeInventory() {
		
					
				}

				public String getInventoryName() {
					
					return null;
				}

				
				public boolean hasCustomInventoryName() {
				
					return false;
				}

				public boolean isItemValidForSlot(int var1, ItemStack var2) {
					return false;
				}
					public void openInventory() {
						
				}
}