package ere_geologique.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ere_geologique.client.model.ModelCroMagnon;
import ere_geologique.common.entity.CroMagnon;
 
public class RenderCroMagnon extends RenderLiving
{
	protected static final ResourceLocation texture = new ResourceLocation("ere_geologique:textures/entity/Homme.png");
	
    private ModelBase ModelCroMagnon = new ModelCroMagnon();
 
    public RenderCroMagnon(ModelCroMagnon ModelCroMagnon, float par2)
    {
        super(new ModelCroMagnon(), 0.5F);
    }
    
    protected ResourceLocation getCroMagnonTextures(CroMagnon cromagnon)
    {
        return texture;
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getCroMagnonTextures((CroMagnon)entity);
	}
}