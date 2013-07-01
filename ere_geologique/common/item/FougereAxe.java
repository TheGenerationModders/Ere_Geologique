package ere_geologique.common.item;

import ere_geologique.proxy.EreGeologiqueClientProxy;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

public class FougereAxe extends ItemAxe
{
	public FougereAxe(int itemID, EnumToolMaterial toolMaterial)
    {
        super(itemID, toolMaterial);
    }
 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}
