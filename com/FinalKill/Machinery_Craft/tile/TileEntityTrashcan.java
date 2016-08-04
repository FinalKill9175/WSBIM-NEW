package com.FinalKill.Machinery_Craft.tile;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrashcan extends TileEntity implements IInventory {
	public float field_145972_a;
    public float field_145975_i;
    public int field_145973_j;
    private int field_145974_k;
        private ItemStack[] inv;
	

        public TileEntityTrashcan(){
                inv = new ItemStack[10];
        }
        public void updateEntity()
        {
        	deleteItem();
        }
        
        public void deleteItem(){
        	if(inv[1] !=null){
        		inv[1] = null;
        		
        	}
        	
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
               
                return null;
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
        }

       
        public void writeToNBT(NBTTagCompound tagCompound) {
                super.writeToNBT(tagCompound);
                
        }

     
        @Override
		public void openInventory() {

        	  		}
        @Override
		public void closeInventory() {
		
        			}

		@Override
		public String getInventoryName() {
					
			return null;
		}

				@Override
				public boolean hasCustomInventoryName() {
				
					return false;
				}

				@Override
				public boolean isItemValidForSlot(int var1, ItemStack var2) {
					// TODO Auto-generated method stub
					return false;
				}

				
}