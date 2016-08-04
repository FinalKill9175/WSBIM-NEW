package com.FinalKill.wsbim.client.tile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.item.render.RenderAbstractItem;
import com.FinalKill.wsbim.client.model.ModelTable;
import com.FinalKill.wsbim.common.tileentity.TileEntityAdvancedCraftingTable;

public class TileEntityAdvancedCraftingTableRenderer extends
		TileEntitySpecialRenderer {

	private static ResourceLocation texture = new ResourceLocation(WSBIM.modid, "textures/model/advancedCraftingTable.png");
	
	private ModelTable model = new ModelTable();
	
	private RenderAbstractItem itemRenderer = new RenderAbstractItem(Minecraft.getMinecraft());
	
	private TileEntity e;
	
	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float partialTick) {
		/**
		if(t !=null){
		if(t.getWorldObj() !=null){
			this.renderTileEntityInWorld(t, x, y, z, partialTick);
		}
		else{
			this.renderTileEntityInInventory(t, x, y, z, partialTick);
		}
		*/
		GL11.glPushMatrix();
		this.bindTexture(texture);
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(.5, 1.5, .5);
		
		int var9 = 0;
		
		if(t.hasWorldObj()){
			var9 = t.getBlockMetadata();
		}
		 short var10 = 180;
         float var11= -1F;
         float var12= 0F;
         float var13= 1F;

         if (var9 == 5)
         {
             var10 = 180;
             var11 = 0F;
             var12 = 0F;
             var13 = 1F;
         }

         if (var9 == 4)
         {
             var10 = 180;
             var11 = 1F;
             var12 = 0F;
             var13 = 0F;
         }

         if (var9 == 3)
         {
             var10 = 180;
             var11 = -1F;
             var12 = 0F;
             var13 = 1F;
         }

         if (var9 == 2)
         {
             var10 = 90*2;
             var11= 1F;
             var12= 0F;
             var13= 1F;
         }
		
         GL11.glRotatef(var10, var11, var12, var13);
     	GL11.glRotatef(180, 0, 1, 0);
		
		GL11.glPushMatrix();
		
		model.renderModel(1F/16F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	/**
	public void renderTileEntityInWorld(TileEntity t, double x, double y, double z, float partialTick){
		e = t;
		TileEntityAdvancedCraftingTable tile = null;
		TileEntityAdvancedCraftingTable tile_c = (TileEntityAdvancedCraftingTable)t;
		int posX = t.xCoord;
		int posY = t.yCoord;
		int posZ = t.zCoord;
		if(Minecraft.getMinecraft().isIntegratedServerRunning()){
        	if(Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(posX, posY, posZ) !=null){
        		if(Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(posX, posY, posZ) instanceof TileEntityAdvancedCraftingTable){
        			tile = (TileEntityAdvancedCraftingTable) Minecraft.getMinecraft().getIntegratedServer().getEntityWorld().getTileEntity(posX, posY, posZ);
        		}
        	}
		}
        	else if(!Minecraft.getMinecraft().isIntegratedServerRunning() && t.getWorldObj().isRemote && MinecraftServer.getServer() !=null && MinecraftServer.getServer().isServerRunning()){
				if(MinecraftServer.getServer().getEntityWorld().getTileEntity(posX, posY, posZ) !=null){
					if(MinecraftServer.getServer().getEntityWorld().getTileEntity(posX, posY, posZ) instanceof TileEntityAdvancedCraftingTable){
						tile = (TileEntityAdvancedCraftingTable)MinecraftServer.getServer().getEntityWorld().getTileEntity(posX, posY, posZ);
					}
				}
			}
			
        
        else if(Minecraft.getMinecraft().isIntegratedServerRunning() &&  t.getWorldObj().isRemote){
        	tile = tile_c;
        }
		
		if(tile !=null){
			
			GL11.glPushMatrix();
			this.bindTexture(texture);
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(.5, 1.5, .5);
			int var9 = t.getBlockMetadata();
			 short var10 = 180;
	         float var11= -1F;
	         float var12= 0F;
	         float var13= 1F;

	         if (var9 == 5)
	         {
	             var10 = 180;
	             var11 = 0F;
	             var12 = 0F;
	             var13 = 1F;
	         }

	         if (var9 == 4)
	         {
	             var10 = 180;
	             var11 = 1F;
	             var12 = 0F;
	             var13 = 0F;
	         }

	         if (var9 == 3)
	         {
	             var10 = 180;
	             var11 = -1F;
	             var12 = 0F;
	             var13 = 1F;
	         }

	         if (var9 == 2)
	         {
	             var10 = 90*2;
	             var11= 1F;
	             var12= 0F;
	             var13= 1F;
	         }
			
	         GL11.glRotatef(var10, var11, var12, var13);
	     	GL11.glRotatef(180, 0, 1, 0);
			
			GL11.glPushMatrix();
			
			model.renderModel(1F/16F);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			
			if(tile.matrixStacks[0] !=null && tile.matrixStacks[0].stackSize > 0){
				// 3rd stack 1.3, -0.435, -0.55, 0.35, 0.85, -0.35,
				this.renderItemStack(tile.matrixStacks[0], 1.3, -0.435, 0.55, -0.35, 0.85, -0.35, partialTick);
			}
			
			
			if(tile.matrixStacks[1] !=null && tile.matrixStacks[1].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[1], 1.3, -0.435, -0.0, 0.0, 0.85, -0.35, partialTick);
			}
			
			if(tile.matrixStacks[2] !=null && tile.matrixStacks[2].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[2], 1.3, -0.435, -0.55, 0.35, 0.85, -0.35, partialTick);
			}
			if(tile.matrixStacks[3] !=null && tile.matrixStacks[3].stackSize > 0){
				//5th 1.3, 0.1, -0.55, 0.35, 0.85, -0.0
				this.renderItemStack(tile.matrixStacks[3],1.3, 0.1, 0.55, -0.35, 0.85, -0.0 , partialTick);
			}
			if(tile.matrixStacks[4] !=null && tile.matrixStacks[4].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[4], 1.3, 0.1, -0.0, 0.0, 0.85, -0.0, partialTick);
			}
			if(tile.matrixStacks[5] !=null && tile.matrixStacks[5].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[5], 1.3, 0.1, -0.55, 0.35, 0.85, -0.0 , partialTick);
			}
			if(tile.matrixStacks[6] !=null && tile.matrixStacks[6].stackSize > 0){
				//9th 1.3, 0.65, -0.55, 0.35, 0.85, 0.35
				this.renderItemStack(tile.matrixStacks[6], 1.3, 0.65, 0.55, -0.35, 0.85, 0.35, partialTick);
			}
			if(tile.matrixStacks[7] !=null && tile.matrixStacks[7].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[7], 1.3, 0.65, -0.0, 0.0, 0.85, 0.35, partialTick);
			}
			if(tile.matrixStacks[8] !=null && tile.matrixStacks[8].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[8], 1.3, 0.65, -0.55, 0.35, 0.85, 0.35, partialTick);
			}
			/**
			if(tile.matrixStacks[0] !=null && tile.matrixStacks[0].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[0], 1.3, -0.435, -0.55, 0.35, 0.85, -0.35, partialTick);
			}
			
			if(tile.matrixStacks[1] !=null && tile.matrixStacks[1].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[3], 1.3, -0.435, -0.0, 0.0, 0.85, -0.35, partialTick);
			}
			
			if(tile.matrixStacks[2] !=null && tile.matrixStacks[2].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[2], 1.3, -0.435, 0.55, -0.35, 0.85, -0.35, partialTick);
			}
			if(tile.matrixStacks[3] !=null && tile.matrixStacks[3].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[3], 1.3, 0.1, -0.55, 0.35, 0.85, -0.0, partialTick);
			}
			if(tile.matrixStacks[4] !=null && tile.matrixStacks[4].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[4], 1.3, 0.1, -0.0, 0.0, 0.85, -0.0, partialTick);
			}
			
			if(tile.matrixStacks[5] !=null && tile.matrixStacks[5].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[5], 1.3, 0.1, 0.55, -0.35, 0.85, -0.0, partialTick);
			}
			
			if(tile.matrixStacks[6] !=null && tile.matrixStacks[6].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[6], 1.3, 0.65, -0.55, 0.35, 0.85, 0.35, partialTick);
			}
			if(tile.matrixStacks[7] !=null && tile.matrixStacks[7].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[7], 1.3, 0.65, -0.0, 0.0, 0.85, 0.35, partialTick);
			}
			
			if(tile.matrixStacks[8] !=null && tile.matrixStacks[8].stackSize > 0){
				this.renderItemStack(tile.matrixStacks[8], 1.3, 0.65, 0.55, -0.35, 0.85, 0.35, partialTick);
			}
			
			GL11.glPopMatrix();
			
			GL11.glPopMatrix();
		}
	}
	
	public void renderTileEntityInInventory(TileEntity t, double x, double y, double z, float partialTick){
		
		GL11.glPushMatrix();
		this.bindTexture(texture);
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(.5, 1.5, .5);
		 short var10 = 180;
         float var11= -1F;
         float var12= 0F;
         float var13= 1F;

         GL11.glRotatef(var10, var11, var12, var13);
     	GL11.glRotatef(180, 0, 1, 0);
		
		GL11.glPushMatrix();
		
		model.renderModel(1F/16F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	*/
	public void renderItemStack(ItemStack stack, double x, double y, double z, double x1, double y1, double z1, float partialTick){
		//if(Minecraft.getMinecraft().gameSettings.fancyGraphics || stack.getItem() instanceof ItemBlock){
			GL11.glPushMatrix();
			GL11.glScaled(.35, .35, .35);
			
				GL11.glRotatef(270, 1, 0, 0);
				//GL11.glRotatef(180, -1, 1, 0);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(90,0, 0, 1);
				
				GL11.glRotatef(180, 1, 0, 1);
				GL11.glRotatef(270, 0, 1, 0);
				
				this.itemRenderer.onlyRenderFancy = true;
			   this.itemRenderer.renderItemStack(x, y, z, partialTick, stack, true, false);
			   GL11.glPopMatrix();
			//}
		/**
			else{
				int var9 = e.getBlockMetadata();
				 short var10 = 180;
		         float var11= -1F;
		         float var12= 0F;
		         float var13= 1F;

		         if (var9 == 5)
		         {
		             var10 = 180;
		             var11 = 0F;
		             var12 = 0F;
		             var13 = 1F;
		         }

		         if (var9 == 4)
		         {
		             var10 = 180;
		             var11 = 1F;
		             var12 = 0F;
		             var13 = 0F;
		         }

		         if (var9 == 3)
		         {
		             var10 = 180;
		             var11 = -1F;
		             var12 = 0F;
		             var13 = 1F;
		         }

		         if (var9 == 2)
		         {
		             var10 = 90*2;
		             var11= 1F;
		             var12= 0F;
		             var13= 1F;
		         }
				
		         //GL11.glRotatef(var10, var11, var12, var13);
		     //	System.out.println(var9 + ":"+e.xCoord + ":" + e.yCoord + ":" + e.zCoord) ;
				GL11.glPushMatrix();
				GL11.glScaled(.5, .5, .5);
				GL11.glRotatef(var9 == 2 ? 180 : (var9 == 3? 0 : (var9 == 4 ? 270 : (var9 == 5? 90 : 0))), 0, 1, 0);
				this.itemRenderer.renderItemStack(x1, y1, z1, partialTick, stack, true, false);
				GL11.glPopMatrix();
			 }
		*/
	}
}
