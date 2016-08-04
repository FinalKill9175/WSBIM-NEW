package com.FinalKill.wsbim.client.item.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import com.FinalKill.wsbim.common.block.BlockUpgradedChest;
import com.FinalKill.wsbim.common.tileentity.TileEntityAdvancedCraftingTable;
import com.FinalKill.wsbim.util.ChestRegistry;

public class ItemRenderChest implements IItemRenderer {
	
	private final BlockUpgradedChest t;
	
	public ItemRenderChest(BlockUpgradedChest t){
		this.t = t;
	}
	
public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		return true;
	}


	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(ChestRegistry.getChestTileEntityInstance(t.getChestType().getID()), 0.0D, -0.1D, 0.0D, 0.0F);
		
		
	}
}
