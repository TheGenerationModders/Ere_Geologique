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
			GameRegistry.registerTileEntity(TileEntityDrum.class, "TileEntityDrum");
		}
		catch(Exception ex)
		{
			EreGeologique.egLog.severe("Erreur lors de l'initialisation des TileEntity's!");
		}
		EreGeologique.egLog.info("Initialisation des TileEntity's terminés!");
	}
}