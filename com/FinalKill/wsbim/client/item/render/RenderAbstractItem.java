package com.FinalKill.wsbim.client.item.render;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.FinalKill.wsbim.common.block.BlockAdvancedWorkbench;

/**
 * Used for rendering items into the world or other locations.
 * @author Final Kill
 *
 */
public class RenderAbstractItem {

	private ItemRenderer itemRenderer = new ItemRenderer(Minecraft.getMinecraft());

	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	private Random rand = new Random();

	private RenderBlocks renderBlocksRi  = new RenderBlocks();
	private RenderItem renderItem  = new RenderItem();

	private Minecraft mc;
	
	public boolean onlyRenderFancy = false;
	
	public RenderAbstractItem(Minecraft mc){
		this.mc = mc;
			}
	
	/**
	 * Renders an item stack into the world at a given location. Great for tile entities that need to render a stack somewhere.
	 * Please take note that when the game is running in fast graphics and you're trying to render an item, the y variable may need to be tweaked (use if statements to check whether the item stack you're rendering is a block or fancy graphics are on (pretty much 3-D rendering)) and call this method with a 0.1 difference. ex. {@code 
	 *      if(Minecraft.getMinecraft().gameSettings.fancyGraphics || stackInSlot.getItem() instanceof ItemBlock){
				   this.itemRenderer.renderItemStack(0.0, 0.8, 0.0, partialTick, stackInSlot, true);
				}
				else{
					this.itemRenderer.renderItemStack(0.0, 0.7, 0.0, partialTick, stackInSlot, true);
				} }
	 * @param x - the double x 
	 * @param y - the double y
	 * @param z - the double z
	 * @param partialTick - (used in a method (ex. renderTileEntity(TileEntity t, double x , double y, double z, float partialTick)))
	 * @param stack - the stack for rendering - TAKE NOTE - stacks will look better if they are in the max size of one, because stacks may not be rendered in the right location if they are bundled
	 * @param handleTranslation - did you all ready do GL11.glTranslatef(x, y, z) for rendering? other wise, we'll do it for you.
	 * @param bundleStacks - false?? only show one size of the stack, true?? show bundleing.
	 */
	public void renderItemStack(double x, double y, double z, float partialTick, ItemStack stack, boolean handleTranslation, boolean bundleStacks) {
		  ItemStack itemstack = stack;

	      if (itemstack.getItem() != null)
	      {
	          this.bindATexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(stack.getItemSpriteNumber()));
	          TextureUtil.func_152777_a(false, false, 1.0F);
	          this.rand.setSeed(187L);
	          GL11.glPushMatrix();
	          float f2 = 0F;
	          float f3 = 0F;
	          byte b0 = 1;
	          
	         
	          if(bundleStacks){
	          if (stack.stackSize > 1)
	          {
	              b0 = 2;
	          }

	          if (stack.stackSize > 5)
	          {
	              b0 = 3;
	          }

	          if (stack.stackSize > 20)
	          {
	              b0 = 4;
	          }

	          if (stack.stackSize > 40)
	          {
	              b0 = 5;
	          }
	          

	          b0 = getMiniBlockCount(itemstack, b0);
	          }
	         if(handleTranslation) GL11.glTranslatef((float)x, (float)y, (float)z);
	          GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	          float f6;
	          float f7;
	          int k;

	          if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()) )
	          {
	              Block block = Block.getBlockFromItem(itemstack.getItem());
	              GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
	              GL11.glRotatef(180F, 1F, 0F, 1F);
	              
	                  GL11.glScalef(1.25F, 1.25F, 1.25F);
	                 GL11.glTranslatef(0.0F, 0.05F, 0.0F);
	                  GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
	              

	              float f9 = 0.25F;
	              k = block.getRenderType();

	              if (k == 1 || k == 19 || k == 12 || k == 2)
	              {
	                  f9 = 0.5F;
	              }

	              if (block.getRenderBlockPass() > 0)
	              {
	                  GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
	                  GL11.glEnable(GL11.GL_BLEND);
	                  OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	              }

	              GL11.glScalef(f9, f9, f9);

	              for (int l = 0; l < b0; ++l)
	              {
	                  GL11.glPushMatrix();

	                  if (l > 0)
	                  {
	                      f6 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
	                      f7 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
	                      float f8 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
	                      GL11.glTranslatef(f6, f7, f8);
	                  }
	                  if(!(block instanceof BlockAdvancedWorkbench)){
	                  this.renderBlocksRi .renderBlockAsItem(block, itemstack.getItemDamage(), 1.0F);
	                  }
	                  GL11.glPopMatrix();
	              }

	              if (block.getRenderBlockPass() > 0)
	              {
	                  GL11.glDisable(GL11.GL_BLEND);
	              }
	          }
	          else
	          {
	              float f5;

	              if (/*itemstack.getItemSpriteNumber() == 1 &&*/ itemstack.getItem().requiresMultipleRenderPasses())
	              {
	                  
	                      GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
	                      GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	                 

	                  for (int j = 0; j < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++j)
	                  {
	                      rand.setSeed(187L);
	                      IIcon iicon1 = itemstack.getItem().getIcon(itemstack, j);

	                     
	                          k = itemstack.getItem().getColorFromItemStack(itemstack, j);
	                          f5 = (float)(k >> 16 & 255) / 255.0F;
	                          f6 = (float)(k >> 8 & 255) / 255.0F;
	                          f7 = (float)(k & 255) / 255.0F;
	                          GL11.glColor4f(f5, f6, f7, 1.0F);
	                          this.renderDroppedItem(stack, iicon1, b0, partialTick, f5, f6, f7, j);
	                      
	                     
	                  }
	              }
	              else
	              {
	                  if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
	                  {
	                      GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
	                      GL11.glEnable(GL11.GL_BLEND);
	                      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	                  }

	                 
	                      GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
	                      GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	                  //

	                  IIcon iicon = itemstack.getIconIndex();

	                  
	                      int i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
	                      float f4 = (float)(i >> 16 & 255) / 255.0F;
	                      f5 = (float)(i >> 8 & 255) / 255.0F;
	                      f6 = (float)(i & 255) / 255.0F;
	                      this.renderDroppedItem(stack, iicon, b0, partialTick, f4, f5, f6, 0);
	                  
	                

	                  if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
	                  {
	                      GL11.glDisable(GL11.GL_BLEND);
	                  }
	              }
	          }

	          GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	          GL11.glPopMatrix();
	          this.bindATexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(stack.getItemSpriteNumber()));
	TextureUtil.func_147945_b();
	      }
			
		}
	/**
	 * Used for binding textures.
	 * @param resourceLocation - the texture to bind
	 */
	private void bindATexture(ResourceLocation resourceLocation) {
		mc.renderEngine.bindTexture(resourceLocation);
	}

	/**
	 * Just used for block rendering
	 * @param stack
	 * @param original
	 * @return
	 */
	public byte getMiniBlockCount(ItemStack stack, byte original)
	{
	    return original;
	}

	/**
	 * Allows for a subclass to override how many rendered items appear in a
	 * "mini item 3d stack"
	 * @param stack The item stack
	 * @param original The default amount vanilla would use
	 * @return
	 */
	public byte getMiniItemCount(ItemStack stack, byte original)
	{
	    return original;
	}

	private void renderDroppedItem(ItemStack p_77020_1_, IIcon p_77020_2_, int p_77020_3_, float p_77020_4_, float p_77020_5_, float p_77020_6_, float p_77020_7_, int pass)
	{
	    Tessellator tessellator = Tessellator.instance;
	    ItemStack itemstack = p_77020_1_;
        int j = itemstack.stackSize;
        byte b0;

        if (j < 2)
        {
            b0 = 1;
        }
        else if (j < 16)
        {
            b0 = 2;
        }
        else if (j < 32)
        {
            b0 = 3;
        }
        else
        {
            b0 = 4;
        }
	    if (p_77020_2_ == null)
	    {
	        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
	        ResourceLocation resourcelocation = texturemanager.getResourceLocation(p_77020_1_.getItemSpriteNumber());
	        p_77020_2_ = ((TextureMap)texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
	    }

	    float f14 = ((IIcon)p_77020_2_).getMinU();
	    float f15 = ((IIcon)p_77020_2_).getMaxU();
	    float f4 = ((IIcon)p_77020_2_).getMinV();
	    float f5 = ((IIcon)p_77020_2_).getMaxV();
	    float f6 = 1.0F;
	    float f7 = 0.5F;
	    float f8 = 0.25F;
	    float f10;

	    GL11.glRotatef(180F, 1, 0, 1);
	    if (Minecraft.getMinecraft().gameSettings.fancyGraphics || p_77020_1_.getItem() instanceof ItemBlock || this.onlyRenderFancy)
	    {
	        GL11.glPushMatrix();
	          
	       
	        
	      

	        float f9 = 0.0625F;
	        f10 = 0.021875F;
	        /**
	        ItemStack itemstack = p_77020_1_;
	        int j = itemstack.stackSize;
	        byte b0;

	        if (j < 2)
	        {
	            b0 = 1;
	        }
	        else if (j < 16)
	        {
	            b0 = 2;
	        }
	        else if (j < 32)
	        {
	            b0 = 3;
	        }
	        else
	        {
	            b0 = 4;
	        }
*/
	        b0 = getMiniItemCount(itemstack, b0);

	        GL11.glTranslatef(-f7, -f8, -((f9 + f10) * (float)b0 / 2.0F));
	           
	        if((p_77020_1_.getItem() instanceof ItemBlock)){
	        	
	        	   
	 			  
	         }
	        
	        for (int k = 0; k < b0; ++k)
	        {
	             if (k > 0 && this.renderItem.shouldSpreadItems())
	            {
	                float x = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
	                float y = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
	                float z = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
	                GL11.glTranslatef(x, y, f9 + f10);
	            }
	            else
	            {
	                GL11.glTranslatef(0f, 0f, f9 + f10);
	            }

	            if (itemstack.getItemSpriteNumber() == 0)
	            {
	                this.bindATexture(TextureMap.locationBlocksTexture);
	            }
	            else
	            {
	                this.bindATexture(TextureMap.locationItemsTexture);
	            }

	            GL11.glColor4f(p_77020_5_, p_77020_6_, p_77020_7_, 1.0F);
	            ItemRenderer.renderItemIn2D(tessellator, f15, f4, f14, f5, ((IIcon)p_77020_2_).getIconWidth(), ((IIcon)p_77020_2_).getIconHeight(), f9);
	            if (itemstack.hasEffect(pass))
	            {
	                GL11.glDepthFunc(GL11.GL_EQUAL);
	                GL11.glDisable(GL11.GL_LIGHTING);
	                Minecraft.getMinecraft().renderEngine.bindTexture(RES_ITEM_GLINT);
	                GL11.glEnable(GL11.GL_BLEND);
	                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
	                float f11 = 0.76F;
	                GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
	                GL11.glMatrixMode(GL11.GL_TEXTURE);
	                GL11.glPushMatrix();
	                float f12 = 0.125F;
	                GL11.glScalef(f12, f12, f12);
	                float f13 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
	                GL11.glTranslatef(f13, 0.0F, 0.0F);
	                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
	                GL11.glPopMatrix();
	                GL11.glPushMatrix();
	                GL11.glScalef(f12, f12, f12);
	                f13 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	               GL11.glTranslatef(-f13, 0.0F, 0.0F);
	                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
	                GL11.glPopMatrix();
	                GL11.glMatrixMode(GL11.GL_MODELVIEW);
	                GL11.glDisable(GL11.GL_BLEND);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glDepthFunc(GL11.GL_LEQUAL);
	            }
	        }

	        GL11.glPopMatrix();
	    }
	    else
	    {
	        if(b0 > 1){
	        	for(int k = 0; k < b0; k++){
	        		 // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
	        	      float f9 = 0.0625F;
	        	      float f69 = 0.021875F;
		           
	        	      if (k > 0 && this.renderItem.shouldSpreadItems())
		            {
		                float x = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
		                float y = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
		                float z = (rand.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
		                GL11.glTranslatef(x, y, f9 + f69);
		            }
		            
		            {
		                GL11.glTranslatef(0f, 0f, f9 + f69);
		            }

	            GL11.glPushMatrix();

	            GL11.glTranslated(0.5, 0.0,0);
				
	            GL11.glTranslatef(-f7, -f8, -((f9 + f69) * (float)b0 / 2.0F)/* + (b0>2?-0.1F:0F)*/);
	               GL11.glRotatef((180.0F - RenderManager.instance.playerViewY), 0.0F, 1.0F, 0.0F);
	             
	              	            GL11.glColor4f(p_77020_5_, p_77020_6_, p_77020_7_, 1.0F);
	            tessellator.startDrawingQuads();
	            tessellator.setNormal(0.0F, 1.0F, 0.0F);
	            tessellator.addVertexWithUV((double)(0.0F - f7), (double)(0.0F - f8), 0.0D, (double)f14, (double)f5);
	            tessellator.addVertexWithUV((double)(f6 - f7), (double)(0.0F - f8), 0.0D, (double)f15, (double)f5);
	            tessellator.addVertexWithUV((double)(f6 - f7), (double)(1.0F - f8), 0.0D, (double)f15, (double)f4);
	            tessellator.addVertexWithUV((double)(0.0F - f7), (double)(1.0F - f8), 0.0D, (double)f14, (double)f4);
	            tessellator.draw();
	            GL11.glPopMatrix();
	            
	        	
	        }
	        }
	        else{
	        	  float f9 = 0.0625F;
        	      float f69 = 0.021875F;
	           
        	     
	            
	            {
	                GL11.glTranslatef(0f, 0f, f9 + f69);
	            }

            GL11.glPushMatrix();

            GL11.glTranslated(0.5, 0.0,0);
			
            GL11.glTranslatef(-f7, -f8, -((f9 + f69) * (float)b0 / 2.0F)/* + (b0>2?-0.1F:0F)*/);
               GL11.glRotatef((180.0F - RenderManager.instance.playerViewY), 0.0F, 1.0F, 0.0F);
             
              	            GL11.glColor4f(p_77020_5_, p_77020_6_, p_77020_7_, 1.0F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            tessellator.addVertexWithUV((double)(0.0F - f7), (double)(0.0F - f8), 0.0D, (double)f14, (double)f5);
            tessellator.addVertexWithUV((double)(f6 - f7), (double)(0.0F - f8), 0.0D, (double)f15, (double)f5);
            tessellator.addVertexWithUV((double)(f6 - f7), (double)(1.0F - f8), 0.0D, (double)f15, (double)f4);
            tessellator.addVertexWithUV((double)(0.0F - f7), (double)(1.0F - f8), 0.0D, (double)f14, (double)f4);
            tessellator.draw();
            GL11.glPopMatrix();
	        }
	    }
	     
	   
	}
	
}
