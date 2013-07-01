package ere_geologique.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class PrehistoriqueBlockIron extends Block
{
	
	public PrehistoriqueBlockIron(int par1, int par2)
	{
		super(par1, Material.iron);
		this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
}
