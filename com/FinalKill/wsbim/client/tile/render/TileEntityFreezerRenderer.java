package com.FinalKill.wsbim.client.tile.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.model.ModelFreezer;
import com.FinalKill.wsbim.common.tileentity.TileEntityFreezer;

public class TileEntityFreezerRenderer extends TileEntitySpecialRenderer {

	 private ModelFreezer freezerModel = new ModelFreezer();
	 private static ResourceLocation texture = new ResourceLocation(WSBIM.modid.toLowerCase(), "textures/model/freezer.png");
	 
		public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
		TileEntityFreezer freezer = (TileEntityFreezer) tileentity;
		  int var9 = 5;

	      if (tileentity.hasWorldObj())
	      {
	          var9 = tileentity.getBlockMetadata();
	      }
	      else{
	    	  GL11.glScaled(1.1, 1.1, 1.1);
	    	  GL11.glTranslated(0, 0.1F, 0);
	      }

	      this.bindTexture(texture);
	      GL11.glPushMatrix();
	      //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	      // GL11.glScalef(1.0F, -1.0F, -1.0F);
	     GL11.glTranslated(x, y, z);
	     GL11.glTranslated(.5, 1.5, .5);
	    
	     //GL11.glTranslatef(0.5F, 0.5F, 0.5F);
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
	          float lidangle  = freezer.prevDoorAngle + (freezer.doorAngle - freezer.prevDoorAngle) * partialTick;
	      lidangle = 1.0F - lidangle;
	     lidangle = 1.0F - lidangle * lidangle * lidangle;
	     
	    
	    	
	    	   freezerModel.Door.rotateAngleY = -((lidangle * 3.141593F) / 2F);
	    	   //System.out.println(-((lidangle * 3.141593F) / 2F));
	    	    freezerModel.Handle.rotateAngleZ = ((lidangle * 3.141593F));
	    	  
	    	   // freezerModel.Handle.rotationPointX = 0F;
	    	
	    	 
	    	
	    		
	    	  
	    	  
	       this.freezerModel.renderAll(1F/16F);
	      //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	      GL11.glPopMatrix();
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			
		}
}
