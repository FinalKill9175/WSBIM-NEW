package com.FinalKill.wsbim.common.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NBTPasteItemEvent {
	
	private Block field_150939_a;
	int cooldown = 0;
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e){
		if(e.phase == Phase.END){
			if(e.player !=null){
				
				if(cooldown > 0){
					--cooldown;
				}
				
				boolean request = false;
				MovingObjectPosition ray = e.player.rayTrace(5, 1F);
				int x = ray.blockX;
				int y = ray.blockY;
				int z = ray.blockZ;
				Block rayBlock = e.player.worldObj.getBlock(x, y, z);
				
				if(!request && ray !=null && !rayBlock.isAir(e.player.worldObj, x, y, z) && cooldown == 0 && e.side == Side.SERVER){
					if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_V)){
						request = true;
					}
				}
				
				if(e.player.inventory.getCurrentItem() !=null){
					ItemStack currentItem = e.player.inventory.getCurrentItem();
					if(currentItem !=null){
						Block currentBlock = Block.getBlockFromItem(currentItem.getItem());
						if(currentBlock !=null){
							if(currentBlock.hasTileEntity(currentItem.getItem().getMetadata(currentItem.getItemDamage()))){
								if(request){
									int x1 = x;
									int y1 = y;
									int z1 = z;
									int side = ray.sideHit;
									Block block = currentBlock;
									World world = e.player.worldObj;
									
									if (block == Blocks.snow_layer && (world.getBlockMetadata(x1, y1, z1) & 7) < 1)
							        {
							            side = 1;
							        }
									
							        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
							        {
							            if (side == 0)
							            {
							                --y1;
							            }

							            if (side == 1)
							            {
							                ++y1;
							            }

							            if (side == 2)
							            {
							                --z1;
							            }

							            if (side == 3)
							            {
							                ++z1;
							            }

							            if (side == 4)
							            {
							                --x1;
							            }

							            if (side == 5)
							            {
							                ++x1;
							            }
							        }

							        if (currentItem.stackSize == 0)
							        {
							        	request = false;
							        }
							        else if (!e.player.canPlayerEdit(x1, y1, z1, side, currentItem))
							        {
							        	request = false;
							        }
							        else if (y1 == 255 && block.getMaterial().isSolid())
							        {
							        	request = false;
							        }
							        
							        int i1 = currentItem.getItem().getMetadata(currentItem.getItemDamage());
							        int j1 = block.onBlockPlaced(world, x1, y1, z1, side, 0, 0, 0, i1);

							        if(world.canPlaceEntityOnSide(block, x1, y1, z1, false, side, e.player, currentItem)){
								    if (placeBlockAt(currentItem, e.player, world, x1, y1, z1, side, 0, 0, 0, j1, block)){
								    	//  world.playSoundEffect((double)((float)x1 + 0.5F), (double)((float)y1 + 0.5F), (double)((float)z1 + 0.5F), block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

								    	if(!e.player.capabilities.isCreativeMode){
								        	  if(currentItem.stackSize > 1){
								                	--currentItem.stackSize;
								        	  }
								        	  else{
								        		  e.player.inventory.mainInventory[e.player.inventory.currentItem] = null;
								        	  }
								        }
								    	
								    			cooldown = 20;
								            	request = false;
								    	}
							        }
								}
							}
						}
					}
				}
			}
		}
	}

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onEvent(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);

        if(field_150939_a == null){
        	return false;
        }
        
        if (block == Blocks.snow_layer && (p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) & 7) < 1)
        {
            p_77648_7_ = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
        {
            if (p_77648_7_ == 0)
            {
                --p_77648_5_;
            }

            if (p_77648_7_ == 1)
            {
                ++p_77648_5_;
            }

            if (p_77648_7_ == 2)
            {
                --p_77648_6_;
            }

            if (p_77648_7_ == 3)
            {
                ++p_77648_6_;
            }

            if (p_77648_7_ == 4)
            {
                --p_77648_4_;
            }

            if (p_77648_7_ == 5)
            {
                ++p_77648_4_;
            }
        }

        if (p_77648_1_.stackSize == 0)
        {
            return false;
        }
        else if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else if (p_77648_5_ == 255 && this.field_150939_a.getMaterial().isSolid())
        {
            return false;
        }
        else if (p_77648_3_.canPlaceEntityOnSide(this.field_150939_a, p_77648_4_, p_77648_5_, p_77648_6_, false, p_77648_7_, p_77648_2_, p_77648_1_))
        {
        	 p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);

            int i1 = p_77648_1_.getItem().getMetadata(p_77648_1_.getItemDamage());
            int j1 = this.field_150939_a.onBlockPlaced(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, i1);

//            if (placeBlockAt(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, j1), null)
            {
                               --p_77648_1_.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_150936_a(World p_150936_1_, int p_150936_2_, int p_150936_3_, int p_150936_4_, int p_150936_5_, EntityPlayer p_150936_6_, ItemStack p_150936_7_)
    {
        Block block = p_150936_1_.getBlock(p_150936_2_, p_150936_3_, p_150936_4_);

        if (block == Blocks.snow_layer)
        {
            p_150936_5_ = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(p_150936_1_, p_150936_2_, p_150936_3_, p_150936_4_))
        {
            if (p_150936_5_ == 0)
            {
                --p_150936_3_;
            }

            if (p_150936_5_ == 1)
            {
                ++p_150936_3_;
            }

            if (p_150936_5_ == 2)
            {
                --p_150936_4_;
            }

            if (p_150936_5_ == 3)
            {
                ++p_150936_4_;
            }

            if (p_150936_5_ == 4)
            {
                --p_150936_2_;
            }

            if (p_150936_5_ == 5)
            {
                ++p_150936_2_;
            }
        }

        return p_150936_1_.canPlaceEntityOnSide(this.field_150939_a, p_150936_2_, p_150936_3_, p_150936_4_, false, p_150936_5_, (Entity)null, p_150936_7_);
    }


    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata, Block b)
    {

       if (!world.setBlock(x, y, z, b, metadata, 3))
       {
           return false;
       }

       if (world.getBlock(x, y, z) == b)
       {
           b.onBlockPlacedBy(world, x, y, z, player, stack);
           b.onPostBlockPlaced(world, x, y, z, metadata);
       }
       
       /**
       if(b instanceof BlockContainer){
    	   BlockContainer bc = (BlockContainer) world.getBlock(x, y, z);
    	   if(bc !=null){
    		   world.setTileEntity(x, y, z, bc.createNewTileEntity(world, metadata));
    	   }
       }
       */
       //world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), b.stepSound.func_150496_b(), (b.stepSound.getVolume() + 1.0F) / 2.0F, b.stepSound.getPitch() * 0.8F);

       return true;
    }
}
