package ere_geologique.common;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.block.Sapling;

public class FougereBoneMeal
{
	@ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event)
    {
            if (event.ID == EreGeologique.SaplingID)
            {
                    if (!event.world.isRemote)
                    {
                            ((Sapling)EreGeologiqueBlockList.Sapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
                            event.setResult(Event.Result.ALLOW);
                    }
            }
    }
}
