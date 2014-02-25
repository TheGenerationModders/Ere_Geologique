package ere_geologique.common.event;

import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.block.Sapling;

public class FougereBoneMeal
{
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.block == EGBlockList.sapling)
		{
			if (!event.world.isRemote)
			{
				((Sapling) EGBlockList.sapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
	}
}