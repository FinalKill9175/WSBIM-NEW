package com.FinalKill.wsbim.util;

import static net.minecraftforge.common.util.ForgeDirection.*;
import net.minecraftforge.common.util.ForgeDirection;

public class ForgeDirectionUtils {
	/**Returns a filled matrix with all of the directions in place in this order: UP, DOWN, NORTH, SOUTH, EAST, WEST */
	public static ForgeDirection[] allDirections(){
		return convert(UP, DOWN, NORTH, SOUTH, EAST, WEST);
	}
	
	public static ForgeDirection[] energyCollectorOutputs(){
		return convert(DOWN, NORTH, SOUTH, EAST, WEST);
	}
	
	private static ForgeDirection[] convert(ForgeDirection... directions){
		return directions;
	}
}
