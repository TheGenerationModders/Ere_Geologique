package ere_geologique.common;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import ere_geologique.common.config.EGProperties;

public class EreGeologiqueGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
        {
        case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
        }
	}
	private void generateSurface(World world, Random random, int blockX, int blockZ)
    {
        (new WorldGenMinable(EGProperties.PrehistoriqueBlockCoalID, 10)).generate(world, random, blockX + random.nextInt(64), random.nextInt(32), blockZ + random.nextInt(64));
    }

}
