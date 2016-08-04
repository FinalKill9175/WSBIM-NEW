package com.FinalKill.wsbim.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.DimensionManager;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.gui.tabs.GuiTab;
import com.FinalKill.wsbim.common.net.PacketDispatcher;
import com.FinalKill.wsbim.common.net.server.OpenGui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ContainerUtil {
	/**
	 * STILL IS CLIENT END, but this does not matter for certain functions. Use a custom packet if the server side is absolutely needed.
	 * @param mc
	 * @return
	 */
	@Deprecated
	public static EntityPlayer getServerPlayerObject(Minecraft mc){
		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		//int dimension = player.dimension;
		//World world = DimensionManager.getWorld(dimension);
		//return world.getPlayerEntityByName(player.getCommandSenderName());
		//FMLCommonHandler.instance().
		//MinecraftServer.getServer().
		
		
		
		//return getServerWorldObj(mc).getPlayerEntityByName(mc.thePlayer.getCommandSenderName());
		return GuiTab.getServerPlayerObject(mc);
	}
	
	/**
	 * WARNING - NOT RELIABLE, will still base it off the client end.
	 * @param mc
	 * @return
	 */
	@Deprecated
	public static World getServerWorldObj(Minecraft mc){
		/**
		World world = null;
		
		if(MinecraftServer.getServer() !=null && MinecraftServer.getServer().worldServers !=null){
		WorldServer[] list = MinecraftServer.getServer().worldServers;
		for(WorldServer ins : list){
		if(ins.provider.dimensionId==mc.thePlayer.dimension)
			world = ins;
		}
		if(world==null && list.length > 0)
			world = list[0];
		}
		else if(mc.isIntegratedServerRunning()){
			WorldServer[] list = mc.getIntegratedServer().worldServers;
			for(WorldServer ins : list){
				if(ins.provider.dimensionId==mc.thePlayer.dimension)
					world = ins;
				}
				if(world==null && list.length > 0)
					world = list[0];
		}
		
		else{
			world = (World) mc.theWorld;
		}
		
		return world;*/
		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		//int dimension = player.dimension;
		//return DimensionManager.getWorld(dimension);
		//World worldServer = null;
		//if (mc.getIntegratedServer() !=null && mc.getIntegratedServer().isServerRunning())
		//{
		//    worldServer = mc.getIntegratedServer().worldServerForDimension(mc.thePlayer.dimension);
		//}/
		//else if (MinecraftServer.getServer() !=null && MinecraftServer.getServer().isServerRunning())
		//{
		//    worldServer = MinecraftServer.getServer().worldServerForDimension(mc.thePlayer.dimension);
		//}
		//return FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(mc.thePlayer.dimension);
		return GuiTab.getServerWorldObj(mc);
	}
	/**
	 * Used for opening a inventory GUI on the client side by sending a custom Gui open request packet to the integrated or dedicated server.
	 * Also, it automatically will open the gui from the gui handler in this mod already.
	 * I might add a parameter for your own instance of your mod maybe later.
	 * @param call - GUI-ID for the Gui Handler
	 */
	public static void openGUI(int call){
		EntityPlayer player = ContainerUtil.getServerPlayerObject(Minecraft.getMinecraft());
		World world = ContainerUtil.getServerWorldObj(Minecraft.getMinecraft());
		if(player !=null && world !=null){
			//if(!world.isRemote){
				OpenGui gui_packet = new OpenGui();
				gui_packet.id = call;
				//player.openGui(WSBIM.instance, call, world, 0,0,0);
				PacketDispatcher.sendToServer(gui_packet);
				//player.open
			//}
		}
	
	}
}
