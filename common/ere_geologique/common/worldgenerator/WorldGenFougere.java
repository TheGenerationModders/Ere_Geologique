package ere_geologique.common.worldgenerator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import ere_geologique.common.block.EGBlockList;

public class WorldGenFougere extends WorldGenAbstractTree
{
	private final int minTreeHeight;
	private final boolean growVines;
	private final int metaWood;
	private final int metaLeaves;

	public WorldGenFougere(boolean flag)
	{
		this(flag, 4, 0, 0, false);
	}

	public WorldGenFougere(boolean flag, int i, int j, int k, boolean flag1)
	{
		super(flag);
		minTreeHeight = i;
		metaWood = j;
		metaLeaves = k;
		growVines = flag1;
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int l = random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
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
            else
            {
                Block block2 = world.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)EGBlockList.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block2.onPlantGrow(world, x, y - 1, z, x, y, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = x - l1; i2 <= x + l1; ++i2)
                        {
                            j2 = i2 - x;

                            for (int k2 = z - l1; k2 <= z + l1; ++k2)
                            {
                                int l2 = k2 - z;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = world.getBlock(i2, k1, k2);

                                    if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, i2, k1, k2, EGBlockList.leaves, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = world.getBlock(x, y + k1, z);

                        if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k1, z, EGBlockList.wood, this.metaWood);

                            if (this.growVines && k1 > 0)
                            {
                                if (random.nextInt(3) > 0 && world.isAirBlock(x - 1, y + k1, z))
                                {
                                    this.setBlockAndNotifyAdequately(world, x - 1, y + k1, z, Blocks.vine, 8);
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(x + 1, y + k1, z))
                                {
                                    this.setBlockAndNotifyAdequately(world, x + 1, y + k1, z, Blocks.vine, 2);
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k1, z - 1))
                                {
                                    this.setBlockAndNotifyAdequately(world, x, y + k1, z - 1, Blocks.vine, 1);
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k1, z + 1))
                                {
                                    this.setBlockAndNotifyAdequately(world, x, y + k1, z + 1, Blocks.vine, 4);
                                }
                            }
                        }
                    }

                    if (this.growVines)
                    {
                        for (k1 = y - 3 + l; k1 <= y + l; ++k1)
                        {
                            i3 = k1 - (y + l);
                            l1 = 2 - i3 / 2;

                            for (i2 = x - l1; i2 <= x + l1; ++i2)
                            {
                                for (j2 = z - l1; j2 <= z + l1; ++j2)
                                {
                                    if (world.getBlock(i2, k1, j2).isLeaves(world, i2, k1, j2))
                                    {
                                        if (random.nextInt(4) == 0 && world.getBlock(i2 - 1, k1, j2).isAir(world, i2 - 1, k1, j2))
                                        {
                                            this.growVines(world, i2 - 1, k1, j2, 8);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlock(i2 + 1, k1, j2).isAir(world, i2 + 1, k1, j2))
                                        {
                                            this.growVines(world, i2 + 1, k1, j2, 2);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlock(i2, k1, j2 - 1).isAir(world, i2, k1, j2 - 1))
                                        {
                                            this.growVines(world, i2, k1, j2 - 1, 1);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlock(i2, k1, j2 + 1).isAir(world, i2, k1, j2 + 1))
                                        {
                                            this.growVines(world, i2, k1, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
	}
	
	private void growVines(World world, int x, int y, int z, int metadata)
    {
        this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, metadata);
        int i1 = 4;

        while (true)
        {
            --y;

            if (world.getBlock(x, y, z).isAir(world, x, y, z) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, metadata);
            --i1;
        }
    }

	/*public boolean generate(World world, Random random, int x, int y, int z)
	{
		int l = random.nextInt(3) + minTreeHeight;
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
						setBlockAndNotifyAdequately(world, k4, j2, k5, EGBlockList.leaves, metaLeaves);
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

			setBlockAndNotifyAdequately(world, x, y + k2, z, EGBlockList.wood, metaWood);

			if (!growVines || k2 <= 0)
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

		if (growVines)
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

	}*/
}