package com.FinalKill.MachineryCraftAPI.power;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.Machinery_Craft.tile.TileEntityIronCable;

public class BlockConnectable extends BlockContainer {

	protected BlockConnectable(Material m) {
		super(m);
		float pixel = 1F/16F;
		this.setBlockBounds(11*pixel/2, 11*pixel/2,11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2);
			}
	
	 /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
  	IConnectableBlock cable =(IConnectableBlock) world.getTileEntity(x, y, z);
    if(cable !=null){
    	ForgeDirection[] connections = cable.getConnections();
    	  
    	float pixel = 1F/16F;
    	float minX = 11*pixel/2-(connections[5] !=null ?(11*pixel/2):0);
    	float maxX = 1-11*pixel/2+(connections[3] !=null ?(11*pixel/2):0);
    	float minY = 11*pixel/2-(connections[1] !=null ?(11*pixel/2):0);
    	float maxY = 1-11*pixel/2+(connections[0] !=null ?(11*pixel/2):0);
    	
    	
    	float minZ = 11*pixel/2-(connections[2] !=null ?(11*pixel/2):0);
    	float maxZ = 1-11*pixel/2+(connections[4] !=null ?(11*pixel/2):0);
    	
    	
    	this.setBlockBounds(minX, minY,minZ, maxX, maxY, maxZ);
		
    }
        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	IConnectableBlock cable =(IConnectableBlock) world.getTileEntity(x, y, z);
    	
    if(cable !=null){
    	ForgeDirection[] connections = cable.getConnections();
    	float pixel = 1F/16F;
    	float minX = 11*pixel/2-(connections[5] !=null ?(11*pixel/2):0);
    	float maxX = 1-11*pixel/2+(connections[3] !=null ?(11*pixel/2):0);
    	float minY = 11*pixel/2-(connections[1] !=null ?(11*pixel/2):0);
    	float maxY = 1-11*pixel/2+(connections[0] !=null ?(11*pixel/2):0);
    	
    	
    	float minZ = 11*pixel/2-(connections[2] !=null ?(11*pixel/2):0);
    	float maxZ = 1-11*pixel/2+(connections[4] !=null ?(11*pixel/2):0);
    	
    	
    	this.setBlockBounds(minX, minY,minZ, maxX, maxY, maxZ);
		
    }
        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
    }
    
    
    
	public TileEntity createNewTileEntity(World var1, int var2) {
		return null;
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
