package com.FinalKill.Machinery_Craft.tabs;

import java.util.Random;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.blocks.BlockLamp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MachineryCraftTabs {

	
	
	
	private final Random rand = new Random();
	
	/**public static CreativeTabs machinesTab = new CreativeTabs("machinesTab"){

		@Override
		public Item getTabIconItem() {
		
			return Item.getItemFromBlock(MachineryCraft.blocks.macerator);
		}
    	
    	
    	
    };
    

	  public static CreativeTabs materialsTab = new CreativeTabs("materialsTab"){

			@Override
			public Item getTabIconItem() {
			
				return MachineryCraft.items.industrialIron;
			}
	    	
	    	
	    	
	    };
	    
	    public static CreativeTabs decorTab = new CreativeTabs("decorTab"){

			@Override
			public Item getTabIconItem() {
			
				int lamp = BlockLamp.reg_lamps[new Random().nextInt(7)];
				
				return Item.getItemFromBlock(MachineryCraft.blocks.lamps[lamp]);
			}
	    	
	    	
	    	
	    };
	    public static CreativeTabs chestsTab = null;
	    /**
	    public static CreativeTabs chestsTab = new CreativeTabs("chestsTab"){

			@Override
			public Item getTabIconItem() {
			
				return Item.getItemFromBlock(Blocks.chest);
			}
	    	
	    	
	    	
	    };
	    */
	
	
	
	
	
}
