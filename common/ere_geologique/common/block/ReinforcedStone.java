package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class ReinforcedStone extends Block
{
	public ReinforcedStone()
	{
		super(Material.field_151576_e);
		this.func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	}
}