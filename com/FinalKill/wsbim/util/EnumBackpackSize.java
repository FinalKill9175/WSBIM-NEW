package com.FinalKill.wsbim.util;

import net.minecraft.util.ResourceLocation;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum EnumBackpackSize {
	SMALL(176,166,2,9,WSBIM.modid+":"+"textures/gui/backpack/small.png"),
	DEFAULT(176,166,3,9,WSBIM.modid+":"+"textures/gui/backpack/default.png"),
	MEDIUM(176, 186, 4, 9, WSBIM.modid+":"+"textures/gui/backpack/medium.png"),
	LARGE(176,222, 6, 9, WSBIM.modid+":"+"textures/gui/backpack/large.png");
	
	public final int inv_size;
	public final int inv_width;
	public final int inv_height;
	public final int rows;
	public final int colums;
	public final String gui_texture;
	
	private EnumBackpackSize(int inv_width, int inv_height, int rows, int colums, String gui_texture){
		this.inv_size = inv_width * inv_height;
		this.inv_width = inv_width;
		this.inv_height = inv_height;
		this.rows = rows;
		this.colums = colums;
		this.gui_texture = gui_texture;
	}
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getGuiTexture(){
		String[] str = this.gui_texture.split(":");
		return new ResourceLocation(str !=null && str.length > 0? str[0] : "minecraft", str !=null && str.length > 0? str[1] : this.gui_texture);
	}
}
