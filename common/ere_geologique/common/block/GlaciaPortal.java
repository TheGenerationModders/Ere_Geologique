package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.dimension.TeleporterGlacia;

public class GlaciaPortal extends BlockBreakable
{
	public GlaciaPortal()
	{
		super("ere_geologique:GlaciaPortal", Material.portal, false);
		this.setTickRandomly(true);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		super.updateTick(world, x, y, z, rand);
		if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("DoMobSpawning") && rand.nextInt(2000) < world.difficultySetting.getDifficultyId())
		{
			int l;
			for (l = y; !world.doesBlockHaveSolidTopSurface(world, x, l, z) && l > 0; --l)
			{
				;
			}
			if (l > 0 && !world.doesBlockHaveSolidTopSurface(world, x, l + 1, z))
			{
				Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, (double) x + 0.5D, (double) l + 1.1D, (double) z + 0.5D);
				if (entity != null)
				{
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		float f;
		float f1;
		if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
		{
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		} else
		{
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}

	/**
	 * Is this block (a) opaque and (B) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Checks to see if this location is valid to create a portal and will
	 * return True if it does. Args: world, x, y, z
	 */
	public boolean tryToCreatePortal(World world, int x, int y, int z)
	{
		byte b0 = 0;
		byte b1 = 0;
		if (world.getBlock(x - 1, y, z) == Blocks.ice || world.getBlock(x + 1, y, z) == Blocks.ice)
		{
			b0 = 1;
		}
		if (world.getBlock(x, y, z - 1) == Blocks.ice || world.getBlock(x, y, z + 1) == Blocks.ice)
		{
			b1 = 1;
		}
		if (b0 == b1)
		{
			return false;
		} else
		{
			if (world.getBlock(x - b0, y, z - b1) == 0)
			{
				x -= b0;
				z -= b1;
			}
			int l;
			int i1;
			for (l = -1; l <= 2; ++l)
			{
				for (i1 = -1; i1 <= 3; ++i1)
				{
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
					if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
					{
						int j1 = world.getBlock(x + b0 * l, y + i1, z + b1 * l);
						if (flag)
						{
							if (j1 != Blocks.ice)
							{
								return false;
							}
						} else if (j1 != 0 && j1 != EGBlockList.blueFire)
						{
							return false;
						}
					}
				}
			}
			for (l = 0; l < 2; ++l)
			{
				for (i1 = 0; i1 < 3; ++i1)
				{
					world.setBlock(x + b0 * l, y + i1, z + b1 * l, EGBlockList.glaciaPortal, 0, 2);
				}
			}
			return true;
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor blockID
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
	{
		byte b0 = 0;
		byte b1 = 1;
		if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this)
		{
			b0 = 1;
			b1 = 0;
		}
		int i1;
		for (i1 = y; world.getBlock(x, i1 - 1, z) == this; --i1)
		{
			;
		}
		if (world.getBlock(x, i1 - 1, z) != Blocks.ice)
		{
			world.setBlockToAir(x, y, z);
		} else {
			int j1;
			for (j1 = 1; j1 < 4 && world.getBlock(x, i1 + j1, z) == this; ++j1)
			{
				;
			}
			if (j1 == 3 && world.getBlock(x, i1 + j1, z) == Blocks.ice)
			{
				boolean flag = world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this;
				boolean flag1 = world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this;
				if (flag && flag1)
				{
					world.setBlockToAir(x, y, z);
				} else
				{
					if ((world.getBlock(x + b0, y, z + b1) != Blocks.ice || world.getBlock(x - b0, y, z - b1) != this) && (world.getBlock(x - b0, y, z - b1) != Blocks.ice || world.getBlock(x + b0, y, z + b1) != this))
					{
						world.setBlockToAir(x, y, z);
					}
				}
			} else
			{
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (blockAccess.getBlock(x, y, z) == this)
		{
			return false;
		} else
		{
			boolean flag = blockAccess.getBlock(x - 1, y, z) == this && blockAccess.getBlock(x - 2, y, z) != this;
			boolean flag1 = blockAccess.getBlock(x + 1, y, z) == this && blockAccess.getBlock(x + 2, y, z) != this;
			boolean flag2 = blockAccess.getBlock(x, y, z - 1) == this && blockAccess.getBlock(x, y, z - 2) != this;
			boolean flag3 = blockAccess.getBlock(x, y, z + 1) == this && blockAccess.getBlock(x, y, z + 2) != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
			if (thePlayer.timeUntilPortal > 0)
			{
				thePlayer.timeUntilPortal = 10;
			} else if (thePlayer.dimension != EGProperties.glaciaID)
			{
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, EGProperties.glaciaID, new TeleporterGlacia(thePlayer.mcServer.worldServerForDimension(EGProperties.glaciaID)));
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterGlacia(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 1;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(100) == 0)
		{
			world.playSound((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}
		for (int l = 0; l < 4; ++l)
		{
			double d0 = (double) ((float) x + rand.nextFloat());
			double d1 = (double) ((float) y + rand.nextFloat());
			double d2 = (double) ((float) z + rand.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
			{
				d0 = (double) x + 0.5D + 0.25D * (double) i1;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) i1);
			} else
			{
				d2 = (double) z + 0.5D + 0.25D * (double) i1;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) i1);
			}
			world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return 0;
	}
}