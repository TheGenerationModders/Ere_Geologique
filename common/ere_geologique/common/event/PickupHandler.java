package ere_geologique.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import cpw.mods.fml.common.IPickupNotifier;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.item.EGItemList;

public class PickupHandler implements IPickupNotifier
{

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
	{
		if(item.getEntityItem().itemID == EGBlockList.wood.blockID)
		{
			player.triggerAchievement(AchievementList.mineWood);
		}

		if(item.getEntityItem().itemID == EGItemList.bioFossil.itemID)
		{
			player.addStat(EGAchievement.fossil, 1);
		}	
	}
}