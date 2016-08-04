package com.FinalKill.wsbim.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFreezer extends ModelBase
{
  //fields
    ModelRenderer Bottom;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Top;
  public static ModelRenderer Door;
  public static ModelRenderer Handle;
    ModelRenderer Shelf1;
    ModelRenderer Shelf2;
  
  public ModelFreezer()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Bottom = new ModelRenderer(this, 0, 0);
      Bottom.addBox(0F, 0F, 0F, 14, 1, 12);
      Bottom.setRotationPoint(-7F, 23F, -6F);
      Bottom.setTextureSize(128, 128);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 16);
      Shape1.addBox(0F, 0F, 0F, 1, 11, 12);
      Shape1.setRotationPoint(6F, 12F, -6F);
      Shape1.setTextureSize(128, 128);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 47);
      Shape2.addBox(0F, 0F, 0F, 13, 11, 1);
      Shape2.setRotationPoint(-7F, 12F, 5F);
      Shape2.setTextureSize(128, 128);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 67);
      Shape3.addBox(0F, 0F, 0F, 13, 11, 1);
      Shape3.setRotationPoint(-7F, 12F, -6F);
      Shape3.setTextureSize(128, 128);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 88);
      Top.addBox(0F, 0F, 0F, 13, 1, 10);
      Top.setRotationPoint(-7F, 12F, -5F);
      Top.setTextureSize(128, 128);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      Door = new ModelRenderer(this, 0, 103);
      Door.addBox(0F, 0F, 0F, 1, 10, 10);
      Door.setRotationPoint(-7F, 13F, -5F);
      Door.setTextureSize(128, 128);
      Door.mirror = true;
      setRotation(Door, 0F, 0F, 0F);
      Handle = new ModelRenderer(this, 58, 0);
      Handle.addBox(0F, 0F, 0F, 1, 3, 1);
      Handle.setRotationPoint(-8F, 16F, 3F);
      Handle.setTextureSize(128, 128);
      Handle.mirror = true;
      setRotation(Handle, 0F, 0F, 0F);
      Shelf1 = new ModelRenderer(this, 0, 88);
      Shelf1.addBox(0F, 0F, 0F, 11, 1, 10);
      Shelf1.setRotationPoint(-5F, 15F, -5F);
      Shelf1.setTextureSize(128, 128);
      Shelf1.mirror = true;
      setRotation(Shelf1, 0F, 0F, 0F);
      Shelf2 = new ModelRenderer(this, 0, 88);
      Shelf2.addBox(0F, 0F, 0F, 11, 1, 10);
      Shelf2.setRotationPoint(-5F, 19F, -5F);
      Shelf2.setTextureSize(128, 128);
      Shelf2.mirror = true;
      setRotation(Shelf2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Top.render(f5);
    Door.render(f5);
    Handle.render(f5);
    Shelf1.render(f5);
    Shelf2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

public void renderAll(float f5) {
      Bottom.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Top.render(f5);
    Door.render(f5);
    Handle.render(f5);
    Shelf1.render(f5);
    Shelf2.render(f5);
}

}
