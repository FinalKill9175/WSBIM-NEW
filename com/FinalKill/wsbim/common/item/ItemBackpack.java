package com.FinalKill.wsbim.common.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.util.EnumBackpackSize;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBackpack extends ItemArmor {
	
	public final EnumBackpackSize backpackSize;
	static int aioud;
	public static final ItemArmor.ArmorMaterial mat = EnumHelper.addArmorMaterial("Backpack", 100000, new int[]{0,0,0,0}, 0);
	
	public ItemBackpack(String name, EnumBackpackSize backpackSize){
		super(mat, aioud, 1);
		this.backpackSize = backpackSize;
		this.setUnlocalizedName(name);
		this.setMaxDamage(0);
		this.setTextureName(WSBIM.modid+":"+name);
		this.setCreativeTab(WSBIM.main.ourTab);
		GameRegistry.registerItem(this, name);
	}
	
	public boolean isMiningBackpack(ItemBackpack backpack){
		return backpack == WSBIM.itemMiningBackpack;
	}
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
    	if(p_77659_3_.isSneaking()){
    		//this.getServerPlayerObject(p_77659_3_).openGui(WSBIM.instance, 212, this.getServerWorldObj(p_77659_3_), 0, 0, 0);
    	}
    	else{
    		super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    	}
        return p_77659_1_;
    }
	protected EntityPlayer getServerPlayerObject(EntityPlayer e){
		return this.getServerWorldObj(e).getPlayerEntityByName(e.getDisplayName());
	}
	
	protected World getServerWorldObj(EntityPlayer old){
		
		World world = null;
		WorldServer[] list = MinecraftServer.getServer().worldServers;
		for(WorldServer ins : list){
		if(ins.provider.dimensionId==old.dimension)
			world = ins;
		}
		if(world==null)
			world = list[0];
		return world;
	}
	
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		if(stack.getItem() instanceof ItemBackpack){
			return WSBIM.modid+":"+"textures/model/armor/backpack.png";
		}
		return null;
		
	}
}
