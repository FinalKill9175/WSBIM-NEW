package com.FinalKill.wsbim.common.tileentity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

import com.FinalKill.wsbim.common.block.BlockUpgradedFurnace;
import com.FinalKill.wsbim.util.EnumFurnaceFunction;
import com.FinalKill.wsbim.util.EnumFurnaceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityUpgradedFurnace extends TileEntity implements IInventory{

	private EnumFurnaceType type;
	
	public ItemStack[] inv = new ItemStack[5]; 
	
	public static final int fuel = 0;
	public static final int input = 1;
	public static final int output = 2;
	public static final int input_2 = 3;
	public static final int output_2 = 4;
	
	public int burnTime;
	public int itemBurnTime;
	public int cookTime;
	
	
	public void updateEntity(){
		if(!worldObj.isRemote){
			if(!this.isDoubleFurnace()){
				this.updateFurnace();
			}
			if(this.isDoubleFurnace()){
				this.updateDoubleFurnace();
			}
		}
	}
	
	public void updateDoubleFurnace(){
		
	}
	public void updateFurnace(){

		this.type = this.getFurnaceBlock().getFurnaceType();

		boolean flag = this.burnTime > 0;
		if(inv[input] !=null && FurnaceRecipes.smelting().getSmeltingResult(inv[input]) !=null){
			
			if(inv[fuel] !=null && TileEntityFurnace.isItemFuel(inv[fuel]) && !this.isBurning()){
				this.itemBurnTime = TileEntityFurnace.getItemBurnTime(inv[fuel]);
				
				if(inv[fuel].stackSize == 1){
					inv[fuel] = inv[fuel].getItem().getContainerItem(inv[fuel]);
				}
				else{
					inv[fuel].stackSize -=1;
				}
				this.burnTime +=this.itemBurnTime;
			}
			
			if(this.isBurning()){

					++this.cookTime;
					if(this.cookTime == this.getFurnaceType().getTicksPerSmelt()){
						ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inv[input]);
						if(inv[input].stackSize == 1){
							inv[input] = inv[input].getItem().getContainerItem(inv[input]);
						}
						else{
							inv[input].stackSize -=1;
						}
						if(inv[output] == null){
						inv[output] = new ItemStack(result.getItem(), 1, result.getItemDamage());
						}
						else{
							inv[output].stackSize++;
						}
						this.cookTime = 0;
					}
				
				
				
				
			}
			if(this.cookTime > 0 && !this.isBurning() || inv[input] == null){
				this.cookTime = 0;
			}
			
		}
		else{
			this.cookTime = 0;
		}
		
		
		
		
		if(this.burnTime > 0){
			--this.burnTime;
		}
        if (this.burnTime > 0)
        {
        	BlockUpgradedFurnace.updateFurnace(true, getWorldObj(), xCoord, yCoord, zCoord);
        }
        else BlockUpgradedFurnace.updateFurnace(false, getWorldObj(), xCoord, yCoord, zCoord);
       
		
	}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	public EnumFurnaceType getFurnaceType(){
		return type;
	}

	public int getSizeInventory() {
		return inv.length;
	}

	public ItemStack getStackInSlot(int p_70301_1_) {
		return inv[p_70301_1_];
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
	
		
	}

	@Override
	public String getInventoryName() {
		
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return false;
	}

	public void openInventory() {

		
	}
	
	public boolean isDoubleFurnace(){
		return this.getFurnaceBlock().getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE;
	}
	
	public BlockUpgradedFurnace getFurnaceBlock(){
		return (BlockUpgradedFurnace)this.worldObj.getBlock(xCoord, yCoord, zCoord);
	}
	

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        		
		  NBTTagList nbttaglist = nbt.getTagList("Items", 10);

        NBTTagList tagList = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }

        this.itemBurnTime = inv[fuel] !=null? TileEntityFurnace.getItemBurnTime(this.inv[fuel]) : 0;
        this.burnTime = nbt.getInteger("burnTime");
        this.cookTime = nbt.getInteger("cookTime");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
    
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

        nbt.setTag("Items", itemList);
        nbt.setInteger("burnTime", this.burnTime);
        nbt.setInteger("cookTime", this.cookTime);
    
    }
    
    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.cookTime * p_145953_1_ / this.getFurnaceBlock().getFurnaceType().getTicksPerSmelt();
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.itemBurnTime == 0)
        {
            this.itemBurnTime = 200;
        }

        return this.burnTime * p_145955_1_ / this.itemBurnTime;
    }

}
