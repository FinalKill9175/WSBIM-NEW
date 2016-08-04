package com.FinalKill.Machinery_Craft.blocks;

import java.util.List;
import java.util.Random;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColoredStoneSlab extends BlockColoredStone {

	public boolean isFullBlock;
	
	protected BlockColoredStoneSlab(int stone, Material m) {
		super(stone, m);
		this.setBlockBounds(0, 0, 0, 1, 0.5F, 1);
	
	
	}
	/**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        if (this.isFullBlock)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }
	 public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	    {
	        if (this.isFullBlock)
	        {
	            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	        }
	        else
	        {
	            boolean var5 = (p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) & 8) != 0;

	            if (var5)
	            {
	                this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
	            }
	            else
	            {
	                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	            }
	        }
	    }
	 public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
	    {
	        this.setBlockBoundsBasedOnState(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_);
	        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
	    }

	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        return this.isFullBlock ? p_149660_9_ : (p_149660_5_ != 0 && (p_149660_5_ == 1 || (double)p_149660_7_ <= 0.5D) ? p_149660_9_ : p_149660_9_ | 8);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return this.isFullBlock ? 2 : 1;
    }
    public boolean renderAsNormalBlock()
    {
        return this.isFullBlock;
    }
	   public boolean isOpaqueCube()
	    {
	        return isFullBlock;
	    }
	   
	 
	  /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int p_149741_1_)
    {
  
    	Block[] coloredStones = MachineryCraft.blocks.coloredStoneSlab;
 
    	for(int i = 0; i<coloredStones.length; i++){
    		if(this == coloredStones[i]) return this.colors[i];
    		
    	}
    	return colors[1];
    
    }
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess blocks, int x, int y, int z)
    {
    
   
    	if(blocks.getBlock(x, y, z) instanceof BlockColoredStoneSlab){
    		
    		Block[] coloredStones = MachineryCraft.blocks.coloredStoneSlab;
    		
    		for(int i = 0; i<MachineryCraft.blocks.coloredStoneSlab.length; i++){
    			
    			if(blocks.getBlock(x, y, z).equals(coloredStones[i])){
    				
    				return this.colors[i];
    				
    			}
    			
    		}
    		
    	}
    	
    	
    	
		return 16777215;
    }

    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        if (this.isFullBlock)
        {
            return super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
        }
        else if (p_149646_5_ != 1 && p_149646_5_ != 0 && !super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_))
        {
            return false;
        }
        else
        {
            int var6 = p_149646_2_ + Facing.offsetsXForSide[Facing.oppositeSide[p_149646_5_]];
            int var7 = p_149646_3_ + Facing.offsetsYForSide[Facing.oppositeSide[p_149646_5_]];
            int var8 = p_149646_4_ + Facing.offsetsZForSide[Facing.oppositeSide[p_149646_5_]];
            boolean var9 = (p_149646_1_.getBlockMetadata(var6, var7, var8) & 8) != 0;
            return var9 ? (p_149646_5_ == 0 ? true : (p_149646_5_ == 1 && super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_) ? true : !func_150003_a(p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_)) || (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) & 8) == 0)) : (p_149646_5_ == 1 ? true : (p_149646_5_ == 0 && super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_) ? true : !func_150003_a(p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_)) || (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) & 8) != 0));
        }
    }

    private static boolean func_150003_a(Block p_150003_0_)
    {
        return p_150003_0_ == Blocks.stone_slab || p_150003_0_ == Blocks.wooden_slab;
    }
    public MapColor getMapColor(int p_149728_1_)
    {
    	Block[] coloredStones = MachineryCraft.blocks.coloredStoneSlab;
    	 
    	for(int i = 0; i<coloredStones.length; i++){
    		if(this == coloredStones[i]) return MapColor.getMapColorForBlockColored(i);
    		
    	}
    	return MapColor.clothColor;   
    	
    
    }
}
