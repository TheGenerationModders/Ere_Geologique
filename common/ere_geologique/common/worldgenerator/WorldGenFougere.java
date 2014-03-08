package ere_geologique.common.worldgenerator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import ere_geologique.common.block.EGBlockList;

public class WorldGenFougere extends WorldGenerator
{
	private final int field_48202_a;
	private final boolean field_48200_b;
	private final int field_48201_c;
	private final int field_48199_d;

	public WorldGenFougere(boolean flag)
	{
		this(flag, 4, 0, 0, false);
	}

	public WorldGenFougere(boolean flag, int i, int j, int k, boolean flag1)
	{
		super(flag);
		field_48202_a = i;
		field_48201_c = j;
		field_48199_d = k;
		field_48200_b = flag1;
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int l = random.nextInt(3) + field_48202_a;
		boolean flag = true;

		if (y < 1 || y + l + 1 > 256)
		{
			return false;
		}

		for (int i1 = y; i1 <= y + 1 + l; i1++)
		{
			byte byte0 = 1;

			if (i1 == y)
			{
				byte0 = 0;
			}

			if (i1 >= (y + 1 + l) - 2)
			{
				byte0 = 2;
			}

			for (int k1 = x - byte0; k1 <= x + byte0 && flag; k1++)
			{
				for (int i2 = z - byte0; i2 <= z + byte0 && flag; i2++)
				{
					if (i1 >= 0 && i1 < 256)
					{
						Block block = world.getBlock(k1, i1, i2);
						// ce code indique sur quel block l'arbre peut se generer
						if (block != null && block != EGBlockList.leaves && block != Blocks.dirt && block != EGBlockList.wood)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
			}
		}

		if (!flag)
		{
			return false;
		}

		Block j1 = world.getBlock(x, y - 1, z);

		if (j1 != Blocks.dirt && j1 != Blocks.grass || y >= 256 - l - 1)
		{
			return false;
		}

		func_50073_a(world, x, y - 1, z, EGBlockList.wood);
		byte byte1 = 3;
		int l1 = 0;

		for (int j2 = (y - byte1) + l; j2 <= y + l; j2++)
		{
			int j3 = j2 - (y + l);
			int i4 = (l1 + 1) - j3 / 2;

			for (int k4 = x - i4; k4 <= x + i4; k4++)
			{
				int i5 = k4 - x;

				for (int k5 = z - i4; k5 <= z + i4; k5++)
				{
					int l5 = k5 - z;

					if ((Math.abs(i5) != i4 || Math.abs(l5) != i4 || random.nextInt(2) != 0 && j3 != 0) && !(world.getBlock(k4, j2, k5).isOpaqueCube()))
					{
						setBlockAndNotifyAdequately(world, k4, j2, k5, EGBlockList.leaves, field_48199_d);
					}
				}
			}
		}

		for (int k2 = 0; k2 < l; k2++)
		{
			Block k3 = world.getBlock(x, y + k2, z);

			if (k3 != null && k3 != EGBlockList.leaves)
			{
				continue;
			}

			setBlockAndNotifyAdequately(world, x, y + k2, z, EGBlockList.wood, field_48201_c);

			if (!field_48200_b || k2 <= 0)
			{
				continue;
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(x - 1, y + k2, z))
			{
				setBlockAndNotifyAdequately(world, x - 1, y + k2, z, Blocks.dirt, 8);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(x + 1, y + k2, z))
			{
				setBlockAndNotifyAdequately(world, x + 1, y + k2, z, Blocks.dirt, 2);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k2, z - 1))
			{
				setBlockAndNotifyAdequately(world, x, y + k2, z - 1, Blocks.dirt, 1);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k2, z + 1))
			{
				setBlockAndNotifyAdequately(world, x, y + k2, z + 1, Blocks.dirt, 4);
			}
		}

		if (field_48200_b)
		{
			for (int l2 = (y - 3) + l; l2 <= y + l; l2++)
			{
				int l3 = l2 - (y + l);
				int j4 = 2 - l3 / 2;

				for (int l4 = x - j4; l4 <= x + j4; l4++)
				{
					for (int j5 = z - j4; j5 <= z + j4; j5++)
					{
						if (world.getBlock(l4, l2, j5) != EGBlockList.wood)
						{
							continue;
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 - 1, l2, j5) == null)
						{
							func_48198_a(world, l4 - 1, l2, j5, 8);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 + 1, l2, j5) == null)
						{
							func_48198_a(world, l4 + 1, l2, j5, 2);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 - 1) == null)
						{
							func_48198_a(world, l4, l2, j5 - 1, 1);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 + 1) == null)
						{
							func_48198_a(world, l4, l2, j5 + 1, 4);
						}
					}
				}
			}
		}

		return true;
	}

	private void func_50073_a(World world, int x, int y, int z, Block block)
	{

	}

	private void func_48198_a(World world, int x, int y, int z, int l)
	{
		setBlockAndNotifyAdequately(world, x, y, z, Blocks.dirt, l);

		for (int i1 = 4; world.getBlock(x, --y, z) == null && i1 > 0; i1--)
		{
			setBlockAndNotifyAdequately(world, x, y, z, Blocks.dirt, l);
		}
	}

	public void fertilize(World world, int x, int y, int z)
	{

	}
}