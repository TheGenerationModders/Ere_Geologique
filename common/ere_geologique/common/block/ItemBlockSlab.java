package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockSlab extends ItemBlock
{
	private final boolean isFullBlock;
	private final BlockSlab theHalfSlab;
	private final BlockSlab doubleSlab;

	public ItemBlockSlab(Block block, BlockSlab blockSlab, BlockSlab blockSlab2, boolean b)
	{
		super(block);
		this.theHalfSlab = blockSlab;
		this.doubleSlab = blockSlab2;
	    this.isFullBlock = b;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int metadata)
	{
		return Block.func_149634_a(this).func_149691_a(2, metadata);
	}

	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		return ((Slab)theHalfSlab).func_150002_b(stack.getItemDamage());
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		if(this.isFullBlock)
		{
			return super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
		}
		else if(stack.stackSize == 0)
		{
			return false;
		}
		else if(!player.canPlayerEdit(x, y, z, side, stack))
		{
			return false;
		}
		else
		{
			Block block = world.func_147439_a(x, y, z);
			int j1 = world.getBlockMetadata(x, y, z);
			int k1 = j1 & 7;
			boolean flag = (j1 & 8) != 0;

			if((side == 1 && !flag || side == 0 && flag) && block == this.theHalfSlab && k1 == stack.getItemDamage())
			{
				if(world.checkNoEntityCollision(this.doubleSlab.func_149668_a(world, x, y, z)) && world.func_147465_d(x, y, z, this.doubleSlab, k1, 3))
				{
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.doubleSlab.field_149762_H.func_150496_b(), (this.doubleSlab.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.doubleSlab.field_149762_H.func_150494_d() * 0.8F);
					--stack.stackSize;
				}
				return true;
			}
			else
			{
				return this.placeDoubleSlabFromTop(stack, player, world, x, y, z, side) ? true : super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack)
	{
		int i1 = x;
		int j1 = y;
		int k1 = z;
		Block block = world.func_147439_a(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		int j2 = meta & 7;
		boolean flag = (meta & 8) != 0;

		if((side == 1 && !flag || side == 0 && flag) && block == this.theHalfSlab && j2 == stack.getItemDamage())
		{
			return true;
		}
		else
		{
			if(side == 0)
			{
				--y;
			}

			if(side == 1)
			{
				++y;
			}

			if(side == 2)
			{
				--z;
			}

			if(side == 3)
			{
				++z;
			}

			if(side == 4)
			{
				--x;
			}

			if(side == 5)
			{
				++x;
			}

			block = world.func_147439_a(x, y, z);
			meta = world.getBlockMetadata(x, y, z);
			j2 = meta & 7;
			flag = (meta & 8) != 0;
			return block == this.theHalfSlab && j2 == stack.getItemDamage() ? true : super.func_150936_a(world, i1, j1, k1, side, player, stack);
		}
	}

	private boolean placeDoubleSlabFromTop(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side)
	{
		if(side == 0)
		{
			--y;
		}

		if(side == 1)
		{
			++y;
		}

		if(side == 2)
		{
			--z;
		}

		if(side == 3)
		{
			++z;
		}

		if(side == 4)
		{
			--x;
		}

		if(side == 5)
		{
			++x;
		}

		Block block = world.func_147439_a(x, y, z);
		int j1 = world.getBlockMetadata(x, y, z);
		int k1 = j1 & 7;

		if(block == this.theHalfSlab && k1 == stack.getItemDamage())
		{
			if(world.checkNoEntityCollision(this.doubleSlab.func_149668_a(world, x, y, z)) && world.func_147465_d(x, y, z, this.doubleSlab, k1, 3))
			{
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.doubleSlab.field_149762_H.func_150496_b(), (this.doubleSlab.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.doubleSlab.field_149762_H.func_150494_d() * 0.8F);
				--stack.stackSize;
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}