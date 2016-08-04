package com.FinalKill.wsbim.client.util;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerOverlayRegistry {
	public static final List<IGuiContainerOverlay> overlays = new ArrayList<IGuiContainerOverlay>();
	
	/**Adds an overlay to be rendered in any instance of a GuiContainer.*/
	public static void addOverlay(IGuiContainerOverlay overlay){
		overlays.add(overlay);
	}
	/**Gets the number of toltal reigstered overlays.*/
	public static int getToltalOverlays(){
		return overlays.size();
	}
	/**Gets the overlay from a list.*/
	public static IGuiContainerOverlay getOverlay(int i){
		return overlays.get(i);
	}
}
