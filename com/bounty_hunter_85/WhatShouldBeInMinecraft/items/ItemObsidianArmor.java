package com.bounty_hunter_85.WhatShouldBeInMinecraft.items;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemObsidianArmor extends ItemArmor{

	
	
	public ItemObsidianArmor(ArmorMaterial matrieral, int renderIndex,
			int armorType) {
		super(matrieral, renderIndex, armorType);
		
		
		
	}

	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
			if (stack.getItem() == Whatshouldbeinminecraft.itemObsidianHelmet
			|| stack.getItem()== Whatshouldbeinminecraft.itemObsidianChestplate
			|| stack.getItem() ==  Whatshouldbeinminecraft.itemObsidianBoots) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/obsidianLayer1.png";
			}
			else if (stack.getItem() ==Whatshouldbeinminecraft.itemObsidianLeggings) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/obsidianLayer2.png";
			} 
			else return null;
			
			}

}
