package com.FinalKill.wsbim.util.addon;

public interface IAddonPack {

	/**Called when the mod is pre initialized, does packs last.*/
	public void packPreInit();
	/**Called when the mod is initialized, does packs last.*/
	public void packInit();
	/**Called when the mod is post initialized, does packs last.*/
	public void packPostInit();
	/**Can this modpack load? Otherwise it will not load.*/
	public boolean canLoad();
	/**Gets the pack ID*/
	public int getPackID();
}
