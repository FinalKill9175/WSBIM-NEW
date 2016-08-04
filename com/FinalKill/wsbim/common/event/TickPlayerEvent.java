package com.FinalKill.wsbim.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import com.FinalKill.wsbim.common.inventory.ContainerFreezer;
import com.FinalKill.wsbim.util.FrozenFoods;
import com.FinalKill.wsbim.util.TempuratureUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TickPlayerEvent {

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e){
		if(e.phase == Phase.END){
			this.tick(e.player);
		}
	}
	
	public void tick(EntityPlayer player){
		//Used to un-freeze foods
		BiomeGenBase biome = player.worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ);
		float temp = TempuratureUtils.getTempForBiomeCalc(player.worldObj, biome);
		//System.out.println(temp);
		for(int i = 0; i < player.inventory.mainInventory.length; i++){
			ItemStack stack = player.inventory.mainInventory[i];
			if(stack !=null && temp > 70F){
				if(FrozenFoods.getNormalFood(stack.getItem()) !=null && FrozenFoods.getKeepInInvFrozen(stack.getItem())){
					Item norm = FrozenFoods.getNormalFood(stack.getItem());
					if(player.worldObj.rand.nextInt(stack.stackSize * (400 - (int) temp)) == 0){
						player.inventory.mainInventory[i] = new ItemStack(norm, stack.stackSize);
					}
				}
			}
		}
	}
	
}
