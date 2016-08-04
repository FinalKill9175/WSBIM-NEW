package com.FinalKill.wsbim.common.event.tooltip;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import com.FinalKill.wsbim.common.item.ItemBackpack;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemTooltipFullName {
	
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event){
		if(event.itemStack !=null){
			if(event.itemStack.getItem() !=null){
				Item item = event.itemStack.getItem();
				ItemStack stack = event.itemStack;
				if(event.showAdvancedItemTooltips){
					if(!(item instanceof ItemBlock)){
						event.toolTip.add(EnumChatFormatting.DARK_GRAY+Item.itemRegistry.getNameForObject(item));
					}
					else{
						Block block = Block.getBlockFromItem(item);
						if(block !=null){
							event.toolTip.add(EnumChatFormatting.DARK_GRAY+Block.blockRegistry.getNameForObject(block));
						}
					}	
				
				}
				if(item instanceof ItemFood){
					ItemFood food = (ItemFood)item;
					event.toolTip.add(EnumChatFormatting.DARK_AQUA+"Can be eaten.");
				}
				
				if(item instanceof ItemBackpack){
					ItemBackpack backpack = (ItemBackpack)item;
					if(stack.hasTagCompound() && stack.stackTagCompound.hasKey("Inventory")){
						event.toolTip.add(EnumChatFormatting.DARK_GREEN+"May contain items.");
					}
					else{
						event.toolTip.add(EnumChatFormatting.DARK_GREEN+"Empty - "+backpack.backpackSize.rows * backpack.backpackSize.colums+" slots toltal.");
					}
				}
				
				if(item.isRepairable()){
					event.toolTip.add(EnumChatFormatting.BLUE+"Can be repaired in an anvil.");
				}
				if(item.hasContainerItem(new ItemStack(item))){
					Item containerItem = item.getContainerItem();
					if(containerItem !=null){
						event.toolTip.add(EnumChatFormatting.DARK_GRAY+"Container Item: "+new ItemStack(containerItem).getDisplayName());
					}
				}
				
				if(stack.isItemDamaged() && Minecraft.getMinecraft().gameSettings.heldItemTooltips){
					//event.toolTip.add("Durability: "+(stack.getMaxDamage()-stack.getItemDamage())+" / "+stack.getMaxDamage());
				}
				
				if(stack.hasTagCompound()){
					NBTTagCompound tagCompound = stack.getTagCompound();
					int tags = 0;
					Set tag_set = null;
					if(tagCompound !=null){
						tags = tagCompound.func_150296_c().size();
						tag_set = tagCompound.func_150296_c();
					}
					String format = EnumChatFormatting.ITALIC+""+EnumChatFormatting.DARK_PURPLE;
					event.toolTip.add(format+"+NBT ("+(tags>0?tags:"")+")");
					if(tags > 0 && tagCompound !=null && event.showAdvancedItemTooltips){
						if((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
							Object[] set_data = tag_set.toArray();
							String[] string_tags = new String[set_data.length];
							for(int i = 0; i < string_tags.length; i++){
								if(set_data[i] !=null && set_data[i] instanceof String){
									string_tags[i] = (i+1)+". Value Name: "+(String)set_data[i]+" Value: "+tagCompound.getTag((String)set_data[i]);
								}
							}
							for(int i = 0; i < string_tags.length; i++){
								event.toolTip.add(string_tags[i]);
							}
						}
						else{
							event.toolTip.add(EnumChatFormatting.DARK_GRAY+"Press "+EnumChatFormatting.YELLOW+"'Shift' "+EnumChatFormatting.DARK_GRAY+"for a list of tags.");
						}
					}
				}
			}
		}
	}
}
