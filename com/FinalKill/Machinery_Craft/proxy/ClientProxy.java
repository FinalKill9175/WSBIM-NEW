package com.FinalKill.Machinery_Craft.proxy;


import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronCable;
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;
import com.FinalKill.Machinery_Craft.tile.TileEntityTable;
import com.FinalKill.Machinery_Craft.tile.TileEntityTrashcan;
import com.FinalKill.Machinery_Craft.tile.render.ItemRendererMacerator;
import com.FinalKill.Machinery_Craft.tile.render.ItemRendererTable;
import com.FinalKill.Machinery_Craft.tile.render.ItemRendererTrashcan;
import com.FinalKill.Machinery_Craft.tile.render.TileEntityRenderCable;
import com.FinalKill.Machinery_Craft.tile.render.TileEntityRenderMacerator;
import com.FinalKill.Machinery_Craft.tile.render.TileEntityRenderRainCollector;
import com.FinalKill.Machinery_Craft.tile.render.TileEntityRenderTable;
import com.FinalKill.Machinery_Craft.tile.render.TileEntityRenderTrashcan;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
public void registerProxies() {
	
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronChest.class, new TileEntityRendererIronChest());
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TileEntityRenderTable(new ResourceLocation(MachineryCraft.MODID.toLowerCase(),"textures/model/wooden_table.png")));
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MachineryCraft.blocks.woodenTable), new ItemRendererTable());
	
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashcan.class, new TileEntityRenderTrashcan(new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/model/Trashcan.png")));
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MachineryCraft.blocks.trashCan), new ItemRendererTrashcan());
	//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStoneTable.class, new TileEntityRenderStoneTable(new ResourceLocation(MachineryCraft.MODID.toLowerCase(),"textures/model/stone_table.png")));
	//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MachineryCraft.stoneTable), new ItemRendererStoneTable());
	
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMacerator.class, new TileEntityRenderMacerator());
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MachineryCraft.blocks.macerator), new ItemRendererMacerator());

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronCable.class, new TileEntityRenderCable(new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/model/iron_cable.png")));

	
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRainCollector.class, new TileEntityRenderRainCollector());

}
}
