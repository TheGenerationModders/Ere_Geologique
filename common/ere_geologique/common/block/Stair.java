package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Stair extends BlockStairs
{	
	public Stair(Block block, int metadata)
    {
        super(block, metadata);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
        this.setLightOpacity(0);
    }
}