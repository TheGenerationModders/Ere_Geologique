package ere_geologique.common.dimension;

import ere_geologique.common.config.EGProperties;
import net.minecraftforge.common.DimensionManager;

public class EGDimensionList
{	
	public static void loadDimension()
	{
		DimensionManager.registerProviderType(EGProperties.GlaciaID, WorldProviderGlacia.class, false);
		DimensionManager.registerDimension(EGProperties.GlaciaID, EGProperties.GlaciaID);
	}
}