package ere_geologique.common.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.item.EGItemList;

public class EGAchievement
{
	public static Achievement Installmod;
	
	public static Achievement DimensionGlacia, DimensionPrehistoria, DimensionPrimitive;
	
	public static Achievement Fossil;
	
	public static Achievement Cultivator, Analyser, Feeder;
	
	public static AchievementPage pageGlacia, pagePrehistoria, pagePrimitive;
	
	public static void loadAchievement()
	{
		Installmod = new Achievement(1000, "InstallMod", 0, 0, EGItemList.DinoPedia, null).registerAchievement().setSpecial();
		DimensionGlacia = new Achievement(1001, "Glacia", 2, 0, EGItemList.FlintAndSteel, Installmod).registerAchievement();
		DimensionPrehistoria = new Achievement(1002, "Prehistoria", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
		DimensionPrimitive = new Achievement(1003, "Primitive", 2, 0, EGItemList.DinoPedia, Installmod).registerAchievement();
		
		Fossil = new Achievement(1004, "Fossil", 4, 0, EGItemList.BioFossil, Installmod).registerAchievement();
		Cultivator = new Achievement(1005, "Cultivator", 4, 2, EGBlockList.CultivatorIdle, Fossil).registerAchievement();
		Analyser = new Achievement(1006, "Analyzer", 4, -2, EGBlockList.AnalyzerIdle, Fossil).registerAchievement();
		Feeder = new Achievement(1007, "Feeder", 6, 0, EGBlockList.FeederIdle, Fossil).registerAchievement();
		
		pageGlacia = new AchievementPage("Glacia", Installmod, DimensionGlacia);
        AchievementPage.registerAchievementPage(pageGlacia);
        
        pagePrehistoria = new AchievementPage("Prehistoria", Installmod, DimensionPrehistoria);
        AchievementPage.registerAchievementPage(pagePrehistoria);
        
        pagePrimitive = new AchievementPage("Primitive", Installmod, DimensionPrimitive);
        AchievementPage.registerAchievementPage(pagePrimitive);
	}
}