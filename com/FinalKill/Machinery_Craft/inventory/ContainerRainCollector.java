package com.FinalKill.Machinery_Craft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.MachineryCraftAPI.ItemPower;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;

public class ContainerRainCollector extends Container {
	
	
	private TileEntityRainCollector rainCollector;
	
	private int lastWater;

	public ContainerRainCollector(InventoryPlayer player, TileEntityRainCollector tileentity) {
		rainCollector = tileentity;
for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(player, i, 8 + i*18 ,142));
		}
		for(int i = 0; i<3; i++){
			
			for(int j = 0; j<9; j++){
				
				this.addSlotToContainer(new Slot(player,9+j+i*9, 8+j*18, 84+i*18));
				
			}
			
			
		}
		
		this.addSlotToContainer(new SlotWater(rainCollector, 0 , 116, 62));
		this.addSlotToContainer(new SlotWater(rainCollector, 1 , 116, 8));

		}

	public boolean canInteractWith(EntityPlayer var1) {
		
		return rainCollector.isUseableByPlayer(var1);
		
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
				/**
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}
				*/

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				
			 if (itemstack1.getItem()  == Items.bucket)
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					/**
					if (!this.mergeItemStack(itemstack1, 30, 40, false))
					{
						
					return null;
					}
					*/
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			/**
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}
			*/

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
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, (int) this.rainCollector.water);
			
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

			if (this.lastWater != this.rainCollector.water)
			{
				icrafting.sendProgressBarUpdate(this, 0, (int) this.rainCollector.water);
			}
			
			
		}

		this.lastWater = (int) this.rainCollector.water;
	
	
	}

	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.rainCollector.water = par2;
		}
		
		

		
	}

}
