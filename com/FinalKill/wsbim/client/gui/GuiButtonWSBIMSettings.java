package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import com.FinalKill.wsbim.client.gui.settings.GuiWSBIMSettings;

public class GuiButtonWSBIMSettings extends GuiButton{

    public GuiButtonWSBIMSettings(int p_i1021_1_, int p_i1021_2_,
			int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		}

	/**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
    	if(this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height){
    		p_146116_1_.displayGuiScreen(new GuiWSBIMSettings(p_146116_1_, p_146116_1_.currentScreen));
    		return true;
    	}
    	else return false;
    }
}
