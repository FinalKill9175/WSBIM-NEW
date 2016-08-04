package com.FinalKill.wsbim.client.gui.settings;

import java.util.ArrayList;
import java.util.List;

public class Description {

	public static List<String> getItemDescription(String valName){
		List<String> desc = new ArrayList<String>();
		
		if(valName.equals("minHeightForTabs")){
			return getStringListFromArray(
					"Do not change unless you know what",
					"you are doing, this controlls",
					"the minium height a gui must be",
					"for tabs to appear.");
		}
		
		if(valName.equals("maxSlotsForTabs")){
			return getStringListFromArray(
					"Controlls the limit for",
					"how many slots (including",
					"the player's inventory)",
					"that can be in this",
					"container for the tabs",
					"to appear. Set to 80 or ",
					"below to exclude any",
					"chests or large containers."
					);
		}
		if(valName.equals("showContainerTabs")){
			return getStringListFromArray(
					"Display the tabs that would",
					"appear in the top left corner",
					"of any GuiContainer (gui",
					"with slots)."
					);
		}
		if(valName.equals("currentTheme")){
			return getStringListFromArray(
					"Changes the current",
					"theme for the ingame GUI."
					
					);
		}
		
		if(valName.equals("showCrosshairs")){
			return getStringListFromArray(
					"Option for rendering the",
					"crosshairs in the HUD."
					);
		}
		
		if(valName.equals("showScoreboard")){
			return getStringListFromArray(
					"Option for rendering the",
					"scoreboard in the HUD."
					);
		}
		if(valName.equals("showArmorInHUD")){
			return getStringListFromArray(
					"Show the player's armor in",
					"the ingame GUI."
					);
		}
		if(valName.equals("themeBrightness")){
			return getStringListFromArray(
					"Adjusts the transparency behind",
					"elements in the HUD."
					);
		}
		if(valName.equals("showInventoryTabs")){
			return getStringListFromArray(
					"Display the tabs in the inventory",
					"GUI, this option is here if other",
					"mods have tabs in the inventory,",
					"it can be disabled."
					);
		}
		
		if(valName.equals("useAdvancedResourcePackSupport")){
			return getStringListFromArray(
					"(Advanced Resource Pack Support)",
					"NOTE: Will only have effect when",
					"a dedicated resource pack is loaded.",
					"Will render out some guis more geared",
					"out for resource locations. Compare this", 
					"feature in the guis within tabs found in", 
					"containers. Some resource packs may",
					"cause issues with this feature. Disable",
					"if any mod-guis look strange or out",
					"of place."
					);
		}
		
		if(valName.equals("playOpenGUISound")){
			return getStringListFromArray(
					"Opion for playing the",
					"'wood click' sound effect",
					"whenever any gui container",
					"is loaded."
					);
		}
		
		return desc;
	}
		
	private static List<String> getStringListFromArray(String... desc){
		List<String> strs = new ArrayList<String>();
		for(int i = 0; i < desc.length; i++){
			strs.add(desc[i]);
		}
		
		return strs;
	}
}
