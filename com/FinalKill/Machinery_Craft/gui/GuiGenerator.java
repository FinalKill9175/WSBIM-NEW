package com.FinalKill.Machinery_Craft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.inventory.ContainerGenerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityGenerator;

public class GuiGenerator extends GuiContainer{
	
	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/gui/generator.png");

	TileEntityGenerator generator;
	
	public GuiGenerator(InventoryPlayer player, TileEntityGenerator generator) {
		super(new ContainerGenerator(player, generator));

		this.generator = generator;
		xSize = 176;
		ySize = 166;
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
int var4 = (int) generator.getPowerScaled(70);
		
		this.drawTexturedModalRect(guiLeft +7, guiTop+77-var4, xSize, 70-var4, 24, var4);
		
		if(generator.isGenerating() &&(generator.power<=this.generator.max_power-1) &&!(generator.power==this.generator.max_power-1)){
		int var5 = generator.getBurnTimeScaled(12);
		 this.drawTexturedModalRect(guiLeft + 62, guiTop + 44 + 12 - var5, 0, ySize+12 - var5, 14, var5 + 2);
		}
		
	}
	
	protected void drawGuiContainerForegroundLayer(int var2,
			int var3) {
		int gray = MachineryCraft.gray;
		this.fontRendererObj.drawString("Generator", 65, 7, gray);
		this.fontRendererObj.drawString("Stored Power: "+(int)generator.power, 35, 30, gray);
		this.fontRendererObj.drawString("Power/Tick: "+"1", 85, 40, gray);
		this.fontRendererObj.drawString("Burn Time: "+generator.burn_time, 85, 50, gray);
		this.fontRendererObj.drawString("Power/Item: "+generator.toltal_burn_time/4, 85, 60, gray);
		
		String status = EnumChatFormatting.DARK_RED+"Check";
		if(this.generator.burn_time>0 && (generator.power<=this.generator.max_power-1) &&!(generator.power==this.generator.max_power-1)){
			status = EnumChatFormatting.DARK_GREEN+"Going";
			
		}
		else if(generator.power>4900){
			
			status = EnumChatFormatting.DARK_PURPLE+"Full";
		}
		else if(this.generator.power>=0 && this.generator.burn_time == 0){
			status = EnumChatFormatting.GOLD+"Idle";
		}
		else{
			status  = EnumChatFormatting.DARK_RED+"Check";
		}
	
		this.fontRendererObj.drawString("Status: "+status, 85, 70, gray);
		

	}

}
