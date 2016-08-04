package com.FinalKill.wsbim.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.util.recipe.AntimatterWorkbenchCrafting;

public class TileEntityAntimatterWorkbench extends TileEntity implements IInventory{
	
	public ItemStack[] inv = new ItemStack[14];

	public int progressTime = 0;
	public int fuelTime = 0;
	public final int maxFuel = 1500;
	public final int processTime = 100;
	
	public final int fuelSlot = 12;
	public final int outputSlot = 13;
	
	public void updateEntity(){
		super.updateEntity();
		if(!worldObj.isRemote){
			this.updateWorkbench();
		}
	}
	
	public void updateWorkbench(){
		
		if(fuelTime < 0){
			fuelTime = 0;
		}
		
		if(progressTime < 0){
			progressTime = 0;
		}
		
		if(inv[this.fuelSlot] !=null){
			int fuelVal = AntimatterWorkbenchCrafting.getValueForStack(inv[fuelSlot].getItem());
			if(fuelVal > 0){
				if((fuelTime + fuelVal) <= this.maxFuel){
					if(inv[fuelSlot].stackSize > 1){
						inv[fuelSlot].stackSize--;
						fuelTime += fuelVal;
					}
					
					else if(inv[fuelSlot].stackSize == 1){
						inv[fuelSlot] = inv[fuelSlot].getItem().getContainerItem(inv[fuelSlot]);
						fuelTime += fuelVal;
					}
				}
			}
		}
		
		if(this.fuelTime > 0){
			ItemStack stack0 = inv[0];
			ItemStack stack1 = inv[1];
			ItemStack stack2 = inv[2];
			ItemStack stack3 = inv[3];
			ItemStack stack4 = inv[4];
			ItemStack stack5 = inv[5];
			ItemStack stack6 = inv[6];
			ItemStack stack7 = inv[7];
			ItemStack stack8 = inv[8];
			ItemStack stack9 = inv[9];
			ItemStack stack10 = inv[10];
			ItemStack stack11 = inv[11];
			
			boolean isNullStack0 = stack0 == null;
			boolean isNullStack1 = stack1 ==null;
			boolean isNullStack2 = stack2 ==null;
			boolean isNullStack3 = stack3 ==null;
			boolean isNullStack4 = stack4 ==null;
			boolean isNullStack5 = stack5 ==null;
			boolean isNullStack6 = stack6 ==null;
			boolean isNullStack7 = stack7 ==null;
			boolean isNullStack8 = stack8 ==null;
			boolean isNullStack9 = stack9 ==null;
			boolean isNullStack10 = stack10 ==null;
			boolean isNullStack11 = stack11 ==null;
			
			if(!isNullStack0 && !isNullStack1 && !isNullStack2 && !isNullStack3 && !isNullStack4 && !isNullStack5 && !isNullStack6 && !isNullStack7 && !isNullStack8 && !isNullStack9 && !isNullStack10 && !isNullStack11){
				ItemStack output = AntimatterWorkbenchCrafting.getOuputForIngredients(stack0, stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9, stack10, stack11);
				if(output !=null && (inv[outputSlot] == null) || (inv[outputSlot] !=null && inv[outputSlot].getItem() == output.getItem() && (inv[outputSlot].stackSize + output.stackSize <= 64))){
					if(progressTime == processTime){
						for(int i = 0; i < 12; i++){
							ItemStack stack = inv[i];
							if(stack !=null){
								if(stack.stackSize > 1){
									inv[i].stackSize--;
								}
								else if(stack.stackSize == 1){
									inv[i] = inv[i].getItem().getContainerItem(stack);
								}
							}
						}
						this.progressTime = 0;
						
						if(inv[outputSlot] == null){
							inv[outputSlot] = output.copy();
						}
						else if(inv[outputSlot] !=null){
							inv[outputSlot].stackSize ++;
						}
						
					}
					
					if(progressTime < processTime){
						++progressTime;
						--fuelTime;
					}
					
				}
				else{
					progressTime = 0;
				}
			}
			else{
				progressTime = 0;
			}
			
			if(progressTime > processTime){
				progressTime = 0;
			}
	
		}
		else{
			this.progressTime = 0;
		}
		
	}
	
	public boolean isFuel(Item stack){
		return AntimatterWorkbenchCrafting.isItemFuel(stack);
	}
	
	
	public boolean isGettingRedstoneSignal(){
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
	
	public int getSizeInventory() {
		return inv.length;
	}

	public ItemStack getStackInSlot(int p_70301_1_) {
		return inv[p_70301_1_];
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
	public String getInventoryName() {
		return null;
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void markDirty() {
		super.markDirty();
	}

	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	
	 public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            this.fuelTime = tagCompound.getInteger("fuelTime");
            this.progressTime = tagCompound.getInteger("progressTime");
            
             NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
             for (int i = 0; i < tagList.tagCount(); i++) {
                     NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                     int slot = tag.getInteger("Slot");
                     if (slot >= 0 && slot < inv.length) {
                             inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                     }
             }
     }

     public void writeToNBT(NBTTagCompound tagCompound) {
             super.writeToNBT(tagCompound);
             
             tagCompound.setInteger("fuelTime", fuelTime);
             tagCompound.setInteger("progressTime", progressTime);
             
             NBTTagList itemList = new NBTTagList();
             for (int i = 0; i < inv.length; i++) {
                     ItemStack stack = inv[i];
                     if (stack != null) {
                             NBTTagCompound tag = new NBTTagCompound();
                             tag.setInteger("Slot", (int) i);
                             stack.writeToNBT(tag);
                             itemList.appendTag(tag);
                     }
             }
             tagCompound.setTag("Inventory", itemList);
     }

	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	public void openInventory() {
		
	}

	public void closeInventory() {
		
	}
	
	public int getFuelScaled(int i){
		return (fuelTime*i) / maxFuel;
	}
	
	public int getProgressScaled(int i){
		return (progressTime * i) / processTime;
	}
}
