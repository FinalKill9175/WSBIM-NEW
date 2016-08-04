package com.FinalKill.Machinery_Craft.tile.render;

import com.FinalKill.Machinery_Craft.model.ModelTable;
import com.FinalKill.Machinery_Craft.tile.TileEntityStoneTable;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererStoneTable implements IItemRenderer {
private ModelTable model ;
	
	public ItemRendererStoneTable(){
		
		model = new ModelTable();
		
	}
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityStoneTable(), 0.0D, -0.1D, 0.0D, 0F);

	}

public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
	
		return true;
	}

}
