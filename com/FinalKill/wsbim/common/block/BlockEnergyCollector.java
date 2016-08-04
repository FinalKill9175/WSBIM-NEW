package com.FinalKill.wsbim.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.util.condenservalues.EnumEnergyCollector;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnergyCollector extends BlockContainer{

	protected EnumEnergyCollector enumenergy;
	private TileEntity te;
	
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;
	    
	public BlockEnergyCollector(String unloc, EnumEnergyCollector enumenergy, TileEntity te) {
		super(Material.ground);
		this.setHardness(2.5F);
		this.setResistance(100F);
		this.setStepSound(soundTypeStone);
		this.setBlockName(unloc);		
		this.enumenergy = enumenergy;
		this.te = te;
	
	}

	public EnumEnergyCollector getType(){
		return enumenergy;
	}
	
	
	
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.topIcon : (p_149691_1_ == 0 ? this.bottomIcon : sideIcon);
    }
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.topIcon = reg.registerIcon(getType().getTexture().getBlockTopTexture());
    	this.sideIcon = reg.registerIcon(getType().getTexture().getBlockSideTexture());
    	this.bottomIcon = reg.registerIcon(WSBIM.modid+":"+"energyCollectorBottom");
    
    }
    
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		try {
			return te.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
