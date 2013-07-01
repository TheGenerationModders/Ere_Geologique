package ere_geologique.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.entity.CroMagnon;
import ere_geologique.common.entity.Triceratops;

public class EreGeologiqueEntityList
{
	public static void loadEreGeologiqueEntity()
	{
	   EntityRegistry.registerGlobalEntityID(CroMagnon.class, "CroMagnon", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
	   EntityRegistry.registerModEntity(CroMagnon.class, "CroMagnon", 1, EreGeologique.Instance, 40, 1, true);
	   EntityRegistry.addSpawn(CroMagnon.class, 5, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
	   LanguageRegistry.instance().addStringLocalization("entity.MobTuto.name", "MobTuto");
	   
	   EntityRegistry.registerGlobalEntityID(Triceratops.class, "Tric\351ratops", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
	   EntityRegistry.registerModEntity(Triceratops.class, "Tric\351ratops", 2, EreGeologique.Instance, 40, 1, true);
	   EntityRegistry.addSpawn(Triceratops.class, 2, 1, 4, EnumCreatureType.creature, BiomeGenBase.plains);
	   LanguageRegistry.instance().addStringLocalization("entity.Tric\351ratops.name", "Tric\351ratops");
	}
}
