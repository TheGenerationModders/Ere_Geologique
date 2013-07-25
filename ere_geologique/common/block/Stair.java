package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import ere_geologique.common.EGCreativeTab;

public class Stair extends BlockStairs
{
	public Stair(int par1, Block par2Block, int par2)
    {
        super(par1, par2Block, par2);
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
        this.setLightOpacity(0);
    }
 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
}
