package ere_geologique.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerPedia extends Container
{

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}

}