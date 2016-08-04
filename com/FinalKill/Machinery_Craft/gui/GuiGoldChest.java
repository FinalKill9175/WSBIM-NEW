package com.FinalKill.Machinery_Craft.gui;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerGoldChest;
import com.FinalKill.Machinery_Craft.inventory.ContainerIronChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityGoldChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGoldChest extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/goldChest.png");

	public GuiGoldChest(InventoryPlayer player, TileEntityGoldChest tileentity) {
		super(new ContainerGoldChest(player, tileentity));
		this.xSize = 194;
		this.ySize = 219;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRendererObj.drawString("Gold Chest", 7, 5, MachineryCraft.gray);
		this.fontRendererObj.drawString("Inventory", 16, 125, MachineryCraft.gray);
		
	}
}
