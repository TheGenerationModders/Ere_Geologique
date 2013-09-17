package ere_geologique.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import ere_geologique.client.model.ModelmobTriceratops;
import ere_geologique.common.entity.Triceratops;

public class RendermobTriceratops extends RenderLiving
{
	protected static final ResourceLocation texture = new ResourceLocation("ere_geologique:textures/entity/Tric\351ratops.png");
	
	private ModelBase ModelmobTriceratops = new ModelmobTriceratops();
	
	public RendermobTriceratops(ModelmobTriceratops par1ModelmobTriceratops, float par2)
	{
		super(new ModelmobTriceratops(), 0.5F);
	}
	public void renderTriceratops(Triceratops entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
 
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTriceratops((Triceratops)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
 	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTriceratops((Triceratops)par1Entity, par2, par4, par6, par8, par9);
    }
 	
 	protected ResourceLocation getTriceratopsTextures(Triceratops triceratops)
    {
        return texture;
    }
 	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getTriceratopsTextures((Triceratops)entity);
	}
}