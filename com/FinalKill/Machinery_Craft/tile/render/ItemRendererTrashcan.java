package com.FinalKill.Machinery_Craft.tile.render;

import com.FinalKill.Machinery_Craft.model.ModelTrashcan;
import com.FinalKill.Machinery_Craft.tile.TileEntityTable;
import com.FinalKill.Machinery_Craft.tile.TileEntityTrashcan;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemRendererTrashcan implements IItemRenderer {

private ModelTrashcan model ;
	
	public ItemRendererTrashcan(){
		
		model = new ModelTrashcan();
		
	}
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
	
		return true;
	}


	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTrashcan(), 0.0D, -0.1D, 0.0D, 0.0F);
		
		
	}
}
