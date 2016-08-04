package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerSmallCrafting;

public class GuiSmallCrafting extends GuiContainer{

	private static ResourceLocation texture = new ResourceLocation(WSBIM.modid, "textures/gui/tab.png"); 
	private static ResourceLocation inv = new ResourceLocation("textures/gui/container/inventory.png");

	private static ResourceLocation background = new ResourceLocation("textures/gui/container/dispenser.png");

	public GuiSmallCrafting(EntityPlayer player) {
		super(new ContainerSmallCrafting(player));
	
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int grid_width = 88;
		int grid_height = 48;
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		//mc.renderEngine.bindTexture(texture);
		//this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if(WSBIM.options.useAdvancedResourcePackSupport && (mc.gameSettings.resourcePacks.size() > 0)){
			Minecraft.getMinecraft().renderEngine.bindTexture(this.background);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(guiLeft+56, guiTop+5, 7, 5, 48, 68);
			this.drawTexturedModalRect(guiLeft+56+48, guiTop+5, 7, 5, 48, 68);
		}
		else{
			mc.renderEngine.bindTexture(texture);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
		
		mc.renderEngine.bindTexture(inv);
		this.drawTexturedModalRect(guiLeft + (xSize / 2 - (grid_width / 2)), guiTop + 18, 80, 18, grid_width, grid_height);
		GL11.glPopMatrix();
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);;
		  String s = I18n.format("container.crafting", new Object[0]);
	      this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
	}

}
