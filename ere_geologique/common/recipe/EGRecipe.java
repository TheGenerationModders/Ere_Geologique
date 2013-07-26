package ere_geologique.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.EreGeologiquefuel;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.item.EGItemList;

public class EGRecipe
{
	public static void loadRecipe() 
	{
	           GameRegistry.addShapelessRecipe(new ItemStack(EGBlockList.Plank,4), new Object[]
		       {
		        new ItemStack(EGBlockList.Wood)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGBlockList.Slab,6), new Object[]
		       {
		        "   ", "   ", "XXX", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGBlockList.Stair,4), new Object[]
		       {
		    	"X  ", "XX ", "XXX", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Item.stick,4), new Object[]
		       {
		    	" X ", " X ", "   ", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.workbench,1), new Object[]
		       {
		    	"   ", "XX ", "XX ", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.chest,1), new Object[]
		       {
		    	"XXX", "X X", "XXX", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.doorWood,1), new Object[]
		       {
		    	"XX ", "XX ", "XX ", 'X', new ItemStack(EGBlockList.Plank)
		       });
		       GameRegistry.addRecipe(new ItemStack(Block.bookShelf,1), new Object[]
		       {
		    	"XXX", "YYY", "XXX", 'X', new ItemStack(EGBlockList.Plank), 'Y', new ItemStack(Item.book)
		       });
		       GameRegistry.addRecipe(new ItemStack(Item.bed,1), new Object[]
		       {
		    	"   ", "YYY", "XXX", 'X', new ItemStack(EGBlockList.Plank), 'Y', new ItemStack(Block.cloth)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereSword,1), new Object[]
		       {
		    	" X ", " X ", " Y ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougerePickaxe,1), new Object[]
		       {
		    	"XXX", " Y ", " Y ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereAxe,1), new Object[]
		       {
		    	"XX ", "XY ", " Y ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereShovel,1), new Object[]
		       {
		    	" X ", " Y ", " Y ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereHoe,1), new Object[]
		       {
		    	"XX ", " Y ", " Y ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal), 'Y', new ItemStack(Item.stick)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereHelmet,1), new Object[]
		       {
		    	"XXX", "X X", "   ", 'X', new ItemStack(EGItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereChestplate,1), new Object[]
		       {
		    	"X X", "XXX", "XXX", 'X', new ItemStack(EGItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereLeggings,1), new Object[]
		       {
		    	"XXX", "X X", "X X", 'X', new ItemStack(EGItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGItemList.FougereBoots,1), new Object[]
		       {
		    	"   ", "X X", "X X", 'X', new ItemStack(EGItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGBlockList.PrehistoriqueBlockCoal,1), new Object[]
		       {
		    	"XXX", "XXX", "XXX", 'X', new ItemStack(EGItemList.PrehistoriqueCoal)
		       });
		       GameRegistry.addRecipe(new ItemStack(EGBlockList.PrehistoriqueBlockIron,1), new Object[]
		       {
		    	"XXX", "XXX", "XXX", 'X', new ItemStack(EGItemList.PrehistoriqueIronIngot)
		       });
	}
	public static void loadSmelting()
	{
		GameRegistry.addSmelting(EGProperties.PlankID, new ItemStack(EGItemList.PrehistoriqueCharCoal), 5);
	    GameRegistry.registerFuelHandler(new EreGeologiquefuel());
	}
}