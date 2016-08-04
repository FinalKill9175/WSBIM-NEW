package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.texture.IIconRegister;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerArmorInfo;

public class GuiArmorInfo extends GuiContainer {

	EntityPlayer player;
	private static ResourceLocation texture = new ResourceLocation(WSBIM.modid, "textures/gui/tab.png"); 
	private static ResourceLocation inv = new ResourceLocation("textures/gui/container/inventory.png");
	private static ResourceLocation background = new ResourceLocation("textures/gui/container/dispenser.png");
	 /**
     * x size of the inventory window in pixels. Defined as  float, passed as int
     */
    private float xSizeFloat;
    /**
     * y size of the inventory window in pixels. Defined as  float, passed as int.
     */
    private float ySizeFloat;
	
	public GuiArmorInfo(EntityPlayer player) {
		super(new ContainerArmorInfo(player));
		this.player = player;
	}

	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
		IIconRegister reg = mc.getTextureMapBlocks();
		IIcon empty_helmet = reg.registerIcon("stone");
		
		
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		if(WSBIM.options.useAdvancedResourcePackSupport && (mc.gameSettings.resourcePacks.size() > 0)){
		Minecraft.getMinecraft().renderEngine.bindTexture(this.background);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
		this.drawTexturedModalRect(guiLeft+56, guiTop+5, 7, 5, 48, 68);
		this.drawTexturedModalRect(guiLeft+56+48, guiTop+5, 7, 5, 48, 68);
		}
		else{
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(inv);
		this.drawTexturedModalRect(guiLeft + (176 / 2 - (72/2)), guiTop + 7, 7, 7, 72, 72);
		drawPlayerModel(guiLeft + (176 / 2 + (72/8)), guiTop + 75, 30, (float)(guiLeft + (176 / 2 + (72/8))) - this.xSizeFloat, (float)(guiTop + 75 - 50) - this.ySizeFloat, this.mc.thePlayer);
		GL11.glPopMatrix();
	}
	
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        this.xSizeFloat = (float)p_73863_1_;
        this.ySizeFloat = (float)p_73863_2_;
        
    }
    
	public static void drawPlayerModel(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
        GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = p_147046_5_.renderYawOffset;
        float f3 = p_147046_5_.rotationYaw;
        float f4 = p_147046_5_.rotationPitch;
        float f5 = p_147046_5_.prevRotationYawHead;
        float f6 = p_147046_5_.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
        p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
        p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
        p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
        p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
        GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        p_147046_5_.renderYawOffset = f2;
        p_147046_5_.rotationYaw = f3;
        p_147046_5_.rotationPitch = f4;
        p_147046_5_.prevRotationYawHead = f5;
        p_147046_5_.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		
	}

}
