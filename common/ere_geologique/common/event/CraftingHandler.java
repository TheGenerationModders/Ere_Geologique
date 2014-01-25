package ere_geologique.common.event;

import java.util.Arrays;
import java.util.HashSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class CraftingHandler
{
	HashSet<Integer> eggsFound = new HashSet<Integer>();
    Integer[] subeggsTotal = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13};
    HashSet<Integer> eggsTotal = new HashSet<Integer>(Arrays.asList(subeggsTotal));
	
    @SubscribeEvent
	public void ItemCraftedEvent(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
		if(item.getItem().equals(EGBlockList.feeder))
		{
			player.addStat(EGAchievement.feeder, 1);
		}else if(item.getItem().equals(EGBlockList.analyzer))
		{
			player.addStat(EGAchievement.analyzer, 1);
		}else if(item.getItem().equals(EGBlockList.cultivatorIdle))
		{
			player.addStat(EGAchievement.cultivator, 1);
		}
		
	}

    @SubscribeEvent
	public void ItemSmeltedEvent(EntityPlayer player, ItemStack item)
	{
		for (int i = 0; i < EnumDinoType.values().length; i++)
		{
			if (item.getItem().equals(EnumDinoType.values()[i].eggItem))
			{
				player.addStat(EGAchievement.firstEgg, 1);
				if (!eggsFound.contains(i))
					this.eggsFound.add(i);
			}
		}

		/*EreGeologique.DebugMessage("eggsTotal: " + this.eggsTotal);
		EreGeologique.DebugMessage("eggsFound: " + this.eggsFound);
		EreGeologique.DebugMessage("" + eggsFound.containsAll(eggsTotal));*/

		if (eggsFound.containsAll(eggsTotal))
		{
			player.addStat(EGAchievement.allEggs, 1);
		}
	}
}