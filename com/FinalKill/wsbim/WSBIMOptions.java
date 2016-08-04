package com.FinalKill.wsbim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import com.FinalKill.wsbim.client.gui.settings.GuiWSBIMSetingsTabs;
import com.FinalKill.wsbim.client.gui.settings.GuiWSBIMSettingsHUD;
import com.FinalKill.wsbim.option.Option;
import com.FinalKill.wsbim.option.OptionBoolean;
import com.FinalKill.wsbim.option.OptionCycle;
import com.FinalKill.wsbim.option.OptionGUI;
import com.FinalKill.wsbim.option.OptionInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WSBIMOptions {

	/**Option File*/
	public final File optionsFile = new File(FMLCommonHandler.instance().getSide() == Side.SERVER?MinecraftServer.getServer().getFile("")+"\\wsbim\\options.txt" : Minecraft.getMinecraft().mcDataDir.getPath()+"\\wsbim\\options.txt");
	
	/**WSBIM Folder*/
	public final File modFolder = new File(FMLCommonHandler.instance().getSide() == Side.SERVER?MinecraftServer.getServer().getFile("")+"\\wsbim" : Minecraft.getMinecraft().mcDataDir.getPath()+"\\wsbim");
	
	/**Options List*/
	public final List<Option> optionsList = new ArrayList<Option>();
	
	//TODO Make all of the option variables here
	/**
	public boolean testBoolean;
	public String testCycle;
	public int testInteger;
	public float testFloat;
	*/
	
	public boolean checkForUpdates;
	public boolean showScoreboard;
	public boolean showCrosshairs;
	public boolean showArmorInHUD;
	public boolean showContainerTabs;
	public boolean showInventoryTabs;
	public boolean useAdvancedResourcePackSupport;
	public boolean playOpenGUISound;
	
	public String currentTheme;
	public String themeBrightness;
	
	public int minHeightForTabs;
	public int maxSlotsForTabs;
	//public int maxHeightForTabs;
	
	
	//Array Lists
	/**public final String[] varTypes = new String[]{"Boolean","Integer","Float","Double","String"};*/
	
	public static final String[] brightnesses = new String[]{"Dark","Light"};
	//Value limits
	/**
	public final int testIntegerMin = 0;
	public final int testIntegerMax = 100;
	
	public final float testFloatMin = 0.0F;
	public final float testFloatMax = 15.0F;
	*/
	public WSBIMOptions(){
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			for(int i = 0; i < 100; i++){
				this.optionsList.add(i, null);
			}
		}
	}
	
	public void createOptions() throws IOException{

	
		if(!modFolder.exists()){
			modFolder.mkdir();
		}
		if(!optionsFile.exists()){
			this.setDefaults();
			try {
				this.saveOptions(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(this.optionsFile));
				 String s = "";
			       
		         while ((s = br.readLine()) != null)
		         {
		             try{
		            	 CharSequence charseq = ":";
		            	 if(s.contains(charseq)){
		            		String[] line = s.split(":");
		            		if(line[0].equals("Mod Version")){
		            			if(!line[1].equals(WSBIM.version)){
		            				this.setDefaults();
		            				this.saveOptions(true);
		            			}
		            		}
		            	 }
		             }
		             catch(Exception e){
		            	 e.printStackTrace();
		             }
		             
		         }
				br.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				this.loadOptions();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			this.registerOptions();
		}
		
	}
	
	private void setDefaults(){
		//TODO Set all of the default values here.
		/**
		testBoolean = false;
		testCycle = varTypes[0];
		testInteger = 40;
		testFloat = 15 / 2;
		*/
		this.checkForUpdates = true;
		this.showScoreboard = true;
		this.showCrosshairs = true;
		this.currentTheme = "Vanilla";
		this.showArmorInHUD = false;
		this.themeBrightness = this.brightnesses[1];
		this.showContainerTabs = true;
		this.minHeightForTabs = 120;
		this.maxSlotsForTabs = 80;
		showInventoryTabs = true;
		useAdvancedResourcePackSupport = true;
		playOpenGUISound = false;
		//this.maxHeightForTabs = 256 - 26;
	}
	
	public void saveOptions(boolean loadOptionsWhenDone)throws IOException{
		if(this.optionsFile.exists()){
			this.optionsFile.delete();
		}
		if(!this.optionsFile.exists()){
			this.optionsFile.createNewFile();
		}
		PrintWriter pw = new PrintWriter(new FileWriter(this.optionsFile));
		pw.println("*****WSBIM Options File*****");
		pw.println("Below are all of the options for WSBIM,");
		pw.println("each option is in the following format:");
		pw.println("OptionName:variable");
		pw.println("If there are boolean variables, change from");
		pw.println("true to false, or false to true if you want");
		pw.println("to change the variable.");
		pw.println("Written: "+new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss").format(Calendar.getInstance().getTime()));
		pw.println("Mod Version:"+WSBIM.version);
		pw.println("********Options Start********");
		//TODO Put all options here to be written to the file.
		pw.println("Check for Mod Updates:"+this.checkForUpdates);
		pw.println();
		pw.println("------HUD Customization Settings------");
		pw.println("Render Scoreboard:"+this.showScoreboard);
		pw.println("Render Crosshairs:"+this.showCrosshairs);
		pw.println("Current Theme:"+this.currentTheme);
		pw.println("Show Armor in HUD:"+this.showArmorInHUD);
		pw.println();
		pw.println("Theme Backround Opacity:"+this.themeBrightness);
		pw.println("Options: Dark or Light");
		pw.println();
		pw.println("Play Soud when a GUI is Opened:"+this.playOpenGUISound);
		pw.println("----Gui Container Tab Customization----");
		pw.println("Show Tabs in Containers:"+this.showContainerTabs);
		pw.println("Minimum Height for Tabs:"+this.minHeightForTabs);
		pw.println("Max Slots for Tabs:"+this.maxSlotsForTabs);
		pw.println("Show Tabs in Inventory:"+this.showInventoryTabs);
		pw.println("Advanced Resource Pack Support:"+this.useAdvancedResourcePackSupport);
		//pw.println("Max Height for Tabs: "+this.maxHeightForTabs);
		
		/**
		pw.println("TestBoolean:"+this.testBoolean);
		pw.println();
		pw.println("Test Cycle:"+this.testCycle);
		pw.println("Avalable Types for Test Cycle Option: "+this.convertArrayListToStrings(varTypes));
		pw.println();
		pw.println("Test Integer:"+this.testInteger);
		pw.println();
		pw.println("Test Float:"+this.testFloat);
		*/
		pw.println("********Options End********");
		pw.close();
		if(loadOptionsWhenDone){
			this.loadOptions();
		}
	}
	
	public void loadOptions() throws IOException{
		 if (!this.optionsFile.exists())
         {
             return;
         }

         BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
         String s = "";
       
         while ((s = bufferedreader.readLine()) != null)
         {
             try{
            	 CharSequence charseq = ":";
            	 if(s.contains(charseq)){
            		String[] line = s.split(":");
            		//TODO Put all loads in here.
            		//Example:
            		/**
            		 * if(line[0].equals("TestBoolean")){this.testBoolean = Boolean.getBoolean(line[1]);}
            		 * 
            		 * 
            		 */
            		if(line[0].equals("Check for Mod Updates")){
            			this.checkForUpdates = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Render Scoreboard")){
            			this.showScoreboard = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Render Crosshairs")){
            			this.showCrosshairs = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Current Theme")){
            			this.currentTheme = line[1];
            		}
            		
            		if(line[0].equals("Show Armor in HUD")){
            			this.showArmorInHUD = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Theme Backround Opacity")){
            			this.themeBrightness = line[1];
            		}
            		
            		if(line[0].equals("Show Tabs in Containers")){
            			this.showContainerTabs = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Minimum Height for Tabs")){
            			this.minHeightForTabs = Integer.parseInt(line[1]);
            		}
            		if(line[0].equals("Max Height for Tabs")){
            		//	this.maxHeightForTabs = Integer.parseInt(line[1]);
            		}
            		
            		if(line[0].equals("Max Slots for Tabs")){
            			this.maxSlotsForTabs = Integer.parseInt(line[1]);
            		}
            		if(line[0].equals("Show Tabs in Inventory")){
            			this.showInventoryTabs = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Advanced Resource Pack Support")){
            			this.useAdvancedResourcePackSupport = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Play Soud when a GUI is Opened")){
            			this.playOpenGUISound = Boolean.parseBoolean(line[1]);
            		}
            		/**
            	 	if(line[0].equals("TestBoolean")){
            	 		this.testBoolean = Boolean.parseBoolean(line[1]);
                    }
            	 	
            	 	if(line[0].equals("Test Cycle")){
            	 		this.testCycle = line[1];
            	 	}
            	 	
            	 	if(line[0].equals("Test Integer")){
            	 		this.testInteger = Integer.parseInt(line[1]);
            	 	}
            	 	
            	 	if(line[0].equals("Test Float")){
            	 		this.testFloat = Float.parseFloat(line[1]);
            	 	}
            	 	*/
            	 	
            	 }
             }
             catch(Exception e){
            	 
             }
         }
         bufferedreader.close();
	}
	
	public String convertArrayListToStrings(Object[] arr){
		String str = "";
		for(int i = 0; i < arr.length; i++){
			if(arr[i] !=null){
				if(i == 0){
					str = arr[i].toString();
				}
				else{
					str = str+", "+arr[i].toString();
				}
			}
		}
		
		return str;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerOptions(){
		//TODO add options here
		/**this.addOption(0, new OptionBoolean(true, "testBoolean", "TestBoolean"));
		this.addOption(1, new OptionCycle(true, "testCycle", "Test Cycle", this.varTypes));
		this.addOption(2, new OptionGUI(new GuiIngameMenu(), "Test Gui"));
		this.addOption(3, new OptionInteger(true, "testInteger", "Test Integer", this.testIntegerMin, this.testIntegerMax, 10));
		this.addOption(4, new OptionFloat(true, "testFloat", "Test Float", this.testFloatMin, this.testFloatMax, 0.1F));
		*/
		
		//Main Page
		this.addOption(0, new OptionGUI(new GuiWSBIMSettingsHUD(Minecraft.getMinecraft()), "HUD Customization"));
		this.addOption(3, new OptionBoolean(true, "useAdvancedResourcePackSupport", "Adv. Res. Pack Support"));
		this.addOption(4, new OptionBoolean(true, "playOpenGUISound", "Play Open Gui Sound"));
		//Use ids 8 - 15
		this.addOption(1, new OptionBoolean(true, "checkForUpdates", "Check for Updates"));
		
		//Use ids 16 - 23
		this.addOption(2, new OptionGUI(new GuiWSBIMSetingsTabs(Minecraft.getMinecraft()), "Tab Customization"));
		
		//HUD Customization
		this.addOption(8, new OptionCycle(true, "currentTheme", "Theme", WSBIM.themes.getAvalableThemes()));
		this.addOption(9, new OptionBoolean(true, "showScoreboard", "Render Scoreboard"));
		this.addOption(10, new OptionBoolean(true, "showCrosshairs", "Render Crosshairs"));
		this.addOption(11, new OptionBoolean(true, "showArmorInHUD", "Show Armor in HUD"));
		this.addOption(12, new OptionCycle(true, "themeBrightness", "Background Opacity", this.brightnesses));
		
		//Tab Customization
		this.addOption(16, new OptionBoolean(true, "showContainerTabs", "Show Tabs in Containers"));
		this.addOption(17, new OptionInteger(true, "minHeightForTabs", "Min. Height for Tabs", 20, 256 - 26, 1));
		this.addOption(18, new OptionInteger(true, "maxSlotsForTabs", "Max Slots for Tabs", 50, 200, 1));
		this.addOption(19, new OptionBoolean(true, "showInventoryTabs", "Show Tabs in Inventory"));
		
	}
	
	@SideOnly(Side.CLIENT)
	public void addOption(int id, Option option){
		this.optionsList.set(id, option);
	}
	
	
	public enum OptionType{
		INT,BOOLEAN,FLOAT,STRING,CYCLE,GUI;
		
		private OptionType(){
			
		}
	}
	
}
