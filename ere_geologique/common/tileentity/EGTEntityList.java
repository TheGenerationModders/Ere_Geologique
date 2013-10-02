package ere_geologique.common.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.EreGeologique;

public class EGTEntityList
{
	public static void loadTileEntity()
	{
		try
		{
			GameRegistry.registerTileEntity(TileEntityFeeder.class, "TileEntityFeeder");
			GameRegistry.registerTileEntity(TileEntityAnalyzer.class, "TileEntityAnalyzer");
			GameRegistry.registerTileEntity(TileEntityCultivator.class, "TileEntityCultivator");
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des TileEntity's!");
		}
		EreGeologique.EGLog.info("Initialisation des TileEntity's terminés!");
	}
}