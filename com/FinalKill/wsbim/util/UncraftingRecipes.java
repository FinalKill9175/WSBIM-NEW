package com.FinalKill.wsbim.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class UncraftingRecipes {

	/**Holds an array list of all the items that cant be un crafted.*/
	public static List<Item> blackListedItems = new ArrayList<Item>();

	
	/**
	 * Gets the 3x3 crafting grid of a recipe. Works with any IRecipe instance extending or is an instanceof {@link ShapedRecipes} (Vanilla), {@link ShapelessRecipes} (Vanilla), {@link ShapedOreRecipe} (Forge), or {@link ShapelessOreRecipe} (Forge)
	 * @param result - the result to search crafting recipes for.
	 * @return the array of item stacks.
	 */
	public static ItemStack[] getCraftingGridForResult(ItemStack result){
		for(int i = 0; i < CraftingManager.getInstance().getRecipeList().size(); i++){
			
			if(!isItemStackUncraftable(result)){
				return null;
			}
			
			IRecipe rec = (IRecipe)CraftingManager.getInstance().getRecipeList().get(i);
			if(rec !=null && areStacksSameTypeCrafting(result, rec.getRecipeOutput())){
			System.out.println(rec);
			}
			
			if(rec !=null && areStacksSameTypeCrafting(result, rec.getRecipeOutput()) && result.isItemDamaged()){
				return null;
			}
			if(rec instanceof ShapedRecipes){
				ShapedRecipes shaped = (ShapedRecipes)rec;
			
				if(areStacksSameTypeCrafting(result, shaped.getRecipeOutput())){
					return shaped.recipeItems.clone();
				}
			}
			
			if(rec instanceof ShapelessRecipes){
				ShapelessRecipes shapeless = (ShapelessRecipes)rec;
				ItemStack[] stacks = new ItemStack[shapeless.recipeItems.size()];
				for(int j = 0; j < shapeless.recipeItems.size(); j++){
					ItemStack stack = ((ItemStack) shapeless.recipeItems.get(j));
					stacks[j] = stack.copy();
				}
				
				if(areStacksSameTypeCrafting(result, shapeless.getRecipeOutput())){
					return stacks;
				}
			}
			
			//Gosh dang it forge, you had to make this so complex! Took me hours! Why do you need to have a crafting table use a shaped ore recipe?
			if(rec instanceof ShapedOreRecipe){
				ShapedOreRecipe ore_rec = (ShapedOreRecipe)rec;
		        Object[] items = ((ShapedOreRecipe) rec).getInput();
		        for (Object item : items)
		            if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
		            return new ItemStack[9];
		        
		        if(areStacksSameTypeCrafting(result, ore_rec.getRecipeOutput())){
		            ItemStack[] stacks = new ItemStack[ore_rec.getInput().length];
			        Object[] obj_arr = ore_rec.getInput();
			        for(int j = 0; j < stacks.length; j++){
			        	Object obj = obj_arr[j];
			        	
			        	//If its an itemstack, its much easier.
			        	if(obj !=null && obj instanceof ItemStack){
			        	ItemStack stack = extractRecipeItems(obj)[0];
			        		Item item = stack.getItem();
			        		int size = stack.stackSize;
			        		int damage = stack.getItemDamage();
			        		stacks[j] = new ItemStack(item, size, damage);
			        	}
			        	//Checks if this ingredeint is a list, then extracts the itemstack and uses it in the recipe. 
			        	if(obj !=null && obj instanceof List){
			        		List<ItemStack> list = (List<ItemStack>)obj;
			        		ItemStack[] arr_stack = list.toArray(new ItemStack[0]);
			        		ItemStack stack = arr_stack[0];
			        		if(stack !=null){
			        			Item item = stack.getItem();
			        			int size = stack.stackSize;
			        			int damage = stack.getItemDamage();
			        			
			        			stacks[j] = new ItemStack(item, size, damage);
			        		}
			        	}
			        	
			        	if(obj !=null && obj instanceof ItemStack[]){
			        		ItemStack[] arr_stack = (ItemStack[])obj;
			        		ItemStack stack = arr_stack[0];
			        		if(stack !=null){
			        			Item item = stack.getItem();
			        			int size = stack.stackSize;
			        			int meta = stack.getItemDamage();
			        			stacks[j] = new ItemStack(item, size, meta);
			        		}
			        	}
			        	else if(stacks[j] == null){
			        		try {
								throw new CraftingRecipeException("The shaped ore recipe is not an instance of anything. Please consult your mod author for the mod, What Should be in minecraft.");
							} catch (CraftingRecipeException e) {
								e.printStackTrace();
							}
			        	}
			        }  
		        	return stacks;
		        }
			}
			
			if(rec instanceof ShapelessOreRecipe){
				ShapelessOreRecipe ore_rec = (ShapelessOreRecipe)rec;
				Object[] obj_arr = ore_rec.getInput().toArray();
				ItemStack[] stacks = new ItemStack[obj_arr.length];
						
				if(areStacksSameTypeCrafting(result, ore_rec.getRecipeOutput())){
					for(int j = 0; j < obj_arr.length; j++){
					Object obj = obj_arr[j];
						//If its an itemstack, its much easier.
			        	if(obj !=null && obj instanceof ItemStack){
			        	ItemStack stack = extractRecipeItems(obj)[0];
			        		Item item = stack.getItem();
			        		int size = stack.stackSize;
			        		int damage = stack.getItemDamage();
			        		stacks[j] = new ItemStack(item, size, damage);
			        	}
			        	//Checks if this ingredeint is a list, then extracts the itemstack and uses it in the recipe. 
			        	if(obj !=null && obj instanceof List){
			        		List<ItemStack> list = (List<ItemStack>)obj;
			        		ItemStack[] arr_stack = list.toArray(new ItemStack[0]);
			        		ItemStack stack = arr_stack[0];
			        		if(stack !=null){
			        			Item item = stack.getItem();
			        			int size = stack.stackSize;
			        			int damage = stack.getItemDamage();
			        			stacks[j] = new ItemStack(item, size, damage);
			        		}
			        	}
			        	if(obj !=null && obj instanceof ItemStack[]){
			        		ItemStack[] arr_stack = (ItemStack[])obj;
			        		ItemStack stack = arr_stack[0];
			        		if(stack !=null){
			        			Item item = stack.getItem();
			        			int size = stack.stackSize;
			        			int meta = stack.getItemDamage();
			        			stacks[j] = new ItemStack(item, size, meta);
			        		}
			        	}
			        	else{
			        		try {
								throw new CraftingRecipeException("The shapeless ore recipe is not an instance of anything. Please consult your mod author for the mod, What Should be in minecraft.");
							} catch (CraftingRecipeException e) {
								e.printStackTrace();
							}
			        	}
					}
					return stacks;
				}
			}
			else{
				
			}
			if(rec == null){
				continue;
			}
		}
		return null;
	
	}
	
	/**Adds an item to the black list that prevents the item from being un-crafted. Used to prevent condenser value exploits. */
	public static void addItemToNonUncraftableItems(Item item){
		blackListedItems.add(item);
	}

	/**Adds an item to the black list that prevents the item from being un-crafted. Used to prevent condenser value exploits. */
	public static void addItemToNonUncraftableItems(Block b){
		addItemToNonUncraftableItems(Item.getItemFromBlock(b));
	}
	public static boolean isItemStackUncraftable(ItemStack result){
		for(int i = 0; i < blackListedItems.size(); i++){
			Item item = blackListedItems.get(i);
			if(item == result.getItem()){
				return false;
			}
			else continue;
		}
		
		return true;
	}
    /**
     * {@link ItemStack}s with damage -1 are wildcards allowing all damages. Eg all colours of wool are allowed to create Beds.
     *
     * @param stack1 The {@link ItemStack} being compared.
     * @param stack2 The {@link ItemStack} to compare to.
     * @return whether the two items are the same from the perspective of a crafting inventory.
     */
    public static boolean areStacksSameTypeCrafting(ItemStack stack1, ItemStack stack2) {
        return stack1 != null && stack2 != null &&
                stack1.getItem() == stack2.getItem() &&
                (stack1.getItemDamage() == stack2.getItemDamage() || stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack1.getItem().isDamageable()) && stack1.stackSize == stack2.stackSize;
    }
    public static ItemStack[] extractRecipeItems(Object obj) {
        if (obj instanceof ItemStack)
            return new ItemStack[]{(ItemStack) obj};
        if (obj instanceof ItemStack[])
            return (ItemStack[]) obj;
        if (obj instanceof List)
            return ((List<ItemStack>) obj).toArray(new ItemStack[0]);

        throw new ClassCastException("not an ItemStack, ItemStack[] or List<ItemStack>?");
    }
}
