package com.FinalKill.MachineryCraftAPI.power;

import net.minecraftforge.common.util.ForgeDirection;

public interface IConnectionInput {

	public ForgeDirection[] getConnections();
	public void updateConnections();
	
}
