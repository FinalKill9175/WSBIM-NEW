package com.FinalKill.Machinery_Craft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockIndustrialIronBlock extends Block {

	protected BlockIndustrialIronBlock() {
		super(Material.iron);
		

		this.setHardness(5.0F);
	this.setResistance(50*10);
	}

}
