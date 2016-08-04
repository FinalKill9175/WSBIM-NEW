package com.FinalKill.Machinery_Craft.tile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FinalKill.Machinery_Craft.MachineryCraft;
import com.FinalKill.Machinery_Craft.blocks.BlockMacerator;
import com.FinalKill.Machinery_Craft.model.ModelMacerator;
import com.FinalKill.Machinery_Craft.tile.TileEntityMacerator;

public class TileEntityRenderMacerator extends TileEntitySpecialRenderer {
	public static final ResourceLocation texture = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/model/macerator.png");
	public static final ResourceLocation texture_active = new ResourceLocation(MachineryCraft.MODID.toLowerCase(), "textures/model/macerator_active.png");
	private static ResourceLocation img;
	
	public ModelMacerator model = new ModelMacerator();
	private float turn = 0.0F;
	private float lastTurn = 0.0F;
	private float turn2;

		public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
			int var9 = 0;
			img = texture;
		   //	TileEntityMacerator macerator = null;
		        if (tileentity.hasWorldObj())
		        {
		            var9 = tileentity.getBlockMetadata();
		      //      macerator = (TileEntityMacerator) Minecraft.getMinecraft().theWorld.getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
		            //  macerator.turn = turn;
		         //   macerator.lastTurn = lastTurn;
		          //  macerator.rotateAngle = model.Blade1.rotateAngleY;
		        //    turn = macerator.turn;
		          //  lastTurn = macerator.lastTurn;
		          // model.Blade1.rotateAngleY = macerator.rotateAngle;
		            
		        }
		        
		        
				 BlockMacerator furnaceBlock = null;
				 if(x !=0 && y !=-0.1 && z!=0){
				 TileEntityMacerator furnace = (TileEntityMacerator) Minecraft.getMinecraft().theWorld.getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);

				furnaceBlock = (BlockMacerator) Minecraft.getMinecraft().theWorld.getBlock(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
			}
		        
				    
			        if(x !=0.0 && y!=-0.1 && z !=0.0){
			            if(furnaceBlock.equals(MachineryCraft.blocks.macerator_active)){
			            	/**
					       turn +=0.0001F;
					        if(turn >=0.23F) turn = 0.23F;
					        	
					       	model.Blade1.rotateAngleY +=turn;
					        	System.out.println(turn);
					       	lastTurn = turn;
					        }
			            	model.Blade1.rotateAngleY +=0.23F;
					       */
			          	  img = texture_active;
			          	  
			            }
			            else{
			            	
			          	  img =texture;
			          	  /**
			          	  turn2 +=-0.0001F;
					      if(turn2<0)turn2 = 0.0F; 
			        	model.Blade1.rotateAngleY -= turn2;
			        	*/
			            	//model.Blade1.rotateAngleY = 0.0F;
			            }
			        }
		        if(x !=0.0){
		         }
		                short var10 = 180;
		        float var11= -1F;
		        float var12= 0F;
		        float var13= 1F;

		        if (var9 == 2)
		        {
		            var10 = 180;
		            var11 = 0F;
		            var12 = 0F;
		            var13 = 1F;
		        }

		        if (var9 == 3)
		        {
		            var10 = 180;
		            var11 = 1F;
		            var12 = 0F;
		            var13 = 0F;
		        }

		        if (var9 == 4)
		        {
		            var10 = 180;
		            var11 = 1F;
		            var12 = 0F;
		            var13 = 1F;
		        }

		        if (var9 == 5)
		        {
		            var10 = 90*2;
		            var11= -1F;
		            var12= 0F;
		            var13= 1F;
		        }
		   
		       model.Blade1.isHidden = true;
		        model.BladeAxis.isHidden = true;
		        
		     //   macerator.rotateAngle = model.Blade1.rotateAngleY;
		             //model.Blade1.rotateAngleY = macerator.rotateAngle;
	/**
		        if(x !=0.0 && tileentity.getWorldObj().getBlock(macerator.xCoord, macerator.yCoord, macerator.zCoord).equals(MachineryCraft.blocks.macerator_active)){
		        	
		        turn +=0.0001F;
		        if(turn >=0.23F) turn = 0.23F;
		        	
		        	model.Blade1.rotateAngleY +=turn;
		        	//System.out.println(turn);
		        	lastTurn = turn;
		        }
		       
		        else if(x !=0.0) {
		        	   turn +=-0.0001F;
				       if(turn <0)turn = 0.0F; 
		        	model.Blade1.rotateAngleY -= turn;
		        }
		        */
		    
		        
		   	  GL11.glPushMatrix();
		         GL11.glTranslatef((float)x + 0.5F, (float)y+ 1.5F,(float) z+ 0.5F);
		      GL11.glRotatef((float)var10, var11, var12,var13);
		      
		       //  GL11.glRotatef(180,0,0,1);
		        
		         this.bindTexture(img);
		         GL11.glPushMatrix();
		   
		        model.renderModel(0.0625F);
			     
		        
		         GL11.glPopMatrix();
		         GL11.glPopMatrix();

	}

}
