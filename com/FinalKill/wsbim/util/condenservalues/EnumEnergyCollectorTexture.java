package com.FinalKill.wsbim.util.condenservalues;

import com.FinalKill.wsbim.WSBIM;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum EnumEnergyCollectorTexture {
	DEFAULT("energyCollectorSide","energyCollectorTop",WSBIM.modid,"energyCollector.png"),
	MK2("energyCollectorSide","energyCollectorTopMK2", WSBIM.modid, "energyCollector.png"),
	MK3("energyCollectorSide","energyCollectorTopMK3", WSBIM.modid, "energyCollector.png");


	private String blockSideTexture;
	private String blockTopTexture;
	private String modid;
	private String guiTexture;
	
	private EnumEnergyCollectorTexture(String blockSideTexture, String blockTopTexture, String modid, String guiTexture){
		this.blockSideTexture = blockSideTexture;
		this.blockTopTexture = blockTopTexture;
		this.modid = modid;
		this.guiTexture = guiTexture;
	}

	public String getBlockSideTexture() {
		return modid+":"+blockSideTexture;
	}

	public String getBlockTopTexture() {
		return modid+":"+blockTopTexture;
	}
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getGuiTexture(){
		return new ResourceLocation(modid, "textures/gui/energy_collector/"+guiTexture);
	}
}
