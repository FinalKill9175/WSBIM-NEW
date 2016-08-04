package com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen;

import java.util.Random;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorWetSand implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 256; j++){
				for(int k = 0; k < 16; k++){
					int x1 = i + x;
					int y = j;
					int z1 = z + k;
					if(world.getBlock(x1, y, z1) !=null){
						if(world.getBlock(x1, y, z1) == Blocks.sand){
							if(this.isUnderWater(world, x1, y, z1, 10) ||(world.getBlock(x1+1, y, z1) == Blocks.water || world.getBlock(x1+1, y, z1) == Blocks.flowing_water)||(world.getBlock(x1-1, y, z1) == Blocks.water || world.getBlock(x1-1, y, z1) == Blocks.flowing_water)||(world.getBlock(x1, y, z1+1) == Blocks.water || world.getBlock(x1, y, z1+1) == Blocks.flowing_water)||(world.getBlock(x1, y, z1-1) == Blocks.water || world.getBlock(x1, y, z1-1) == Blocks.flowing_water)){
								world.setBlock(x1, y, z1, Whatshouldbeinminecraft.blockWetSand);
							}
						}
					}
				}
			}
		}
	}
	
	
	public boolean isUnderWater(World world, int x, int y, int z, int max_depth){
		for(int i = 0; i < max_depth; i++){
			if(world.getBlock(x, y + i, z) !=null){
				if(world.getBlock(x, y + i, z) == Blocks.water || world.getBlock(x, y + i, z) == Blocks.flowing_water){
					return true;
				}
			}
		}
		return false;
	}
}
