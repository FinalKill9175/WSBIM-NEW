package com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorMudPit  implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(random.nextInt(6) == 0){
			if(random.nextInt(3) == 0){
				int x = chunkX * 16 + random.nextInt(6);
				int z = chunkZ * 16 + random.nextInt(6);
				int y = world.getHeightValue(x, z);
				if(world.getBiomeGenForCoords(x, z).topBlock.getMaterial() == Material.grass){
					WorldGenerator gen = new WorldGenMudPit(Whatshouldbeinminecraft.blockMud);
					gen.generate(world, random, x, y, z);
				}	
			}
		}
	}

}
