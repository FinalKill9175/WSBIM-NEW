package com.FinalKill.wsbim.client.gui.tabs;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;

public abstract class GuiTab extends GuiScreen{
	
	protected int x;
	protected int y;
	
	protected final int tab_width = 28;
	protected final int tab_height = 26;
	
	public boolean selected = false;
	
	public GuiContainer gui;
	
	protected int guiContainerLeft;
	protected int guiContainerTop;
	
	protected int click = 0;
	protected long ms = 0;
	
	public int id;
	protected String tabListVarName;
	protected Class<?> tabListClass;
	protected Object instance;
	
	public GuiTab(Minecraft mc, int id, GuiContainer guiContainer, String tabListVarName, Class<?> tabListClass, Object classObject){
		this.mc = Minecraft.getMinecraft();
		this.id = id;
		gui = guiContainer;
		
		if(gui !=null){
			this.guiContainerLeft = guiContainer.guiLeft;
			this.guiContainerTop = guiContainer.guiTop;
		}
		this.selected = false;
		this.tabListVarName = tabListVarName;
		this.tabListClass = tabListClass;
		this.instance = classObject;
		

		this.initTab();
	
	}
	int t = 0;
	/**Called every tick*/
	public void handleTabMouseInput(GuiContainer c) {
		this.updateTab();
		this.setOpenContainer(c);
		int i = Mouse.getEventX() * c.width / c.mc.displayWidth;
	    int j = c.height - Mouse.getEventY() * c.height / c.mc.displayHeight - 1;
	    int k = Mouse.getEventButton();
	  
	    
	   if((i >= x && j <= (x+tab_width)) && (i >= y && j <= (y+tab_height)) && Mouse.getEventButtonState() && click == 0){	
			
		   List<GuiTab> tabList = (List<GuiTab>) HUDOverlayRendererEvent.getObjectFromClass(tabListVarName, tabListClass, this.instance);
		   if(tabList.contains(this) && this.canClickOnTab()){
			   this.onTabClicked(i, j);
				//this.selected = false;
				click = 1;
				t++;
				if(WSBIM.options.playOpenGUISound && click == 1 && !(this instanceof GuiContainerTab)){
					//Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("random.wood_click"), 1.0F));
				}
				return;
			
		   }
				
		}
	    
	    if(!Mouse.getEventButtonState()){
	    	click = 0;
	    	
	    }
	}

	public abstract void renderTab(float partialTick, int mouseX, int mouseY);
	public abstract void initTab();
	public abstract void onTabClicked(int mouseX, int mouseY);
	public abstract boolean canClickOnTab();
	public abstract void onGuiClosed();
	
	public void updateTab(){
		
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	
	public void mouseClicked(int mouseX, int mouseY, int eventButton){
		super.mouseClicked(mouseX, mouseY, eventButton);
			
	}
	
	public boolean isInTab(int mouseX, int mouseY){
		return (mouseX >=x && mouseX <= x + this.tab_width) && (mouseY >=y && mouseY <= y + this.tab_height);
	}
	
	
	
	
	
	public static EntityPlayer getServerPlayerObject(Minecraft mc){
		if(Minecraft.getMinecraft().isIntegratedServerRunning()){
			return mc.getIntegratedServer().getEntityWorld().getPlayerEntityByName(mc.thePlayer.getDisplayName());
		}
		else if(MinecraftServer.getServer() !=null && MinecraftServer.getServer().isServerRunning()){
			return MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(mc.thePlayer.getDisplayName());
		}
		else{
			return mc.theWorld.getPlayerEntityByName(mc.thePlayer.getDisplayName());
		}
	}
	
	public static World getServerWorldObj(Minecraft mc){

		World world = null;
		
		if(MinecraftServer.getServer() !=null && MinecraftServer.getServer().worldServers !=null){
		WorldServer[] list = MinecraftServer.getServer().worldServers;
		for(WorldServer ins : list){
		if(ins.provider.dimensionId==mc.thePlayer.dimension)
			world = ins;
		}
		if(world==null && list.length > 0)
			world = list[0];
		}
		else if(mc.isIntegratedServerRunning()){
			WorldServer[] list = mc.getIntegratedServer().worldServers;
			for(WorldServer ins : list){
				if(ins.provider.dimensionId==mc.thePlayer.dimension)
					world = ins;
				}
				if(world==null && list.length > 0)
					world = list[0];
		}
		else{
			world = mc.theWorld;
		}
		
		
		
		return world;
	}
	
	
	

	public void setOpenContainer(GuiContainer container){
		this.gui = container;
		this.guiContainerLeft = container.guiLeft;
		this.guiContainerTop = container.guiTop;
		
           // this.handleTabMouseInput(container);
        
	}
	
	public void drawTab(float partialTick, GuiContainer openGUI){
				Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer renderPlayer = mc.thePlayer;
		//TODO Make option for tabs only
		ResourceLocation tab_location = WSBIM.options.useAdvancedResourcePackSupport ? new ResourceLocation("textures/gui/container/creative_inventory/tabs.png") : new ResourceLocation(WSBIM.modid, "textures/gui/tabs_default.png");
		int left = this.guiContainerLeft + (id * this.tab_width);
		int top = this.guiContainerTop - this.tab_height;
		
		x = left;
		y = top - (this.isSelected() ? 0 : 0);
		
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1F, 1.0F);
		mc.renderEngine.bindTexture(tab_location);
		
		
		
		if(!this.isSelected()){
			this.drawTexturedModalRect(left, top, 0, 2, this.tab_width, this.tab_height);
		}
		else{
			this.drawTexturedModalRect(left, top-2, 0+(id > 0? 28 : 0), 32, tab_width, tab_height + 6);
		}
		
		 this.renderTab(partialTick, 0,0);
	    GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
