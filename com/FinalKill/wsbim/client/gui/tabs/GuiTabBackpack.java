package com.FinalKill.wsbim.client.gui.tabs;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;
import com.FinalKill.wsbim.client.gui.GuiContainerOverlayTabs;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.util.ContainerUtil;

public class GuiTabBackpack extends GuiTab{
	
	@Deprecated
	public boolean isBackpackTabShowing = false;
	
	public GuiTabBackpack(Minecraft mc, int id, GuiContainer guiContainer,
			String tabListVarName, Class<?> tabListClass, Object classObject) {
		super(mc, id, guiContainer, tabListVarName, tabListClass, classObject);

		
	}

	public void renderTab(float partialTick, int mouseX, int mouseY) {
		this.selected = GuiContainerOverlayTabs.backpackTab !=null ? GuiContainerOverlayTabs.backpackTab.selected : false;
		//MUST DO TODO
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		RenderHelper.enableGUIStandardItemLighting();
		ItemStack backpack = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] !=null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2].getItem() instanceof ItemBackpack? Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] : Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
		if(this.isSelected() || !this.isSelected())GuiContainerOverlayTabs.renderItemStack(backpack, x + 6, y + 6, partialTick);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	public void initTab() {
		//
		//this.getServerPlayerObject(Minecraft.getMinecraft()).openContainer.onContainerClosed(this.getServerPlayerObject(Minecraft.getMinecraft()));

	}

	public void onTabClicked(int mouseX, int mouseY) {
		
	if(!this.isSelected() && this.canClickOnTab()){
		List<GuiTab> tabList = (List<GuiTab>) HUDOverlayRendererEvent.getObjectFromClass(tabListVarName, tabListClass, this.instance);
		int selectedTab = GuiContainerOverlayTabs.getPressedTab(tabList);
		
		if((mouseX >=x && mouseX <= x + 28) && (mouseY >=y && mouseY <= y + 26)){
			int sel = selectedTab;
			
			for(int i = 0; i < tabList.size(); i++){
				GuiTab tab = tabList.get(i);
				if(tab !=null){
					if(tab.id != this.id){
						tab.selected = false;
					}
				}
			}
			ContainerUtil.openGUI(212);
			this.selected = true;

		}
		Minecraft mc = Minecraft.getMinecraft();
		
	}
	}
	
	public void updateTab(){
			
	}

	@Override
	public boolean canClickOnTab() {
		return GuiContainerOverlayTabs.backpackTabAvailable;
	}

	@Override
	public void onGuiClosed() {
		GuiContainerOverlayTabs.backpackTab = null;
	}
	
}
