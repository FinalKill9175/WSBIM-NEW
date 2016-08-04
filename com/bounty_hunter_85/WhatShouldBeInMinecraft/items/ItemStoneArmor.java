package com.bounty_hunter_85.WhatShouldBeInMinecraft.items;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ItemStoneArmor extends ItemArmor {
	public ItemStoneArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_,
			int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		// TODO Auto-generated constructor stub
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
			if (stack.getItem() == Whatshouldbeinminecraft.itemStoneHelmet
			|| stack.getItem()== Whatshouldbeinminecraft.itemStoneChestplate
			|| stack.getItem() ==  Whatshouldbeinminecraft.itemStoneBoots) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/stoneLayer_1.png";
			}
			else if (stack.getItem() ==Whatshouldbeinminecraft.itemStoneLeggings) {
			return Whatshouldbeinminecraft.modid+":"+"textures/model/armor/stoneLayer_2.png";
			} 
			else return null;
			
			}
}
