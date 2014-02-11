package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class ReinforcedStone extends Block
{
	public ReinforcedStone()
	{
		super(Material.rock);
		this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	}
}