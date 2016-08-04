package com.FinalKill.Machinery_Craft.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.FinalKill.MachineryCraftAPI.ItemPower;
import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.TextureName;
import com.FinalKill.Machinery_Craft.tabs.MachineryCraftTabs;
import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.registry.GameRegistry;

public class MachineryCraftItems {
	public static Item battery;
	
	//Materials
		public static Item industrialIron;
		public static Item tin_ingot;
		public static Item copper_ingot;
		
		//Do Not Use
		public static Item upgradeCookSpeed1;
		public static Item upgradeCookSpeed2;
		public static Item upgradeCookSpeed3;
//		public static Item infoItem;
		
		//Dusts
		
		public static Item iron_ore_dust;
		public static Item gold_ore_dust;
		public static Item coal_dust;
		public static Item industrial_iron_dust;
		public static Item copper_dust;
		public static Item tin_dust;
		
		//Block-Type Items
		public static Item rainCollector;

		
		private MachineryCraftTabs tabs = MachineryCraft.tabs;
	
	public void registerItems(){
		//industrialIron = new Item().setCreativeTab(MachineryCraft.tabs.materialsTab).setTextureName(new TextureName("industrialIron", MachineryCraft.MODID).getTextureName()).setUnlocalizedName("industrialIron");
    	//GameRegistry.registerItem(industrialIron, "industrialIron");
    	//GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(industrialIron), 1.0F);
    
    //	upgradeCookSpeed1 = new ItemUpgradeCookSpeed(30).setCreativeTab(tabs.machinesTab).setUnlocalizedName("upgradeCookSpeedLvl1");
 //   	upgradeCookSpeed2 = new ItemUpgradeCookSpeed(15).setCreativeTab(tabs.machinesTab).setUnlocalizedName("upgradeCookSpeedLvl2");
 //   	upgradeCookSpeed3 = new ItemUpgradeCookSpeed(5).setCreativeTab(tabs.machinesTab).setUnlocalizedName("upgradeCookSpeedLvl3");
   // 	GameRegistry.registerItem(upgradeCookSpeed1, "upgradeCookSpeedLvl1");
   // 	GameRegistry.registerItem(upgradeCookSpeed2, "upgradeCookSpeedLvl2");
    //	GameRegistry.registerItem(upgradeCookSpeed3, "upgradeCookSpeedLvl3");
	    	
    	//copper_ingot = new Item().setCreativeTab(tabs.materialsTab).setTextureName("machinery_craft:copper_ingot").setUnlocalizedName("copper_ingot");
     	//GameRegistry.registerItem(copper_ingot, "copper_ingot_machineryCraft");
     	
     	//tin_ingot = new Item().setCreativeTab(tabs.materialsTab).setTextureName("machinery_craft:tin_ingot").setUnlocalizedName("tin_ingot");
     	//GameRegistry.registerItem(tin_ingot, "tin_ingot_machineryCraft");

     	
     	//copper_dust = new Item().setCreativeTab(tabs.materialsTab).setTextureName("machinery_craft:copper_dust").setUnlocalizedName("copper_dust");
     	//GameRegistry.registerItem(copper_dust, "copper_dust_machineryCraft");

     	//tin_dust = new Item().setCreativeTab(tabs.materialsTab).setTextureName("machinery_craft:tin_dust").setUnlocalizedName("tin_dust");
     	//GameRegistry.registerItem(tin_dust, "tin_dust_machineryCraft");

	//battery =  new ItemPower(40).setCreativeTab(tabs.machinesTab).setTextureName(new TextureName("battery_normal", MachineryCraft.MODID).getTextureName()).setUnlocalizedName("battery").setMaxStackSize(1);
 //GameRegistry.registerItem(battery, battery.getUnlocalizedName());

 	//tin_ingot = new Item().setCreativeTab(tabs.materialsTab).setTextureName("machinery_craft:tin_ingot").setUnlocalizedName("tin_ingot");
 	//GameRegistry.registerItem(tin_ingot, "tin_ingot_machineryCraft");
 	
 	rainCollector = new ItemRainCollector().setCreativeTab(WSBIM.main.ourTab).setUnlocalizedName("rainCollector").setTextureName("wsbim_machinery:rainCollector").setMaxStackSize(1);
 
 	GameRegistry.registerItem(rainCollector, "rainCollecotr_machineryCraft_item");
 	
 	
//infoItem = new ItemModInformation(MODID, version, "FinalKill9175").setCreativeTab(decorTab).setTextureName("info").setUnlocalizedName("infoItem");
//GameRegistry.registerItem(infoItem, infoItem.getUnlocalizedName());

		
		
	}
	
	
	
	
	
	
}
