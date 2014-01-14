package ere_geologique.common.item;

import ere_geologique.common.creativetabs.EGCreativeTab;
import net.minecraft.item.Item;

public class DinoPedia extends Item
{
	public DinoPedia(int id)
	{
		super(id);
		this.setMaxStackSize(1);
		this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	}
}