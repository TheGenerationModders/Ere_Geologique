package ere_geologique.common.item;

import net.minecraft.item.Item;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class IvoryIngot extends Item
{
	public IvoryIngot(int id)
	{
		super(id);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	}
}