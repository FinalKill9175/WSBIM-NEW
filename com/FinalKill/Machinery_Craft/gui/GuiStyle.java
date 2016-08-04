package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.util.ResourceLocation;

public class GuiStyle {

	private static ResourceLocation texture;
	private static String invName;
	private static int textureWidth;
	private static int textureHeight;
	private static CustomInventory customInventory;

	public GuiStyle(ResourceLocation texture, String inv_name,int texture_width, int texture_height, CustomInventory inv){
		
		this.texture = texture;
		this.invName = inv_name;
		this.textureWidth = texture_width;
		this.textureHeight = texture_height;
		this.customInventory = inv;
	}

	public static ResourceLocation getTexture() {
		return texture;
	}

	public static void setTexture(ResourceLocation texture) {
		GuiStyle.texture = texture;
	}

	public static String getInvName() {
		return invName;
	}

	public static void setInvName(String invName) {
		GuiStyle.invName = invName;
	}

	public static int getTextureWidth() {
		return textureWidth;
	}

	public static void setTextureWidth(int textureWidth) {
		GuiStyle.textureWidth = textureWidth;
	}

	public static int getTextureHeight() {
		return textureHeight;
	}

	public static void setTextureHeight(int textureHeight) {
		GuiStyle.textureHeight = textureHeight;
	}

	public static CustomInventory getCustomInventory() {
		return customInventory;
	}

	public static void setCustomInventory(CustomInventory customInventory) {
		GuiStyle.customInventory = customInventory;
	}
	
	
}
