package ere_geologique.common.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import ere_geologique.common.item.EGItemList;

public class EGAchievement
{
	public static Achievement Installmod;
	
	public static Achievement DimensionGlacia, DimensionPrehistoria, DimensionPrimitive;
	
	public static AchievementPage pageGlacia, pagePrehistoria, pagePrimitive;
	
	public static void loadAchievement()
	{
		Installmod = new Achievement(1000, "InstallMod", 0, 0, EGItemList.DinoPedia, null).registerAchievement().setSpecial();
		DimensionGlacia = new Achievement(1001, "Glacia", 2, 0, EGItemList.FlintAndSteel, Installmod).registerAchievement();
		DimensionPrehistoria = new Achievement(1002, "Prehistoria", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
		DimensionPrimitive = new Achievement(1003, "Primitive", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();

		
		pageGlacia = new AchievementPage("Glacia", Installmod, DimensionGlacia);
        AchievementPage.registerAchievementPage(pageGlacia);
        
        pagePrehistoria = new AchievementPage("Prehistoria", Installmod, DimensionPrehistoria);
        AchievementPage.registerAchievementPage(pagePrehistoria);
        
        pagePrimitive = new AchievementPage("Primitive", Installmod, DimensionPrimitive);
        AchievementPage.registerAchievementPage(pagePrimitive);
	}
}