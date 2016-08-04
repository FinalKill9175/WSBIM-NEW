package com.FinalKill.Machinery_Craft.gui;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerTrashcan;
import com.FinalKill.Machinery_Craft.tile.TileEntityTrashcan;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;



public class GuiTrashcan extends GuiContainer {


	private static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/trashCan.png");
	TileEntityTrashcan t;
	public GuiTrashcan(InventoryPlayer inventory, TileEntityTrashcan tileentity) {
	super(new ContainerTrashcan(inventory, tileentity));
	xSize = 176;
	ySize = 110;
	 t = tileentity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
					Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
					
					this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
				
	}	
	@Override
	protected void drawGuiContainerForegroundLayer(int var1,int var2) {
		this.fontRendererObj.drawString("<- Trash", 102, 12, MachineryCraft.gray);
		this.fontRendererObj.drawString("Inventory", 7, 16, MachineryCraft.gray);
		
		
	}	
   


}
