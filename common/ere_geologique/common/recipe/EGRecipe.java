package ere_geologique.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;

public class EGRecipe
{
	public static void loadRecipe()
	{
		GameRegistry.addRecipe(new ItemStack(EGBlockList.cultivatorIdle, 1), new Object[] {"XYX", "XWX", "ZZZ", 'X', Block.glass, 'Y', new ItemStack(Item.dyePowder, 1, 2), 'W', Item.bucketWater, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.analyzer, 1), new Object[] {"XYX", "XWX", 'X', Item.ingotIron, 'Y', EGItemList.relic, 'W', EGItemList.bioFossil});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.feeder, 1), new Object[] {"XYX", "ZAB", "BBB", 'X', Item.ingotIron, 'Y', Block.glass, 'Z', Block.stoneButton, 'A', Item.bucketEmpty, 'B', Block.stone});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"MMM", "SES", "WWW", 'M', Item.bucketMilk, 'S', Item.sugar, 'W', Item.wheat, 'E', EnumDinoType.values()[i].eggItem});
		
		GameRegistry.addRecipe(new ItemStack(EGItemList.skullStick, 1), new Object[] {"X", "Y", 'X', EGBlockList.skull, 'Y', Item.stick});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.dinoPedia), new Object[] {Item.book, EnumDinoType.values()[i].dnaItem});
		
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.rawChickenSoup, 1, 0), new Object[] {Item.bucketEmpty, Item.chickenRaw});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 1), new Object[] {new ItemStack(EGItemList.magicConch, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 2), new Object[] {new ItemStack(EGItemList.magicConch, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 0), new Object[] {new ItemStack(EGItemList.magicConch, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(EGItemList.chickenEss, 8), new Object[] {"XXX", "XYX", "XXX", 'X', Item.glassBottle, 'Y', EGItemList.cookedChickenSoup});
		GameRegistry.addRecipe(new ItemStack(EGItemList.whip, 1), new Object[] {"  Y", " XY", "X Y", 'X', Item.stick, 'Y', Item.silk});
		GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.plank, 4, 0), new Object[] {new ItemStack(EGBlockList.wood, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.plank, 4, 1), new Object[] {new ItemStack(EGBlockList.wood, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.plank, 4, 2), new Object[] {new ItemStack(EGBlockList.wood, 1, 2)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.plank, 4, 3), new Object[] {new ItemStack(EGBlockList.wood, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.plank, 4, 4), new Object[] {new ItemStack(EGBlockList.wood, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.slab, 6, 0), new Object[] {"XXX", 'X', new ItemStack(EGBlockList.wood, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.slab, 6, 1), new Object[] {"XXX", 'X', new ItemStack(EGBlockList.wood, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.slab, 6, 2), new Object[] {"XXX", 'X', new ItemStack(EGBlockList.wood, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.slab, 6, 3), new Object[] {"XXX", 'X', new ItemStack(EGBlockList.wood, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.slab, 6, 4), new Object[] {"XXX", 'X', new ItemStack(EGBlockList.wood, 1, 4)});
		
		if(!Loader.isModLoaded("IC2") || !Loader.isModLoaded("Railcraft"))
		{
			GameRegistry.addRecipe(new ItemStack(EGBlockList.reinforcedStone, 1), new Object[]{" X ", "XYX", " X ", 'X', EGItemList.steelPlate, 'Y', Block.stone});
			GameRegistry.addRecipe(new ItemStack(EGBlockList.reinforcedGlass, 1), new Object[]{" X ", "XYX", " X ", 'X', EGItemList.steelPlate, 'Y', Block.glass});
			GameRegistry.addRecipe(new ItemStack(EGItemList.steelPlate, 1), new Object[]{"XX ", "XX ", 'X', EGItemList.steelIngot});
			GameRegistry.addRecipe(new ItemStack(EGBlockList.steelBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', EGItemList.steelIngot});
			GameRegistry.addSmelting(Item.ingotIron.itemID, new ItemStack(EGItemList.steelIngot), 3.0F);
		}

	}

	public static void loadSmelting()
	{
		GameRegistry.addSmelting(EnumDinoType.values()[4].eggItem.itemID, new ItemStack(EGItemList.sjl), 3.0F);
        
        for(int i=0;i<EnumDinoType.values().length;i++)
        if(i!=4)
        GameRegistry.addSmelting(EnumDinoType.values()[i].dropItem.itemID, new ItemStack(EGItemList.cookedDinoMeat), 3.0F);
        GameRegistry.addSmelting(EGItemList.rawChickenSoup.itemID, new ItemStack(EGItemList.cookedChickenSoup), 3.0F);
	}
}