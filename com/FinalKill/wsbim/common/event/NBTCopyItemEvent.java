package com.FinalKill.wsbim.common.event;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class NBTCopyItemEvent {

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e){
		if(e.phase == Phase.END && e !=null && e.player.capabilities.isCreativeMode){
			boolean requestAction = false;
				if(e.side == Side.CLIENT){
					MovingObjectPosition ray = Minecraft.getMinecraft().renderViewEntity.rayTrace(5, 1F);
					Block b =e.player.worldObj.getBlock(ray.blockX, ray.blockY, ray.blockZ) ;
					TileEntity tileEntity = e.player.worldObj.getTileEntity(ray.blockX, ray.blockY, ray.blockZ);

				
				if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)&&Keyboard.isKeyDown(Keyboard.KEY_C)&&Minecraft.getMinecraft().renderViewEntity.rayTrace(5, 1F) !=null && e.player.capabilities.isCreativeMode){
					requestAction = true;
				}
				
				if(requestAction && b.hasTileEntity(e.player.worldObj.getBlockMetadata(ray.blockX, ray.blockY, ray.blockZ)) && tileEntity !=null){
					Item item = Item.getItemFromBlock(b);
					ItemStack stack = new ItemStack(b);
					int hotBarSlotToReplace = -1;
					if(hasItemStackInHotbar(e.player, stack)){
						for(int i = 0; i < 9; i++){
							ItemStack current = e.player.inventory.getStackInSlot(i);
							if(current !=null){
								if(current.getItem() == stack.getItem() && current.stackSize == 1 && current.getItemDamage() == stack.getItemDamage()){
									hotBarSlotToReplace = i;
								}
							}
						}
						if(hotBarSlotToReplace !=-1){
							ItemStack old_stack = e.player.inventory.getStackInSlot(hotBarSlotToReplace);
							e.player.inventory.mainInventory[hotBarSlotToReplace] = null;
							 NBTTagCompound tagCompound = new NBTTagCompound();
							//for future code, will determine if this itemstack was a compied block.
							 tagCompound.setBoolean("isCopied", true);
							tileEntity.writeToNBT(tagCompound);
							Set set = tagCompound.func_150296_c();
							Object[] objArray = set.toArray();
							for(int i = 0; i < objArray.length; i++){
								if(objArray[i] !=null && objArray[i] instanceof String){
									String str = (String)objArray[i];
									if(str.equals("x")||str.equals("y")||str.equals("z")){
										//We dont want the coords, wierd things could happen.
										set.remove(str);
									}
								}
							}
							ItemStack newStack = new ItemStack(e.player.worldObj.getBlock(ray.blockX, ray.blockY, ray.blockZ));
							newStack.setTagCompound(tagCompound);
							e.player.inventory.mainInventory[hotBarSlotToReplace] = newStack;
							e.player.inventory.currentItem = hotBarSlotToReplace;
						}
					}
					else{
						for(int i = 8; i >= 0; i--){
							ItemStack current = e.player.inventory.getStackInSlot(i);
							if(current == null){
								hotBarSlotToReplace = i;
							}
						}
						if(hotBarSlotToReplace !=-1){
							ItemStack old_stack = e.player.inventory.getStackInSlot(hotBarSlotToReplace);
							e.player.inventory.mainInventory[hotBarSlotToReplace] = null;
							 NBTTagCompound tagCompound = new NBTTagCompound();
							 //for future code, will determine if this itemstack was a compied block.
							 tagCompound.setBoolean("isCopied", true);
							tileEntity.writeToNBT(tagCompound);
							Set set = tagCompound.func_150296_c();
							Object[] objArray = set.toArray();
							for(int i = 0; i < objArray.length; i++){
								if(objArray[i] !=null && objArray[i] instanceof String){
									String str = (String)objArray[i];
									if(str.equals("x")||str.equals("y")||str.equals("z")){
										//We dont want the coords, wierd things could happen.
										set.remove(str);
									}
								}
							}
							ItemStack newStack = new ItemStack(e.player.worldObj.getBlock(ray.blockX, ray.blockY, ray.blockZ));
							newStack.setTagCompound(tagCompound);
							e.player.inventory.mainInventory[hotBarSlotToReplace] = newStack;
							e.player.inventory.currentItem = hotBarSlotToReplace;
						}
					}
					
					
					requestAction = false;
				}
			}
		}
	}

	private boolean hasItemStackInHotbar(EntityPlayer player, ItemStack itemStack) {
		for(int i = 0; i < 9; i++){
			ItemStack current = player.inventory.getStackInSlot(i);
			if(current !=null){
				if(current.getItem() == itemStack.getItem()){
					return true;
				}
			}
		}
		
		return false;
	}
}
