package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class FougereSword extends ItemSword
{
	public FougereSword(int itemID, EnumToolMaterial toolMaterial)
    {
        super(itemID, toolMaterial);
    }
 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}
