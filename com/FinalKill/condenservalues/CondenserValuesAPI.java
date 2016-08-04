package com.FinalKill.condenservalues;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = CondenserValuesAPI.modid, version = CondenserValuesAPI.version)
public class CondenserValuesAPI {
	public static final String modid = "condenser_values_api";
	public static final String version = "1.7.10-2.1-Release";
	private static CondenserItemValues instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		instance = new CondenserItemValues();
		//CondenserValuesAPI.instance().addCondenserValueZipFile(new FileZip("https://www.dropbox.com/s/wq94yey8daeyggn/mod_texts.zip?dl=1","test"));
	
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		MinecraftForge.EVENT_BUS.register(new EventHooksFinalKill());
		for(int i = 0; i<instance().itemsToLoad.length; i++){	
			instance().itemsWithValue.add(i, null);
			instance().itemValues.add(i, null);
		}
		CondenserItemValues.createBlockValues();
		CondenserItemValues.createItemValues();
				//CondenserItemValues.manageCondenserItemValues();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		//CondenserItemValues.createValues();

		instance().registerValueWithCrossModCompatibility("IC2", "itemIngot", 128, false);
		instance().registerValueWithCrossModCompatibility("TConstruct", "material", 128, false);
		instance().registerValueWithCrossModCompatibility("IC2", "itemHarz", 24, false);
		instance().registerValueWithCrossModCompatibility("BigReactors", "BRIngot", 128, false);
		instance().registerValueWithCrossModCompatibility("Backpack", "boundLeather", 128 + (12*7), false);
		instance().registerValueWithCrossModCompatibility("Backpack", "tannedLeather", 128 + (12*7), false);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","logs1", 32, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","logs2", 32, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","logs3", 32, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","logs4", 32, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","planks", 8, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","newBopGrass", 1, true);
		instance().registerValueWithCrossModCompatibility("BiomesOPlenty","newBopDirt", 1, true);

		instance().registerValueWithCrossModCompatibility("IC2", "blockMetal", 128*9, true);
		

		

		
		
	}
	public static CondenserItemValues instance(){
		
		return instance;
		
	}
	


}
