package com.FinalKill.wsbim.client.item.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererTileEntity implements IItemRenderer{
	
	private Class<?extends TileEntity> te_class;
	
	public ItemRendererTileEntity(Class<?extends TileEntity> clazz){
		te_class = clazz;
	}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		try {
			TileEntityRendererDispatcher.instance.renderTileEntityAt(te_class.newInstance(), 0.0, -0.1, 0.0, 0F);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
