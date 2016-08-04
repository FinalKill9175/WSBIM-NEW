package com.FinalKill.wsbim.util.condenservalues;

import net.minecraftforge.common.util.ForgeDirection;

public interface ICondenserValuesEmitter extends ICondenserValuesTileEntity{
	public int getTicksPerCV();
	public int getCVPerEmition();
	public float getLightIncrementPerCVIncrease();
	public float getMinLightLevel();
	public float getMaxLightLevel();
	public int getCVIncrease();
	public ForgeDirection[] getOutputDirections();
	public boolean canEmit();
}
