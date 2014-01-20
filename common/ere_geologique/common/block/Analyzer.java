package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.tileentity.TileEntityAnalyzer;

public class Analyzer extends BlockContainer
{
	private Random furnaceRand = new Random();
	private IIcon top, front, frontActive;

	public Analyzer(int id)
	{
		super(id, Material.iron);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.field_149761_L = iconRegister.registerIcon("ere_geologique:Analyser_Sides");
		this.top = iconRegister.registerIcon("ere_geologique:Analyser_Top");
		this.front = iconRegister.registerIcon("ere_geologique:Analyser_Front_Idle");
		this.frontActive = iconRegister.registerIcon("ere_geologique:Analyser_Front_Active");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntity te = blockAccess.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityAnalyzer)
		{
			TileEntityAnalyzer analyzer = (TileEntityAnalyzer)te;
			int direction = analyzer.getDirection();
			if(analyzer.isActive())
			{
				return side == 1 ? this.top : (side == 0 ? this.field_149761_L : (direction == 2 && side == 2 ? this.frontActive : (direction == 3 && side == 5 ? this.frontActive : (direction == 0 && side == 3 ? this.frontActive : (direction == 1 && side == 4 ? this.frontActive : this.field_149761_L)))));
			}
			else
			{
				return side == 1 ? this.top : (side == 0 ? this.field_149761_L : (direction == 2 && side == 2 ? this.front : (direction == 3 && side == 5 ? this.front : (direction == 0 && side == 3 ? this.front : (direction == 1 && side == 4 ? this.front : this.field_149761_L)))));
			}
		}
		return this.getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return side == 1 ? this.top : side == 3 ? this.front : this.field_149761_L;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			player.openGui(EreGeologique.Instance, 2, world, x, y, z);
			return true;
		}
		return false;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityAnalyzer)
		{
			((TileEntityAnalyzer)te).setDirection(direction);
			world.markBlockForUpdate(x, y, z);
		}
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityAnalyzer();
	}

	public void breakBlock(World world, int x, int y, int z, int var5, int var6)
	{
		TileEntityAnalyzer analyzer = (TileEntityAnalyzer)world.getBlockTileEntity(x, y, z);

		if(analyzer != null)
		{
			for(int slotId = 0; slotId < analyzer.getSizeInventory(); ++slotId)
			{
				ItemStack stack = analyzer.getStackInSlot(slotId);

				if(stack != null)
				{
					float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

					while(stack.stackSize > 0)
					{
						int var13 = this.furnaceRand.nextInt(21) + 10;

						if(var13 > stack.stackSize)
						{
							var13 = stack.stackSize;
						}

						stack.stackSize -= var13;
						EntityItem entityItem = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(stack.itemID, var13, stack.getItemDamage()));

						if(stack.hasTagCompound())
						{
							entityItem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
						}

						float var15 = 0.05F;
						entityItem.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
						entityItem.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
						entityItem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
		}

		super.breakBlock(world, x, y, z, var5, var6);
	}

	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory)world.func_147438_o(x, y, z));
	}

	@Override
	public TileEntity func_149915_a(World var1, int var2) {
		return null;
	}
}