package com.FinalKill.wsbim.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.util.IGuiContainerOverlay;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class OpenGUISoundEffect implements IGuiContainerOverlay {

	boolean played;
	
	public void setAdjustedGuiVars(boolean i) {
		played = i;
	}

	public boolean hasAdjustedGuiVars() {
		return this.played;
	}

	public boolean updateGuiSizes() {
		return true;
	}

	public void render(GuiContainer container_gui, float partialTick) {
		if(!this.played){
			Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("random.wood_click"), 1.0F));
			this.played = true;
		}
	}

	public boolean canRender(Minecraft mc, RenderTickEvent e, GuiContainer open_container) {
		if(!WSBIM.options.playOpenGUISound) return false;
		return true;
	}

	public boolean hasNoInventorySlots(Minecraft mc, GuiContainer open_container) {
		return true;
	}

}
