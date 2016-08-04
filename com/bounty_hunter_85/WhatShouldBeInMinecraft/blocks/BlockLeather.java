package com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLeather extends Block {

	public BlockLeather(Material material) {
		super(material);
		
		this.setHardness(0.8F);
		this.setResistance(4.0F);
		this.setStepSound(soundTypeCloth);
		}
	
	

}
