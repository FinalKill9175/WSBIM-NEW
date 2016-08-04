package com.FinalKill.MachineryCraftAPI;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Used to register a ore related dust item to a macerator and other uses.
 * 
 * @author FinalKill
 *
 */
public class OreDustRegistry {

	public static final int capacity = 200;
	public static ItemStack[] ores = new ItemStack[capacity];
	public static ItemStack[] dusts = new ItemStack[capacity];
	private static List<ItemStack> oreBlocks = new ArrayList<ItemStack>();
	private static List<ItemStack> dustItems = new ArrayList<ItemStack>();
	
	/**
	 * Prepares the lists for use. ONLY CALL ONCE, WILL RESET THE LISTS!
	 */
	protected static void prepareLists(){

		for(int i = 0; i<capacity; i++){
			
			oreBlocks.add(i, null);
			dustItems.add(i, null);
			

		}
		
		
	}
	/**
	 * Registers a dust and ore together. Mainly used in macerators, and other things.
	 * @param id - the id to use when registering MAX:200 MIN:1 OCCUPIED: 1-15
	 * @param dust - the itemstack of the dust being registered. can have a custom size, mostly 2 if it is an ore block for the ore.
	 * @param ore - the itemstack of the ore being registered. ONLY A SIZE OF 1! ex. new ItemStack(Blocks.ore_gold)
	 */
	public static void registerOreDust(int id, ItemStack dust, ItemStack ore){
		
		
		dusts[id] = dust;
		ores[id] = ore;
		
		dustItems.set(id, dusts[id]);
		oreBlocks.set(id, ores[id]);
		
		System.out.println("Set a new ore (id: "+id+") dust for the "+ore.getDisplayName()+" and the dust "+dust.getDisplayName());
		
	}
	
	protected static void registerVanillaOres(){
		
	
		
	}
	
	/**
	 * Gets the ID used at registery for the Ore
	 * @param ore - the ore to get the ID from
	 * @return - the id from the ore.
	 */
	public static int getIDFromOre(ItemStack ore){
		
		for(int i = 0; i<capacity; i++){
				if(oreBlocks.get(i) !=null){
			if(oreBlocks.get(i).getItem() == ore.getItem()){
				
				return i;
			}
				}
		
		}
		
	return -1;
		
	}
	/**
	 * Gets the ID used at registery for the Dust
	 * @param dust - the dust to get the ID from
	 * @return - the id from the dust
	 */
public static int getIDFromDust(Item dust){
		
		for(int i = 0; i<capacity; i++){
				
			if(dustItems.get(i) !=null){
			if(dustItems.get(i).getItem() == dust){
				
				return i;
			}
			}
		
		}
		
	return -1;
		
	}

/**
 * Gets the Ore from the Registered Dust.
 * @param dust - the dust to get the ore from
 * @return - the ore from the dust.
 */
public static ItemStack getOreFromDust(Item dust){
	
	if(getIDFromDust(dust) !=-1){
		
		return oreBlocks.get(getIDFromDust(dust));
		
	}
	else{
		
		return null;
	}
}
	/**
	 * Gets the dust from a registered ore. Main use is for the macerator.
	 * @param ore - the ore to get the dust from.
	 * @return - the dust from the ore.
	 */
public static ItemStack getDustFromOre(ItemStack ore){
	
	if(getIDFromOre(ore) !=-1){
		
		return dustItems.get(getIDFromOre(ore));
		
	}
	else{
		
		return null;
	}
}
	
}
