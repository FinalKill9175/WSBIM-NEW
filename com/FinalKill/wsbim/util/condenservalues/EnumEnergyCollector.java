package com.FinalKill.wsbim.util.condenservalues;

public enum EnumEnergyCollector {
	MK1(EnumEnergyCollectorTexture.DEFAULT, 20, 1, 0.8F, 1.2F, 0.1F, 1),
	MK2(EnumEnergyCollectorTexture.MK2, 6, 1, 0.8F, 1.2F, 0.1F, 1),
	MK3(EnumEnergyCollectorTexture.MK3, 2, 1, 0.8F, 1.2F, 0.1F, 1);
	
	private EnumEnergyCollectorTexture texture;
	private int ticksPerEmittion;
	private int cVPerEmittion;
	public float minLightlevel;
	public float maxLightLevel;
	public float lightLevelDiffPerCVIncrement;
	private int cvIncrease;
	
	private EnumEnergyCollector(EnumEnergyCollectorTexture tex, int ticksPerEmittion, int cVPerEmittion, float minLightLevel, float maxLightLevel, float lightLevelDiffPerCVIncrement, int cvIncrease){
		texture = tex;
		this.ticksPerEmittion = ticksPerEmittion;
		this.cVPerEmittion = cVPerEmittion;
		this.minLightlevel = minLightLevel;
		this.maxLightLevel = maxLightLevel;
		this.lightLevelDiffPerCVIncrement = lightLevelDiffPerCVIncrement;
		this.cvIncrease = cvIncrease;
	}

	public int getCVIncrease() {
		return cvIncrease;
	}

	public EnumEnergyCollectorTexture getTexture() {
		return texture;
	}

	public int getTicksPerEmittion() {
		return ticksPerEmittion;
	}

	public int getCVPerEmittion() {
		return cVPerEmittion;
	}
}
