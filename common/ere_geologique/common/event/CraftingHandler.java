package ere_geologique.common.event;

import java.util.Arrays;
import java.util.HashSet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.enums.EnumDinoType;

public class CraftingHandler
{
	HashSet<Integer> eggsFound = new HashSet<Integer>();
    Integer[] subeggsTotal = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13};
    HashSet<Integer> eggsTotal = new HashSet<Integer>(Arrays.asList(subeggsTotal));
	
    @SubscribeEvent
	public void ItemCraftedEvent(ItemCraftedEvent event)
	{
		if(event.crafting.getItem().equals(EGBlockList.feeder))
		{
			event.player.addStat(EGAchievement.feeder, 1);
		}else if(event.crafting.getItem().equals(EGBlockList.analyzer))
		{
			event.player.addStat(EGAchievement.analyzer, 1);
		}else if(event.crafting.getItem().equals(EGBlockList.cultivatorIdle))
		{
			event.player.addStat(EGAchievement.cultivator, 1);
		}
		
	}

    @SubscribeEvent
	public void ItemSmeltedEvent(ItemSmeltedEvent event)
	{
		for (int i = 0; i < EnumDinoType.values().length; i++)
		{
			if (event.smelting.getItem().equals(EnumDinoType.values()[i].eggItem))
			{
				event.player.addStat(EGAchievement.firstEgg, 1);
				if (!eggsFound.contains(i))
					this.eggsFound.add(i);
			}
		}

		/*EreGeologique.DebugMessage("eggsTotal: " + this.eggsTotal);
		EreGeologique.DebugMessage("eggsFound: " + this.eggsFound);
		EreGeologique.DebugMessage("" + eggsFound.containsAll(eggsTotal));*/

		if (eggsFound.containsAll(eggsTotal))
		{
			event.player.addStat(EGAchievement.allEggs, 1);
		}
	}
}