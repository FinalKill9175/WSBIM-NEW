package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockIronChest extends BlockContainer {

	




	public BlockIronChest(String unlocalized_name_and_tag) {
		super(Material.iron);
			
			this.setHardness(5.0F);
		this.setResistance(50);
		this.setBlockTextureName(unlocalized_name_and_tag);
		this.setBlockName(unlocalized_name_and_tag);
	
			GameRegistry.registerBlock(this, unlocalized_name_and_tag);
	
		
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote||player.isSneaking()){
			
			return false;
		}
		else{
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,0, world, x, y, z);
			return true;
		}
		
		
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityIronChest();
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

    
    
    
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
      
            TileEntityIronChest var7 = (TileEntityIronChest)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

            Random random = new Random();
            
            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = random.nextFloat() * 0.8F + 0.1F;
                        float var11 = random.nextFloat() * 0.8F + 0.1F;
                        float var12 = random.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = random.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + var10), (double)((float)p_149749_3_ + var11), (double)((float)p_149749_4_ + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)random.nextGaussian() * var15);
                            var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)random.nextGaussian() * var15);
                            p_149749_1_.spawnEntityInWorld(var14);
                        }
                    }
                }

                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
            }
        

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
    
    
public int getRenderType(){
		
		return 0;
	}
	
	public boolean isOpaqueCube(){
		
		return true;
	}
	
	public boolean renderAsNormalBlock(){
		
		return true;
	}


}
