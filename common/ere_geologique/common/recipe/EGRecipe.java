package ere_geologique.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
		GameRegistry.addRecipe(new ItemStack(EGBlockList.cultivatorIdle, 1), new Object[] {"XYX", "XWX", "ZZZ", 'X', Blocks.glass, 'Y', new ItemStack(Items.dye, 1, 2), 'W', Items.water_bucket, 'Z', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.analyzer, 1), new Object[] {"XYX", "XWX", 'X', Items.iron_ingot, 'Y', EGItemList.relic, 'W', EGItemList.bioFossil});
		GameRegistry.addRecipe(new ItemStack(EGBlockList.feeder, 1), new Object[] {"XYX", "ZAB", "BBB", 'X', Items.iron_ingot, 'Y', Blocks.glass, 'Z', Blocks.stone_button, 'A', Items.bucket, 'B', Blocks.stone});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addRecipe(new ItemStack(Items.cake, 1), new Object[] {"MMM", "SES", "WWW", 'M', Items.milk_bucket, 'S', Items.sugar, 'W', Items.wheat, 'E', EnumDinoType.values()[i].eggItem});
		
		GameRegistry.addRecipe(new ItemStack(EGItemList.skullStick, 1), new Object[] {"X", "Y", 'X', EGBlockList.skull, 'Y', Items.stick});
		for(int i=0;i<EnumDinoType.values().length;i++)
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.dinoPedia), new Object[] {Items.book, EnumDinoType.values()[i].dnaItem});
		
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.rawChickenSoup, 1, 0), new Object[] {Items.bucket, Items.chicken});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 1), new Object[] {new ItemStack(EGItemList.magicConch, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 2), new Object[] {new ItemStack(EGItemList.magicConch, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(EGItemList.magicConch, 1, 0), new Object[] {new ItemStack(EGItemList.magicConch, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(EGItemList.chickenEss, 8), new Object[] {"XXX", "XYX", "XXX", 'X', Items.glass_bottle, 'Y', EGItemList.cookedChickenSoup});
		GameRegistry.addRecipe(new ItemStack(EGItemList.whip, 1), new Object[] {"  Y", " XY", "X Y", 'X', Items.stick, 'Y', Items.string});
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
			GameRegistry.addRecipe(new ItemStack(EGBlockList.reinforcedStone, 1), new Object[]{" X ", "XYX", " X ", 'X', EGItemList.steelPlate, 'Y', Blocks.stone});
			GameRegistry.addRecipe(new ItemStack(EGBlockList.reinforcedGlass, 1), new Object[]{" X ", "XYX", " X ", 'X', EGItemList.steelPlate, 'Y', Blocks.glass});
			GameRegistry.addRecipe(new ItemStack(EGItemList.steelPlate, 1), new Object[]{"XX ", "XX ", 'X', EGItemList.steelIngot});
			GameRegistry.addRecipe(new ItemStack(EGBlockList.steelBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', EGItemList.steelIngot});
			GameRegistry.addSmelting(new ItemStack(Items.iron_ingot, 1), new ItemStack(EGItemList.steelIngot), 3.0F);
		}
	}

	public static void loadSmelting()
	{
		GameRegistry.addSmelting(new ItemStack(EnumDinoType.values()[4].eggItem, 1), new ItemStack(EGItemList.sjl), 3.0F);
        
        for(int i=0;i<EnumDinoType.values().length;i++)
        if(i!=4)
        GameRegistry.addSmelting(new ItemStack(EnumDinoType.values()[i].dropItem, 1), new ItemStack(EGItemList.cookedDinoMeat), 3.0F);
        GameRegistry.addSmelting(new ItemStack(EGItemList.rawChickenSoup, 1), new ItemStack(EGItemList.cookedChickenSoup), 3.0F);
	}
}