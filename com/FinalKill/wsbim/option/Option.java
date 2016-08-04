package com.FinalKill.wsbim.option;

import net.minecraft.client.gui.GuiScreen;

import com.FinalKill.wsbim.WSBIMOptions.OptionType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Option{
	
	public boolean avalableOption;
	public String variableName;
	public OptionType type;
	public String guiName;
	
	public Option(boolean gui, String variableName, String guiName, OptionType type){
		this.avalableOption = gui;
		this.variableName = variableName;
		this.guiName = guiName;
		this.type = type;
	}
	
	public abstract Object getObjectForCycle(int cycle);
	public abstract int getCycleLength();
	public abstract int getCycleStart();
	public abstract String getStringForCycle(int cycle);
	@SideOnly(Side.CLIENT)
	public abstract GuiScreen getGuiToOpen();
}

