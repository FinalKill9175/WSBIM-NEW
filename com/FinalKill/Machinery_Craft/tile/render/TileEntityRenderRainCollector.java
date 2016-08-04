package com.FinalKill.Machinery_Craft.tile.render;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL41;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.model.ModelRainCollector;
import com.FinalKill.Machinery_Craft.tile.TileEntityRainCollector;

public class TileEntityRenderRainCollector extends TileEntitySpecialRenderer {
	
	
	public ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/model/rainCollector.png");
	public ResourceLocation texture_water = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/blocks/water_flow.png");
	
	private ModelRainCollector model = new ModelRainCollector();

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float update) {
		
		
         
         short var10 = 180;
         float var11= -1F;
         float var12= 0F;
         float var13= 1F;

    
    	  GL11.glPushMatrix();
          GL11.glTranslatef((float)x + 0.5F, (float)y+ 1.5F,(float) z+ 0.5F);
          GL11.glRotatef((float)var10, var11, var12,var13);
          
          this.bindTexture(texture);
          GL11.glPushMatrix();
          
          
         TileEntityRainCollector t = (TileEntityRainCollector)tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
         int water_meta = tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
         if(water_meta >0){
        	 
        	 float water = water_meta;
        //	 System.out.println(water);
		GL11.glColor4f(1,1,1,0.75F);
	
        	 model.water.cubeList.clear();
        	 model. water = new ModelRenderer(model, 0, 24);
        	 model. water.addBox(0F, 0F, 0F, 10, new Float(water).intValue(), 10);
        	 model.water.setRotationPoint(-5F, -water+23, -5F); 
        	 model. water.setTextureSize(128, 64);
        	 model.   water.mirror = true;
             model.water.isHidden = false;
        	
         }
         else{
        	 model.water.isHidden = true;
         }
       //  System.out.println(t.water);
 
         model.renderModel(0.0625F);
         
          GL11.glPopMatrix();
          GL11.glPopMatrix();
			
	}

}
