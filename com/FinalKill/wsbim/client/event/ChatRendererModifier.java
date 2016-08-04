package com.FinalKill.wsbim.client.event;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChatRendererModifier {
	@SubscribeEvent
	public void onChatRenderIngame(RenderGameOverlayEvent.Chat e){
		int offset = HUDOverlayRendererEvent.getChatOffsetY(WSBIM.options.currentTheme);
		if(offset !=0){
			e.posY = e.resolution.getScaledHeight()+offset;
		}
	}
}
