package com.FinalKill.MachineryCraftAPI.power;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPower {

	public float getPower();
	public ForgeDirection[] getConnections();
	public void setPower(float power);
	public void addPower(float power);
	public void removePower(float power);
	public float powerToUpdateOnTick();
	
	
}
