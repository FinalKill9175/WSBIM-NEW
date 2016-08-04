package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class BlockRainCollector extends BlockContainer {

	protected BlockRainCollector() {
		super(Material.ground);
		this.setHardness(4.0F);
		this.setResistance(10);
		this.setStepSound(soundTypeGlass);
		float pixel = 1F/16F;
		this.setBlockBounds(pixel*2, 0, pixel*2, 1-pixel*2, 1-pixel*2, 1-pixel*2);
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityRainCollector();
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return MachineryCraft.items.rainCollector;
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
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote&&!player.isSneaking()){
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,MachineryCraft.guiRainCollector, world, x, y, z);

			return true;
		}
		else{
				return false;
		}
		
		
		
	}
    

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
      
            TileEntityRainCollector var7 = (TileEntityRainCollector)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
                
                float var10 = random.nextFloat() * 0.8F + 0.1F;
                float var11 = random.nextFloat() * 0.8F + 0.1F;
                float var12 = random.nextFloat() * 0.8F + 0.1F;

                int var13 = (int) var7.getWaterScaled(16);
                
                     EntityItem var14 = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + var10), (double)((float)p_149749_3_ + var11), (double)((float)p_149749_4_ + var12), new ItemStack(Blocks.water, var13));

                
                    float var15 = 0.05F;
                    var14.motionX = (double)((float)random.nextGaussian() * var15);
                    var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                    var14.motionZ = (double)((float)random.nextGaussian() * var15);
                    p_149749_1_.spawnEntityInWorld(var14);
               
                
                

                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
            }
        
            
            

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
}
