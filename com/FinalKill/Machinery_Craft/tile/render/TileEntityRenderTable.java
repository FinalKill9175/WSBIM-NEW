package com.FinalKill.Machinery_Craft.tile.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.model.ModelTable;
import com.FinalKill.Machinery_Craft.tile.TileEntityTable;

public class TileEntityRenderTable extends TileEntitySpecialRenderer
{
    private static ResourceLocation texture;
    // = new ResourceLocation(MachineryCraft.MODID.toLowerCase(),"textures/model/wooden_table.png")
    private ModelTable model = new ModelTable();
  
    public TileEntityRenderTable(ResourceLocation table){
    	texture = table;
    	
    }
    public void renderTileEntityAt(TileEntityTable tileentity, double x, double y, double z, float f)
    {
    	 int var9 = 0;

         if (tileentity.hasWorldObj())
         {
             var9 = tileentity.getBlockMetadata();
         }

         
         short var10 = 180;
         float var11= -1F;
         float var12= 0F;
         float var13= 1F;

         if (var9 == 2)
         {
             var10 = 180;
             var11 = 0F;
             var12 = 0F;
             var13 = 1F;
         }

         if (var9 == 3)
         {
             var10 = 180;
             var11 = 1F;
             var12 = 0F;
             var13 = 0F;
         }

         if (var9 == 4)
         {
             var10 = 180;
             var11 = 1F;
             var12 = 0F;
             var13 = 1F;
         }

         if (var9 == 5)
         {
             var10 = 90*2;
             var11= -1F;
             var12= 0F;
             var13= 1F;
         }
    
    	  GL11.glPushMatrix();
          GL11.glTranslatef((float)x + 0.5F, (float)y+ 1.5F,(float) z+ 0.5F);
          GL11.glRotatef((float)var10, var11, var12,var13);
          
          this.bindTexture(texture);
          GL11.glPushMatrix();
          model.renderModel(0.0625F);
          GL11.glPopMatrix();
          GL11.glPopMatrix();
          
          
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((TileEntityTable)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}
