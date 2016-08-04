package com.FinalKill.wsbim.client.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.client.event.HUDOverlayRendererEvent;
import com.FinalKill.wsbim.client.gui.tabs.GuiContainerTab;
import com.FinalKill.wsbim.client.gui.tabs.GuiTab;
import com.FinalKill.wsbim.client.gui.tabs.GuiTabArmor;
import com.FinalKill.wsbim.client.gui.tabs.GuiTabBackpack;
import com.FinalKill.wsbim.client.gui.tabs.GuiTabSmallCraft;
import com.FinalKill.wsbim.client.util.IGuiContainerOverlay;
import com.FinalKill.wsbim.common.inventory.ContainerBackpack;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.util.ContainerUtil;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerOverlayTabs extends Gui implements IGuiContainerOverlay{
	
	private int guiContainerXSize;
	private int guiContainerYSize;
	
	private int guiContainerLeft;
	private int guiContainerTop;
	
	final int tab_width = 28;
	final int tab_height = 26;
	
	boolean hasAdjustedGui;
	static RenderItem itemRenderer = new RenderItem();
	
	public List<GuiTab> tabList = new ArrayList<GuiTab>();
	
	public static GuiContainerTab containerTab;
	public static GuiTab armorTab;
	public static GuiTab smallCraftTab;
	public static GuiTabBackpack backpackTab;
	
	public static boolean containerTabAvailable = true;
	public static boolean armorTabAvailable = true;
	public static boolean smallCraftTabAvailable = true;
	public static boolean backpackTabAvailable = false;
	
	private long ms = 0;
	
	public void render(GuiContainer container_gui, float partialTick) {
		//System.out.println("guiLeft: "+this.guiContainerLeft + " guiTop: "+this.guiContainerTop+" xSize: "+this.guiContainerXSize+" ySize: "+this.guiContainerYSize+" ");
		ItemStack backpack = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] !=null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2].getItem() instanceof ItemBackpack? Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] : Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
		
		if((container_gui instanceof GuiBackpack) && this.getPressedTab(tabList) == 0){
			this.backpackTab = null;
			this.backpackTabAvailable = false;
			if(tabList.contains(backpackTab)){
				tabList.remove(backpackTab);
			}
		}
		
		if(this.hasAdjustedGui && this.containerTab !=null && this.containerTab.containerToOpen !=null && (this.containerTab.containerToOpen instanceof ContainerBackpack || container_gui.inventorySlots instanceof ContainerBackpack) && ( backpack == null || (backpack !=null && !(backpack.getItem() instanceof ItemBackpack)))){
			Minecraft.getMinecraft().displayGuiScreen(null);
			ContainerUtil.getServerPlayerObject(Minecraft.getMinecraft()).closeScreen();
			ContainerUtil.getServerPlayerObject(Minecraft.getMinecraft()).addChatMessage(new ChatComponentText("You backpack  was not deleted, if the backpack did not eject please go into your inventory and go into the armor tab and DO NOT press 'B' until the backpack shows up."));
		}
		
		//System.out.println(Minecraft.getMinecraft().debug.split("fps")[0]);
		if(!this.hasAdjustedGui){
			this.ms = 0;
			

			this.containerTab = new GuiContainerTab(Minecraft.getMinecraft(), container_gui, "tabList", this.getClass(), this);
			armorTab = new GuiTabArmor(Minecraft.getMinecraft(), 1, container_gui, "tabList", this.getClass(), this);
			this.smallCraftTab = new GuiTabSmallCraft(Minecraft.getMinecraft(), 2, container_gui, "tabList", this.getClass(), this);
					this.tabList.add(this.containerTab);
			this.tabList.add(armorTab);
			
			this.tabList.add(smallCraftTab);
			if(backpack !=null && backpack.getItem() instanceof ItemBackpack && !(container_gui.inventorySlots instanceof ContainerBackpack) && !(Minecraft.getMinecraft().thePlayer.openContainer instanceof ContainerBackpack) && !(container_gui instanceof GuiBackpack)){
				this.backpackTab = new GuiTabBackpack(Minecraft.getMinecraft(), 3, container_gui, "tabList", this.getClass(), this);

				this.tabList.add(backpackTab);
			}
			else{
			  this.backpackTab = null;
			}
			
			
			this.hasAdjustedGui = true;
		}
		if(backpack == null){
			this.backpackTabAvailable = false;
			this.backpackTab = null;
			tabList.remove(backpackTab);
			//this.backpackTab.isBackpackTabShowing = false;
		}
		
		else if(this.backpackTab == null && backpack !=null && backpack.getItem() instanceof ItemBackpack && !((container_gui.inventorySlots instanceof ContainerBackpack) && this.getPressedTab(tabList) == 0)){
			
			this.backpackTab = new GuiTabBackpack(Minecraft.getMinecraft(), 3, container_gui, "tabList", this.getClass(), this);
			this.tabList.add(backpackTab);
		}
		
		if(( !(container_gui.inventorySlots instanceof ContainerBackpack) && containerTab.containerToOpen instanceof ContainerBackpack) && backpackTab !=null){
			this.backpackTab = null;
			this.backpackTabAvailable = false;
			tabList.remove(backpackTab);
			//this.backpackTab.isBackpackTabShowing = false;
		}
		//System.out.println((GuiContainerTab.containerToOpen.inventorySlots instanceof ContainerBackpack));
		
		if(backpackTab !=null){
			this.backpackTabAvailable = true;
			this.backpackTab.drawTab(partialTick, container_gui);
		}
		
		for(int i = 0; i < this.tabList.size(); i++){
			GuiTab tab = this.tabList.get(i);
			if(tab !=null && this.hasAdjustedGuiVars() && !(tab instanceof GuiTabBackpack) && tab.canClickOnTab()){
				tab.drawTab(partialTick, container_gui);
			}
		}
	}
	
	public static int getPressedTab(List<GuiTab> tabList){
		for(int i = 0; i < tabList.size(); i++){
			GuiTab tab = tabList.get(i);
			if(tab !=null){
				if(tab.isSelected()){
					return i;
				}
			}
		}
		
		return -1;
	}
	
	@SubscribeEvent
	public void updateInput(ClientTickEvent e){
		for(int i = 0; i < this.tabList.size(); i++){
			GuiTab tab = tabList.get(i);
			if(tab !=null && Minecraft.getMinecraft().currentScreen !=null && Minecraft.getMinecraft().currentScreen instanceof GuiContainer && this.canRender(Minecraft.getMinecraft(), null, (GuiContainer)Minecraft.getMinecraft().currentScreen)){
				tab.handleTabMouseInput((GuiContainer)Minecraft.getMinecraft().currentScreen);
			}
		}
	}
	
	
	 /**
     * Renders the specified item of the inventory slot at the specified location. Args: slot, x, y, partialTick
     */
    public static void renderItemStack(ItemStack p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_)
    {
     
        if (p_73832_1_ != null)
        {
            float f1 = (float)p_73832_1_.animationsToGo - p_73832_4_;

             
            if (f1 > 0.0F)
            {
                GL11.glPushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
                GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
            }

            itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), p_73832_1_, p_73832_2_, p_73832_3_);

            if (f1 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), p_73832_1_, p_73832_2_, p_73832_3_);
        }
    }

	public boolean canRender(Minecraft mc, RenderTickEvent e, GuiContainer open_container) {
		if(this.getServerWorldObj(mc) == null)return false;
		if(open_container instanceof GuiContainerCreative) return false;
		if((open_container instanceof GuiInventory) && !WSBIM.options.showInventoryTabs) return false;
		if(this.guiContainerYSize < WSBIM.options.minHeightForTabs) return false;
		if(!(open_container.inventorySlots.inventoryItemStacks.size() <= WSBIM.options.maxSlotsForTabs) && !(open_container.inventorySlots instanceof ContainerBackpack) && !(open_container.inventorySlots instanceof ContainerChest)) return false;
		if(this.guiContainerXSize >= 256 || this.guiContainerYSize >= (256))return false;
		if(this.guiContainerTop < this.tab_height) return false;
		if(!WSBIM.options.showContainerTabs) return false;
		
		return true;
	}
	
	public void canKeepBackpack(){
		
	}
	public void updateSizes(GuiContainer open_container){
		this.guiContainerLeft = open_container.guiLeft;
		this.guiContainerTop = open_container.guiTop;
		this.guiContainerXSize = open_container.xSize;                             
		this.guiContainerYSize = open_container.ySize;
	}
	public World getServerWorldObj(Minecraft mc){
		
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
	
	public boolean hasNoInventorySlots(Minecraft mc,
			GuiContainer open_container) {
		return true;
	}
	
	
	public static void setInteger(int new_integer, String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			f.setInt(instance, new_integer);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void setObject(Object new_obj, String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			f.set(instance, new_obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void setAdjustedGuiVars(boolean bool) {
		this.hasAdjustedGui = bool;
	}

	public boolean hasAdjustedGuiVars() {
		return this.hasAdjustedGui;
	}

	public boolean updateGuiSizes() {
		if(ms == 0){
			ms = System.currentTimeMillis();
			return true;
		}
		else if (ms + 800 > System.currentTimeMillis()){
			return true;
		}
		
			
		else return false;
	}

}
