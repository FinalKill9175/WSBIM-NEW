package com.FinalKill.MachineryCraftAPI.power;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPowerEmitter extends IPower {
	
public float getMaxPower();
public void emitPowerToConductors();
	

}
