package ere_geologique.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class EGEntityList
{
	public static void loadEntity()
	{
		try
		{
			EntityRegistry.registerGlobalEntityID(CroMagnon.class, "CroMagnon", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
			EntityRegistry.registerModEntity(CroMagnon.class, "CroMagnon", 230, EreGeologique.Instance, 40, 1, true);
			EntityRegistry.addSpawn(CroMagnon.class, 5, 2, 4, EnumCreatureType.monster, BiomeGenBase.plains);
	   
			for(int i=0;i<EnumDinoType.values().length;i++)
			EntityRegistry.registerModEntity(EnumDinoType.values()[i].getDinoClass(),EnumDinoType.values()[i].name(),233+i, EreGeologique.Instance, 250, 3, true);
	   
			EntityRegistry.registerModEntity(DinoEgg.class, "DinoEgg", 231, EreGeologique.Instance, 250, 5, true);
			EntityRegistry.registerModEntity(Failuresaurus.class, "Failuresaurus", 232, EreGeologique.Instance, 250, 5, true);
			
	        LanguageRegistry.instance().addStringLocalization("entity.fossil.Failuresaurus.name", StatCollector.translateToLocal(LocalizationStrings.MOB_FAILURESAURUS));
			for(int i=0;i<EnumDinoType.values().length;i++)
	        LanguageRegistry.instance().addStringLocalization("entity.fossil."+EnumDinoType.values()[i].name()+".name", StatCollector.translateToLocal("Dino."+EnumDinoType.values()[i].name()));
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des entity's!");
		}
		EreGeologique.EGLog.info("Initialisation des entity's terminés!");
	}
}