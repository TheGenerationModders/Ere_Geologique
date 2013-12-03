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
		player.triggerAchievement(EGAchievement.Installmod);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		if(player.dimension == EGProperties.GlaciaID)
        {
                player.triggerAchievement(EGAchievement.DimensionGlacia);
        }else if(player.dimension == EGProperties.PrehistoriaID)
        {
            player.triggerAchievement(EGAchievement.DimensionPrehistoria);
        }else if(player.dimension == EGProperties.PrimitiveID)
        {
            player.triggerAchievement(EGAchievement.DimensionPrimitive);
		}
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		
	}

}