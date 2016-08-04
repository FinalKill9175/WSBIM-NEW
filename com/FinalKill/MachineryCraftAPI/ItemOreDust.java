package com.FinalKill.MachineryCraftAPI;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ItemOreDust extends Item {
	
	public Block oreBlock;
	
	public ItemOreDust( Block ore_block){
		
		oreBlock = ore_block;
	
		
	}
	
	public ItemStack getDustFromOre(ItemStack stack){
		
		if(stack !=null){
			
			if(stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore)){
				
				//return new ItemStack(MachineryCraftItems.iron_ore_dust);
				
			}
			
			else if(stack.getItem() == Item.getItemFromBlock(Blocks.gold_ore)){
				
				//return new ItemStack(MachineryCraftItems.gold_ore_dust);
				
			}
			else if(stack.getItem() == Item.getItemFromBlock(Blocks.coal_ore)){
	
				//return new ItemStack(MachineryCraftItems.coal_dust);
	
			}
			
			
		}
		return null;
		
	}
	
	
	public Block getOreBlock(){
		
		return oreBlock;
	}
	
	

}
