package com.FinalKill.wsbim;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FinalKill.wsbim.client.gui.GuiAdvancedCraftingTable;
import com.FinalKill.wsbim.client.gui.GuiAntimatterWorkbench;
import com.FinalKill.wsbim.client.gui.GuiArmorInfo;
import com.FinalKill.wsbim.client.gui.GuiBackpack;
import com.FinalKill.wsbim.client.gui.GuiCondenser;
import com.FinalKill.wsbim.client.gui.GuiFreezer;
import com.FinalKill.wsbim.client.gui.GuiSmallCrafting;
import com.FinalKill.wsbim.client.gui.GuiUncraftingTable;
import com.FinalKill.wsbim.client.gui.GuiUpgradedChest;
import com.FinalKill.wsbim.client.gui.GuiUpgradedFurnace;
import com.FinalKill.wsbim.client.gui.tabs.GuiContainerTab;
import com.FinalKill.wsbim.common.block.BlockFreezer;
import com.FinalKill.wsbim.common.block.BlockUncraftingTable;
import com.FinalKill.wsbim.common.inventory.ContainerAdvancedCraftingTable;
import com.FinalKill.wsbim.common.inventory.ContainerAntimatterWorkbench;
import com.FinalKill.wsbim.common.inventory.ContainerArmorInfo;
import com.FinalKill.wsbim.common.inventory.ContainerBackpack;
import com.FinalKill.wsbim.common.inventory.ContainerCondenser;
import com.FinalKill.wsbim.common.inventory.ContainerFreezer;
import com.FinalKill.wsbim.common.inventory.ContainerNormalCrafting;
import com.FinalKill.wsbim.common.inventory.ContainerSmallCrafting;
import com.FinalKill.wsbim.common.inventory.ContainerUncraftingTable;
import com.FinalKill.wsbim.common.inventory.ContainerUpgradedChest;
import com.FinalKill.wsbim.common.inventory.ContainerUpgradedFurnace;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.common.tileentity.TileEntityAdvancedCraftingTable;
import com.FinalKill.wsbim.common.tileentity.TileEntityAntimatterWorkbench;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;
import com.FinalKill.wsbim.util.IUpgradedChest;
import com.FinalKill.wsbim.util.IUpgradedFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class WSBIMGuiHandler implements IGuiHandler {

	
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		Block block = world.getBlock(x, y, z);
		switch(ID){
		
		
		}
	
		if(tile instanceof IUpgradedFurnace){
			IUpgradedFurnace tuf = (IUpgradedFurnace)tile;
			return new GuiUpgradedFurnace(player.inventory, (IUpgradedFurnace) tuf);
		}
		
		if(tile instanceof IUpgradedChest){
			IUpgradedChest tuf = (IUpgradedChest)tile;
			return new GuiUpgradedChest(player.inventory, (IUpgradedChest) tuf);
		}
		
		if(tile instanceof TileEntityAdvancedCraftingTable){
			return new GuiAdvancedCraftingTable(player.inventory, (TileEntityAdvancedCraftingTable)tile);
		}
		
		if(block instanceof BlockUncraftingTable){
			return new GuiUncraftingTable(player.inventory, world, x, y, z);
		}
		
		if(block instanceof BlockFreezer){
			return new GuiFreezer(player.inventory, (TileEntityFreezer)tile);
		}
		
		if(block instanceof com.FinalKill.wsbim.common.block.BlockCondenser){
			return new GuiCondenser(player.inventory, (com.FinalKill.wsbim.common.tileentity.TileEntityCondenser)tile);
		}
		
		if(tile instanceof TileEntityAntimatterWorkbench){
			return new GuiAntimatterWorkbench(player.inventory, (TileEntityAntimatterWorkbench)tile);
		}
		
		if(ID == 209){
			if(GuiContainerTab.guiToOpen !=null){
				return GuiContainerTab.guiToOpen;
			}
		}
		
		if(ID == 210){
			return new GuiArmorInfo(player);
		}
		
		if(ID == 211){
			if(player.inventory.hasItem(Item.getItemFromBlock(Blocks.crafting_table))){
				return new com.FinalKill.wsbim.client.gui.GuiNormalCrafting(player);
			}
			else return new GuiSmallCrafting(player);
		}
	
		
		if(ID == 212){
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.getCurrentEquippedItem();
			if(backpack !=null && backpack.getItem() instanceof ItemBackpack){
				return new GuiBackpack(backpack, player);
			}
		}
		
		if(ID == 213){
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.getCurrentEquippedItem();
			if(backpack !=null && backpack.getItem() instanceof ItemBackpack){
				return new GuiBackpack(backpack, player);
			}
		}
		
		return null;
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		Block block = world.getBlock(x, y, z);

		switch(ID){
		
		}
		if(tile instanceof IUpgradedFurnace){
			IUpgradedFurnace tuf = (IUpgradedFurnace)tile;
			return new ContainerUpgradedFurnace(player.inventory, (IUpgradedFurnace) tuf);
		}
		if(tile instanceof IUpgradedChest){
			IUpgradedChest tuf = (IUpgradedChest)tile;
			return new ContainerUpgradedChest(player.inventory, (IUpgradedChest) tuf);
		}
		if(tile instanceof TileEntityAdvancedCraftingTable){
			return new ContainerAdvancedCraftingTable(player.inventory, (TileEntityAdvancedCraftingTable)tile);
		}
		if(block instanceof BlockUncraftingTable){
			return new ContainerUncraftingTable(player.inventory, world, x, y, z);
		}
		if(block instanceof BlockFreezer){
			return new ContainerFreezer(player.inventory, (TileEntityFreezer)tile);
		}
		if(block instanceof com.FinalKill.wsbim.common.block.BlockCondenser){
			return new ContainerCondenser(player.inventory, (com.FinalKill.wsbim.common.tileentity.TileEntityCondenser)tile);
		}
		
		if(tile instanceof TileEntityAntimatterWorkbench){
			return new ContainerAntimatterWorkbench(player.inventory, (TileEntityAntimatterWorkbench)tile);
		}
		
		if(ID == 209){
			if(GuiContainerTab.containerToOpen !=null){
				//System.out.println("Yay");
				//BUG FIX: You wil crash if the gui is opened more that 2 times.
				//GuiContainerOverlayTabs.setObject(new ArrayList(), "crafters", Container.class, GuiContainerTab.containerToOpen);
				GuiContainerTab.containerToOpen.crafters = new ArrayList();
				//Changes the gui slots
				if(GuiContainerTab.containerToOpen.inventorySlots.size() > 0){
					//GuiContainerOverlayTabs.setObject(GuiContainerTab.containerToOpen.inventorySlots, "inventorySlots", Container.class, GuiContainerTab.containerToOpen);
				}
					return GuiContainerTab.containerToOpen;
				
			}
		}
		
		if(ID == 210){
			return new ContainerArmorInfo(player);
		}
		
		if(ID == 211){
			if(player.inventory.hasItem(Item.getItemFromBlock(Blocks.crafting_table))){
				return new ContainerNormalCrafting(player);
			}
			else return new ContainerSmallCrafting(player);
		}
		
		if(ID == 212){
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.getCurrentEquippedItem();
			if(backpack !=null){
				return new ContainerBackpack(backpack, player);
			}
		}
		
		if(ID == 213){
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.getCurrentEquippedItem();
			if(backpack !=null){
				return new ContainerBackpack(backpack, player);
			}
		}
			return null;
	}


}
