package com.FinalKill.Machinery_Craft.blocks;

import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlockTable extends BlockContainer {

	
	
	public BlockTable(Material p_i45394_1_, float hardness, float resistance, SoundType sound) {
		super(p_i45394_1_);
		setHardness(hardness);
		setResistance(resistance);
		setStepSound(sound);
		

	}
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        byte var7 = 0;
        int var8 = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var8 == 0)
        {
            var7 = 2;
        }

        if (var8 == 1)
        {
            var7 = 5;
        }

        if (var8 == 2)
        {
            var7 = 3;
        }

        if (var8 == 3)
        {
            var7 = 4;
        }
       
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, var7, 2);
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTable();
	}
	
public int getRenderType(){
		
		return -1;
	}
	
	public boolean isOpaqueCube(){
		
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		
		return false;
	}
	
}
