package com.FinalKill.Machinery_Craft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityGoldChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

public class BlockChestDropper extends Block {

	protected BlockChestDropper() {
		super(Material.iron);
		this.setHardness(4.0F);
		this.setResistance(30);
		
	}

	public boolean dropAndCheckTileEntities(World world, int x, int y, int z, EntityPlayer player){
		if(world.getTileEntity(x+1, y, z) !=null){
		    
    		TileEntity tileentity = world.getTileEntity(x+1, y, z);
    		TileEntityChest chest_extention = (TileEntityChest) world.getTileEntity(x+2, y, z);
    		
    		if(tileentity instanceof TileEntityChest){
    			tileentity = (TileEntityChest)world.getTileEntity(x+1, y, z);
    			
    			this.dropChestInventory(world, (TileEntityChest) tileentity, x, y, z, player );
    			
    			if(chest_extention !=null){
    				this.dropChestInventory(world, chest_extention, x, y, z, player);
    				
    			}
    			
    			
    		}
    		/**
    		else if(tileentity instanceof TileEntityIronChest){
	tileentity = (TileEntityIronChest)world.getTileEntity(x+1, y, z);
    			
    			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity, x, y, z, player );
    		}
	else if(tileentity instanceof TileEntityGoldChest){
		tileentity = (TileEntityGoldChest)world.getTileEntity(x+1, y, z);
		
		this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity, x, y, z, player );
    		}
	else if(tileentity instanceof TileEntityDiamondChest){
		tileentity = (TileEntityDiamondChest)world.getTileEntity(x+1, y, z);
		
		this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity, x, y, z, player );
	}
    		
    		*/
    		return true;
    		
    
    	}
    	else if(world.getTileEntity(x, y+1, z) !=null){

    		TileEntity tileentity = world.getTileEntity(x, y+1, z);
    		
    		if(tileentity instanceof TileEntityChest){
    			tileentity = (TileEntityChest)world.getTileEntity(x, y+1, z);
    			
    			this.dropChestInventory(world, (TileEntityChest) tileentity, x, y+1, z, player );
    			
    			
    		}
    		else if(tileentity instanceof TileEntityIronChest){
	tileentity = (TileEntityIronChest)world.getTileEntity( x, y+1, z);
    			
    			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity,  x, y+1, z, player );
    		}
	else if(tileentity instanceof TileEntityGoldChest){
		tileentity = (TileEntityGoldChest)world.getTileEntity( x, y+1, z);
		
		this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity,  x, y+1, z, player );
    		}
	else if(tileentity instanceof TileEntityDiamondChest){
		tileentity = (TileEntityDiamondChest)world.getTileEntity( x, y+1, z);
		
		this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity,  x, y+1, z, player );
	}
    		
    		
    		return true;
    		
    	}
	else if(world.getTileEntity(x, y-1, z) !=null){
		
		int x1 = x;
		int y1 = y-1;
		int z1 = z;
		
		TileEntity tileentity = world.getTileEntity(x1, y1, z1);
		
		if(tileentity instanceof TileEntityChest){
			tileentity = (TileEntityChest)world.getTileEntity(x1, y1, z1);
			
			this.dropChestInventory(world, (TileEntityChest) tileentity, x1, y1, z1, player );
			
			
		}
		else if(tileentity instanceof TileEntityIronChest){
tileentity = (TileEntityIronChest)world.getTileEntity( x, y+1, z);
			
			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityGoldChest){
	tileentity = (TileEntityGoldChest)world.getTileEntity( x1, y1, z1);
	
	this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityDiamondChest){
	tileentity = (TileEntityDiamondChest)world.getTileEntity(x1, y1, z1);
	
	this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity, x1, y1, z1, player );
	
	
}
		
		
		return true;
    	}
	else if(world.getTileEntity(x-1, y, z) !=null){
		int x1 = x-1;
		int y1 = y;
		int z1 = z;
		
		TileEntity tileentity = world.getTileEntity(x1, y1, z1);
		TileEntityChest chest_extention = (TileEntityChest) world.getTileEntity(x-2, y, z);
		if(tileentity instanceof TileEntityChest){
			tileentity = (TileEntityChest)world.getTileEntity(x1, y1, z1);
			
			this.dropChestInventory(world, (TileEntityChest) tileentity, x1, y1, z1, player );
			if(chest_extention !=null){
				
				this.dropChestInventory(world, chest_extention, x1, y1, z1, player);
			}
			
		}
		else if(tileentity instanceof TileEntityIronChest){
tileentity = (TileEntityIronChest)world.getTileEntity( x, y+1, z);
			
			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityGoldChest){
	tileentity = (TileEntityGoldChest)world.getTileEntity( x1, y1, z1);
	
	this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityDiamondChest){
	tileentity = (TileEntityDiamondChest)world.getTileEntity(x1, y1, z1);
	
	this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity, x1, y1, z1, player );
	
	
}
		
		
		return true;
	}
	else if(world.getTileEntity(x, y, z+1) !=null){
		int x1 = x;
		int y1 = y;
		int z1 = z+1;
		
		TileEntity tileentity = world.getTileEntity(x1, y1, z1);
		TileEntityChest chest_extention = (TileEntityChest) world.getTileEntity(x, y, z+2);
		if(tileentity instanceof TileEntityChest){
			tileentity = (TileEntityChest)world.getTileEntity(x1, y1, z1);
			
			this.dropChestInventory(world, (TileEntityChest) tileentity, x1, y1, z1, player );
			
			if(chest_extention !=null){
				this.dropChestInventory(world, (TileEntityChest) chest_extention, x, y, z, player);
				
			}
			
		}
		else if(tileentity instanceof TileEntityIronChest){
tileentity = (TileEntityIronChest)world.getTileEntity( x, y+1, z);
			
			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityGoldChest){
	tileentity = (TileEntityGoldChest)world.getTileEntity( x1, y1, z1);
	
	this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityDiamondChest){
	tileentity = (TileEntityDiamondChest)world.getTileEntity(x1, y1, z1);
	
	this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity, x1, y1, z1, player );
	
	
}
		
		
		return true;
	}
	else if(world.getTileEntity(x, y, z-1) !=null){
		int x1 = x;
		int y1 = y;
		int z1 = z-1;
		
		TileEntity tileentity = world.getTileEntity(x1, y1, z1);
		TileEntityChest chest_extention = (TileEntityChest) world.getTileEntity(x, y, z-2);
		if(tileentity instanceof TileEntityChest){
			tileentity = (TileEntityChest)world.getTileEntity(x1, y1, z1);
			
			this.dropChestInventory(world, (TileEntityChest) tileentity, x1, y1, z1, player );
			if(chest_extention !=null){
				
				this.dropChestInventory(world, chest_extention, x, y, z, player);
			}
			
		}
		else if(tileentity instanceof TileEntityIronChest){
tileentity = (TileEntityIronChest)world.getTileEntity( x, y+1, z);
			
			this.dropIronChestInventory(world, (TileEntityIronChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityGoldChest){
	tileentity = (TileEntityGoldChest)world.getTileEntity( x1, y1, z1);
	
	this.dropGoldChestInventory(world, (TileEntityGoldChest) tileentity,  x1, y1, z1, player );
		}
else if(tileentity instanceof TileEntityDiamondChest){
	tileentity = (TileEntityDiamondChest)world.getTileEntity(x1, y1, z1);
	
	this.dropDiamondChestInventory(world, (TileEntityDiamondChest) tileentity, x1, y1, z1, player );
	
	
}
		
		
		return true;
	}
	else{
        return false;
    }
		
	}
	
	  /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	return this.dropAndCheckTileEntities(world, x, y, z, player);
    	
    }


	public void dropChestInventory(World world, TileEntityChest tileentity, int x, int y, int z, EntityPlayer player) {
		 TileEntityChest var7 = tileentity;

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
                         EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                     
                         if (var9.hasTagCompound())
                         {
                             var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                         }

                         
                         float var15 = 0.05F;
                         var14.motionX = (double)((float)random.nextGaussian() * var15);
                         var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                         var14.motionZ = (double)((float)random.nextGaussian() * var15);
                         world.spawnEntityInWorld(var14);
                         var7.setInventorySlotContents(var8, null);
                     }
                   	 

                    	
                    	 
                     
                 }
             }

             world.func_147453_f(x, y, z, world.getBlock(x, y, z));
         }
     

   
	}
	/**
	  * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
      * their own) Args: x, y, z, neighbor blockID
      */
     public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
     {
             if (!world.isRemote)
             {
                  
                  if (world.isBlockIndirectlyGettingPowered(x,y,z))
                     {
                             this.dropAndCheckTileEntities(world, x, y, z, Minecraft.getMinecraft().thePlayer);
                     }
                 
             }
     }
	
	public void dropIronChestInventory(World world, TileEntityIronChest tileentity, int x, int y, int z, EntityPlayer player) {
		 TileEntityIronChest var7 = tileentity;

		 /**
        Random random = new Random();
        
        if (var7 != null)
        {
            for (int var8 = 0; var8 < 55; ++var8)
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
                        EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (double)((float)random.nextGaussian() * var15);
                        var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)random.nextGaussian() * var15);
                        world.spawnEntityInWorld(var14);
                        var7.setInventorySlotContents(var8, null);
                    }
                }
            }

           // world.func_147453_f(x, y, z, world.getBlock(x, y, z));
        }
    */

  
	}

	public void dropGoldChestInventory(World world, TileEntityGoldChest tileentity, int x, int y, int z, EntityPlayer player) {
		 TileEntityGoldChest var7 = tileentity;

        Random random = new Random();
        /**
        if (var7 != null)
        {
            for (int var8 = 0; var8 < 60; ++var8)
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
                        EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (double)((float)random.nextGaussian() * var15);
                        var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)random.nextGaussian() * var15);
                        world.spawnEntityInWorld(var14);
                        var7.setInventorySlotContents(var8, null);
                    }
                }
            }

         //   world.func_147453_f(x, y, z, world.getBlock(x, y, z));
        }
    */

  
	}

	public void dropDiamondChestInventory(World world, TileEntityDiamondChest tileentity, int x, int y, int z, EntityPlayer player) {
		 TileEntityDiamondChest var7 = tileentity;
/**
        Random random = new Random();
        
        if (var7 != null)
        {
            for (int var8 = 0; var8 < 104; ++var8)
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
                        EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (double)((float)random.nextGaussian() * var15);
                        var14.motionY = (double)((float)random.nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)random.nextGaussian() * var15);
                        world.spawnEntityInWorld(var14);
                        var7.setInventorySlotContents(var8, null);
                    }
                }
            }

          //  world.func_147453_f(x, y, z, world.getBlock(x, y, z));
        }
    
*/
  
	}


}
