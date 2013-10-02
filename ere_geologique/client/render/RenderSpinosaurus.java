package ere_geologique.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.entity.Spinosaurus;

@SideOnly(Side.CLIENT)
public class RenderSpinosaurus extends RenderLiving
{
    private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/entity/Spinosaurus_Adult.png");

    public RenderSpinosaurus(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    /**
     * Applies the scale to the transform matrix
     * 
     * Use this to grow the dinonsaur with age.
     */
    protected void preRenderScale(Spinosaurus entitydinosaur, float par2)
    {
        GL11.glScalef(entitydinosaur.getDinoWidth(), entitydinosaur.getDinoHeight(), entitydinosaur.getDinoLength());
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((Spinosaurus)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation func_110919_a(Spinosaurus par1Entity)
    {
        return loc;
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110919_a((Spinosaurus)par1Entity);
    }
}