package com.FinalKill.condenservalues;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.Timer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CondenserItemValues {
	
	
	
	
	public static boolean valuesAreLoaded = false;
	public static final int capacity = 2000;
	public static ItemStack[] itemsToLoad= new ItemStack[capacity];
	public static List<ItemStack> itemsWithValue = new ArrayList<ItemStack>();
	public static List<Integer> itemValues = new ArrayList<Integer>();
	public static List<Integer> itemIDs = new ArrayList<Integer>();
	public static boolean isWorldLoaded;
	protected static Timer check_timer;
	protected static Timer check_timer_2;

	public static File valuesFolder = new File(FMLCommonHandler.instance().getSide() == Side.SERVER? MinecraftServer.getServer().getFile("").getPath() + "//condenser_values" : Minecraft.getMinecraft().mcDataDir.getPath()+"//condenser_values");
	
	protected static boolean needsToUpdateCondenserValues = false;
	protected static final String id_version = CondenserValuesAPI.version;
	
	protected static final String metadata_file_url = "https://www.dropbox.com/s/by4demxp4swb65o/condenser_values_metadata.txt?dl=1";
	
	protected static MetaDataFile metaData;
	
	protected static File metaDataFile = new File(FMLCommonHandler.instance().getSide() == Side.SERVER? MinecraftServer.getServer().getFile("").getPath() + "//condenser_values_metadata.txt" : Minecraft.getMinecraft().mcDataDir.getPath()+"//condenser_values_metadata.txt");
	
	protected static List<FileZip> filesToDownload = new ArrayList<FileZip>();
	
	protected static File urlsFile = new File(FMLCommonHandler.instance().getSide() == Side.SERVER? MinecraftServer.getServer().getFile("").getPath() + "//condenser_values_urls.txt" : Minecraft.getMinecraft().mcDataDir.getPath()+"//condenser_values_urls.txt");
	
	public static boolean hasCreatedCondenserValues = false;
	
	protected static int prevFilesDownloaded = 0;
	
	protected static List<CondenserValueFile> condenserFiles = new ArrayList<CondenserValueFile>();
	
	public static Integer[] condenserValues = new Integer[itemsToLoad.length];
	private static boolean hasValue;
	
	//public static NBTTagCompound itemCompound = new NBTTagCompound();
	public static final String value = "value";

	@Deprecated
	/**WIP*/
	public static void addCondenserValueZipFile(FileZip file){
		filesToDownload.add(file);
	}
	
	protected static void loadItemValues(boolean isLoggingInToWorld){
		for(int currentID = 0; currentID<itemsWithValue.size(); currentID++){
			ItemStack currentStack = itemsWithValue.get(currentID);
			if(currentStack !=null){
				int currentItemValue = itemsToLoad[currentID].stackTagCompound.getInteger("value");
				if(isLoggingInToWorld){
						
				}
				else{
					Item currentItem = currentStack.getItem();
					ItemTooltipEvent e = EventHooksFinalKill.e;
					if(e.itemStack.getItem() == currentItem){
				    e.toolTip.add(EnumChatFormatting.WHITE+"Condenser Value : "+currentItemValue+ " CV");
				}
			}
		}
	}
	valuesAreLoaded = true;	
    }
	
	public static void setItemValue(Item item, int value){
		if(doesItemStackHaveValue(new ItemStack(item))){
			itemValues.set(getIDFromItem(item), value);
			
		}
		
		
	}
	public static void setBlockValue(Block item, int value){
		if(doesItemStackHaveValue(new ItemStack(item))){
			itemValues.set(getIDFromBlock(item), value);
			
		}
		
		
	}
	
	public static void setItemStackValue(ItemStack item, int value){
		if(doesItemStackHaveValue(item)){
			itemValues.set(getIDFromItemStack(item), value);
			
		}
		
		
	}
	
	public static Item getItemForString(String str){
		return (Item)Item.itemRegistry.getObject(str);
	}
	
	public static Block getBlockForString(String str){
		return (Block)Block.blockRegistry.getObject(str);
	}
	
	public static void createValues(){
		createItemValues();
		createBlockValues();
		System.out.println("Creating condenser values!");
	
		File[] filesToCheck = CondenserItemValues.valuesFolder.listFiles();
		for(int i = 0; i < filesToCheck.length; i++){
			File current = filesToCheck[i];
			if(current.getName().startsWith("value_") && current.getPath().endsWith(".cv")){
				
				try{
					FileReader inputFile = new FileReader(current);
				    BufferedReader bufferReader = new BufferedReader(inputFile);
					String itemModid = null;
					String metadata = null;
					//String itemID = null;
					String value = null;
					String itemUnlocalizedName = null;
					System.out.println("Found file "+current.getPath()+" as a condenser values file!");
				
				    String line;
				    while ((line = bufferReader.readLine()) != null)   {
				    	/**
				    		 itemModid = readLine(line, "modid");
				    		
				           metadata = readLine(line,"metadata");
				          // itemID = readLine(line, "item_id");
				           value = readLine(line, "item_value");
				           itemUnlocalizedName = readLine(line, "item_name");
				           */
				    	
				    	if(line.startsWith("modid")){
				    		itemModid = line.substring(6);
				    	}
				    	
				    	if(line.startsWith("metadata")){
				    		metadata = line.substring("metadata".length()+1);
				    	}
				    	
				    	if(line.startsWith("item_value")){
				    		value = line.substring("item_value".length()+1);
				    	}
				    	
				    	if(line.startsWith("item_name")){
				    		itemUnlocalizedName = line.substring("item_name".length()+1);
				    	}
				    }
				    bufferReader.close();
				    
				    if(itemModid !=null && Loader.isModLoaded(itemModid)){
				    
						//TODO CondenserItemValues.condenserFiles.add(new CondenserValueFile(itemModid, itemUnlocalizedName, metadata, value));
					}
					if(itemModid == "minecraft"){
						//TODO CondenserItemValues.condenserFiles.add(new CondenserValueFile(itemModid, itemUnlocalizedName, metadata, value));
					}
				}
				
				catch(Exception e){
		         e.printStackTrace();
				}
				
				
		   }
		}
		
		
		//TODO
		System.out.println(needsToUpdateCondenserValues);
		for(int i = 0; i < condenserFiles.size(); i++){
			CondenserValueFile file = condenserFiles.get(i);
			if(file !=null){
				System.out.println("Adding "+file);
				ItemStack stack = calcItemFromFile(file);
				Item item = stack.getItem();
				registerItemValue(nextID(), file.getValue(), item);
			}
		}
		
		CondenserItemValues.hasCreatedCondenserValues = true;
	}
	
	static ItemStack calcItemFromFile(CondenserValueFile file){
		
	ItemStack stack = new ItemStack((getItemForString(file.getItemUnlocalizedName()) instanceof ItemBlock?Item.getItemFromBlock(getBlockForString(file.getItemUnlocalizedName())) : getItemForString(file.getItemUnlocalizedName())));
	
		return  (file.hasMetadata()? stack: new ItemStack((getItemForString(file.getItemUnlocalizedName()) instanceof ItemBlock?Item.getItemFromBlock(getBlockForString(file.getItemUnlocalizedName())) : getItemForString(file.getItemUnlocalizedName()))));
	}
	
	static int nextID(){
		for(int i = 0; i < CondenserItemValues.itemsWithValue.size(); i++){
			if(itemsWithValue.get(i) == null){
				return i;
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	/**
	 * Registers a condenservalue from a string that can be read in the item/block registry. Useful for cross mod compatibility.
	 * @param val - The input string, will read like you would in the /give command use it like ex. minecraft:dirt, or (modid:unlocalizedName), TAKE NOTE: This does not work for metadata values at this moment. Also, IDs ARE NOT SUPPORTED IN THIS!
	 * @param value - The value to assign this item or block.
	 * @param isBlock - Is what you're trying to register a block?
	 */
	//TODO
	public static void registerValueWithString(String val, int value, boolean isBlock){
		if(isBlock){
			Object obj = Block.blockRegistry.getObject(val);
			if(obj == null){
				System.err.println("Error registering a condenser value with the string "+val+" because it was not found in the block registry.");
				return;
			}
			if(obj instanceof Block){
				Block b = (Block)obj;
				registerBlockValue(nextID(), value, b);
			}
			else{
				System.err.println("For some reason, the string found in the block registry is not a block, an unknown error has ocured while registering the value "+val);
				return;
			}
		}
		else{
			Object obj = Item.itemRegistry.getObject(val);
			if(obj == null){
				System.err.println("Error registering a condenser value with the string "+val+" because it was not found in the item registry.");
				return;
			}
			if(obj instanceof Item){
				Item item = (Item)obj;
				registerItemValue(nextID(), value, item);
			}
			else{
				System.err.println("For some reason, the string found in the item registry is not an item, an unknown error has ocured while registering the value "+val);
				return;
			}
		}
	}
	
	/**
	 * Does a simular thing as the registerValueWithString method, but is geared more towards cross mod compatibility. It will check to see if the mod is found, and if so, it will register the item or block's condenser value.
	 * @param modid - The modid this item or block is uncluded with.
	 * @param unlocalizedName - The item/block's unocalized name.
	 * @param value - The value to be assigned to this item or block.
	 * @param isBlock - Is what you're registering a block?
	 */
	public static void registerValueWithCrossModCompatibility(String modid, String unlocalizedName, int value, boolean isBlock){
		if(Loader.isModLoaded(modid)){
			registerValueWithString(modid+":"+unlocalizedName, value, isBlock);
		}
		else{
			System.err.println("Mod not found, "+modid+" while registering a value for "+modid+":"+unlocalizedName);
			return;
		}
	}
	
	static String readLine(String line, String key){
			return line.substring(key.length()+1);
		
		
	}
	
	protected static void createItemValues(){
		
		CondenserItemValues.registerItemValue(4, 16, Items.apple);
		CondenserItemValues.registerItemValue(5, 8, Items.arrow);
		CondenserItemValues.registerItemValue(6, 24, Items.baked_potato);
		CondenserItemValues.registerItemValue(7, 48, Items.bed);
		CondenserItemValues.registerItemValue(8, 64, Items.beef);
		CondenserItemValues.registerItemValue(9, 768, Items.blaze_powder);
		CondenserItemValues.registerItemValue(10, 768*3, Items.blaze_rod);
		CondenserItemValues.registerItemValue(11, 40, Items.boat);
		CondenserItemValues.registerItemValue(12, 144, Items.bone);
		CondenserItemValues.registerItemValue(13, 96, Items.book);
		CondenserItemValues.registerItemValue(14, 48, Items.bow);
		CondenserItemValues.registerItemValue(15, 6, Items.bowl);
		CondenserItemValues.registerItemValue(16, 72, Items.bread);
		CondenserItemValues.registerItemValue(17, 16, Items.brick);
		CondenserItemValues.registerItemValue(18, 768, Items.bucket);
		CondenserItemValues.registerItemValue(19, 769, Items.water_bucket);
		CondenserItemValues.registerItemValue(20, 832, Items.lava_bucket);
		CondenserItemValues.registerItemValue(21, 411, Items.cake);
		CondenserItemValues.registerItemValue(22, 16, Items.carrot);
		CondenserItemValues.registerItemValue(23, 256*4, Items.cauldron);
		CondenserItemValues.registerItemValue(24, 64, Items.chicken);
		CondenserItemValues.registerItemValue(25, 64, Items.cooked_chicken);
		CondenserItemValues.registerItemValue(26, 16, Items.clay_ball);
		CondenserItemValues.registerItemValue(27, 8256, Items.clock);
		CondenserItemValues.registerItemValue(28, 128, Items.coal);
		CondenserItemValues.registerItemValue(29, 256+(68*3)+3, Items.comparator);
		CondenserItemValues.registerItemValue(30, 128, Items.quartz);
		CondenserItemValues.registerItemValue(31, 1088 , Items.compass);
		CondenserItemValues.registerItemValue(32, 32, Items.cooked_beef);
		CondenserItemValues.registerItemValue(33, 64, Items.cooked_fished);
		CondenserItemValues.registerItemValue(34, 64, Items.cooked_porkchop);
		CondenserItemValues.registerItemValue(35, 22, Items.cookie);
		CondenserItemValues.registerItemValue(36, 8192, Items.diamond);
		CondenserItemValues.registerItemValue(37, 24584, Items.diamond_axe);
		CondenserItemValues.registerItemValue(38, 16392, Items.diamond_hoe);
		CondenserItemValues.registerItemValue(39, 24584, Items.diamond_pickaxe);
		CondenserItemValues.registerItemValue(40, 16388, Items.diamond_sword);
		CondenserItemValues.registerItemValue(41, 8200, Items.diamond_shovel);
		CondenserItemValues.registerItemValue(43, 8,Items.dye);
		CondenserItemValues.registerItemValue(42, 32, Items.egg);
		CondenserItemValues.registerItemValue(45, 8192/2, Items.emerald);
		CondenserItemValues.registerItemValue(46, 1792, Items.ender_eye);
		CondenserItemValues.registerItemValue(47, 1024, Items.ender_pearl);
		CondenserItemValues.registerItemValue(48, 48, Items.feather);
		CondenserItemValues.registerItemValue(49, 192, Items.fermented_spider_eye);
		CondenserItemValues.registerItemValue(50, 298, Items.fire_charge);
		CondenserItemValues.registerItemValue(51, 192*4, Items.firework_charge);
		CondenserItemValues.registerItemValue(52, 64, Items.fish);
		CondenserItemValues.registerItemValue(53, 4, Items.flint);
		CondenserItemValues.registerItemValue(54, 260, Items.flint_and_steel);
		CondenserItemValues.registerItemValue(55, 48, Items.flower_pot);
		CondenserItemValues.registerItemValue(56, 4096, Items.ghast_tear);
		CondenserItemValues.registerItemValue(57, 1, Items.glass_bottle);
		CondenserItemValues.registerItemValue(58, 384, Items.glowstone_dust);
		CondenserItemValues.registerItemValue(59, 2048, Items.gold_ingot);
		CondenserItemValues.registerItemValue(60, 1944, Items.golden_apple);
		CondenserItemValues.registerItemValue(61, 6152, Items.golden_axe);
		CondenserItemValues.registerItemValue(62, 4104, Items.golden_hoe);
		CondenserItemValues.registerItemValue(63, 6152, Items.golden_pickaxe);
		CondenserItemValues.registerItemValue(64, 4100, Items.golden_sword);
		CondenserItemValues.registerItemValue(65, 2056, Items.golden_shovel);
		CondenserItemValues.registerItemValue(66, 227, Items.gold_nugget);
		CondenserItemValues.registerItemValue(67, 192, Items.gunpowder);
		CondenserItemValues.registerItemValue(68, 256, Items.iron_ingot);
		CondenserItemValues.registerItemValue(69, 256*6, Items.iron_door);
		CondenserItemValues.registerItemValue(70, 776, Items.iron_axe);
		CondenserItemValues.registerItemValue(71, 520, Items.iron_hoe);
		CondenserItemValues.registerItemValue(72, 776, Items.iron_pickaxe);
		CondenserItemValues.registerItemValue(73, 516, Items.iron_sword);
		CondenserItemValues.registerItemValue(74, 264, Items.iron_shovel);
		CondenserItemValues.registerItemValue(75, 64, Items.leather);
		CondenserItemValues.registerItemValue(76, 792, Items.magma_cream);
		CondenserItemValues.registerItemValue(77, 16, Items.melon);
		CondenserItemValues.registerItemValue(78, 16, Items.melon_seeds);
		CondenserItemValues.registerItemValue(79, 1344, Items.map);
		CondenserItemValues.registerItemValue(80, 849, Items.milk_bucket);
		CondenserItemValues.registerItemValue(81, 1280, Items.minecart);
		CondenserItemValues.registerItemValue(82, 70, Items.mushroom_stew);
		CondenserItemValues.registerItemValue(83, 8192*8, Items.nether_star);
		CondenserItemValues.registerItemValue(84, 24, Items.nether_wart);
		//Remember, the nether brick block must be 16!
		CondenserItemValues.registerItemValue(85, 1, Items.netherbrick);
		//Carry On :)
		CondenserItemValues.registerItemValue(86, 32, Items.paper);
		CondenserItemValues.registerItemValue(87, 24, Items.poisonous_potato);
		CondenserItemValues.registerItemValue(88, 24, Items.potato);
		CondenserItemValues.registerItemValue(89, 64, Items.porkchop);
		CondenserItemValues.registerItemValue(90, 64, Items.pumpkin_pie);
		CondenserItemValues.registerItemValue(91, 36, Items.pumpkin_seeds);
		CondenserItemValues.registerItemValue(92, 64, Items.redstone);
		CondenserItemValues.registerItemValue(93, 32, Items.reeds);
		CondenserItemValues.registerItemValue(94, 203, Items.repeater);
		CondenserItemValues.registerItemValue(95, 24, Items.rotten_flesh);
		CondenserItemValues.registerItemValue(96, 24, Items.slime_ball);
		CondenserItemValues.registerItemValue(97, 1, Items.snowball);
		CondenserItemValues.registerItemValue(98, 243, Items.speckled_melon);
		CondenserItemValues.registerItemValue(99, 128, Items.spider_eye);
		CondenserItemValues.registerItemValue(100, 4, Items.stick);
		CondenserItemValues.registerItemValue(101, 11, Items.stone_axe);
		CondenserItemValues.registerItemValue(102, 10, Items.stone_hoe);
		CondenserItemValues.registerItemValue(103, 11, Items.stone_pickaxe);
		CondenserItemValues.registerItemValue(104, 6, Items.stone_sword);
		CondenserItemValues.registerItemValue(105, 10, Items.stone_shovel);
		CondenserItemValues.registerItemValue(106, 12, Items.string);
		CondenserItemValues.registerItemValue(107, 32, Items.sugar);
		CondenserItemValues.registerItemValue(108, 24, Items.wheat);
		CondenserItemValues.registerItemValue(109, 16, Items.wheat_seeds);
		CondenserItemValues.registerItemValue(110, 32, Items.wooden_axe);
		CondenserItemValues.registerItemValue(111, 24, Items.wooden_hoe);
		CondenserItemValues.registerItemValue(112, 32, Items.wooden_pickaxe);
		CondenserItemValues.registerItemValue(113, 20, Items.wooden_sword);
		CondenserItemValues.registerItemValue(114, 16, Items.wooden_shovel);
		
	}
	
	public static void prepareValues(){
		CondenserItemValues.hasCreatedCondenserValues = false;
		for(int i = 0; i<itemsToLoad.length; i++){	
			itemsWithValue.add(i, null);
			itemValues.add(i, null);
		}

		
	
		new DownloaderMetadataFile(metadata_file_url,metaDataFile.getPath(),"condenser_values_metadata.txt");
		
	

	}
	
	protected static void continuePreparingValues() throws IOException{
		boolean dontReadUrlsFile = false;
		
		needsToUpdateCondenserValues = false;
		if(!valuesFolder.exists()){
			//TODO 
			needsToUpdateCondenserValues = true;
			valuesFolder.mkdir();

		}
		if(urlsFile.exists()){
			urlsFile.delete();
		}
		if(!urlsFile.exists()){
			dontReadUrlsFile = true;
		}
		
		metaDataFile.deleteOnExit();
		
		String key_version="id_version";
		String aurl = "url";
	
		List<String> urls = new ArrayList<String>();
		
		
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(CondenserItemValues.metaDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    BufferedReader bufferReader = new BufferedReader(inputFile);
	    String line;
	    String meta = null;
	    boolean b = false;
	    while ((line = bufferReader.readLine()) != null)   {
	            if(line.startsWith(key_version)){
	            	meta = (line.substring(key_version.length()+1));
	            }
	            if(line.startsWith(aurl)){
	            	urls.add(line.substring(aurl.length()+1));
	            }
	    }
	    bufferReader.close();

	    metaData = new MetaDataFile(meta, urls);
		for(int i = 0; i < urls.size(); i++){
			if(urls.get(i) !=null){
				filesToDownload.add(new FileZip(urls.get(i),new Random().nextInt() * new Random().nextInt() + "", CondenserValuesAPI.version));
				
			}
		}
		 List<String> prevFileVersions = null;
		 List<String> fileVersions = new ArrayList<String>();
		   
		 for(int i = 0; i < filesToDownload.size(); i++){
			 if(filesToDownload.get(i) !=null){
				 //TODO WIP fileVersions.add(filesToDownload.get(i).fileVersion);
			 }
		 }
		if(!dontReadUrlsFile){
			prevFileVersions = new ArrayList<String>();
			FileReader inputFile2 = null;
			try {
				inputFile = new FileReader(CondenserItemValues.urlsFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    BufferedReader bufferReader2 = new BufferedReader(inputFile);
		    String line2;
		    
		    while ((line2 = bufferReader.readLine()) != null)   {
		    	if(line2.startsWith("files_to_download")){
		           CondenserItemValues.prevFilesDownloaded = Integer.parseInt(line2.substring("files_to_download".length()+1));
		    	}
		    	for(int i = 0; i < prevFilesDownloaded; i++){
		    		if(line2.startsWith("file_version_"+i)){
		    			prevFileVersions.add(line2.substring("file_version_#".length()+1));
		    		}
		    	}
		    }
		    bufferReader.close();
		    
	
		    
		    //Uppdate the file now.
		    urlsFile.createNewFile();
			FileWriter fw = new FileWriter(urlsFile);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("files_to_download="+filesToDownload.size());
			for(int i = 0; i < filesToDownload.size(); i++){
				FileZip zip = filesToDownload.get(i);
				pw.println("zip_name_"+i+"="+zip.name);
				pw.println("url_"+i+"="+zip.url);
				pw.println("file_version_"+i+"="+zip.fileVersion);
			}
			pw.close();
		}
		
		if(!urlsFile.exists()){
			urlsFile.createNewFile();
			FileWriter fw = new FileWriter(urlsFile);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("files_to_download="+filesToDownload.size());
			for(int i = 0; i < filesToDownload.size(); i++){
				FileZip zip = filesToDownload.get(i);
				pw.println("zip_name_"+i+"="+zip.name);
				pw.println("url_"+i+"="+zip.url);
				pw.println("file_version_"+i+"="+zip.fileVersion);
			}
			pw.close();
		}
		
		
		if(prevFileVersions !=null){
			if(!isSameList(prevFileVersions, fileVersions)){
		    	needsToUpdateCondenserValues = true;
		    }
		}
	    if(!(metaData.getVersion().equals(CondenserValuesAPI.version))){
	   //TODO 	
	    	needsToUpdateCondenserValues = true;
	    }
	    
	    	downloadZips();
	    
	    
	}
	
	static boolean isSameList(List<?> list, List<?> list2){
		if(list.size() == list.size()){
			for(int i = 0; i < list.size(); i++){
				Object obj = list.get(i);
				Object obj2 = list.get(i);
				if(obj == null || obj2 == null){
					return false;
				}
				if(obj.equals(obj2)){
					return true;
				}
			}
			return false;
		}
		else return false;
	}
	
	private static void downloadZips() throws IOException{
		if(needsToUpdateCondenserValues){
			for(int i = 0; i < valuesFolder.listFiles().length; i++){
				File f = valuesFolder.listFiles()[i];
				f.delete();
			}
		}
		
		FileZip zipFile = filesToDownload.get(0);
		if(zipFile !=null){
			new DownloaderZip(0, zipFile.url, CondenserItemValues.valuesFolder.getPath()+"//"+zipFile.name+".zip",zipFile.name+".zip");
		}
		
	}
	
	protected static void extractZips() throws IOException{
		FileZip zip = filesToDownload.get(0);
		if(zip !=null){
			unZipIt(0, CondenserItemValues.valuesFolder.getPath()+"//"+zip.name+".zip", CondenserItemValues.valuesFolder.getPath(), true);
		}
	}
	
	/**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public static void unZipIt(int id, String zipFile, String outputFolder, boolean b){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	 if(needsToUpdateCondenserValues){
    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdirs();
    	}
 
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
 
    	while(ze!=null){
 
    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);
           System.out.println("Unzipping : "+ newFile.getAbsoluteFile());
           CharSequence var1 = ".";
           if(!newFile.getName().contains(var1)){
        	   newFile.mkdirs();
           }
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
 
            FileOutputStream fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
 
            fos.close();   
            ze = zis.getNextEntry();
    	}
 
        zis.closeEntry();
    	zis.close();
 
    	System.out.println("Done");
    	
    	new File(zipFile).delete();
    	 }
    	 else{
    		 
    	 }
    	if(b){
    		if(id<filesToDownload.size()-1){
    			FileZip zip = filesToDownload.get(id+1);
    			if(zip !=null){
    				unZipIt(id+1, CondenserItemValues.valuesFolder.getPath()+"//"+zip.name+".zip", CondenserItemValues.valuesFolder.getPath(), true);
    			}    		
    		}
    		else{
    			createValues();
    		}
    	}
    	
 
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
	public static void registerItemValue(int id, int value, Item item){
		
	
		if(itemsToLoad[id] !=null){
			Minecraft.getMinecraft().crashed(new CrashReport("Condenser Value ID Conflict", new CondenserIDThrowable("ID Conflict when registering an item for a condenser value with the id of "+id+" and the item "+itemsToLoad[id].getDisplayName())));
			System.err.println("Unsuccsessfully Registered a Condenser Value with the id of "+ id+" and has already been occupied by "+itemsToLoad[id].getDisplayName());

		}
		else{
		
	
	//	itemsToLoad[id].equals(item);
		itemsToLoad[id] = new ItemStack(item);
		if(!itemsToLoad[id].hasTagCompound()){
			itemsToLoad[id].setTagCompound(new NBTTagCompound());
		}
		itemsWithValue.set(id,itemsToLoad[id]);
	itemsToLoad[id].stackTagCompound.setInteger(CondenserItemValues.value, value);	
	itemValues.set(id, value);
			
		
		
		
		
	
		//System.out.println("Succsessfully Registered a Condenser Value with the item name of "+ itemsToLoad[id].getDisplayName()+" with the condenser value of "+value+ " and the id of "+ id);
		}
		
	}
	
	public static void registerBlockValue(int id, int value, Block block){
		if(itemsToLoad[id] !=null){
			Minecraft.getMinecraft().crashed(new CrashReport("Condenser Value ID Conflict", new CondenserIDThrowable("ID Conflict when registering an block for a condenser value with the id of "+id+" and the item "+itemsToLoad[id].getDisplayName())));
			System.err.println("Unsuccsessfully Registered a Condenser Value with the id of "+ id+" and has already been occupied by "+itemsToLoad[id].getDisplayName());

		}
		else{
		
		
	//	itemsToLoad[id].equals(item);
		itemsToLoad[id] = new ItemStack(Item.getItemFromBlock(block));
		if(!itemsToLoad[id].hasTagCompound()){
		itemsToLoad[id].setTagCompound(new NBTTagCompound());
		}
		itemsWithValue.set(id,itemsToLoad[id]);
		itemsToLoad[id].stackTagCompound.setInteger(CondenserItemValues.value, value);	
	
		itemValues.set(id, value);
		
	
		//System.out.println("Succsessfully Registered a Condenser Value with the block name of "+ itemsToLoad[id].getDisplayName()+" with the condenser value of "+value+ " and the id of "+ id);
		}
		
		
	}
	
	public static ItemStack getItemStackThatHasValue(int id){
		
		if(valuesAreLoaded){
			
			return itemsWithValue.get(id);
		}
		else{
		
		return null;
		}
		
		
		
	}
	
	public static int getItemValue(int id){
		
		if(valuesAreLoaded){
			
			
			return  itemValues.get(id);
		}
		else{
		
		return 0;
		}
		
		
	}
	
	public static int getItemValueFromItemStack(ItemStack stack){
		
		if(doesItemStackHaveValue(stack)){
			
			return itemValues.get(getIDFromItemStack(stack));
			
		}
		else{
			
			return 0;
		}
		
	}
public static int getItemValueFromItem(Item stack){
		
		if(doesItemStackHaveValue(new ItemStack(stack))){
			
			return itemValues.get(getIDFromItem(stack));
			
		}
		else{
			
			return 0;
		}
		
	}
	
public static int getItemValueFromBlock(Block stack){
	
	if(doesItemStackHaveValue(new ItemStack(Item.getItemFromBlock(stack)))){
		
		return itemValues.get(getIDFromBlock(stack));
		
	}
	else{
		
		return 0;
	}
	
}

	
	public static ItemStack[] getItemValueStack(){
		
		return itemsToLoad;
		
	}
	
	public static int getIDFromItemStack(ItemStack stack){
		int id = 0;
		for(int i = 0; i<capacity; i++){
			
			ItemStack currentStack = itemsToLoad[i];
			if(currentStack !=null){
				
				if(currentStack.getItem() == stack.getItem()){
					
					id = itemsWithValue.indexOf(currentStack);
					
					
				}
				
				
			}
			
		}
		
		return id;
		
		
	}
	
	public static int getIDFromItem(Item stack){
		int id = 0;
		for(int i = 0; i<capacity; i++){
			
			ItemStack currentStack = itemsToLoad[i];
			if(currentStack !=null){
				
				if(currentStack.getItem() == stack){
					
					id = itemsWithValue.indexOf(currentStack);
					
					
				}
				
				
			}
			
		}
		
		return id;
		
		
	}
	public static int getIDFromBlock(Block stack){
		int id = 0;
		for(int i = 0; i<capacity; i++){
			
			ItemStack currentStack = itemsToLoad[i];
			if(currentStack !=null){
				
				if(currentStack.getItem() == Item.getItemFromBlock(stack)){
					
					id = itemsWithValue.indexOf(currentStack);
					
					
				}
				
				
			}
			
		}
		
		return id;
		
		
	}
	public static boolean doesItemStackHaveValue(ItemStack item){
		int id = 0;
	
	for(int i = 0; i<itemsToLoad.length; i++){
		
		ItemStack currentStack = itemsToLoad[i];
		
		if(currentStack !=null){
		if(currentStack.getItem()== item.getItem()){
			id = itemValues.get(i) ;
		//	System.out.println(item.getItem());
			//System.out.println(id);
			return id != 0;
			
		}
		
		}
		
	}
	
	return id != 0;	
		
		
	}


	protected static void createBlockValues() {
		CondenserItemValues.registerBlockValue(115, 512, Blocks.activator_rail);
		CondenserItemValues.registerBlockValue(116, 4096, Blocks.anvil);
		CondenserItemValues.registerBlockValue(117, 1, Blocks.bedrock);
		CondenserItemValues.registerBlockValue(118, 336, Blocks.bookshelf);
		CondenserItemValues.registerBlockValue(119, 64, Blocks.brick_block);
		CondenserItemValues.registerBlockValue(120, 32, Blocks.cactus);
		CondenserItemValues.registerBlockValue(121, 4, Blocks.carpet);
		CondenserItemValues.registerBlockValue(122, 64, Blocks.clay);
		CondenserItemValues.registerBlockValue(123, 128*9, Blocks.coal_block);
		CondenserItemValues.registerBlockValue(124, 1, Blocks.cobblestone);
		CondenserItemValues.registerBlockValue(125, 32, Blocks.crafting_table);
		CondenserItemValues.registerBlockValue(126, 256, Blocks.detector_rail);
		CondenserItemValues.registerBlockValue(127, 8192*9, Blocks.diamond_block);
		CondenserItemValues.registerBlockValue(128, 1, Blocks.dirt);
		CondenserItemValues.registerBlockValue(129, 119, Blocks.dispenser);
		CondenserItemValues.registerBlockValue(130, 119, Blocks.dropper);
		CondenserItemValues.registerBlockValue(131, (8192*2)*9, Blocks.emerald_block);
		CondenserItemValues.registerBlockValue(132, 16736, Blocks.enchanting_table);
		CondenserItemValues.registerBlockValue(133, 8192, Blocks.ender_chest);
		CondenserItemValues.registerBlockValue(134, 12, Blocks.fence);
		CondenserItemValues.registerBlockValue(135, 8, Blocks.furnace);
		CondenserItemValues.registerBlockValue(136, 1, Blocks.glass);
		CondenserItemValues.registerBlockValue(137, 1, Blocks.glass_pane);
		CondenserItemValues.registerBlockValue(138, 2048*9, Blocks.gold_block);
		CondenserItemValues.registerBlockValue(139, 2048, Blocks.golden_rail);
		CondenserItemValues.registerBlockValue(140, 4, Blocks.gravel);
		CondenserItemValues.registerBlockValue(141, 1, Blocks.grass);
		CondenserItemValues.registerBlockValue(142, 64+8, Blocks.hardened_clay);
		CondenserItemValues.registerBlockValue(143, 24*9, Blocks.hay_block);
		CondenserItemValues.registerBlockValue(144, 1, Blocks.ice);
		CondenserItemValues.registerBlockValue(145, 96, Blocks.iron_bars);
		CondenserItemValues.registerBlockValue(146, 256*9, Blocks.iron_block);
		CondenserItemValues.registerBlockValue(147, 8256, Blocks.jukebox);
		CondenserItemValues.registerBlockValue(148, 14, Blocks.ladder);
		CondenserItemValues.registerBlockValue(149, 8*9, Blocks.lapis_block);
		CondenserItemValues.registerBlockValue(150, 9, Blocks.lever);
		CondenserItemValues.registerBlockValue(151, 144+9, Blocks.lit_pumpkin);
		CondenserItemValues.registerBlockValue(152, 32, Blocks.log);
		CondenserItemValues.registerBlockValue(153, 32, Blocks.log2);
		CondenserItemValues.registerBlockValue(154, 1, Blocks.leaves);
		CondenserItemValues.registerBlockValue(155, 1, Blocks.leaves2);
		CondenserItemValues.registerBlockValue(156, 128, Blocks.melon_block);
		CondenserItemValues.registerBlockValue(157, 1, Blocks.mossy_cobblestone);
		CondenserItemValues.registerBlockValue(158, 4, Blocks.nether_brick);
		//CondenserItemValues.registerBlockValue(159, 6, Blocks.nether_brick_fence);
		CondenserItemValues.registerBlockValue(160, 1, Blocks.netherrack);
		CondenserItemValues.registerBlockValue(161, 128, Blocks.noteblock);
		CondenserItemValues.registerBlockValue(162, 64, Blocks.obsidian);
		CondenserItemValues.registerBlockValue(163, 8, Blocks.packed_ice);
		CondenserItemValues.registerBlockValue(164, 8, Blocks.planks);
		CondenserItemValues.registerBlockValue(165, 144, Blocks.pumpkin);
		CondenserItemValues.registerBlockValue(166, 128*9, Blocks.quartz_block);
		CondenserItemValues.registerBlockValue(167, 96, Blocks.rail);
		CondenserItemValues.registerBlockValue(168, 32, Blocks.red_mushroom_block);
		CondenserItemValues.registerBlockValue(169, 32, Blocks.brown_mushroom_block);
		CondenserItemValues.registerBlockValue(170, 2, Blocks.mycelium);
		CondenserItemValues.registerBlockValue(171, 64*9, Blocks.redstone_block);
		CondenserItemValues.registerBlockValue(172, 1792, Blocks.redstone_lamp);
		CondenserItemValues.registerBlockValue(173, 68, Blocks.redstone_torch);
		CondenserItemValues.registerBlockValue(174, 16, Blocks.red_flower);
		CondenserItemValues.registerBlockValue(175, 16, Blocks.yellow_flower);
		CondenserItemValues.registerBlockValue(176, 2, Blocks.sand);
		CondenserItemValues.registerBlockValue(177, 8, Blocks.sandstone);
		CondenserItemValues.registerBlockValue(178, 32, Blocks.sapling);
		CondenserItemValues.registerBlockValue(179, 4, Blocks.snow);
		CondenserItemValues.registerBlockValue(180, 1, Blocks.snow_layer);
		CondenserItemValues.registerBlockValue(181, 49, Blocks.soul_sand);
		CondenserItemValues.registerBlockValue(182, 8, Blocks.sponge);
		CondenserItemValues.registerBlockValue(183, 1, Blocks.stone);
		CondenserItemValues.registerBlockValue(184, 2, Blocks.stone_button);
		CondenserItemValues.registerBlockValue(185, 4, Blocks.stonebrick);
		CondenserItemValues.registerBlockValue(186, 9, Blocks.stained_glass);
		CondenserItemValues.registerBlockValue(187, 372, Blocks.sticky_piston);
		CondenserItemValues.registerBlockValue(188, 964, Blocks.tnt);
		CondenserItemValues.registerBlockValue(189, 9, Blocks.torch);
		CondenserItemValues.registerBlockValue(190, 24, Blocks.trapdoor);
		CondenserItemValues.registerBlockValue(191, 128, Blocks.trapped_chest);
		CondenserItemValues.registerBlockValue(192, 1, Blocks.tallgrass);
		CondenserItemValues.registerBlockValue(193, 8, Blocks.tripwire_hook);
		CondenserItemValues.registerBlockValue(194, 1, Blocks.vine);
		CondenserItemValues.registerBlockValue(195, 8, Blocks.water);
		CondenserItemValues.registerBlockValue(196, 8, Blocks.lava);
		CondenserItemValues.registerBlockValue(197, 16, Blocks.web);
		CondenserItemValues.registerBlockValue(198, 16, Blocks.wooden_button);
		CondenserItemValues.registerBlockValue(199, 48, Blocks.wool);
		CondenserItemValues.registerBlockValue(200, 384*4, Blocks.glowstone);
		CondenserItemValues.registerBlockValue(201, CondenserItemValues.getItemValueFromBlock(Blocks.planks)*8, Blocks.chest);
		CondenserItemValues.registerBlockValue(202, (8*4)+3+256+64, Blocks.piston);
		CondenserItemValues.registerItemValue(203, 4+(8*6), Items.sign);
		CondenserItemValues.registerBlockValue(204, (8*3)/6, Blocks.wooden_slab);
		CondenserItemValues.registerBlockValue(205, 1, Blocks.stone_slab);
		CondenserItemValues.registerBlockValue(207, 16, Blocks.wooden_pressure_plate);
		CondenserItemValues.registerBlockValue(209, 2, Blocks.stone_pressure_plate);
		CondenserItemValues.registerBlockValue(210, getItemValueFromBlock(Blocks.brick_block)*6, Blocks.brick_stairs);
		CondenserItemValues.registerBlockValue(211, 48, Blocks.birch_stairs);
		CondenserItemValues.registerBlockValue(212, 48, Blocks.oak_stairs);
		CondenserItemValues.registerBlockValue(213, 48, Blocks.jungle_stairs);
		CondenserItemValues.registerBlockValue(214, getItemValueFromBlock(Blocks.nether_brick)*6, Blocks.nether_brick_stairs);
		CondenserItemValues.registerBlockValue(215, 1, Blocks.nether_brick_fence);
		CondenserItemValues.registerBlockValue(216, (getItemValueFromBlock(Blocks.planks)*2)+(getItemValueFromItem(Items.stick)*4), Blocks.fence_gate);
		CondenserItemValues.registerBlockValue(217, 48, Blocks.acacia_stairs);
		CondenserItemValues.registerBlockValue(218, getItemValueFromBlock(Blocks.diamond_block)*2, Blocks.dragon_egg);
		
		
	}

	/**
	protected static void manageCondenserItemValues() {
		check_timer = new Timer(600, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(MinecraftServer.getServer().isServerRunning() ? MinecraftServer.getServer().getEntityWorld() !=null : Minecraft.getMinecraft().theWorld !=null){
					
					isWorldLoaded = true;
					check_timer.stop();
					CondenserItemValues.loadItemValues(true);
				}
				else{
					
					isWorldLoaded = false;
				}
		
				
				
			}
			
			
		});

		check_timer.start();
		check_timer_2 = new Timer(100, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!(Minecraft.getMinecraft().theWorld !=null) && !check_timer.isRunning()){
					
					check_timer.start();
		
				}
				
			
			}
			
			
		});

		check_timer_2.start();
		
	}
    */

}
