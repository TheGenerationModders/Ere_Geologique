package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class Feeder extends BlockContainer
{
	private Random rand = new Random();
	private IIcon topEmply1, topEmply2, topEmply3, topEmply4, topHerb1, topHerb2, topHerb3, topHerb4, topCan1, topCan2, topCan3, topCan4;
	private IIcon topBoth1, topBoth2, topBoth3, topBoth4;// Both
	private IIcon front1;
	private IIcon front2;
	private IIcon front3;
	private IIcon front4;
	private IIcon bottom;

	public Feeder()
	{
		super(Material.rock);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		int l = world.getBlockMetadata(x, y, z);
		if(random.nextInt(25) == 0)
		{
			float f = (float)x + 0.5F;
			float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float f2 = (float)z + 0.5F;
			float f3 = 0.52F;
			float f4 = random.nextFloat() * 0.6F - 0.3F;

			if(l == 1)
			{
				world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 3)
			{
				world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 2)
			{
				world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 0)
			{
				world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("ere_geologique:Feeder_Sides");
		this.bottom = iconRegister.registerIcon("ere_geologique:Feeder_Bottom");
		this.topEmply1 = iconRegister.registerIcon("ere_geologique:Feeder_top_empty_1");
		this.topEmply2 = iconRegister.registerIcon("ere_geologique:Feeder_top_empty_2");
		this.topEmply3 = iconRegister.registerIcon("ere_geologique:Feeder_top_empty_3");
		this.topEmply4 = iconRegister.registerIcon("ere_geologique:Feeder_top_empty_4");
		this.topHerb1 = iconRegister.registerIcon("ere_geologique:Feeder_top_herb_1");
		this.topHerb2 = iconRegister.registerIcon("ere_geologique:Feeder_top_herb_2");
		this.topHerb3 = iconRegister.registerIcon("ere_geologique:Feeder_top_herb_3");
		this.topHerb4 = iconRegister.registerIcon("ere_geologique:Feeder_top_herb_4");
		this.topCan1 = iconRegister.registerIcon("ere_geologique:Feeder_top_carn_1");
		this.topCan2 = iconRegister.registerIcon("ere_geologique:Feeder_top_carn_2");
		this.topCan3 = iconRegister.registerIcon("ere_geologique:Feeder_top_carn_3");
		this.topCan4 = iconRegister.registerIcon("ere_geologique:Feeder_top_carn_4");
		this.topBoth1 = iconRegister.registerIcon("ere_geologique:Feeder_top_both_1");
		this.topBoth2 = iconRegister.registerIcon("ere_geologique:Feeder_top_both_2");
		this.topBoth3 = iconRegister.registerIcon("ere_geologique:Feeder_top_both_3");
		this.topBoth4 = iconRegister.registerIcon("ere_geologique:Feeder_top_both_4");
		this.front1 = iconRegister.registerIcon("ere_geologique:Feeder_Front1");
		this.front2 = iconRegister.registerIcon("ere_geologique:Feeder_Front2");
		this.front3 = iconRegister.registerIcon("ere_geologique:Feeder_Front3");
		this.front4 = iconRegister.registerIcon("ere_geologique:Feeder_Front4");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntity te = blockAccess.getTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityFeeder)
		{
			TileEntityFeeder feeder = (TileEntityFeeder)te;
			int direction = feeder.getDirection();
			if(side == 0)
			{
				return this.bottom;
			}
			if(feeder.getCurreentVeg() > 0 && feeder.getCurrentMeat() == 0)
			{
				return side == 1 ? (direction == 0 ? topHerb1 : direction == 1 ? topHerb2 : direction == 2 ? topHerb3 : topHerb4) : (direction == 2 && side == 2 ? front2 : (direction == 3 && side == 5 ? front2 : (direction == 0 && side == 3 ? front2 : (direction == 1 && side == 4 ? front2 : this.blockIcon))));
			}
			else if(feeder.getCurreentVeg() == 0 && feeder.getCurrentMeat() > 0)
			{					
				return side == 1 ? (direction == 0 ? topCan1 : direction == 1 ? topCan2 : direction == 2 ? topCan3 : topCan4) : (direction == 2 && side == 2 ? front3 : (direction == 3 && side == 5 ? front3 : (direction == 0 && side == 3 ? front3 : (direction == 1 && side == 4 ? front3 : this.blockIcon))));
			}
			else if(feeder.getCurreentVeg() > 0 && feeder.getCurrentMeat() > 0)
			{
				return side == 1 ? (direction == 0 ? topBoth1 : direction == 1 ? topBoth2 : direction == 2 ? topBoth3 : topBoth4) : (direction == 2 && side == 2 ? front4 : (direction == 3 && side == 5 ? front4 : (direction == 0 && side == 3 ? front4 : (direction == 1 && side == 4 ? front4 : this.blockIcon))));
			}
			else
			{
				return side == 1 ? (direction == 0 ? topEmply1 : direction == 1 ? topEmply2 : direction == 2 ? topEmply3 : topEmply4) : (direction == 2 && side == 2 ? front1 : (direction == 3 && side == 5 ? front1 : (direction == 0 && side == 3 ? front1 : (direction == 1 && side == 4 ? front1 : this.blockIcon))));
			}
		}
		return this.getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return side == 0 ? this.bottom : side == 1 ? this.topEmply1 : side == 3 ? this.front1 : this.blockIcon;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityFeeder tileEntityFeeder = (TileEntityFeeder)world.getTileEntity(x, y, z);
			if(tileEntityFeeder != null)
			{
				player.openGui(EreGeologique.instance, 0, world, x, y, z);
			}
			return true;
		}
	}

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityFeeder)
		{
			((TileEntityFeeder)te).setDirection(direction);
			world.markBlockForUpdate(x, y, z);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int var6)
	{
		TileEntityFeeder feeder = (TileEntityFeeder)world.getTileEntity(x, y, z);

		if(feeder != null)
		{
			for(int slotId = 0; slotId < feeder.getSizeInventory(); ++slotId)
			{
				ItemStack stack = feeder.getStackInSlot(slotId);

				if(stack != null)
				{
					float var10 = this.rand.nextFloat() * 0.8F + 0.1F;
					float var11 = this.rand.nextFloat() * 0.8F + 0.1F;
					float var12 = this.rand.nextFloat() * 0.8F + 0.1F;

					while(stack.stackSize > 0)
					{
						int quantity = this.rand.nextInt(21) + 10;

						if(quantity > stack.stackSize)
						{
							quantity = stack.stackSize;
						}

						stack.stackSize -= quantity;
						EntityItem item = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(stack.getItem(), quantity, stack.getItemDamage()));
						float var15 = 0.05F;
						item.motionX = (double)((float)this.rand.nextGaussian() * var15);
						item.motionY = (double)((float)this.rand.nextGaussian() * var15 + 0.2F);
						item.motionZ = (double)((float)this.rand.nextGaussian() * var15);
						world.spawnEntityInWorld(item);
					}
				}
			}
		}
		super.breakBlock(world, x, y, z, block, var6);
	}

	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityFeeder();
	}
}