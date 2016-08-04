package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColoredStone extends Block{
	 public static final String[] names = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};

	 public static final int black = 0;
	 public static final int red = 1;
	 public static final int green = 2;
	 public static final int brown = 3;
	 public static final int blue = 4;
	 public static final int purple = 5;
	 public static final int cyan = 6;
	 public static final int light_gray = 7;
	 public static final int dark_gray = 8;
	 public static final int pink = 9;
	 public static final int lime = 10;
	 public static final int yellow = 11;
	 public static final int light_blue = 12;
	 public static final int magenta = 13;
	 public static final int orange = 14;
	 public static final int white = 15;
	 
	 
	 public static final int[] colors = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

	 
	protected BlockColoredStone(int stone, Material m) {
		super(m);
		
	
	
		}
   
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockAdded(World world, int x, int y, int z){
   	
    	
		
	}
	
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }
    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int p_149741_1_)
    {
  
    	Block[] coloredStones = MachineryCraft.blocks.coloredStone;
 
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
    
   
    	if(blocks.getBlock(x, y, z) instanceof BlockColoredStone){
    		
    		Block[] coloredStones = MachineryCraft.blocks.coloredStone;
    		
    		for(int i = 0; i<MachineryCraft.blocks.coloredStone.length; i++){
    			
    			if(blocks.getBlock(x, y, z).equals(coloredStones[i])){
    				
    				return this.colors[i];
    				
    			}
    			
    		}
    		
    	}
    	
    	
    	
		return 16777215;
    }
    
    public MapColor getMapColor(int p_149728_1_)
    {
    	Block[] coloredStones = MachineryCraft.blocks.coloredStone;
    	 
    	for(int i = 0; i<coloredStones.length; i++){
    		if(this == coloredStones[i]) return MapColor.getMapColorForBlockColored(i);
    		
    	}
    	return MapColor.clothColor;   
    	
    
    }


}
