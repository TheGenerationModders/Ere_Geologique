package ere_geologique.common;

import buildcraft.BuildCraftCore;
import cpw.mods.fml.common.registry.GameRegistry;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import ic2.api.Ic2Recipes;
import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.item.EreGeologiqueItemList;
import forestry.api.fuels.EngineBronzeFuel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;

public class Integration
{
	public static void loadIndustrialCraft()
	{
	 Ic2Recipes.addMaceratorRecipe(new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), new ItemStack(EreGeologiqueItemList.PrehistoriqueCoalDust,1));
	 Ic2Recipes.addCompressorRecipe(new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal,9), new ItemStack(EreGeologiqueBlockList.PrehistoriqueBlockCoal,1));
	 Ic2Recipes.addExtractorRecipe(new ItemStack(EreGeologiqueBlockList.Wood), new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal));
	}
	
	public static void loadBuildCraft()
	{
	 //Recette à rajouter
		GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.IvoryGear,1), new Object[]
	    {
		" X ", "XOX", " X ", 'X', new ItemStack(EreGeologiqueItemList.IvoryIngot), 'O', new ItemStack(BuildCraftCore.diamondGearItem)
	    });
	}
	
	public static void loadRailCraft()
	{
	 RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), new ItemStack(EreGeologiqueItemList.PrehistoriqueCokeCoal), LiquidDictionary.getLiquid("CoalLiquid", 500), 3000);
	 RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(EreGeologiqueBlockList.Wood), new ItemStack(EreGeologiqueItemList.PrehistoriqueCharCoal), LiquidDictionary.getLiquid("CoalLiquid", 250), 3000);
	}

	public static void loadForestry()
	{
	 forestry.api.recipes.RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(EreGeologiqueBlockList.Wood)}, LiquidDictionary.getLiquid("CoalLiquid", 200), new ItemStack(EreGeologiqueBlockList.Plank,4), 100);
	 forestry.api.fuels.FuelManager.bronzeEngineFuel.put(new ItemStack(EreGeologiqueItemList.CoalLiquidItem), new EngineBronzeFuel(new ItemStack(EreGeologiqueItemList.CoalLiquidItem), EGProperties.FCoalLiquidMJ, EGProperties.FCoalLiquidDurability, 1));
	}

	public static void loadIC_BC()
	{
	 //Recette à rejouter
	}

}
