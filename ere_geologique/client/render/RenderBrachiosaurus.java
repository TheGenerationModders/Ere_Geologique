package ere_geologique.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.model.ModelBrachiosaurus;
import ere_geologique.common.entity.Brachiosaurus;

@SideOnly(Side.CLIENT)
public class RenderBrachiosaurus extends RenderLiving
{
//    private static final ResourceLocation loc = new ResourceLocation("fossil:textures/mob/Brachiosaurus.png");
    
    public RenderBrachiosaurus(float var1)
    {
        super(new ModelBrachiosaurus(), var1);
    }
    
    protected ResourceLocation func_110919_a(Brachiosaurus par1Entity)
    {
        return new ResourceLocation(par1Entity.getTexture());
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110919_a((Brachiosaurus)par1Entity);
    }
}