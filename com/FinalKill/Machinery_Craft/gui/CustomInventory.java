package com.FinalKill.Machinery_Craft.gui;

public class CustomInventory {
	private static int length_to_player_inv;
	private static int length_to_hotbar;
	private static int height_to_player_inv;
	private static int height_to_hotbar;
	private static int length_to_custom_inv;
	private static int height_to_custom_inv;
	private static int rows;
	private static int colums;
	
	public CustomInventory(int length_to_player_inv, int length_to_hotbar, int height_to_player_inv, int height_to_hotbar, int length_to_custom_inv, int height_to_custom_inv, int rows, int colums){
		this.length_to_player_inv = length_to_player_inv;
		this.length_to_hotbar = length_to_hotbar;
		this.height_to_player_inv = height_to_player_inv;
		this.height_to_hotbar = height_to_hotbar;
		this.length_to_custom_inv = length_to_custom_inv;
		this.height_to_custom_inv = height_to_custom_inv;
		this.rows = rows;
		this.colums = colums;
	}

	public static int getLengthToPlayerInv() {
		return length_to_player_inv;
	}

	public static void setLengthToPlayerInv(int length_to_player_inv) {
		CustomInventory.length_to_player_inv = length_to_player_inv;
	}

	public static int getLengthToHotbar() {
		return length_to_hotbar;
	}

	public static void setLengthToHotbar(int length_to_hotbar) {
		CustomInventory.length_to_hotbar = length_to_hotbar;
	}

	public static int getHeightToPlayerInv() {
		return height_to_player_inv;
	}

	public static void setHeightToPlayerInv(int height_to_player_inv) {
		CustomInventory.height_to_player_inv = height_to_player_inv;
	}

	public static int getHeightToHotbar() {
		return height_to_hotbar;
	}

	public static void setHeightToHotbar(int height_to_hotbar) {
		CustomInventory.height_to_hotbar = height_to_hotbar;
	}

	public static int getLengthToCustomInv() {
		return length_to_custom_inv;
	}

	public static void setLengthToCustomInv(int length_to_custom_inv) {
		CustomInventory.length_to_custom_inv = length_to_custom_inv;
	}

	public static int getHeightToCustomInv() {
		return height_to_custom_inv;
	}

	public static void setHeightToCustomInv(int height_to_custom_inv) {
		CustomInventory.height_to_custom_inv = height_to_custom_inv;
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		CustomInventory.rows = rows;
	}

	public static int getColums() {
		return colums;
	}

	public static void setColums(int colums) {
		CustomInventory.colums = colums;
	}

}
