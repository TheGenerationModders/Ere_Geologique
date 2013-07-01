package ere_geologique.common;

import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.block.FougereSapling;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class FougereBoneMeal
{
	@ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event)
    {
            if (event.ID == EreGeologique.FougereSaplingID)
            {
                    if (!event.world.isRemote)
                    {
                            ((FougereSapling)EreGeologiqueBlockList.FougereSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
                            event.setResult(Event.Result.ALLOW);
                    }
            }
    }
}
