package ere_geologique.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import ere_geologique.client.audio.DinoSoundHandler;
import ere_geologique.client.model.ModelAnkylosaurus;
import ere_geologique.client.model.ModelBrachiosaurus;
import ere_geologique.client.model.ModelCompsognathus;
import ere_geologique.client.model.ModelCroMagnon;
import ere_geologique.client.model.ModelDilophosaurus;
import ere_geologique.client.model.ModelFailuresaurus;
import ere_geologique.client.model.ModelMosasaurus;
import ere_geologique.client.model.ModelNautilus;
import ere_geologique.client.model.ModelPachycephalosaurus;
import ere_geologique.client.model.ModelPlesiosaure;
import ere_geologique.client.model.ModelPterosaureGround;
import ere_geologique.client.model.ModelSpinosaurus;
import ere_geologique.client.model.ModelStegosaurus;
import ere_geologique.client.model.ModelTRex;
import ere_geologique.client.model.ModelTriceratops;
import ere_geologique.client.model.ModelVelociraptor;
import ere_geologique.client.render.RenderAnkylosaurus;
import ere_geologique.client.render.RenderBrachiosaurus;
import ere_geologique.client.render.RenderCompsognathus;
import ere_geologique.client.render.RenderCroMagnon;
import ere_geologique.client.render.RenderDilophosaurus;
import ere_geologique.client.render.RenderDinoEgg;
import ere_geologique.client.render.RenderFailuresaurus;
import ere_geologique.client.render.RenderMosasaurus;
import ere_geologique.client.render.RenderNautilus;
import ere_geologique.client.render.RenderPachycephalosaurus;
import ere_geologique.client.render.RenderPlesiosaure;
import ere_geologique.client.render.RenderPterosaure;
import ere_geologique.client.render.RenderSpinosaurus;
import ere_geologique.client.render.RenderStegosaurus;
import ere_geologique.client.render.RenderTRex;
import ere_geologique.client.render.RenderTriceratops;
import ere_geologique.client.render.RenderVelociraptor;
import ere_geologique.common.entity.Ankylosaurus;
import ere_geologique.common.entity.Brachiosaurus;
import ere_geologique.common.entity.Compsognathus;
import ere_geologique.common.entity.CroMagnon;
import ere_geologique.common.entity.Dilophosaurus;
import ere_geologique.common.entity.DinoEgg;
import ere_geologique.common.entity.Failuresaurus;
import ere_geologique.common.entity.Mosasaurus;
import ere_geologique.common.entity.Nautilus;
import ere_geologique.common.entity.Pachycephalosaurus;
import ere_geologique.common.entity.Plesiosaure;
import ere_geologique.common.entity.Pterosaure;
import ere_geologique.common.entity.Spinosaurus;
import ere_geologique.common.entity.Stegosaurus;
import ere_geologique.common.entity.TRex;
import ere_geologique.common.entity.Triceratops;
import ere_geologique.common.entity.Velociraptor;

public class EGClientProxy extends EGCommonProxy
{
	public void initSound()
	{
		FMLCommonHandler.instance().bus().register(new DinoSoundHandler());
	}
	
	public void registerRender()
	{
	}

	@Override
	public void registerRenderEntity()
	{
		RenderingRegistry.registerEntityRenderingHandler(CroMagnon.class, new RenderCroMagnon(new ModelCroMagnon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Triceratops.class, new RenderTriceratops(new ModelTriceratops(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Velociraptor.class, new RenderVelociraptor(new ModelVelociraptor(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(TRex.class, new RenderTRex(new ModelTRex(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Pterosaure.class, new RenderPterosaure(new ModelPterosaureGround(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Nautilus.class, new RenderNautilus(new ModelNautilus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Plesiosaure.class, new RenderPlesiosaure(new ModelPlesiosaure(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Mosasaurus.class, new RenderMosasaurus(new ModelMosasaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Stegosaurus.class, new RenderStegosaurus(new ModelStegosaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(DinoEgg.class, new RenderDinoEgg(1.5F));
		RenderingRegistry.registerEntityRenderingHandler(Dilophosaurus.class, new RenderDilophosaurus(new ModelDilophosaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Brachiosaurus.class, new RenderBrachiosaurus(new ModelBrachiosaurus(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(Spinosaurus.class, new RenderSpinosaurus(new ModelSpinosaurus(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(Compsognathus.class, new RenderCompsognathus(new ModelCompsognathus(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(Ankylosaurus.class, new RenderAnkylosaurus(new ModelAnkylosaurus(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(Pachycephalosaurus.class, new RenderPachycephalosaurus(new ModelPachycephalosaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Failuresaurus.class, new RenderFailuresaurus(new ModelFailuresaurus(), 0.5F));
	}
}