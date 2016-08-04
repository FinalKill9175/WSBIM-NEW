package com.FinalKill.wsbim.client.event;

import java.util.Random;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.FinalKill.wsbim.client.gui.settings.GuiWSBIMSettings;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class GuiButtonEvent {

	@SubscribeEvent
	public void onRender(RenderTickEvent e){
		if(Minecraft.getMinecraft() !=null){
			Minecraft mc = Minecraft.getMinecraft();
	
			//System.out.println(WSBIM.options.testBoolean);
			if(mc.currentScreen !=null){
				GuiScreen current = mc.currentScreen;
				 int i = Mouse.getEventX() * current.width / mc.displayWidth;
			        int j = current.height - Mouse.getEventY() * current.height / mc.displayHeight - 1;
				boolean b = false;
				if(current instanceof GuiMainMenu || current instanceof GuiIngameMenu || current instanceof GuiOptions){
					if(!(current instanceof GuiMainMenu) && !(current instanceof GuiIngameMenu)){
						current.drawString(Minecraft.getMinecraft().fontRenderer, EnumChatFormatting.GOLD+"Hit 'U' to view options for WSBIM", 2, 2, 16777215);
					}
					
					if(Mouse.getEventButtonState() && i < Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT && j < 500){
					//	Minecraft.getMinecraft().displayGuiScreen(new GuiWSBIMSettings(Minecraft.getMinecraft(), current));
					}
				
					if(Keyboard.isKeyDown(Keyboard.KEY_U)){
						b = true;
					}
				}
				
				if(b){
					Minecraft.getMinecraft().displayGuiScreen(new GuiWSBIMSettings(Minecraft.getMinecraft(), current));
					b = false;
				}
				
				//int c = Blocks.wool.getMapColor(new Random().nextInt(15)).colorValue;
				//int c = new Random().nextInt(2) == 0? MapColor.blackColor.colorValue : 0xFFFFFFF;
				//current.drawRect(i-100, j-100, i, j, -c);
				
				/**
				if(current instanceof GuiOptions){
					if(buttonList.size() < 13) //this has to be one smaller than the number of buttons it usually has, else you will keep adding your button over and oever again
					{
						buttonList.add(new GuiButtonWSBIMSettings(-1, current.width / 2 - 155, current.height / 6 + 72 - 30, 150, 20, EnumChatFormatting.YELLOW+I18n.format("button.wsbimSettings", new Object[0]))); //you have to make a class extending GuiButton to add function to your button
					}	
				}
				*/
			}
		}
	}
	
}
