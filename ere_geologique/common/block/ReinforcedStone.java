package ere_geologique.common.block;

import ere_geologique.common.creativetabs.EGCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ReinforcedStone extends Block
{

	public ReinforcedStone(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}

}