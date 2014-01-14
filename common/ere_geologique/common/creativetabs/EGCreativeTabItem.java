package ere_geologique.common.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ere_geologique.common.item.EGItemList;

public class EGCreativeTabItem extends CreativeTabs
{
	 public EGCreativeTabItem(String name)
	 {
	        super(name);
	 }

	@Override
	public Item getTabIconItem()
	{
		return EGItemList.dinoPedia;
	}
}