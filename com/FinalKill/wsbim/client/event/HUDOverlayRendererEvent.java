package com.FinalKill.wsbim.client.event;

import java.lang.reflect.Field;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.ForgeHooks;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.item.ItemBackpack;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HUDOverlayRendererEvent extends Gui{

	
	RenderItem itemRenderer = new RenderItem();
	float partialTick;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(Minecraft.getMinecraft().thePlayer !=null && Minecraft.getMinecraft().theWorld !=null){
	
			
			
			GuiIngameForge.renderObjective = WSBIM.options.showScoreboard;
			GuiIngameForge.renderCrosshairs = WSBIM.options.showCrosshairs;
			this.partialTick = e.renderTickTime;
			if(WSBIM.options.currentTheme.equals("PVP")){
				this.disableVanillaUIElements();
				this.renderPvpTheme();
			}
			
			else if(WSBIM.options.currentTheme.equals("Bars")){
				this.disableVanillaUIElements();
				this.renderBarsTheme();
			}
			
			else{
				
				if(WSBIM.options.showArmorInHUD){
					this.renderArmorInHUDVanilla();
				}
				
				this.enableVanillaUIElements();
			}
		}
	}
	
	private void renderArmorInHUDVanilla() {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer renderPlayer = mc.thePlayer;
		boolean isSurvivalOrAdventure = mc.playerController.shouldDrawHUD();
		GuiIngameForge ingameGUI = (GuiIngameForge)mc.ingameGUI;
		GuiScreen currentGui = mc.currentScreen;
		ItemStack[] armorSlots = renderPlayer.inventory.armorInventory;
		ResourceLocation armorSlotsBackground = new ResourceLocation(WSBIM.modid, "textures/gui/hud/armor_hud.png");
		
		if(!mc.gameSettings.showDebugInfo && ((currentGui instanceof GuiChat) || currentGui == null) && isSurvivalOrAdventure && (armorSlots[0] !=null || armorSlots[1] !=null || armorSlots[2] !=null && !(armorSlots[2].getItem() instanceof ItemBackpack) || armorSlots[3] !=null)){
			int left = 5;
			int top = 5;
			GL11.glPushMatrix();
			GL11.glColor4f(1, 1, 1, 1);
			mc.renderEngine.bindTexture(armorSlotsBackground);
			this.drawTexturedModalRect(left, top, 0, 0, 30, 85);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();
			
			if(armorSlots[3] !=null){
				this.renderItemStack(armorSlots[3], left + 7, top + 7, this.partialTick);
			}
			
			if(armorSlots[2] !=null ){
				this.renderItemStack(armorSlots[2], left + 7, top + 25, this.partialTick);
			}
			
			if(armorSlots[1] !=null){
				this.renderItemStack(armorSlots[1], left + 7, top + 43, this.partialTick);
			}

			if(armorSlots[0] !=null){
				this.renderItemStack(armorSlots[0], left + 7, top + 61, this.partialTick);
			}
			
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	public static int getChatOffsetY(String theme){
		if(theme.equals("PVP")){
			return -65;
		}
		else{
			return 0;
		}
	}
	
	public void disableVanillaUIElements(){
		GuiIngameForge.renderAir = false;
		GuiIngameForge.renderArmor = false;
		GuiIngameForge.renderBossHealth = false;
		GuiIngameForge.renderExperiance = false;
		GuiIngameForge.renderFood = false;
		GuiIngameForge.renderHealthMount = false;
		GuiIngameForge.renderHealth = false;
		//For Horses
		GuiIngameForge.renderJumpBar = false;
		
	}
	
	public void enableVanillaUIElements(){
		GuiIngameForge.renderAir = true;
		GuiIngameForge.renderArmor = true;
		GuiIngameForge.renderBossHealth = true;
		GuiIngameForge.renderExperiance = true;
		GuiIngameForge.renderFood = true;
		GuiIngameForge.renderHealthMount = true;
		GuiIngameForge.renderHealth = true;
		//For Horses
		GuiIngameForge.renderJumpBar = true;
		GuiIngameForge.renderHotbar = true;
	}
	
	public void renderPvpTheme(){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer renderPlayer = mc.thePlayer;
		boolean isSurvivalOrAdventure = mc.playerController.shouldDrawHUD();
		GuiIngameForge ingameGUI = (GuiIngameForge)mc.ingameGUI;
		GuiScreen currentGui = mc.currentScreen;
		ItemStack[] inv = renderPlayer.inventory.mainInventory;
		ItemStack[] hotbar = new ItemStack[9];
		ItemStack[] armorSlots = renderPlayer.inventory.armorInventory;
		hotbar[0] = inv[0];
		hotbar[1] = inv[1];
		hotbar[2] = inv[2];
		hotbar[3] = inv[3];
		hotbar[4] = inv[4];
		hotbar[5] = inv[5];
		hotbar[6] = inv[6];
		hotbar[7] = inv[7];
		hotbar[8] = inv[8];
		ResourceLocation bars_texture = new ResourceLocation(WSBIM.modid, "textures/gui/hud/pvp_theme/bars.png");
		ScaledResolution res = (ScaledResolution) this.getObjectFromClass("res", GuiIngameForge.class, ingameGUI);
		//int updateCounter = this.getIntegerFromClass("updateCounter", GuiIngameForge.class, ingameGUI);
		int width = res !=null? res.getScaledWidth() : 0;
		int height = res !=null? res.getScaledHeight() : 0;
		float alpha = 1.0F;
		
	
		GuiIngameForge.renderHotbar = false;
		
		GL11.glPushMatrix();
		// see through - GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		if((mc.currentScreen instanceof GuiChat) || (mc.currentScreen instanceof GuiGameOver) || mc.currentScreen == null && !mc.gameSettings.showDebugInfo && !Keyboard.isKeyDown(Keyboard.KEY_TAB)){
			if(isSurvivalOrAdventure){
				//Health
				{
					//Backround
					GL11.glPushMatrix();
					this.drawRect(0, 0, 196, 45, this.getBackgroundColor());
					GL11.glPopMatrix();
					//Bar
					
					
					GL11.glPushMatrix();
					mc.renderEngine.bindTexture(bars_texture);
					GL11.glColor4f(1F, 1F, 1F, alpha);
					float player_health = renderPlayer.getHealth();
					int pixel = (int) ((int)player_health/20F*176);
					this.drawTexturedModalRect(10, 15, 0, 20, 176, 20);
					if(renderPlayer.getHealth() <= 20F){
						this.drawTexturedModalRect(10, 15, 0, 0, pixel, 20);
					}
					else{
						this.drawTexturedModalRect(10, 15, 0, 0, 176, 20);
					}
					this.drawString(mc.fontRenderer, "Health: "+(player_health <= 4 ? EnumChatFormatting.RED : "")+(int)(player_health / 1F)+" Hearts", 10, 5, 16777215);
					GL11.glPopMatrix();
				}
				
				//Armor
				{
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					mc.renderEngine.bindTexture(icons);
			        int left = 5;
			        int top = 55;
			        
			  
			        int level = ForgeHooks.getTotalArmorValue(renderPlayer);
			        
			        if(level > 0){
			            this.drawRect(0, 45, 95, 75, getBackgroundColor());
			        }
			        GL11.glColor4f(1, 1, 1, 1);
			        
			        for (int i = 1; level > 0 && i < 20; i += 2)
			        {
			            if (i < level)
			            {
			                drawTexturedModalRect(left, top, 34, 9, 9, 9);
			            }
			            else if (i == level)
			            {
			                drawTexturedModalRect(left, top, 25, 9, 9, 9);
			            }
			            else if (i > level)
			            {
			                drawTexturedModalRect(left, top, 16, 9, 9, 9);
			            }
			            left += 8;
			        }
			       
			        
			        GL11.glDisable(GL11.GL_BLEND);
					
					GL11.glPopMatrix();
				}
				
				//Hunger Bar
				{
					GL11.glColor4f(1,1,1,1);
					this.drawRect(width - 196, 0, width, 45, this.getBackgroundColor());

					int hunger = renderPlayer.getFoodStats().getFoodLevel();
					GL11.glPushMatrix();
					mc.renderEngine.bindTexture(bars_texture);
					GL11.glColor4f(1, 1, 1, 1);
					int pixel = (int) (176 - (((float)hunger / 20F) * 176));
					boolean flag = renderPlayer.isPotionActive(Potion.hunger.id);
					this.drawTexturedModalRect(width - 186, 15, 0, flag? 101 : 60, 176, 21);
					this.drawTexturedModalRect((width - 186) + pixel, 15, pixel, flag? 80 : 40, 176 - pixel, 21);
					this.drawString(mc.fontRenderer, "Hunger: "+(hunger <= 6 ? EnumChatFormatting.RED : "")+hunger, width - 186, 5, 16777215);
					GL11.glPopMatrix();
				}
				
				//Air
				{
					GL11.glPushMatrix();
					GL11.glColor4f(1,1,1,1);
					int air = renderPlayer.getAir();
					mc.renderEngine.bindTexture(icons);
					 GL11.glEnable(GL11.GL_BLEND);
					 GL11.glColor4f(1, 1, 1, 1);
				        int left = width - 5;
				        int top = 55;

				        if (mc.thePlayer.isInsideOfMaterial(Material.water))
				        {
				        	if(air > 0){
				        		this.drawRect(width - 95, 45, width, 75, this.getBackgroundColor());
				        	}
				        	
				        	GL11.glColor4f(1,1,1,1);
				            int full = MathHelper.ceiling_double_int((double)(air - 2) * 10.0D / 300.0D);
				            int partial = MathHelper.ceiling_double_int((double)air * 10.0D / 300.0D) - full;

				            for (int i = 0; i < full + partial; ++i)
				            {
				                drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
				            }
				        
				        }

				        GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}
				
				//Expirience
				{
					
					if(!(currentGui instanceof GuiChat)){
					 int cap = mc.thePlayer.xpBarCap();
					 int left = width/2 - 176/2;

			            mc.renderEngine.bindTexture(bars_texture);
			            
			            int top = height-12-5;
			            
			            if (cap > 0)
			            {
			                short barWidth = 176;
			                int filled = (int)(mc.thePlayer.experience * (float)(barWidth + 1));
			                
			                
			                drawTexturedModalRect(left, top, 0, 121+12, barWidth, 12);

			                if (filled > 0)
			                {
			                    drawTexturedModalRect(left, top, 0, 121, filled, 12);
			                }
			            }
			            
			            boolean flag1 = false;
		                int color = flag1 ? 16777215 : 8453920;
		                String text = "" + mc.thePlayer.experienceLevel;
		                int x = (width / 2) - (mc.fontRenderer.getStringWidth(text) / 2);
		                int y =  top + 3 ;
		                mc.fontRenderer.drawString(text, x + 1, y, 0);
		                mc.fontRenderer.drawString(text, x - 1, y, 0);
		                mc.fontRenderer.drawString(text, x, y + 1, 0);
		                mc.fontRenderer.drawString(text, x, y - 1, 0);
		                mc.fontRenderer.drawString(text, x, y, color);
					}
				}				
			}
			
			if(WSBIM.options.showArmorInHUD && isSurvivalOrAdventure){
				if(armorSlots[3] !=null){
					this.drawRect(0, 50 + (1 * 25), 25, 75+(1*25), this.getBackgroundColor());
				}
				if(armorSlots[2] !=null){
					this.drawRect(0, 50 + (2 * 25), 25, 75+(2*25), this.getBackgroundColor());
				}
				if(armorSlots[1] !=null){
					this.drawRect(0, 50 + (3 * 25), 25, 75+(3*25), this.getBackgroundColor());
				}
				if(armorSlots[0] !=null){
					this.drawRect(0, 50 + (4 * 25), 25, 75+(4*25), this.getBackgroundColor());
				}
			}
			
			//Hotbar
			{
				
				int sel = mc.thePlayer.inventory.currentItem;
				int left = (width/2) - ((20*9)/2) - 2;
				int top = (height) - 40 - 2 + (mc.thePlayer.capabilities.isCreativeMode && (!(currentGui instanceof GuiChat)) ? 12 : 0);
				
				
				GL11.glPushMatrix();
				
				GL11.glColor4f(1, 1, 1, 1);
				
				this.drawRect(left - 2, top, left+(20*9)+4, top + 20 + 2, -16777215);
				
				this.drawRect(left + 2 + (sel*20), top + 2, left + 2 + (sel*20) + 18, top + 2 + 18, -0x808080);

				RenderHelper.enableGUIStandardItemLighting();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				for(int i = 0; i < 9; i++){
					if(hotbar[i] !=null){
						this.renderItemStack(hotbar[i], left + 2 + (i*20) + 1, top + 2 + 1, this.partialTick);
					}
				}
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				RenderHelper.disableStandardItemLighting();
													
				GL11.glPopMatrix();
				
			}
			
			
			
			if(WSBIM.options.showArmorInHUD && isSurvivalOrAdventure){
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(1,1,1,1);
				
				//ARMOR DATA IN HUD
				int start_height = 75 + (25*4);
				int k = 0;
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				RenderHelper.enableGUIStandardItemLighting();
				for(int i = 0; i < 4; i++){
					ItemStack current = armorSlots[i];
					if(current !=null){
						//this.drawRect(0, 75 + (i * 25), 25, 100+(i*25), this.getBackgroundColor());
						this.renderItemStack(current, 4, 150 - (i * 25) + 5, this.partialTick);
						start_height -=25;
					}
				}
				RenderHelper.disableStandardItemLighting();
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
		
	}
	
	public int getBackgroundColor(){
		String b = WSBIM.options.themeBrightness;
		if(b.equals("Dark")){
			return -1342177280;
		}
		if(b.equals("Light")){
			return 1342177280;
		}
		else return 0;
	}
	
	 /**
     * Renders the specified item of the inventory slot at the specified location. Args: slot, x, y, partialTick
     */
    protected void renderItemStack(ItemStack p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_)
    {
     
        if (p_73832_1_ != null)
        {
            float f1 = (float)p_73832_1_.animationsToGo - p_73832_4_;

             
            if (f1 > 0.0F)
            {
                GL11.glPushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
                GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
            }

            itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), p_73832_1_, p_73832_2_, p_73832_3_);

            if (f1 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), p_73832_1_, p_73832_2_, p_73832_3_);
        }
    }
	
	public static Object getObjectFromClass(String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			return f.get(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getIntegerFromClass(String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			return f.getInt(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void renderBarsTheme(){
		
	}
}
