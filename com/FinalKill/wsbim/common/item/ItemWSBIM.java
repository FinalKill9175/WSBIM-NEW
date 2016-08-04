package com.FinalKill.wsbim.common.item;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemWSBIM extends Item{
	public ItemWSBIM(String str){
		this.setUnlocalizedName(str);
		this.setTextureName(WSBIM.modid+":"+str);
		this.setCreativeTab(WSBIM.main.ourTab);
		GameRegistry.registerItem(this, str);
	}
	
}
