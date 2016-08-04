package com.FinalKill.wsbim.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerAntimatterWorkbench;
import com.FinalKill.wsbim.common.tileentity.TileEntityAntimatterWorkbench;

public class GuiAntimatterWorkbench extends GuiContainer{
	
	private static final ResourceLocation gui_texture = new ResourceLocation(WSBIM.modid, "textures/gui/antimatter_workbench.png");

	private TileEntityAntimatterWorkbench tile;
	
	public GuiAntimatterWorkbench(InventoryPlayer player, TileEntityAntimatterWorkbench tile) {
		super(new ContainerAntimatterWorkbench(player, tile));
		xSize = 176;
		ySize = 166;
		this.tile = tile;
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(gui_texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int var = tile.getFuelScaled(34);
		this.drawTexturedModalRect(guiLeft+11, guiTop+16 + (34-var), xSize, 15 + (34 - var), 10, var);
		
		int var2 = tile.getProgressScaled(23);
		this.drawTexturedModalRect(guiLeft + 111, guiTop + 36, xSize, 0, var2, 15);
		
		GL11.glPopMatrix();
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		 String s = "Antimatter Workbench";
	     this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 0);
	     this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 0xFFFFFF);
	     //TODO
	     /**
	     List<String> str = new ArrayList<String>();
	     str.add("Fuel Level: "+tile.fuelTime +" / "+ tile.maxFuel + " Amps");
	     
	 
	     if((mouseX >= (guiLeft + 11) && mouseX <= (guiLeft + 11 + 10)) && (mouseY >= guiTop + 16 && mouseY <= guiTop + 16 + 34)){
	    	 this.drawText(str, guiLeft, guiTop, this.fontRendererObj);
	     }
	     
	     
	     List<String> str2 = new ArrayList<String>();
	     int var = tile.getProgressScaled(100);
	     str2.add("Progress: "+var+" %");
	     
	     if(var > 0){
	    	if((mouseX >= (guiLeft + 111) && mouseX <= (guiLeft + 111 + 23)) && (mouseY >= guiTop + 36 && mouseY <= guiTop + 36 + 15)){
	    		this.drawText(str2, guiLeft + 23, guiTop + 10, this.fontRendererObj);
	     	}
	     }
	     */
	     
	}
	 protected void drawText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font)
	    {
	        if (!p_146283_1_.isEmpty())
	        {
	        	   GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	               RenderHelper.disableStandardItemLighting();
	               GL11.glDisable(GL11.GL_LIGHTING);
	               GL11.glDisable(GL11.GL_DEPTH_TEST);
	               GL11.glPushMatrix();
	               int k = 0;
	               Iterator iterator = p_146283_1_.iterator();

	               while (iterator.hasNext())
	               {
	                   String s = (String)iterator.next();
	                   int l = font.getStringWidth(s);

	                   if (l > k)
	                   {
	                       k = l;
	                   }
	               }

	               int j2 = p_146283_2_;
	               int k2 = p_146283_3_;
	               int i1 = 8;

	               if (p_146283_1_.size() > 1)
	               {
	                   i1 += 2 + (p_146283_1_.size() - 1) * 10;
	               }

	               int j1 = -267386864;
	               this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
	               this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
	               this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
	               this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
	               this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
	               int k1 = 1347420415;
	               int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
	               this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
	               this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
	               this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
	               this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

	               for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
	               {
	                   String s1 = (String)p_146283_1_.get(i2);
	                   font.drawStringWithShadow(s1, j2, k2, -1);

	                   if (i2 == 0)
	                   {
	                       k2 += 2;
	                   }

	                   k2 += 10;
	               }

	               GL11.glPopMatrix();

	        }
	    }

}
