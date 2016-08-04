package com.FinalKill.Machinery_Craft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FinalKill.Machinery_Craft.gui.GuiDiamondChest;
import com.FinalKill.Machinery_Craft.gui.GuiGenerator;
import com.FinalKill.Machinery_Craft.gui.GuiGoldChest;
import com.FinalKill.Machinery_Craft.gui.GuiIronChest;
import com.FinalKill.Machinery_Craft.gui.GuiIronFurnace;
import com.FinalKill.Machinery_Craft.gui.GuiMacerator;
import com.FinalKill.Machinery_Craft.gui.GuiPowerFurnace;
import com.FinalKill.Machinery_Craft.gui.GuiRainCollector;
import com.FinalKill.Machinery_Craft.gui.GuiTrashcan;
import com.FinalKill.Machinery_Craft.inventory.ContainerDiamondChest;
import com.FinalKill.Machinery_Craft.inventory.ContainerGenerator;
import com.FinalKill.Machinery_Craft.inventory.ContainerGoldChest;
import com.FinalKill.Machinery_Craft.inventory.ContainerIronChest;
import com.FinalKill.Machinery_Craft.inventory.ContainerIronFurnace;
import com.FinalKill.Machinery_Craft.inventory.ContainerMacerator;
import com.FinalKill.Machinery_Craft.inventory.ContainerPowerFurnace;
import com.FinalKill.Machinery_Craft.inventory.ContainerRainCollector;
import com.FinalKill.Machinery_Craft.inventory.ContainerTrashcan;
import com.FinalKill.Machinery_Craft.tile.TileEntityDiamondChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityGenerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityGoldChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;
import com.FinalKill.Machinery_Craft.tile.TileEntityIronFurnace;
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityPowerFurnace;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;
import com.FinalKill.Machinery_Craft.tile.TileEntityTrashcan;

import cpw.mods.fml.common.network.IGuiHandler;

public class FinalKillGuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
			switch(ID){
		
	
         		 
         	 case MachineryCraft.guiMacerator:
         		 if(tileentity instanceof TileEntityMacerator){
         			 return new GuiMacerator(player.inventory, (TileEntityMacerator)tileentity);
         			 
         		 }
         		 
         		
         		 
         	 case MachineryCraft.trashCanGUI:
         		 if(tileentity instanceof TileEntityTrashcan){
         			 
         			 return new GuiTrashcan(player.inventory, (TileEntityTrashcan)tileentity);
         			 
         		 }
         		 
         	 case MachineryCraft.guiGenerator:
         		 if(tileentity instanceof TileEntityGenerator){
         			 
         			 return new GuiGenerator(player.inventory, (TileEntityGenerator)tileentity);
         			 
         		 }
         		 
         		 
         	 case MachineryCraft.guiRainCollector:
         		 if(tileentity instanceof TileEntityRainCollector){
         			 
         			 return new GuiRainCollector(player.inventory, (TileEntityRainCollector)tileentity);

         			 
         		 }
         		 
         	 case MachineryCraft.guiElectricFurnace:
         		 if(tileentity instanceof TileEntityPowerFurnace){
         			 
         			 return new GuiPowerFurnace(player.inventory, (TileEntityPowerFurnace)tileentity);
         			 
         		 }
         		 
         	 case MachineryCraft.guiIronFurnace:
         		 if(tileentity instanceof TileEntityIronFurnace){
         			 return new GuiIronFurnace(player.inventory, (TileEntityIronFurnace)tileentity);
         		 }
         	 
         		 
		}
		

			if(tileentity instanceof TileEntityIronChest){
	
		return new GuiIronChest(player.inventory, (TileEntityIronChest)tileentity);
		
	}
	
			else if(tileentity instanceof TileEntityGoldChest){
		
		return new GuiGoldChest(player.inventory, (TileEntityGoldChest)tileentity);
		
	}
			else if(tileentity instanceof TileEntityDiamondChest){
		return new GuiDiamondChest(player.inventory, (TileEntityDiamondChest)tileentity);
		
	}

	
		

			
			return null;
			
	}	
	

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
	
		switch(ID){
		
		
		 case MachineryCraft.guiMacerator:
     		 if(tileentity instanceof TileEntityMacerator){
     			 return new ContainerMacerator(player.inventory, (TileEntityMacerator)tileentity);
     			 
     		 }
			
		 
		 case MachineryCraft.trashCanGUI:
     		 if(tileentity instanceof TileEntityTrashcan){
     			 
     			 return new ContainerTrashcan(player.inventory, (TileEntityTrashcan)tileentity);
     			 
     		 }
		 case MachineryCraft.guiGenerator:
     		 if(tileentity instanceof TileEntityGenerator){
     			 
     			 return new ContainerGenerator(player.inventory, (TileEntityGenerator)tileentity);
     			 
     		 }
		 case MachineryCraft.guiRainCollector:
     		 if(tileentity instanceof TileEntityRainCollector){
     			 
     			 return new ContainerRainCollector(player.inventory, (TileEntityRainCollector)tileentity);

     			 
     		 }
		 case MachineryCraft.guiElectricFurnace:
     		 if(tileentity instanceof TileEntityPowerFurnace){
     			 
     			 return new ContainerPowerFurnace(player.inventory, (TileEntityPowerFurnace)tileentity);
     			 
     		 }
		 case MachineryCraft.guiIronFurnace:
     		 if(tileentity instanceof TileEntityIronFurnace){
     			 return new ContainerIronFurnace(player.inventory, (TileEntityIronFurnace)tileentity);
     		 }
		}
		if(tileentity instanceof TileEntityIronChest){
			
			return new ContainerIronChest(player.inventory, (TileEntityIronChest)tileentity);
			
		}
		
				else if(tileentity instanceof TileEntityGoldChest){
			
			return new ContainerGoldChest(player.inventory, (TileEntityGoldChest)tileentity);
			
		}
				else if(tileentity instanceof TileEntityDiamondChest){
			return new ContainerDiamondChest(player.inventory, (TileEntityDiamondChest)tileentity);
			
			
			
			
		}

		
			
			
		
		
			
			return null;
	}

}
