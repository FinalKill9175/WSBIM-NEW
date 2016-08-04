package com.FinalKill.wsbim.common.item;

import net.minecraft.item.ItemFood;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemModdedFood extends ItemFood{
	
	public ItemModdedFood(String str, int p_i45339_1_, float p_i45339_2_) {
		super(p_i45339_1_, p_i45339_2_, false);
		this.setUnlocalizedName(str);
		this.setTextureName(WSBIM.modid+":"+str);
		this.setCreativeTab(WSBIM.tabFood);
		GameRegistry.registerItem(this, str);
	}

	public ItemModdedFood(String str, int p_i45339_1_, float p_i45339_2_, int potionID, int duration, int amp, float chance) {
		super(p_i45339_1_, p_i45339_2_, false);
		this.setUnlocalizedName(str);
		this.setTextureName(WSBIM.modid+":"+str);	
		this.setPotionEffect(potionID, duration, amp, chance);
		this.setCreativeTab(WSBIM.tabFood);
		GameRegistry.registerItem(this, str);
	}

}
