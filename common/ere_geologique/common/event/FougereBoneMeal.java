package ere_geologique.common.event;

import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.block.Sapling;
import ere_geologique.common.config.EGProperties;

public class FougereBoneMeal
{
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.ID == EGProperties.saplingID)
		{
			if (!event.world.isRemote)
			{
				((Sapling) EGBlockList.sapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
	}
}