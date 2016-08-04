package com.FinalKill.Machinery_Craft.blocks;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockColoredWood extends BlockColoredStone{

	protected BlockColoredWood(int wood) {
		super(wood, Material.wood);
		

		
		}
	 /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int p_149741_1_)
    {
  
    	Block[] coloredWoods = MachineryCraft.blocks.coloredWood;
 
    	for(int i = 0; i<coloredWoods.length; i++){
    		if(this == coloredWoods[i]) return this.colors[i];
    		
    	}
    	return colors[1];
    
    }
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess blocks, int x, int y, int z)
    {
    
   
    	if(blocks.getBlock(x, y, z) instanceof BlockColoredWood){
    		
    		Block[] coloredWoods = MachineryCraft.blocks.coloredWood;
    		
    		for(int i = 0; i<MachineryCraft.blocks.coloredWood.length; i++){
    			
    			if(blocks.getBlock(x, y, z).equals(coloredWoods[i])){
    				
    				return this.colors[i];
    				
    			}
    			
    		}
    		
    	}
    	
    	
    	
		return 16777215;
    }
    public MapColor getMapColor(int p_149728_1_)
    {
    	Block[] coloredStones = MachineryCraft.blocks.coloredWood;
    	 
    	for(int i = 0; i<coloredStones.length; i++){
    		if(this == coloredStones[i]) return MapColor.getMapColorForBlockColored(i);
    		
    	}
    	return MapColor.clothColor;   
    	
    
    }
}
