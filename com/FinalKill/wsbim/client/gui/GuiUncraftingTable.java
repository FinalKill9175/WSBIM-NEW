package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerUncraftingTable;

public class GuiUncraftingTable extends GuiContainer {
	
	private final ResourceLocation res = new ResourceLocation(WSBIM.modid, "textures/gui/uncraftingTable.png");

	
	public GuiUncraftingTable(InventoryPlayer inventory, World world, int x,
			int y, int z) {
		super(new ContainerUncraftingTable(inventory, world, x, y,z));
	
	}

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
   	 String s = "Dismantling Table";
     this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        ResourceLocation background = new ResourceLocation("textures/gui/container/dispenser.png");
        ResourceLocation craft = new ResourceLocation("textures/gui/container/crafting_table.png");
        
        if(!WSBIM.options.useAdvancedResourcePackSupport || !(mc.gameSettings.resourcePacks.size() > 0)){
        	this.mc.getTextureManager().bindTexture(res);
        	this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        }
        else{
        	Minecraft.getMinecraft().renderEngine.bindTexture(background);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(guiLeft+56, guiTop+5, 7, 5, 48, 68);
			this.drawTexturedModalRect(guiLeft+56+48, guiTop+5, 7, 5, 48, 68);
			Minecraft.getMinecraft().renderEngine.bindTexture(craft);
			this.drawTexturedModalRect(guiLeft+93, guiTop+17, 29, 16, 54, 54);
			this.drawTexturedModalRect(guiLeft+63, guiTop+35, 90, 35, 22, 15);
			this.drawTexturedModalRect(guiLeft+29, guiTop+30, 119, 30, 26, 26);
        }
    }

}
