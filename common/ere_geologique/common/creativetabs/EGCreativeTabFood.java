package ere_geologique.common.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ere_geologique.common.item.EGItemList;

public class EGCreativeTabFood extends CreativeTabs
{
	
	 public EGCreativeTabFood(String name)
	 {
	        super(name);
	 }
	 
	 @Override
	 public ItemStack getIconItemStack()
	 {
		 return new ItemStack(EGItemList.cookedDinoMeat);
	 }
}