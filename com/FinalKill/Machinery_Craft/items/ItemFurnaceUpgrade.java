package com.FinalKill.Machinery_Craft.items;



import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFurnaceUpgrade extends Item{
	
	public static int cookSpeed;
	public static int maxCV;
	public static int cvUsage;
	public ItemFurnaceUpgrade(int cs, int mcv, int cvu){
	this.cookSpeed = cs;
	this.maxCV = mcv;
	this.cvUsage = cvu;
		
	}

	 /**
     * allows items to add custom lines of information to the mouseover description
     */
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
    	
    	
    	list.add("For Use in a Furnace from Machienry Craft.");
    	list.add("Upgrade Specs:");
    	list.add("Cook Speed: "+this.cookSpeed);
    	list.add("Max CV: "+this.maxCV);
    	list.add("CV Usage: "+this.cvUsage);
    	
    	
    }

}
