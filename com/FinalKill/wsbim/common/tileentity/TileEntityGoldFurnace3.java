package com.FinalKill.wsbim.common.tileentity;

import com.FinalKill.wsbim.common.block.BlockUpgradedFurnace;
import com.FinalKill.wsbim.util.EnumFurnaceFunction;
import com.FinalKill.wsbim.util.EnumFurnaceType;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class TileEntityGoldFurnace3 extends TileEntity implements IUpgradedFurnace{

private EnumFurnaceType type;
	
	public ItemStack[] inv = new ItemStack[10]; 
		
	public int burnTime;
	public int itemBurnTime;
	public int cookTime;
	public int cookTime2;
	
	
	
	public void updateEntity(){
		if(!worldObj.isRemote){
			boolean flag = this.burnTime > 0;
			if((inv[input] !=null && FurnaceRecipes.smelting().getSmeltingResult(inv[input]) !=null)){
				
				if(inv[fuel] !=null && TileEntityFurnace.isItemFuel(inv[fuel]) && !this.isBurning()){
					if(inv[output] !=null && inv[output].stackSize < FurnaceRecipes.smelting().getSmeltingResult(inv[input]).getMaxStackSize()){
					this.itemBurnTime = TileEntityFurnace.getItemBurnTime(inv[fuel]);
					
					if(inv[fuel].stackSize == 1){
						inv[fuel] = inv[fuel].getItem().getContainerItem(inv[fuel]);
					}
					else{
						inv[fuel].stackSize -=1;
					}
					this.burnTime +=this.itemBurnTime;
					}
					else if(inv[output] == null){
						this.itemBurnTime = TileEntityFurnace.getItemBurnTime(inv[fuel]);
						
						if(inv[fuel].stackSize == 1){
							inv[fuel] = inv[fuel].getItem().getContainerItem(inv[fuel]);
						}
						else{
							inv[fuel].stackSize -=1;
						}
						this.burnTime +=this.itemBurnTime;
					}
				}
				
				if(this.isBurning()){
					if(inv[output] == null){
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
							
							this.cookTime = 0;
						}
					
					}
					else if(FurnaceRecipes.smelting().getSmeltingResult(inv[input]).getItem() == inv[output].getItem() && inv[output].stackSize < FurnaceRecipes.smelting().getSmeltingResult(inv[input]).getMaxStackSize()){
						
						++this.cookTime;
						if(this.cookTime == this.getFurnaceType().getTicksPerSmelt()){
							ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inv[input]);
							if(inv[input].stackSize == 1){
								inv[input] = inv[input].getItem().getContainerItem(inv[input]);
							}
							else{
								inv[input].stackSize -=1;
							}
							inv[output].stackSize++;
							
							this.cookTime = 0;
						}
						
					}
					
					
				}
				if(this.cookTime > 0 && (!this.isBurning() && inv[output] == null && inv[output_2] == null) || inv[input] == null && inv[input_2] == null){
					this.cookTime = 0;
				}
				
			}
			else{
				this.cookTime = 0;
			}
			
		 if((inv[input_2] !=null && FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]) !=null)){
				if(inv[fuel] !=null && TileEntityFurnace.isItemFuel(inv[fuel]) && !this.isBurning()){
					if(inv[output_2] !=null && inv[output_2].stackSize < FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]).getMaxStackSize()){
					this.itemBurnTime = TileEntityFurnace.getItemBurnTime(inv[fuel]);
					
					if(inv[fuel].stackSize == 1){
						inv[fuel] = inv[fuel].getItem().getContainerItem(inv[fuel]);
					}
					else{
						inv[fuel].stackSize -=1;
					}
					this.burnTime +=this.itemBurnTime;
					}
					else if(inv[output_2] == null){
						this.itemBurnTime = TileEntityFurnace.getItemBurnTime(inv[fuel]);
						
						if(inv[fuel].stackSize == 1){
							inv[fuel] = inv[fuel].getItem().getContainerItem(inv[fuel]);
						}
						else{
							inv[fuel].stackSize -=1;
						}
						this.burnTime +=this.itemBurnTime;
					}
				}
				
				if(this.isBurning()){
					
					
					if(inv[output_2] == null){
						++this.cookTime2;
						if(this.cookTime2 == this.getFurnaceType().getTicksPerSmelt()){
							ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]);
							if(inv[input_2].stackSize == 1){
								inv[input_2] = inv[input_2].getItem().getContainerItem(inv[input_2]);
							}
							else{
								inv[input_2].stackSize -=1;
							}
							if(inv[output_2] == null){
							inv[output_2] = new ItemStack(result.getItem(), 1, result.getItemDamage());
							}
							
							this.cookTime2 = 0;
						}
					
					}
					else if(FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]).getItem() == inv[output_2].getItem() && inv[output_2].stackSize < FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]).getMaxStackSize()){
						
						++this.cookTime2;
						if(this.cookTime2 == this.getFurnaceType().getTicksPerSmelt()){
							ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inv[input_2]);
							if(inv[input_2].stackSize == 1){
								inv[input_2] = inv[input_2].getItem().getContainerItem(inv[input_2]);
							}
							else{
								inv[input_2].stackSize -=1;
							}
							inv[output_2].stackSize++;
							
							this.cookTime2 = 0;
						}
						
					}
				}
				if(this.cookTime2 > 0 && (!this.isBurning() && inv[output] == null && inv[output_2] == null) || inv[input] == null && inv[input_2] == null){
					this.cookTime2 = 0;
				}
			}
			else{
				this.cookTime2 = 0;
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
	}
	
	public void updateDoubleFurnace(){}
	public void updateFurnace(){}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	public EnumFurnaceType getFurnaceType(){
		//TODO change type
		return EnumFurnaceType.GOLD3;
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
		return this.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE;
	}
	
	public BlockUpgradedFurnace getFurnaceBlock(){
		return (BlockUpgradedFurnace)this.worldObj.getBlock(xCoord, yCoord, zCoord);
	}
	

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        		
        this.itemBurnTime = TileEntityFurnace.getItemBurnTime(this.inv[fuel]);
        this.burnTime = nbt.getInteger("burnTime");
        this.cookTime = nbt.getInteger("cookTime");
        this.cookTime2 = nbt.getInteger("cookTime2");
        
        NBTTagList tagList = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }

      
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("burnTime", this.burnTime);
        nbt.setInteger("cookTime", this.cookTime);
        nbt.setInteger("cookTime2", this.cookTime2);
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

        nbt.setTag("Inventory", itemList);
      
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

	public boolean isSmelting() {
		return this.cookTime > 0;
	}

	public World getWorld() {
		return this.worldObj;
	}


	public int getCookTime() {
		return cookTime;
	}

	public int getBurnTime() {
		return burnTime;
	}

	public int getItemBurnTime() {
		return itemBurnTime;
	}

	public void setCookTime(int i) {
		this.cookTime = i;
	}

	public void setBurnTime(int i) {
		this.burnTime = i;
	}

	public void setItemBurnTime(int i) {
		this.itemBurnTime = i;
	}

	public int getSecondCookTime() {
		return cookTime2;
	}

	public void setSecondCookTime(int i) {
		cookTime2 = i;
	}

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgress2Scaled(int p_145953_1_)
    {
        return this.cookTime2 * p_145953_1_ / this.getFurnaceBlock().getFurnaceType().getTicksPerSmelt();
    }

	@Override
	public int getThirdCookTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFourthCookTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setThirdCookTime(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFourthCookTime(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCookProgress3Scaled(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCookProgress4Scaled(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
