package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.Machinery_Craft.inventory.ContainerIronFurnace;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronFurnace;

public class GuiIronFurnace extends GuiContainer{

	public static final ResourceLocation texture = new ResourceLocation("textures/gui/container/furnace.png");
	private TileEntityIronFurnace furnace;
	
	public GuiIronFurnace(InventoryPlayer inventory, TileEntityIronFurnace tileentity) {
		super(new ContainerIronFurnace(inventory, tileentity));
		xSize = 176;
		ySize = 166;
		this.furnace = tileentity;
		
		}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		  if (this.furnace.isBurning())
	        {
	           int var6 = this.furnace.getBurnTimeScaled(12);
	            this.drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - var6, 176, 12 - var6, 14, var6 + 2);
	        }

	       int var7 = this.furnace.getProgressScaled(24);
	        this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, var7 + 1, 16);

	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
	       this.fontRendererObj.drawString("Iron Furnace", this.guiLeft / 2 - this.fontRendererObj.getStringWidth("Iron Furnace") / 2 + 31, 6, 4210752);

		  this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, 72, 4210752);
		 		
	}

}
