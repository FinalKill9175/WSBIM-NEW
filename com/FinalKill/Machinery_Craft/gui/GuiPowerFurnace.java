package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerPowerFurnace;
import com.FinalKill.Machinery_Craft.tile.TileEntityPowerFurnace;

public class GuiPowerFurnace extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/electricFurnace.png");

	
	private TileEntityPowerFurnace furnace;
	
	public GuiPowerFurnace(InventoryPlayer inventory, TileEntityPowerFurnace tileentity) {
		
		super(new ContainerPowerFurnace(inventory, (TileEntityPowerFurnace)tileentity));
		this.furnace = tileentity;
		this.xSize = 176;
		this.ySize = 166;
		
		
		}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		

		int power = (int) furnace.getPowerScaled(70);
		
		this.drawTexturedModalRect(guiLeft +7, guiTop+77-power, xSize, 70-power, 24, power);
int cook = furnace.getTimeScaled(22);
		
		this.drawTexturedModalRect(guiLeft + 104 - 1, guiTop + 35, 0, ySize, cook, 15);
		
		
		
	}
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		
		this.fontRendererObj.drawString("Electric Furnace",65, 6, MachineryCraft.gray);

		
	}

}
