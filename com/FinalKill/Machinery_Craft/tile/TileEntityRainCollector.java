package com.FinalKill.Machinery_Craft.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRainCollector extends TileEntity implements IInventory{

	
	public float water;
	public float water_increment = 0.03F;
	public float max_water = 500.0F;
	public final int air_blocks_required_for_rain_water = 6;

	public ItemStack[] inv = new ItemStack[2];
	
	public void updateEntity(){
			
		if(!this.worldObj.isRemote){
		if(this.worldObj.isRaining()&&this.checkIfGettingRain(this.air_blocks_required_for_rain_water)){
			if(this.water<= (this.max_water - this.water_increment)){
			this.water+=this.water_increment;
			
				
							}
			else{
				this.water = this.max_water;
				
			}
					
		}
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (int) this.getWaterScaled(12), 2);
		
		
		
		if(inv[0] !=null){
			if(inv[0].getItem() == Items.water_bucket){
				
				if(this.water <=(this.max_water-16F*inv[0].stackSize)){
					
					this.water = this.water +16*inv[0].stackSize;
					inv[0] = new ItemStack(Items.bucket, inv[0].stackSize);
					
				}
				
			}
			
			
		}
		if(inv[1] !=null){
			if(inv[1].getItem() == Items.bucket){
				
				if(this.water >= 16F*inv[1].stackSize){
					
					this.water = this.water -16*inv[1].stackSize;
					inv[1] = new ItemStack(Items.water_bucket, inv[1].stackSize);
					
				}
				
			}
			
			
		}
		
		//System.out.println(this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
		//System.out.println(this.water);
		

		}
		
		/**
		if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == Block.blockRegistry.getObjectById(9)){
			
			if(this.water<=(this.max_water - 16F)){
				this.water=this.water+16F;
				worldObj.setBlock(xCoord, yCoord+1, zCoord, Blocks.air);
				
				
			}
			
			
		}
		*/
		
		
	
			
			
		
	}
	
	private void addWater(){
		this.water+=this.water_increment;
		
		
		
	}
	public float getWater(){
		
		return this.water;
	}
	
	public boolean checkIfGettingRain(int radious){
		
		for(int i=1; i<radious; i++){
			
			if(this.worldObj.getBlock(xCoord, yCoord+i, zCoord) !=Blocks.air){
				
				return false;
				
			}
			
		}
		return true; 
		
	}
	
	 @Override
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
	           this.water = tagCompound.getFloat("water");
	           
	         
	    }

	    @Override
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
	           tagCompound.setFloat("water", this.water);
	            
	         
	    }
	    
	    
	    public float getWaterScaled(int i){
	    	float water = this.water;
	    	if(water>0){
	    		
	    		return  ((water*i)/this.max_water);
	    		
	    	}
	    	else{
	    		return  0F;
	    	}
	    	
	    }

	    @Override
        public int getSizeInventory() {
                return inv.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
                return inv[slot];
        }
        
        @Override
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }               
        }

        @Override
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

        @Override
        public ItemStack getStackInSlotOnClosing(int slot) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                return stack;
        }
        
        @Override
        public int getInventoryStackLimit() {
                return 64;
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
                return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
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

				@Override
				public void openInventory() {
					// TODO Auto-generated method stub
					
				}
	
	
}
