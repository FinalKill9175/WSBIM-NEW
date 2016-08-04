package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerMacerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;

public class GuiMacerator extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/macerator.png");
	
	
	public TileEntityMacerator macerator;
	
	public GuiMacerator(InventoryPlayer player, TileEntityMacerator macerator) {
		super(new ContainerMacerator(player, macerator));
		
		this.macerator = macerator;

		xSize = 176;
		ySize = 166;
		
	}

	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1,
			int arg2) {
		
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		
		
		
		int var1 = (int) macerator.getPowerScaled(70);
		
		this.drawTexturedModalRect(guiLeft +7, guiTop+77-var1, xSize, 70-var1, 24, var1);
		
		int var2 = macerator.getTimeScaled(22);
		
		this.drawTexturedModalRect(guiLeft + 104, guiTop + 35, 0, ySize, var2, 15);
		
		
		
	}

	protected void drawGuiContainerForegroundLayer(int arg1,
			int arg2) {
		this.fontRendererObj.drawString("Macerator",65, 6, MachineryCraft.gray);
		
	}
	

}
