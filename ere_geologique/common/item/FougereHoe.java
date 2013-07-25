package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class FougereHoe extends ItemHoe
{
	public FougereHoe(int itemID, EnumToolMaterial toolMaterial)
    {
        super(itemID, toolMaterial);
    }
 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}
