package ere_geologique.common.event;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.config.EGProperties;

public class PlayerTracker
{
	@SubscribeEvent
	public void PlayerLoggedInEvent(EntityPlayer player)
	{
		player.triggerAchievement(EGAchievement.installmod);
	}

	@SubscribeEvent
	public void PlayerLoggedOutEvent(EntityPlayer player)
	{

	}

	@SubscribeEvent
	public void PlayerChangedDimensionEvent(EntityPlayer player)
	{
		if(player.dimension == EGProperties.glaciaID)
        {
			player.triggerAchievement(EGAchievement.dimensionGlacia);
        }else if(player.dimension == EGProperties.prehistoriaID)
        {
            player.triggerAchievement(EGAchievement.dimensionPrehistoria);
        }else if(player.dimension == EGProperties.primitiveID)
        {
            player.triggerAchievement(EGAchievement.dimensionPrimitive);
		}
	}

	@SubscribeEvent
	public void PlayerRespawnEvent(EntityPlayer player)
	{
		
	}
}