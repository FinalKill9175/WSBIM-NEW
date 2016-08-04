package com.FinalKill.wsbim.util.recipe;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.ItemStack;

public class AntimatterWorkbenchRecipe {
	
	private ItemStack[] inputStacks = new ItemStack[12];
	private ItemStack outputStack;
	
	public AntimatterWorkbenchRecipe(ItemStack outputStack, ItemStack... inputStacks){
		this.outputStack = outputStack;
		if(inputStacks.length > 12){
			try {
				Throwable t = new Throwable("You only can have 12 input stacks for a Antimatter Workbench Recipe");
				Minecraft.getMinecraft().crashed(new CrashReport("AnitmatterWorkbench Recipe Error", t));
				throw t;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		else{
			this.inputStacks = inputStacks;
		}
		
	}
	
	public ItemStack[] getInputStacks(){
		return this.inputStacks;
	}
	
	public ItemStack getOutputStack(){
		return this.outputStack;
	}
}
