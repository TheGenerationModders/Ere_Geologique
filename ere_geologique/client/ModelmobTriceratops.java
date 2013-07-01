package ere_geologique.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelmobTriceratops extends ModelBase
{
  //fields
    ModelRenderer Tete;
    ModelRenderer Corne1;
    ModelRenderer Corne2;
    ModelRenderer Corne3;
    ModelRenderer Body;
    ModelRenderer Patte1;
    ModelRenderer Patte2;
    ModelRenderer Patte3;
    ModelRenderer Patte4;
    ModelRenderer Queue1;
    ModelRenderer Queue2;
    ModelRenderer Queue3;
    ModelRenderer Queue4;
  
  public ModelmobTriceratops()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Tete = new ModelRenderer(this, 0, 11);
      Tete.addBox(-5F, -5F, -2F, 10, 10, 2);
      Tete.setRotationPoint(0F, 11F, -9F);
      Tete.setTextureSize(128, 64);
      Tete.mirror = true;
      setRotation(Tete, 0F, 0F, 0F);
      Corne1 = new ModelRenderer(this, 29, 26);
      Corne1.addBox(0F, 0F, -10F, 1, 1, 10);
      Corne1.setRotationPoint(3F, 8F, -11F);
      Corne1.setTextureSize(128, 64);
      Corne1.mirror = true;
      setRotation(Corne1, -0.3316126F, 0F, 0F);
      Corne2 = new ModelRenderer(this, 52, 26);
      Corne2.addBox(-1F, 0F, -10F, 1, 1, 10);
      Corne2.setRotationPoint(-3F, 8F, -11F);
      Corne2.setTextureSize(128, 64);
      Corne2.mirror = true;
      setRotation(Corne2, -0.3316126F, 0F, 0F);
      Corne3 = new ModelRenderer(this, 75, 26);
      Corne3.addBox(-1F, -1F, -4F, 2, 2, 4);
      Corne3.setRotationPoint(0F, 12F, -11F);
      Corne3.setTextureSize(128, 64);
      Corne3.mirror = true;
      setRotation(Corne3, -0.2268928F, 0F, 0F);
      Body = new ModelRenderer(this, 24, 0);
      Body.addBox(-5F, 0F, 0F, 10, 8, 18);
      Body.setRotationPoint(0F, 8F, -9F);
      Body.setTextureSize(128, 64);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Patte1 = new ModelRenderer(this, 0, 0);
      Patte1.addBox(0F, 0F, 0F, 3, 8, 3);
      Patte1.setRotationPoint(-5F, 16F, -9F);
      Patte1.setTextureSize(128, 64);
      Patte1.mirror = true;
      setRotation(Patte1, 0F, 0F, 0F);
      Patte2 = new ModelRenderer(this, 0, 0);
      Patte2.addBox(-3F, 0F, 0F, 3, 8, 3);
      Patte2.setRotationPoint(5F, 16F, -9F);
      Patte2.setTextureSize(128, 64);
      Patte2.mirror = true;
      setRotation(Patte2, 0F, 0F, 0F);
      Patte3 = new ModelRenderer(this, 0, 0);
      Patte3.addBox(0F, 0F, 0F, 3, 8, 3);
      Patte3.setRotationPoint(-5F, 16F, 6F);
      Patte3.setTextureSize(128, 64);
      Patte3.mirror = true;
      setRotation(Patte3, 0F, 0F, 0F);
      Patte4 = new ModelRenderer(this, 0, 0);
      Patte4.addBox(-3F, 0F, 0F, 3, 8, 3);
      Patte4.setRotationPoint(5F, 16F, 6F);
      Patte4.setTextureSize(128, 64);
      Patte4.mirror = true;
      setRotation(Patte4, 0F, 0F, 0F);
      Queue1 = new ModelRenderer(this, 0, 26);
      Queue1.addBox(-4F, -3F, 0F, 8, 6, 6);
      Queue1.setRotationPoint(0F, 12F, 9F);
      Queue1.setTextureSize(128, 64);
      Queue1.mirror = true;
      setRotation(Queue1, 0F, 0F, 0F);
      Queue2 = new ModelRenderer(this, 0, 48);
      Queue2.addBox(-3F, -2F, 0F, 6, 4, 4);
      Queue2.setRotationPoint(0F, 12F, 15F);
      Queue2.setTextureSize(128, 64);
      Queue2.mirror = true;
      setRotation(Queue2, 0F, 0F, 0F);
      Queue3 = new ModelRenderer(this, 0, 44);
      Queue3.addBox(-2F, -1F, 0F, 4, 2, 2);
      Queue3.setRotationPoint(0F, 12F, 19F);
      Queue3.setTextureSize(128, 64);
      Queue3.mirror = true;
      setRotation(Queue3, 0F, 0F, 0F);
      Queue4 = new ModelRenderer(this, 12, 0);
      Queue4.addBox(-1F, -1F, 0F, 2, 2, 1);
      Queue4.setRotationPoint(0F, 12F, 21F);
      Queue4.setTextureSize(128, 64);
      Queue4.mirror = true;
      setRotation(Queue4, 0F, 0F, 0F);
  }
  
  public void render(Entity par1entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
    super.render(par1entity, par2, par3, par4, par5, par6, par7);
    setRotationAngles(par2, par3, par4, par5, par6, par7, par1entity);
    Tete.render(par7);
    Corne1.render(par7);
    Corne2.render(par7);
    Corne3.render(par7);
    Body.render(par7);
    Patte1.render(par7);
    Patte2.render(par7);
    Patte3.render(par7);
    Patte4.render(par7);
    Queue1.render(par7);
    Queue2.render(par7);
    Queue3.render(par7);
    Queue4.render(par7);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    this.Tete.rotateAngleX = par5 / (180F / (float)Math.PI);
    this.Tete.rotateAngleY = par4 / (180F / (float)Math.PI);
    this.Patte1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    this.Patte2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    this.Patte3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    this.Patte4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
  }

}
