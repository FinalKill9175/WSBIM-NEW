package com.FinalKill.wsbim.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.FinalKill.wsbim.WSBIM;

public enum EnumFurnaceType {
	
	STONE(0, 240, Blocks.cobblestone, Item.getItemFromBlock(Blocks.cobblestone), Blocks.furnace, Blocks.lit_furnace,EnumFurnaceFunction.NORMAL, null),
	IRON(1, 125, Blocks.iron_block, Items.iron_ingot, WSBIM.blockIronFurnaceIdle, WSBIM.blockIronFurnaceActive, EnumFurnaceFunction.NORMAL,STONE),
	GOLD(2, 95, Blocks.gold_block, Items.gold_ingot, WSBIM.blockGoldFurnaceIdle, WSBIM.blockGoldFurnaceActive, EnumFurnaceFunction.NORMAL,IRON),
	DIAMOND(3, 40, Blocks.diamond_block, Items.diamond, WSBIM.blockDiamondFurnaceIdle, WSBIM.blockDiamondFurnaceActive,EnumFurnaceFunction.NORMAL, GOLD),
	OBSIDIAN(4, 300, Blocks.obsidian, Item.getItemFromBlock(Blocks.obsidian),WSBIM.blockObsidianFurnaceIdle, WSBIM.blockObsidianFurnaceActive,EnumFurnaceFunction.DOUBLE, IRON), 
	QUATZ(5, 165, Blocks.quartz_block, Items.quartz, WSBIM.blockQuartzFurnaceIdle, WSBIM.blockQuartzFurnaceActive,EnumFurnaceFunction.DOUBLE, OBSIDIAN),
	MIXEDMETAL(6, 20, WSBIM.main.blockMixedMetalBlock, WSBIM.main.itemMixedMetalIngot,WSBIM.blockMixedMetalFurnaceIdle, WSBIM.blockMixedMetalFurnaceActive,EnumFurnaceFunction.DOUBLE,DIAMOND),
	IRON2(7, 150, Blocks.iron_block, Item.getItemFromBlock(Blocks.iron_block), WSBIM.blockIronFurnace2Idle, WSBIM.blockIronFurnace2Active, EnumFurnaceFunction.DOUBLE, IRON),
	GOLD2(8, 50, Blocks.gold_block, Item.getItemFromBlock(Blocks.gold_block), WSBIM.blockGoldFurnaceIdle, WSBIM.blockGoldFurnaceActive, EnumFurnaceFunction.DOUBLE, GOLD),
	DIAMOND2(9, 80, Blocks.diamond_block, Item.getItemFromBlock(Blocks.diamond_block), WSBIM.blockDiamondFurnace2Idle, WSBIM.blockDiamondFurnace2Active, EnumFurnaceFunction.DOUBLE_FUNC, DIAMOND),
	OBSIDIAN2(10, 400, Blocks.obsidian, Item.getItemFromBlock(Blocks.obsidian), WSBIM.blockObsidianFurnace2Idle, WSBIM.blockObsidianFurnace2Active, EnumFurnaceFunction.DOUBLE_FUNC, OBSIDIAN),
	MIXEDMETAL2(11, 20, WSBIM.main.blockMixedMetalBlock, WSBIM.main.itemMixedMetalIngot, WSBIM.blockMixedMetalFurnace2Idle, WSBIM.blockMixedMetalFurnace2Active, EnumFurnaceFunction.DOUBLE_FUNC, MIXEDMETAL),
	IRON3(12, 125, Blocks.iron_block, Items.iron_ingot, WSBIM.blockIronFurnace3Idle, WSBIM.blockIronFurnace3Active, EnumFurnaceFunction.DOUBLE_FUNC, IRON2),
	GOLD3(13, 95, Blocks.gold_block, Items.gold_ingot, WSBIM.blockGoldFurnace3Idle, WSBIM.blockGoldFurnace3Active, EnumFurnaceFunction.DOUBLE_FUNC, GOLD2),
	DIAMOND3(14, 100, Blocks.diamond_block, Item.getItemFromBlock(Blocks.diamond_block), WSBIM.blockDiamondFurnace3Idle, WSBIM.blockDiamondFurnace3Active, EnumFurnaceFunction.QUAD_FUNC, DIAMOND2),
	MIXEDMETAL3(15, 20, WSBIM.main.blockMixedMetalBlock, WSBIM.main.itemMixedMetalIngot, WSBIM.blockMixedMetalFurnace3Idle, WSBIM.blockMixedMetalFurnace3Active, EnumFurnaceFunction.QUAD_FUNC, MIXEDMETAL2),
	ULTIMATE(16, 3, WSBIM.main.blockMixedMetalBlock, WSBIM.main.itemMixedMetalIngot, WSBIM.blockUltimateFurnaceIdle, WSBIM.blockUltimateFurnaceActive, EnumFurnaceFunction.QUAD_FUNC, MIXEDMETAL3);

	
    private int speed;
	private Block block;
	private Item craft;
	private EnumFurnaceType prod;
	private Block idle;
	private Block active;
	private EnumFurnaceFunction func;
	private int id;

    private EnumFurnaceType(int id, int speed, Block mainBlock, Item craftItem, Block furnaceBlockIde, Block furnaceBlockActive, EnumFurnaceFunction func, EnumFurnaceType prodecessor)
    {
    this.id = id;
      this.speed = speed;
      block = mainBlock;
      craft = craftItem;
      prod = prodecessor;
      idle = furnaceBlockIde;
      active = furnaceBlockActive;
      this.func = func;
    }
    
    public int getTicksPerSmelt(){
    	return speed;
    }
    
    public Block getMainBlock(){
    	return block;
    }
    
    public Item getCraftingItem(){
    	return craft;
    }
    public EnumFurnaceType getProdesessor(){
    	return prod;
    }
    
    public Block getFurnaceBlockIdle(){
    	return idle;
    }
    public Block getFurnaceBlockActive(){
    	return active;
    }
    public EnumFurnaceFunction getFurnaceFunction(){
    	return func;
    }
    public int getID(){
    	return id;
    }
}
