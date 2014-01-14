package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Slab extends BlockSlab
{
	public static final String[] woodType = new String[] { "fougere", "cycas", "araucarias", "metasequoias", "ginkgos" };

	public Slab(int id, boolean isDouble)
	{
		super(id, isDouble, Material.wood);
		if(!this.isDoubleSlab)
		{
			this.setLightOpacity(0);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private static boolean isBlockSingleSlab(int id)
	{
		return id == EGBlockList.slab.blockID;
	}
	
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z)
	{
		return isBlockSingleSlab(this.blockID) ? this.blockID : EGBlockList.doubleSlab.blockID;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return EGBlockList.plank.getIcon(side, metadata & 7);
	}

	public int idDropped(int id, Random rand, int fortune)
	{
		return EGBlockList.slab.blockID;
	}

	protected ItemStack createStackedBlock(int metadata)
	{
		return new ItemStack(EGBlockList.slab.blockID, 2, metadata & 7);
	}

	public String getFullSlabName(int metadata)
	{
		if (metadata < 0 || metadata >= woodType.length)
		{
			metadata = 0;
		}

		return super.getUnlocalizedName() + "." + woodType[metadata];
	}

	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
	{
		if (id != EGBlockList.doubleSlab.blockID)
		{
			for (int i = 0; i < woodType.length; i++)
			{
				list.add(new ItemStack(id, 1, i));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {}
}