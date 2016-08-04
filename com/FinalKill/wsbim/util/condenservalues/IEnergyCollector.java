package com.FinalKill.wsbim.util.condenservalues;

public interface IEnergyCollector {
	public boolean isEmitting();
	public boolean isCollecting();
	public int getCV();
	public void setCV(int new_cv);
	public EnumEnergyCollector getCollectorType();
}
