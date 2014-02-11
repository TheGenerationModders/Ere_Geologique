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

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			y--;
		}
		if (par7 == 1)
		{
			y++;
		}
		if (par7 == 2)
		{
			z--;
		}
		if (par7 == 3)
		{
			z++;
		}
		if (par7 == 4)
		{
			x--;
		}
		if (par7 == 5)
		{
			x++;
		}
		if (!player.canPlayerEdit(x, y, z, par7, itemStack))
		{
			return false;
		}
		if (world.isAirBlock(x, y, z))
		{
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			world.setBlock(x, y, z, EGBlockList.blueFire);
		}
		itemStack.damageItem(1, player);
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("ere_geologique:frozen_flint_and_steel");
    }
}