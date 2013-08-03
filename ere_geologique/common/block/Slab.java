package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EGCreativeTab;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;

public class Slab extends BlockHalfSlab
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	
	public Slab(int par1, boolean par2)
	 
	{
	super(par1, par2, Material.wood);
	this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return EGBlockList.Plank.getIcon(par1, par2 & 7);
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
	{
	return EGProperties.SlabID;
	}
	
	protected ItemStack createStackedBlock(int par1)
	{
	return new ItemStack(EGProperties.SlabID, 2, par1 & 7);
	}
	
	public String getFullSlabName(int par1)
	{
		if (par1 < 0 || par1 >= woodType.length)
		{
			par1 = 0;
		}
		
		return super.getUnlocalizedName() + "." + woodType[par1];
	}
	
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		if (par1 != EGProperties.SlabID)
		{
			
			for (int j = 0; j < 5; ++j)
			{
			par3List.add(new ItemStack(par1, 1, j));
			}
		}
	}
	 
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {}	 
}
