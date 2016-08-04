package com.FinalKill.wsbim.client.gui.tabs;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;
import com.FinalKill.wsbim.client.gui.GuiContainerOverlayTabs;
import com.FinalKill.wsbim.util.ContainerUtil;

public class GuiTabSmallCraft extends GuiTab{
	public GuiTabSmallCraft(Minecraft mc, int id, GuiContainer guiContainer,
			String tabListVarName, Class<?> tabListClass, Object classObject) {
		super(mc, id, guiContainer, tabListVarName, tabListClass, classObject);

		
	}

	public void renderTab(float partialTick, int mouseX, int mouseY) {
		this.selected = GuiContainerOverlayTabs.smallCraftTab !=null? GuiContainerOverlayTabs.smallCraftTab.selected : false;
		//MUST DO TODO
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		RenderHelper.enableGUIStandardItemLighting();
		if(this.isSelected() || !this.isSelected())GuiContainerOverlayTabs.renderItemStack(new ItemStack(Blocks.crafting_table), x + 6, y + 6, partialTick);
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
			ContainerUtil.openGUI(211);
			this.selected = true;

		}
		Minecraft mc = Minecraft.getMinecraft();
		
	}
	}
	
	public void updateTab(){
			
	}

	@Override
	public boolean canClickOnTab() {
		return GuiContainerOverlayTabs.smallCraftTabAvailable;
	}

	@Override
	public void onGuiClosed() {
		GuiContainerOverlayTabs.smallCraftTab = null;
	}
	
	
}
