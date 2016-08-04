package com.FinalKill.Machinery_Craft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.MachineryCraftAPI.ItemPower;
import com.FinalKill.MachineryCraftAPI.MachineryCraftAPI;
import com.FinalKill.MachineryCraftAPI.OreDustRegistry;
import com.FinalKill.MachineryCraftAPI.PowerItems;
import com.FinalKill.MachineryCraftAPI.power.IConnectionInput;
import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.blocks.BlockMacerator;

public class TileEntityMacerator extends TileEntity implements IInventory, IConnectionInput {

    private ItemStack[] inv;
    
    public float power = 0;
    public float max_power = 2500;
    public final float power_decrease = 0.25F;
    
    //30 Ticks = 1 Second!
    public int maceratingSpeed = 150;
    public int maceratingTime = 0;
    
    public float turn = 0.0F;
    public float lastTurn = 0.0F;
    public float rotateAngle =0.0F;
    
    
    private int macerateSlot = 0;
    private int finishSlot = 1;
    private int powerSlot = 2;

	public ForgeDirection[] connections = new ForgeDirection[6];
    
    private static OreDustRegistry ores = MachineryCraftAPI.getOreDustRegistry();
    
    
    public TileEntityMacerator(){
            inv = new ItemStack[4];
            
            
            
            
            
            
            
            
            
    }
    
    public void updateEntity(){
    	this.updateConnections();
    	if(!worldObj.isRemote){
    		
    		/**
    		if(this.worldObj.isRaining() && this.worldObj.rand.nextInt(40) == 3 && this.worldObj.getBlock(xCoord, yCoord, zCoord).equals(MachineryCraft.blocks.macerator_active)){
    			worldObj.playSoundEffect(xCoord + 0.5D, yCoord +0.5D, zCoord +0.5D, "random.fizz", 0.75F, 1.0F);
    		
    		}
    		else if(this.worldObj.isRaining()){
    			this.maceratingSpeed = 150*2;
    			
    			
    		}
    		else{
    			this.maceratingSpeed = 150;
    		}
	       */
    		    		
    	//System.out.println(this.maceratingTime);
    	//	System.out.println(this.power);
    		if(this.power > this.max_power){
    			this.power = this.max_power;
    			
    		}
    		if(this.maceratingTime>this.maceratingSpeed){
    			
    			this.maceratingTime = this.maceratingSpeed-1;
    			
    		}
    		
    		if(inv[this.powerSlot] !=null){
    		if(this.isItemValidForPower(inv[this.powerSlot]) && this.getPowerForItem(inv[this.powerSlot]) !=0){
    			
    			if(this.power <= (this.max_power - this.getPowerForItem(inv[this.powerSlot]))){
    			
    			ItemStack slot = inv[this.powerSlot];
    			if(!inv[this.powerSlot].isItemStackDamageable()){
    			
    		
    			if(inv[this.powerSlot].stackSize == 1){
    				this.power +=this.getPowerForItem(inv[this.powerSlot]);
    				--inv[this.powerSlot].stackSize;
    				inv[this.powerSlot] = null;
   
    				
    				
    				
    			}
    			else{
    				--slot.stackSize;
    				this.power +=this.getPowerForItem(inv[this.powerSlot]);
    				
    			}
    				
    			}
    			else{
    				
    				if(inv[powerSlot].getItemDamage()< inv[powerSlot].getMaxDamage()){
    					this.power +=this.getPowerForItem(inv[this.powerSlot]);
    				inv[powerSlot] = new ItemStack(inv[powerSlot].getItem(), inv[powerSlot].stackSize, inv[powerSlot].getItemDamage()+1);
    				}
    				}
    				
    				
    		
    			
    			
    			
    		}
    		}
    		}
    		if(inv[this.macerateSlot] !=null && isOre(inv[this.macerateSlot])){
    		if(hasPower() && isOre(inv[this.macerateSlot]) ){
    			if(inv[this.finishSlot] !=null){
    				if(ores.getDustFromOre(inv[this.macerateSlot]).getItem() == inv[this.finishSlot].getItem())
    				if(this.inv[this.finishSlot].stackSize<this.getInventoryStackLimit()){
    				
    					++this.maceratingTime;
    	    			power -=this.power_decrease;
    	    			
    	    			if(this.worldObj.rand.nextInt(50) == 4){
    	    				}
    	    		//	this.updateBlades();
    	    			if(this.maceratingTime==1)	this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:macerator", 0.35F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));
        	    		
    	    			
    	    			if(this.maceratingTime == this.maceratingSpeed){
    	    				
    	    				finishMaceration();
    	    			//	this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:macerator_done", 0.5F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));
        	    			
    	    				this.maceratingTime = 0;
    	    			}
    	    			}
    				if(inv[this.finishSlot].stackSize == this.getInventoryStackLimit()){
    					this.maceratingTime = 0;
    					
    				}
    			
    			}
    			else{
    				
    					++this.maceratingTime;
    					power -=this.power_decrease;
    					if(this.maceratingTime==1)	this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:macerator", 0.35F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));
        	    		
    				//	this.updateBlades();
    					if(this.worldObj.rand.nextInt(50) == 2){
        	    			}
    	    			if(this.maceratingTime == this.maceratingSpeed){
    	    				
    	    				finishMaceration();
    	    			//	this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:macerator_done", 0.5F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));
        	    			
    	    				this.maceratingTime = 0;
    	    			}
    	    			}
    			
    			
    		}
    		else{
    			this.maceratingTime = 0;
    		}
    		}
    		else{
    			
    			this.maceratingTime = 0;
    		}
    		
    		
    		
    		if(inv[this.macerateSlot] !=null && this.hasPower() && this.isOre(inv[this.macerateSlot]) && this.maceratingTime >0){
    			
    			
    		
    		if(inv[this.finishSlot] !=null){
    			
    			if(ores.getDustFromOre(inv[this.macerateSlot]).getItem() == inv[this.finishSlot].getItem()){
    				
    				BlockMacerator.updateMaceratorBlockState(true, worldObj, xCoord, yCoord, zCoord);

    			}
    		}
    		else{
    			
    			BlockMacerator.updateMaceratorBlockState(true, worldObj, xCoord, yCoord, zCoord);

    		}
    		
    		}
    		else{
    			BlockMacerator.updateMaceratorBlockState(false, worldObj, xCoord, yCoord, zCoord);
    		}
    		
    	}

		
    }
    
    public int getTimeScaled(int i){
    	
    	return (this.maceratingTime *i) / this.maceratingSpeed;
    	
    }
    
    
    private void finishMaceration() {
    	//this.worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "machinery_craft:macerator", 0.35F, (float) (this.worldObj.rand.nextFloat()*0.1+0.9F));
		
    	
    	ItemStack input = inv[this.macerateSlot];
    	ItemStack output = inv[this.finishSlot];
    	if(output !=null){
    		
    		output.stackSize +=ores.getDustFromOre(input).stackSize;
    		
    		if(input.stackSize == 1){
    			input.stackSize = 0;
    			inv[this.macerateSlot] = null;
    			
    		}
    		else{
    			input.stackSize -=1;
        		
    		}
    		
    	}
    	else{
    		inv[this.finishSlot] = new ItemStack(ores.getDustFromOre(input).getItem(), ores.getDustFromOre(input).stackSize);
			

    		if(input.stackSize == 1){
    			input.stackSize = 0;
    			inv[this.macerateSlot] = null;
    			
    		}
    		else{
    			input.stackSize -=1;
        		
    		}	
	
    	}
    	this.markDirty();
		
	}


    
    public void updateBlades(){
    	 if( this.maceratingTime >0){
	        	
	        	this.turn +=0.01F;
	        if( this.turn >=1.0F)  this.turn = 1.0F;
	        	rotateAngle += this.turn;
	        //s	System.out.println(rotateAngle);
	        	this.lastTurn =  this.turn;
	        }
	       
	        else {
	        	this.turn +=-0.01F;
			       if( this.turn <0) this.turn = 0.0F; 
			       rotateAngle -=  this.turn;
	        }
    	
    	
    }
	public boolean hasPower(){
    	
    	return this.power>0;
    }
    
    public boolean hasEnoughPower(int min_power){
    	
    	return(this.power - min_power)>0;
    	
    }
    
    public static boolean isItemValidForPower(ItemStack stack){
		if(stack.getItem() instanceof ItemPower){
			return true;
			
		}
    	
		else{
    	return false;
		}
    	
    	
    }
    
    public static int getPowerForItem(ItemStack stack){
    	
    	return PowerItems.getPowerForItem(stack);
    	
    	
    	
    }
    
    public float getPowerScaled(int i){
    	
    	
    	return this.power*i / this.max_power;
    }
    
    
  	public static boolean isOre(ItemStack itemstack){
		
  		/**
  		String[] oreNames = OreDictionary.getOreNames();
	
		for(int i = 0; i < oreNames.length; i++){
			if(oreNames[i].contains("ore")){
    			if(OreDictionary.getOres(oreNames[i]) != null){
    				if(OreDictionary.getOres(oreNames[i]).get(0) == itemstack){
    					return true;        			
    				}
    			}
			}
		}
		
  		*/
  		if(ores.getDustFromOre(itemstack) != null){
  			
  			return true;
  		}
	
  		else{

  			return false;
	}
	
	
  	}
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
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inv.length) {
                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
            
           this.power = tagCompound.getFloat("power");
            this.maceratingTime = tagCompound.getInteger("time");
            this.turn = tagCompound.getFloat("turn");
            this.lastTurn = tagCompound.getFloat("last_turn");
            this.rotateAngle = tagCompound.getFloat("rotate");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
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
            tagCompound.setTag("Inventory", itemList);
            tagCompound.setFloat("power", this.power);
            tagCompound.setInteger("time", this.maceratingTime);
            tagCompound.setFloat("turn", this.turn);
            tagCompound.setFloat("last_furn", this.lastTurn);
            tagCompound.setFloat("rotate", this.rotateAngle);
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
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void openInventory() {
				// TODO Auto-generated method stub
				
			}

			public void updateConnections() {
				
				
				
				if((this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IConnectionInput)) connections  [0] = ForgeDirection.UP;
				else connections[0] = null;
				if((this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IConnectionInput)) connections [1] = ForgeDirection.DOWN;
				else connections[1] = null;
				if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IConnectionInput)) connections [2] = ForgeDirection.NORTH;
				else connections[2] = null;
				if((this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IConnectionInput)) connections [3] = ForgeDirection.EAST;
				else connections[3] = null;
				if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IConnectionInput)) connections [4] = ForgeDirection.SOUTH;
				else connections[4] = null;
				if((this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IConnectionInput)) connections [5] = ForgeDirection.WEST;
				else connections[5] = null;
			}

		
			public ForgeDirection[] getConnections() {
			
				return this.connections;
			}


}
