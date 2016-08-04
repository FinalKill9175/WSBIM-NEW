package com.FinalKill.Machinery_Craft.tile.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.FinalKill.MachineryCraftAPI.power.IConnectableBlock;

public class TileEntityRenderCable extends TileEntitySpecialRenderer {
	
	private static ResourceLocation texture;

	float pixel = 1F/16F;
	
	float texturePixel = 1F/32F;
	
	boolean drawInside = true;
	
	public TileEntityRenderCable(ResourceLocation tex){
		texture = tex;
		
		
	}
	
	
	/**
	 * @Youtube:
	 * https://www.youtube.com/watch?v=-bqP-UmZXjo
	 * *Did decide to add api compatibility;
	 */
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f) {
	
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(texture);
		{
			if(tileentity instanceof IConnectableBlock){

			IConnectableBlock cable = (IConnectableBlock)tileentity;
			
			ForgeDirection[] connections = cable.getConnections();
			
		if(!cable.onlyOneOpposite(cable.getConnections())){
			drawCore(tileentity);
			for(int i=0; i<cable.getConnections().length; i++){
				
				if(connections[i] !=null){
					
					
					drawConnector(connections[i]);
					
				}
				
				
			}
		}
		else{
			
			/**
			for(int i=0; i<cable.connections.length; i++){
				
				if(cable.connections[i] !=null){
					
				//	drawStraight(cable.connections[i]);
					break;
					
				}
				
				
			}
			*/
			
			this.drawStraight();
			
		}
		
		
		for(int i=0; i<connections.length; i++){
			
			if(connections[i] !=null){
				
				
				drawConnector(connections[i]);
				
			}
			
			
		}
		
			}
		
		}
		drawInside = true;
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
		
	}

	public void drawStraight(){
		
	
		
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		{	
			
			
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2,1- 11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);

			t.addVertexWithUV(11*pixel/2, 11*pixel/2,  11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 10*texturePixel, 5*texturePixel);

			t.addVertexWithUV(11*pixel/2, 11*pixel/2,1- 11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 10*texturePixel, 5*texturePixel);

			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1- 11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);

			
			t.addVertexWithUV(11*pixel/2, 11*pixel/2,  1-11*pixel/2, 15*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 15*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2,10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2,10*texturePixel, 5*texturePixel);

			
			if(drawInside){
			
				
			}
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
			GL11.glTranslatef(-0.5F,- 0.5F, -0.5F);
			
		}
		t.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
		GL11.glTranslatef(-0.5F,- 0.5F, -0.5F);
	}
	
	public void drawConnector(ForgeDirection direction){
	
		
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		{
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1,1- 11*pixel/2, 10*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);			
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);			
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			
			if(drawInside){
				t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				t.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				t.addVertexWithUV(1-11*pixel/2, 1,1- 11*pixel/2, 10*texturePixel, 5*texturePixel);
				
				t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
				t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				t.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);			
				t.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				
				t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				t.addVertexWithUV(1-11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				t.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				t.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				
				t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				t.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);			
				t.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				
				t.addVertexWithUV(11*pixel/2,1- 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				
				
			}
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if(direction.equals(ForgeDirection.UP)){
				//ROTATE
				
				
			}
			else if(direction.equals(ForgeDirection.DOWN)){
				GL11.glRotatef(180, 1, 0, 0);
				
			}
			else if(direction.equals(ForgeDirection.NORTH)){
				GL11.glRotatef(270, 1, 0, 0);
				
			}
			else if(direction.equals(ForgeDirection.EAST)){
				GL11.glRotatef(270, 0, 0, 1);

			}
			else if(direction.equals(ForgeDirection.SOUTH)){
				GL11.glRotatef(90, 1, 0, 0);
				
			}
			else if(direction.equals(ForgeDirection.WEST)){
				GL11.glRotatef(90, 0, 0, 1);

			}
			GL11.glTranslatef(-0.5F,- 0.5F, -0.5F);
			
		}
		t.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.UP)){
			//ROTATE
			
			
		}
		else if(direction.equals(ForgeDirection.DOWN)){
			GL11.glRotatef(-180, 1, 0, 0);
			
		}
		else if(direction.equals(ForgeDirection.NORTH)){
			GL11.glRotatef(-270, 1, 0, 0);
			
		}
		else if(direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(-270, 0, 0, 1);

		}
		else if(direction.equals(ForgeDirection.SOUTH)){
			GL11.glRotatef(-90, 1, 0, 0);
			
		}
		else if(direction.equals(ForgeDirection.WEST)){
			GL11.glRotatef(-90, 0, 0, 1);

		}
		GL11.glTranslatef(-0.5F,- 0.5F, -0.5F);
	}
	
	public void drawCore(TileEntity tileentity){
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		{
	
			
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
		
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);

		t.addVertexWithUV(11*pixel/2, 11*pixel/2,  11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);

		t.addVertexWithUV(11*pixel/2, 11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);

		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);

		
		t.addVertexWithUV(11*pixel/2, 11*pixel/2,  1-11*pixel/2, 5*texturePixel, 5*texturePixel);
		t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
		t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);

		if(this.drawInside){
			
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);			
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 0*texturePixel);		
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2,11*pixel/2, 5*texturePixel, 0*texturePixel);		
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);		
			t.addVertexWithUV(11*pixel/2, 11*pixel/2,  11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2,1- 11*pixel/2, 5*texturePixel, 0*texturePixel);		
			t.addVertexWithUV(11*pixel/2, 11*pixel/2,1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1- 11*pixel/2, 5*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			t.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			t.addVertexWithUV(11*pixel/2, 11*pixel/2,  1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			
			
			
			
			
		}
		
		
		}
		t.draw();
	}
	
}
