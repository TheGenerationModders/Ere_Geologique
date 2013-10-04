package ere_geologique.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;

public class EGRecipe
{
	public static void loadRecipe()
	{
		GameRegistry.addRecipe(new ItemStack(EGBlockList.CultivatorIdle, 1), new Object[] {"XYX", "XWX", "ZZZ", 'X', Block.glass, 'Y', new ItemStack(Item.dyePowder, 1, 2), 'W', Item.bucketWater, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.AnalyzerIdle, 1), new Object[] {"XYX", "XWX", 'X', Item.ingotIron, 'Y', EGItemList.Relic, 'W', EGItemList.BioFossil});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.FeederIdle, 1), new Object[] {"XYX", "ZAB", "BBB", 'X', Item.ingotIron, 'Y', Block.glass, 'Z', Block.stoneButton, 'A', Item.bucketEmpty, 'B', Block.stone});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"MMM", "SES", "WWW", 'M', Item.bucketMilk, 'S', Item.sugar, 'W', Item.wheat, 'E', EnumDinoType.values()[i].EggItem});
		
		GameRegistry.addRecipe(new ItemStack(EGItemList.SkullStick, 1), new Object[] {"X", "Y", 'X', EGBlockList.Skull, 'Y', Item.stick});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.DinoPedia), new Object[] {Item.book, EnumDinoType.values()[i].DNAItem});
		
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.rawChickenSoup, 1, 0), new Object[] {Item.bucketEmpty, Item.chickenRaw});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.MagicConch, 1, 1), new Object[] {new ItemStack(EGItemList.MagicConch, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.MagicConch, 1, 2), new Object[] {new ItemStack(EGItemList.MagicConch, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.MagicConch, 1, 0), new Object[] {new ItemStack(EGItemList.MagicConch, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(EGItemList.ChickenEss, 8), new Object[] {"XXX", "XYX", "XXX", 'X', Item.glassBottle, 'Y', EGItemList.cookedChickenSoup});
		GameRegistry.addRecipe(new ItemStack(EGItemList.Whip, 1), new Object[] {"  Y", " XY", "X Y", 'X', Item.stick, 'Y', Item.silk});
	}

	public static void loadSmelting()
	{
		GameRegistry.addSmelting(EnumDinoType.values()[4].EggItem.itemID, new ItemStack(EGItemList.sjl), 3.0F);
        
        for(int i=0;i<EnumDinoType.values().length;i++)
        if(i!=4)
        GameRegistry.addSmelting(EnumDinoType.values()[i].DropItem.itemID, new ItemStack(EGItemList.cookedDinoMeat), 3.0F);
        GameRegistry.addSmelting(EGItemList.rawChickenSoup.itemID, new ItemStack(EGItemList.cookedChickenSoup), 3.0F);
	}
}