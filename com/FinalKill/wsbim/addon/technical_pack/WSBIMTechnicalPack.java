package com.FinalKill.wsbim.addon.technical_pack;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=WSBIMTechnicalPack.modid,version=WSBIMTechnicalPack.version,name=WSBIMTechnicalPack.name)
public class WSBIMTechnicalPack{
	public static final String modid = "wsbim_technical_pack";
	public static final String version = "1.7.10-1.0-Dev";
	public static final String name = "WSBIM Technical Pack Addon";
	public static final int pack_id = 0;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		if(!Loader.isModLoaded("wsbim")){
			this.reportNoMods("What should be in minecraft");
		}
		else if(!Loader.isModLoaded("condenser_values_api")){
			//TODO this.reportNoMods("Condenser Values API");
		}
		else{
			
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	public void reportNoMods(String mod){
		Minecraft.getMinecraft().crashed(new CrashReport(mod+" is not loaded, but only with an addon.", new InvalidPackThrowable("Invalid mods loaded, cant have this addon pack without "+mod+"!")));
	}
	
}
