package ere_geologique.common;

import ere_geologique.common.block.EreGeologiqueBlockList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EreGeologiqueCreativeTab extends CreativeTabs
{
	 public EreGeologiqueCreativeTab(String label) {
	        super(label);
	    }
	 
	    @Override
	    public ItemStack getIconItemStack() {
	        return new ItemStack(EreGeologiqueBlockList.Wood);
	    }
}
