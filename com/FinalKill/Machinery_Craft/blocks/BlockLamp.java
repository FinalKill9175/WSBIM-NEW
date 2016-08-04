package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.wsbim.WSBIM;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockLamp extends Block {

	public String[] lamp_names = {"redLamp","redLamp_lit","orangeLamp","orangeLamp_lit", "yellowLamp","yellowLamp_lit", "lightGreenLamp", "lightGreenLamp_lit", "greenLamp", "greenLamp_lit", "blueLamp", "blueLamp_lit", "purpleLamp", "purpleLamp_lit"};
	
	public static int[] lit_lamps = {1,3,5,7,9,11,13};
	public static int[] reg_lamps = {0,2,4,6,8,10,12};
	
	private Block blockToDrop;
	
	
	public BlockLamp(int lamp) {
		super(Material.iron);

		
	if(lamp_names[lamp].endsWith("_lit")){
		
		this.setLightLevel(0.9F);
		this.setLightOpacity(10);

		
	}
	else{
		this.setCreativeTab(WSBIM.main.ourTab);
	}
	
	
		this.blockToDrop = this;
	
	this.setHardness(1.45F);
	this.setResistance(25);
	this.setBlockName(lamp_names[lamp]);
	this.setBlockTextureName("wsbim_machinery"+":"+lamp_names[lamp]);
	
	
	
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		  if (!world.isRemote)
          {
               
               if (world.isBlockIndirectlyGettingPowered(x,y,z) && isLamp(world.getBlock(x, y, z)))
                  {
                          
            	   if(getLitLamp(world.getBlock(x, y, z))!=0){
            		   
            		   world.setBlock(x, y, z, MachineryCraft.blocks.lamps[getLitLamp(world.getBlock(x, y, z))]);
            		   this.blockToDrop = MachineryCraft.blocks.lamps[this.getUnLitLamp(world.getBlock(x, y, z))];
            	   }
            	   
                  }
               else if(isLamp(world.getBlock(x, y, z))){
            	   
            	   world.setBlock(x, y, z, MachineryCraft.blocks.lamps[getUnLitLamp(world.getBlock(x, y, z))]);
            	   this.blockToDrop = world.getBlock(x, y, z);

               }
              
          }
		
	}
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
    	this.onNeighborBlockChange(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_));
    	
    }

	
	public int getLitLamp(Block lamp){
		for(int i =0; i<14; i++){
			Block[] lamps = MachineryCraft.blocks.lamps;
				if(lamp == lamps[i]){
					
					return lit_lamps[i/2];
				}
				
				
			
		}
		return 0;
		
	}
	public int getUnLitLamp(Block lamp){
		for(int i =0; i<14; i++){
			Block[] lamps = MachineryCraft.blocks.lamps;
				if(lamp == lamps[i]){
					
					return reg_lamps[i/2];
				}
				
				
			
		}
		return 0;
	
		
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this.blockToDrop);
    }
	public boolean isLamp(Block block){
		
		for(int i =0; i<14; i++){
			Block[] lamps = MachineryCraft.blocks.lamps;
			if(lamps[i] !=null){
				if(block == lamps[i]){
					
					return true;
				}
				
				
			}
			
			
		}
		return false;
		
	}
	
}
