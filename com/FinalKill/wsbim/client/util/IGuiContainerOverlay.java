package com.FinalKill.wsbim.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IGuiContainerOverlay {
	
	
	public void setAdjustedGuiVars(boolean i);
	public boolean hasAdjustedGuiVars();
	
	public boolean updateGuiSizes();
	
	/**
	 * Renders the overlay in the gui container.
	 * @param container_gui The GuiContainer instance.
	 * @param partialTick
	 */
	public void render(GuiContainer container_gui, float partialTick);
	/**
	 * Tells the event when this overlay can render.
	 * @param mc The Minecraft instance. 
	 * @param e The RenderTickEvent instance. IS NULL!!!!
	 * @param open_container The currently opened container that the overlay renderer will render on. Great for checking the instance of something.
	 * @return Can this overlay render right now?
	 */
	public boolean canRender(Minecraft mc, RenderTickEvent e, GuiContainer open_container);
	
	/**Will this container renderer remove inventory slots at a given time?*/
	public boolean hasNoInventorySlots(Minecraft mc, GuiContainer open_container);
}
