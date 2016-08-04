package com.FinalKill.wsbim.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.tileentity.TileEntityObsidianChest;
import com.FinalKill.wsbim.util.ChestRegistry;
import com.FinalKill.wsbim.util.EnumChestType;
import com.FinalKill.wsbim.util.IUpgradedChest;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class BlockUpgradedChest extends BlockContainer{

	private final EnumChestType type;
	private int times;
	
	public BlockUpgradedChest(EnumChestType type, String block) {
		super(type.getMaterialBlock() !=null? type.getMaterialBlock().getMaterial() : Material.ground);
		this.type = type;
		if(type.getMaterialBlock() !=null){
			this.setHardness(type.getMaterialBlock().getBlockHardness(null, 0, 0, 0));
			this.setResistance(type.getMaterialBlock().getExplosionResistance(null) * 5.0F);
			this.setStepSound(type.getMaterialBlock().stepSound);
		}
		else{
			this.setHardness(Blocks.iron_block.getBlockHardness(null, 0, 0, 0));
			this.setResistance(Blocks.iron_block.getExplosionResistance(null) * 5.0F);
			this.setStepSound(soundTypeMetal);
		}
		this.setBlockName(block);
		this.setCreativeTab(WSBIM.tabMechanical);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}
		

	public EnumChestType getChestType(){
		return type;
	}
	
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return ChestRegistry.getChestTileEntityInstance(this.getChestType().getID());
	}
	
	public IIcon getIcon(int side, int meta){
		return this.getChestType().getMaterialBlock() !=null ? this.getChestType().getMaterialBlock().getIcon(side, meta) : Blocks.iron_block.getIcon(side, meta);
	}
    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     
    public float getBlockHardness(World world, int x, int y, int z)
    {
    	if(world !=null){
    		TileEntity te = world.getTileEntity(x, y, z);
    		if(te instanceof TileEntityObsidianChest){
    		EntityPlayer player = null;
    		
    		TileEntityObsidianChest 
    		
    		for(int i = 0; i < world.playerEntities.size(); i++){
    		
    		}
        	return this.getPlayerBlockHardness(player, world, x, y, z);
    		}
    		else{
    			return this.blockHardness;
    		}
    	}
    	else {
    		return this.blockHardness;
    	}
    }
    */
    /**
     * Gets the hardness of block at the given coordinates in the given world, relative to the ability of the given
     * EntityPlayer.
     */
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
    {
    	TileEntity te = world.getTileEntity(x, y, z);
    	Block block = world.getBlock(x, y, z);
    	int metadata = world.getBlockMetadata(x, y, z);
    	if(te instanceof IUpgradedChest && ((IUpgradedChest)te).getChestType() != EnumChestType.OBSIDIAN){
    		//System.out.println(((IUpgradedChest)te).getChestType().getMaterialBlock().getBlockHardness(world, x, y, z));
    		
    		return player.getBreakSpeed(block, true, metadata, x, y, z)/((IUpgradedChest)te).getChestType().getMaterialBlock().getBlockHardness(world, x, y, z)/ 30F;
    	}
    	else if(te instanceof TileEntityObsidianChest){
    		TileEntityObsidianChest ob = (TileEntityObsidianChest)world.getTileEntity(x, y, z);
    		//TODO !
    		if(ob.owner !=null && !ob.owner.equals(player.getDisplayName())){
    			//System.out.println(-1F);
    			
    			
    			return 0.0F;
    		}
    		else{
    			
    			return player.getBreakSpeed(block, true, metadata, x, y, z)/50F/ 30F;
    		}
    	}
    	else{
    		times++;
        return 1.0F;
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
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
    	IUpgradedChest var7 = (IUpgradedChest)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
	
    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
    	times = 0;
    	
    	
    }


	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		TileEntity te = world.getTileEntity(x, y, z);
		if(te !=null){
		if(!(te instanceof TileEntityObsidianChest)){
        if (te == null || !(te instanceof IUpgradedChest))
        {
            return true;
        }

        if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
        {
            return true;
        }

        if (world.isRemote)
        {
            return true;
        }
        
        FMLNetworkHandler.openGui(player,WSBIM.instance,-1, world, x, y, z);
        return true;
 	
		}

		else{
			if (te == null || !(te instanceof TileEntityObsidianChest))
	        {
	            return true;
	        }

	        if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
	        {
	            return true;
	        }

	        if (world.isRemote)
	        {
	            return true;
	        }
	        
			String owner = ((TileEntityObsidianChest)te).owner;
			//TODO !
			
			if(owner !=null && !owner.equals(player.getDisplayName())){
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Sorry, "+player.getDisplayName()+" you do "+EnumChatFormatting.RED+"not own "+EnumChatFormatting.RED+"this "+EnumChatFormatting.RED+"chest, "+EnumChatFormatting.RED+"it is owned by"+EnumChatFormatting.RED+" "+EnumChatFormatting.RED+owner));
					if(world.getPlayerEntityByName(owner) !=null){
						world.getPlayerEntityByName(owner).addChatMessage(new ChatComponentText(EnumChatFormatting.RED+player.getDisplayName()+" tried "+EnumChatFormatting.RED+"to get"+EnumChatFormatting.RED+" into your "+EnumChatFormatting.RED+"obsidian "+EnumChatFormatting.RED+"che"+EnumChatFormatting.RED+"st! "+EnumChatFormatting.RED+"@ "+EnumChatFormatting.RED+"x="+EnumChatFormatting.RED+""+x+" y="+EnumChatFormatting.RED+""+y+" z="+EnumChatFormatting.RED+""+z));
					}
					
				return true;
			}
			else if(owner == null){
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN+"This obsidian "+EnumChatFormatting.GREEN+"chest "+EnumChatFormatting.GREEN+"is now your's,"+EnumChatFormatting.GREEN+" "+EnumChatFormatting.GREEN+"you "+EnumChatFormatting.GREEN+"will "+EnumChatFormatting.GREEN+"be "+EnumChatFormatting.GREEN+"notified "+EnumChatFormatting.GREEN+"if"+EnumChatFormatting.GREEN+" "+EnumChatFormatting.GREEN+"someone "+EnumChatFormatting.GREEN+"tries "+EnumChatFormatting.GREEN+"to steal "+EnumChatFormatting.GREEN+"things from "+EnumChatFormatting.GREEN+"this chest. "+EnumChatFormatting.GREEN+"No "+EnumChatFormatting.GREEN+"one "+EnumChatFormatting.GREEN+"else "+EnumChatFormatting.GREEN+"besides"+EnumChatFormatting.GREEN+" you "+EnumChatFormatting.GREEN+"can "+EnumChatFormatting.GREEN+"go "+EnumChatFormatting.GREEN+"into "+EnumChatFormatting.GREEN+"this "+EnumChatFormatting.GREEN+"chest. "));
				((TileEntityObsidianChest)te).owner = player.getDisplayName();
			}
			  
		        
		        
		        FMLNetworkHandler.openGui(player,WSBIM.instance,-1, world, x, y, z);
		        return true;
		}
    		
		}
		return true;
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
