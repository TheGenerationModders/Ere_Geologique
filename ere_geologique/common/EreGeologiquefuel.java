package ere_geologique.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import ere_geologique.common.config.EGProperties;

public class EreGeologiquefuel implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.itemID == EGProperties.PrehistoriqueCoalID)
			return 3200;
		else
			return 0;
	}

}
