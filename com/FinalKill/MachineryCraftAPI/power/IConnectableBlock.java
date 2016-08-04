package com.FinalKill.MachineryCraftAPI.power;

import net.minecraftforge.common.util.ForgeDirection;

public interface IConnectableBlock {

	public void updateConnections();
	public ForgeDirection[] getConnections();
	public boolean onlyOneOpposite(ForgeDirection[] directions);
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection);
	
	
}
