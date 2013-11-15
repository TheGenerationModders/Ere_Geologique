package ere_geologique.common.item;

import ere_geologique.common.creativetabs.EGCreativeTab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class EGItem extends Item
{
	String TextureFileName;
	
	public EGItem(int par1, String TextureFileName0)
	{
		super(par1);
		this.TextureFileName=TextureFileName0;
		this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("ere_geologique:" + TextureFileName);
    }
}