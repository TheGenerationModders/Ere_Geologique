package ere_geologique.common.block;

import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class Dirt extends Block
{

	public Dirt(int par1, int par2)
	{
		super(par1, Material.grass);
		this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
	}

	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }

}
