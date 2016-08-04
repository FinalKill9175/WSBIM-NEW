package com.FinalKill.wsbim.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.condenservalues.CondenserItemValues;
import com.FinalKill.condenservalues.CondenserValuesAPI;
import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.util.ForgeDirectionUtils;
import com.FinalKill.wsbim.util.condenservalues.ICondenserValuesReciever;

public class TileEntityCondenser extends TileEntity implements IInventory, ICondenserValuesReciever{
	 private ItemStack[] inv;
	    
	    private CondenserItemValues values =CondenserValuesAPI.instance();
	    
	    public final int target_slot = 199;
	    
	    public int cv = 0;
	    public int max_cv = 0;

	    public final int[] slotsTop = new int[]{target_slot};
	    
	    
	    public int numUsingPlayers;
		private int ticksSinceSync;
		public float lidAngle;
		public float prevLidAngle;
		private byte facing;
		
		protected ForgeDirection[] directions = new ForgeDirection[6];
	

	    public TileEntityCondenser(){
	            inv = new ItemStack[201];
	    }
	    
	    public void updateEntity(){
	    	super.updateEntity();
	    	updateChestState();
	    	boolean flag1 = false;
	    	
	    
	    	if(!this.worldObj.isRemote){
	    		this.updateCV();
	    		if(inv[this.target_slot] !=null){
	    			if(values.doesItemStackHaveValue(inv[this.target_slot])){
	    				
	    				this.max_cv = this.values.getItemValueFromItemStack(inv[this.target_slot]);
	    				
	    			}
	    			
	    		}
	    		else{
	    		max_cv = 0;
	    		}
	    		
	    		
	    		if(inv[this.target_slot] !=null && this.getFirstOccupiedSlot(inv[this.target_slot]) !=-1 && values.doesItemStackHaveValue(inv[this.getFirstOccupiedSlot(inv[target_slot])]) && !(this.cv <0)){
	    			ItemStack stack  = inv[this.getFirstOccupiedSlot(inv[this.target_slot])];
	    			if(stack !=null){
	    				if(inv[this.target_slot] !=stack && values.getItemValueFromItemStack(stack) <=(this.max_cv-cv)){
	    					
	    					if(values.getItemValueFromItemStack(inv[this.getFirstOccupiedSlot(inv[target_slot])]) <= this.max_cv){
	    						    						
	    						if(inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize == 1){
	    							this.cv +=values.getItemValueFromItemStack(stack);
	    							inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize = 0;
	    							inv[this.getFirstOccupiedSlot(inv[target_slot])] = null;
	    							//inv[this.getFirstOccupiedSlot(inv[target_slot])] = null;
	    					
	    						}
	    						else{
	    						this.cv +=values.getItemValueFromItemStack(stack);
	    							--inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize;
	    						
	    						
	        						
	        						
	    						}
	    						
	    						
	    						
	    					}
	    					else{
	    						flag1 = false;
	    					}
	    					
	    				}
	    				else if(inv[this.target_slot] !=stack ){
	    					
	    					if(values.getItemValueFromItemStack(inv[this.getFirstOccupiedSlot(inv[target_slot])]) !=0){
	    						    						
	    						if(inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize == 1){
	    							this.cv +=values.getItemValueFromItemStack(stack);
	    							inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize = 0;
	    							inv[this.getFirstOccupiedSlot(inv[target_slot])] = null;
	    							//inv[this.getFirstOccupiedSlot(inv[target_slot])] = null;
	    							
	    						}
	    						else{
	    						this.cv +=values.getItemValueFromItemStack(stack);
	    							--inv[this.getFirstOccupiedSlot(inv[target_slot])].stackSize;
	    						

	        						
	    						}
	    						
	    						
	    						
	    					}
	    					
	    				}
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if( this.inv[this.target_slot] !=null){
	    		if(this.cv>=this.max_cv && this.cv>0 && this.cv !=0){
	    			
	    			if(this.getNextAvalableSlot() !=-1 ){
	    			if( inv[this.getNextAvalableSlot()] !=null){
	    				
	    				if(inv[this.getNextAvalableSlot()].stackSize +1<=this.getInventoryStackLimit()){
	    					
	    					if(inv[this.getNextAvalableSlot()].getMaxStackSize() >1){
	    					
	    					this.cv-=this.values.getItemValueFromItemStack(inv[this.target_slot]);
	    					inv[this.getNextAvalableSlot()].stackSize +=1;
	    					}
	    					else{
	    						inv[this.getNextAvalableSlot()] = new ItemStack(inv[this.target_slot].getItem(), 1, inv[target_slot].getItemDamage() );
	    						this.cv-=this.values.getItemValueFromItemStack(inv[this.target_slot]);
		
	    						
	    					}
	    				}
			
	    				
	    			}
	    			else{
						
						inv[this.getNextAvalableSlot()] = new ItemStack(inv[this.target_slot].getItem(), 1, inv[target_slot].getItemDamage() );
						this.cv-=this.values.getItemValueFromItemStack(inv[this.target_slot]);
					     }
	    			  }
	    		   }
	    		}
	    			
	        	
	    		if(this.cv<0){
	    			this.cv = 0;
	    			
	    		}
	    		else{
	    			//do nothing
	    		}
	
	  	    	}

	    }
	    
	    private void updateChestState() {
	    	 if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0)
		        {
		            worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockCondenser, 3, ((numUsingPlayers << 3) & 0xF8));
		        }
		
		         this.ticksSinceSync++;
		        prevLidAngle = lidAngle;
		        float f = 0.1F;
		        if (numUsingPlayers > 0 && lidAngle == 0.0F)
		        {
		            double d = (double) xCoord + 0.5D;
		            double d1 = (double) zCoord + 0.5D;
		            worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		        }
		        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F)
		        {
		            float f1 = lidAngle;
		            if (numUsingPlayers > 0)
		            {
		                lidAngle += f;
		            }
		            else
		            {
		                lidAngle -= f;
		            }
		            if (lidAngle > 1.0F)
		            {
		                lidAngle = 1.0F;
		            }
		            float f2 = 0.5F;
		            if (lidAngle < f2 && f1 >= f2)
		            {
		                double d2 = (double) xCoord + 0.5D;
		                double d3 = (double) zCoord + 0.5D;
		                worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		            }
		            if (lidAngle < 0.0F)
		            {
		                lidAngle = 0.0F;
		            }
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

	    private int getNextAvalableSlot(){
	    	for(int i = 0; i<7; i++){
				for(int j = 0; j<13; j++){
					if(inv[i+j*13] ==null){
						return i+j*13;
					}
					else if(inv[this.target_slot] !=null){
					 if(inv[this.target_slot].getItem() == inv[i+j*13].getItem()){
						if(inv[i+j*13].stackSize + 1 <= inv[i+j*13].getMaxStackSize()){
							return i+j*13;
						}
					}
				}
			}
		 }
	    	return -1;
	   }
	    
	    private int getFirstOccupiedSlot(ItemStack stack){
	  	    for(int i = 0; i<7; i++){
	    			for(int j = 0; j<13; j++){
	    				if(inv[i+j*13] !=null && inv[i+j*13].getItem() !=stack.getItem()){
	    					return i+j*13;
	    				}
	    			}
	    			
	    	}
	    	return -1;
	    }
	    
	    public void readFromNBT(NBTTagCompound tagCompound) {
	            super.readFromNBT(tagCompound);
	            
	            NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
	            for (int i = 0; i < 201; i++) {
	                    NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
	                    int slot = tag.getInteger("Slot");
	                    if (slot >= 0 && slot < 201) {
	                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
	                    }
	            }
	    
	            cv = tagCompound.getInteger("CV");
	          
	            NBTTagList list = tagCompound.getTagList("Slot 1", 10);
	            NBTTagCompound list_tag = list.getCompoundTagAt(0);
	            int slot = list_tag.getInteger("Slot");
	            inv[0] = ItemStack.loadItemStackFromNBT(list_tag);
	    }

	    public void writeToNBT(NBTTagCompound tagCompound) {
	            super.writeToNBT(tagCompound);
	                            
	            NBTTagList itemList = new NBTTagList();
	            for (int i = 0; i < 201; i++) {
	                    ItemStack stack = inv[i];
	                    if (stack != null) {
	                            NBTTagCompound tag = new NBTTagCompound();
	                            tag.setInteger("Slot", i);
	                            stack.writeToNBT(tag);
	                            itemList.appendTag(tag);
	                    }
	            }
	            tagCompound.setTag("Inventory", itemList);
	            
	            tagCompound.setInteger("CV", cv);
	            NBTTagList list  = new NBTTagList();
	            if(inv[0] !=null){
	            	
	            	NBTTagCompound tag = new NBTTagCompound();
	            	tag.setInteger("Slot", 0);
	            	inv[0].writeToNBT(tag);
	            	list.appendTag(tag);
	            	
	            }
	            tagCompound.setTag("Slot 1", list);
	            
	          
	    }


	    public int getCVScaled(int i){
	    	if(this.max_cv !=0 && !(this.max_cv<this.cv)){
	    	return this.cv*i / this.max_cv;
	    	}
	    	else if(this.max_cv<this.cv && this.max_cv !=0){
	    		
	    		return i;
	    	}
	    	else{
	    		return 0;
	    	}
	    }
	    
		

	    public String getInventoryName() {
			return null;
		}

	    public boolean hasCustomInventoryName() {	
			return false;
		}

	    public boolean isItemValidForSlot(int var1, ItemStack var2) {
			return false;
		}

	    @Override
	    public boolean receiveClientEvent(int i, int j)
	    {
	        if (i == 1)
	        {
	            numUsingPlayers = j;
	        }
	     
	        else if (i == 3)
	        {
	             numUsingPlayers = (j & 0xF8) >> 3;
	        }
	        return true;
	    }

	    @Override
	    public void openInventory()
	    {
	       numUsingPlayers++;
	        worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockCondenser, 1, numUsingPlayers);
	    }

	    @Override
	    public void closeInventory()
	    {
	         numUsingPlayers--;
	        worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockCondenser, 1, numUsingPlayers);
	    }

		public void setCV(int cv) {
			this.cv = cv;
		}

		public int getCV() {
			return cv;
		}

		public ForgeDirection[] getDirections() {
			return directions;
		}

		public void updateCV() {
			
			//TODO
			
		}

		public ForgeDirection[] getInputDirections() {
			 return ForgeDirectionUtils.allDirections();
		}

		public boolean canRecieve(ForgeDirection direction) {
			List<ForgeDirection> allowed = new ArrayList<ForgeDirection>();
			for(int i = 0; i < this.getInputDirections().length; i++){
				allowed.add(this.getInputDirections()[i]);
			}
			
			return inv[target_slot] !=null && cv >=0 && allowed.contains(direction) && this.getNextAvalableSlot() !=-1;
		}



	    
}
