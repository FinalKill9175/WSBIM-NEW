package com.FinalKill.Machinery_Craft;

public class TextureName {
	public static String texture_name;
	public TextureName(String name, String modid){
		
		texture_name = modid+":"+name;
		
	}
	
	public static String getTextureName(){
		return texture_name;
		
	}

}
