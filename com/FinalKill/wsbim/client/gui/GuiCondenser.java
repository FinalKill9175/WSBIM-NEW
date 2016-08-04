package com.FinalKill.wsbim.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerCondenser;
import com.FinalKill.wsbim.common.tileentity.TileEntityCondenser;

public class GuiCondenser extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(WSBIM.modid, "textures/gui/guiCondenser.png");
	public static final ResourceLocation sprites = new ResourceLocation(WSBIM.modid, "textures/gui/sprites.png");
	
	private TileEntityCondenser condenser;
	
	public static final int progressBarLength = 201;
	public static final int progressBarHeight = 17;
	
	public static final int xpBarWidth = 11;
	public static final int xpBarHeight = 102;

	public GuiCondenser(InventoryPlayer player, TileEntityCondenser tileentity) {
		super(new ContainerCondenser(player, tileentity));
		condenser = tileentity;
		xSize = 256;
		ySize = 256;
	}

	
	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1,
			int arg2) {

		GL11.glColor4f(1, 1, 1, (float) 0.5);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(sprites);
		//TileEntityCondenser var4 = (TileEntityCondenser)Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(condenser.xCoord, condenser.yCoord, condenser.zCoord);
	   
		TileEntityCondenser var4 = this.condenser;
		int var5 = var4.getCVScaled(this.progressBarLength);
		this.drawTexturedModalRect(guiLeft+46, guiTop+13, 0, xpBarHeight+this.progressBarHeight, var5, this.progressBarHeight);
	
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2){
		TileEntityCondenser var4  = this.condenser;
		this.fontRendererObj.drawString("Inventory", 48, 162, 4210752);
		this.fontRendererObj.drawString(var4.cv+"", 65, 18, 0);

	}

}
