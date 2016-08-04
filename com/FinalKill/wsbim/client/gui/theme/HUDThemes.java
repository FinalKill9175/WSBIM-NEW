package com.FinalKill.wsbim.client.gui.theme;

import java.util.ArrayList;
import java.util.List;

public class HUDThemes {

	private final List<String> themeList = new ArrayList<String>();
	
	public void initialize(){
		this.registerThemes();
	}
	
	public void registerThemes(){
		this.addTheme("Vanilla");
		this.addTheme("PVP");
		//this.addTheme("Bars");
		
	}
	
	public void addTheme(String theme){
		this.themeList.add(theme);
	}
	
	public String[] getAvalableThemes(){
		Object[] obj = themeList.toArray();
		String[] arr = new String[obj.length];
		for(int i = 0; i < obj.length; i++){
			Object o = obj[i];
			if(o instanceof String){
				arr[i] = (String)o;
			}
		}
		return arr;
	}
	
}
