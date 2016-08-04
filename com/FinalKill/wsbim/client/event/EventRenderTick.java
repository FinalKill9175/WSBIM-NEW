package com.FinalKill.wsbim.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

import com.FinalKill.wsbim.client.gui.GuiContainerOverlayTabs;
import com.FinalKill.wsbim.client.util.GuiContainerOverlayRegistry;
import com.FinalKill.wsbim.client.util.IGuiContainerOverlay;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EventRenderTick {
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END){
			if(Minecraft.getMinecraft().currentScreen !=null && Minecraft.getMinecraft().currentScreen instanceof GuiContainer){
				for(int i = 0; i < GuiContainerOverlayRegistry.overlays.size(); i++){
					IGuiContainerOverlay overlay = GuiContainerOverlayRegistry.getOverlay(i);
					GuiContainer gui = (GuiContainer)Minecraft.getMinecraft().currentScreen;
					if(overlay !=null){
						if(!overlay.hasAdjustedGuiVars() && overlay instanceof GuiContainerOverlayTabs){
							((GuiContainerOverlayTabs)overlay).updateSizes(gui);
						}
						if(overlay.canRender(Minecraft.getMinecraft(), e, gui)){
							overlay.render(gui, e.renderTickTime);
						}
						
					
						else{
							overlay.setAdjustedGuiVars(false);
						}
					}
				}
			}
			else{
				for(int i = 0; i < GuiContainerOverlayRegistry.overlays.size(); i++){
					IGuiContainerOverlay overlay = GuiContainerOverlayRegistry.getOverlay(i);
					if(overlay !=null){
						overlay.setAdjustedGuiVars(false);
					}
				}
			}
		}	
	}
}
