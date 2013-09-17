package ere_geologique.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import ere_geologique.common.EreGeologique;

public class EGEntityList
{
	public static void loadEntity()
	{
	   EntityRegistry.registerGlobalEntityID(CroMagnon.class, "CroMagnon", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
	   EntityRegistry.registerModEntity(CroMagnon.class, "CroMagnon", 230, EreGeologique.Instance, 40, 1, true);
	   EntityRegistry.addSpawn(CroMagnon.class, 5, 2, 4, EnumCreatureType.monster, BiomeGenBase.plains);
	   
	   EntityRegistry.registerGlobalEntityID(Triceratops.class, "Tric\351ratops", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
	   EntityRegistry.registerModEntity(Triceratops.class, "Tric\351ratops", 231, EreGeologique.Instance, 40, 1, true);
	   EntityRegistry.addSpawn(Triceratops.class, 2, 1, 4, EnumCreatureType.creature, BiomeGenBase.plains);
	}
}