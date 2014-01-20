package ere_geologique.common.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.item.EGItemList;

public class EGAchievement
{
	public static Achievement installmod;
	
	public static Achievement dimensionGlacia, dimensionPrehistoria, dimensionPrimitive;
	
	public static Achievement fossil;
	
	public static Achievement firstEgg, allEggs;

	public static Achievement cultivator, analyzer, feeder;
	
	public static AchievementPage pageGlacia, pagePrehistoria, pagePrimitive;
	
	public static void loadAchievement()
	{
		installmod = new Achievement("achievement.InstallMod", "InstallMod", 0, 0, EGItemList.dinoPedia, null).registerStat().setSpecial();
		dimensionGlacia = new Achievement("achievement.Glacia", "Glacia", 2, 0, EGItemList.flintAndSteel, installmod).registerStat();
//		DimensionPrehistoria = new Achievement(1002, "Prehistoria", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
//		DimensionPrimitive = new Achievement(1003, "Primitive", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
		
		fossil = new Achievement("achievement.Fossil", "Fossil", 4, 0, EGItemList.bioFossil, dimensionGlacia).registerStat();
		cultivator = new Achievement("achievement.Cultivator", "Cultivator", 4, 2, EGBlockList.cultivatorIdle, fossil).registerStat();
		analyzer = new Achievement("achievement.Analyzer", "Analyzer", 4, -2, EGBlockList.analyzer, fossil).registerStat();
		feeder = new Achievement("achievement.Feeder", "Feeder", 6, 0, EGBlockList.feeder, fossil).registerStat();
		
		pageGlacia = new AchievementPage("Glacia", installmod, dimensionGlacia, fossil, cultivator, analyzer, feeder);
        AchievementPage.registerAchievementPage(pageGlacia);
        
/*        pagePrehistoria = new AchievementPage("Prehistoria", Installmod, DimensionPrehistoria);
        AchievementPage.registerAchievementPage(pagePrehistoria);
        
        pagePrimitive = new AchievementPage("Primitive", Installmod, DimensionPrimitive);
        AchievementPage.registerAchievementPage(pagePrimitive);*/
	}
}