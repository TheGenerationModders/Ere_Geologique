package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class FlintAndSteel extends Item
{
	public FlintAndSteel()
	{
		super();
		this.maxStackSize = 1;
		setMaxDamage(64);
		setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			par5--;
		}
		if (par7 == 1)
		{
			par5++;
		}
		if (par7 == 2)
		{
			par6--;
		}
		if (par7 == 3)
		{
			par6++;
		}
		if (par7 == 4)
		{
			par4--;
		}
		if (par7 == 5)
		{
			par4++;
		}
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		if (par3World.func_147437_c(par4, par5, par6))
		{
			par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			par3World.func_147449_b(par4, par5, par6, EGBlockList.blueFire);
		}
		par1ItemStack.damageItem(1, par2EntityPlayer);
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("ere_geologique:frozen_flint_and_steel");
    }
}