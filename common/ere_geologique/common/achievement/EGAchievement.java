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
		installmod = new Achievement(1000, "InstallMod", 0, 0, EGItemList.dinoPedia, null).registerAchievement().setSpecial();
		dimensionGlacia = new Achievement(1001, "Glacia", 2, 0, EGItemList.flintAndSteel, installmod).registerAchievement();
//		DimensionPrehistoria = new Achievement(1002, "Prehistoria", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
//		DimensionPrimitive = new Achievement(1003, "Primitive", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
		
		fossil = new Achievement(1004, "Fossil", 4, 0, EGItemList.bioFossil, dimensionGlacia).registerAchievement();
		cultivator = new Achievement(1005, "Cultivator", 4, 2, EGBlockList.cultivatorIdle, fossil).registerAchievement();
		analyzer = new Achievement(1006, "Analyzer", 4, -2, EGBlockList.analyzer, fossil).registerAchievement();
		feeder = new Achievement(1007, "Feeder", 6, 0, EGBlockList.feeder, fossil).registerAchievement();
		
		pageGlacia = new AchievementPage("Glacia", installmod, dimensionGlacia, fossil, cultivator, analyzer, feeder);
        AchievementPage.registerAchievementPage(pageGlacia);
        
/*        pagePrehistoria = new AchievementPage("Prehistoria", Installmod, DimensionPrehistoria);
        AchievementPage.registerAchievementPage(pagePrehistoria);
        
        pagePrimitive = new AchievementPage("Primitive", Installmod, DimensionPrimitive);
        AchievementPage.registerAchievementPage(pagePrimitive);*/
	}
}