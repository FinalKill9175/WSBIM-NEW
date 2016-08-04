package com.FinalKill.Machinery_Craft.tile;

import java.util.Random;

import scala.tools.nsc.doc.Settings;
import scala.tools.nsc.settings.MutableSettings.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.MachineryCraftAPI.ItemPower;
import com.FinalKill.MachineryCraftAPI.power.IConnectionInput;
import com.FinalKill.MachineryCraftAPI.power.IPowerConductor;
import com.FinalKill.MachineryCraftAPI.power.IPowerUsage;
import com.FinalKill.Machinery_Craft.blocks.BlockPowerFurnace;

public class TileEntityPowerFurnace extends TileEntity implements IInventory, IConnectionInput {
	
	public float power = 0.0F;
	public final float max_power = 2500.0F;
	public final float powerToTakeOnTick = 0.10F;
	
	
	public int cookTime;
	public int cookSpeed = 300;
	
	public ItemStack[] inv = new ItemStack[3];
	public ForgeDirection[] connections =new ForgeDirection[6];
	
	public void updateEntity(){
		this.updateConnections();
		if(!this.worldObj.isRemote){
			
			if(inv[0] !=null){
	    		if(this.isItemValidForPower(inv[0]) && this.getPowerForItem(inv[0]) !=0){
	    			
	    			if(this.power <= (this.max_power - this.getPowerForItem(inv[0]))){
	    			
	    				this.emptyPowerFromSlot(0);
	    			}
	    		}
			}
			
			
			if(inv[1] !=null){
				
				if(this.power>0){
				if(this.hasRecipe(inv[1])){
					
					
					
					if(this.inv[2] == null){
						
						++this.cookTime;
						this.power-=this.powerToTakeOnTick;
						if(this.cookTime == this.cookSpeed){
							
							this.smeltItemForFirstTime();
							this.cookTime = 0;
						}
						
					}
					
					else if(inv[2].getItem() == this.getSmeltingResult(inv[1]).getItem()){
					
						if(this.inv[2].stackSize+1<=this.getSmeltingResult(inv[1]).getMaxStackSize()){
						
							++this.cookTime;
							this.power-=this.powerToTakeOnTick;
							
							if(this.cookTime == this.cookSpeed){
								this.smeltItem();
								this.cookTime = 0;
								
								
						}
							
					
						
						
						}
						
					}
					
					
					
					
				}else this.cookTime = 0;
				
				}else this.cookTime = 0;
			}
			else this.cookTime = 0;
			
			
		
			if(this.cookTime == 1){
				
				this.playLoopNoise(getWorldObj(), xCoord, yCoord, zCoord, this.getWorldObj().rand);
			}
			
			if(this.hasPower() && this.isSmelting() && inv[1] !=null){
				
				BlockPowerFurnace.updateFurnaceBlockState(true, getWorldObj(), xCoord, yCoord, zCoord);
				
			
			}
			else BlockPowerFurnace.updateFurnaceBlockState(false, getWorldObj(), xCoord, yCoord, zCoord);
			
		}
		
	}
	public static void playLoopNoise(World world, int x, int y, int z, Random rand){
		world.playSoundEffect(x+0.5D, y+0.5D, z+0.5D, "machinery_craft:powerFurnace", 0.7F, (float) 1.0F);
		
	}

	public boolean hasPower(){
		
		return this.power>0;
	}
	
	
	public void smeltItemForFirstTime(){
		ItemStack result = this.getSmeltingResult(inv[1]);
		
		if(inv[1].stackSize == 1){
			inv[1].stackSize = 0;
			inv[1] = null;
			
		}
		else{
			inv[1].stackSize -=1;
    		
		}
	
	inv[2] = new ItemStack(result.getItem(), 1, result.getItemDamage());
	
	
		
	}
	
	public void smeltItem(){
		ItemStack result = this.getSmeltingResult(inv[1]);
		if(inv[1].stackSize>0){
			--inv[1].stackSize;
			
		}
		else{
			inv[1].stackSize = 0;
			inv[1] = null;
			
		}
		inv[2] = new ItemStack(result.getItem(), inv[2].stackSize+ 1, result.getItemDamage());
		
	}
	
	public ItemStack getSmeltingResult(ItemStack stack){
		
		return FurnaceRecipes.smelting().getSmeltingResult(stack);
		
	}
	
	public boolean hasRecipe(ItemStack item){
		
		return FurnaceRecipes.smelting().getSmeltingResult(item) !=null;
	}
	
	public boolean isSmelting(){
		
		return cookTime > 0;
		
	}
	  public static boolean isItemValidForPower(ItemStack stack){
			if(stack.getItem() instanceof ItemPower){
				return true;
				
			}
	    	
			else{
	    	return false;
			}
	    	
	    	
	    }
	public void emptyPowerFromSlot(int slot){
		
		
		if(inv[slot].getItem() instanceof ItemPower){
			if(!inv[slot].isItemStackDamageable()){
    			
	    		
    			if(inv[slot].stackSize == 1){
    				this.power +=this.getPowerForItem(inv[slot]);
    				--inv[slot].stackSize;
    				inv[slot] = null;
   
    				
    				
    				
    			}
    			else{
    				--inv[slot].stackSize;
    				this.power +=this.getPowerForItem(inv[slot]);
    				
    			}
    				
    			}
    			else{
    				
    				if(inv[slot].getItemDamage()< inv[slot].getMaxDamage()){
    					this.power +=this.getPowerForItem(inv[slot]);
    					inv[slot] = new ItemStack(inv[slot].getItem(),inv[slot].stackSize, inv[slot].getItemDamage()+1);
    				}
    				}
    				
		}
		
	}
	
	public float getPowerForItem(ItemStack itemStack) {
		return TileEntityMacerator.getPowerForItem(itemStack);
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
           this.cookTime = tagCompound.getInteger("cookTime");
         
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
            tagCompound.setInteger("cookTime", this.cookTime);
         
    }

public void updateConnections() {
	
	
	if((this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IConnectionInput)) connections  [0] = ForgeDirection.UP;
	else connections [0] = null;
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

public ForgeDirection[] getConnections() {
	return this.connections;
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
System.out.println("Closed Inventory");
	
}

@Override
public String getInventoryName() {
	
	return "container.powerFurnace";
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

public void openInventory() {
	System.out.println("Opened Inventory");
}

public int getPowerScaled(int i) {
	if(this.power>0){
		
		return (int) ((this.power*i)/this.max_power);
		
	}
	else return 0;
}
public int getTimeScaled(int i) {
if(this.cookTime >0) return (this.cookTime*i)/this.cookSpeed;
else return 0;
}

/**
public float getPower() {
	return this.power;
}
public void setPower(float power) {
this.power = power;
}
public void addPower(float power) {
	this.power +=power;
}
public void removePower(float power) {
	this.power-=power;
}
public float powerToUpdateOnTick() {
	return 0.5F;
}
public float getMaxPower() {
	return this.max_power;
}


*/

	
}
