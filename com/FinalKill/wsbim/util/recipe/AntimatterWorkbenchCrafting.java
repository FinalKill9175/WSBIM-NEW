package com.FinalKill.wsbim.util.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AntimatterWorkbenchCrafting {
	
	private static List<AntimatterWorkbenchRecipe> craftingRecipes = new ArrayList<AntimatterWorkbenchRecipe>();
	private static List<Item> fuelStacks = new ArrayList<Item>();
	private static List<Integer> fuelVals = new ArrayList<Integer>();
	
	static int cap = 1000;

	
	public static ItemStack[] convertInputStacks(Object... data){
		if(data.length < 5){
			return null;
		}
		
	    if((!(data[0] instanceof String)) || (!(data[1] instanceof String)) || (!(data[2] instanceof String))){
	    	return null;
	    }
	    
	    if((((String)data[0]).length() < 4) || (((String)data[1]).length() < 4) || (((String)data[2]).length() < 4)){
	    	return null;
	    }
	    //("iiii","iiii","iiii", 'i', new ItemStack(Blocks.dirt))
	    ItemStack[] inputStacks = new ItemStack[12];
	    
	    String row1 = (String)data[0];
	    String row2 = (String)data[1];
	    String row3 = (String)data[2];
	    char[] row1_char = row1.toCharArray();  
	    char[] row2_char = row2.toCharArray();
	    char[] row3_char = row3.toCharArray();

	    List<Character> charlist2 = new ArrayList<Character>();
	    List<ItemStack> stacklist = new ArrayList<ItemStack>();
	    
	    List<Object> data_list = new ArrayList<Object>();
	    
	    for(int i = 0; i < data.length; i++){
	    	data_list.add(data[i]);
	    }
	    
	    for(int i = 0; i < 100; i++){
	    	stacklist.add(null);
	    }
	    
	    for(int i = 3; i < data.length; i++){
	    	if(data[i] instanceof Character){
	    		charlist2.add((Character)data[i]);
	    	}
	    	if(data[i] instanceof ItemStack){
	    		stacklist.set(i, (ItemStack) data[i]);
	    	}
	    	
	    }
	    
	    for(int i = 0; i < row1_char.length; i++){
	    	char c = row1_char[i];
	    	if(charlist2.contains(c) && stacklist.get(data_list.indexOf(Character.valueOf(c))+1) !=null){
	    		inputStacks[i] = stacklist.get(data_list.indexOf(Character.valueOf(c))+1);
	    	}
	    }
	    
	    for(int i = 0; i < row2_char.length; i++){
	    	char c = row2_char[i];
	    	if(charlist2.contains(c) && stacklist.get(data_list.indexOf(Character.valueOf(c))+1) !=null){
	    		inputStacks[i+4] = stacklist.get(data_list.indexOf(Character.valueOf(c))+1);
	    	}
	    }
	    
	    for(int i = 0; i < row3_char.length; i++){
	    	char c = row3_char[i];
	    	if(charlist2.contains(c) && stacklist.get(data_list.indexOf(Character.valueOf(c))+1) !=null){
	    		inputStacks[i+8] = stacklist.get(data_list.indexOf(Character.valueOf(c))+1);
	    	}
	    }
	    
		return inputStacks;
	}
	
	public static void registerCraftingRecipe(ItemStack outputStack, Object... recipe){
		ItemStack[] inputStacks = convertInputStacks(recipe);
		if(inputStacks !=null){
			craftingRecipes.add(new AntimatterWorkbenchRecipe(outputStack, inputStacks));
			return;
		}
		else{
			System.err.println("Error in creating an antimatter crafting recipe with the ouput "+outputStack);
			return;
		}
	}
	
	public static void registerCraftingRecipe(ItemStack outputStack, ItemStack... recipe){
		ItemStack[] inputStacks = recipe;
		if(inputStacks !=null){
			craftingRecipes.add(new AntimatterWorkbenchRecipe(outputStack, inputStacks));
			JOptionPane.showMessageDialog(null, inputStacks);
			return;
		}
		else{
			System.err.println("Error in creating an antimatter crafting recipe with the ouput "+outputStack);
			return;
		}
	}
	
	public static void registerFuelItemStack(int val, Item fuelStack){
		fuelStacks.add(fuelStack);
		fuelVals.add(val);
	}
	
	public static ItemStack[] getIngredientsFromOutput(ItemStack output){
		for(int i = 0; i < craftingRecipes.size(); i++){
			AntimatterWorkbenchRecipe r = craftingRecipes.get(i);
			if(r !=null){
				if(areStacksSameTypeCrafting(output, r.getOutputStack())){
					return r.getInputStacks();
				}
			}
		}
		
		
		return null;
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
    
    
    public static boolean isItemFuel(Item stack){
    	for(int i = 0; i < fuelStacks.size(); i++){
    		Item c = fuelStacks.get(i);
    		if(c !=null){
    			if(c == stack){
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    public static int getValueForStack(Item stack){
    	if(!isItemFuel(stack)){
    		return 0;
    	}
    	else{
    		return fuelVals.get(fuelStacks.indexOf(stack));
    	}
    }
    
    public static ItemStack getOuputForIngredients(ItemStack... recipe){
    	
    	
    	ItemStack stack0 = recipe[0];
    	ItemStack stack1 = recipe[1];
    	ItemStack stack2 = recipe[2];
    	ItemStack stack3 = recipe[3];
    	ItemStack stack4 = recipe[4];
    	ItemStack stack5 = recipe[5];
    	ItemStack stack6 = recipe[6];
    	ItemStack stack7 = recipe[7];
    	ItemStack stack8 = recipe[8];
    	ItemStack stack9 = recipe[9];
    	ItemStack stack10 = recipe[10];
    	ItemStack stack11 = recipe[11];
    	
    	for(int i = 0; i < craftingRecipes.size(); i++){
    		AntimatterWorkbenchRecipe r = craftingRecipes.get(i);
    		if(r !=null){
    			ItemStack rstack0 = r.getInputStacks()[0];
    			ItemStack rstack1 = r.getInputStacks()[1];
    			ItemStack rstack2 = r.getInputStacks()[2];
    			ItemStack rstack3 = r.getInputStacks()[3];
    			ItemStack rstack4 = r.getInputStacks()[4];
    			ItemStack rstack5 = r.getInputStacks()[5];
    			ItemStack rstack6 = r.getInputStacks()[6];
    			ItemStack rstack7 = r.getInputStacks()[7];
    			ItemStack rstack8 = r.getInputStacks()[8];
    			ItemStack rstack9 = r.getInputStacks()[9];
    			ItemStack rstack10 = r.getInputStacks()[10];
    			ItemStack rstack11 = r.getInputStacks()[11];
    			
    			boolean same0 = areStacksSameTypeCraftingNoStackSizeChecks(stack0, rstack0);
    			boolean same1 = areStacksSameTypeCraftingNoStackSizeChecks(stack1, rstack1);
    			boolean same2 = areStacksSameTypeCraftingNoStackSizeChecks(stack2, rstack2);
    			boolean same3 = areStacksSameTypeCraftingNoStackSizeChecks(stack3, rstack3);
    			boolean same4 = areStacksSameTypeCraftingNoStackSizeChecks(stack4, rstack4);
    			boolean same5 = areStacksSameTypeCraftingNoStackSizeChecks(stack5, rstack5);
    			boolean same6 = areStacksSameTypeCraftingNoStackSizeChecks(stack6, rstack6);
    			boolean same7 = areStacksSameTypeCraftingNoStackSizeChecks(stack7, rstack7);
    			boolean same8 = areStacksSameTypeCraftingNoStackSizeChecks(stack8, rstack8);
    			boolean same9 = areStacksSameTypeCraftingNoStackSizeChecks(stack9, rstack9);
    			boolean same10 = areStacksSameTypeCraftingNoStackSizeChecks(stack10, rstack10);
    			boolean same11 = areStacksSameTypeCraftingNoStackSizeChecks(stack11, rstack11);
    			
    			if(same0 && same1 && same2 && same3 && same4 && same5 && same6 && same7 && same8 && same9 && same10 && same11){
    				return r.getOutputStack();
    			}
    			
    		}
    	}
    	
    	return null;
    }

	private static boolean areStacksSameTypeCraftingNoStackSizeChecks(
			ItemStack stack1, ItemStack stack2) {
	     return stack1 != null && stack2 != null &&
	                stack1.getItem() == stack2.getItem() &&
	                (stack1.getItemDamage() == stack2.getItemDamage() || stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack1.getItem().isDamageable());

	}
}
