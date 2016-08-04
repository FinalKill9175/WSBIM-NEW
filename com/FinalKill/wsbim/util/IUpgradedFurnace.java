package com.FinalKill.wsbim.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

import com.FinalKill.wsbim.common.block.BlockUpgradedFurnace;

public interface IUpgradedFurnace extends IInventory{

	public static final int fuel = 0;
	public static final int input = 1;
	public static final int input_2 = 2;
	public static final int input_3 = 3;
	public static final int input_4 = 4;
	public static final int output = 5;
	public static final int output_2 = 6;
	public static final int output_3 = 7;
	public static final int output_4 = 8;
	
	public void updateFurnace();
	public void updateDoubleFurnace();
	public boolean isDoubleFurnace();
	public EnumFurnaceType getFurnaceType();
	public boolean isBurning();
	public boolean isSmelting();
	public BlockUpgradedFurnace getFurnaceBlock();
	public int getCookProgressScaled(int i);
	public int getBurnTimeRemainingScaled(int i);
	public int getCookProgress2Scaled(int i);
	public int getCookProgress3Scaled(int i);
	public int getCookProgress4Scaled(int i);
	public World getWorld();
	public int getCookTime();
	public int getSecondCookTime();
	public int getThirdCookTime();
	public int getFourthCookTime();
	public int getBurnTime();
	public int getItemBurnTime();
	public void setCookTime(int i);
	public void setBurnTime(int i);
	public void setItemBurnTime(int i);
	public void setSecondCookTime(int i);
	public void setThirdCookTime(int i);
	public void setFourthCookTime(int i);
}
