package ere_geologique.common.worldgenerator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenAraucarias extends WorldGenAbstractTree
{
	public WorldGenAraucarias(boolean flag)
	{
		super(flag);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		return false;
	}
}