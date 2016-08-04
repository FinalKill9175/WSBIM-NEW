package com.FinalKill.Machinery_Craft.blocks;

import com.FinalKill.Machinery_Craft.tile.TileEntityChargingStation;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChargingStation extends BlockContainer {

	public BlockChargingStation() {
		super(Material.wood);
		 this.setHardness(2.0F);
	        this.setStepSound(soundTypeWood);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {

		return new TileEntityChargingStation();
	}

}
