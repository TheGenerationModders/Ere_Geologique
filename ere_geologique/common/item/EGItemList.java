package ere_geologique.common.item;

import net.minecraft.item.Item;
import ere_geologique.common.config.EGProperties;

public class EGItemList
{
	public static Item IvoryNugget;
	public static Item IvoryIngot;
	public static Item IvoryGear;
	
	public static void loadItem()
	{
	       IvoryIngot = new IvoryIngot(EGProperties.IvoryIngotID).setUnlocalizedName("IvoryIngot").setTextureName("ere_geologique:IvoryIngot");
	       IvoryNugget = new IvoryIngot(EGProperties.IvoryNuggetID).setUnlocalizedName("IvoryNugget").setTextureName("ere_geologique:IvoryNugget");
	       IvoryGear = new IvoryIngot(EGProperties.IvoryGearID).setUnlocalizedName("IvoryGear").setTextureName("ere_geologique:IvoryGear");
	}
}