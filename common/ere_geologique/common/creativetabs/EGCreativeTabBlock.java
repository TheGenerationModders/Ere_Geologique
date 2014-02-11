package ere_geologique.common.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ere_geologique.common.block.EGBlockList;

public class EGCreativeTabBlock extends CreativeTabs
{
	 public EGCreativeTabBlock(String name)
	 {
	        super(name);
	 }

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(EGBlockList.wood);
	}
}