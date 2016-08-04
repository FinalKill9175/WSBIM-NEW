package com.FinalKill.wsbim.common.item;

import com.FinalKill.wsbim.WSBIM;

import net.minecraft.item.Item;

public class ItemFrozenFood extends Item{
	public ItemFrozenFood(String itemName){
		this.setUnlocalizedName(itemName);
		this.setTextureName(WSBIM.modid+":"+itemName);
		this.setCreativeTab(WSBIM.tabFood);
	}
}
