package com.FinalKill.wsbim.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.tileentity.TileEntityDiamondFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityGoldFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityIronFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityMixedMetalFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityObsidianFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityQuartzFurnace;
import com.FinalKill.wsbim.common.tileentity.TileEntityUpgradedFurnace;
import com.FinalKill.wsbim.util.EnumFurnaceType;
import com.FinalKill.wsbim.util.FurnaceRegistry;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUpgradedFurnace extends BlockContainer{

	private final EnumFurnaceType furnType;
	private static boolean keepInventory;
	
	@SideOnly(Side.CLIENT)
	private IIcon front_icon;
	@SideOnly(Side.CLIENT)
	private IIcon sides;
	private boolean isActiveBlock;
	
	private Block dropBlock;
	
	public BlockUpgradedFurnace(boolean active, EnumFurnaceType type, String block) {
		super(Material.ground);
	furnType = type;
	this.isActiveBlock = active;
	this.setBlockName(active?block+"_active": block);
	this.setBlockTextureName(block);
	this.setHardness(2.0F);
	this.setResistance(250);
	this.setStepSound(type.getMainBlock().stepSound);
	dropBlock = type.getFurnaceBlockIdle();
	
	if(active){
		this.setLightLevel(0.8F);
		this.setLightOpacity(1);
	}
	}

	public String getTextureIconString(){
		return this.getTextureName();
	}
	
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return this.createTileEntities(this);
	}

	public EnumFurnaceType getFurnaceType(){
		return furnType;
	}
	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	    {
	        return Item.getItemFromBlock(this.getInactiveBlock(this));
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
	    
	    /**
	     * A randomly called display update to be able to add particles or other items for display
	     */
	    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	    {
	        if (!p_149734_1_.getBlock(p_149734_2_, p_149734_3_, p_149734_4_).equals(this.getInactiveBlock((BlockUpgradedFurnace) p_149734_1_.getBlock(p_149734_2_, p_149734_3_, p_149734_4_))))
	        {
	            int var6 = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
	            float var7 = (float)p_149734_2_ + 0.5F;
	            float var8 = (float)p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
	            float var9 = (float)p_149734_4_ + 0.5F;
	            float var10 = 0.52F;
	            float var11 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

	            if (var6 == 4)
	            {
	                p_149734_1_.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                p_149734_1_.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (var6 == 5)
	            {
	                p_149734_1_.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                p_149734_1_.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (var6 == 2)
	            {
	                p_149734_1_.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	                p_149734_1_.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            }
	            else if (var6 == 3)
	            {
	                p_149734_1_.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
	                p_149734_1_.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {if (!keepInventory)
	{
      
    	IUpgradedFurnace var7 = (IUpgradedFurnace)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
		   this.sides = this.getFurnaceType() == EnumFurnaceType.ULTIMATE ? p_149651_1_.registerIcon(WSBIM.modid + ":" + getTextureName() + "Side") : this.getFurnaceType().getMainBlock().getIcon(0, 0);
	        this.blockIcon = this.sides;
	        
	        this.front_icon = p_149651_1_.registerIcon(this.isActiveBlock ? WSBIM.modid +":"+ getTextureName()+"_front_active" : WSBIM.modid+":"+ getTextureName()+"_front_idle");
	           }
	   
	   
		public static void updateFurnace(boolean par0, World par1World, int par2, int par3, int par4)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			IUpgradedFurnace tileentity = (IUpgradedFurnace)par1World.getTileEntity(par2, par3, par4);
			TileEntity t = par1World.getTileEntity(par2, par3, par4);
			keepInventory = true;

			if (par0)
			{
				par1World.setBlock(par2, par3, par4, tileentity.getFurnaceBlock().getActiveBlock(tileentity.getFurnaceBlock()));
				
			}
			else
			{
				par1World.setBlock(par2, par3, par4,  tileentity.getFurnaceBlock().getInactiveBlock(tileentity.getFurnaceBlock()));
			}
			keepInventory = false;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

			if (tileentity != null)
			{
				t.validate();
				par1World.setTileEntity(par2, par3, par4, t);
			}
		}
		/**
		public Block getInactiveBlock(BlockUpgradedFurnace b){
			if(b == WSBIM.blockDiamondFurnaceActive || b == WSBIM.blockDiamondFurnaceIdle){
				return WSBIM.blockDiamondFurnaceIdle;
			}
			else if(b == WSBIM.blockGoldFurnaceActive || b == WSBIM.blockGoldFurnaceIdle){
				return WSBIM.blockGoldFurnaceIdle;
			}
			else if(b == WSBIM.blockIronFurnaceActive || b == WSBIM.blockIronFurnaceIdle){
				return WSBIM.blockIronFurnaceIdle;
			}
			else if(b == WSBIM.blockQuartzFurnaceActive || b == WSBIM.blockQuartzFurnaceIdle){
				return WSBIM.blockQuartzFurnaceIdle;
			}
			else if(b == WSBIM.blockObsidianFurnaceActive || b == WSBIM.blockObsidianFurnaceIdle){
				return WSBIM.blockObsidianFurnaceIdle;
			}
			else if(b == WSBIM.blockMixedMetalFurnaceActive || b == WSBIM.blockMixedMetalFurnaceIdle){
				return WSBIM.blockMixedMetalFurnaceIdle;
			}
			else{
				return null;
			}
			
		}
		public Block getActiveBlock(BlockUpgradedFurnace b){
			if(b == WSBIM.blockDiamondFurnaceActive || b == WSBIM.blockDiamondFurnaceIdle){
				return WSBIM.blockDiamondFurnaceActive;
			}
			else if(b == WSBIM.blockGoldFurnaceActive || b == WSBIM.blockGoldFurnaceIdle){
				return WSBIM.blockGoldFurnaceActive;
			}
			else if(b == WSBIM.blockIronFurnaceActive || b == WSBIM.blockIronFurnaceIdle){
				return WSBIM.blockIronFurnaceActive;
			}
			else if(b == WSBIM.blockQuartzFurnaceActive || b == WSBIM.blockQuartzFurnaceIdle){
				return WSBIM.blockQuartzFurnaceActive;
			}
			else if(b == WSBIM.blockObsidianFurnaceActive || b == WSBIM.blockObsidianFurnaceIdle){
				return WSBIM.blockObsidianFurnaceActive;
			}
			else if(b == WSBIM.blockMixedMetalFurnaceActive || b == WSBIM.blockMixedMetalFurnaceIdle){
				return WSBIM.blockMixedMetalFurnaceActive;
			}
			else{
				return null;
			}
			
		}
		*/
	
		
		public BlockUpgradedFurnace getInactiveBlock(BlockUpgradedFurnace b){
			return (BlockUpgradedFurnace) FurnaceRegistry.idleFurnaces.get(b.getFurnaceType().getID());
		}
		
		public BlockUpgradedFurnace getActiveBlock(BlockUpgradedFurnace b){
			return (BlockUpgradedFurnace) FurnaceRegistry.activeFurnaces.get(b.getFurnaceType().getID());
		}
		/**
		public TileEntity createTileEntities(Block b){
			if(b == WSBIM.blockDiamondFurnaceActive || b == WSBIM.blockDiamondFurnaceIdle){
				return new TileEntityDiamondFurnace();
			}
			else if(b == WSBIM.blockGoldFurnaceActive || b == WSBIM.blockGoldFurnaceIdle){
				return new TileEntityGoldFurnace();
			}
			else if(b == WSBIM.blockIronFurnaceActive || b == WSBIM.blockIronFurnaceIdle){
				return new TileEntityIronFurnace();
			}
			else if(b == WSBIM.blockQuartzFurnaceActive || b == WSBIM.blockQuartzFurnaceIdle){
				return new TileEntityQuartzFurnace();
			}
			else if(b == WSBIM.blockObsidianFurnaceActive || b == WSBIM.blockObsidianFurnaceIdle){
				return new TileEntityObsidianFurnace();
			}
			else if(b == WSBIM.blockMixedMetalFurnaceActive || b == WSBIM.blockMixedMetalFurnaceIdle){
				return new TileEntityMixedMetalFurnace();
			}
			else{
				return null;
			}
		}
		*/
		
		public TileEntity createTileEntities(Block b){
			try {
				return (TileEntity) FurnaceRegistry.furnaceTiles.get(this.getFurnaceType().getID()).getClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}
}
