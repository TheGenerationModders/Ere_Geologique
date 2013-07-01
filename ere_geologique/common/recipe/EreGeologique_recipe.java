package ere_geologique.common.recipe;

import buildcraft.BuildCraftCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.EreGeologiquefuel;
import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.item.EreGeologiqueItemList;

public class EreGeologique_recipe
{
	public static void loadrecipe() 
	{
	           GameRegistry.addShapelessRecipe(new ItemStack(EreGeologiqueBlockList.FougerePlank,4), new Object[]
		       {
		        new ItemStack(EreGeologiqueBlockList.FougereWood)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueBlockList.FougereSlab,6), new Object[]
		       {
		        "   ", "   ", "XXX", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueBlockList.FougereStair,4), new Object[]
		       {
		    	"X  ", "XX ", "XXX", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Item.stick,4), new Object[]
		       {
		    	" X ", " X ", "   ", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.workbench,1), new Object[]
		       {
		    	"   ", "XX ", "XX ", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.chest,1), new Object[]
		       {
		    	"XXX", "X X", "XXX", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.doorWood,1), new Object[]
		       {
		    	"XX ", "XX ", "XX ", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.bookShelf,1), new Object[]
		       {
		    	"XXX", "YYY", "XXX", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank), 'Y', new ItemStack(Item.book)
		       });
		       GameRegistry.addRecipe(new ItemStack(Item.bed,1), new Object[]
		       {
		    	"   ", "YYY", "XXX", 'X', new ItemStack(EreGeologiqueBlockList.FougerePlank), 'Y', new ItemStack(Block.cloth)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereSword,1), new Object[]
		       {
		    	" X ", " X ", " Y ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougerePickaxe,1), new Object[]
		       {
		    	"XXX", " Y ", " Y ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereAxe,1), new Object[]
		       {
		    	"XX ", "XY ", " Y ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereShovel,1), new Object[]
		       {
		    	" X ", " Y ", " Y ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereHoe,1), new Object[]
		       {
		    	"XX ", " Y ", " Y ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereHelmet,1), new Object[]
		       {
		    	"XXX", "X X", "   ", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereChestplate,1), new Object[]
		       {
		    	"X X", "XXX", "XXX", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereLeggings,1), new Object[]
		       {
		    	"XXX", "X X", "X X", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueItemList.FougereBoots,1), new Object[]
		       {
		    	"   ", "X X", "X X", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addShapelessRecipe(new ItemStack(EreGeologiqueItemList.FougereSeeds, 5), new Object[]
		       {
		    	new ItemStack(EreGeologiqueBlockList.FougereSapling)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueBlockList.PrehistoriqueBlockCoal,1), new Object[]
		       {
		    	"XXX", "XXX", "XXX", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EreGeologiqueBlockList.PrehistoriqueBlockIron,1), new Object[]
		       {
		    	"XXX", "XXX", "XXX", 'X', new ItemStack(EreGeologiqueItemList.PrehistoriqueIronIngot)
		       });
	}
	public static void loadSmelting()
	{
		GameRegistry.addSmelting(EreGeologique.FougerePlankID, new ItemStack(EreGeologiqueItemList.PrehistoriqueCharCoal), 5);
	    GameRegistry.registerFuelHandler(new EreGeologiquefuel());
	}
}