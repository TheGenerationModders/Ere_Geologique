/*package ere_geologique.proxy.network;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ere_geologique.common.item.Gun;

public class TickHandlerClient implements ITickHandler
{
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.PLAYER)))
		{
			playerTick(((EntityPlayer)tickData[0]).worldObj, (EntityPlayer)tickData[0]);
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel()
	{
		return "TickHandlerClient";
	}
	
	public void playerTick(World world, EntityPlayer player)
	{
		ItemStack is = player.getCurrentEquippedItem();
		if ((is != null) && ((is.getItem() instanceof Gun)))
		{
			if (((player != Minecraft.getMinecraft().renderViewEntity) || (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)))
			{
				if (player.getItemInUseCount() <= 0)
				{
					player.clearItemInUse();
					player.setItemInUse(is, Integer.MAX_VALUE);
				}
			}
		}
	}
}*/