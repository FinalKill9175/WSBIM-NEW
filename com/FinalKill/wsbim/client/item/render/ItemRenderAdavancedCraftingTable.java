package com.FinalKill.wsbim.client.item.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import com.FinalKill.wsbim.common.tileentity.TileEntityAdvancedCraftingTable;

public class ItemRenderAdavancedCraftingTable implements IItemRenderer {

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		return true;
	}


	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityAdvancedCraftingTable(), 0.0D, -0.1D, 0.0D, 0.0F);
		
		
	}

}
