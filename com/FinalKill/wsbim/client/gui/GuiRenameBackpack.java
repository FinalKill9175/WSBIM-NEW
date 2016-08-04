package com.FinalKill.wsbim.client.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import com.FinalKill.wsbim.common.NBTTagStringModded;
import com.FinalKill.wsbim.common.item.ItemBackpack;

public class GuiRenameBackpack extends GuiScreen {
	
	private GuiButton doneButton;
	private GuiTextField renameTextBox;
	private GuiButton cancelButton;
	
	private ItemStack backpackStack;
	private ItemBackpack backpackItem;
	
	public GuiRenameBackpack(ItemStack backpackStack, ItemBackpack backpackItem){
		this.backpackStack = backpackStack;
		this.backpackItem = backpackItem;
	}
	
	public void mouseClicked(int i, int j, int k){
		super.mouseClicked(i, j, k);
		this.renameTextBox.mouseClicked(i, j, k);
	}
	
	
	public void drawScreen(int mx, int my, float part){
		this.drawDefaultBackground();
		super.drawScreen(mx, my, part);
		if(this.renameTextBox !=null){
			this.renameTextBox.drawTextBox();
		}
		
			
		this.drawString(Minecraft.getMinecraft().fontRenderer, "Rename Backpack", this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth("Rename Backpack") / 2), this.height / 2 - 100  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		if(doneButton.enabled){
			this.drawString(Minecraft.getMinecraft().fontRenderer, EnumChatFormatting.GOLD+"*You may have to re-open a gui a few times or hover to see changes to the name.", this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth("*You may have to re-open a gui a few times or hover to see changes to the name.") / 2), this.height / 2 - 100 + 48 + 12  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		}
		this.drawString(Minecraft.getMinecraft().fontRenderer, "+ Backpack", this.width / 2 + 105, this.height / 2 - 76 + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT / 2), 16777215);

	}
	
	public void keyTyped(char c, int key){
		super.keyTyped(c, key);
		this.renameTextBox.textboxKeyTyped(c, key);
	CharSequence ch = "Backpack";
		
		this.doneButton.enabled = this.renameTextBox.getText() !=null  && !(this.renameTextBox.getText().equals(""))&& this.renameTextBox.getText().length() < 21 && !(this.backpackStack.getDisplayName().split(" Backpack")[0].equals(this.renameTextBox.getText())) && !this.renameTextBox.getText().contains(ch);
		
		if(this.renameTextBox.isFocused() && this.doneButton.enabled){
			if(key == Keyboard.KEY_RETURN){
				this.actionPerformed(doneButton);
			}
		}
	}
	
	public void initGui(){
		super.initGui();
		this.renameTextBox = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.width / 2 - 100, this.height / 2 - 76, 200, 20);
		this.doneButton = new GuiButton(0,this.width / 2 - 100, this.height / 2 - 76 + 48,I18n.format("gui.done", new Object[0]));
		this.cancelButton = new GuiButton(1,this.width / 2 - 100, this.height / 2,I18n.format("gui.cancel", new Object[0]));
		this.buttonList.add(doneButton);
		this.buttonList.add(cancelButton);
		this.doneButton.enabled = false;
		
		this.renameTextBox.setText(this.backpackStack.getDisplayName().split(" Backpack")[0]);
	}
	
	public void renameBackpack(ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.stackTagCompound = new NBTTagCompound();
		}
		if(stack.stackTagCompound.hasKey("display")){
			stack.stackTagCompound.removeTag("display");
		}
		if(stack.stackTagCompound.hasKey("RepairCost")){
			stack.stackTagCompound.removeTag("RepairCost");
		}

		stack.setStackDisplayName(this.renameTextBox.getText()+" Backpack");
	}
	
	public void actionPerformed(GuiButton button){
		if(button.id == 0){
			this.renameBackpack(backpackStack);
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if(button.id == 1){
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
}
