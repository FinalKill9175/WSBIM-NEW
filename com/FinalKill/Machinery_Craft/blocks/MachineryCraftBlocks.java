package com.FinalKill.Machinery_Craft.blocks;

import java.awt.DisplayMode;
import java.lang.annotation.ElementType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.TextureName;
import com.FinalKill.Machinery_Craft.tabs.MachineryCraftTabs;
import com.FinalKill.Machinery_Craft.tile.*;
import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.registry.GameRegistry;

public class MachineryCraftBlocks {


	//Ores+Materials
	public static Block industrialIronBlock;
	public static Block tin_ore;
	public static Block copper_ore;
	
	
	//Power-Related Blocks
	public static Block iron_cable;
	public static Block macerator;
	public static Block macerator_active;
	public static Block generator;
	public static Block generator_active;
	public static Block power_furance;
	public static Block power_furance_active;
	/**
	 * DO NOT USE
	 */
	public static Block iron_furnace;
	/**
	 * DO NOT USE
	 */
	public static Block iron_furnace_acitve;
	
	//Logical
	public static Block chestDropper;
	public static Block rainCollector;
	
	//Furniture
	public static Block fridge;
	
	
	//Decorational
	public static Block ironChest;
	public static Block goldChest;
	public static Block diamondChest;
	public static Block woodenTable;
	public static Block stoneTable;
	public static Block trashCan;
	
	//Colored Stuff
	public static Block[] lamps = new Block[14];
	public static Block[] coloredStone = new Block[16];
	public static Block[] coloredStoneSlab = new Block[16];
	public static Block[] coloredWood = new Block[16];
	public static Block[] coloredWoodSlab = new Block[16];
	
	
	
	
	private static MachineryCraftTabs tabs = MachineryCraft.tabs;
	
	public void registerBlocks(){
	
	//	materialCondenser = new BlockCondenser().setCreativeTab(MachineryCraft.tabs.machinesTab).setBlockName("condenser");
    	//GameRegistry.registerBlock(materialCondenser, "condenser");
    	//GameRegistry.registerTileEntity(TileEntityCondenser.class, "tile.condenser");
    	//ironChest = new BlockIronChest("ironChest").setCreativeTab(MachineryCraft.tabs.chestsTab);
    	//goldChest = new BlockGoldChest("goldChest").setCreativeTab(MachineryCraft.tabs.chestsTab);
    	//diamondChest = new BlockDiamondChest("diamondChest").setCreativeTab(MachineryCraft.tabs.chestsTab);
    	//GameRegistry.registerTileEntity(TileEntityIronChest.class, "ironChest");
    	//GameRegistry.registerTileEntity(TileEntityGoldChest.class, "goldChest");
    	//GameRegistry.registerTileEntity(TileEntityDiamondChest.class, "diamondChest");
    	//GameRegistry.addRecipe(new ItemStack(ironChest), "iii","ici","iii", 'i', new ItemStack(Items.iron_ingot), 'c', new ItemStack(Blocks.chest));
       //	GameRegistry.addRecipe(new ItemStack(goldChest), "ggg","gcg","ggg", 'g', new ItemStack(Items.gold_ingot), 'c', new ItemStack(ironChest));
       	//GameRegistry.addRecipe(new ItemStack(diamondChest), "ddd","dcd","ddd", 'd', new ItemStack(Items.diamond), 'c', new ItemStack(goldChest));

    	woodenTable =  new BlockTable(Material.wood, 2.0F, 10, Block.soundTypeWood).setCreativeTab(WSBIM.main.ourTab).setBlockName("woodenTable").setBlockTextureName("log_oak");
    	GameRegistry.registerBlock(woodenTable, woodenTable.getUnlocalizedName());
    	GameRegistry.registerTileEntity(TileEntityTable.class, woodenTable.getUnlocalizedName());
    	GameRegistry.addRecipe(new ItemStack(woodenTable), "lll","s s","s s",'l',new ItemStack(Blocks.log), 's', new ItemStack(Items.stick));
    	
    	
    	//tin_ore = new BlockModOre(2.0F, 10.0F, 3.0F, 2.85F, 2.0F, 1.9F).setBlockName("tin_ore").setBlockTextureName("machinery_craft:tin_ore").setCreativeTab(tabs.materialsTab).setResistance(4.5F);
    	//copper_ore = new BlockModOre(2.0F, 3.0F, 2.85F, 2.5F, 1.8F, 1.5F).setBlockName("copper_ore").setBlockTextureName("machinery_craft:copper_ore").setCreativeTab(tabs.materialsTab).setResistance(5.0F);
    	//GameRegistry.registerBlock(tin_ore, "tin_ore_machineryCraft");
    	//GameRegistry.registerBlock(copper_ore, "copper_ore_machineryCraft");
    
    	
    	trashCan = new BlockTrashcan(Material.iron, 2.5F, 100, Block.soundTypeMetal).setCreativeTab(WSBIM.main.ourTab).setBlockName("trashcan").setBlockTextureName("anvil_base");
    	GameRegistry.registerBlock(trashCan, "trashcan");
    	GameRegistry.registerTileEntity(TileEntityTrashcan.class, "trashcan");
    	    	GameRegistry.addRecipe(new ItemStack(trashCan), "i i","i i"," i ",'i',new ItemStack(Items.iron_ingot));
    	/**    	
    	chargingStation = new BlockChargingStation().setCreativeTab(MachineryCraft.tabs.machinesTab).setBlockName("chargingStation");
    	GameRegistry.registerBlock(chargingStation, chargingStation.getUnlocalizedName());
    	GameRegistry.registerTileEntity(TileEntityChargingStation.class, chargingStation.getUnlocalizedName());
    	GameRegistry.addRecipe(new ItemStack(chargingStation), "lll","iri","s s",'l', new ItemStack(Blocks.log),'i',new ItemStack(MachineryCraft.items.industrialIron),'r',new ItemStack(Blocks.redstone_block),'s',new ItemStack(Items.stick));
*/
    	//macerator = new BlockMacerator()/**.setCreativeTab(tabs.machinesTab)*/.setBlockName("macerator").setBlockTextureName("iron_block");
    	//GameRegistry.registerBlock(macerator, macerator.getUnlocalizedName());
    	//GameRegistry.registerTileEntity(TileEntityMacerator.class, "macerator");
    	//macerator_active = new BlockMacerator().setBlockName("macerator_active").setBlockTextureName("iron_block").setLightLevel(0.8F).setLightOpacity(10);
    	//GameRegistry.registerBlock(macerator_active, macerator_active.getUnlocalizedName());
    	//GameRegistry.registerTileEntity(TileEntityMacerator.class, "macerator_active");

    	//generator = new BlockGenerator(false).setBlockName("generator")/**.setCreativeTab(tabs.machinesTab)*/;
    	//generator_active = new BlockGenerator(true).setBlockName("generator_active").setLightOpacity(10).setLightLevel(0.75F);
    	//GameRegistry.registerBlock(generator, "generator");
    	//GameRegistry.registerBlock(generator_active, "generator_active");
    	//GameRegistry.registerTileEntity(TileEntityGenerator.class, "generator");
    	
    	//industrialIronBlock = new BlockIndustrialIronBlock().setBlockName("industrialIronBlock").setBlockTextureName(new TextureName("industrialIronBlock",MachineryCraft.MODID).getTextureName()).setCreativeTab(tabs.materialsTab).setStepSound(Block.soundTypeMetal);
		//GameRegistry.registerBlock(industrialIronBlock, industrialIronBlock.getUnlocalizedName());
	
		
		//chestDropper  = new BlockChestDropper().setCreativeTab(tabs.machinesTab).setBlockName("chestDropper").setBlockTextureName(new TextureName("chestDropper",MachineryCraft.MODID.toLowerCase()).getTextureName());
		//GameRegistry.registerBlock(chestDropper, "chestDropper");
		
		
		//iron_cable= new BlockIronCable().setBlockName("iron_cable").setBlockTextureName("wool_colored_black");
		//GameRegistry.registerBlock(iron_cable, "iron_cable");
		//GameRegistry.registerTileEntity(TileEntityIronCable.class, "iron_cable");
		
		
		rainCollector = new BlockRainCollector().setBlockName("rainCollector").setBlockTextureName("glass");
		GameRegistry.registerBlock(rainCollector, "rainCollector_machineryCraft");
		GameRegistry.registerTileEntity(TileEntityRainCollector.class, "rainCollector_machineryCraft");
			
		for(int i = 0; i<lamps.length; i++){
			lamps[i] = new BlockLamp(i);
			GameRegistry.registerBlock(lamps[i], lamps[i].getUnlocalizedName());
			
		}
		for(int i=0; i<coloredStone.length; i++){
			this.coloredStone[i] = new BlockColoredStone(i,Material.rock).setCreativeTab(WSBIM.main.ourTab).setHardness(3.0F).setResistance(1F).setBlockName(BlockColoredStone.names[i]+"_stone").setBlockTextureName("stone");
			GameRegistry.registerBlock(coloredStone[i], BlockColoredStone.names[i]+"_stone");
			
		}
		for(int i=0; i<coloredStoneSlab.length; i++){
			this.coloredStoneSlab[i] = new BlockColoredStoneSlab(i,Material.rock).setCreativeTab(WSBIM.main.ourTab).setHardness(3.0F).setResistance(1F).setBlockName(BlockColoredStone.names[i]+"_stone_slab").setBlockTextureName("stone");
			GameRegistry.registerBlock(coloredStoneSlab[i], BlockColoredStone.names[i]+"_stone_slab");
			
		}
		for(int i=0; i<coloredWood.length; i++){
			this.coloredWood[i] = new BlockColoredWood(i).setCreativeTab(WSBIM.main.ourTab).setBlockName(BlockColoredWood.names[i]+"_planks").setBlockTextureName("wsbim_machinery:coloredPlanks").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(5.0F);
			GameRegistry.registerBlock(coloredWood[i], BlockColoredWood.names[i]+"_planks");
			
		}
		for(int i=0; i<coloredWoodSlab.length; i++){
			this.coloredWoodSlab[i] = new BlockColoredWoodSlab(i).setCreativeTab(WSBIM.main.ourTab).setBlockName(BlockColoredWood.names[i]+"_planks_slab").setBlockTextureName("wsbim_machinery:coloredPlanks").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(5.0F);
			GameRegistry.registerBlock(coloredWoodSlab[i], BlockColoredWood.names[i]+"_planks_slab");
			
		}
		
		
		//this.power_furance = new BlockPowerFurnace(false).setCreativeTab(tabs.machinesTab).setBlockName("powerFurnace").setBlockTextureName("machinery_craft:iron_old");
		//GameRegistry.registerBlock(this.power_furance, "powerFurnace");
		//this.power_furance_active = new BlockPowerFurnace(true).setBlockName("powerFurnaceActive").setLightOpacity(1).setLightLevel(0.8F).setBlockTextureName("machinery_craft:iron_old");
		//GameRegistry.registerBlock(this.power_furance_active, "powerFuranceActive");
		//GameRegistry.registerTileEntity(TileEntityPowerFurnace.class, "powerFurnace_machineryCraft");
	
	/**
		this.iron_furnace = new BlockIronFurnace(false).setCreativeTab(tabs.machinesTab).setBlockName("ironFurnace").setBlockTextureName("iron_block");
		this.iron_furnace_acitve = new BlockIronFurnace(true).setBlockName("ironFurnaceActive").setBlockTextureName("iron_block").setLightOpacity(1).setLightLevel(0.8F);
		GameRegistry.registerBlock(iron_furnace, "ironFurnace_machineryCraft");
		GameRegistry.registerBlock(iron_furnace_acitve, "ironFurnaceActive_machineryCraft");
		GameRegistry.registerTileEntity(TileEntityIronFurnace.class, "ironFurnace");
		*/
		
		//this.fridge = new BlockFridge().setBlockName("fridge").setBlockTextureName("iron_block");
		//GameRegistry.registerBlock(fridge, "fridge_machineryCraft");
		//GameRegistry.registerTileEntity(TileEntityFridge.class, "fridge_machineryCraft");

			}
	
}
