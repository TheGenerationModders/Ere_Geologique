package ere_geologique.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBrachiosaurus extends ModelBase
{
	float yoffset = 0.0F;
    ModelRenderer Snout = (new ModelRenderer(this, 50, 8)).setTextureSize(64, 32);
    ModelRenderer Head;
    ModelRenderer Crest;
    ModelRenderer Neck;
    ModelRenderer Neck1;
    ModelRenderer Neck2;
    ModelRenderer Neck3;
    ModelRenderer Neck4;
    ModelRenderer Neck5;
    ModelRenderer Neck6;
    ModelRenderer Neck7;
    ModelRenderer Lower_Neck;
    ModelRenderer Lower_Neck1;
    ModelRenderer Body;
    ModelRenderer Body2;
    ModelRenderer Lower_Body;
    ModelRenderer Front_ThighRight;
    ModelRenderer Front_ThighLeft;
    ModelRenderer Back_CalfRight;
    ModelRenderer Front_CalfRight;
    ModelRenderer Back_ThighLeft;
    ModelRenderer Back_ThighRight;
    ModelRenderer Front_CalfLeft;
    ModelRenderer Back_CalfLeft;
    ModelRenderer Tail;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    
    ModelRenderer dummy;

    public ModelBrachiosaurus()
    {
        // Head
        this.Head = (new ModelRenderer(this, 48, 14)).setTextureSize(64, 32);
        this.Head.addBox(-2.0F, -1.0F, -4.0F, 4, 3, 4);
        this.Head.setRotationPoint(0.0F, -6.0F, -10.5F);
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
        this.Head.mirror = true;
        
        this.Snout.addBox(-1.5F, -1.0F, -6.5F, 3, 2, 4);
        this.Snout.setRotationPoint(0.0F, -0.0F, -0.0F);
        this.setRotation(this.Snout, 0.2617994F, 0.0F, 0.0F);
        this.Snout.mirror = true;
        
        this.Crest = (new ModelRenderer(this, 52, 0)).setTextureSize(64, 32);
        this.Crest.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 4);
        this.Crest.setRotationPoint(0.0F, -3.0F, -5.0F);
        this.setRotation(this.Crest, 0.0F, 0.0F, 0.0F);
        this.Crest.mirror = true;
        
        Head.addChild(Crest);
        Head.addChild(Snout);
        
        
        this.Neck = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2);
        this.Neck.setRotationPoint(0.0F, -6.0F-yoffset, -10.5F);
        this.setRotation(this.Neck, -((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Neck.mirror = true;
        this.Neck1 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck1.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2);
        this.Neck1.setRotationPoint(0.0F, -4.5F-yoffset, -9.0F);
        this.setRotation(this.Neck1, -0.9599311F, 0.0F, 0.0F);
        this.Neck1.mirror = true;
        this.Neck2 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck2.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2);
        this.Neck2.setRotationPoint(0.0F, -3.0F-yoffset, -8.0F);
        this.setRotation(this.Neck2, -1.23464F, 0.0F, 0.0F);
        this.Neck2.mirror = true;
        this.Neck3 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck3.addBox(-1.5F, 0.0F, 2.0F, 3, 2, 2);
        this.Neck3.setRotationPoint(0.0F, -1.5F-yoffset, -7.5F);
        this.setRotation(this.Neck3, -1.343904F, 0.0F, 0.0F);
        this.Neck3.mirror = true;
        this.Neck4 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck4.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2);
        this.Neck4.setRotationPoint(0.0F, -1.5F-yoffset, -7.5F);
        this.setRotation(this.Neck4, -1.343904F, 0.0F, 0.0F);
        this.Neck4.mirror = true;
        this.Neck5 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck5.addBox(-1.5F, 0.0F, 4.0F, 3, 2, 2);
        this.Neck5.setRotationPoint(0.0F, -1.5F-yoffset, -7.5F);
        this.setRotation(this.Neck5, -1.343904F, 0.0F, 0.0F);
        this.Neck5.mirror = true;
        this.Neck6 = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
        this.Neck6.addBox(-1.5F, 0.0F, 6.0F, 3, 2, 2);
        this.Neck6.setRotationPoint(0.0F, -1.5F-yoffset, -7.5F);
        this.setRotation(this.Neck6, -1.343904F, 0.0F, 0.0F);
        this.Neck6.mirror = true;
        this.Neck7 = (new ModelRenderer(this, 34, 11)).setTextureSize(64, 32);
        this.Neck7.addBox(-2.0F, -1.0F, -0.5F, 4, 3, 3);
        this.Neck7.setRotationPoint(0.0F, 6.0F-yoffset, -6.5F);
        this.setRotation(this.Neck7, -0.9637522F, 0.0F, 0.0F);
        this.Neck7.mirror = true;
        
        this.Lower_Neck = (new ModelRenderer(this, 32, 24)).setTextureSize(64, 32);
        this.Lower_Neck.addBox(-2.5F, -0.5F, -0.5F, 5, 4, 4);
        this.Lower_Neck.setRotationPoint(0.0F, 7.0F-yoffset, -5.0F);
        this.setRotation(this.Lower_Neck, -0.8377581F, 0.0F, 0.0F);
        this.Lower_Neck.mirror = true;
        

        
        
        //Body
        
        this.Lower_Neck1 = (new ModelRenderer(this, 10, 21)).setTextureSize(64, 32);
        this.Lower_Neck1.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 5);
        this.Lower_Neck1.setRotationPoint(0.0F, 3.0F, -0.0F);
        this.setRotation(this.Lower_Neck1, -0.5907885F, 0.0F, 0.0F);
        this.Lower_Neck1.mirror = true;
        
        this.Body = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
        this.Body.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 6);
        this.Body.setRotationPoint(0.0F, 9.0F, -3.0F);
        this.setRotation(this.Body, -0.0F, 0.0F, 0.0F);
        this.Body.mirror = true;
        
        this.Body2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
        this.Body2.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 2);
        this.Body2.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.setRotation(this.Body2, -0.0F, 0.0F, 0.0F);
        this.Body2.mirror = true;

        this.Lower_Body = (new ModelRenderer(this, 28, 0)).setTextureSize(64, 32);
        this.Lower_Body.addBox(-3.5F, 0.0F, 3.0F, 7, 6, 5);
        this.Lower_Body.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.setRotation(this.Lower_Body, -0.3346075F, 0.0F, 0.0F);
        this.Lower_Body.mirror = true;
        
        //Tail
        this.Tail = (new ModelRenderer(this, 0, 13)).setTextureSize(64, 32);
        this.Tail.addBox(-2.5F, 0.0F, 3.0F, 5, 4, 5);
        this.Tail.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.setRotation(this.Tail, -0.2064018F, 0.0F, 0.0F);
        this.Tail.mirror = true;
        
        this.Tail1 = (new ModelRenderer(this, 18, 13)).setTextureSize(64, 32);
        this.Tail1.addBox(-2.0F, -1.0F, 4.0F, 4, 3, 6);
        this.Tail1.setRotationPoint(0.0F, 0.5F, 4.0F);
        this.setRotation(this.Tail1, -0.2576873F, 0.0F, 0.0F);
        this.Tail1.mirror = true;
        
        this.Tail2 = (new ModelRenderer(this, 34, 17)).setTextureSize(64, 32);
        this.Tail2.addBox(-1.5F, 1.5F, 5.0F, 3, 2, 3);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 4F);
        this.setRotation(this.Tail2, 0.3717943F, 0.0F, 0.0F);
        this.Tail2.mirror = true;
        
        this.Tail3 = (new ModelRenderer(this, 34, 17)).setTextureSize(64, 32);
        this.Tail3.addBox(-1.5F, 2.F, 0.0F, 3, 1, 3);
        this.Tail3.setRotationPoint(0.0F, 0.5F, 6.0F);
        this.setRotation(this.Tail3, 0.3717943F, 0.0F, 0.0F);
        this.Tail3.mirror = true;
        
        Body.addChild(Lower_Body);
        Body.addChild(Body2);
        Body.addChild(Lower_Neck1);
        Lower_Body.addChild(Tail);
        Tail.addChild(Tail1);
        Tail1.addChild(Tail2);
        Tail2.addChild(Tail3);
        
        
        
        //Legs
        this.Front_ThighRight = (new ModelRenderer(this, 50, 21)).setTextureSize(64, 32);
        this.Front_ThighRight.addBox(0.0F, 0.0F, -2.0F, 3, 7, 4);
        this.Front_ThighRight.setRotationPoint(2.0F, 3.0F, -0.5F);
        this.setRotation(this.Front_ThighRight, 0.0F, 0.0F, 0.0F);
        this.Front_ThighRight.mirror = true;
        
        this.Front_CalfRight = (new ModelRenderer(this, 0, 24)).setTextureSize(64, 32);
        this.Front_CalfRight.addBox(1.5F, 7.0F, -0.5F, 2, 5, 3);
        this.Front_CalfRight.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.setRotation(this.Front_CalfRight, 0.0F, 0.0F, 0.0F);
        this.Front_CalfRight.mirror = true;     
        
        Front_ThighRight.addChild(Front_CalfRight);      
        
        this.Front_ThighLeft = (new ModelRenderer(this, 50, 21)).setTextureSize(64, 32);
        this.Front_ThighLeft.addBox(-3.0F, 0.0F, -2.0F, 3, 7, 4);
        this.Front_ThighLeft.setRotationPoint(-2.0F, 3.0F, -0.5F);
        this.setRotation(this.Front_ThighLeft, 0.0F, 0.0F, 0.0F);
        this.Front_ThighLeft.mirror = true;
        
        this.Front_CalfLeft = (new ModelRenderer(this, 0, 24)).setTextureSize(64, 32);
        this.Front_CalfLeft.addBox(-3.5F, 7.0F, -0.5F, 2, 5, 3);
        this.Front_CalfLeft.setRotationPoint(1.0F, 0.0F, -1.0F);
        this.setRotation(this.Front_CalfLeft, 0.0F, 0.0F, 0.0F);
        this.Front_CalfLeft.mirror = true;   
        
        Front_ThighLeft.addChild(Front_CalfLeft);           
  
        this.Back_ThighRight = (new ModelRenderer(this, 50, 21)).setTextureSize(64, 32);
        this.Back_ThighRight.addBox(-1.0F, 0.0F, -2.0F, 3, 5, 4);
        this.Back_ThighRight.setRotationPoint(3.0F, 5.0F, 9.5F);
        this.setRotation(this.Back_ThighRight, 0.0F, 0.0F, 0.0F);
        this.Back_ThighRight.mirror = true;     
        
        this.Back_CalfRight = (new ModelRenderer(this, 0, 24)).setTextureSize(64, 32);
        this.Back_CalfRight.addBox(0.5F, 5.0F, -0.5F, 2, 5, 3);
        this.Back_CalfRight.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.setRotation(this.Back_CalfRight, 0.0F, 0.0F, 0.0F);
        this.Back_CalfRight.mirror = true;
        
        Back_ThighRight.addChild(Back_CalfRight);
        
        this.Back_ThighLeft = (new ModelRenderer(this, 50, 21)).setTextureSize(64, 32);
        this.Back_ThighLeft.addBox(-2.0F, 0.0F, -2.0F, 3, 5, 4);
        this.Back_ThighLeft.setRotationPoint(-3.0F, 5.0F, 9.5F);
        this.setRotation(this.Back_ThighLeft, 0.0F, 0.0F, 0.0F);
        this.Back_ThighLeft.mirror = true;
        
        this.Back_CalfLeft = (new ModelRenderer(this, 0, 24)).setTextureSize(64, 32);
        this.Back_CalfLeft.addBox(-0.5F, 5.0F, -0.5F, 2, 5, 3);
        this.Back_CalfLeft.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.setRotation(this.Back_CalfLeft, 0.0F, 0.0F, 0.0F);
        this.Back_CalfLeft.mirror = true;           

        Back_ThighLeft.addChild(Back_CalfLeft);
        
        Body.addChild(Back_ThighLeft);
        Body.addChild(Back_ThighRight);
        Body.addChild(Front_ThighRight);
        Body.addChild(Front_ThighLeft);
        
        

        
        
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        float var14 = 0.0225F;
        super.render(var1, var2, var3, var4, var5, var6, var7);
  //      this.setRotationAngles(var2, var3, var4, var5, var6, var7, ((EntityDinosaur)var1).isModelized());

        this.Head.render(var7);
        this.Neck.render(var7);
        this.Neck1.render(var7);
        this.Neck2.render(var7);
        this.Neck3.render(var7);
        this.Neck4.render(var7);
        this.Neck5.render(var7);
        this.Neck6.render(var7);
        this.Neck7.render(var7);
        this.Lower_Neck.render(var7);
        this.Body.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    protected void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, boolean var7)
    {
        //if (var7==false)
        {
            this.Front_ThighRight.rotateAngleX = MathHelper.cos((var1)* 0.2662F + 1) * 1.0F * var2/2F;
            this.Front_ThighLeft.rotateAngleX = MathHelper.cos((var1) * 0.2662F + (float)Math.PI) * 1.0F * var2/2F;
            this.Back_ThighRight.rotateAngleX = MathHelper.cos((var1) * 0.2662F + (float)Math.PI +2) * 1.0F * var2/2F;
            this.Back_ThighLeft.rotateAngleX = MathHelper.cos((var1) * 0.2662F + 1) * 1.0F * var2/2F;

        }
    }
}