/**
 * 
 */
package com.bounty_hunter_85.WhatShouldBeInMinecraft.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.bounty_hunter_85.WhatShouldBeInMinecraft.Whatshouldbeinminecraft;

import cpw.mods.fml.common.IWorldGenerator;

/**
 * @author Jake
 *
 */
public class WorldGeneratorMixedMetalOre  implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//if(random.nextInt(1) == 0){
		for(int i = 0; i < 2; i++){
			int x =  chunkX * 16 + random.nextInt(16);
			int y = random.nextInt(12);
			int z =  chunkZ * 16 + random.nextInt(16);
		
			WorldGenMinable gen = new WorldGenMinable(Whatshouldbeinminecraft.blockMixedMetalOre, 3);
			gen.generate(world, random, x, y, z);
		}
		//}
		
		
		
	}

}
