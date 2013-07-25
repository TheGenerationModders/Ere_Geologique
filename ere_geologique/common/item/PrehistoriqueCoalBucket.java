package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import ere_geologique.common.EGCreativeTab;

public class PrehistoriqueCoalBucket extends Item
{

	public PrehistoriqueCoalBucket(int i) {
		super(i);
		maxStackSize = 64;
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }
}