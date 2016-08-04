package com.FinalKill.MachineryCraftAPI.power;

public interface IPowerConductor extends IPower {

	public float getPowerDuductionPerBlock();
	public void updatePowerInSurroundingConductors();
	public float getMaxLoad();
	public void sendPower();
	
}
