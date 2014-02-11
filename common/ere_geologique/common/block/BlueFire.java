package ere_geologique.common.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.IdentityHashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlueFire extends BlockFire
{
	@Deprecated
	private int[] chanceToEncourageFire = new int[256];
	@Deprecated
	private int[] abilityToCatchFire = new int[256];
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlueFire()
	{
		super();
		setTickRandomly(true);
	}

	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}

	public void initializeBlock()
	{
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.planks), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.double_wooden_slab), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.wooden_slab), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.fence), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.oak_stairs), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.birch_stairs), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.spruce_stairs), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.jungle_stairs), 5, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.log), 5, 5);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.leaves), 30, 60);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.bookshelf), 30, 20);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.tnt), 15, 100);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.grass), 60, 100);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.wool), 30, 60);
		EGBlockList.blueFire.func_149842_a(getIdFromBlock(Blocks.vine), 15, 100);
	}

	@Deprecated // Use setFireInfo
    public void func_149842_a(int p_149842_1_, int p_149842_2_, int p_149842_3_)
    {
        this.setFireInfo((Block)Block.blockRegistry.getObjectById(p_149842_1_), p_149842_2_, p_149842_3_);
    }

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return 3;
	}

	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	public int tickRate(World par1World)
	{
		return 30;
	}

	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.getGameRules().getGameRuleBooleanValue("doFireTick"))
		{
			boolean flag = world.getBlock(x, y, z).isFireSource(world, x, y - 1, z, ForgeDirection.UP);
			if (!canPlaceBlockAt(world, x, y, z))
			{
				world.setBlockToAir(x, y, z);
			}
			if ((!flag) && (world.isRaining()) && ((world.canLightningStrikeAt(x, y, z)) || (world.canLightningStrikeAt(x - 1, y, z)) || (world.canLightningStrikeAt(x + 1, y, z)) || (world.canLightningStrikeAt(x, y, z - 1)) || (world.canLightningStrikeAt(x, y, z + 1))))
			{
				world.setBlockToAir(x, y, z);
			} else
			{
				int l = world.getBlockMetadata(x, y, z);
				if (l < 15)
				{
					world.setBlockMetadataWithNotify(x, y, z, l + rand.nextInt(3) / 2, 4);
				}
				world.scheduleBlockUpdate(x, y, z, this, tickRate(world) + rand.nextInt(10));
				if ((!flag) && (!canNeighborBurn(world, x, y, z)))
				{
					if ((!world.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) || (l > 3))
					{
						world.setBlockToAir(x, y, z);
					}
				} else if ((!flag) && (!canBlockCatchFire(world, x, y - 1, z, ForgeDirection.UP)) && (l == 15) && (rand.nextInt(4) == 0))
				{
					world.setBlockToAir(x, y, z);
				} else
				{
					boolean flag1 = world.isBlockHighHumidity(x, y, z);
					byte b0 = 0;
					if (flag1)
					{
						b0 = -50;
					}
					tryToCatchBlockOnFire(world, x + 1, y, z, 300 + b0, rand, l, ForgeDirection.WEST);
					tryToCatchBlockOnFire(world, x - 1, y, z, 300 + b0, rand, l, ForgeDirection.EAST);
					tryToCatchBlockOnFire(world, x, y - 1, z, 250 + b0, rand, l, ForgeDirection.UP);
					tryToCatchBlockOnFire(world, x, y + 1, z, 250 + b0, rand, l, ForgeDirection.DOWN);
					tryToCatchBlockOnFire(world, x, y, z - 1, 300 + b0, rand, l, ForgeDirection.SOUTH);
					tryToCatchBlockOnFire(world, x, y, z + 1, 300 + b0, rand, l, ForgeDirection.NORTH);
					for (int i1 = x - 1; i1 <= x + 1; i1++)
					{
						for (int j1 = z - 1; j1 <= z + 1; j1++)
						{
							for (int k1 = y - 1; k1 <= y + 4; k1++)
							{
								if ((i1 != x) || (k1 != y) || (j1 != z))
								{
									int l1 = 100;
									if (k1 > y + 1)
									{
										l1 += (k1 - (y + 1)) * 100;
									}
									int i2 = getChanceOfNeighborsEncouragingFire(world, i1, k1, j1);
									if (i2 > 0)
									{
										int j2 = (i2 + 40 + world.difficultySetting.getDifficultyId() * 7)/ (l + 30);
										if (flag1)
										{
											j2 /= 2;
										}
										if ((j2 > 0) && (rand.nextInt(l1) <= j2) && ((!world.isRaining()) || (!world.canLightningStrikeAt(i1, k1, j1))) && (!world.canLightningStrikeAt(i1 - 1, k1, z)) && (!world.canLightningStrikeAt(i1 + 1, k1, j1)) && (!world.canLightningStrikeAt(i1, k1, j1 - 1)) && (!world.canLightningStrikeAt(i1, k1, j1 + 1)))
										{
											int k2 = l + rand.nextInt(5) / 4;
											if (k2 > 15)
											{
												k2 = 15;
											}
											world.setBlock(i1, k1, j1,this, k2, 3);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean func_82506_l()
	{
		return true;
	}

	@Deprecated
	private void tryToCatchBlockOnFire(World par1World, int par2, int par3, int par4, int par5, Random par6Random, int par7)
	{
		tryToCatchBlockOnFire(par1World, par2, par3, par4, par5, par6Random, par7, ForgeDirection.UP);
	}

	private void tryToCatchBlockOnFire(World world, int x, int y,int z, int par5, Random rand, int par7, ForgeDirection face)
	{
		int j1 = world.getBlock(x, y, z).getFlammability(world, x, y, z, face);

		if (rand.nextInt(par5) < j1)
		{
			boolean flag = world.getBlock(x, y, z) == Blocks.tnt;
			if ((rand.nextInt(par7 + 10) < 5)&& (!world.canLightningStrikeAt(x, y, z)))
			{
				int k1 = par7 + rand.nextInt(5) / 4;
				if (k1 > 15)
				{
					k1 = 15;
				}
				world.setBlock(x, y, z, this, k1, 3);
			} else
			{
				world.setBlockToAir(x, y, z);
			}
			if (flag)
			{
				Blocks.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
			}
		}
	}

	private boolean canNeighborBurn(World par1World, int par2, int par3, int par4)
	{
		return (canBlockCatchFire(par1World, par2 + 1, par3, par4, ForgeDirection.WEST)) || (canBlockCatchFire(par1World, par2 - 1, par3, par4, ForgeDirection.EAST)) || (canBlockCatchFire(par1World, par2, par3 - 1, par4, ForgeDirection.UP)) || (canBlockCatchFire(par1World, par2, par3 + 1, par4, ForgeDirection.DOWN)) || (canBlockCatchFire(par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH)) || (canBlockCatchFire(par1World, par2, par3, par4 + 1, ForgeDirection.NORTH));
	}

	private int getChanceOfNeighborsEncouragingFire(World par1World, int par2, int par3, int par4)
	{
		byte b0 = 0;
		if (!par1World.isAirBlock(par2, par3, par4))
		{
			return 0;
		}
		int l = getChanceToEncourageFire(par1World, par2 + 1, par3, par4, b0, ForgeDirection.WEST);
		l = getChanceToEncourageFire(par1World, par2 - 1, par3, par4, l, ForgeDirection.EAST);
		l = getChanceToEncourageFire(par1World, par2, par3 - 1, par4, l, ForgeDirection.UP);
		l = getChanceToEncourageFire(par1World, par2, par3 + 1, par4, l, ForgeDirection.DOWN);
		l = getChanceToEncourageFire(par1World, par2, par3, par4 - 1, l, ForgeDirection.SOUTH);
		l = getChanceToEncourageFire(par1World, par2, par3, par4 + 1, l, ForgeDirection.NORTH);
		return l;
	}

	public boolean isCollidable()
	{
		return false;
	}

	@Deprecated
	public boolean canBlockCatchFire(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return canBlockCatchFire(par1IBlockAccess, par2, par3, par4, ForgeDirection.UP);
	}

	@Deprecated
	public int getChanceToEncourageFire(World par1World, int par2, int par3, int par4, int par5)
	{
		return getChanceToEncourageFire(par1World, par2, par3, par4, par5, ForgeDirection.UP);
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return (par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4)) || (canNeighborBurn(par1World, par2, par3, par4));
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if ((!par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4)) && (!canNeighborBurn(par1World, par2, par3, par4)))
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if ((par1World.getBlock(par2, par3 - 1, par4) != Blocks.ice) || (!EGBlockList.glaciaPortal.tryToCreatePortal(par1World, par2, par3, par4)))
		{
			if ((!par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4)) && (!canNeighborBurn(par1World, par2, par3, par4)))
			{
				par1World.setBlockToAir(par2, par3, par4);
			} else
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World) + par1World.rand.nextInt(10));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par5Random.nextInt(24) == 0)
		{
			par1World.playSound(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, "fire.fire", 1.0F + par5Random.nextFloat(), par5Random.nextFloat() * 0.7F + 0.3F, false);
		}
		if ((!par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4)) && (!EGBlockList.blueFire.canBlockCatchFire(par1World, par2, par3 - 1, par4, ForgeDirection.UP)))
		{
			if (EGBlockList.blueFire.canBlockCatchFire(par1World, par2 - 1, par3, par4, ForgeDirection.EAST))
			{
				for (int l = 0; l < 2; l++)
				{
					float f = par2 + par5Random.nextFloat() * 0.1F;
					float f1 = par3 + par5Random.nextFloat();
					float f2 = par4 + par5Random.nextFloat();
					par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
				}
			}
			if (EGBlockList.blueFire.canBlockCatchFire(par1World, par2 + 1, par3, par4, ForgeDirection.WEST))
			{
				for (int l = 0; l < 2; l++)
				{
					float f = par2 + 1 - par5Random.nextFloat() * 0.1F;
					float f1 = par3 + par5Random.nextFloat();
					float f2 = par4 + par5Random.nextFloat();
					par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
				}
			}
			if (EGBlockList.blueFire.canBlockCatchFire(par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH))
			{
				for (int l = 0; l < 2; l++)
				{
					float f = par2 + par5Random.nextFloat();
					float f1 = par3 + par5Random.nextFloat();
					float f2 = par4 + par5Random.nextFloat() * 0.1F;
					par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
				}
			}
			if (EGBlockList.blueFire.canBlockCatchFire(par1World, par2, par3, par4 + 1, ForgeDirection.NORTH))
			{
				for (int l = 0; l < 2; l++)
				{
					float f = par2 + par5Random.nextFloat();
					float f1 = par3 + par5Random.nextFloat();
					float f2 = par4 + 1 - par5Random.nextFloat() * 0.1F;
					par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
				}
			}
			if (EGBlockList.blueFire.canBlockCatchFire(par1World, par2, par3 + 1, par4, ForgeDirection.DOWN))
			{
				for (int l = 0; l < 2; l++)
				{
					float f = par2 + par5Random.nextFloat();
					float f1 = par3 + 1 - par5Random.nextFloat() * 0.1F;
					float f2 = par4 + par5Random.nextFloat();
					par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
				}
			}
		} else
		{
			for (int l = 0; l < 3; l++)
			{
				float f = par2 + par5Random.nextFloat();
				float f1 = par3 + par5Random.nextFloat() * 0.5F + 0.5F;
				float f2 = par4 + par5Random.nextFloat();
				par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Deprecated
	public boolean canBlockCatchFire(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
        return canCatchFire(world, x, y, z, UP);
	}
	
	@Deprecated
	public int func_149846_a(World world, int x, int y, int z, int oldChance, ForgeDirection face)
	{
        return getChanceToEncourageFire(world, x, y, z, oldChance, UP);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.iconArray = new IIcon[]{par1IconRegister.registerIcon("ere_geologique:Bluefire_0"), par1IconRegister.registerIcon("ere_geologique:Bluefire_1")};
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_94438_c(int par1)
	{
		return this.iconArray[par1];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		return this.iconArray[0];
	}
	
	private static class FireInfo
    {
        private int encouragement = 0;
        private int flammibility = 0;
    }
    private IdentityHashMap<Block, FireInfo> blockInfo = Maps.newIdentityHashMap();

    public void setFireInfo(Block block, int encouragement, int flammibility)
    {
        if (block == Blocks.air) throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        int id = Block.getIdFromBlock(block);
        this.chanceToEncourageFire[id] = encouragement;
        this.abilityToCatchFire[id] = flammibility;

        FireInfo info = getInfo(block, true);
        info.encouragement = encouragement;
        info.flammibility = flammibility;
    }

    private FireInfo getInfo(Block block, boolean garentee)
    {
        FireInfo ret = blockInfo.get(block);
        if (ret == null && garentee)
        {
            ret = new FireInfo();
            blockInfo.put(block, ret);
        }
        return ret;
    }

    public void rebuildFireInfo()
    {
        for (int x = 0; x < 4096; x++)
        {
            //If we care.. we could detect changes in here and make sure we keep them, however 
            //it's my thinking that anyone who hacks into the private variables should DIAF and we don't care about them.
            chanceToEncourageFire[x] = 0;
            abilityToCatchFire[x] = 0;
        }

        for (Entry<Block, FireInfo> e : blockInfo.entrySet())
        {
            int id = Block.getIdFromBlock(e.getKey());
            if (id >= 0 && id < 4096)
            {
                chanceToEncourageFire[id] = e.getValue().encouragement;
                abilityToCatchFire[id] = e.getValue().flammibility;
            }
        }
    }

    public int getFlammability(Block block)
    {
        int id = Block.getIdFromBlock(block);
        return id >= 0 && id < 4096 ? abilityToCatchFire[id] : 0;
    }

    public int getEncouragement(Block block)
    {
        int id = Block.getIdFromBlock(block);
        return id >= 0 && id < 4096 ? chanceToEncourageFire[id] : 0;
    }

    /**
     * Side sensitive version that calls the block function.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param face The side the fire is coming from
     * @return True if the face can catch fire.
     */
    public boolean canCatchFire(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return world.getBlock(x, y, z).isFlammable(world, x, y, z, face);
    }

    /**
     * Side sensitive version that calls the block function.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param oldChance The previous maximum chance.
     * @param face The side the fire is coming from
     * @return The chance of the block catching fire, or oldChance if it is higher
     */
    public int getChanceToEncourageFire(IBlockAccess world, int x, int y, int z, int oldChance, ForgeDirection face)
    {
        int newChance = world.getBlock(x, y, z).getFireSpreadSpeed(world, x, y, z, face);
        return (newChance > oldChance ? newChance : oldChance);
    }
}