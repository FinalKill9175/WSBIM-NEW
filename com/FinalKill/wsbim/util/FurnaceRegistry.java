package com.FinalKill.wsbim.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.FinalKill.wsbim.common.block.BlockUpgradedFurnace;

public class FurnaceRegistry {

	public static final List idleFurnaces = new ArrayList<Block>();
	public static final List activeFurnaces = new ArrayList<Block>();
	public static final List furnaceTiles = new ArrayList<TileEntity>();
	
	static final int cap = 100;
	
	public static void prepDelegates(){
		for(int i = 0; i < cap; i++){
			idleFurnaces.add(null);
			activeFurnaces.add(null);
			furnaceTiles.add(null);
		}
	}
	
	/**Registers a furnace to the registry, used for things in the BlockUpgradedFurnace class.*/
	public static void registerFurnace(int id, BlockUpgradedFurnace idle, BlockUpgradedFurnace active, TileEntity furnaceTE){
		if(idleFurnaces.get(id) == null && activeFurnaces.get(id) == null){
			idleFurnaces.set(id, idle);
			activeFurnaces.set(id, active);
			furnaceTiles.set(id, furnaceTE);
			System.out.println("Registered a furnace with the furnace type "+idle.getFurnaceType()+" with the id "+id);
		}
		else{
			System.err.println("Failed to register a furnace with the id "+ id + " because the id is already used.");
		}
	}
	
	public static ItemStack getInactiveBlockForEnum(EnumFurnaceType t){
		return new ItemStack((Block) idleFurnaces.get(t.getID()));
	}
}
