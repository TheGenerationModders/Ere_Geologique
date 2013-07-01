package ere_geologique.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class EreGeologiquefuel implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.itemID == EreGeologique.PrehistoriqueCoalID)
			return 3200;
		else
			return 0;
	}

}
