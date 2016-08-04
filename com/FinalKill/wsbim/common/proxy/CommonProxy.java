package com.FinalKill.wsbim.common.proxy;

import java.awt.event.InputMethodListener;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;

import com.FinalKill.wsbim.common.event.TickPlayerEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

	public void registerProxies(){
	
	}
	
	// In your server proxy (mine is named CommonProxy):
	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
	 return ctx.getServerHandler().playerEntity;
	}

	/**
	 * Returns the current thread based on side during message handling,
	 * used for ensuring that the message is being handled by the main thread
	 */
	public WorldServer getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity.getServerForPlayer();
	}
	
}
