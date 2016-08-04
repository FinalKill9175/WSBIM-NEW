package com.FinalKill.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import com.FinalKill.wsbim.common.inventory.slot.SlotCraftingUpdate;
import com.FinalKill.wsbim.common.inventory.slot.SlotUpdate;
import com.FinalKill.wsbim.common.tileentity.TileEntityAdvancedCraftingTable;

public class ContainerAdvancedCraftingTable extends Container
{
     private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    private static final String __OBFID = "CL_00001744";
    
    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();

    private TileEntityAdvancedCraftingTable table;

    public ContainerAdvancedCraftingTable(InventoryPlayer p_i1808_1_, TileEntityAdvancedCraftingTable table)
    {
    	this.table = table;
    	table.container = this;
        this.worldObj = table.getWorldObj();
        this.posX = table.xCoord;
        this.posY = table.yCoord;
        this.posZ = table.zCoord;
        /**
        if(MinecraftServer.getServer().isServerRunning()){
        	if(MinecraftServer.getServer().getEntityWorld().getTileEntity(posX, posY, posZ) !=null){
        		if(MinecraftServer.getServer().getEntityWorld().getTileEntity(posX, posY, posZ) instanceof TileEntityAdvancedCraftingTable){
        			this.table = (TileEntityAdvancedCraftingTable) this.worldObj.getTileEntity(posX, posY, posZ);
        		}
        	}
        }
        else if(Minecraft.getMinecraft().isIntegratedServerRunning()){
        	if(Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(posX, posY, posZ) !=null){
        	eif(Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(posX, posY, posZ) instanceof TileEntityAdvancedCraftingTable){
        			this.table = (TileEntityAdvancedCraftingTable) this.worldObj.getTileEntity(posX, posY, posZ);
        		}
        	}
        }
        */
        this.addSlotToContainer(new SlotCraftingUpdate(this, p_i1808_1_.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        int l;
        int i1;

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new SlotUpdate(this, this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new SlotUpdate(this, p_i1808_1_, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new SlotUpdate(this, p_i1808_1_, l, 8 + l * 18, 142));
        }

        
         this.onContainerOpened();
         this.onCraftMatrixChanged(this.craftMatrix);
         
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
       /**
        for(int i = 0; i < 9; i++){
        	if(this.table.matrixStacks[i] !=null && this.craftMatrix.getStackInSlot(i) !=null){
        		if(this.table.matrixStacks[i].getItem() != this.craftMatrix.getStackInSlot(i).getItem() || this.table.matrixStacks[i].stackSize != this.craftMatrix.getStackInSlot(i).stackSize || this.table.matrixStacks[i].getItemDamage() != this.craftMatrix.getStackInSlot(i).getItemDamage()){
        			this.table.matrixStacks[i] = this.craftMatrix.getStackInSlot(i);
        		}
        	}
        	if(this.table.matrixStacks[i] == null && this.craftMatrix.getStackInSlot(i) !=null){
        		this.table.matrixStacks[i] = this.craftMatrix.getStackInSlot(i);
        	}
        }
       */
        
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));

    }

    
      
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

            this.closeContainer();
        
    }
    
    public void closeContainer(){
    	for (int i = 0; i < 9; ++i)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);
                this.table.matrixStacks[i] = itemstack;
        
        }
        
        if(this.craftResult.getStackInSlot(0) !=null){
        //	this.table.resultStack = this.craftResult.getStackInSlot(0);
        }
    }
    
    public void onContainerOpened(){
    	
    	
             for (int i = 0; i < 9; ++i)
             {
                 ItemStack itemstack = this.table.matrixStacks[i];

                 if (itemstack != null && itemstack.stackSize > 0)
                 {
                     this.craftMatrix.setInventorySlotContents(i, itemstack);
                   
                 }
                             }
             
             if(this.table.resultStack !=null){
            	 
            	 
             }
         
    	
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return p_75145_1_.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
    	
    	 this.closeContainer();
         this.onContainerOpened();
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 10 && p_82846_2_ < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 37 && p_82846_2_ < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

    
}