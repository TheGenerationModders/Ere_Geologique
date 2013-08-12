package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import ere_geologique.common.EGCreativeTab;
import ere_geologique.common.config.EGProperties;

public class armureFougere extends ItemArmor
{
	 public armureFougere(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	    {
	        super(par1, par2EnumArmorMaterial, par3, par4);
	        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	    }
	 
	    public String getArmorTextureFile(ItemStack par1)
	    {
	        if (par1.itemID == EGProperties.FougereLeggingsID)
	        {
	            return "/ere_geologique/client/armortuto_2.png";
	        }
	        else
	        {
	            return "/ere_geologique/client/armortuto_1.png";
	        }
	    }
	 
	    @Override
	    public void registerIcons(IconRegister par1IconRegister)
	    {
			itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
	    }
}
