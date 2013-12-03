package ere_geologique.common.event;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.block.Sapling;
import ere_geologique.common.config.EGProperties;

public class FougereBoneMeal
{
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.ID == EGProperties.SaplingID)
		{
			if (!event.world.isRemote)
			{
				((Sapling) EGBlockList.Sapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
	}
}