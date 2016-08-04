package com.FinalKill.wsbim.client.tile.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.util.EnumChestType;
import com.FinalKill.wsbim.util.IUpgradedChest;

public class TileEntityUpgradedChestRenderer extends TileEntitySpecialRenderer {

	 private ModelChest chestModel = new ModelChest();

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
	IUpgradedChest chest = (IUpgradedChest) tileentity;
	  int i = 5;

      if (tileentity.hasWorldObj())
      {
          i = tileentity.getBlockMetadata();
      }

      this.bindTexture(chest.getChestType().getTexture().getModelTexture());
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
      
     float lidangle  = chest.getPrevLidAngle() + (chest.getLidAngle() - chest.getPrevLidAngle()) * partialTick;
      lidangle = 1.0F - lidangle;
     lidangle = 1.0F - lidangle * lidangle * lidangle;
     
     if(chest.getChestType() != EnumChestType.OBSIDIAN){
       chestModel.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
     }
     else{
    	
    	   chestModel.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 4.5F);
    	   
     }
       this.chestModel.renderAll();
      //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		
	}
}
