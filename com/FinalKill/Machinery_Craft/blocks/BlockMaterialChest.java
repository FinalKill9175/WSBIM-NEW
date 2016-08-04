package com.FinalKill.Machinery_Craft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMaterialChest extends BlockContainer {

	public static final int iron_chest = 0;
	public static final int gold_chest = 1;
	public static final int diamond_chest = 2;
	public static int[] types = {iron_chest, gold_chest, diamond_chest};
	public static BlockMaterialChest[] materialChests = new BlockMaterialChest[20];
	private static TileEntity tileEntity;




	public static int type;
	public BlockMaterialChest(int type, String unlocalized_name_and_tag, TileEntity tileentity) {
		super(Material.iron);
		if(type == iron_chest){
			
			this.setHardness(5.0F);
		}
		else if(type == gold_chest){
			this.setHardness(0.5F);
			
		}
		else if(type == diamond_chest){
			
			this.setHardness(5.0F);
		}
		this.setResistance(50);
		this.setBlockTextureName(unlocalized_name_and_tag);
		this.setBlockName(unlocalized_name_and_tag);
	
		tileEntity = tileentity;
		GameRegistry.registerBlock(this, unlocalized_name_and_tag);
		
		this.type = type;
		materialChests[type] = this;
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote||player.isSneaking()){
			
			return false;
		}
		else{
			FMLNetworkHandler.openGui(player, MachineryCraft.instance,((BlockMaterialChest) world.getBlock(x, y, z)).getType(), world, x, y, z);
			return true;
		}
		
		
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return tileEntity;
	}
	
	
	public int getType(){
		
		return type;
	}

}
