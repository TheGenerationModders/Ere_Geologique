package ere_geologique.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class IvoryIngot extends Item
{

	public IvoryIngot(int i)
	{
		super(i);
		maxStackSize = 64;
        this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}
