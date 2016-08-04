package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.common.inventory.ContainerUpgradedChest;
import com.FinalKill.wsbim.util.EnumChestType;
import com.FinalKill.wsbim.util.IUpgradedChest;

public class GuiUpgradedChest extends GuiContainer {
	
	private IUpgradedChest chest;

	public GuiUpgradedChest(InventoryPlayer player, IUpgradedChest chest) {
		super(new ContainerUpgradedChest(player, chest));
		this.chest = chest;
		this.xSize = this.chest.getChestType().getxSize();
		this.ySize = this.chest.getChestType().getySize();
		
	}
		protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.chest.getChestType().getTexture().getGuiTexture());
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);			
		}
	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(chest.getBlockInWorld().getLocalizedName(), this.chest.getChestType().getInv_start_x(), 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), this.chest.getChestType().getPlayer_inv_start_x(), this.ySize - 96 + 2 +(this.chest.getChestType() == EnumChestType.MIXEDMETAL? 2 : 0), 4210752);
    }

}
