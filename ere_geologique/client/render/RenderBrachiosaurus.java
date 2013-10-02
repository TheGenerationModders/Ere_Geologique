package ere_geologique.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.model.ModelBrachiosaurus;
import ere_geologique.common.entity.Brachiosaurus;

@SideOnly(Side.CLIENT)
public class RenderBrachiosaurus extends RenderLiving
{
    private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/entity/Brachiosaurus.png");
    
    public RenderBrachiosaurus(float var1)
    {
        super(new ModelBrachiosaurus(), var1);
    }
    
    protected void preRenderScale(Brachiosaurus entitydinosaur, float par2)
    {
        GL11.glScalef(entitydinosaur.getDinoWidth(), entitydinosaur.getDinoHeight(), entitydinosaur.getDinoLength());
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((Brachiosaurus)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation func_110919_a(Brachiosaurus par1Entity)
    {
        return loc;
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110919_a((Brachiosaurus)par1Entity);
    }
}