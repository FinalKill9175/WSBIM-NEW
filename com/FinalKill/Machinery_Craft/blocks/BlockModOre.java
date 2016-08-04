package com.FinalKill.Machinery_Craft.blocks;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class BlockModOre extends Block{
	
	public final float hardness;
	public final float wood_hardness;
	public final float stone_hardness;
	public final float iron_hardness;
	public final float diamond_hardness;
	public final float gold_hardness;

	protected BlockModOre(float hardness,float woodHardness, float stoneHardness, float ironHardness, float diamondHardness, float goldHardness) {
		super(Material.rock);

		this.hardness=hardness;
		wood_hardness = woodHardness;
		stone_hardness = stoneHardness;
		iron_hardness = ironHardness;
		diamond_hardness = diamondHardness;
		gold_hardness = goldHardness;
		this.setStepSound(soundTypeStone);
		this.setHardness(hardness);
	}

	


	 /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World w, int x, int y, int z, int side, EntityPlayer p) {
    	
    	
    	if(w.getBlock(x, y, z).equals(MachineryCraft.blocks.copper_ore)){
    		
    		p.addStat(MachineryCraft.copper, 1);
    		
    	}
if(w.getBlock(x, y, z).equals(MachineryCraft.blocks.tin_ore)){
    		
    		p.addStat(MachineryCraft.tin, 1);
    		
    	}
    	
    
    }

	
}
