package com.FinalKill.wsbim.client.tile.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.tileentity.TileEntityCondenser;
import com.FinalKill.wsbim.util.EnumChestType;

public class TileEntityCondenserRenderer extends TileEntitySpecialRenderer {

	 private ModelChest chestModel = new ModelChest();
	 private final ResourceLocation texture = new ResourceLocation(WSBIM.modid, "textures/model/condenser.png");
	 
	 
		public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
		TileEntityCondenser chest = (TileEntityCondenser) tileentity;
		  int i = 5;

	      if (tileentity.hasWorldObj())
	      {
	          i = tileentity.getBlockMetadata();
	      }

	      this.bindTexture(texture);
	      GL11.glPushMatrix();
	      //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	     GL11.glColor4f(1,1,1,1);
	      GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
	      GL11.glScalef(1.0F, -1.0F, -1.0F);
	      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	      short short1 = 0;

	      if (i == 2)
	      {
	          short1 = 180;
	      }

	      if (i == 3)
	      {
	          short1 = 0;
	      }

	      if (i == 4)
	      {
	          short1 = 90;
	      }

	      if (i == 5)
	      {
	          short1 = -90;
	      }

	      GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
	      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	      
	     float lidangle  = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTick;
	      lidangle = 1.0F - lidangle;
	     lidangle = 1.0F - lidangle * lidangle * lidangle;
	     
	     
	    	
	    	   chestModel.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2F);
	    	   
	     
	       this.chestModel.renderAll();
	      //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	      GL11.glPopMatrix();
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			
		}

}
