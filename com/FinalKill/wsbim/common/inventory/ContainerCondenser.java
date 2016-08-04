package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.FinalKill.wsbim.common.inventory.slot.SlotCondenser;
import com.FinalKill.wsbim.common.tileentity.TileEntityCondenser;

public class ContainerCondenser extends Container {

	private int numRows;
	private TileEntityCondenser condenser;
	private int lastCV;
	private int lastMaxCV;
	public ContainerCondenser(InventoryPlayer player, TileEntityCondenser tileentity) {
condenser = tileentity;
numRows = 7;
tileentity.openInventory();
		for(int i = 0; i<7; i++){
			
			for(int j = 0; j<13; j++){
				
				this.addSlotToContainer(new Slot(tileentity, i+j*13, 12+(j*18), 34+(i*18)));
			}
			
		}
		
			
for(int i = 0; i < 9; i++){
			
			this.addSlotToContainer(new Slot(player, i, 48 + i*18 ,233));
		}
		for(int i = 0; i<3; i++){
					
					for(int j = 0; j<9; j++){
						
						this.addSlotToContainer(new Slot(player,9+j+i*9, 48+j*18, 175+i*18));
						
					}
					
					
				}
		
		this.addSlotToContainer(new SlotCondenser(tileentity, 199, 21,13));

		
		
		
	}
	
	public void onContainerClosed(EntityPlayer player){
		condenser.closeInventory();
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 10, condenser.cv);
		par1ICrafting.sendProgressBarUpdate(this, 11, condenser.max_cv);
		
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
   	if (this.lastCV != condenser.cv)
			{
				icrafting.sendProgressBarUpdate(this, 10, condenser.cv);
			}

			if (this.lastMaxCV != condenser.max_cv)
			{
				icrafting.sendProgressBarUpdate(this, 11, condenser.max_cv);
			}
		}

		this.lastCV = condenser.cv;
		this.lastMaxCV = condenser.max_cv;
	
	}
	
	 public void updateProgressBar(int par1, int par2)
	    {
	        if (par1 == 10)
	        {
	            this.condenser.cv = par2;
	        }

	        if (par1 == 11)
	        {
	            this.condenser.max_cv = par2;
	        }

	       
	    }


	public boolean canInteractWith(EntityPlayer var1) {

		return true;
	}
 /**
	     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	     */
	    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	    {
	        ItemStack var3 = null;
	        Slot var4 = (Slot)this.inventorySlots.get(par2);

	        if (var4 != null && var4.getHasStack())
	        {
	            ItemStack var5 = var4.getStack();
	            var3 = var5.copy();

	            if (par2 < this.numRows * 9)
	            {
	                if (!this.mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(var5, 0, this.numRows * 9, false))
	            {
	                return null;
	            }

	            if (var5.stackSize == 0)
	            {
	                var4.putStack((ItemStack)null);
	            }
	            else
	            {
	                var4.onSlotChanged();
	            }
	        }

	        return var3;
	    }

}
