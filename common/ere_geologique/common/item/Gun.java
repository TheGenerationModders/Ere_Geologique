package ere_geologique.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Gun extends Item
{
	public Gun(int id)
	{
		super();
		this.setFull3D();
		this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
		this.setMaxStackSize(1);
		this.setMaxDamage(384);
	}
	
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(player.capabilities.isCreativeMode || player.inventory.func_146028_b(EGItemList.tranquilizerDart))
		player.setItemInUse(stack, 7200);
		return stack;
    }
}