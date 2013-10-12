package ere_geologique.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.entity.Triceratops;

@SideOnly(Side.CLIENT)
public class RenderTriceratops extends RenderLiving
{
//    private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/entity/Triceratops_Adult_1.png");
    
    public RenderTriceratops(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected void preRenderScale(Triceratops entitydinosaur, float par2)
    {
        GL11.glScalef(entitydinosaur.getDinoWidth(), entitydinosaur.getDinoHeight(), entitydinosaur.getDinoLength());
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((Triceratops)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation func_110919_a(Triceratops par1Entity)
    {
        return new ResourceLocation(par1Entity.getTexture());
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110919_a((Triceratops)par1Entity);
    }
}