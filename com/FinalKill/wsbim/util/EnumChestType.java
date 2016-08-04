package com.FinalKill.wsbim.util;

import com.FinalKill.wsbim.WSBIM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;


public enum EnumChestType {
//TODO xSize, ySize, inventory stuff
	IRON(0, Blocks.iron_block,5, 9, 45, 176, 204, 8,18,8,122,EnumChestTexture.IRON),
	GOLD(1, Blocks.gold_block, 6, 10, 66, 194, 222, 8, 18, 17, 140, EnumChestTexture.GOLD),
	DIAMOND(2, Blocks.diamond_block, 7, 12, 84, 230, 241, 8, 18, 35, 159, EnumChestTexture.DIAMOND),
	OBSIDIAN(3, Blocks.obsidian, 5, 9, 45, 176, 204, 8, 18, 8, 122, EnumChestTexture.OBSIDIAN),
	MIXEDMETAL(4, WSBIM.main.blockMixedMetalBlock, 8, 13,8 * 13, 256, 256, 12, 18, 48, 176, EnumChestTexture.MIXEDMETAL),
	EMERALD(5, Blocks.emerald_block, 6, 10, 66, 194, 222, 8, 18, 17, 140, EnumChestTexture.EMERALD),
	COPPER(6, null, 4, 11, 44, 194, 186, 8, 18, 17, 104, EnumChestTexture.COPPER);
	
	private int id;
	private Block mat;
	private int rows;
	private int columns;
	private int size_inv;
	private int xSize;
	private int ySize;
	private int inv_start_x;
	private int inv_start_y;
	private int player_inv_start_x;
	private int player_inv_start_y;
	private EnumChestTexture texture;
	
	private EnumChestType(int id, Block materialBlock, int rows, int columns, int size_inv, int xSize, int ySize, int inv_start_x, int inv_start_y, int player_inv_start_x, int player_inv_start_y, EnumChestTexture texture){
		this.id = id;
		mat = materialBlock;
		this.rows = rows;
		this.columns = columns;
		this.size_inv = size_inv;
		this.xSize = xSize;
		this.ySize = ySize;
		this.inv_start_x = inv_start_x;
		this.inv_start_y = inv_start_y;
		this.player_inv_start_x = player_inv_start_x;
		this.player_inv_start_y = player_inv_start_y;
		this.texture = texture;
	}
	public int getID(){
		return id;
	}
	
	public Block getMaterialBlock(){
		return mat;
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	public int getInvSize() {
		return size_inv;
	}
	public int getxSize() {
		return xSize;
	}
	public int getySize() {
		return ySize;
	}
	public int getInv_start_x() {
		return inv_start_x;
	}
	public int getInv_start_y() {
		return inv_start_y;
	}
	public int getPlayer_inv_start_x() {
		return player_inv_start_x;
	}
	public int getPlayer_inv_start_y() {
		return player_inv_start_y;
	}
	public EnumChestTexture getTexture() {
		return texture;
	}
	
}
