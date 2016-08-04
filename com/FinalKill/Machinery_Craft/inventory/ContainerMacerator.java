package com.FinalKill.Machinery_Craft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.MachineryCraftAPI.ItemPower;
import com.FinalKill.MachineryCraftAPI.MachineryCraftAPI;
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;

public class ContainerMacerator extends Container {
	
	public TileEntityMacerator macerator;
	private int lastMacerateTime;
	private int lastPower;
	private int lastSpeed;
	
	public ContainerMacerator(InventoryPlayer player, TileEntityMacerator tileentity){
		
		macerator = tileentity;
		
for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(player, i, 8 + i*18 ,142));
		}
		for(int i = 0; i<3; i++){
			
			for(int j = 0; j<9; j++){
				
				this.addSlotToContainer(new Slot(player,9+j+i*9, 8+j*18, 84+i*18));
				
			}
			
			
		}
		
		this.addSlotToContainer(new Slot(tileentity, 0, 80  , 34 ));
		this.addSlotToContainer(new SlotMacerator(tileentity, 1, 136 + 4, 31 + 4));
		this.addSlotToContainer(new Slot(tileentity, 2, 35, 7));
	
	}
	

	public boolean canInteractWith(EntityPlayer var1) {
		return macerator.isUseableByPlayer(var1);
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, macerator.maceratingTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, (int) macerator.power);
		par1ICrafting.sendProgressBarUpdate(this, 2, (int) macerator.maceratingSpeed);
			
	}
	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				
			 if (itemstack1.getItem() instanceof ItemPower)
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return null;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
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

			if (this.lastMacerateTime != macerator.maceratingTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, macerator.maceratingTime);
			}
			if (this.lastPower != macerator.power)
			{
				icrafting.sendProgressBarUpdate(this, 1, (int) macerator.power);
			}
			if (this.lastSpeed != macerator.maceratingSpeed)
			{
				icrafting.sendProgressBarUpdate(this, 2, (int) macerator.maceratingSpeed);
			}

			
		}

		this.lastMacerateTime = macerator.maceratingTime;
		this.lastPower = (int) macerator.power;
		this.lastSpeed = macerator.maceratingSpeed;

	
	}

	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			macerator.maceratingTime = par2;
		}
		
		if(par1 == 1){
			
			macerator.power = par2;
		}
		
if(par1 == 2){
			
			macerator.maceratingSpeed = par2;
		}
		

		
	}
}
