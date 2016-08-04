package com.FinalKill.Machinery_Craft.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorRandomBonusChest implements IWorldGenerator {
	
	public static final Item[] rand_items = {Items.iron_ingot, MachineryCraft.items.tin_ingot, MachineryCraft.items.copper_ingot, Items.stick, new ItemStack(Blocks.log).getItem(), Items.wooden_pickaxe, Items.wooden_axe, Items.wooden_hoe, Items.wooden_shovel, Items.wooden_sword, Items.book, Items.sugar, Items.paper, MachineryCraft.items.iron_ore_dust, MachineryCraft.items.tin_dust, MachineryCraft.items.copper_dust, Items.stone_pickaxe, Items.stone_axe, new ItemStack(Blocks.torch).getItem(), Items.apple};

	public void generate(Random rand, int x, int z, World world, IChunkProvider chunks, IChunkProvider chunks2) {

    	if(rand.nextInt(40) == 0){
    		
    		int xCoord = x*16+ rand.nextInt(8) - rand.nextInt(4);
    		int zCoord = z*16+rand.nextInt(8) - rand.nextInt(4);
    		int yCoord = world.getHeightValue(xCoord, zCoord);
    		
    			
    		world.setBlock(xCoord, yCoord, zCoord, Blocks.chest);
    		
    		TileEntityChest t = (TileEntityChest) world.getTileEntity(xCoord, yCoord, zCoord);
    		
    		for(int i=0; i<t.getSizeInventory(); i++){
    			if(rand.nextInt(4) == 0){
    				Item rand_item = this.rand_items[rand.nextInt(this.rand_items.length)];
    				if(rand_item !=null){
    				t.setInventorySlotContents(i, new ItemStack(rand_item, 1+rand.nextInt(1)));
    				}
    			}
    			
    			
    		}
    
    		
    	}
    	
	}

}
