package com.FinalKill.wsbim.common.block;

import java.util.Random;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.tileentity.TileEntityAntimatterWorkbench;
import com.FinalKill.wsbim.util.IUpgradedChest;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAntimatterWorkbench extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	
	public BlockAntimatterWorkbench() {
		super(Material.ground);
		this.setHardness(2.5F);
		this.setResistance(100);
		this.setBlockName("antimatterWorkbench");
		this.setStepSound(soundTypeStone);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.topIcon = reg.registerIcon(WSBIM.modid+":"+"antimatter_table_top");
    	this.blockIcon = reg.registerIcon(WSBIM.modid+":"+"magic_default");
    
    }
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.topIcon : this.blockIcon;
    }
    
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityAntimatterWorkbench();
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

	 public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	    {
	    	TileEntityAntimatterWorkbench var7 = (TileEntityAntimatterWorkbench)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
