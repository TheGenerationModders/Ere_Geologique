package ere_geologique.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.config.EGProperties;

public class PlayerTracker
{
	@SubscribeEvent
	public void PlayerLoggedInEvent(PlayerLoggedInEvent event)
	{
		event.player.triggerAchievement(EGAchievement.installmod);
	}

	@SubscribeEvent
	public void PlayerLoggedOutEvent(PlayerLoggedOutEvent event)
	{

	}

	@SubscribeEvent
	public void PlayerChangedDimensionEvent(PlayerChangedDimensionEvent event)
	{
		if(event.player.dimension == EGProperties.glaciaID)
        {
			event.player.triggerAchievement(EGAchievement.dimensionGlacia);
        }else if(event.player.dimension == EGProperties.prehistoriaID)
        {
        	event.player.triggerAchievement(EGAchievement.dimensionPrehistoria);
        }else if(event.player.dimension == EGProperties.primitiveID)
        {
        	event.player.triggerAchievement(EGAchievement.dimensionPrimitive);
		}
	}

	@SubscribeEvent
	public void PlayerRespawnEvent(PlayerRespawnEvent event)
	{
		
	}
}