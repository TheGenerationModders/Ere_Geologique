package ere_geologique.common.event;

import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.item.EGItemList;

public class PickupHandler
{
	@SubscribeEvent
	public void ItemPickupEvent(ItemPickupEvent event)
	{
		if(event.pickedUp.getEntityItem().getItem().equals(Item.getItemFromBlock(EGBlockList.wood)))
		{
			event.player.triggerAchievement(AchievementList.mineWood);
		}

		if(event.pickedUp.getEntityItem().getItem().equals(EGItemList.bioFossil))
		{
			event.player.addStat(EGAchievement.fossil, 1);
		}	
	}
}