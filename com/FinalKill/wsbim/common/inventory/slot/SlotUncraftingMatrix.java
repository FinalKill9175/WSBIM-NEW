package com.FinalKill.wsbim.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import cpw.mods.fml.common.FMLCommonHandler;

public class SlotUncraftingMatrix extends Slot
{
    /** The craft matrix inventory linked to this result slot. */
    private final IInventory craftResult;
    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;
    private static final String __OBFID = "CL_00001761";

    public SlotUncraftingMatrix(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_)
    {
        super(p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
        this.thePlayer = p_i1823_1_;
        this.craftResult = p_i1823_2_;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int p_75209_1_)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(p_75209_1_, this.getStack().stackSize);
        }

        return super.decrStackSize(p_75209_1_);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_)
    {
        this.amountCrafted += p_75210_2_;
        this.onCrafting(p_75210_1_);
    }





    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.onCrafting(p_82870_2_);

      
            ItemStack itemstack1 = this.craftResult.getStackInSlot(0);
           
            p_82870_2_.stackSize--;
            
            if (itemstack1 != null)
            {
                this.craftResult.decrStackSize(0, 1);

                if (itemstack1.getItem().hasContainerItem(itemstack1))
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);


                    if (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2))
                    {
                    	
                        if (this.craftResult.getStackInSlot(0) == null)
                        {
                            this.craftResult.setInventorySlotContents(0, itemstack2);
                        }
                        else
                        {
                            this.thePlayer.dropPlayerItemWithRandomChoice(itemstack2, false);
                        }
                    }
                }
            }
        
    }
}