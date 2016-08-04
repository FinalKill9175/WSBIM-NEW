package com.FinalKill.Machinery_Craft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorMachineryCraftOre implements IWorldGenerator {

	private Block block;
	private int rarity;
	private int startX;
	private int startY;
	private int startZ;
	private int vein_size;
	private boolean use_rand_on_vein_size;
	private int dimension;
	private boolean generate_in_all_dimensions;
	private int base_vein_size;
	
	
	public WorldGeneratorMachineryCraftOre(Block blocks, int rarity, int startX, int startY, int startZ, int vein_size, boolean useRandOnVeinSize, int baseVeinSize, int worldID, boolean generateInAllDimensions) {

		this.block = blocks;
		this.rarity = rarity;
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.vein_size = vein_size;
		this.use_rand_on_vein_size = useRandOnVeinSize;
		this.dimension = worldID;
		this.generate_in_all_dimensions = generateInAllDimensions;
		this.base_vein_size = baseVeinSize;
		//ex. 8 - rarity;
		//16, 50-startX, 16
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider provider, IChunkProvider provider2) {

		if(!this.generate_in_all_dimensions){
			if(world.provider.dimensionId == this.dimension){
			//generateOverworld(this.block, world, rand, chunkX * 16, chunkZ * 16, this.rarity, this.startX, this.startY, this.startZ,this.vein_size, this.use_rand_on_vein_size, this.base_vein_size);
			}
		}
		else{
			//generateOverworld(this.block, world, rand, chunkX * 16, chunkZ * 16, this.rarity, this.startX, this.startY, this.startZ,this.vein_size, this.use_rand_on_vein_size, this.base_vein_size);
	
			
		}
		
		
		
	}

	private void generateOverworld(Block block, World world, Random rand, int chunkX, int chunkZ, int rarity, int startX, int startY, int startZ, int rand_vein_size, boolean randomizeVeins, int base_vein_size) {
		
		for(int i = 0; i<rarity; i++){
			int xCoord = chunkX+rand.nextInt(startX);
			int yCoord = rand.nextInt(startY);
			int zCoord = chunkZ+rand.nextInt(startZ);
			int vein_size_gen = 0;
			
			if(randomizeVeins){
				vein_size_gen = rand.nextInt(rand_vein_size)+base_vein_size;
				
			}
			else{
				vein_size_gen = base_vein_size;
			}
			
			new WorldGenMinable(block, vein_size_gen).generate(world, rand, xCoord, yCoord, zCoord);
		}
		
	}

}
