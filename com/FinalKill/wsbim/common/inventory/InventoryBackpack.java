package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.FinalKill.wsbim.common.item.ItemBackpack;

public class InventoryBackpack implements IInventory{
	
	private final ItemStack backpackStack;
	private final ItemBackpack backpackItem;
	
	private final EntityPlayer thePlayer;
	
	public final ItemStack[] inv;
	
	public InventoryBackpack(ItemStack backpack, ItemBackpack backpackItem, EntityPlayer player){
		this.backpackStack = backpack;
		this.backpackItem = backpackItem;
		this.thePlayer = player;
		this.inv = new ItemStack[1000];
		this.openInventory();
	}
	
	public int getSizeInventory() {
		return backpackItem.backpackSize.inv_size;
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
		
	}

	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	public void openInventory() {
		
		if(this.backpackStack.hasTagCompound()){
			if(this.backpackStack.stackTagCompound.hasKey("Inventory")){
				this.readFromNBT(this.backpackStack.stackTagCompound);
			}
		}
		
		else{
			this.backpackStack.setTagCompound(new NBTTagCompound());
		}
		
	}

	
	 public void readFromNBT(NBTTagCompound tagCompound) {
            
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
	public void closeInventory() {
		this.writeToNBT(this.backpackStack.stackTagCompound);
	}

	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

}
