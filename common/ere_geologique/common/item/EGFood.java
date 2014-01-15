package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class EGFood extends ItemFood
{
	String TextureFileName;
	
	public EGFood(int par1, int par2, float par3, boolean par4,String TextureFileName0)
	{
		super(par1,par2, par4);
		this.TextureFileName=TextureFileName0;
		this.setCreativeTab(EGCreativeTab.EGCreativeTabFood);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("ere_geologique:" + TextureFileName);
    }
}