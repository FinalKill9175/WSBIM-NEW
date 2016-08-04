package com.FinalKill.wsbim.util.condenservalues;

import net.minecraftforge.common.util.ForgeDirection;

public interface ICondenserValuesReciever extends ICondenserValuesTileEntity{
	public ForgeDirection[] getInputDirections();
	public boolean canRecieve(ForgeDirection direction);
}
