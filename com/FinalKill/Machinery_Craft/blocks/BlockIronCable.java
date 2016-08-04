package com.FinalKill.Machinery_Craft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FinalKill.MachineryCraftAPI.power.BlockConnectable;
import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronCable;

public class BlockIronCable extends BlockConnectable {

	protected BlockIronCable() {
		super(MachineryCraft.cabels);
		this.setStepSound(soundTypeCloth);
		float pixel = 1F/16F;
		this.setHardness(1.0F);
		this.setBlockBounds(11*pixel/2, 11*pixel/2,11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2);
		this.setResistance(10);
		}
	
	
    
    
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityIronCable();
	}

public int getRenderType(){
		
		return -1;
	}
	
	public boolean isOpaqueCube(){
		
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		
		return false;
	}
}
