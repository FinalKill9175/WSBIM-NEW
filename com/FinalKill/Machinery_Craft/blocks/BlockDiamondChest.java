package com.FinalKill.Machinery_Craft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockDiamondChest extends BlockContainer {

	




	public BlockDiamondChest(String unlocalized_name_and_tag) {
		super(Material.iron);
			
			this.setHardness(5.0F);
		this.setResistance(50);
		this.setBlockTextureName(unlocalized_name_and_tag);
		this.setBlockName(unlocalized_name_and_tag);
	
			GameRegistry.registerBlock(this, unlocalized_name_and_tag);
		
		
		
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote||player.isSneaking()){
			
			return false;
		}
		else{
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,2, world, x, y, z);
			return true;
		}
		
		
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityDiamondChest();
	}
	
	
	

}
