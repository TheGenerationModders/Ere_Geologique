package ere_geologique.common.event;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.config.EGProperties;

public class PlayerTracker implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		player.triggerAchievement(EGAchievement.installmod);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
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

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		
	}
}