package com.FinalKill.wsbim.client.gui.settings;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.WSBIMOptions.OptionType;
import com.FinalKill.wsbim.option.Option;
import com.FinalKill.wsbim.option.OptionFloat;
import com.FinalKill.wsbim.option.OptionInteger;

public class GuiWSBIMSettings extends GuiScreen{
	private Minecraft mc;
	private GuiScreen prevScreen;
	
	public GuiWSBIMSettings(Minecraft minecraft, GuiScreen prevScreen){
		this.mc = minecraft;
		this.prevScreen = prevScreen;
	}
	
    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {
    	super.onGuiClosed();
    	
    	try {
			WSBIM.options.saveOptions(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
    	this.drawDefaultBackground();
    	
    	 int k;

         for (k = 0; k < this.buttonList.size(); ++k)
         {
        	 
        	   
             ((GuiButton)this.buttonList.get(k)).drawButton(this.mc, p_73863_1_, p_73863_2_);
             
       	  if(this.buttonList.get(k) instanceof GuiSliderOptionFloat){
          	 ((GuiSliderOptionFloat)this.buttonList.get(k)).drawDescription(mc, p_73863_1_, p_73863_2_);
          	if(this.buttonList.size() > k + 2 && this.buttonList.get(k+2) !=null){
         		 if(((GuiSliderOptionFloat)this.buttonList.get(k)).showingTooltip){
         			((GuiButton)this.buttonList.get(k+2)).visible = false;
         	 	 }
         		 else{
         			((GuiButton)this.buttonList.get(k+2)).visible = true;
         		 }
         	 }
          	
          	if(this.buttonList.size() > k + 4 && this.buttonList.get(k+4) !=null){
        		 if(((GuiSliderOptionFloat)this.buttonList.get(k)).showingTooltip){
        			((GuiButton)this.buttonList.get(k+4)).visible = false;
        	 	 }
        		 else{
        			((GuiButton)this.buttonList.get(k+4)).visible = true;
        		 }
        	 }
           }
           
           if(this.buttonList.get(k) instanceof GuiSliderOptionInteger){
          	 ((GuiSliderOptionInteger)this.buttonList.get(k)).drawDescription(mc, p_73863_1_, p_73863_2_);
          	 if(this.buttonList.size() > k + 2 && this.buttonList.get(k+2) !=null){
          		 if(((GuiSliderOptionInteger)this.buttonList.get(k)).showingTooltip){
          			((GuiButton)this.buttonList.get(k+2)).visible = false;
          	 	 }
          		 else{
          			((GuiButton)this.buttonList.get(k+2)).visible = true;
          		 }
          	 }
          	 if(this.buttonList.size() > k + 4 && this.buttonList.get(k+4) !=null){
          		 if(((GuiSliderOptionInteger)this.buttonList.get(k)).showingTooltip){
          			((GuiButton)this.buttonList.get(k+2)).visible = false;
          	 	 }
          		 else{
          			((GuiButton)this.buttonList.get(k+2)).visible = true;
          		 }
          	 }
           }
           if(this.buttonList.get(k) instanceof GuiButtonOption){
          	 ((GuiButtonOption)this.buttonList.get(k)).drawDescription(mc, p_73863_1_, p_73863_2_);
          	if(this.buttonList.size() > k + 2 && this.buttonList.get(k+2) !=null){
         		 if(((GuiButtonOption)this.buttonList.get(k)).showingTooltip){
         			((GuiButton)this.buttonList.get(k+2)).visible = false;
         	 	 }
         		 else{
         			((GuiButton)this.buttonList.get(k+2)).visible = true;
         		 }
         	 }
         	if(this.buttonList.size() > k + 4 && this.buttonList.get(k+4) !=null){
        		 if(((GuiButtonOption)this.buttonList.get(k)).showingTooltip){
        			((GuiButton)this.buttonList.get(k+4)).visible = false;
        	 	 }
        		 else{
        			((GuiButton)this.buttonList.get(k+4)).visible = true;
        		 }
        	 }
           }
           
            
         }

         for (k = 0; k < this.labelList.size(); ++k)
         {
             ((GuiLabel)this.labelList.get(k)).func_146159_a(this.mc, p_73863_1_, p_73863_2_);
         }
         
        this.drawCenteredString(this.fontRendererObj, I18n.format("wsbimSettings.title", new Object[0]), this.width / 2, 15, 16777215);

        
        
    }

	public void initGui(){
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
		
		for(int i = 0; i < WSBIM.options.optionsList.size(); i++){
			Option option = WSBIM.options.optionsList.get(i);
			if(option !=null){
				if(i < 8){
					Position pos = this.getPos(i);
					if(pos !=null && option.avalableOption){
						if((option.type !=OptionType.INT) && (option.type !=OptionType.FLOAT) && (option.type != OptionType.STRING) && (option.type !=OptionType.GUI)){
							this.buttonList.add(new GuiButtonOption(WSBIM.options.optionsList.get(i), -1, pos.x, pos.y-24, 150, 20, WSBIM.options.optionsList.get(i).guiName));
						}
						else if(option.type == OptionType.GUI){
							this.buttonList.add(new GuiButtonOpenGUI(option, -1, pos.x, pos.y-24, 150, 20, option.guiName));
						}
						else if(option.type == OptionType.INT){
							this.buttonList.add(new GuiSliderOptionInteger(-1, pos.x, pos.y-24, (OptionInteger) option));
						}
						else if(option.type == OptionType.FLOAT){
							this.buttonList.add(new GuiSliderOptionFloat(-1, pos.x, pos.y-24, (OptionFloat)option));
						}
					}
				}
			}
		}
	}
	
	public Position getPos(int num){
		switch(num){
			case 0:
				return new Position(this.width / 2 - 155, this.height / 6 + 72 - 6);
			case 1:
				return new Position(this.width / 2 + 5, this.height / 6 + 72 - 6);
			case 2:
				return new Position(this.width / 2 - 155, this.height / 6 + 96 - 6);
			case 3:
				return new Position(this.width / 2 + 5, this.height / 6 + 96 - 6);
			case 4:
				return new Position(this.width / 2 - 155, this.height / 6 + 120 - 6);
			case 5:
				return new Position(this.width / 2 + 5, this.height / 6 + 120 - 6);
			case 6:
				return new Position(this.width / 2 - 155, this.height / 6 + 144 - 6);
			case 7:
				return new Position(this.width / 2 + 5, this.height / 6 + 144 - 6);
		}
		return null;
	}
	
	public void actionPerformed(GuiButton b){
		if(b.id == 200){
			this.openPrevGui(mc);
		}
	}
	
	public void openPrevGui(Minecraft mc){
		mc.displayGuiScreen(prevScreen);
	}
}
