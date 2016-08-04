package com.FinalKill.Machinery_Craft;

import com.FinalKill.Machinery_Craft.blocks.MachineryCraftBlocks;
import com.FinalKill.Machinery_Craft.items.MachineryCraftItems;
import com.FinalKill.condenservalues.CondenserValuesAPI;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachineryCraftCrafting {
	
	private static MachineryCraftBlocks blocks = MachineryCraft.blocks;
	
	private static MachineryCraftItems items = MachineryCraft.items;
	
	
		
		public static void registerCrafting(){
		
		
		//GameRegistry.addRecipe(new ItemStack(blocks.industrialIronBlock), "iii","iii","iii", 'i', new ItemStack(MachineryCraft.items.industrialIron));
		//GameRegistry.addShapelessRecipe(new ItemStack(MachineryCraft.items.industrialIron, 9), new Object[]{
			
		//	blocks.industrialIronBlock
		//});
		
				GameRegistry.addRecipe(new ItemStack(blocks.chestDropper)," i ","ici"," i ", 'i', new ItemStack(items.industrialIron),'c',new ItemStack(Blocks.chest));
		
			
				for(int i = 0; i<blocks.coloredStone.length; i++){
					
					GameRegistry.addShapelessRecipe(new ItemStack(blocks.coloredStone[i]), new ItemStack(Blocks.stone), new ItemStack(Items.dye, 1, i));
					
				}
for(int i = 0; i<blocks.coloredWood.length; i++){
					
					GameRegistry.addShapelessRecipe(new ItemStack(blocks.coloredWood[i]), new ItemStack(Blocks.planks), new ItemStack(Items.dye, 1, i));
					
				}


for(int i = 0; i<blocks.coloredStoneSlab.length; i++){
	
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredStoneSlab[i], 6), "sss","   ","   ",'s', new ItemStack(blocks.coloredStone[i]));
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredStoneSlab[i], 6), "   ","sss","   ",'s', new ItemStack(blocks.coloredStone[i]));
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredStoneSlab[i], 6), "   ","   ","sss",'s', new ItemStack(blocks.coloredStone[i]));
	GameRegistry.addShapelessRecipe(new ItemStack(blocks.coloredStone[i]), new ItemStack(blocks.coloredStoneSlab[i]), new ItemStack(blocks.coloredStoneSlab[i]));

	
}
for(int i = 0; i<blocks.coloredWoodSlab.length; i++){
	
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredWoodSlab[i], 6), "sss","   ","   ",'s', new ItemStack(blocks.coloredWood[i]));
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredWoodSlab[i], 6), "   ","sss","   ",'s', new ItemStack(blocks.coloredWood[i]));
	GameRegistry.addShapedRecipe(new ItemStack(blocks.coloredWoodSlab[i], 6), "   ","   ","sss",'s', new ItemStack(blocks.coloredWood[i]));
	GameRegistry.addShapelessRecipe(new ItemStack(blocks.coloredWood[i]), new ItemStack(blocks.coloredWoodSlab[i]), new ItemStack(blocks.coloredWoodSlab[i]));

	
}

		
	}

}
