package com.FinalKill.wsbim.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.common.block.BlockUpgradedChest;

import cpw.mods.fml.common.registry.GameRegistry;

public class ChestRegistry {
	public static final List<TileEntity> tileList = new ArrayList<TileEntity>();
	public static final List<BlockUpgradedChest> chestList = new ArrayList<BlockUpgradedChest>();
	static final int cap = 1000;
	
	public static void prepDelegates(){
		for(int i = 0; i < cap; i++){
			tileList.add(0, null);
			chestList.add(i, null);
		}
	}
	
	public static void registerChest(int id, BlockUpgradedChest chest, TileEntity entity){
		if(tileList.get(id) == null && chestList.get(id) == null){
			tileList.set(id, entity);
			chestList.set(id, chest);
			GameRegistry.registerTileEntity(entity.getClass(), chest.getUnlocalizedName());
			GameRegistry.registerBlock(chest, chest.getUnlocalizedName());
		}
	}
	
	public static void registerChest(BlockUpgradedChest chest, TileEntity entity){
		registerChest(chest.getChestType().getID(), chest, entity);
	}
	
	public static TileEntity getChestTileEntityInstance(int id){
		if(tileList.get(id) !=null){
			try {
				return tileList.get(id).getClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static BlockUpgradedChest getChestBlock(int id){
		return chestList.get(id);
	}
}
