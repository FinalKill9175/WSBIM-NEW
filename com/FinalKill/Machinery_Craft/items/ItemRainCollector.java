package com.FinalKill.Machinery_Craft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.MachineryCraft;

public class ItemRainCollector extends Item{

	public ItemRainCollector() {

		}

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        Block var11 = par3World.getBlock(par4, par5, par6);

        if (var11 == Blocks.snow_layer && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
        {
            par7 = 1;
        }
        else if (var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush)
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if (par5 == 255 && MachineryCraft.blocks.rainCollector.getMaterial().isSolid())
        {
            return false;
        }
        else if (par3World.canPlaceEntityOnSide(MachineryCraft.blocks.rainCollector, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack))
        {
            int var12 = this.getMetadata(par1ItemStack.getItemDamage());
            int var13 = MachineryCraft.blocks.rainCollector.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, var12);

            if (par3World.setBlock(par4, par5, par6, MachineryCraft.blocks.rainCollector, var13, 3))
            {
                if (par3World.getBlock(par4, par5, par6) == MachineryCraft.blocks.rainCollector)
                {
                    MachineryCraft.blocks.rainCollector.onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
                    MachineryCraft.blocks.rainCollector.onPostBlockPlaced(par3World, par4, par5, par6, var13);
                }

                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), MachineryCraft.blocks.rainCollector.stepSound.func_150496_b(), ( MachineryCraft.blocks.rainCollector.stepSound.volume + 1.0F) / 2.0F, MachineryCraft.blocks.rainCollector.stepSound.getPitch() * 0.8F);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
