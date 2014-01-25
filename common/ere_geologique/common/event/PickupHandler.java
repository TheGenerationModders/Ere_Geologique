package ere_geologique.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.item.EGItemList;

public class PickupHandler
{
	@SubscribeEvent
	public void ItemPickupEvent(EntityItem item, EntityPlayer player)
	{
		if(item.getEntityItem().getItem().equals(EGBlockList.wood))
		{
			player.triggerAchievement(AchievementList.mineWood);
		}

		if(item.getEntityItem().getItem().equals(EGItemList.bioFossil))
		{
			player.addStat(EGAchievement.fossil, 1);
		}	
	}
}