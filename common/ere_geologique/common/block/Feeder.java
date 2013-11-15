package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class Feeder extends BlockContainer
{
	private Random furnaceRand = new Random();
	private Icon top1;// None
	private Icon top2;// Herb
	private Icon top3;// Carn
	private Icon top4;// Both
	private Icon front1;
	private Icon front2;
	private Icon front3;
	private Icon front4;
	private Icon bottom;

	public Feeder(int id)
	{
		super(id, Material.rock);
	}

	public int idDropped(int var1, Random var2, int var3)
	{
		return EGBlockList.FeederActive.blockID;
	}

	public int getRenderType()
	{
		return 2303;
	}

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

			if(l == 3 - 2)
			{
				world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 5 - 2)
			{
				world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 4 - 2)
			{
				world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
			}
			else if(l == 2 - 2)
			{
				world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("ere_geologique:Feeder_Sides");
		this.bottom = iconRegister.registerIcon("ere_geologique:Feeder_Bottom");
		this.top1 = iconRegister.registerIcon("ere_geologique:Feeder_Top1");
		this.top2 = iconRegister.registerIcon("ere_geologique:Feeder_Top2");
		this.top3 = iconRegister.registerIcon("ere_geologique:Feeder_Top3");
		this.top4 = iconRegister.registerIcon("ere_geologique:Feeder_Top4");
		this.front1 = iconRegister.registerIcon("ere_geologique:Feeder_Front1");
		this.front2 = iconRegister.registerIcon("ere_geologique:Feeder_Front2");
		this.front3 = iconRegister.registerIcon("ere_geologique:Feeder_Front3");
		this.front4 = iconRegister.registerIcon("ere_geologique:Feeder_Front4");
	}

	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntity te = blockAccess.getBlockTileEntity(x, y, z);
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
				return side == 1 ? top2 : (direction == 2 && side == 2 ? front2 : (direction == 3 && side == 5 ? front2 : (direction == 0 && side == 3 ? front2 : (direction == 1 && side == 4 ? front2 : this.blockIcon))));
			}
			else if(feeder.getCurreentVeg() == 0 && feeder.getCurrentMeat() > 0)
			{					
				return side == 1 ? top3 : (direction == 2 && side == 2 ? front3 : (direction == 3 && side == 5 ? front3 : (direction == 0 && side == 3 ? front3 : (direction == 1 && side == 4 ? front3 : this.blockIcon))));
			}
			else if(feeder.getCurreentVeg() > 0 && feeder.getCurrentMeat() > 0)
			{
				return side == 1 ? top4 : (direction == 2 && side == 2 ? front4 : (direction == 3 && side == 5 ? front4 : (direction == 0 && side == 3 ? front4 : (direction == 1 && side == 4 ? front4 : this.blockIcon))));
			}
			else
			{
				return side == 1 ? top1 : (direction == 2 && side == 2 ? front1 : (direction == 3 && side == 5 ? front1 : (direction == 0 && side == 3 ? front1 : (direction == 1 && side == 4 ? front1 : this.blockIcon))));
			}
		}
		return this.getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		return side == 0 ? this.bottom : side == 1 ? this.top1 : side == 3 ? this.front1 : this.blockIcon;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			player.openGui(EreGeologique.Instance, 0, world, x, y, z);
			return true;
		}
	}

	/*public static void updateFurnaceBlockState(boolean herb, boolean carn, World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == EGBlockList.FeederIdle.blockID)// won't be used anymore
			world.setBlock(x, y, z, EGBlockList.FeederActive.blockID, 0, 2);
		int l = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
		
	}*/

	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityFeeder();
	}

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityFeeder)
		{
			((TileEntityFeeder)te).setDirection(direction);
			world.markBlockForUpdate(x, y, z);
		}
	}

	public void breakBlock(World world, int x, int y, int z, int var5, int var6)
	{
		TileEntityFeeder feeder = (TileEntityFeeder)world.getBlockTileEntity(x, y, z);

		if(feeder != null)
		{
			for(int slotId = 0; slotId < feeder.getSizeInventory(); ++slotId)
			{
				ItemStack stack = feeder.getStackInSlot(slotId);

				if(stack != null)
				{
					float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

					while(stack.stackSize > 0)
					{
						int quantity = this.furnaceRand.nextInt(21) + 10;

						if(quantity > stack.stackSize)
						{
							quantity = stack.stackSize;
						}

						stack.stackSize -= quantity;
						EntityItem item = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(stack.itemID, quantity, stack.getItemDamage()));
						float var15 = 0.05F;
						item.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
						item.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
						item.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
						world.spawnEntityInWorld(item);
					}
				}
			}
		}
		super.breakBlock(world, x, y, z, var5, var6);
	}
}