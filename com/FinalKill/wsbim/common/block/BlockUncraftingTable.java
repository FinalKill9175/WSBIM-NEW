package com.FinalKill.wsbim.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUncraftingTable extends Block {
	
	@SideOnly(Side.CLIENT)
	private IIcon side;
	
	@SideOnly(Side.CLIENT)
	private IIcon front;
	
	@SideOnly(Side.CLIENT)
	private IIcon top;
	
	public BlockUncraftingTable() {
		super(Material.wood);
		this.setHardness(2.5F);
		this.setResistance(10F);
		this.setCreativeTab(WSBIM.main.ourTab);
		this.setBlockName("uncraftingTable");
		this.setBlockTextureName("uncraftingTable");
		this.setStepSound(soundTypeWood);
	}
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return side == 1 ? top: (side == 0? Blocks.planks.getBlockTextureFromSide(side) : (side == 3? front : this.side));
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.side = reg.registerIcon(WSBIM.modid + ":"+getTextureName()+"_side");
		this.top = reg.registerIcon(WSBIM.modid + ":" + this.getTextureName() + "_top");
		this.front = reg.registerIcon(WSBIM.modid + ":" + this.getTextureName() + "_front");
	}
}
