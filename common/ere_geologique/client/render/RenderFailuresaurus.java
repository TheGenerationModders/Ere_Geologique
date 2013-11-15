package ere_geologique.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ere_geologique.common.entity.Failuresaurus;

public class RenderFailuresaurus extends RenderLiving
{
	
    private static final ResourceLocation texturelocation = new ResourceLocation("er_geologique:textures/mob/Failuresaurus.png");
    
    public RenderFailuresaurus(ModelBase var1, float var2)
    {
        super(var1, var2);
    }
    
    protected ResourceLocation func_110919_a(Failuresaurus par1Entity)
    {
        return texturelocation;
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110919_a((Failuresaurus)par1Entity);
    }

}