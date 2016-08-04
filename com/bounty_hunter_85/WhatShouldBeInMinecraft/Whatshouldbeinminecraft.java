package com.bounty_hunter_85.WhatShouldBeInMinecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.FinalKill.wsbim.WSBIM;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockColoredLeather;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockLeather;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockMixedMetal;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockMixedMetalOre;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockMud;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks.BlockSandModded;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemCobblestoneArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemEmeraldArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemMixedMetalArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemModdedAxe;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemModdedHoe;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemModdedPickaxe;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemModdedShovel;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemModdedSword;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemObsidianArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemStoneArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.items.ItemWoodenArmor;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen.WorldGeneratorMixedMetalOre;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen.WorldGeneratorMudPit;
import com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen.WorldGeneratorWetSand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Whatshouldbeinminecraft.modid, version = Whatshouldbeinminecraft.version, name = WSBIM.name)
public class Whatshouldbeinminecraft {
	
	public static final String modid = WSBIM.modid;
	public static final String version = WSBIM.version;
	
	private static final WSBIM otherContainer = new WSBIM();
	public static final Whatshouldbeinminecraft instance = otherContainer.instance;
	
	public static Item itemIronStick;
	public static Item itemObsidianPickaxe;
	public static Item itemObsidianShovel;
	public static Item itemObsidianHoe;
	public static Item itemObsidianAxe;
	public static Item itemObsidianSword;
	public static Block blockLeatherBlock;
	public static Item itemStonePlate;
	public static Item itemCobblestonePlate;
	public static Item itemDiamondPlate;
	public static Item itemWoodenPlate;
	public static Item itemMixedMetalContents;
	public static Item itemMixedMetalIngot;
	public static Item itemObsidianPlate;
	public static Item itemGoldenStick;
	public static Block blockMixedMetalBlock;
	public static Block blockMixedMetalOre;
	public static Block blockWetSand;
	public static Item itemPlate;
	public static Block blockMud;
	public static Item itemEmeraldSword;
	public static Item itemEmeraldPickaxe;
	public static Item itemEmeraldShovel;
	public static Item itemEmeraldAxe;
	public static Item itemEmeraldHoe;
	public static Item itemIronPlate;
	
	public static Item itemMixedMetalSword;
	public static Item itemMixedMetalPickaxe;
	public static Item itemMixedMetalShovel;
	public static Item itemMixedMetalAxe;
	public static Item itemMixedMetalHoe;
	
	public static Item itemObsidianHelmet;
	public static Item itemObsidianChestplate;
	public static Item itemObsidianLeggings;
	public static Item itemObsidianBoots;
	
	public static Item itemCobblestoneHelmet;
	public static Item itemCobblestoneChestplate;
	public static Item itemCobblestoneLeggings;
	public static Item itemCobblestoneBoots;
	
	public static Item itemStoneHelmet;
	public static Item itemStoneChestplate;
	public static Item itemStoneLeggings;
	public static Item itemStoneBoots;
	
	public static Item itemWoodenHelmet;
	public static Item itemWoodenChestplate;
	public static Item itemWoodenLeggings;
	public static Item itemWoodenBoots;
	
	public static Item itemMixedMetalHelmet;
	public static Item itemMixedMetalChestplate;
	public static Item itemMixedMetalLeggings;
	public static Item itemMixedMetalBoots;
	
	public static Item itemEmeraldHelmet;
	public static Item itemEmeraldChestplate;
	public static Item itemEmeraldLeggings;
	public static Item itemEmeraldBoots;
	
	public static Block[] coloredLeather = new Block[16];
	
	private int moddedArmor;
	
	public static Item.ToolMaterial obsidianToolMaterial = EnumHelper.addToolMaterial("obsidian", 3, 4700, 16F, 3F, 16);
	
	public static Item.ToolMaterial mixedMetalToolMaterial = EnumHelper.addToolMaterial("mixedMetal", 3, 3000, 20F, 6F, 22);
	
	public static ItemArmor.ArmorMaterial obsidianArmorMaterial = EnumHelper.addArmorMaterial("obsidian", 1000, new int[]{4,9,7,4}, 8);
	
	public static ItemArmor.ArmorMaterial cobblestoneArmorMaterial = EnumHelper.addArmorMaterial("cobblestone", 10, new int[]{2,4,3,1}, 2);
	
	public static ItemArmor.ArmorMaterial woodenArmorMaterial = EnumHelper.addArmorMaterial("wooden", 6, new int[]{2,3,2,1}, 1);
	
	public static ItemArmor.ArmorMaterial mixedMetalArmorMaterial = EnumHelper.addArmorMaterial("mixedMetal", 500, new int[]{7,11,10,7}, 22);
	
	public static ItemArmor.ArmorMaterial emeraldArmorMaterial = EnumHelper.addArmorMaterial("emerald", 50, new int[]{4,6,4,3}, 18);
	
	public static Item.ToolMaterial emeraldToolMaterial = EnumHelper.addToolMaterial("emerald", 3, 500, 10F, 2F, 20);
	
	
	public static CreativeTabs ourTab = new CreativeTabs("whatshouldbeinminecrafttab"){

		public Item getTabIconItem() {
			return (itemObsidianPickaxe);
		}
		
	};
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		this.registerItems();
		this.registerBlocks();
		otherContainer.preInit(e);
			}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		this.registerCrafting();
		otherContainer.init(e);
		GameRegistry.registerWorldGenerator(new WorldGeneratorMudPit(), 0);
		GameRegistry.registerWorldGenerator(new WorldGeneratorMixedMetalOre(), 1);
		GameRegistry.registerWorldGenerator(new WorldGeneratorWetSand(), 2);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		otherContainer.postInit(e);
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		Items.sign.setMaxStackSize(64);
		Items.bucket.setMaxStackSize(64);
		Items.minecart.setMaxStackSize(64);
		Items.boat.setMaxStackSize(64);
		Items.snowball.setMaxStackSize(64);
		Items.ender_pearl.setMaxStackSize(64);
		Items.chest_minecart.setMaxStackSize(64);
		Items.furnace_minecart.setMaxStackSize(64);
		Items.hopper_minecart.setMaxStackSize(64);
		Items.tnt_minecart.setMaxStackSize(64);
		Items.egg.setMaxStackSize(64);
		Items.bed.setMaxStackSize(64);
		Items.diamond_horse_armor.setMaxStackSize(64);
		Items.golden_horse_armor.setMaxStackSize(64);
		Items.iron_horse_armor.setMaxStackSize(64);
		
		
		
	
		
	}
	
	private void registerCrafting(){
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), "i  ","i  ","   ", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), " i "," i ","   ", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), "  i","  i","   ", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), "   ","i  ","i  ", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), "   "," i "," i ", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemIronStick, 4), "   ","  i","  i", 'i',new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianPickaxe, 1),"ooo"," i "," i ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianAxe, 1),"oo ","oi "," i ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianAxe, 1)," oo"," oi","  i", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianSword, 1)," o "," o "," i ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianSword, 1),"o  ","o  ","i  ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianSword, 1),"  o","  o","  i", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianShovel, 1),"o  ","i  ","i  ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianShovel, 1)," o "," i "," i ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianShovel, 1),"  o","  i","  i", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianHoe, 1),"oo "," i "," i ", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianHoe, 1)," oo","  i","  i", 'o', new ItemStack(Blocks.obsidian), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemStonePlate, 8)," s ","s s"," s ",'s', new ItemStack(Blocks.stone));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestonePlate, 8)," c ","c c"," c ",'c', new ItemStack(Blocks.cobblestone));
		GameRegistry.addRecipe(new ItemStack(Blocks.sponge, 1),"dsd","cwc","dsd",'d', new ItemStack(Blocks.dirt), 'c', new ItemStack(Blocks.clay), 'w', new ItemStack(Items.water_bucket), 's', new ItemStack(Blocks.sand));
		GameRegistry.addRecipe(new ItemStack(this.itemDiamondPlate, 2)," d ","d d"," d ", 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianHelmet, 1),"   ","ooo","odo", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianHelmet, 1),"ooo","odo","   ", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianChestplate, 1),"odo","ooo","ooo", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianLeggings, 1),"ooo","odo","o o", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianBoots, 1),"odo","o o","   ", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianBoots, 1),"   ","odo","o o", 'd', new ItemStack(this.itemDiamondPlate),'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneHelmet, 1),"ccc","c c","   ", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneHelmet, 1),"   ","ccc","c c", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneChestplate, 1),"c c","ccc","ccc", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneLeggings, 1),"ccc","c c","c c", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneBoots, 1),"c c","c c","   ", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemCobblestoneBoots, 1),"   ","c c","c c", 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenPlate, 8)," w ","w w"," w ", 'w', new ItemStack(Blocks.planks));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneHelmet, 1),"sss","s s","   ", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneHelmet, 1),"   ","sss","s s", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneChestplate, 1),"s s","sss","sss", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneLeggings, 1),"sss","s s","s s", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneBoots, 1),"s s","s s","   ", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(this.itemStoneBoots, 1),"   ","s s","s s", 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(Items.saddle, 1),"lll","s s","   ", 'l', new ItemStack(Items.leather), 's', new ItemStack(Items.string));
		GameRegistry.addRecipe(new ItemStack(Items.saddle, 1),"   ","lll","s s", 'l', new ItemStack(Items.leather), 's', new ItemStack(Items.string));
		GameRegistry.addRecipe(new ItemStack(Items.iron_horse_armor, 1),"lli","iic","ccc", 'l', new ItemStack(Items.leather), 'i', new ItemStack(Items.iron_ingot), 'c', new ItemStack(this.itemCobblestonePlate));
		GameRegistry.addRecipe(new ItemStack(Items.iron_horse_armor, 1),"lli","iis","sss", 'l', new ItemStack(Items.leather), 'i', new ItemStack(Items.iron_ingot), 's', new ItemStack(this.itemStonePlate));
		GameRegistry.addRecipe(new ItemStack(Items.golden_horse_armor, 1),"llg","ggi","iii", 'l', new ItemStack(Items.leather), 'g', new ItemStack(Items.gold_ingot), 'i', new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(Items.diamond_horse_armor, 1),"lld","ddg","ggg", 'l', new ItemStack(Items.leather), 'd', new ItemStack(Items.diamond), 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenHelmet, 1),"www","w w","   ", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenHelmet, 1),"   ","www","w w", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenChestplate, 1),"w w","www","www", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenLeggings, 1),"www","w w","w w", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenBoots, 1),"w w","w w","   ", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemWoodenBoots, 1),"   ","w w","w w", 'w', new ItemStack(this.itemWoodenPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalContents, 1),"ogo","idi","ogo", 'd', new ItemStack(Items.diamond), 'g', new ItemStack(Items.gold_ingot), 'i', new ItemStack(Items.iron_ingot), 'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalHelmet, 1),"mmm","mom","   ", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalHelmet, 1),"   ","mmm","mom", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalChestplate, 1),"mom","mmm","mmm", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalLeggings, 1),"mmm","mom","m m", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalBoots, 1),"mom","m m","   ", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalBoots, 1),"   ","mom","m m", 'm', new ItemStack(this.itemMixedMetalIngot), 'o', new ItemStack(this.itemObsidianPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4),"g  ","g  ","   ", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4)," g "," g ","   ", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4),"  g","  g","   ", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4),"   ","g  ","g  ", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4),"   "," g "," g ", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemGoldenStick, 4),"   ","  g","  g", 'g', new ItemStack(Items.gold_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalSword, 1),"m  ","m  ","g  ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalSword, 1)," m "," m "," g ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalSword, 1),"  m","  m","  g", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalPickaxe, 1),"mmm"," g "," g ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalShovel, 1),"m  ","g  ","g  ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalShovel, 1)," m "," g "," g ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalShovel, 1),"  m","  g","  g", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalAxe, 1),"mm ","mg "," g ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalAxe, 1)," mm"," mg","  g", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalHoe, 1)," mm","  g","  g", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(this.itemMixedMetalHoe, 1),"mm "," g "," g ", 'm', new ItemStack(this.itemMixedMetalIngot), 'g', new ItemStack(this.itemGoldenStick));
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 1),"ii ","ii ","   ", 'i', new ItemStack(Blocks.ice));
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 1)," ii"," ii","   ", 'i', new ItemStack(Blocks.ice));
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 1),"   ","ii ","ii ", 'i', new ItemStack(Blocks.ice));
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 1),"   "," ii"," ii", 'i', new ItemStack(Blocks.ice));
		GameRegistry.addRecipe(new ItemStack(this.blockMixedMetalBlock, 1),"mmm","mmm","mmm", 'm', new ItemStack(this.itemMixedMetalIngot));
		GameRegistry.addRecipe(new ItemStack(this.itemPlate, 2),"ppp","   ","   ", 'p', new ItemStack(Items.paper));
		GameRegistry.addRecipe(new ItemStack(this.itemPlate, 2),"   ","ppp","   ", 'p', new ItemStack(Items.paper));
		GameRegistry.addRecipe(new ItemStack(this.itemPlate, 2),"   ","   ","ppp", 'p', new ItemStack(Items.paper));
		GameRegistry.addRecipe(new ItemStack(this.itemObsidianPlate, 8)," o ","o o"," o ", 'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice, 4), new ItemStack(Blocks.packed_ice));
		GameRegistry.addShapelessRecipe(new ItemStack(this.itemMixedMetalIngot, 9), new ItemStack(this.blockMixedMetalBlock));
		GameRegistry.addShapelessRecipe(new ItemStack(this.blockMud, 1), new ItemStack(Blocks.dirt), new ItemStack(Items.water_bucket));
		GameRegistry.addShapelessRecipe(new ItemStack(this.blockWetSand, 1), new ItemStack(Blocks.sand), new ItemStack(Items.water_bucket));
		GameRegistry.addRecipe(new ItemStack(this.itemIronPlate, 8)," i ","i i"," i " ,'i', new ItemStack(Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldSword, 1),"e  ","e  ","i  ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldSword, 1)," e "," e "," i ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldSword, 1),"  e","  e","  i", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldPickaxe, 1),"eee"," i "," i ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldShovel, 1)," e "," i "," i ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldShovel, 1),"e  ","i  ","i  ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldShovel, 1),"  e","  i","  i", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldAxe, 1),"ee ","ei "," i ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldAxe, 1)," ee"," ei","  i", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldHoe, 1),"ee "," i "," i ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldHoe, 1)," ee","  i","  i", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronStick));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldHelmet, 1),"eee","eie","   ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldHelmet, 1),"   ","eee","eie", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldChestplate, 1),"eie","eee","eee", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldLeggings, 1),"eee","e e","e e", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldBoots, 1), "e e","e e","   ", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		GameRegistry.addRecipe(new ItemStack(this.itemEmeraldBoots, 1),"   ","e e","e e", 'e', new ItemStack(Items.emerald), 'i', new ItemStack(this.itemIronPlate));
		
		
		
		
		for(int i = 0; i < 16; i++){
			GameRegistry.addShapelessRecipe(new ItemStack(this.coloredLeather[i]), new ItemStack(this.blockLeatherBlock), new ItemStack(Items.dye, 1, i));
		}
		
		GameRegistry.addRecipe(new ItemStack(this.blockLeatherBlock), "ll ","ll ","   ", 'l', new ItemStack(Items.leather));
		GameRegistry.addRecipe(new ItemStack(this.blockLeatherBlock), "   ","ll ","ll ", 'l', new ItemStack(Items.leather));
		GameRegistry.addRecipe(new ItemStack(this.blockLeatherBlock), " ll"," ll","   ", 'l', new ItemStack(Items.leather));
		GameRegistry.addRecipe(new ItemStack(this.blockLeatherBlock), "   "," ll"," ll", 'l', new ItemStack(Items.leather));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.leather, 4), new ItemStack(this.blockLeatherBlock));
		
		
		
		GameRegistry.addSmelting(new ItemStack(this.itemMixedMetalContents), new ItemStack(this.itemMixedMetalIngot), 1000.0F);
		GameRegistry.addSmelting(new ItemStack(this.blockMixedMetalOre), new ItemStack(this.itemMixedMetalIngot), 1000.0F);
		GameRegistry.addSmelting(new ItemStack(this.blockMud), new ItemStack(Blocks.dirt), 1F);
		GameRegistry.addSmelting(new ItemStack(this.blockWetSand), new ItemStack(Blocks.sand), 1F);
		
	}
	private void registerBlocks(){
		blockLeatherBlock = new BlockLeather(Material.cloth).setCreativeTab(ourTab).setBlockName("leatherBlock").setBlockTextureName(modid + ":" + "leatherBlock");
		GameRegistry.registerBlock(blockLeatherBlock, "leatherBlock");
		blockMixedMetalBlock = new BlockMixedMetal(Material.iron).setCreativeTab(ourTab).setBlockName("mixedMetalBlock").setBlockTextureName(modid + ":" + "mixedMetalBlock").setStepSound(Block.soundTypeMetal).setHardness(5F).setResistance(100F);
		GameRegistry.registerBlock(blockMixedMetalBlock, "mixedMetalBlock");
		blockMixedMetalOre = new BlockMixedMetalOre(Material.rock).setCreativeTab(ourTab).setBlockName("mixedMetalOre").setBlockTextureName(modid + ":" + "mixedMetalOre").setStepSound(Block.soundTypeStone).setHardness(4F).setResistance(20F);
		GameRegistry.registerBlock(blockMixedMetalOre, "mixedMetalOre");
		blockWetSand = new BlockSandModded(Material.sand).setCreativeTab(ourTab).setBlockName("wetSand").setBlockTextureName(modid + ":" + "wetSand").setStepSound(Block.soundTypeSand).setHardness(0.5F).setResistance(2.5F);
		GameRegistry.registerBlock(blockWetSand, "wetSand");
		blockMud = new BlockMud().setCreativeTab(ourTab).setBlockName("mud").setBlockTextureName(modid + ":" + "mud").setStepSound(Block.soundTypeGravel).setHardness(0.5F).setResistance(4F);
		GameRegistry.registerBlock(blockMud, "mud");
	
		for(int i = 0; i < 16; i++){
			
			this.coloredLeather[i] = new BlockColoredLeather(i);
			GameRegistry.registerBlock(this.coloredLeather[i], "coloredLeather"+BlockColoredLeather.names[i]);
			
		}
		
		
	}
	
	
	private void registerItems(){
		itemIronStick = new Item().setCreativeTab(ourTab).setUnlocalizedName("ironStick").setTextureName(modid + ":" + "ironStick");
		GameRegistry.registerItem(itemIronStick, "ironStick");
		itemStonePlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("stonePlate").setTextureName(modid + ":" + "stonePlate");
		GameRegistry.registerItem(itemStonePlate, "stonePlate");
		itemCobblestonePlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("cobblestonePlate").setTextureName(modid + ":" + "cobblestonePlate");
		GameRegistry.registerItem(itemCobblestonePlate, "cobblestonePlate");
		itemWoodenPlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("woodenPlate").setTextureName(modid + ":" + "woodenPlate");
		GameRegistry.registerItem(itemWoodenPlate, "woodenPlate");
		itemMixedMetalContents =  new Item().setCreativeTab(ourTab).setUnlocalizedName("mixedMetalContents").setTextureName(modid + ":" + "mixedMetalContents");
		GameRegistry.registerItem(itemMixedMetalContents, "mixedMetalContents");
		itemDiamondPlate =  new Item().setCreativeTab(ourTab).setUnlocalizedName("diamondPlate").setTextureName(modid + ":" + "diamondPlate");
		GameRegistry.registerItem(itemDiamondPlate, "diamondPlate");
		itemMixedMetalIngot = new Item().setCreativeTab(ourTab).setUnlocalizedName("mixedMetalIngot").setTextureName(modid + ":" + "mixedMetalIngot");
		GameRegistry.registerItem(itemMixedMetalIngot, "mixedMetalIngot");
		itemObsidianPlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("obsidianPlate").setTextureName(modid + ":" + "obsidianPlate");
		GameRegistry.registerItem(itemObsidianPlate, "obsidianPlate");
		itemGoldenStick = new Item().setCreativeTab(ourTab).setUnlocalizedName("goldenStick").setTextureName(modid + ":" + "goldenStick");
		GameRegistry.registerItem(itemGoldenStick, "goldenStick");
		itemPlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("plate").setTextureName(modid + ":" + "plate");
		GameRegistry.registerItem(itemPlate, "plate");
		itemIronPlate = new Item().setCreativeTab(ourTab).setUnlocalizedName("ironPlate").setTextureName(modid + ":" + "ironPlate");
		GameRegistry.registerItem(itemIronPlate, "ironPlate");
	
		this.itemObsidianPickaxe = new ItemModdedPickaxe(this.obsidianToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemObsidianPickaxe").setTextureName(modid+":"+"obsidianPickaxe");
		this.itemObsidianAxe = new ItemModdedAxe(this.obsidianToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemObsidianAxe").setTextureName(modid+":"+"obsidianAxe");
		this.itemObsidianShovel = new ItemModdedShovel(this.obsidianToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemObsidianShovel").setTextureName(modid+":"+"obsidianShovel");
		this.itemObsidianHoe = new ItemModdedHoe(this.obsidianToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemObsidianHoe").setTextureName(modid+":"+"obsidianHoe");
		this.itemObsidianSword = new ItemModdedSword(this.obsidianToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemObsidianSword").setTextureName(modid+":"+"obsidianSword");
		GameRegistry.registerItem(itemObsidianAxe, "itemObsidianAxe");
		GameRegistry.registerItem(itemObsidianPickaxe, "itemObsidianPickaxe");
		GameRegistry.registerItem(itemObsidianShovel, "itemObsidianShovel");
		GameRegistry.registerItem(itemObsidianHoe, "itemObsidianHoe");
		GameRegistry.registerItem(itemObsidianSword, "itemObsidianSword");
		
		this.itemEmeraldPickaxe = new ItemModdedPickaxe(this.emeraldToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemEmeraldPickaxe").setTextureName(modid+":"+"emeraldPickaxe");
		this.itemEmeraldAxe = new ItemModdedAxe(this.emeraldToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemEmeraldAxe").setTextureName(modid+":"+"emeraldAxe");
		this.itemEmeraldShovel = new ItemModdedShovel(this.emeraldToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemEmeraldShovel").setTextureName(modid+":"+"emeraldShovel");
		this.itemEmeraldHoe = new ItemModdedHoe(this.emeraldToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemEmeraldHoe").setTextureName(modid+":"+"emeraldHoe");
		this.itemEmeraldSword = new ItemModdedSword(this.emeraldToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemEmeraldSword").setTextureName(modid+":"+"emeraldSword");
		GameRegistry.registerItem(itemEmeraldAxe, "itemEmeraldAxe");
		GameRegistry.registerItem(itemEmeraldPickaxe, "itemEmeraldPickaxe");
		GameRegistry.registerItem(itemEmeraldShovel, "itemEmeraldShovel");
		GameRegistry.registerItem(itemEmeraldHoe, "itemEmeraldHoe");
		GameRegistry.registerItem(itemEmeraldSword, "itemEmeraldSword");
		
		this.itemMixedMetalPickaxe = new ItemModdedPickaxe(this.mixedMetalToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemMixedMetalPickaxe").setTextureName(modid+":"+"mixedMetalPickaxe");
		this.itemMixedMetalAxe = new ItemModdedAxe(this.mixedMetalToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemMixedMetalAxe").setTextureName(modid+":"+"mixedMetalAxe");
		this.itemMixedMetalShovel = new ItemModdedShovel(this.mixedMetalToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemMixedMetalShovel").setTextureName(modid+":"+"mixedMetalShovel");
		this.itemMixedMetalHoe = new ItemModdedHoe(this.mixedMetalToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemMixedMetalHoe").setTextureName(modid+":"+"mixedMetalHoe");
		this.itemMixedMetalSword = new ItemModdedSword(this.mixedMetalToolMaterial).setCreativeTab(ourTab).setUnlocalizedName("itemMixedMetalSword").setTextureName(modid+":"+"mixedMetalSword");
		GameRegistry.registerItem(itemMixedMetalAxe, "itemMixedMetalAxe");
		GameRegistry.registerItem(itemMixedMetalPickaxe, "itemMixedMetalPickaxe");
		GameRegistry.registerItem(itemMixedMetalShovel, "itemMixedMetalShovel");
		GameRegistry.registerItem(itemMixedMetalHoe, "itemMixedMetalHoe");
		GameRegistry.registerItem(itemMixedMetalSword, "itemMixedMetalSword");
		
		
		this.itemObsidianHelmet = new ItemObsidianArmor(this.obsidianArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemObsidianHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"obsidianHelmet");
		this.itemObsidianChestplate = new ItemObsidianArmor(this.obsidianArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemObsidianChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"obsidianChestplate");
		this.itemObsidianLeggings = new ItemObsidianArmor(this.obsidianArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemObsidianLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"obsidianLeggings");
		this.itemObsidianBoots = new ItemObsidianArmor(this.obsidianArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemObsidianBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"obsidianBoots");
		GameRegistry.registerItem(itemObsidianHelmet, "itemObsidianHelmet");
		GameRegistry.registerItem(itemObsidianChestplate, "itemObsidianChestplate");
		GameRegistry.registerItem(itemObsidianLeggings, "itemObsidianLeggings");
		GameRegistry.registerItem(itemObsidianBoots, "itemObsidianBoots");
		
		this.itemCobblestoneHelmet = new ItemCobblestoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemCobblestoneHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"cobblestoneHelmet");
		this.itemCobblestoneChestplate = new ItemCobblestoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemCobblestoneChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"cobblestoneChestplate");
		this.itemCobblestoneLeggings = new ItemCobblestoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemCobblestoneLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"cobblestoneLeggings");
		this.itemCobblestoneBoots = new ItemCobblestoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemCobblestoneBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"cobblestoneBoots");
		GameRegistry.registerItem(itemCobblestoneHelmet, "itemCobblestoneHelmet");
		GameRegistry.registerItem(itemCobblestoneChestplate, "itemCobblestoneChestplate");
		GameRegistry.registerItem(itemCobblestoneLeggings, "itemCobblestoneLeggings");
		GameRegistry.registerItem(itemCobblestoneBoots, "itemCobblestoneBoots");
		
		this.itemStoneHelmet = new ItemStoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemStoneHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"stoneHelmet");
		this.itemStoneChestplate = new ItemStoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemStoneChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"stoneChestplate");
		this.itemStoneLeggings = new ItemStoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemStoneLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"stoneLeggings");
		this.itemStoneBoots = new ItemStoneArmor(this.cobblestoneArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemStoneBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"stoneBoots");
		GameRegistry.registerItem(itemStoneHelmet, "itemStoneHelmet");
		GameRegistry.registerItem(itemStoneChestplate, "itemStoneChestplate");
		GameRegistry.registerItem(itemStoneLeggings, "itemStoneLeggings");
		GameRegistry.registerItem(itemStoneBoots, "itemStoneBoots");
		
		this.itemWoodenHelmet = new ItemWoodenArmor(this.woodenArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemWoodenHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"woodenHelmet");
		this.itemWoodenChestplate = new ItemWoodenArmor(this.woodenArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemWoodenChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"woodenChestplate");
		this.itemWoodenLeggings = new ItemWoodenArmor(this.woodenArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemWoodenLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"woodenLeggings");
		this.itemWoodenBoots = new ItemWoodenArmor(this.woodenArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemWoodenBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"woodenBoots");
		GameRegistry.registerItem(itemWoodenHelmet, "itemWoodenHelmet");
		GameRegistry.registerItem(itemWoodenChestplate, "itemWoodenChestplate");
		GameRegistry.registerItem(itemWoodenLeggings, "itemWoodenLeggings");
		GameRegistry.registerItem(itemWoodenBoots, "itemWoodenBoots");

		this.itemMixedMetalHelmet = new ItemMixedMetalArmor(this.mixedMetalArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemMixedMetalHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"mixedMetalHelmet");
		this.itemMixedMetalChestplate = new ItemMixedMetalArmor(this.mixedMetalArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemMixedMetalChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"mixedMetalChestplate");
		this.itemMixedMetalLeggings = new ItemMixedMetalArmor(this.mixedMetalArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemMixedMetalLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"mixedMetalLeggings");
		this.itemMixedMetalBoots = new ItemMixedMetalArmor(this.mixedMetalArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemMixedMetalBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"mixedMetalBoots");
		GameRegistry.registerItem(itemMixedMetalHelmet, "itemMixedMetalHelmet");
		GameRegistry.registerItem(itemMixedMetalChestplate, "itemMixedMetalChestplate");
		GameRegistry.registerItem(itemMixedMetalLeggings, "itemMixedMetalLeggings");
		GameRegistry.registerItem(itemMixedMetalBoots, "itemMixedMetalBoots");
		
		this.itemEmeraldHelmet = new ItemEmeraldArmor(this.emeraldArmorMaterial, moddedArmor, 0).setUnlocalizedName("itemEmeraldHelmet").setCreativeTab(ourTab).setTextureName(modid+":"+"emeraldHelmet");
		this.itemEmeraldChestplate = new ItemEmeraldArmor(this.emeraldArmorMaterial, moddedArmor, 1).setUnlocalizedName("itemEmeraldChestplate").setCreativeTab(ourTab).setTextureName(modid+":"+"emeraldChestplate");
		this.itemEmeraldLeggings = new ItemEmeraldArmor(this.emeraldArmorMaterial, moddedArmor, 2).setUnlocalizedName("itemEmeraldLeggings").setCreativeTab(ourTab).setTextureName(modid+":"+"emeraldLeggings");
		this.itemEmeraldBoots = new ItemEmeraldArmor(this.emeraldArmorMaterial, moddedArmor, 3).setUnlocalizedName("itemEmeraldBoots").setCreativeTab(ourTab).setTextureName(modid+":"+"emeraldBoots");
		GameRegistry.registerItem(itemEmeraldHelmet, "itemEmeraldHelmet");
		GameRegistry.registerItem(itemEmeraldChestplate, "itemEmeraldChestplate");
		GameRegistry.registerItem(itemEmeraldLeggings, "itemEmeraldLeggings");
		GameRegistry.registerItem(itemEmeraldBoots, "itemEmeraldBoots");
		
		

	}
	
	

}
