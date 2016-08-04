package com.FinalKill.wsbim.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.common.inventory.ContainerAdvancedCraftingTable;

public class TileEntityAdvancedCraftingTable extends TileEntity {
 
    public ContainerAdvancedCraftingTable container;

	 public ItemStack[] matrixStacks = new ItemStack[9];
	 public ItemStack resultStack;
    
	public void updateEntity(){
		if(!worldObj.isRemote){
		for(int i = 0 ; i < this.matrixStacks.length; i++){
				if(matrixStacks[i] !=null){
					if(matrixStacks[i].stackSize == 0){
						this.matrixStacks[i] = null;
					}
				}
				
				
				
			}
		}
		
	
	}
	
	public EntityPlayer getClosestPlayer(){
		return this.getWorldObj().getClosestPlayer(xCoord, yCoord, zCoord, -1);
	}
	
	
	public void readFromNBT(NBTTagCompound nbt){
	    super.readFromNBT(nbt);
        NBTTagList tagList = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < this.matrixStacks.length) {
                        this.matrixStacks[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }
		
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.matrixStacks.length; i++) {
                ItemStack stack = this.matrixStacks[i];
                if (stack != null) {
                        NBTTagCompound tag = new NBTTagCompound();
                        tag.setByte("Slot", (byte) i);
                        stack.writeToNBT(tag);
                        itemList.appendTag(tag);
                }
        }

        nbt.setTag("Inventory", itemList);
		
	}
	
}
