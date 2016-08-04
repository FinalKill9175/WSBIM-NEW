package com.FinalKill.wsbim.util.condenservalues;

import net.minecraftforge.common.util.ForgeDirection;

public interface ICondenserValuesTileEntity {
	public void setCV(int cv);
	public int getCV();
	
	/**Use the Direction matrix in this order: U,D,N,S,E,W*/
	public ForgeDirection[] getDirections();
	
	/**Always call in the updateEntity method!*/
	public void updateCV();
}
