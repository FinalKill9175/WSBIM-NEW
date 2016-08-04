package com.FinalKill.MachineryCraftAPI;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid =MachineryCraftAPI.modid, version =MachineryCraftAPI.version, name = "WSBIM Machinery Toolset/API")
public class MachineryCraftAPI {

	public static final String modid = "machinery_craft_api";
	public static final String version = WSBIM.version;
	
	private static OreDustRegistry ores;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		ores = new OreDustRegistry();
		ores.prepareLists();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	public static OreDustRegistry getOreDustRegistry(){
		
		return ores;
	}
	
	
	
	
}
