package com.FinalKill.wsbim.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.block.BlockFreezer;
import com.FinalKill.wsbim.common.inventory.ContainerFreezer;
import com.FinalKill.wsbim.util.FrozenFoods;
import com.FinalKill.wsbim.util.TempuratureUtils;

public class TileEntityFreezer extends TileEntity implements IInventory{

	private ItemStack[] inv = new ItemStack[100];

	public int numUsingPlayers;
	private int ticksSinceSync;
	public float doorAngle;
	public float prevDoorAngle;
	private byte facing;
  
	private int coldSlot = 0;
	
	public int freezeLoop;
	public int freezeTime;
	private int tick;
	private int max_tick = 20*5;
	
	public float warmest = 350F;
	public float temp = 350F;
	public float biome_temp;
	public int temp_int;
	public float temp_decrease = 0.55F;
	public float temp_increase = 0.15F;
	public float coldest = -2F;
	
	public void updateFreezer(){
		temp_int = (int)temp;
		biome_temp = TempuratureUtils.getSurroundingBlockTemperature(worldObj, xCoord, zCoord);
		  if(temp > warmest){
		    	temp = warmest;
		    }
		    if(temp < coldest){
		    	temp = coldest;
		    }
		
		
		Block block = worldObj.getBlock(xCoord, yCoord-1, zCoord);
		warmest = TempuratureUtils.isBlockCold(block)? biome_temp - (biome_temp < 60F? 15F : 45F) : (TempuratureUtils.isBlockWarm(block)? biome_temp + 150F : biome_temp);
		temp_increase = (warmest / 100F) - (warmest > 65F? 0.40F : (warmest > 40F? 0.35F : (warmest > 35F? 0.25F : 0F)));
		temp_decrease = 0.25F;
	    //System.out.println(biome_temp);
	    if(warmest < coldest){
	    	warmest = coldest;
	    }
	  
		if(inv[coldSlot] !=null && this.isRecieveingPower() && this.freezeTime == 0){
			Item item = inv[coldSlot].getItem();
			if(item !=null && this.isItemCold(item)){
				int coldness = this.getItemColdness(item);
				this.freezeTime = coldness;
					if(inv[coldSlot].stackSize >= 1){
						inv[coldSlot].stackSize-=1;
					}else{
						inv[coldSlot] = inv[coldSlot].getItem().getContainerItem(inv[coldSlot]);
					}
				
			}
		}
		
		if(!this.isRecieveingPower()){
			this.freezeTime = 0;
		}
		
		if(this.freezeTime > 0){
			--this.freezeTime;
		}
		
		if(temp >= coldest - 1.65F && temp <= warmest + 3F && this.freezeTime > 0){
			this.temp -= this.temp_decrease / 20F;
		}
		
		if(temp >= coldest - 2F && temp <= warmest + 3F && this.freezeTime == 0){
			
			this.temp += this.temp_increase / 20F;
		}
		
		if(this.freezeTime < 0){
			this.freezeTime = 0;
		}
		
		if(this.freezeTime > 0 && this.freezeLoop < 15)this.freezeLoop++;
		
		if(this.freezeTime > 0 && this.freezeLoop == 15){
			this.freezeLoop = 0;
		}	
		if(this.freezeTime == 0){
			this.freezeLoop = 0;
		}
		
		
		
		//Will un-freeze foods
					for(int i = 1; i < inv.length; i++){
						if(inv[i] !=null){
							ItemStack stack = inv[i];
							if(FrozenFoods.getNormalFood(stack.getItem()) !=null && this.temp >= FrozenFoods.getThawTemp(stack.getItem()) && FrozenFoods.getKeepInInvFrozen(stack.getItem())){
								if(worldObj.rand.nextInt(stack.stackSize * (temp_int < 200 ? (250 - temp_int) : (405 - temp_int))) == 0){
									inv[i] = new ItemStack(FrozenFoods.getNormalFood(stack.getItem()), stack.stackSize);
								}
							}
						}
					}
				
				
		//logic, will freeze the foods.
		if(freezeTime > 0){
			for(int i = 1; i < inv.length; i++){
				if(inv[i] !=null){
					ItemStack stack = inv[i];
					if(FrozenFoods.getFrozenFood(stack.getItem()) !=null && this.temp <= FrozenFoods.getFreezeTemp(stack.getItem())){
						if(worldObj.rand.nextInt(stack.stackSize * (85 + temp_int)) == 0){
							inv[i] = new ItemStack(FrozenFoods.getFrozenFood(stack.getItem()), stack.stackSize);
							if(stack.getItem().hasContainerItem(stack)){
								EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, -1);
								if(player.openContainer !=null && player.openContainer instanceof ContainerFreezer){
									player.inventory.addItemStackToInventory(stack.getItem().getContainerItem(stack));
								}
							}
						}
					}
				}
			}
		}
		
		
		if(this.freezeTime == 1){
			//worldObj.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5,WSBIM.modid+":"+"freezer_loop", 1F, 1.0F);
		}
		
		if(this.freezeTime > 0){
			++tick;
			if(tick == 100){
				tick = 0;
			}
			if(tick == 1){
				worldObj.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5,WSBIM.modid+":"+"freezer_loop", 0.15F, 1.0F);
			}
		}
		else{
			tick = 0;
		}
		
		//System.out.println(this.getLoopScaled(16));
		
		
		if(this.freezeTime > 0){
			BlockFreezer.updateFreezer(true, worldObj, xCoord, yCoord, zCoord);
		}
		else BlockFreezer.updateFreezer(false, worldObj, xCoord, yCoord, zCoord);

		
	}
	
	public int getLoopScaled(int i){
		return  this.freezeLoop * i / 15;
	}
	
	public boolean isRecieveingPower(){
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
	
	
	public static boolean isItemCold(Item item){
		return getItemColdness(item) > 0;
	}
	
	public static int getItemColdness(Item item){
		
		return TempuratureUtils.getItemColdness(item);
		
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

  
  public boolean isCurrentBlockActiveBlock(){
	return worldObj.getBlock(xCoord, yCoord, zCoord) == WSBIM.blockFreezerActive;
	  
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
                  int slot = tag.getInteger("Slot");
                  if (slot >= 0 && slot < inv.length) {
                          inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                  }
          }
          
          this.freezeTime = tagCompound.getInteger("freezeTime");
          this.freezeLoop = tagCompound.getInteger("freezeLoop");
          this.doorAngle = tagCompound.getFloat("doorAngle");
          temp = tagCompound.getFloat("temp");
  }

  @Override
  public void writeToNBT(NBTTagCompound tagCompound) {
          super.writeToNBT(tagCompound);
                          
          NBTTagList itemList = new NBTTagList();
          for (int i = 0; i < inv.length; i++) {
                  ItemStack stack = inv[i];
                  if (stack != null) {
                          NBTTagCompound tag = new NBTTagCompound();
                          tag.setInteger("Slot", (int) i);
                          stack.writeToNBT(tag);
                          itemList.appendTag(tag);
                  }
          }
          tagCompound.setTag("Inventory", itemList);
          tagCompound.setInteger("freezerTime", this.freezeTime);
          tagCompound.setInteger("freezeLoop", this.freezeLoop);
          tagCompound.setFloat("doorAngle", doorAngle);
          tagCompound.setFloat("temp", temp);
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
		    public void updateEntity()
		    {
		        super.updateEntity();
		      
		        if(!worldObj.isRemote){
		        	this.updateFreezer();
		        }
		        /**
		        if(worldObj.getClosestPlayer(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, 8.0D) !=null){
		        if(worldObj.getClosestPlayer(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, 8.0D).openContainer instanceof ContainerMustardChest){
		        	this.numUsingPlayers = 1;
		        
		        }
		        else{
		        	this.numUsingPlayers = 0;
		        }
		        }
		        
		        else{
		        
		        this.numUsingPlayers  = 0;
		        }
		        */
		       //S this.doorAngle += 0.001F;
		        if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0)
		        {
		        	if(!this.isCurrentBlockActiveBlock()){
		        		worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerIdle, 3, ((numUsingPlayers << 3) & 0xF8));
		        	}
		        	else{
		        		worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerActive, 3, ((numUsingPlayers << 3) & 0xF8));
		        	}
		        }
		
		         this.ticksSinceSync++;
		        prevDoorAngle = doorAngle;
		        float f = 0.1F;
		        if (numUsingPlayers > 0 && doorAngle == 0.0F)
		        {
		            double d = (double) xCoord + 0.5D;
		            double d1 = (double) zCoord + 0.5D;
		            worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1, WSBIM.modid+":"+"freezer_open", 1F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		        }
		        if (numUsingPlayers == 0 && doorAngle > 0.0F || numUsingPlayers > 0 && doorAngle < 1.0F)
		        {
		            float f1 = doorAngle;
		            if (numUsingPlayers > 0)
		            {
		                doorAngle += f;
		            }
		            else
		            {
		                doorAngle -= f;
		            }
		            if (doorAngle > 1.0F)
		            {
		                doorAngle = 1.0F;
		            }
		            float f2 = 0.5F;
		            if (doorAngle < f2 && f1 >= f2)
		            {
		                double d2 = (double) xCoord + 0.5D;
		                double d3 = (double) zCoord + 0.5D;
		                worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3, WSBIM.modid+":"+"freezer_close", 1F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		            }
		            if (doorAngle < 0.0F)
		            {
		                doorAngle = 0.0F;
		            }
		        }
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
		       if(this.isCurrentBlockActiveBlock()){
		    	   worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerActive, 1, numUsingPlayers);
		       }
		       else{
		       	   worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerIdle, 1, numUsingPlayers);
		       }
		    }

		    @Override
		    public void closeInventory()
		    {
		         numUsingPlayers--;
		         if(this.isCurrentBlockActiveBlock()){
			    	   worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerActive, 1, numUsingPlayers);
			     }
			     else{
			       	   worldObj.addBlockEvent(xCoord, yCoord, zCoord, WSBIM.blockFreezerIdle, 1, numUsingPlayers);
			     }
		    }

}
