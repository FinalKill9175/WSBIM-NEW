package com.FinalKill.wsbim;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.wsbim.client.gui.theme.HUDThemes;
import com.FinalKill.wsbim.common.block.BlockAntimatterWorkbench;
import com.FinalKill.wsbim.common.block.BlockCondenser;
import com.FinalKill.wsbim.common.block.BlockEnergyCollector;
import com.FinalKill.wsbim.common.block.BlockFreezer;
import com.FinalKill.wsbim.common.block.BlockUncraftingTable;
import com.FinalKill.wsbim.common.block.BlockUpgradedChest;
import com.FinalKill.wsbim.common.block.BlockUpgradedFurnace;
import com.FinalKill.wsbim.common.event.NBTCopyItemEvent;
import com.FinalKill.wsbim.common.event.TickPlayerEvent;
import com.FinalKill.wsbim.common.event.tooltip.ItemTooltipFullName;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.common.item.ItemFrozenFood;
import com.FinalKill.wsbim.common.item.ItemMeal;
import com.FinalKill.wsbim.common.item.ItemModdedFood;
import com.FinalKill.wsbim.common.item.ItemWSBIM;
import com.FinalKill.wsbim.common.net.PacketDispatcher;
import com.FinalKill.wsbim.common.proxy.CommonProxy;
import com.FinalKill.wsbim.common.tileentity.TileEntityAntimatterWorkbench;
import com.FinalKill.wsbim.common.tileentity.TileEntityCopperChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityDiamondChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityDiamondFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityDiamondFurnace2;
import com.FinalKill.wsbim.common.tileentity.TileEntityDiamondFurnace3;
import com.FinalKill.wsbim.common.tileentity.TileEntityEmeraldChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollector;
import com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollectorMK2;
import com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollectorMK3;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;
import com.FinalKill.wsbim.common.tileentity.TileEntityGoldChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityGoldFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityGoldFurnace2;
import com.FinalKill.wsbim.common.tileentity.TileEntityGoldFurnace3;
import com.FinalKill.wsbim.common.tileentity.TileEntityIronChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityIronFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityIronFurnace2;
import com.FinalKill.wsbim.common.tileentity.TileEntityIronFurnace3;
import com.FinalKill.wsbim.common.tileentity.TileEntityMixedMetalChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityMixedMetalFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityMixedMetalFurnace2;
import com.FinalKill.wsbim.common.tileentity.TileEntityMixedMetalFurnace3;
import com.FinalKill.wsbim.common.tileentity.TileEntityObsidianChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityObsidianFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityObsidianFurnace2;
import com.FinalKill.wsbim.common.tileentity.TileEntityQuartzFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityUltimateFurnace;
import com.FinalKill.wsbim.util.ChestRegistry;
import com.FinalKill.wsbim.util.EnumBackpackSize;
import com.FinalKill.wsbim.util.EnumChestType;
import com.FinalKill.wsbim.util.EnumFurnaceType;
import com.FinalKill.wsbim.util.FrozenFoods;
import com.FinalKill.wsbim.util.FurnaceRegistry;
import com.FinalKill.wsbim.util.UncraftingRecipes;
import com.FinalKill.wsbim.util.condenservalues.EnumEnergyCollector;
import com.FinalKill.wsbim.util.recipe.AntimatterWorkbenchCrafting;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class WSBIM {
	
	public static final String modid = "wsbim";
	public static final String version = "1.7.10-1.5-Pre-Release";
	public static final String name = "What Should be in Minecraft (WSBIM)";

	//TODO 
	//update .cfg file every time forge updates,
	//to get method names use 
	//Add a way of warning the user that tabs must be disabled on vanilla servers
	//Creative Tabs
	public static final CreativeTabs tabMagical = new CreativeTabs("wsbimMagical"){
		@Override
		public Item getTabIconItem() {
			if(Loader.isModLoaded("condenser_values_api")){
				return Item.getItemFromBlock(blockCondenser);
			}
			else{
				return itemTabIconMagical;
			}
		}
	};
	
	public static final HUDThemes themes = new HUDThemes();

	
	public static final CreativeTabs tabMechanical = new CreativeTabs("wsbimMechanical"){
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(blockFreezerIdle);
		}
	};
	
	public static final CreativeTabs tabFood = new CreativeTabs("wsbimFood"){
		@Override
		public Item getTabIconItem() {
			return itemChickenSandwich;
		}
	};
	//Blocks
	public static BlockUpgradedFurnace blockIronFurnaceIdle;
	public static BlockUpgradedFurnace blockIronFurnaceActive;
	
	public static BlockUpgradedFurnace blockQuartzFurnaceIdle;
	public static BlockUpgradedFurnace blockQuartzFurnaceActive;

	public static BlockUpgradedFurnace blockGoldFurnaceIdle;
	public static BlockUpgradedFurnace blockGoldFurnaceActive;

	public static BlockUpgradedFurnace blockDiamondFurnaceIdle;
	public static BlockUpgradedFurnace blockDiamondFurnaceActive;
	
	public static BlockUpgradedFurnace blockObsidianFurnaceIdle;
	public static BlockUpgradedFurnace blockObsidianFurnaceActive;
	
	public static BlockUpgradedFurnace blockMixedMetalFurnaceIdle;
	public static BlockUpgradedFurnace blockMixedMetalFurnaceActive;
	
	public static BlockUpgradedFurnace blockIronFurnace2Idle;
	public static BlockUpgradedFurnace blockIronFurnace2Active;
	
	public static BlockUpgradedFurnace blockGoldFurnace2Idle;
	public static BlockUpgradedFurnace blockGoldFurnace2Active;
	
	public static BlockUpgradedFurnace blockDiamondFurnace2Idle;
	public static BlockUpgradedFurnace blockDiamondFurnace2Active;
	
	public static BlockUpgradedFurnace blockObsidianFurnace2Idle;
	public static BlockUpgradedFurnace blockObsidianFurnace2Active;
	
	public static BlockUpgradedFurnace blockMixedMetalFurnace2Idle;
	public static BlockUpgradedFurnace blockMixedMetalFurnace2Active;
	
	public static BlockUpgradedFurnace blockIronFurnace3Idle;
	public static BlockUpgradedFurnace blockIronFurnace3Active;
	
	public static BlockUpgradedFurnace blockGoldFurnace3Idle;
	public static BlockUpgradedFurnace blockGoldFurnace3Active;
	
	public static BlockUpgradedFurnace blockDiamondFurnace3Idle;
	public static BlockUpgradedFurnace blockDiamondFurnace3Active;
	
	public static BlockUpgradedFurnace blockMixedMetalFurnace3Idle;
	public static BlockUpgradedFurnace blockMixedMetalFurnace3Active;
	
	public static BlockUpgradedFurnace blockUltimateFurnaceIdle;
	public static BlockUpgradedFurnace blockUltimateFurnaceActive;
	
	public static Block blockFreezerIdle;
	public static Block blockFreezerActive;
	
	public static Block blockUncraftingTable;
	
	//Chests
	public static BlockUpgradedChest blockIronChest;
	
	public static BlockUpgradedChest blockGoldChest;
	
	public static BlockUpgradedChest blockDiamondChest;
	
	public static BlockUpgradedChest blockObsidianChest;
	
	public static BlockUpgradedChest blockMixedMetalChest;
	
	public static BlockUpgradedChest blockEmeraldChest;
	
	public static BlockUpgradedChest blockTinChest;
	
	public static BlockUpgradedChest blockCopperChest;
	
	
	
	private final boolean o = false;
	
	//Items
	public static Item itemPulverizedMilk;
	
	public static Item itemMiningBackpack;
	public static Item itemSmallBackpack;
	public static Item itemMediumBackpack;
	public static Item itemLargeBackpack;
	public static Item itemMeltedEmerald;
	
	
	//Meals
	public static ItemMeal itemMeal1;
	public static ItemMeal itemMeal2;
		
	
	//Food
	public static Item itemCheese;
	public static Item itemLettuce;
	public static Item itemChickenSandwich;
	public static Item itemBreadSlice;
	
	//Frozen Foods
	public static Item itemFrozenRawPorkchop;
	public static Item itemFrozenCookedPorkchop;
	public static Item itemFrozenApple;
	
	//Items
	public static Item itemTabIconMagical;
	
	//public static Block blockAdvancedCraftingTable;
	
	//Magical Blocks
	public static Block blockCondenser;
	public static Block blockEnergyCollector;
	public static Block blockEnergyCollectorMK2;
	public static Block blockEnergyCollectorMK3;
	//public static Block blockEnergyRelay;
	public static Block blockAntimatterWorkbench;
	
	
	public static WSBIMOptions options = new WSBIMOptions();

	
	@Instance(modid)
	public static Whatshouldbeinminecraft instance;
	
	@SidedProxy(clientSide = "com.FinalKill.wsbim.client.proxy.ClientProxy", serverSide = "com.FinalKill.wsbim.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static Whatshouldbeinminecraft main = new Whatshouldbeinminecraft(); 
	
	public void preInit(FMLPreInitializationEvent e){
		PacketDispatcher.registerPackets();
		FurnaceRegistry.prepDelegates();
		ChestRegistry.prepDelegates();
		FrozenFoods.prepDelegates();
		this.themes.initialize();
		try {
			options.createOptions();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		itemTabIconMagical = new Item().setTextureName(modid+":"+"alchemy").setUnlocalizedName("f398h387hd8739ydys8er78y49a90jgrl03");
		GameRegistry.registerItem(itemTabIconMagical, "f398h387hd8739ydys8er78y49a90jgrl03");
		
		this.blockIronFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.IRON, "ironFurnace").setCreativeTab(tabMechanical);
		this.blockIronFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.IRON, "ironFurnace");
	
		this.blockQuartzFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.QUATZ, "quartzFurnace").setCreativeTab(tabMechanical);
		this.blockQuartzFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.QUATZ, "quartzFurnace");
		
		this.blockGoldFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.GOLD, "goldFurnace").setCreativeTab(tabMechanical);
		this.blockGoldFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.GOLD, "goldFurnace");
		
		this.blockDiamondFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.DIAMOND, "diamondFurnace").setCreativeTab(tabMechanical);
		this.blockDiamondFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.DIAMOND, "diamondFurnace");
		
		this.blockObsidianFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.OBSIDIAN, "obsidianFurnace").setCreativeTab(tabMechanical);
		this.blockObsidianFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.OBSIDIAN, "obsidianFurnace");
		
		this.blockMixedMetalFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.MIXEDMETAL, "mixedMetalFurnace").setCreativeTab(tabMechanical);
		this.blockMixedMetalFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.MIXEDMETAL, "mixedMetalFurnace");
		
		this.blockIronFurnace2Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.IRON2, "ironFurnace2").setBlockTextureName("ironFurnace").setCreativeTab(tabMechanical);
		this.blockIronFurnace2Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.IRON2, "ironFurnace2").setBlockTextureName("ironFurnace");
		
		this.blockGoldFurnace2Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.GOLD2, "goldFurnace2").setBlockTextureName("goldFurnace").setCreativeTab(tabMechanical);
		this.blockGoldFurnace2Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.GOLD2, "goldFurnace2").setBlockTextureName("goldFurnace");

		this.blockDiamondFurnace2Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.DIAMOND2, "diamondFurnace2").setBlockTextureName("diamondFurnace").setCreativeTab(tabMechanical);
		this.blockDiamondFurnace2Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.DIAMOND2, "diamondFurnace2").setBlockTextureName("diamondFurnace");
		
		this.blockObsidianFurnace2Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.OBSIDIAN2, "obsidianFurnace2").setBlockTextureName("obsidianFurnace").setCreativeTab(tabMechanical);
		this.blockObsidianFurnace2Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.OBSIDIAN2, "obsidianFurnace2").setBlockTextureName("obsidianFurnace");

		this.blockMixedMetalFurnace2Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.MIXEDMETAL2, "mixedMetalFurnace2").setBlockTextureName("mixedMetalFurnace").setCreativeTab(tabMechanical);
		this.blockMixedMetalFurnace2Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.MIXEDMETAL2, "mixedMetalFurnace2").setBlockTextureName("mixedMetalFurnace");

		this.blockIronFurnace3Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.IRON3, "ironFurnace3").setBlockTextureName("ironFurnace").setCreativeTab(tabMechanical);
		this.blockIronFurnace3Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.IRON3, "ironFurnace3").setBlockTextureName("ironFurnace");

		this.blockGoldFurnace3Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.GOLD3, "goldFurnace3").setBlockTextureName("goldFurnace").setCreativeTab(tabMechanical);
		this.blockGoldFurnace3Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.GOLD3, "goldFurnace3").setBlockTextureName("goldFurnace");
		
		this.blockDiamondFurnace3Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.DIAMOND3, "diamondFurnace3").setBlockTextureName("diamondFurnace").setCreativeTab(tabMechanical);
		this.blockDiamondFurnace3Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.DIAMOND3, "diamondFurnace3").setBlockTextureName("diamondFurnace");

		this.blockMixedMetalFurnace3Idle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.MIXEDMETAL3, "mixedMetalFurnace3").setBlockTextureName("mixedMetalFurnace").setCreativeTab(tabMechanical);
		this.blockMixedMetalFurnace3Active = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.MIXEDMETAL3, "mixedMetalFurnace3").setBlockTextureName("mixedMetalFurnace");

		this.blockUltimateFurnaceIdle = (BlockUpgradedFurnace) new BlockUpgradedFurnace(false, EnumFurnaceType.ULTIMATE, "ultimateFurnace").setCreativeTab(tabMechanical);
		this.blockUltimateFurnaceActive = (BlockUpgradedFurnace) new BlockUpgradedFurnace(true, EnumFurnaceType.ULTIMATE, "ultimateFurnace");

		
		
		
		this.registerUpgradedFurnace(blockIronFurnaceIdle, blockIronFurnaceActive, new TileEntityIronFurnace());
		this.registerUpgradedFurnace(blockMixedMetalFurnaceIdle, blockMixedMetalFurnaceActive, new TileEntityMixedMetalFurnace());
		this.registerUpgradedFurnace(blockObsidianFurnaceIdle, blockObsidianFurnaceActive, new TileEntityObsidianFurnace());
		this.registerUpgradedFurnace(blockDiamondFurnaceIdle, blockDiamondFurnaceActive, new TileEntityDiamondFurnace());
		this.registerUpgradedFurnace(blockGoldFurnaceIdle, blockGoldFurnaceActive, new TileEntityGoldFurnace());
		this.registerUpgradedFurnace(blockQuartzFurnaceIdle, blockQuartzFurnaceActive, new TileEntityQuartzFurnace());
		this.registerUpgradedFurnace(blockIronFurnace2Idle, blockIronFurnace2Active, new TileEntityIronFurnace2());
		this.registerUpgradedFurnace(blockGoldFurnace2Idle, blockGoldFurnace2Active, new TileEntityGoldFurnace2());
		this.registerUpgradedFurnace(blockDiamondFurnace2Idle, blockDiamondFurnace2Active, new TileEntityDiamondFurnace2());
		this.registerUpgradedFurnace(blockObsidianFurnace2Idle, blockObsidianFurnace2Active, new TileEntityObsidianFurnace2());
		this.registerUpgradedFurnace(blockMixedMetalFurnace2Idle, blockMixedMetalFurnace2Active, new TileEntityMixedMetalFurnace2());
		this.registerUpgradedFurnace(blockIronFurnace3Idle, blockIronFurnace3Active, new TileEntityIronFurnace3());
		this.registerUpgradedFurnace(blockGoldFurnace3Idle, blockGoldFurnace3Active, new TileEntityGoldFurnace3());
		this.registerUpgradedFurnace(blockDiamondFurnace3Idle, blockDiamondFurnace3Active, new TileEntityDiamondFurnace3());
		this.registerUpgradedFurnace(blockMixedMetalFurnace3Idle, blockMixedMetalFurnace3Active, new TileEntityMixedMetalFurnace3());
		this.registerUpgradedFurnace(blockUltimateFurnaceIdle, blockUltimateFurnaceActive, new TileEntityUltimateFurnace());
		
		GameRegistry.registerTileEntity(TileEntityIronFurnace.class, "ironFurnace");
		GameRegistry.registerTileEntity(TileEntityGoldFurnace.class, "goldFurnace");
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace.class, "diamondFurnace");
		GameRegistry.registerTileEntity(TileEntityObsidianFurnace.class, "obsidianFurnace");
		GameRegistry.registerTileEntity(TileEntityQuartzFurnace.class, "quartzFurnace");
		GameRegistry.registerTileEntity(TileEntityMixedMetalFurnace.class, "mixedMetalFurnace");
		GameRegistry.registerTileEntity(TileEntityIronFurnace2.class, "ironFurnace2");
		GameRegistry.registerTileEntity(TileEntityGoldFurnace2.class, "goldFurnace2");
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace2.class, "diamondFurnace2");
		GameRegistry.registerTileEntity(TileEntityObsidianFurnace2.class, "obsidianFurnace2");
		GameRegistry.registerTileEntity(TileEntityMixedMetalFurnace2.class, "mixedMetalFurnace2");
		GameRegistry.registerTileEntity(TileEntityIronFurnace3.class, "ironFurnace3");
		GameRegistry.registerTileEntity(TileEntityGoldFurnace3.class, "goldFurnace3");
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace3.class, "diamondFurnace3");
		GameRegistry.registerTileEntity(TileEntityMixedMetalFurnace3.class, "mixedMetalFurnace3");
		GameRegistry.registerTileEntity(TileEntityUltimateFurnace.class, "ultimateFurnace");
		/**
		this.blockAdvancedCraftingTable = new BlockAdvancedWorkbench().setBlockName("advancedWorkbench").setStepSound(Block.soundTypeWood).setResistance(1).setHardness(2.0F).setCreativeTab(main.ourTab);
		GameRegistry.registerBlock(blockAdvancedCraftingTable, "advancedCrafting");
		GameRegistry.registerTileEntity(TileEntityAdvancedCraftingTable.class, "advancedCrafting");
		*/
		this.blockUncraftingTable = new BlockUncraftingTable();
		GameRegistry.registerBlock(blockUncraftingTable, "uncraftingTable");
		
		
		//TODO always go into client proxy and reigster the chest, after the ChestRegistry is called.
		
		this.blockIronChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.IRON, "ironChest");
		ChestRegistry.registerChest(this.blockIronChest, new TileEntityIronChest());
		
		this.blockGoldChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.GOLD, "goldChest");
		ChestRegistry.registerChest(blockGoldChest, new TileEntityGoldChest());
		
		this.blockDiamondChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.DIAMOND, "diamondChest");
		ChestRegistry.registerChest(blockDiamondChest, new TileEntityDiamondChest());
		
		this.blockObsidianChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.OBSIDIAN, "obsidianChest");
		ChestRegistry.registerChest(blockObsidianChest, new TileEntityObsidianChest());
		
		this.blockMixedMetalChest = (BlockUpgradedChest) new BlockUpgradedChest(EnumChestType.MIXEDMETAL, "mixedMetalChest");
		ChestRegistry.registerChest(blockMixedMetalChest, new TileEntityMixedMetalChest());
		
		this.blockEmeraldChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.EMERALD, "emeraldChest");
		ChestRegistry.registerChest(blockEmeraldChest, new TileEntityEmeraldChest());
		
		this.blockCopperChest = (BlockUpgradedChest)new BlockUpgradedChest(EnumChestType.COPPER, "copperChest");
		ChestRegistry.registerChest(blockCopperChest, new TileEntityCopperChest());
		
		this.itemFrozenRawPorkchop = new ItemFrozenFood("frozenRawPorkchop");
		FrozenFoods.registerFrozenFood(0, Items.porkchop, itemFrozenRawPorkchop, true, 20F, 50F);
		
		this.itemFrozenCookedPorkchop = new ItemFrozenFood("frozenCookedPorkchop");
		FrozenFoods.registerFrozenFood(1, Items.cooked_porkchop, itemFrozenCookedPorkchop, true, 20F, 50F);
		
		this.itemFrozenApple = new ItemFrozenFood("frozenApple");
		FrozenFoods.registerFrozenFood(2, Items.apple, itemFrozenApple, true, 20F, 50F);
		FrozenFoods.addFrozenFood(3, Items.water_bucket, Item.getItemFromBlock(Blocks.ice), false, 32F, 40F, false);
		FrozenFoods.addFrozenFood(4, Item.getItemFromBlock(Blocks.ice), Item.getItemFromBlock(Blocks.snow), false, 0F, 100F, false);
		
		this.blockFreezerIdle = new BlockFreezer(false, "freezerIdle").setCreativeTab(this.tabMechanical);
		this.blockFreezerActive = new BlockFreezer(true, "freezerActive");
		this.registerBlockAndTileEntity((BlockContainer) blockFreezerIdle, TileEntityFreezer.class);
		this.registerBlockAndTileEntity((BlockContainer) blockFreezerActive, TileEntityFreezer.class);

		//Other Items
		this.itemPulverizedMilk = new ItemWSBIM("pulverizedMilk");
		this.itemMeltedEmerald = new ItemWSBIM("meltedEmerald");
	
		//Meals
		this.itemMeal1 = new ItemMeal("meal1", 14, 1.4F, ItemMeal.calcMealContents(Items.cooked_beef, Items.baked_potato));
		this.itemMeal2 = (ItemMeal) new ItemMeal("meal2", 16, 1.6F, ItemMeal.calcMealContents(Items.cooked_beef, Items.baked_potato, this.itemPulverizedMilk)).setTextureName(modid+":"+"meal1");
		
		//Other Foods
		this.itemCheese = new ItemModdedFood("cheese", 2, 0.1F);
		this.itemLettuce = new ItemModdedFood("lettuce", 1, 0.1F);
		this.itemBreadSlice = new ItemModdedFood("breadSlice", 1, 0.2F);
		this.itemChickenSandwich = new ItemModdedFood("chickenSandwich", 12, 1.2F);
		
		this.itemMiningBackpack = new ItemBackpack("miningBackpack", EnumBackpackSize.DEFAULT);
		this.itemSmallBackpack = new ItemBackpack("smallBackpack", EnumBackpackSize.SMALL);
		this.itemMediumBackpack = new ItemBackpack("mediumBackpack", EnumBackpackSize.MEDIUM);
		this.itemLargeBackpack = new ItemBackpack("largeBackpack", EnumBackpackSize.LARGE);
		
		//TODO add items to blacklist here.
		UncraftingRecipes.addItemToNonUncraftableItems(WSBIM.main.blockMud);
		UncraftingRecipes.addItemToNonUncraftableItems(WSBIM.main.blockWetSand);
		UncraftingRecipes.addItemToNonUncraftableItems(Blocks.sponge);
		UncraftingRecipes.addItemToNonUncraftableItems(itemPulverizedMilk);
		
		if(Loader.isModLoaded("condenser_values_api")){
		//TODO add items to blacklist here if they somehow add a condenser value exploit.
		UncraftingRecipes.addItemToNonUncraftableItems(Items.dye);
		UncraftingRecipes.addItemToNonUncraftableItems(Items.golden_apple);
		}
		
		
		FMLCommonHandler.instance().bus().register(new TickPlayerEvent());
		
		if(Loader.isModLoaded("condenser_values_api")){
			//TODO com.FinalKill.condenservalues.CondenserValuesAPI.instance().addCondenserValueZipFile(new com.FinalKill.condenservalues.FileZip("https://www.dropbox.com/s/5qeizpx0fi9av6f/wsbim_condenser_values.zip?dl=1", "wsbim_condenser_values",version));
			this.blockCondenser = new BlockCondenser();
			GameRegistry.registerBlock(blockCondenser, "blockCondenser");
			GameRegistry.registerTileEntity(com.FinalKill.wsbim.common.tileentity.TileEntityCondenser.class, "wsbim:blockCondenser");
			
			this.blockEnergyCollector = new BlockEnergyCollector("blockEnergyCollector", EnumEnergyCollector.MK1, new TileEntityEnergyCollector()).setCreativeTab(tabMagical);
			GameRegistry.registerBlock(blockEnergyCollector, "blockEnergyCollector");
			GameRegistry.registerTileEntity(com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollector.class, "energyCollector");
			
			this.blockEnergyCollectorMK2 = new BlockEnergyCollector("blockEnergyCollectorMK2", EnumEnergyCollector.MK2, new TileEntityEnergyCollectorMK2()).setCreativeTab(tabMagical);
			GameRegistry.registerBlock(blockEnergyCollectorMK2, "blockEnergyCollectorMK2");
			GameRegistry.registerTileEntity(com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollectorMK2.class, "energyCollectorMK2");

			this.blockEnergyCollectorMK3 = new BlockEnergyCollector("blockEnergyCollectorMK3", EnumEnergyCollector.MK3, new TileEntityEnergyCollectorMK3()).setCreativeTab(tabMagical);
			GameRegistry.registerBlock(blockEnergyCollectorMK3, "blockEnergyCollectorMK3");
			GameRegistry.registerTileEntity(com.FinalKill.wsbim.common.tileentity.TileEntityEnergyCollectorMK3.class, "energyCollectorMK3");
			
			this.blockAntimatterWorkbench = new BlockAntimatterWorkbench().setCreativeTab(tabMagical);
			this.registerBlockAndTileEntity((BlockContainer) this.blockAntimatterWorkbench, TileEntityAntimatterWorkbench.class);
		}
		
		
		
		
	}

	/**Registers both the block and the tileentity in the same method. Yay!*/
	public static void registerBlockAndTileEntity(BlockContainer b, Class<?extends TileEntity> c){
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
		GameRegistry.registerTileEntity(c, b.getUnlocalizedName());
			}
	public void init(FMLInitializationEvent e){
		proxy.registerProxies();
		NetworkRegistry.INSTANCE.registerGuiHandler(modid, new WSBIMGuiHandler());
		MinecraftForge.EVENT_BUS.register(new ItemTooltipFullName());
		
		FMLCommonHandler.instance().bus().register(new NBTCopyItemEvent());
		//TODO
		
	}
	
	 public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
	     	int id = EntityRegistry.findGlobalUniqueEntityId();

	     	EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
	     	EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
	    	}

	     public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
	     	if (spawnProb > 0) {
	     		EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
	     	}
	     }
	     
	public void postInit(FMLPostInitializationEvent e){
		registerCrafting();
		
	
		
		if(Loader.isModLoaded("condenser_values_api")){
			
			com.FinalKill.condenservalues.CondenserItemValues list = com.FinalKill.condenservalues.CondenserValuesAPI.instance();
			list.registerItemValue(600, 13312, WSBIM.main.itemMixedMetalIngot);
			list.registerItemValue(601, 128, main.itemIronStick);
			list.registerItemValue(602, 512, main.itemGoldenStick);
			list.registerBlockValue(603, 13312 * 9, main.blockMixedMetalBlock);
			list.registerItemValue(604, 4, main.itemWoodenPlate);
			list.registerItemValue(605, 32, main.itemObsidianPlate);
			list.registerItemValue(606, 128, main.itemIronPlate);
			list.registerItemValue(607, 8192 * 2, main.itemDiamondPlate);
			list.registerBlockValue(608, (256 * 8) + 8, this.blockIronFurnaceIdle);
			list.registerBlockValue(609, list.getItemValueFromBlock(blockIronFurnaceIdle) + (2048 * 8), this.blockGoldFurnaceIdle);
			list.registerBlockValue(610, (1 * 6) + (8192*2) + list.getItemValueFromBlock(blockGoldFurnaceIdle), this.blockDiamondFurnaceIdle);
			list.registerBlockValue(611, list.getItemValueFromBlock(this.blockIronFurnaceIdle) + (64 * 8), this.blockObsidianFurnaceIdle);
			list.registerBlockValue(612, list.getItemValueFromBlock(this.blockObsidianFurnaceIdle) + (list.getItemValueFromItem(Items.quartz) * 8), this.blockQuartzFurnaceIdle);
			list.registerBlockValue(613, (list.getItemValueFromItem(this.main.itemMixedMetalIngot) * 6) + (list.getItemValueFromBlock(blockObsidianFurnaceIdle) * 2) + list.getItemValueFromBlock(blockDiamondFurnaceIdle), this.blockMixedMetalFurnaceIdle);
			list.registerBlockValue(614, (256 * 6) + (list.getItemValueFromBlock(Blocks.iron_block) * 2) + list.getItemValueFromBlock(this.blockIronFurnaceIdle), blockIronFurnace2Idle);
			list.registerBlockValue(615, (2048 * 6) + (list.getItemValueFromBlock(Blocks.gold_block) * 2) + list.getItemValueFromBlock(blockGoldFurnaceIdle), blockGoldFurnace2Idle);
			list.registerBlockValue(616, 4 + (8192 * 3) + (list.getItemValueFromBlock(blockDiamondFurnaceIdle) * 2), this.blockDiamondFurnace2Idle);
			list.registerBlockValue(617, (64 * 8) + list.getItemValueFromBlock(blockObsidianFurnaceIdle), blockObsidianFurnace2Idle);
			list.registerItemValue(618, list.getItemValueFromItem(main.itemMixedMetalIngot), main.itemMixedMetalContents);
			list.registerBlockValue(619, 1, main.blockMud);
			list.registerBlockValue(620, 1, main.blockWetSand);
			list.registerBlockValue(621, (list.getItemValueFromItem(main.itemMixedMetalIngot) * 6) + (list.getItemValueFromBlock(blockObsidianFurnace2Idle) * 2) + list.getItemValueFromBlock(blockMixedMetalFurnaceIdle), this.blockMixedMetalFurnace2Idle);
			list.registerBlockValue(622, (list.getItemValueFromBlock(Blocks.iron_block) * 7) + (list.getItemValueFromBlock(this.blockIronFurnace2Idle) * 2), blockIronFurnace3Idle);
			list.registerBlockValue(623, (2048 * 6) + (list.getItemValueFromBlock(Blocks.gold_block) * 2) + list.getItemValueFromBlock(blockGoldFurnace2Idle), blockGoldFurnace3Idle);
			list.registerBlockValue(624, (8192 * 4) + (8192 * 3) + (list.getItemValueFromBlock(blockDiamondFurnace2Idle) * 2), this.blockDiamondFurnace3Idle);
			list.registerBlockValue(625, (list.getItemValueFromItem(main.itemMixedMetalIngot) * 6) + (list.getItemValueFromBlock(blockObsidianFurnace2Idle) * 2) + list.getItemValueFromBlock(blockMixedMetalFurnace2Idle), this.blockMixedMetalFurnace3Idle);
			list.registerBlockValue(626, (list.getItemValueFromItem(main.itemMixedMetalIngot) * 4) + (list.getItemValueFromBlock(blockDiamondFurnace3Idle)) + list.getItemValueFromBlock(blockMixedMetalFurnace3Idle) + list.getItemValueFromBlock(blockGoldFurnace3Idle) + list.getItemValueFromBlock(blockIronFurnace3Idle) + list.getItemValueFromBlock(blockObsidianFurnace2Idle), this.blockUltimateFurnaceIdle);
			//list.registerBlockValue(627, (list.getItemValueFromItem(Items.stick) * 6) + list.getItemValueFromBlock(Blocks.chest) + list.getItemValueFromBlock(Blocks.crafting_table), this.blockAdvancedCraftingTable);
			list.registerBlockValue(628, (256 * 8) + 64, this.blockIronChest);
			list.registerBlockValue(629, (2048 * 8) + list.getItemValueFromBlock(this.blockIronChest), this.blockGoldChest);
			list.registerBlockValue(630, (1 * 6) + list.getItemValueFromBlock(this.blockGoldChest) + (8192 * 2), this.blockDiamondChest);
			list.registerBlockValue(631, (64 * 8) + list.getItemValueFromBlock(blockIronChest), blockObsidianChest);
			list.registerBlockValue(632, (list.getItemValueFromItem(WSBIM.main.itemMixedMetalIngot) * 4) + (8192 * 4) + list.getItemValueFromBlock(blockDiamondChest), blockMixedMetalChest);
			list.registerBlockValue(633, ((8192*2) * 8) + list.getItemValueFromBlock(blockIronChest), this.blockEmeraldChest);
			list.registerBlockValue(634, 3888, blockUncraftingTable);
			list.registerBlockValue(635, 6336, blockFreezerIdle);
			list.registerItemValue(636, (list.getItemValueFromBlock(Blocks.glass) * 6) + (list.getItemValueFromItem(Items.iron_ingot) * 2) + list.getItemValueFromItem(Items.bucket), MachineryCraft.items.rainCollector);
		}
	}
	
	public void registerCrafting(){
		
		GameRegistry.addRecipe(new ItemStack(blockIronFurnaceIdle), "mmm", "mom","mmm",'m', new ItemStack(Items.iron_ingot), 'o', new ItemStack(Blocks.furnace));
		GameRegistry.addRecipe(new ItemStack(blockGoldFurnaceIdle), "mmm", "mom","mmm",'m', new ItemStack(Items.gold_ingot), 'o', new ItemStack(this.blockIronFurnaceIdle));
		GameRegistry.addRecipe(new ItemStack(blockDiamondFurnaceIdle), "mmm", "dod","mmm",'m', new ItemStack(Blocks.glass), 'o', new ItemStack(this.blockGoldFurnaceIdle), 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(blockObsidianFurnaceIdle), "mmm", "mom","mmm",'m', new ItemStack(Blocks.obsidian), 'o', new ItemStack(this.blockIronFurnaceIdle));
		GameRegistry.addRecipe(new ItemStack(blockQuartzFurnaceIdle), "mmm", "mom","mmm",'m', new ItemStack(Items.quartz), 'o', new ItemStack(this.blockObsidianFurnaceIdle));
		GameRegistry.addRecipe(new ItemStack(blockMixedMetalFurnaceIdle), "mmm", "dod","mmm",'m', new ItemStack(main.itemMixedMetalIngot), 'o', new ItemStack(this.blockDiamondFurnaceIdle), 'd', new ItemStack(this.blockObsidianFurnaceIdle));
		GameRegistry.addRecipe(new ItemStack(blockIronFurnace2Idle), "mmm", "dod","mmm",'m', new ItemStack(Items.iron_ingot), 'o', new ItemStack(this.blockIronFurnaceIdle), 'd', new ItemStack(Blocks.iron_block));
		GameRegistry.addRecipe(new ItemStack(blockGoldFurnace2Idle), "mmm", "dod","mmm",'m', new ItemStack(Items.gold_ingot), 'o', new ItemStack(this.blockGoldFurnaceIdle), 'd', new ItemStack(Blocks.gold_block));
		GameRegistry.addRecipe(new ItemStack(blockDiamondFurnace2Idle), "gmg", "dod","gmg",'m', new ItemStack(Items.diamond), 'o', new ItemStack(Items.diamond), 'd', new ItemStack(this.blockDiamondFurnaceIdle), 'g', new ItemStack(Blocks.glass));
		GameRegistry.addRecipe(new ItemStack(blockObsidianFurnace2Idle), "mmm", "mom","mmm",'m', new ItemStack(Blocks.obsidian), 'o', new ItemStack(this.blockObsidianFurnaceIdle));
		GameRegistry.addRecipe(new ItemStack(blockMixedMetalFurnace2Idle), "mmm", "dod","mmm",'m', new ItemStack(main.itemMixedMetalIngot), 'o', new ItemStack(this.blockMixedMetalFurnaceIdle), 'd', new ItemStack(this.blockObsidianFurnace2Idle));
		GameRegistry.addRecipe(new ItemStack(blockIronFurnace3Idle), "ddd", "odo","ddd",'m', new ItemStack(Items.iron_ingot), 'o', new ItemStack(this.blockIronFurnace2Idle), 'd', new ItemStack(Blocks.iron_block));
		GameRegistry.addRecipe(new ItemStack(blockGoldFurnace3Idle), "mmm", "dod","mmm",'m', new ItemStack(Items.gold_ingot), 'o', new ItemStack(this.blockGoldFurnace2Idle), 'd', new ItemStack(Blocks.gold_block));
		GameRegistry.addRecipe(new ItemStack(blockDiamondFurnace3Idle), "gmg", "dod","gmg",'m', new ItemStack(Items.diamond), 'o', new ItemStack(Items.diamond), 'd', new ItemStack(this.blockDiamondFurnace2Idle), 'g', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(blockMixedMetalFurnace3Idle), "mmm", "dod","mmm",'m', new ItemStack(main.itemMixedMetalIngot), 'o', new ItemStack(this.blockMixedMetalFurnace2Idle), 'd', new ItemStack(this.blockObsidianFurnace2Idle));
		GameRegistry.addRecipe(new ItemStack(blockUltimateFurnaceIdle), "cic", "gmd","coc",'c',new ItemStack(main.itemMixedMetalIngot),'i',new ItemStack(this.blockIronFurnace3Idle),'m', new ItemStack(this.blockMixedMetalFurnace3Idle), 'd', new ItemStack(this.blockDiamondFurnace3Idle), 'o', new ItemStack(this.blockObsidianFurnace2Idle), 'g', new ItemStack(this.blockGoldFurnace3Idle));
		//GameRegistry.addRecipe(new ItemStack(this.blockAdvancedCraftingTable), "sts","scs","s s",'s',new ItemStack(Items.stick), 't', new ItemStack(Blocks.crafting_table), 'c', new ItemStack(Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(this.blockIronChest), "mmm", "mom","mmm",'m', new ItemStack(Items.iron_ingot), 'o', new ItemStack(Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(this.blockGoldChest), "mmm", "mom","mmm",'m', new ItemStack(Items.gold_ingot), 'o', new ItemStack(this.blockIronChest));
		GameRegistry.addRecipe(new ItemStack(blockDiamondChest), "mmm", "dod","mmm",'m', new ItemStack(Blocks.glass), 'o', new ItemStack(this.blockGoldChest), 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(this.blockObsidianChest), "mmm", "mom","mmm",'m', new ItemStack(Blocks.obsidian), 'o', new ItemStack(this.blockIronChest));
		GameRegistry.addRecipe(new ItemStack(this.blockMixedMetalChest), "mdm", "dod","mdm",'m', new ItemStack(WSBIM.main.itemMixedMetalIngot), 'o', new ItemStack(this.blockDiamondChest), 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(this.blockEmeraldChest), "mmm", "mom","mmm",'m', new ItemStack(Items.emerald), 'o', new ItemStack(this.blockIronChest));
		GameRegistry.addRecipe(new ItemStack(this.blockUncraftingTable), "ptp", "tct", "ptp",'p',new ItemStack(main.itemWoodenPlate), 't', new ItemStack(Blocks.tnt), 'c', new ItemStack(Blocks.crafting_table));
		GameRegistry.addRecipe(new ItemStack(this.blockFreezerIdle), "iri","bcb","ifi",'i',new ItemStack(Items.iron_ingot),'r',new ItemStack(Items.redstone),'f',new ItemStack(Blocks.redstone_block),'b',new ItemStack(Blocks.iron_block),'c', new ItemStack(Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(this.itemChickenSandwich), " b ","lmc"," b ",'l', new ItemStack(this.itemLettuce), 'm', new ItemStack(Items.cooked_chicken),'b',new ItemStack(this.itemBreadSlice),'c',new ItemStack(this.itemCheese));
		GameRegistry.addRecipe(new ItemStack(this.itemChickenSandwich), " b ","cml"," b ",'l', new ItemStack(this.itemLettuce), 'm', new ItemStack(Items.cooked_chicken),'b',new ItemStack(this.itemBreadSlice),'c',new ItemStack(this.itemCheese));
	
		GameRegistry.addRecipe(new ItemStack(this.itemSmallBackpack), "lll","lcl","lll",'l',new ItemStack(Items.leather), 'c', new ItemStack(Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(this.itemMediumBackpack), "lll","lcl","lsl",'l',new ItemStack(Items.leather), 'c', new ItemStack(Blocks.chest), 's', new ItemStack(this.itemSmallBackpack));
		GameRegistry.addRecipe(new ItemStack(this.itemLargeBackpack), "lll","lcl","lsl",'l',new ItemStack(Items.leather), 'c', new ItemStack(Blocks.chest), 's', new ItemStack(this.itemMediumBackpack));
		GameRegistry.addRecipe(new ItemStack(this.itemMiningBackpack), "lpl","lcl","lsl",'l',new ItemStack(Items.leather), 'c', new ItemStack(Blocks.chest), 's', new ItemStack(this.itemSmallBackpack), 'p', new ItemStack(Items.stone_pickaxe));
		
		//Rain Collector Recipe from Machinery Craft
		GameRegistry.addRecipe(new ItemStack(MachineryCraft.items.rainCollector), "gig", "gig", "gbg", 'g', new ItemStack(Blocks.glass), 'i', new ItemStack(Items.iron_ingot), 'b', new ItemStack(Items.bucket));
		
		//Shapless
		GameRegistry.addShapelessRecipe(new ItemStack(this.itemPulverizedMilk), Items.milk_bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(this.itemLettuce, 3), new ItemStack(Blocks.leaves));
		GameRegistry.addShapelessRecipe(new ItemStack(this.itemBreadSlice, 4), new ItemStack(Items.bread));

		//Smelting
		GameRegistry.addSmelting(this.itemPulverizedMilk, new ItemStack(this.itemCheese), 0.1F);
		
		ItemStack meltedEmerald = new ItemStack(this.itemMeltedEmerald);
		
		
		GameRegistry.addSmelting(Items.emerald, meltedEmerald, 2.0F);
		
		//TODO Antimatter Workbench Recipes
		//this.registerAntimatterWorkbenchRecipe(new ItemStack(Blocks.cobblestone), "ikic", "cccc", "kkkk", 'i', new ItemStack(Blocks.dirt), 'k', new ItemStack(Blocks.stone), 'c', new ItemStack(Blocks.sand));
		
		this.registerAntimatterWorkbenchFuel(100, Items.redstone);
		this.registerAntimatterWorkbenchFuel(250, Items.glowstone_dust);
		
		if(Loader.isModLoaded("IC2")){
			ItemStack copper = new ItemStack((Item)Item.itemRegistry.getObject("IC2:itemIngot"), 1, 0);
			GameRegistry.addRecipe(new ItemStack(this.blockCondenser), "ccc", "cvc", "ccc", 'c', copper, 'v', new ItemStack(Blocks.chest));
		}
	
		if(Loader.isModLoaded("TConstruct")){
			ItemStack copper = new ItemStack((Item)Item.itemRegistry.getObject("TConstruct:materials"), 1, 9);
			GameRegistry.addRecipe(new ItemStack(this.blockCondenser), "ccc", "cvc", "ccc", 'c', copper, 'v', new ItemStack(Blocks.chest));
		}
	}
	
	
	public void registerUpgradedFurnace(BlockUpgradedFurnace idle, BlockUpgradedFurnace active, TileEntity t){
		GameRegistry.registerBlock(idle, idle.getUnlocalizedName());
	GameRegistry.registerBlock(active, active.getUnlocalizedName());
	FurnaceRegistry.registerFurnace(idle.getFurnaceType().getID(), idle, active, t);
	//GameRegistry.addRecipe(new ItemStack(idle), "mmm", "mom","mmm",'m', new ItemStack(idle.getFurnaceType().getCraftingItem()), 'o', new ItemStack(FurnaceRegistry.getInactiveBlockForEnum(idle.getFurnaceType().getProdesessor()).getItem()));

	//GameRegistry.registerTileEntity(idle.createTileEntities(idle).getClass(), idle.getUnlocalizedName());
	
		
	}
	
	
	public void registerAntimatterWorkbenchRecipe(ItemStack outputStack, Object... recipe){
		AntimatterWorkbenchCrafting.registerCraftingRecipe(outputStack, recipe);
	}
	
	public void registerAntimatterWorkbenchFuel(int val, Item stack){
		AntimatterWorkbenchCrafting.registerFuelItemStack(val, stack);
	}
	
	public boolean hasCopperBlock(){
		RegistryNamespaced reg = Block.blockRegistry;
		Object[] arr = reg.getKeys().toArray();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] instanceof Block){
				Block b = (Block)arr[i];
				String str = b.getUnlocalizedName();
				CharSequence char1 = "copper";
				CharSequence char2 = "ore";
				CharSequence char3 = "copperBlock";
				boolean flag = str.contains(char1);
				boolean flag2 = str.contains(char2);
				boolean flag3 = str.contains(char3);
				if((flag || flag3)){
					return true;
				}
			}
		}
		
		return false;
	}
	
}