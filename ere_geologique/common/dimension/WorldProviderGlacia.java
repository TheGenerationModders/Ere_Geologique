package ere_geologique.common.dimension;

import ere_geologique.common.config.EGProperties;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderGlacia extends WorldProvider
{
	@Override
	public String getDimensionName()
	{
		return "Glacia";
	}
	
	public void registerWorldChunkManager()
	{
	       this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.icePlains , 0.8F, 0.1F);
	       this.dimensionId = EGProperties.GlaciaID;
	}
	
	public IChunkProvider createChunkGenerator()
	{
	       return new ChunkProviderGlacia(worldObj, worldObj.getSeed(), true);
	}
}