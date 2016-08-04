package com.FinalKill.Machinery_Craft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.MachineryCraftAPI.power.IConnectionInput;
import com.FinalKill.MachineryCraftAPI.power.IPowerConductor;
import com.FinalKill.MachineryCraftAPI.power.IPowerEmitter;
import com.FinalKill.Machinery_Craft.blocks.BlockGenerator;

public class TileEntityGenerator  extends TileEntity implements IConnectionInput, IInventory{

	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public float power = 0.0F;
	public float max_power = 5000.0F;
	
	public int burn_time = 0;
	public int toltal_burn_time;
	
	private int powerEmpty = 0;
	private int powerCharge = 1;
	private int burn = 2;

	public ItemStack[] inv = new ItemStack[3];
	
	public ForgeDirection[] getConnections() {
		return this.connections;
	}

	public void updateEntity(){
		this.updateConnections();
		if(!this.worldObj.isRemote){
			if(isGenerating()&&!isFull()){
				BlockGenerator.updateGeneratorBlockState(true, worldObj, xCoord, yCoord, zCoord);
				
				if(this.worldObj.rand.nextInt(70) == 2){
					
					
					
				}
				
				
			}
			else{
				BlockGenerator.updateGeneratorBlockState(false, worldObj, xCoord, yCoord, zCoord);
				
			}
			
			
			if(power>max_power)power = max_power;
			if(this.burn_time<0)this.burn_time = 0;
			//System.out.println(this.burn_time);
			//System.out.println(this.toltal_burn_time);
			//System.out.println(this.isGenerating());
			
			if(!isGenerating()&&inv[this.burn] !=null){
				
				if(TileEntityFurnace.getItemBurnTime(inv[this.burn])/20 <= (this.max_power-this.power)){
					
					if(this.inv[this.burn].stackSize>1){
						
					inv[this.burn].stackSize-=1;
					this.toltal_burn_time = TileEntityFurnace.getItemBurnTime(inv[burn]);
			
					
					this.burn_time = TileEntityFurnace.getItemBurnTime(inv[burn]);
					this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:machinery", 0.3F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));

					}
					else{
						this.toltal_burn_time = TileEntityFurnace.getItemBurnTime(inv[burn]);
						
						this.burn_time = TileEntityFurnace.getItemBurnTime(inv[burn]);
						this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:machinery", 0.3F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));

						inv[this.burn].stackSize=0;
						inv[burn] = null;
					}
					
					
				
				}
				
				
			}
else if(isGenerating()){
				
	
	this.generatePower();
	
			}
			else{
				this.toltal_burn_time = 0;
				
			}
			
		
			
		}
		
		if(inv[this.powerEmpty] !=null){
			
			
			if(inv[this.powerEmpty].isItemStackDamageable()){
				if(this.power <= (this.max_power - TileEntityMacerator.getPowerForItem(inv[this.powerEmpty]))){
		    		
				
				if(inv[this.powerEmpty].getItemDamage()<inv[this.powerEmpty].getMaxDamage()){
				inv[this.powerEmpty]= new ItemStack(inv[this.powerEmpty].getItem(), inv[powerEmpty].stackSize, inv[powerEmpty].getItemDamage()+1);
				this.power+=TileEntityMacerator.getPowerForItem(inv[powerEmpty]);
				}
				
			}
			}
			
			
			
		}
	if(inv[this.powerCharge] !=null){
			
			
			if(inv[this.powerCharge].isItemStackDamageable()){
				if(this.power >= (TileEntityMacerator.getPowerForItem(inv[this.powerCharge]))){
		    		
				
				if(inv[this.powerCharge].isItemDamaged()){
				inv[this.powerCharge]= new ItemStack(inv[this.powerCharge].getItem(), inv[powerCharge].stackSize, inv[powerCharge].getItemDamage()-1);
				this.power-=TileEntityMacerator.getPowerForItem(inv[powerCharge]);
				}
				
			}
			}
			
			
			
		}
		
		
		
	}
	
	
	
	private void generatePower() {


		if(this.isGenerating()&& (this.max_power-this.power)>=0.25F){
		burn_time-=4;
			
			if(this.burn_time >0){
			this.power+=1F;
			
			}
			else if(this.burn_time == 0){
				
				
			}
		}
		
		
		
	}

	public boolean isFull(){
		
		return !(this.power<=this.max_power-1) &&!(this.power==this.max_power-1);
	}
	
	public boolean isGenerating(){
		return this.toltal_burn_time>0 && this.burn_time>0;
		
	}
	
	public int getBurnTimeScaled(int i){
		if(this.isGenerating() && this.toltal_burn_time !=0){
			
			return (this.burn_time*i)/this.toltal_burn_time;
			
		}
		else{
			return 0;
		}
		
	}
	
	public float getPowerScaled(int i){
		
		if(power>0){
			
			return (this.power*i)/max_power;
			
		}
		else{
			return 0;
		}
	}
	
	
	 @Override
	    public void readFromNBT(NBTTagCompound tagCompound) {
	            super.readFromNBT(tagCompound);
	            
	            NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
	            for (int i = 0; i < tagList.tagCount(); i++) {
	                    NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
	                    byte slot = tag.getByte("Slot");
	                    if (slot >= 0 && slot < inv.length) {
	                            inv [slot] = ItemStack.loadItemStackFromNBT(tag);
	                    }
	            }
	            
	           this.power = tagCompound.getFloat("power");
	           this.burn_time = tagCompound.getInteger("burn_time");
	           this.toltal_burn_time = tagCompound.getInteger("toltal_burn");
	      
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
	            tagCompound.setFloat("power", this.power);
	            tagCompound.setInteger("burn_time", this.burn_time);
	            tagCompound.setInteger("toltal_burn", this.toltal_burn_time);
	         
	    }
	
	public void updateConnections() {
		
		
		if((this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IConnectionInput)) connections  [0] = ForgeDirection.UP;
		else connections[0] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IConnectionInput)) connections [1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IConnectionInput)) connections [2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if((this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IConnectionInput)) connections [3] = ForgeDirection.EAST;
		else connections[3] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IConnectionInput)) connections [4] = ForgeDirection.SOUTH;
		else connections[4] = null;
		if((this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IConnectionInput)) connections [5] = ForgeDirection.WEST;
		else connections[5] = null;

		
		
		
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

				/**
				public float getPower() {
					return this.power;
				}

				public void setPower(float power) {
				this.power = power;	
				}

				public float getMaxPower() {
					return this.max_power;
				}

				public void emitPowerToConductors() {
				ForgeDirection[] directions = this.getConnections();
					for(int i = 0; i<directions.length; i++){
						
						if(directions[i] !=null){
							
							if(this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ) instanceof IPowerConductor){
								
								IPowerConductor conductor = (IPowerConductor) this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ);
										
								
								if(conductor.getPower()<conductor.getMaxLoad() && this.getPower()>0){
									this.removePower(this.powerToUpdateOnTick());
									conductor.addPower(conductor.powerToUpdateOnTick());
								}
								
								
								
								
							}
							
						}
						
					}
					
				}

				public void addPower(float power) {
				this.power+=power;
				}
				public void removePower(float power) {
					this.power-=power;
				}

				public float powerToUpdateOnTick() {
					return 0.5F;
				}

		

*/



}
