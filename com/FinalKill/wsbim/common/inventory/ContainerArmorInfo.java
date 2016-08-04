package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArmorInfo extends Container {

	private final EntityPlayer thePlayer;
	
	private IInventory armorInventory;
	
	public ContainerArmorInfo(EntityPlayer player) {
		this.thePlayer = player;
		armorInventory = new InventoryArmor(thePlayer);
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
        
        for(int j = 0; j < 4; j++){
        	final int k = j;
        	this.addSlotToContainer(new Slot(armorInventory, j, 176 / 2 - 72/2 + 1, 8 + j * 18){
        		 
                     private static final String __OBFID = "CL_00001755";
                     /**
                      * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                      * in the case of armor slots)
                      */
                     public int getSlotStackLimit()
                     {
                         return 1;
                     }
                     /**
                      * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                      */
                     public boolean isItemValid(ItemStack p_75214_1_)
                     {
                         if (p_75214_1_ == null) return false;
                         return p_75214_1_.getItem().isValidArmor(p_75214_1_, k, thePlayer);
                     }
                     /**
                      * Returns the icon index on items.png that is used as background image of the slot.
                      */
                     @SideOnly(Side.CLIENT)
                     public IIcon getBackgroundIconIndex()
                     {
                         return ItemArmor.func_94602_b(k);
                     }
        	});
        }
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	public void onContainerClosed(EntityPlayer p){
		super.onContainerClosed(p);
		
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot){
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                
                stack = stackInSlot.copy();

                
                //merges the item into player inventory since its in the tileEntity
                if(slot <=(9*4 + 4) && slot>=(9*4)){
                	if(!this.mergeItemStack(stackInSlot, 0, (9*4), false)){
                		return null;
                	}
                }
                
                
                //places it into the tileEntity is possible since its in the player inventory
                else if (stack.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get((9*4)+((ItemArmor)stack.getItem()).armorType)).getHasStack())
                {
                    int j = (9*4)+((ItemArmor)stack.getItem()).armorType;

                    if (!this.mergeItemStack(stackInSlot, j, j + 1, true))
                    {
                        return null;
                    }
                }

    

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
	}
}
