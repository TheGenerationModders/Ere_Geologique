package ere_geologique.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class FougerePickaxe  extends ItemPickaxe
{
	public FougerePickaxe(int itemID, EnumToolMaterial toolMaterial)
    {
        super(itemID, toolMaterial);
    }
 
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}
