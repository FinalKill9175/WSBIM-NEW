package com.FinalKill.wsbim.option;

import java.lang.reflect.Field;

import net.minecraft.client.gui.GuiScreen;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.WSBIMOptions;
import com.FinalKill.wsbim.WSBIMOptions.OptionType;

public class OptionInteger extends Option{
	private int min;
	private int max;
	private int step;
	
	public OptionInteger(boolean gui, String variableName, String guiName,
			int min, int max, int stepping) {
		super(gui, variableName, guiName, OptionType.INT);
		this.min = min;
		this.max = max;
		this.step = stepping;
	}

	public Object getObjectForCycle(int cycle) {
		return null;
	}

	public int getCycleLength() {
		return 0;
	}

	public int getCycleStart() {
		return 0;
	}

	public String getStringForCycle(int cycle) {
		return null;
	}

	public GuiScreen getGuiToOpen() {
		return null;
	}
	
	public int getMax(){
		return max;
	}
	
	public int getMin(){
		return min;
	}
	
	public int getValueStep(){
		return step;
	}
	
	public int getStartingValue(){
		try {
			Field f = WSBIMOptions.class.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
				return f.getInt(WSBIM.options);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
