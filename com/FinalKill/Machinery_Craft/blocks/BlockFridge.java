package com.FinalKill.Machinery_Craft.blocks;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityFridge;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFridge extends BlockContainer {

	protected BlockFridge() {
		super(MachineryCraft.machinery);
		
		this.setHardness(3.0F);
		this.setResistance(100);
		this.setStepSound(soundTypeMetal);
		this.setBlockBounds(0, 0, 0, 1, 2, 1);
		
		}
public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityFridge();
	}

}
