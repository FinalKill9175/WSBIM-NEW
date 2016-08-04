package com.FinalKill.Machinery_Craft;

import java.awt.Color;

import javax.swing.Timer;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;

import com.FinalKill.MachineryCraftAPI.MachineryCraftAPI;
import com.FinalKill.MachineryCraftAPI.OreDustRegistry;
import com.FinalKill.Machinery_Craft.blocks.MachineryCraftBlocks;
import com.FinalKill.Machinery_Craft.items.MachineryCraftItems;
import com.FinalKill.Machinery_Craft.proxy.CommonProxy;
import com.FinalKill.Machinery_Craft.tabs.MachineryCraftTabs;
import com.FinalKill.Machinery_Craft.world.gen.WorldGeneratorMachineryCraftOre;
import com.FinalKill.Machinery_Craft.world.gen.WorldGeneratorRandomBonusChest;
import com.FinalKill.condenservalues.CondenserItemValues;
import com.FinalKill.condenservalues.CondenserValuesAPI;
import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Main class for the machinery craft mod. 
 * @author FinalKill
 */
@Mod(modid=MachineryCraft.MODID, version = MachineryCraft.version, name = "WSBIM Machinery ")
public class MachineryCraft {
	public static final String MODID = "wsbim_machinery";
	public static final String version = WSBIM.version;
	
	private static Timer t;
	public static boolean isOtherModLoaded;
	public static boolean isAPILoaded;
	Color c =new Color(gray);
	/**
	 * Used for guis. a refrence to the color gray in 0xRRGGBB
	 */
	public static final int gray = 4210752;
	public static final int trashCanGUI = 5;
	public static final int cvFurnaceGUI = 2;
	public static final int guiCVCompactor = 69;
	public static final int guiMacerator = 100;
	public static final int guiGenerator = 101;
	public static final int guiRainCollector = 102;
	public static final int guiElectricFurnace = 103;
	public static final int guiIronFurnace = 104;
	
	//Achievements
	public static Achievement copper;
	public static Achievement tin;
	public static Achievement battery;
	
	
	/**
	 * All of the blocks for the machinery craft mod. Note: Don't do anything with this class BEFORE THE INIT METHOD!
	 */
	public static final MachineryCraftBlocks blocks = new MachineryCraftBlocks();
	/**
	 * All of the creative tabs for the machinery craft mod. Note: Don't do anything with this class BEFORE THE INIT METHOD!
	 */
	public static final MachineryCraftTabs tabs = new MachineryCraftTabs();
	/**
	 * All of the items for the machinery craft mod. Note: Don't do anything with this class BEFORE THE INIT METHOD!
	 */
	public static final MachineryCraftItems items = new MachineryCraftItems();
	
	//DDDDEB
	/**
	 * Material used for any type of Machinery.
	 */
	public static final Material machinery = new Material(MapColor.quartzColor);
	

	/**
	 * Material used for any regular old-joe cable.
	 */
	public static final Material cabels = new Material(MapColor.blackColor);
	
	/**
	 * DO NOT USE!
	 */
	public static BiomeGenBase chestBiome;

	
	//public static ElectricRegistry registry = CondenserValuesAPI.getElectricRegistry();
	@Instance(MODID)
	public static MachineryCraft instance;
	
	@SidedProxy(clientSide="com.FinalKill.Machinery_Craft.proxy.ClientProxy", serverSide = "com.FinalKill.Machinery_Craft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	  
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		
		items.iron_ore_dust = new Item()/**.setCreativeTab(tabs.materialsTab)*/.setUnlocalizedName("ironDust").setTextureName("machinery_craft:ironDust");
		GameRegistry.registerItem(items.iron_ore_dust, "ironDust");
		items.gold_ore_dust = new Item().setUnlocalizedName("goldDust")/**.setCreativeTab(tabs.materialsTab)*/.setTextureName("machinery_craft:goldDust");
		GameRegistry.registerItem(items.gold_ore_dust, "goldDust");
		items.coal_dust = new Item().setUnlocalizedName("coalDust")/**.setCreativeTab(tabs.materialsTab)*/.setTextureName("machinery_craft:coalDust");
		GameRegistry.registerItem(items.coal_dust, "coalDust");
    	items.industrial_iron_dust = new Item()/**.setCreativeTab(tabs.materialsTab)*/.setUnlocalizedName("industrialIronDust").setTextureName("machinery_craft:industrialIronDust");
		GameRegistry.registerItem(items.industrial_iron_dust, "industrialIronDust");
    
		
		blocks.registerBlocks();
    	items.registerItems();
 	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new FinalKillGuiHandler());
		proxy.registerProxies();
		MachineryCraftCrafting.registerCrafting();
		isOtherModLoaded = Loader.isModLoaded("condenser_values_api");
		isAPILoaded = Loader.isModLoaded("machinery_craft_api");
		
		if(!isAPILoaded){
			Minecraft.getMinecraft().crashed(new CrashReport("MachineryCraftAPI was not found! It is required for the mod!",new Throwable("MachineryCraftAPI was not found! It is required for the mod!")));
			
		}
		//Use Biome Manager to register a biome, and Dimension Manager for world providers!
	//	 this.chestBiome= new BiomeGenChestBiome();
		
		

		
		
		this.copper = new Achievement("achievement.copper", "copper", 0, 0, blocks.copper_ore, (Achievement)null).registerStat();
		this.tin = new Achievement("achievement.tin", "tin", 0,1,blocks.tin_ore, (Achievement)copper).registerStat();
		this.battery = new Achievement("achievement.battery", "battery", 2,0, items.battery,copper).registerStat();
		AchievementPage.registerAchievementPage(new AchievementPage("Machinery Craft", new Achievement[]{this.copper, this.tin, this.battery}));
		
		
		GameRegistry.registerWorldGenerator(new WorldGeneratorMachineryCraftOre(blocks.tin_ore, 14, 16, 40, 16, 3, true, 5,0, false), 1);
		GameRegistry.registerWorldGenerator(new WorldGeneratorMachineryCraftOre(blocks.copper_ore, 20, 16, 55, 16, 5, true, 5, 0, false), 2);
		GameRegistry.registerWorldGenerator(new WorldGeneratorRandomBonusChest(), 0);
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.water_bucket), new ItemStack(Items.bucket), new ItemStack(Blocks.water));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		/**
		Blocks.chest.setCreativeTab(chestsTab);
		Blocks.ender_chest.setCreativeTab(chestsTab);
		Blocks.trapped_chest.setCreativeTab(chestsTab);
		
		
*/
		if(isOtherModLoaded){
			CondenserItemValues values_instance = CondenserValuesAPI.instance();
			
			int stick_value = values_instance.getItemValueFromItem(Items.stick);
			int log_value = values_instance.getItemValueFromBlock(Blocks.log);
			int diamond_block_value = values_instance.getItemValueFromBlock(Blocks.diamond_block);
			int redstone_value = values_instance.getItemValueFromItem(Items.redstone);
			
		//Condenser Values
		CondenserValuesAPI.instance().registerBlockValue(400, (CondenserValuesAPI.instance().getItemValueFromItem(Items.iron_ingot)*8)+CondenserValuesAPI.instance().getItemValueFromBlock(Blocks.chest), blocks.ironChest);
		values_instance.registerBlockValue(401, (values_instance.getItemValueFromItem(Items.gold_ingot)*8)+values_instance.getItemValueFromBlock(blocks.ironChest), blocks.goldChest);
		values_instance.registerBlockValue(402, (values_instance.getItemValueFromItem(Items.diamond)*8)+values_instance.getItemValueFromBlock(blocks.goldChest), blocks.diamondChest);
		CondenserValuesAPI.instance().registerBlockValue(403, (values_instance.getItemValueFromItem(Items.iron_ingot)*5), blocks.trashCan);
		values_instance.registerBlockValue(404, (log_value*3)+(stick_value*4), blocks.woodenTable);
		values_instance.registerItemValue(405, values_instance.getItemValueFromItem(Items.iron_ingot), this.items.industrialIron);
	//	values_instance.registerBlockValue(406, (log_value*3)+(stick_value*2)+(values_instance.getItemValueFromItem(items.industrialIron)*2)+(values_instance.getItemValueFromBlock(Blocks.redstone_block)*1), blocks.chargingStation);
		int industrial_iron_value = values_instance.getItemValueFromItem(this.items.industrialIron);
				values_instance.registerBlockValue(408, values_instance.getItemValueFromItem(items.industrialIron)*9, blocks.industrialIronBlock);
		//values_instance.registerBlockValue(409, (256*4)+values_instance.getItemValueFromBlock(Blocks.chest), blocks.chestDropper);
		values_instance.registerItemValue(410, 128, items.coal_dust);
		values_instance.registerItemValue(411, 256, items.iron_ore_dust);
		values_instance.registerItemValue(412, 2048, items.gold_ore_dust);
		values_instance.registerItemValue(413, 256, items.industrial_iron_dust);
		values_instance.registerItemValue(414, 85, items.copper_ingot);
		values_instance.registerItemValue(415, 256, items.tin_ingot);
		values_instance.registerItemValue(416, values_instance.getItemValueFromItem(items.copper_ingot), items.copper_dust);
		values_instance.registerItemValue(417, values_instance.getItemValueFromItem(items.tin_ingot), items.tin_dust);
		
		
		for(int i = 0; i<blocks.coloredStone.length; i++){
			
			values_instance.registerBlockValue(418+i, values_instance.getItemValueFromItem(Items.dye)+values_instance.getItemValueFromBlock(Blocks.stone), blocks.coloredStone[i]);
			
		}
for(int i = 0; i<blocks.coloredWood.length; i++){
			
			values_instance.registerBlockValue(434+i, values_instance.getItemValueFromItem(Items.dye)+values_instance.getItemValueFromBlock(Blocks.planks), blocks.coloredWood[i]);
			
		}
//Start next at 450
		
		//Electric Registry 
		
		//registry.registerElectricityForItem(0, 100, battery);
		

		

		}
		
		if(isAPILoaded){
				/**
			OreDustRegistry oreDusts = MachineryCraftAPI.getOreDustRegistry();
			oreDusts.registerOreDust(1, new ItemStack(items.iron_ore_dust, 2), new ItemStack(Blocks.iron_ore));
			oreDusts.registerOreDust(2, new ItemStack(items.gold_ore_dust, 2), new ItemStack(Blocks.gold_ore));
			oreDusts.registerOreDust(3, new ItemStack(items.coal_dust, 2), new ItemStack(Items.coal));
			oreDusts.registerOreDust(4, new ItemStack(items.industrial_iron_dust, 1), new ItemStack(items.industrialIron));
			oreDusts.registerOreDust(5, new ItemStack(items.iron_ore_dust, 1), new ItemStack(Items.iron_ingot));
			oreDusts.registerOreDust(6, new ItemStack(items.gold_ore_dust, 1), new ItemStack(Items.gold_ingot));
			oreDusts.registerOreDust(7, new ItemStack(Blocks.gravel, 4), new ItemStack(Blocks.cobblestone));
			oreDusts.registerOreDust(8, new ItemStack(Blocks.sand, 2), new ItemStack(Blocks.gravel));
			oreDusts.registerOreDust(9, new ItemStack(items.copper_dust), new ItemStack(items.copper_ingot));
			oreDusts.registerOreDust(10, new ItemStack(items.tin_dust), new ItemStack(items.tin_ingot));
			oreDusts.registerOreDust(11, new ItemStack(items.copper_dust, 2), new ItemStack(blocks.copper_ore));
			oreDusts.registerOreDust(12, new ItemStack(items.tin_dust, 2), new ItemStack(blocks.tin_ore));
			System.out.println(oreDusts.getDustFromOre(new ItemStack(Blocks.iron_ore)));
			System.out.println(oreDusts.getDustFromOre(new ItemStack(Blocks.gold_ore)));
			System.out.println(oreDusts.getDustFromOre(new ItemStack(Items.coal)));
			System.out.println(oreDusts.getOreFromDust(items.iron_ore_dust));
			System.out.println(oreDusts.getOreFromDust(items.gold_ore_dust));
			System.out.println(oreDusts.getOreFromDust(items.coal_dust));
			*/
		}

		//GameRegistry.addShapelessRecipe(new ItemStack(Blocks.coal_ore), new ItemStack(Blocks.cobblestone), new ItemStack(Items.coal));
		//GameRegistry.addShapelessRecipe(new ItemStack(Blocks.coal_ore), new ItemStack(Blocks.stone), new ItemStack(Items.coal));
		//GameRegistry.addSmelting(items.iron_ore_dust, new ItemStack(Items.iron_ingot), 1.0F);
		//GameRegistry.addSmelting(items.gold_ore_dust, new ItemStack(Items.gold_ingot), 1.0F);
		//GameRegistry.addSmelting(items.coal_dust, new ItemStack(Items.coal), 1.0F);
		//GameRegistry.addSmelting(items.industrial_iron_dust, new ItemStack(items.industrialIron), 1.0F);
		//GameRegistry.addSmelting(items.copper_dust, new ItemStack(items.copper_ingot), 1.0F);
		//GameRegistry.addSmelting(items.tin_dust, new ItemStack(items.tin_ingot), 1.0F);
		
	
	}
	  
/**
	 * @wbp.parser.entryPoint
	 */


    }
	

