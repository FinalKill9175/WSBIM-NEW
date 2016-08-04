package com.FinalKill.Machinery_Craft.world.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;

public class BiomeGenChestBiome extends BiomeGenBase {

	public BiomeGenChestBiome() {
		super(100);
		this.enableSnow = false;
		this.fillerBlock = Blocks.dirt;
		this.topBlock = Blocks.grass;
		this.flowers.clear();
		this.setTemperatureRainfall(0.7F, 0.9F);
		this.setBiomeName("Chest Biome");
		this.theBiomeDecorator.treesPerChunk = 0;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.clayGen = null;
		this.theBiomeDecorator.cactiPerChunk =0;
		this.theBiomeDecorator.mushroomsPerChunk =0;
		this.theBiomeDecorator.waterlilyPerChunk = 0;
		this.theBiomeDecorator.bigMushroomsPerChunk = 0;
		this.rootHeight = 0.3F;
		this.heightVariation = 0.5F;
		
		}
	
	   /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return this.forest.getBiomeGrassColor(p_150558_1_, p_150558_2_, p_150558_3_);
    }
    
    public int getFoliageColor(int x, int y, int z){
    	
    	return this.forest.getBiomeFoliageColor(x, y, z);
    }
    
    public void decorate(World world, Random rand, int par3, int par4)
    {
     
    	if(rand.nextInt(40) == 0){
    		
    		int xCoord = par3+ rand.nextInt(8) - rand.nextInt(4);
    		int zCoord = par4+rand.nextInt(8) - rand.nextInt(4);
    		int yCoord = world.getHeightValue(xCoord, zCoord);
    		
    		(new WorldGeneratorBonusChest(new WeightedRandomChestContent[rand.nextInt(10)], yCoord)).generate(world, rand, xCoord, yCoord, zCoord);
    		
    	}
    	
    	super.decorate(world, rand, par3, par4);
    }
}
