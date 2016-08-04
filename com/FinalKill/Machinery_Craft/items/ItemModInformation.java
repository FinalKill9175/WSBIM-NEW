package com.FinalKill.Machinery_Craft.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemModInformation extends Item {
	private static  String modid;
	private static String version;
	private static  String author;
	
	public ItemModInformation(String mod, String ver, String auth){
		
		modid = mod;
		version = ver;
		author = auth;
		
	}
	
	
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	
    	if(!par2World.isRemote){
    		  Minecraft.getMinecraft().displayGuiScreen(new GuiModInformation(modid, version, author));
        
    	}
    	return par1ItemStack;
    }

}
