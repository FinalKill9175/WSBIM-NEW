package com.FinalKill.wsbim.client.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.ChatRendererModifier;
import com.FinalKill.wsbim.client.event.EventRenderTick;
import com.FinalKill.wsbim.client.event.GuiButtonEvent;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;
import com.FinalKill.wsbim.client.event.OpenGUISoundEffect;
import com.FinalKill.wsbim.client.event.WSBIMKeyHandler;
import com.FinalKill.wsbim.client.gui.GuiContainerOverlayTabs;
import com.FinalKill.wsbim.client.item.render.ItemRenderChest;
import com.FinalKill.wsbim.client.item.render.ItemRendererTileEntity;
import com.FinalKill.wsbim.client.tile.render.TileEntityCondenserRenderer;
import com.FinalKill.wsbim.client.tile.render.TileEntityFreezerRenderer;
import com.FinalKill.wsbim.client.tile.render.TileEntityUpgradedChestRenderer;
import com.FinalKill.wsbim.client.util.GuiContainerOverlayRegistry;
import com.FinalKill.wsbim.client.util.IGuiContainerOverlay;
import com.FinalKill.wsbim.common.block.BlockUpgradedChest;
import com.FinalKill.wsbim.common.proxy.CommonProxy;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;
import com.FinalKill.wsbim.util.ChestRegistry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy{
	
		
	public void registerProxies(){
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedCraftingTable.class, new TileEntityAdvancedCraftingTableRenderer());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(WSBIM.blockAdvancedCraftingTable), new ItemRenderAdavancedCraftingTable());
		GuiContainerOverlayTabs overlay = new GuiContainerOverlayTabs();
		FMLCommonHandler.instance().bus().register(new WSBIMKeyHandler());
		
		FMLCommonHandler.instance().bus().register(new EventRenderTick());
		this.registerGuiContainerOverlayRenderer(overlay);
		this.registerGuiContainerOverlayRenderer(new OpenGUISoundEffect());
		
		FMLCommonHandler.instance().bus().register(new GuiButtonEvent());
		FMLCommonHandler.instance().bus().register(new HUDOverlayRendererEvent());
		FMLCommonHandler.instance().bus().register(overlay);
		MinecraftForge.EVENT_BUS.register(new ChatRendererModifier());
		
		this.registerTileEntityRenderer(TileEntityFreezer.class, WSBIM.blockFreezerIdle, new TileEntityFreezerRenderer(), true);
		
		if(Loader.isModLoaded("condenser_values_api")){
			this.registerTileEntityRenderer(com.FinalKill.wsbim.common.tileentity.TileEntityCondenser.class, WSBIM.blockCondenser, new TileEntityCondenserRenderer(), false);
		}
		
		this.registerChestRenderer(WSBIM.blockIronChest);
		this.registerChestRenderer(WSBIM.blockGoldChest);
		this.registerChestRenderer(WSBIM.blockDiamondChest);
		this.registerChestRenderer(WSBIM.blockObsidianChest);
		this.registerChestRenderer(WSBIM.blockMixedMetalChest);
		this.registerChestRenderer(WSBIM.blockEmeraldChest);
		this.registerChestRenderer(WSBIM.blockCopperChest);
	}
	
	public static void registerChestRenderer(BlockUpgradedChest chest){
		TileEntity tile = ChestRegistry.getChestTileEntityInstance(chest.getChestType().getID());
		ClientRegistry.bindTileEntitySpecialRenderer(tile.getClass(), new TileEntityUpgradedChestRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(chest), new ItemRenderChest(chest));

	}
	
	public static void registerGuiContainerOverlayRenderer(IGuiContainerOverlay overlay){
		GuiContainerOverlayRegistry.addOverlay(overlay);
	}
	/**Registers a tile entity renderer and an item renderer for you. No need to make one yourself. :D*/
	public static void registerTileEntityRenderer(Class<?extends TileEntity> c, Block block, TileEntitySpecialRenderer render, boolean b){
		ClientRegistry.bindTileEntitySpecialRenderer(c, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new ItemRendererTileEntity(c));
		if(b){
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(WSBIM.blockFreezerActive), new ItemRendererTileEntity(c));
		}
	}
	// In your client proxy:
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
	 // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
	 // your packets will not work because you will be getting a client
	 // player even when you are on the server! Sounds absurd, but it's true.

	 // Solution is to double-check side before returning the player:
	 return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
