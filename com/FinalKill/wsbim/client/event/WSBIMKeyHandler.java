package com.FinalKill.wsbim.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import org.lwjgl.input.Keyboard;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.gui.tabs.GuiTab;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.util.ContainerUtil;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WSBIMKeyHandler
{
/** Key index for easy handling */
public static final int BACKPACK = 0;
/** Key descriptions; use a language file to localize the description later */
private static final String[] desc = {"key.openBackpack.desc"};
/** Default key values */
private static final int[] keyValues = {Keyboard.KEY_B};
private final KeyBinding[] keys;
public WSBIMKeyHandler() {
keys = new KeyBinding[desc.length];
for (int i = 0; i < desc.length; ++i) {
keys[i] = new KeyBinding(desc[i], keyValues[i], "key.wsbim.category");
ClientRegistry.registerKeyBinding(keys[i]);
}
}
/**
* KeyInputEvent is in the FML package, so we must register to the FML event bus
*/
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		if(keys[BACKPACK].isPressed()){
			EntityPlayer player = this.getServerPlayerObject(Minecraft.getMinecraft());
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.inventory.getCurrentItem();
			if(Minecraft.getMinecraft().currentScreen == null && backpack !=null){
				if(backpack.getItem() instanceof ItemBackpack)
				//player.openGui(WSBIM.instance, 212, this.getServerWorldObj(Minecraft.getMinecraft()), 0, 0, 0);
				ContainerUtil.openGUI(212);
			}
		}

	}

	public EntityPlayer getServerPlayerObject(Minecraft mc){
		return this.getServerWorldObj(mc).getPlayerEntityByName(mc.thePlayer.getDisplayName());
	}
	
	public World getServerWorldObj(Minecraft mc){
		
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
			world = mc.theWorld;
		}
		
		
		
		return world;
	}
}




