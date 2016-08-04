package com.FinalKill.wsbim.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class FrozenFoods {
	public static final List<Item> frozenFoods = new ArrayList<Item>();
	public static final List<Item> food = new ArrayList<Item>();
	public static final List<Float> freeze_temps = new ArrayList<Float>();
	public static final List<Float> thaw_temps = new ArrayList<Float>();
	public static final List<Boolean> unFreezeInInv = new ArrayList<Boolean>();
	
	static int cap = 1000;
	
	public static final float null_float = -1012039F;
	
	public static void prepDelegates(){
		for(int i = 0; i < cap; i++){
			frozenFoods.add(i, null);
			food.add(i, null);
		}
	}
	
	public static void addFrozenFood(int id, Item normalFood, Item spoiledFood, boolean un_freeze, float freeze_temp, float thaw, boolean addSmelting){
		if(frozenFoods.get(id) == null && food.get(id) == null){
			frozenFoods.set(id, spoiledFood);
			food.set(id, normalFood);
			freeze_temps.add(freeze_temp);
			thaw_temps.add(thaw);
			unFreezeInInv.add(un_freeze);
			if(addSmelting){
				GameRegistry.addSmelting(new ItemStack(spoiledFood), new ItemStack(normalFood), 0.0F);
			}
		}
		else{
			System.err.println("Unable to register a spoiled food with the id of "+id);
		}
	}

	
	public static void registerFrozenFood(int id, Item normalFood, Item frozenFood, boolean un_freeze, float freeze_temp, float thaw){
		GameRegistry.registerItem(frozenFood, frozenFood.getUnlocalizedName());
		GameRegistry.addSmelting(new ItemStack(frozenFood), new ItemStack(normalFood), 0.0F);
		addFrozenFood(id, normalFood, frozenFood, un_freeze, freeze_temp, thaw, false);
	}
	
	public static Item getNormalFood(Item frozenFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item normalFood = food.get(i);
				if(frozenFoods.get(i) == frozenFood){
					return normalFood;
				}
			}
		}
		return null;
	}
	
	public static Item getFrozenFood(Item normalFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item spoiledFood = frozenFoods.get(i);
				if(food.get(i) == normalFood){
					return spoiledFood;
				}
			}
		}
		return null;
	}
	
	
	
	public static float getFreezeTemp(Item normalFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item spoiledFood = frozenFoods.get(i);
				float temp = freeze_temps.get(i);
				if(food.get(i) == normalFood){
					return temp;
				}
			}
		}
		return -1012039;
	}
	
	public static float getThawTemp(Item frozenFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item normalFood = food.get(i);
				float temp = thaw_temps.get(i);
				if(frozenFoods.get(i) == frozenFood){
					return temp;
				}
			}
		}
		return -1012039;
	}

	
	
	public static boolean getKeepInInvNormal(Item normalFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item spoiledFood = frozenFoods.get(i);
				boolean temp = unFreezeInInv.get(i);
				if(food.get(i) == normalFood){
					return temp;
				}
			}
		}
		return false;
	}
	
	public static boolean getKeepInInvFrozen(Item frozenFood){
		for(int i = 0; i < cap; i++){
			if(frozenFoods.get(i) !=null && food.get(i) !=null){
				Item normalFood = food.get(i);
				boolean temp = unFreezeInInv.get(i);
				if(frozenFoods.get(i) == frozenFood){
					return temp;
				}
			}
		}
		return false;
	}
	
	
}
