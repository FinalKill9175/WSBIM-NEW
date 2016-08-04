package com.FinalKill.Machinery_Craft.gui;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerRainCollector;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiRainCollector extends GuiContainer {

	public ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/rainCollector.png");
	
	private TileEntityRainCollector rainCollector;

	public GuiRainCollector(InventoryPlayer inventory, TileEntityRainCollector tileentity) {
		
		super(new ContainerRainCollector(inventory, tileentity));
		
		this.rainCollector = tileentity;
		this.xSize = 176;
		this.ySize = 166;
	
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2,  int var3) {
		
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		
	int var4 = (int) rainCollector.getWaterScaled(70);
		
		this.drawTexturedModalRect(guiLeft +40, guiTop+77-var4, xSize, 70-var4, 24, var4);
		
	
		
		
	}
	
	protected void drawGuiContainerForegroundLayer(int var1,  int var2) {
		this.fontRendererObj.drawString("Rain Collector", 80, 32, MachineryCraft.gray);
		this.fontRendererObj.drawString("Water: "+(int)this.rainCollector.getWater()+"/500", 80, 45, MachineryCraft.gray);
		
	}

}
