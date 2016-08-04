package com.FinalKill.MachineryCraftAPI;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.item.ItemStack;

public class PowerItems {

public static int getPowerForItem(ItemStack stack){
    
    	if(stack.getItem() == MachineryCraft.items.battery){
    		return 10;
    		
    	}
    	else{
    		return 0;
    	}
    	
    	
    	
    }
	
	
}
