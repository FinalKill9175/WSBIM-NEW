package com.FinalKill.Machinery_Craft.items;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

public class GuiModInformation extends GuiScreen {
	private static  String modid;
	private static String version;
	private static  String author;
	
	public GuiModInformation(String mod, String ver, String auth){
		
		modid = mod;
		version = ver;
		author = auth;
		
	}
	
	@Override
	public void initGui(){
		this.buttonList.add(new GuiButton(0, width/2, height/2, 80,20, "Close"));
		
	}
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        
    }
	
	@Override
	public void actionPerformed(GuiButton b){
		
		if(b.id == 0){
	
		
		}
	}


}
