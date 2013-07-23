package ere_geologique.common.block;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
  

public class Slab extends BlockHalfSlab
{
	public Slab(int par1, boolean par2)
	 
	{
	super(par1, par2, Material.wood);
	this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
	setLightOpacity(0);
	}
	 
	 
	 
	 
	public int idDropped(int par1, Random par2Random, int par3)
	{
	return EreGeologique.SlabID;
	}
	 
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
	if(par1World.getBlockId(par2, par3 - 1, par4) == EreGeologique.SlabID)
	{
	par1World.setBlock(par2, par3, par4, 0);
	par1World.setBlock(par2, par3 - 1, par4, EreGeologique.DoubleSlabID);
	}
	}
	 
	protected ItemStack createStackedBlock(int par1)
	{
	return new ItemStack(EreGeologique.SlabID, 2, par1 & 7);
	}
	 
	public String getFullSlabName(int var1)
	{
	return null;
	}
	 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
	 
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSide(int i){
	return 0;
	}
}
