package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import ere_geologique.common.EGCreativeTab;

public class PrehistoriqueBlockIron extends Block
{
	
	public PrehistoriqueBlockIron(int par1, int par2)
	{
		super(par1, Material.iron);
		this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
}
