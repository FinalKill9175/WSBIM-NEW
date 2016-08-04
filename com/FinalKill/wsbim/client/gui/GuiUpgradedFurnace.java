package com.FinalKill.wsbim.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerUpgradedFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityUpgradedFurnace;
import com.FinalKill.wsbim.util.EnumFurnaceFunction;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

public class GuiUpgradedFurnace extends GuiContainer{

	private final IUpgradedFurnace furnace;
	
	public GuiUpgradedFurnace(InventoryPlayer player, IUpgradedFurnace furn) {
		super(new ContainerUpgradedFurnace(player, furn));
		furnace = furn;
		
		if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
			this.xSize = 176;
			this.ySize = 242;
		}
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 
    if(!(WSBIM.options.useAdvancedResourcePackSupport && (mc.gameSettings.resourcePacks.size() > 0))){
	Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
	this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
    else{
    	ResourceLocation back = new ResourceLocation("textures/gui/container/dispenser.png");
    	ResourceLocation furn = new ResourceLocation("textures/gui/container/furnace.png");
    	if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE){
    		Minecraft.getMinecraft().renderEngine.bindTexture(back);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(guiLeft+56, guiTop+5, 7, 5, 48, 68);
			this.drawTexturedModalRect(guiLeft+56+48, guiTop+5, 7, 5, 48, 68);
			Minecraft.getMinecraft().renderEngine.bindTexture(furn);
			this.drawTexturedModalRect(guiLeft+55, guiTop+37, 55, 37, 18, 33);
			this.drawTexturedModalRect(guiLeft+46, guiTop+16, 55, 16, 18, 18);
			this.drawTexturedModalRect(guiLeft+46+18, guiTop+16, 55, 16, 18, 18);
			this.drawTexturedModalRect(guiLeft+80, guiTop+35, 80, 35, 22, 15);
			this.drawTexturedModalRect(guiLeft+111, guiTop+30, 111, 30, 26, 26);
			this.drawTexturedModalRect(guiLeft+111+26, guiTop+30, 111, 30, 26, 26);
    	}
    	else if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE_FUNC){
    		Minecraft.getMinecraft().renderEngine.bindTexture(back);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(guiLeft+56, guiTop+5, 7, 5, 48, 68);
			this.drawTexturedModalRect(guiLeft+56+48, guiTop+5, 7, 5, 48, 68);
			Minecraft.getMinecraft().renderEngine.bindTexture(furn);
			this.drawTexturedModalRect(guiLeft+7, guiTop+41, 55, 37, 18, 33);
			this.drawTexturedModalRect(guiLeft+46, guiTop+21, 55, 16, 18, 18);
			this.drawTexturedModalRect(guiLeft+46, guiTop+56, 55, 16, 18, 18);
			this.drawTexturedModalRect(guiLeft+79, guiTop+23, 80, 35, 22, 15);
			this.drawTexturedModalRect(guiLeft+79, guiTop+58, 80, 35, 22, 15);
			this.drawTexturedModalRect(guiLeft+110, guiTop+18, 111, 30, 26, 26);
			this.drawTexturedModalRect(guiLeft+110, guiTop+53, 111, 30, 26, 26);
    	}
    	else{
    		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
    		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    	}
    }
    int k = (this.width - this.xSize) / 2;
    int l = (this.height - this.ySize) / 2;
    if(this.furnace.isBurning()){
    if ((furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE) || furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.NORMAL)
    {
        int i1 = this.furnace.getBurnTimeRemainingScaled(13);
        this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        i1 = this.furnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
    if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE_FUNC){
    	int i1 = this.furnace.getBurnTimeRemainingScaled(13);
        this.drawTexturedModalRect(k + 9, l + 41 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        i1 = this.furnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 78, l + 22, 176, 14, i1 + 1, 16);
        i1 = this.furnace.getCookProgress2Scaled(24);
        this.drawTexturedModalRect(k + 78, l + 57, 176, 14, i1 + 1, 16);
    }
    if(furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC){
    	int i1 = this.furnace.getBurnTimeRemainingScaled(13);
        this.drawTexturedModalRect(k + 9, l + 111 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        i1 = this.furnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 78, l + 22, 176, 14, i1 + 1, 16);
        i1 = this.furnace.getCookProgress2Scaled(24);
        this.drawTexturedModalRect(k + 78, l + 57, 176, 14, i1 + 1, 16);
        i1 = this.furnace.getCookProgress3Scaled(24);
        this.drawTexturedModalRect(k + 78, l + 92, 176, 14, i1 + 1, 16);
        i1 = this.furnace.getCookProgress4Scaled(24);
        this.drawTexturedModalRect(k + 78, l + 127, 176, 14, i1 + 1, 16);
        
	}
    }
	}
	
	 public void drawGuiContainerForegroundLayer(int p_adw73863_1_, int p_adw73863_2_){
		 String s = this.furnace.getFurnaceBlock().getLocalizedName();
	        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);

	 }
	 
	 private ResourceLocation getTexture(){
		 return furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE? new ResourceLocation(WSBIM.modid, "textures/gui/doubleFurnace.png") : (this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.DOUBLE_FUNC?new ResourceLocation(WSBIM.modid, "textures/gui/doubleFurnace2.png") :(this.furnace.getFurnaceType().getFurnaceFunction() == EnumFurnaceFunction.QUAD_FUNC? new ResourceLocation(WSBIM.modid, "textures/gui/quadFurnace.png"): new ResourceLocation("textures/gui/container/furnace.png")));
	 }
	 

}
