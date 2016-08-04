package com.FinalKill.wsbim.common.block;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFreezer extends BlockContainer {
	
	private static boolean keepInventory;
	private boolean isActiveBlock;

	public BlockFreezer(boolean activeBlock, String name) {
		super(Material.rock);
		this.setStepSound(soundTypeMetal);
		this.setHardness(2.0F);
		this.setResistance(150F);
		this.setBlockName(name);
		this.isActiveBlock = activeBlock;
		this.setBlockTextureName("iron_block");
		this.setBlockBounds(1F/16F, 0, 1F/16F, 1 - 1F/16F, 1-4F/16F, 1F-1F/16F);
	}

	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFreezer();
	}
	
	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	 {
	       return Item.getItemFromBlock(WSBIM.blockFreezerIdle);
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
	    {if (!keepInventory)
		{
	      
	    	TileEntityFreezer var7 = (TileEntityFreezer)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
	            /**
	            
	            float var10 = random.nextFloat() * 0.8F + 0.1F;
	            float var11 = random.nextFloat() * 0.8F + 0.1F;
	            float var12 = random.nextFloat() * 0.8F + 0.1F;

	       
	                int var13 = random.nextInt(21) + 10;

	      
	                EntityItem var14 = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + var10), (double)((float)p_149749_3_ + var11), (double)((float)p_149749_4_ + var12), new ItemStack(this.getInactiveBlock(this)));

	     

	                float var15 = 0.05F;
	                var14.motionX = (double)((float)random.nextGaussian() * var15);
	                var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
	                var14.motionZ = (double)((float)random.nextGaussian() * var15);
	                p_149749_1_.spawnEntityInWorld(var14);
	            
	*/
	        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	    }
	    }
	    
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
			if (world.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	        	FMLNetworkHandler.openGui(player,WSBIM.instance,-1, world, x, y, z);
	            return true;
	        }
			
			
			
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
	     * A randomly called display update to be able to add particles or other items for display
	     */
	    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	    {
	      if(p_149734_1_.getBlock(p_149734_2_, p_149734_3_, p_149734_4_) == WSBIM.blockFreezerActive){
	    	  double d0 = (double)((float)p_149734_2_ + 0.2F + p_149734_5_.nextFloat() * 0.6F);
	          double d1 = (double)((float)p_149734_3_ + 0.65F);
	          double d2 = (double)((float)p_149734_4_ + 0.2F + p_149734_5_.nextFloat() * 0.6F);
	          p_149734_1_.spawnParticle("snowshovel", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	          p_149734_1_.spawnParticle("snowshovel", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	          p_149734_1_.spawnParticle("snowshovel", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	          p_149734_1_.spawnParticle("snowshovel", d0, d1, d2, 0.0D, 0.0D, 0.0D);

	          int metadata = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
	          
	          if(metadata == 5){
	        	   d0 = (double)((float)p_149734_2_ + 0F);
		           d1 = (double)((float)p_149734_3_ + 0.5F);
		           d2 = (double)((float)p_149734_4_ + 0.5F);
		           GL11.glColor4f(2, 2, 2, 2);
		          p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);

	          }
	          
	          if(metadata == 2){
	        	   d0 = (double)((float)p_149734_2_ + 0.5F);
		           d1 = (double)((float)p_149734_3_ + 0.5F);
		           d2 = (double)((float)p_149734_4_ + 1F);
		           GL11.glColor4f(2, 2, 2, 2);
		          p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);

	          }
	          
	          if(metadata == 4){
	        	   d0 = (double)((float)p_149734_2_ + 1F);
		           d1 = (double)((float)p_149734_3_ + 0.5F);
		           d2 = (double)((float)p_149734_4_ + 0.5F);
		           GL11.glColor4f(2, 2, 2, 2);
		          p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);

	          }
	          
	          if(metadata == 3){
	        	   d0 = (double)((float)p_149734_2_ + 0.5F);
		           d1 = (double)((float)p_149734_3_ + 0.5F);
		           d2 = (double)((float)p_149734_4_ + 0F);
		           GL11.glColor4f(2, 2, 2, 2);
		          p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);

	          }
	      }
	    }

		public static void updateFreezer(boolean par0, World par1World, int par2, int par3, int par4)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			TileEntityFreezer tileentity = (TileEntityFreezer)par1World.getTileEntity(par2, par3, par4);
			TileEntity t = par1World.getTileEntity(par2, par3, par4);
			keepInventory = true;

			if (par0)
			{
				par1World.setBlock(par2, par3, par4, WSBIM.blockFreezerActive);
				
			}
			else
			{
				par1World.setBlock(par2, par3, par4, WSBIM.blockFreezerIdle);
			}
			keepInventory = false;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

			if (tileentity != null)
			{
				t.validate();
				par1World.setTileEntity(par2, par3, par4, t);
			}
		}
}
