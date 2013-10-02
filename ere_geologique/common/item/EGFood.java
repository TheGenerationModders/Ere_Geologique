package ere_geologique.common.item;

import ere_geologique.common.creativetabs.EGCreativeTab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

public class EGFood extends ItemFood
{
	String TextureFileName;
	
	public EGFood(int par1, int par2, float par3, boolean par4,String TextureFileName0)
	{
		super(par1,par2,par3,par4);
		this.TextureFileName=TextureFileName0;
		this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("ere_geologique:" + TextureFileName);
    }

}
