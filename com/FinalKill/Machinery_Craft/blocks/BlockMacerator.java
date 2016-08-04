package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
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
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class BlockMacerator extends BlockMachinery{

	private static boolean keepInventory;

	protected BlockMacerator() {
		super(MachineryCraft.machinery);
		this.setStepSound(soundTypeMetal);
		this.setHardness(2.0F);
		this.setBlockBounds(0, 0, 0, 1, 1-1F/16F, 1);
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
	/**
	 * for (int la = 0; la < 3; ++la)
	        {
	            int l = par1World.getBlockMetadata(par2, par3, par4);
	            double d0 = (double)((float)par2 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
	            double d1 = ((double)((float)par3 + 0.7F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D)+0.5D;
	            double d2 = (double)((float)par4 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
	            double d3 = 0.2199999988079071D;
	            double d4 = 0.27000001072883606D;

	            if (l == 1)
	            {
	                par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 2)
	            {
	                par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 3)
	            {
	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 4)
	            {
	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
	            }
	            else
	            {
	                par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            }
        }
	 */
	
	/**
	 * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	
    	//p_150186_1_.getBlock(p_150186_2_, p_150186_3_, p_150186_4_).equals(CondenserMod.condenser_active)
        if (par1World.getBlock(par2, par3, par4).equals(MachineryCraft.blocks.macerator_active))
        {
        	for (int la = 0; la < 3; ++la)
	        {
	            int l = par1World.getBlockMetadata(par2, par3, par4);
	            double d0 = (double)((float)par2 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
	            double d1 = ((double)((float)par3 + 0.7F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D)+0.5D;
	            double d2 = (double)((float)par4 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
	            double d3 = 0.2199999988079071D;
	            double d4 = 0.27000001072883606D;

	            if (l == 1)
	            {
	                par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 2)
	            {
	                par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 3)
	            {
	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 4)
	            {
	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
	            }
	            else
	            {
	                par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            }
        }
        	
        	
        	
        	
        	if(par1World.isRaining()&& !this.checkOverheadBlocks(par1World, par2, par3, par4, 5)){
        		
        		for (int la = 0; la < 35; ++la)
    	        {
        			
    	            int l = par1World.getBlockMetadata(par2, par3, par4);
    	            double d0 = (double)((float)par2 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
    	            double d1 = ((double)((float)par3 + 0.7F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D)+0.5D;
    	            double d2 = (double)((float)par4 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.5D;
    	            double d3 = 0.2199999988079071D;
    	            double d4 = 0.27000001072883606D;

    	            if (l == 1)
    	            {
    	                par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    	            }
    	            else if (l == 2)
    	            {
    	                par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    	            }
    	            else if (l == 3)
    	            {
    	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
    	            }
    	            else if (l == 4)
    	            {
    	                par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
    	            }
    	            else
    	            {
    	                par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
    	            }
            }
        		
        	}
        }
        
    }
    
	/**
	 * Update which block ID the furnace is using depending on whether or not it is burning
	 */
	public static void updateMaceratorBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		keepInventory = true;

		if (par0)
		{
			par1World.setBlock(par2, par3, par4, MachineryCraft.blocks.macerator_active);
			
		}
		else
		{
			par1World.setBlock(par2, par3, par4,  MachineryCraft.blocks.macerator);
		}
		keepInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setTileEntity(par2, par3, par4, tileentity);
		}
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
	
    public static boolean checkOverheadBlocks(World world, int x, int y, int z, int heightToCheck){
    	
    	
    	for(int i = 1; i<=heightToCheck; i++){
    		if(world.getBlock(x, y+i, z) !=null){
    			
    			
    			return true;
    			
    			
    		}
    		
    		
    	}
    	return false;
    	
    	
    }
    
    
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {if (!keepInventory)
	{
      
            TileEntityMacerator var7 = (TileEntityMacerator)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
    }
	
	public TileEntity createNewTileEntity(World var1, int var2) {
	
		return new TileEntityMacerator();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote&&!player.isSneaking()){
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,MachineryCraft.guiMacerator, world, x, y, z);
			System.out.println(side);
			
			return true;
		}
		else{
				return false;
		}
		
		
		
	}
	
	

}
