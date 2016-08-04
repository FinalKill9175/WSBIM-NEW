package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityPowerFurnace;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;


public class BlockPowerFurnace extends BlockContainer {

	private IIcon front_icon;
	private boolean isActiveBlock;
	private static boolean keepInventory;


	protected BlockPowerFurnace(boolean active_block) {
		super(MachineryCraft.machinery);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3.5F);
		this.setResistance(100);
		this.isActiveBlock = active_block;
		
	}
	
	/**
	 * Update which block ID the furnace is using depending on whether or not it is burning
	 */
	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		keepInventory = true;

		if (par0)
		{
			par1World.setBlock(par2, par3, par4, MachineryCraft.blocks.power_furance_active);
			
		}
		else
		{
			par1World.setBlock(par2, par3, par4,  MachineryCraft.blocks.power_furance);
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
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World w, int x, int y, int z, Random rand)
    {
        if (w.getBlock(x, y, z).equals(MachineryCraft.blocks.power_furance_active))
        {
          
        	 Random var5 = w.rand;
             double var6 = 0.0625D;

             for (int var8 = 0; var8 < 8; ++var8)
             {
                 double var9 = (double)((float)x + var5.nextFloat());
                 double var11 = (double)((float)y + var5.nextFloat());
                 double var13 = (double)((float)z + var5.nextFloat());

                 if (var8 == 0 && !w.getBlock(x, y + 1, z).isOpaqueCube())
                 {
                     var11 = (double)(y + 1) + var6;
                 }

                 if (var8 == 1 && !w.getBlock(x, y - 1, z).isOpaqueCube())
                 {
                     var11 = (double)(y + 0) - var6;
                 }

                 if (var8 == 2 && !w.getBlock(x, y, z + 1).isOpaqueCube())
                 {
                     var13 = (double)(z + 1) + var6;
                 }

                 if (var8 == 3 && !w.getBlock(x, y, z - 1).isOpaqueCube())
                 {
                     var13 = (double)(z + 0) - var6;
                 }

                 if (var8 == 4 && !w.getBlock(x + 1, y, z).isOpaqueCube())
                 {
                     var9 = (double)(x + 1) + var6;
                 }

                 if (var8 == 5 && !w.getBlock(x - 1, y, z).isOpaqueCube())
                 {
                     var9 = (double)(x + 0) - var6;
                 }

                 if (var9 < (double)x || var9 > (double)(x + 1) || var11 < 0.0D || var11 > (double)(y + 1) || var13 < (double)z || var13 > (double)(z + 1))
                 {
                     w.spawnParticle("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                 }
             }

             for (int var8 = 0; var8 < 2; ++var8)
             {
                 double var9 = (double)((float)x + var5.nextFloat());
                 double var11 = (double)((float)y + var5.nextFloat());
                 double var13 = (double)((float)z + var5.nextFloat());

                 if (var8 == 0 && !w.getBlock(x, y + 1, z).isOpaqueCube())
                 {
                     var11 = (double)(y + 1) + var6;
                 }

                 if (var8 == 1 && !w.getBlock(x, y - 1, z).isOpaqueCube())
                 {
                     var11 = (double)(y + 0) - var6;
                 }

                 if (var8 == 2 && !w.getBlock(x, y, z + 1).isOpaqueCube())
                 {
                     var13 = (double)(z + 1) + var6;
                 }

                 if (var8 == 3 && !w.getBlock(x, y, z - 1).isOpaqueCube())
                 {
                     var13 = (double)(z + 0) - var6;
                 }

                 if (var8 == 4 && !w.getBlock(x + 1, y, z).isOpaqueCube())
                 {
                     var9 = (double)(x + 1) + var6;
                 }

                 if (var8 == 5 && !w.getBlock(x - 1, y, z).isOpaqueCube())
                 {
                     var9 = (double)(x + 0) - var6;
                 }

                 if (var9 < (double)x || var9 > (double)(x + 1) || var11 < 0.0D || var11 > (double)(y + 1) || var13 < (double)z || var13 > (double)(z + 1))
                 {
                     w.spawnParticle("smoke", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                 }
             }
        }
    }
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote&&!player.isSneaking()){
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,MachineryCraft.guiElectricFurnace, world, x, y, z);

			return true;
		}
		else{
				return false;
		}
		
		
		
	}
	  /**
     * Gets the block's texture. Args: side, meta
     */
    public IIcon getIcon(int side, int meta)
    {
    //	if(p_149691_1_==3 && p_149691_2_ == -1 )return this.front_icon;
    	//else if(p_149691_2_ ==-1) return this.blockIcon;
    //	else 
    		return (side ==3 && meta ==0?this.front_icon:(meta == 0 && side !=3?this.blockIcon:(side != meta ? this.blockIcon : this.front_icon)));

    	
        }
	   public void registerBlockIcons(IIconRegister p_149651_1_)
	    {
	        this.blockIcon = p_149651_1_.registerIcon("machinery_craft:iron_old");
	        this.front_icon = p_149651_1_.registerIcon(this.isActiveBlock ? "machinery_craft:power_furnace_front_active" : "machinery_craft:power_furnace_front_idle");
	           }
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPowerFurnace();
	}

	
	public int getRenderType(){
		
		return 0;
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
      
            TileEntityPowerFurnace var7 = (TileEntityPowerFurnace)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(MachineryCraft.blocks.power_furance);
    }
}
