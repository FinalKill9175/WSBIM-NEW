package com.FinalKill.wsbim.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import org.lwjgl.opengl.GL11;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.inventory.ContainerBackpack;
import com.FinalKill.wsbim.common.item.ItemBackpack;
import com.FinalKill.wsbim.util.EnumBackpackSize;

public class GuiBackpack extends GuiContainer{
	
	public ItemStack backpackStack;
	public ItemBackpack backpack;

	private EntityPlayer playerObj;
	
	private GuiButton renameButton;

	
	
	public GuiBackpack(ItemStack backpack, EntityPlayer player) {
		super(new ContainerBackpack(backpack, player));
		this.backpackStack = backpack;
		this.backpack = (ItemBackpack) backpack.getItem();
		this.playerObj = player;
		this.xSize = this.backpack.backpackSize.inv_width;
		this.ySize = (18 + (this.backpack.backpackSize.rows * 18)) + 95 + (WSBIM.options.useAdvancedResourcePackSupport && (Minecraft.getMinecraft().gameSettings.resourcePacks.size() > 0) && this.xSize == 176 ? 0 : 0);
		
	}
	
	public void initGui(){
		super.initGui();
		this.renameButton = new GuiButton(100, guiLeft, guiTop+this.ySize, Minecraft.getMinecraft().fontRenderer.getStringWidth("Rename Backpack") + 5, Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, "Rename Backpack");
		this.buttonList.add(renameButton);
		
		
		
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
		//ItemStack backpack = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] !=null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2].getItem() instanceof ItemBackpack? Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] : Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
		//if(backpack == null || (backpack !=null && !(backpack.getItem() instanceof ItemBackpack))){
			//mc.displayGuiScreen(null);
		//}
		
		if(WSBIM.options.useAdvancedResourcePackSupport && (mc.gameSettings.resourcePacks.size() > 0) && this.xSize == 176){
			//Will then render the gui from vanilla resource locations.
			if(this.backpack.backpackSize == EnumBackpackSize.DEFAULT){
				ResourceLocation tex = new ResourceLocation("textures/gui/container/generic_54.png");
				
				GL11.glPushMatrix();
				GL11.glColor4f(1, 1, 1, 1);
				mc.renderEngine.bindTexture(tex);
				this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 71);
				this.drawTexturedModalRect(guiLeft, guiTop+71, 0, 126, 176, 96);
				GL11.glPopMatrix();
			}
			
			else if(this.backpack.backpackSize == EnumBackpackSize.SMALL){
				ResourceLocation tex = new ResourceLocation("textures/gui/container/generic_54.png");
				
				GL11.glPushMatrix();
				GL11.glColor4f(1, 1, 1, 1);
				mc.renderEngine.bindTexture(tex);
				this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 71-18);
				this.drawTexturedModalRect(guiLeft, guiTop+71-18, 0, 126, 176, 96);
				GL11.glPopMatrix();
			}
			
			else if(this.backpack.backpackSize == EnumBackpackSize.MEDIUM){
				ResourceLocation tex = new ResourceLocation("textures/gui/container/generic_54.png");
				
				GL11.glPushMatrix();
				GL11.glColor4f(1, 1, 1, 1);
				mc.renderEngine.bindTexture(tex);
				this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 71+18);
				this.drawTexturedModalRect(guiLeft, guiTop+71+18, 0, 126, 176, 96);
				GL11.glPopMatrix();
			}
			
			else if(this.backpack.backpackSize == EnumBackpackSize.LARGE){
				ResourceLocation tex = new ResourceLocation("textures/gui/container/generic_54.png");
				
				GL11.glPushMatrix();
				GL11.glColor4f(1, 1, 1, 1);
				mc.renderEngine.bindTexture(tex);
				this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 125);
				this.drawTexturedModalRect(guiLeft, guiTop+17+(6*18), 0, 126, 176, 96);
				GL11.glPopMatrix();
			}
			else{
				GL11.glPushMatrix();
				GL11.glColor4f(1,1,1,1);
				mc.renderEngine.bindTexture(this.backpack.backpackSize.getGuiTexture());
				this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
				GL11.glPopMatrix();
			}
		}
		else{
			GL11.glPushMatrix();
			GL11.glColor4f(1,1,1,1);
			mc.renderEngine.bindTexture(this.backpack.backpackSize.getGuiTexture());
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			GL11.glPopMatrix();
		}
	}
	
	public void actionPerformed(GuiButton b){
		super.actionPerformed(b);
		if(b.id == 100){
			//TODO rename GUI
			Minecraft.getMinecraft().displayGuiScreen(null);
			//TODO do not use gui mc object, use Minecraft.getMinecraft();
			EntityPlayer player = this.getServerPlayerObject(Minecraft.getMinecraft());
			ItemStack backpack = player.inventory.armorInventory[2] !=null && player.inventory.armorInventory[2].getItem() instanceof ItemBackpack? player.inventory.armorInventory[2] : player.getCurrentEquippedItem();
			if(backpack !=null && backpack.getItem() instanceof ItemBackpack){
				Minecraft.getMinecraft().displayGuiScreen(new GuiRenameBackpack(backpack, ((ItemBackpack)backpack.getItem())));
			}
		}
	}

	public void onGuiClosed(){
		super.onGuiClosed();
		this.getServerPlayerObject(Minecraft.getMinecraft()).openContainer.onContainerClosed(this.getServerPlayerObject(Minecraft.getMinecraft()));
	}
	protected EntityPlayer getServerPlayerObject(Minecraft mc){
		//return this.getServerWorldObj(mc).getPlayerEntityByName(mc.thePlayer.getDisplayName());
		return playerObj;
	}
	
public World getServerWorldObj(Minecraft mc){
		
		World world = null;
		/**
		if(MinecraftServer.getServer() !=null && MinecraftServer.getServer().worldServers !=null){
		WorldServer[] list = MinecraftServer.getServer().worldServers;
		for(WorldServer ins : list){
		if(ins.provider.dimensionId==mc.thePlayer.dimension)
			world = ins;
		}
		if(world==null && list.length > 0)
			world = list[0];
		}
		else if(mc.isIntegratedServerRunning()){
			WorldServer[] list = mc.getIntegratedServer().worldServers;
			for(WorldServer ins : list){
				if(ins.provider.dimensionId==mc.thePlayer.dimension)
					world = ins;
				}
				if(world==null && list.length > 0)
					world = list[0];
		}
		*/
		//else{
			world = mc.theWorld;
		//}
		
		return world;
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(this.backpackStack.getDisplayName(), 8, 6, 4210752);
		
		 this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 3, 4210752);

	}
	
}
