package ere_geologique.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ere_geologique.common.block.EGBlockList;

public class EGCreativeTab extends CreativeTabs
{
	public static CreativeTabs EGCreativeTab;
	
	public static void loadCreativeTab()
	{
		EGCreativeTab = new EGCreativeTab("EGCreativeTab");
	}
	
	 public EGCreativeTab(String name)
	 {
	        super(name);
	 }
	 
	    @Override
	    public ItemStack getIconItemStack()
	    {
	        return new ItemStack(EGBlockList.Wood);
	    }
}
