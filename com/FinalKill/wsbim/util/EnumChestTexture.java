package com.FinalKill.wsbim.util;

import net.minecraft.util.ResourceLocation;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum EnumChestTexture {

	IRON("ironChest.png"), GOLD("goldChest.png"), DIAMOND("diamondChest.png"), MIXEDMETAL("mixedMetalChest.png"), OBSIDIAN("obsidianChest.png"),EMERALD("emeraldChest.png"),COPPER("copperChest.png"),TIN("tinChest.png");
	
	private String tex;
	
	private EnumChestTexture(String location){
		tex = location;
	}
	
	public String getGuiTextureString(){
		return this == OBSIDIAN?  "textures/gui/chest/ironChest.png"  :  (this == EMERALD ? "textures/gui/chest/goldChest.png" : "textures/gui/chest/"+tex);
	}
	
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getGuiTexture(){
		return new ResourceLocation(WSBIM.modid.toLowerCase(), this.getGuiTextureString());
	}
	
	public String getModelTextureString(){
		return "textures/model/chest/"+tex;
	}
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getModelTexture(){
		return new ResourceLocation(WSBIM.modid.toLowerCase(), this.getModelTextureString());
	}
	
}
