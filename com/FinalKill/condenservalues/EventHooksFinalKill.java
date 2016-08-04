package com.FinalKill.condenservalues;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class EventHooksFinalKill {
	
	public static ItemStack itemStack;
	public static ItemTooltipEvent e;

	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event){
	
	e= event;
	CondenserItemValues.loadItemValues(false);
		
	}
	
}
