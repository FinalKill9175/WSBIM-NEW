package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerDiamondChest;
import com.FinalKill.Machinery_Craft.inventory.ContainerIronChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

public class GuiDiamondChest extends GuiContainer {

	public GuiDiamondChest(InventoryPlayer inventory,
			TileEntityDiamondChest tileentity) {
		super(new ContainerDiamondChest(inventory, tileentity));
		xSize = 256;
		ySize = 256;
	
	}

	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/diamondChest.png");

	

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRendererObj.drawString("Diamond Chest", 11, 5, MachineryCraft.gray);
		this.fontRendererObj.drawString("Inventory", 49, 163, MachineryCraft.gray);
		
	}

}
