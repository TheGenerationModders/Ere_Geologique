package ere_geologique.common.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class EGCreativeTab
{
	public static CreativeTabs EGCreativeTabBlock;
	public static CreativeTabs EGCreativeTabItem;
	public static CreativeTabs EGCreativeTabFood;
	
	public static void loadCreativeTab()
	{
		EGCreativeTabBlock = new EGCreativeTabBlock("EGCreativeTabBlock");
		EGCreativeTabItem = new EGCreativeTabItem("EGCreativeTabItem");
		EGCreativeTabFood = new EGCreativeTabFood("EGCreativeTabFood");
	}
}