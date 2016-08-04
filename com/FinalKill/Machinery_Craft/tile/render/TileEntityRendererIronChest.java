package com.FinalKill.Machinery_Craft.tile.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.Machinery_Craft.tile.TileEntityIronChest;

public class TileEntityRendererIronChest extends TileEntitySpecialRenderer
{
    private static final ResourceLocation texture = new ResourceLocation("textures/entity/chest/ender.png");
    private ModelChest model = new ModelChest();
    private static final String __OBFID = "CL_00000967";

    public void renderTileEntityAt(TileEntityIronChest tileentity, double x, double y, double z, float f)
    {
        int var9 = 0;

        if (tileentity.hasWorldObj())
        {
            var9 = tileentity.getBlockMetadata();
        }

        this.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        
        short var10 = 0;

        if (var9 == 2)
        {
            var10 = 180;
        }

        if (var9 == 3)
        {
            var10 = 0;
        }

        if (var9 == 4)
        {
            var10 = 90;
        }

        if (var9 == 5)
        {
            var10 = -90;
        }
        float var11 = tileentity.field_145975_i + (tileentity.field_145972_a - tileentity.field_145975_i) * f;
        var11 = 1.0F - var11;
        var11 = 1.0F - var11 * var11 * var11;
        this.model.chestLid.rotateAngleX = -(var11 * (float)Math.PI / 2.0F);

        GL11.glRotatef((float)var10, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
         this.model.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((TileEntityIronChest)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}
