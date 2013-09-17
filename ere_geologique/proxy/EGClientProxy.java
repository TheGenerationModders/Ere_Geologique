package ere_geologique.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import ere_geologique.client.model.ModelmobCroMagnon;
import ere_geologique.client.model.ModelmobTriceratops;
import ere_geologique.client.render.RendermobCroMagnon;
import ere_geologique.client.render.RendermobTriceratops;
import ere_geologique.common.entity.CroMagnon;
import ere_geologique.common.entity.Triceratops;

public class EGClientProxy extends EGCommonProxy
{
	public void registerRender()
	{

	}

	@Override
	public void registerRenderEntity()
	{
		RenderingRegistry.registerEntityRenderingHandler(CroMagnon.class, new RendermobCroMagnon(new ModelmobCroMagnon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Triceratops.class, new RendermobTriceratops(new ModelmobTriceratops(), 0.5F));
	}
}