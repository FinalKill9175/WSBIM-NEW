package com.FinalKill.condenservalues;

public class CondenserValueFile {
	//private int id;
	private String itemUnlocalizedName;
	private String itemMetadata;
	private int value;
	public String itemModid;
	
	public CondenserValueFile(String modid/**, String id*/, String un, String meta, String value){
		//this.id = Integer.getInteger(id);
		this.itemUnlocalizedName = un;
		itemMetadata = meta;
		this.value = Integer.valueOf(value);
		this.itemModid = modid;
		
		System.out.println(modid);
		System.out.println(un);
		System.out.println(meta+" and "+value);
	}

	public String getItemMetadata() {
		return itemMetadata;
	}

	//public int getId() {
	//	return id;
	//}

	public String getItemUnlocalizedName() {
		return itemUnlocalizedName;
	}
	public boolean hasMetadata(){
		return itemMetadata !=null;
	}

	public int getValue() {
		return value;
	}
}
