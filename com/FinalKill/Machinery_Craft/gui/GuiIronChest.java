package com.FinalKill.Machinery_Craft.gui;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerIronChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiIronChest extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/ironChest.png");

	public GuiIronChest(InventoryPlayer player, TileEntityIronChest tileentity) {
		super(new ContainerIronChest(player, tileentity));
		this.xSize = 176;
		this.ySize = 204;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRendererObj.drawString("Iron Chest", 7, 6, MachineryCraft.gray);
		this.fontRendererObj.drawString("Inventory", 8, 110, MachineryCraft.gray);
		
	}
}
