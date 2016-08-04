package com.FinalKill.wsbim.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TempuratureUtils {
	public static float getTempForBiome(BiomeGenBase b){
		return (b.temperature * 100 - 0F > 110F? 110F : b.temperature * 100 - 0F);
	}
	public static float getSurroundingBlockTemperature(World w, int x, int z){
		return getTempForBiomeCalc(w, w.getBiomeGenForCoords(x, z));
	}
	
	public static float getTempForBiomeCalc(World w, BiomeGenBase b){
		return w.provider.dimensionId == -1 ? 250F : (w.isDaytime() ? getTempForBiome(b) : getTempForBiome(b) - (getTempForBiome(b)>79F? 20F : 10F));
	}
	
	/**Can this item power the freezer?*/
	public static boolean isItemCold(Item item){
		return getItemColdness(item) > 0;
	}
	
	/**Gets how many ticks that the item will last for in the freezer.*/
	public static int getItemColdness(Item item){
		
		if(item == Items.snowball) return 160;
		if(item instanceof ItemBlock && Block.getBlockFromItem(item) !=Blocks.air && Block.getBlockFromItem(item).getMaterial() == Material.ice){
			return 750;
		}
		if(item instanceof ItemBlock && Block.getBlockFromItem(item) !=Blocks.air && Block.getBlockFromItem(item).getMaterial() == Material.snow){
			return 750/3;
		}
		if(item == Item.getItemFromBlock(Blocks.snow)){
			return 480;
		}
		if(item == Item.getItemFromBlock(Blocks.packed_ice)) return 750 * 4;
			else return 0;	
		
	}
	/**Is the block a cold block? Will it decrease the tempurature?*/
	public static boolean isBlockCold(Block block){
		if(block.getMaterial() == Material.ice) return true;
		if(block.getMaterial() == Material.snow) return true;
		if(block == Blocks.ice || block == Blocks.snow || block == Blocks.snow_layer || block == Blocks.packed_ice) return true;
		
		return false;
	}
	
	/**Is the block a warm/hot block? Will it increase the tempurature?*/
	public static boolean isBlockWarm(Block block){
		if(block.getMaterial() == Material.fire) return true;
		if(block.getMaterial() == Material.lava) return true;
		if(block == Blocks.lava || block == Blocks.fire || block == Blocks.flowing_lava) return true;
		
		return false;
	}
}
