package ere_geologique.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import ere_geologique.common.entity.Ankylosaurus;
import ere_geologique.common.entity.Dinosaure;

public class ModelAnkylosaurus extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer Mouth;
    ModelRenderer Head_Block;
    ModelRenderer HeadHorn1;
    ModelRenderer HeadHorn2;
    ModelRenderer HeadHorn3;
    ModelRenderer HeadHorn4;
    ModelRenderer Body;
    ModelRenderer BodySpikes;
    ModelRenderer Neck;
    ModelRenderer Tail1;
    ModelRenderer Tail3;
    ModelRenderer Tail2;
    ModelRenderer Front_ThighLeft;
    ModelRenderer Front_ThighRight;
    ModelRenderer Back_ThighLeft;
    ModelRenderer Back_ThighRight;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    
    public ModelRenderer Tail[];
  
  public ModelAnkylosaurus()
  {
    textureWidth = 128;
    textureHeight = 64;
    float yoffset = 3.0F;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(0F, 0F + yoffset, 0F, 8, 7, 8);
      Head.setRotationPoint(-4F, 7.5F, -9F);
      Head.setTextureSize(128, 64);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Mouth = new ModelRenderer(this, 0, 15);
      Mouth.addBox(0F, 0F + yoffset, 0F, 4, 6, 4);
      Mouth.setRotationPoint(-2F, 10.5F, -13.5F);
      Mouth.setTextureSize(128, 64);
      Mouth.mirror = true;
      setRotation(Mouth, 0.4363323F, 0F, 0F);
      Head_Block = new ModelRenderer(this, 24, 0);
      Head_Block.addBox(0F, 0F + yoffset, 0F, 6, 2, 5);
      Head_Block.setRotationPoint(-3F, 7.5F, -6.5F);
      Head_Block.setTextureSize(128, 64);
      Head_Block.mirror = true;
      setRotation(Head_Block, 0.1745329F, 0F, 0F);
      HeadHorn1 = new ModelRenderer(this, 32, 7);
      HeadHorn1.addBox(0F, 0F + yoffset, 0F, 3, 3, 3);
      HeadHorn1.setRotationPoint(3F, 7.2F, -4.5F);
      HeadHorn1.setTextureSize(128, 64);
      HeadHorn1.mirror = true;
      setRotation(HeadHorn1, 0.1745329F, 0F, 0F);
      HeadHorn2 = new ModelRenderer(this, 44, 7);
      HeadHorn2.addBox(0F, 0F + yoffset, 0F, 3, 3, 3);
      HeadHorn2.setRotationPoint(-6F, 7.2F, -4.5F);
      HeadHorn2.setTextureSize(128, 64);
      HeadHorn2.mirror = true;
      setRotation(HeadHorn2, 0.1745329F, 0F, 0F);
      HeadHorn3 = new ModelRenderer(this, 32, 7);
      HeadHorn3.addBox(0F, 0F + yoffset, 0F, 3, 3, 3);
      HeadHorn3.setRotationPoint(3F, 11.5F, -4F);
      HeadHorn3.setTextureSize(128, 64);
      HeadHorn3.mirror = true;
      setRotation(HeadHorn3, 0F, 0F, 0.1745329F);
      HeadHorn4 = new ModelRenderer(this, 44, 7);
      HeadHorn4.addBox(0F, 0F + yoffset, 0F, 3, 3, 3);
      HeadHorn4.setRotationPoint(-6F, 12F, -4F);
      HeadHorn4.setTextureSize(128, 64);
      HeadHorn4.mirror = true;
      setRotation(HeadHorn4, 0F, 0F, -0.1745329F);
      Body = new ModelRenderer(this, 68, 0);
      Body.addBox(0F, 0F + yoffset, 0F, 14, 10, 16);
      Body.setRotationPoint(-7F, 6F, 0F);
      Body.setTextureSize(128, 64);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      BodySpikes = new ModelRenderer(this, 56, 26);
      BodySpikes.addBox(0F, 0F + yoffset, 0F, 18, 2, 18);
      BodySpikes.setRotationPoint(-9F, 10F, -1F);
      BodySpikes.setTextureSize(128, 64);
      BodySpikes.mirror = true;
      setRotation(BodySpikes, 0F, 0F, 0F);
      Neck = new ModelRenderer(this, 46, 0);
      Neck.addBox(0F, 0F + yoffset, 0F, 4, 5, 2);
      Neck.setRotationPoint(-2F, 8F, -1F);
      Neck.setTextureSize(128, 64);
      Neck.mirror = true;
      setRotation(Neck, 0F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 104, 46);
      Tail1.addBox(-3F, 0F + yoffset, 0F, 6, 6, 6);
      Tail1.setRotationPoint(0F, 7F, 15F);
      Tail1.setTextureSize(128, 64);
      Tail1.mirror = true;
      setRotation(Tail1, -0.2617994F, 0F, 0F);
      
      Tail3 = new ModelRenderer(this, 80, 46);
      Tail3.addBox(-3F, -3F, 0F, 6, 6, 6);
      Tail3.setRotationPoint(0F, 2F, 7F);
      Tail3.setTextureSize(128, 64);
      Tail3.mirror = true;
      setRotation(Tail3, 0F, 0F, 0F);
      
      Tail2 = new ModelRenderer(this, 56, 46);
      Tail2.addBox(-2F, 0F, 0F, 4, 4, 8);
      Tail2.setRotationPoint(0F, 4F, 5F);
      Tail2.setTextureSize(128, 64);
      Tail2.mirror = true;
      setRotation(Tail2, 0F, 0F, 0F);
      
      Tail1.addChild(Tail2);
      Tail2.addChild(Tail3);
      
      Front_ThighRight = new ModelRenderer(this, 18, 24);
      Front_ThighRight.addBox(0F, 0F + yoffset, 0F, 4, 4, 4);
      Front_ThighRight.setRotationPoint(5F, 14F, 1F);
      Front_ThighRight.setTextureSize(128, 64);
      Front_ThighRight.mirror = true;
      setRotation(Front_ThighRight, 0F, 0F, 0F);
      Front_ThighLeft = new ModelRenderer(this, 18, 24);
      Front_ThighLeft.addBox(0F, 0F + yoffset, 0F, 4, 4, 4);
      Front_ThighLeft.setRotationPoint(-9F, 14F, 1F);
      Front_ThighLeft.setTextureSize(128, 64);
      Front_ThighLeft.mirror = true;
      setRotation(Front_ThighLeft, 0F, 0F, 0F);
      Back_ThighRight = new ModelRenderer(this, 0, 25);
      Back_ThighRight.addBox(0F, 0F + yoffset, 0F, 4, 5, 5);
      Back_ThighRight.setRotationPoint(5F, 13F, 10F);
      Back_ThighRight.setTextureSize(128, 64);
      Back_ThighRight.mirror = true;
      setRotation(Back_ThighRight, 0F, 0F, 0F);
      Back_ThighLeft = new ModelRenderer(this, 0, 25);
      Back_ThighLeft.addBox(0F, 0F + yoffset, 0F, 4, 5, 5);
      Back_ThighLeft.setRotationPoint(-9F, 13F, 10F);
      Back_ThighLeft.setTextureSize(128, 64);
      Back_ThighLeft.mirror = true;
      setRotation(Back_ThighLeft, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 35);
      Leg1.addBox(0F, 3F + yoffset, 0F, 3, 4, 4);
      Leg1.setRotationPoint(5F, 14F, 1F);
      Leg1.setTextureSize(128, 64);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 0, 35);
      Leg2.addBox(1F, 3F + yoffset, 0F, 3, 4, 4);
      Leg2.setRotationPoint(-9F, 14F, 1F);
      Leg2.setTextureSize(128, 64);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 0, 35);
      Leg3.addBox(0F, 4F + yoffset, 0.5F, 3, 4, 4);
      Leg3.setRotationPoint(5F, 13F, 10F);
      Leg3.setTextureSize(128, 64);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, 0F);
      Leg4 = new ModelRenderer(this, 0, 35);
      Leg4.addBox(0F, 4F + yoffset, 0.5F, 3, 4, 4);
      Leg4.setRotationPoint(-9F, 13F, 10F);
      Leg4.setTextureSize(128, 64);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0F);
  }
  
  public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
  {
	  Ankylosaurus entitydinosaure = (Ankylosaurus)var1;
	  
      super.render(var1, var2, var3, var4, var5, var6, var7);
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, ((Dinosaure)var1).isModelized());
      
    Head.render(var7);
    Mouth.render(var7);
    Head_Block.render(var7);
    HeadHorn1.render(var7);
    HeadHorn2.render(var7);
    HeadHorn3.render(var7);
    HeadHorn4.render(var7);
    Body.render(var7);
    BodySpikes.render(var7);
    Neck.render(var7);
    Tail1.render(var7);
    Front_ThighRight.render(var7);
    Front_ThighLeft.render(var7);
    Back_ThighRight.render(var7);
    Back_ThighLeft.render(var7);
    Leg1.render(var7);
    Leg2.render(var7);
    Leg3.render(var7);
    Leg4.render(var7);
  }
  
  private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
  {
      var1.rotateAngleX = var2;
      var1.rotateAngleY = var3;
      var1.rotateAngleZ = var4;
  }
  
  protected void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, boolean var7)
  {
      float PI = (float) Math.PI;
      float initialOffset = PI / 2;
      float offset = PI * 2 / 11;
      float currentAngle = 0;
      
      //if (var7==false)
          this.Front_ThighLeft.rotateAngleX = MathHelper.cos((var1)* 0.63330555F + 1) * 1.0F * var2;
          this.Front_ThighRight.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + (float)Math.PI) * 1.0F * var2;
          this.Back_ThighLeft.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + (float)Math.PI +2) * 1.0F * var2;
          this.Back_ThighRight.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + 1) * 1.0F * var2;
          
          this.Leg2.rotateAngleX = MathHelper.cos((var1)* 0.63330555F + 1) * 1.0F * var2;
          this.Leg1.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + (float)Math.PI) * 1.0F * var2;
          this.Leg4.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + (float)Math.PI +2) * 1.0F * var2;
          this.Leg3.rotateAngleX = MathHelper.cos((var1) * 0.63330555F + 1) * 1.0F * var2;

              this.Tail1.rotateAngleY = 0.15F * MathHelper.sin(var3 * (float)0.3F + var2);
              this.Tail2.rotateAngleY = 0.15F * MathHelper.sin(var3 * (float)0.2F + var2);         
              this.Tail3.rotateAngleY = 0.15F * MathHelper.sin(var3 * (float)0.1F + var2);         
          /*
          this.Tail1.rotateAngleY = ((float) Math.pow(0.25F, 1)) * (MathHelper.cos(-0.6F * var1 + initialOffset));
        currentAngle = Tail1.rotateAngleY;
        this.Tail2.rotateAngleY = ((float) Math.pow(0.25F, 1)) * (MathHelper.cos(-0.6F * var1 + 1F * offset + initialOffset)) - currentAngle;
        currentAngle = Tail1.rotateAngleY + Tail2.rotateAngleY;
        this.Tail3.rotateAngleY = ((float) Math.pow(0.25F, 3)) * (MathHelper.cos(-0.6F * var1 + 1.1F * 2 * offset + PI / 9 + initialOffset)) - currentAngle;
        currentAngle = Tail1.rotateAngleY + Tail2.rotateAngleY + Tail3.rotateAngleY;
        */
  }
}