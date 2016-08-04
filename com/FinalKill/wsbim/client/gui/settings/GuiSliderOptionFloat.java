package com.FinalKill.wsbim.client.gui.settings;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.WSBIMOptions;
import com.FinalKill.wsbim.option.OptionFloat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderOptionFloat extends GuiButton
{
    private float dragged;
    public boolean dragging;
    private OptionFloat option;
    private static final String __OBFID = "CL_00000680";


    public GuiSliderOptionFloat(int p_i45017_1_, int p_i45017_2_, int p_i45017_3_, OptionFloat p_i45017_4_)
    {
        super(p_i45017_1_, p_i45017_2_, p_i45017_3_, 150, 20, "");
        this.dragged = 1.0F;
        this.option = p_i45017_4_;
       Minecraft minecraft = Minecraft.getMinecraft();
        this.dragged = this.normalizeValue(option.getStartingValue(), option.getMin(), option.getMax());
        this.displayString = option.guiName + ": "+option.getStartingValue();
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean p_146114_1_)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.dragged = (float)(p_146119_2_ - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.dragged < 0.0F)
                {
                    this.dragged = 0.0F;
                }

                if (this.dragged > 1.0F)
                {
                    this.dragged = 1.0F;
                }

                float f = this.denormalizeValue(this.dragged, option.getMin(), option.getMax());
                Field field = null;
				try {
					field = WSBIMOptions.class.getDeclaredField(option.variableName);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                field.setAccessible(true);
                try {
					field.setFloat(WSBIM.options, f);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                this.dragged = this.normalizeValue(f, option.getMin(), option.getMax());
                this.displayString = option.guiName + ": "+f;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.dragged * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.dragged * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float p_148266_1_, float min, float max)
    {
        return MathHelper.clamp_float((this.snapToStepClamp(p_148266_1_, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float p_148262_1_, float min, float max)
    {
        return this.snapToStepClamp(min + (max - min) * MathHelper.clamp_float(p_148262_1_, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float p_148268_1_, float min, float max)
    {
        p_148268_1_ = this.snapToStep(p_148268_1_);
        return MathHelper.clamp_float(p_148268_1_, min, max);
    }
    
    protected float snapToStep(float p_148264_1_)
    {
        if (option.getValueStep() > 0.0F)
        {
            p_148264_1_ = option.getValueStep() * (float)Math.round(p_148264_1_ / option.getValueStep());
        }

        return p_148264_1_;
    }
    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
        if (super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_))
        {
            this.dragged = (float)(p_146116_2_ - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.dragged < 0.0F)
            {
                this.dragged = 0.0F;
            }

            if (this.dragged > 1.0F)
            {
                this.dragged = 1.0F;
            }

           
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int p_146118_1_, int p_146118_2_)
    {
        this.dragging = false;
    }
    long ms = 0L;
    boolean flag = false;
    public boolean showingTooltip;
  
    public void drawDescription(Minecraft mc, int x, int y){
    	List<String> list = Description.getItemDescription(option.variableName);
    	if(!flag){
    		showingTooltip = false;
    	}
    	
    	if(list !=null && list.size() > 0 && ((x >= xPosition && x <= this.xPosition + this.width) && (y >= this.yPosition && y <= this.yPosition + this.height)) && flag){
    		
    		if(!(ms+1250 >= System.currentTimeMillis())){
    			this.showingTooltip = true;
    			this.drawHoveringText(list, this.xPosition-8, this.yPosition + this.height * 2 - 4, mc.fontRenderer);
    		}
    	}
    	else{
    		flag = false;
    		showingTooltip = false;
    	}
    	
    	if(!flag){
    		ms = System.currentTimeMillis();
    		flag = true;
    	}
    	this.drawButton(mc, x, y);
    }
    
    RenderItem itemRender = new RenderItem();
    protected void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font)
    {
        if (!p_146283_1_.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glPushMatrix();
            int k = 0;
            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int j2 = p_146283_2_ + 12;
            int k2 = p_146283_3_ - 12;
            int i1 = 8;

            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }

     

            
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }

            GL11.glPopMatrix();

        }
    }
}