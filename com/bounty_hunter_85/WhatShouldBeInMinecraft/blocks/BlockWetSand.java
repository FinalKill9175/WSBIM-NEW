package com.bounty_hunter_85.WhatShouldBeInMinecraft.blocks;


import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWetSand extends BlockFalling {

	public BlockWetSand(Material p_i45394_1_) {
		super();
		this.setTickRandomly(true);
		
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon[] icons = new IIcon[3]; 

	 
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.icons[meta] !=null? this.icons[meta] : this.blockIcon;
    }
	
	
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
       super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
       this.update(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
    }

    
    public void update(World world, int x, int y, int z, Random rand){
    	if(!world.isRemote){
    		boolean isTopBlock = world.getHeightValue(x, z) == y;
    			if(isTopBlock){
    				if(world.isRaining()){
    						if(world.getBlockMetadata(x, y, z) < 2){
    							world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 2);
    						}
    				}
    			}
    		
    	}
    }
    
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
    	super.onBlockAdded(world, x, y, z);
    	if(!world.isRemote){
    		if(world.getBlock(x, y, z) == this){
    			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
    		}
    	}
    	
    }

    
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        this.blockIcon = ir.registerIcon(this.getTextureName());
        	this.icons[0] = ir.registerIcon(getTextureName() + "_"+0); 
        	this.icons[1] = ir.registerIcon(getTextureName() + "_"+1); 
        	this.icons[2] = this.blockIcon;
        	
        
        
    }
    
}
