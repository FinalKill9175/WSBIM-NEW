package com.FinalKill.wsbim.client.gui.tabs;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;
import com.FinalKill.wsbim.client.gui.GuiContainerOverlayTabs;
import com.FinalKill.wsbim.util.ContainerUtil;

public class GuiContainerTab extends GuiTab{
	
	public static GuiContainer guiToOpen;
	public static Container containerToOpen;


	public GuiContainerTab(Minecraft mc, GuiContainer c, String tabListVarName, Class<?> tabListClass, Object classObject) {
		super(mc, 0, c, tabListVarName, tabListClass, classObject);
		
	
	}

	public void renderTab(float partialTick, int mouseX, int mouseY) {
		this.selected = GuiContainerOverlayTabs.containerTab !=null? GuiContainerOverlayTabs.containerTab.selected : false;
		this.drawString(Minecraft.getMinecraft().fontRenderer, "GUI", x + 5, y + 8, 16777215);
	}

	public void initTab() {
		
		this.selected = true;
		//TODO Lagg
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.currentScreen !=null && mc.currentScreen instanceof GuiContainer){
				this.guiToOpen = this.gui;
				this.containerToOpen = ContainerUtil.getServerPlayerObject(mc)!=null?ContainerUtil.getServerPlayerObject(mc).openContainer : ((GuiContainer)mc.currentScreen).inventorySlots;
			}
		
	}

	public void updateTab(){

		
	}
	
	public void onTabClicked(int mouseX, int mouseY) {
		List<GuiTab> tabList = (List<GuiTab>) HUDOverlayRendererEvent.getObjectFromClass(tabListVarName, tabListClass, this.instance);
		int selectedTab = GuiContainerOverlayTabs.getPressedTab(tabList);
		Minecraft mc = Minecraft.getMinecraft();
		if(!this.isSelected() && this.canClickOnTab()){
			
	
			
			for(int i = 0; i < tabList.size(); i++){
				GuiTab tab = tabList.get(i);
				if(tab !=null){
					if(tab.id != this.id){
						tab.selected = false;
					}
				}
			}
			if((mouseX >=x && mouseX <= x + this.tab_width) && (mouseY >=y && mouseY <= y + this.tab_height)){
				if(selectedTab !=0){
					//if(GuiContainerTab.containerToOpen != this.getServerPlayerObject(Minecraft.getMinecraft()).openContainer){
						//this.getServerPlayerObject(Minecraft.getMinecraft()).openContainer.onContainerClosed(this.getServerPlayerObject(Minecraft.getMinecraft()));
					//}
					ContainerUtil.getServerPlayerObject(mc).closeScreen();
					mc.currentScreen = null;
					
				}
				
				this.selected = true;
				ContainerUtil.openGUI(209);
				
			}
		}
	}


	@Override
	public boolean canClickOnTab() {
		return GuiContainerOverlayTabs.containerTabAvailable;
	}

	@Override
	public void onGuiClosed() {
		GuiContainerOverlayTabs.containerTab = null;
	}
	
}
