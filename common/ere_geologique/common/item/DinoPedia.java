package ere_geologique.common.item;

import net.minecraft.item.Item;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class DinoPedia extends Item
{
	public DinoPedia(int id)
	{
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	}
}