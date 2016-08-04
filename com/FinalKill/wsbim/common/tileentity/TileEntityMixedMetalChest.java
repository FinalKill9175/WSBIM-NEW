package com.FinalKill.wsbim.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.util.ChestRegistry;
import com.FinalKill.wsbim.util.EnumChestType;
import com.FinalKill.wsbim.util.IUpgradedChest;

public class TileEntityMixedMetalChest extends TileEntity implements IUpgradedChest {

	 private ItemStack[] inv = new ItemStack[1000];

		public int numUsingPlayers;
		private int ticksSinceSync;
		public float lidAngle;
		public float prevLidAngle;
		private byte facing;
     
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
     public void readFromNBT(NBTTagCompound tagCompound) {
             super.readFromNBT(tagCompound);
             
             NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
             for (int i = 0; i < tagList.tagCount(); i++) {
                     NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                     int slot = tag.getInteger("Slot");
                     if (slot >= 0 && slot < inv.length) {
                             inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                     }
             }
     }

     @Override
     public void writeToNBT(NBTTagCompound tagCompound) {
             super.writeToNBT(tagCompound);
                             
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



	public String getInventoryName() {	
		return null;
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return false;
	}


	public EnumChestType getChestType() {
		// TODO Chest Type
		return EnumChestType.MIXEDMETAL;
	}

	public ItemStack[] getInventory() {
		return inv;
	}

	public Block getBlockInWorld() {
		return this.getWorldObj().getBlock(xCoord, yCoord, zCoord);
	}

	public float getPrevLidAngle() {
		return this.prevLidAngle;
	}

		public float getLidAngle() {
		return this.lidAngle;
	}
		 @Override
		    public void updateEntity()
		    {
		        super.updateEntity();
		      
		        /**
		        if(worldObj.getClosestPlayer(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, 8.0D) !=null){
		        if(worldObj.getClosestPlayer(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, 8.0D).openContainer instanceof ContainerMustardChest){
		        	this.numUsingPlayers = 1;
		        
		        }
		        else{
		        	this.numUsingPlayers = 0;
		        }
		        }
		        
		        else{
		        
		        this.numUsingPlayers  = 0;
		        }
		        */
		       //S this.lidAngle += 0.001F;
		        if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0)
		        {
		            worldObj.addBlockEvent(xCoord, yCoord, zCoord, ChestRegistry.getChestBlock(this.getChestType().getID()), 3, ((numUsingPlayers << 3) & 0xF8));
		        }
		
		         this.ticksSinceSync++;
		        prevLidAngle = lidAngle;
		        float f = 0.1F;
		        if (numUsingPlayers > 0 && lidAngle == 0.0F)
		        {
		            double d = (double) xCoord + 0.5D;
		            double d1 = (double) zCoord + 0.5D;
		            worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		        }
		        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F)
		        {
		            float f1 = lidAngle;
		            if (numUsingPlayers > 0)
		            {
		                lidAngle += f;
		            }
		            else
		            {
		                lidAngle -= f;
		            }
		            if (lidAngle > 1.0F)
		            {
		                lidAngle = 1.0F;
		            }
		            float f2 = 0.5F;
		            if (lidAngle < f2 && f1 >= f2)
		            {
		                double d2 = (double) xCoord + 0.5D;
		                double d3 = (double) zCoord + 0.5D;
		                worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		            }
		            if (lidAngle < 0.0F)
		            {
		                lidAngle = 0.0F;
		            }
		        }
		    }

		    @Override
		    public boolean receiveClientEvent(int i, int j)
		    {
		        if (i == 1)
		        {
		            numUsingPlayers = j;
		        }
		     
		        else if (i == 3)
		        {
		             numUsingPlayers = (j & 0xF8) >> 3;
		        }
		        return true;
		    }

		    @Override
		    public void openInventory()
		    {
		       numUsingPlayers++;
		        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ChestRegistry.getChestBlock(this.getChestType().getID()), 1, numUsingPlayers);
		    }

		    @Override
		    public void closeInventory()
		    {
		         numUsingPlayers--;
		        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ChestRegistry.getChestBlock(this.getChestType().getID()), 1, numUsingPlayers);
		    }

}
