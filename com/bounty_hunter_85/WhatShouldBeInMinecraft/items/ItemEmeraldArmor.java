package com.bounty_hunter_85.WhatShouldBeInMinecraft.items;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemEmeraldArmor extends ItemArmor {

	public ItemEmeraldArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_,
			int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		
	}
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
			if (stack.getItem() == Whatshouldbeinminecraft.itemEmeraldHelmet
			|| stack.getItem()== Whatshouldbeinminecraft.itemEmeraldChestplate
			|| stack.getItem() ==  Whatshouldbeinminecraft.itemEmeraldBoots) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/emeraldLayer_1.png";
			}
			else if (stack.getItem() ==Whatshouldbeinminecraft.itemEmeraldLeggings) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/emeraldLayer_2.png";
			} 
			else return null;
			
			}

}
