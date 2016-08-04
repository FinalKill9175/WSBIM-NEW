package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerFreezer;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;

public class GuiFreezer extends GuiContainer{
	private final ResourceLocation res = new ResourceLocation(WSBIM.modid, "textures/gui/freezer.png");

	TileEntityFreezer freezer;
	
	public GuiFreezer(InventoryPlayer inventory, TileEntityFreezer freezer) {
		super(new ContainerFreezer(inventory, freezer));
		this.freezer = freezer;
		this.ySize = 167;
	}

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
   	 String s = "Freezer";
     this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
        
        this.fontRendererObj.drawString("Temp: "+this.freezer.temp_int+" degrees", 70, this.ySize - 96 + 2, this.freezer.temp_int < 32? 0x3d00cc : (this.freezer.temp_int < 66? 0x00c75a : 0xc7a900));
        
        
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(res);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        if(this.freezer.isRecieveingPower()){
        	//10, 21
        	  this.drawTexturedModalRect(k+12, l+21, this.xSize, 10, 8, 12);

        }
        
       /// if(this.freezer.freezeTime > 0){
        if(this.freezer.freezeLoop > 0){
        int i1 = this.freezer.getLoopScaled(16);
        this.drawTexturedModalRect(k + 8, l + 38, xSize, 0, i1 + 1, 10);
        }
        
           ///}
    }
}
