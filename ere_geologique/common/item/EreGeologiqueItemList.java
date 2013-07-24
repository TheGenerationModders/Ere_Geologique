package ere_geologique.common.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;

public class EreGeologiqueItemList
{
	public static Item PrehistoriqueCoal;
	public static Item PrehistoriqueCoalDust;
	public static Item PrehistoriqueCokeCoal;
	public static Item PrehistoriqueCharCoal;
	public static Item PrehistoriqueIronIngot;
	public static Item IvoryNugget;
	public static Item IvoryIngot;
	public static Item IvoryGear;
	public static Item FougereSeeds;
	public static Item FruitFougere;
	public static Item FougereSword;
	public static Item FougerePickaxe;
	public static Item FougereAxe;
	public static Item FougereShovel;
	public static Item FougereHoe;
	public static Item FougereHelmet;
	public static Item FougereChestplate;
	public static Item FougereLeggings;
	public static Item FougereBoots;
	public static Item CoalLiquidItem;
	public static Item CoalLiquidBucket;
	public static LiquidStack CoalLiquid;
	public static EnumArmorMaterial armureFougere = EnumHelper.addArmorMaterial("armureFougere", 66,new int[]{6, 16, 12, 6}, 20);
	public static EnumToolMaterial materielFougere = EnumHelper.addToolMaterial("materielFougere", 4, 3123, 16.0F, 6, 20);
	   
	
	public static void loadEreGeologiqueItem()
	{
		   PrehistoriqueCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCoalID).setIconIndex(0).setUnlocalizedName("CoalPrehistorique");
	       FougereSeeds = new FougereSeeds(EGProperties.FougereSeedsID).setIconIndex(1).setUnlocalizedName("Fougere");
	       FougereSword = new FougereSword(EGProperties.FougereSwordID, materielFougere).setIconIndex(2).setUnlocalizedName("FougereSword");
	       FougerePickaxe = new FougerePickaxe(EGProperties.FougerePickaxeID, materielFougere).setIconIndex(3).setUnlocalizedName("FougerePickaxe");
	       FougereAxe = new FougereAxe(EGProperties.FougereAxeID, materielFougere).setIconIndex(4).setUnlocalizedName("FougereAxe");
	       FougereShovel = new FougereShovel(EGProperties.FougereShovelID, materielFougere).setIconIndex(5).setUnlocalizedName("FougereShovel");
	       FougereHoe = new FougereHoe(EGProperties.FougereHoeID, materielFougere).setIconIndex(6).setUnlocalizedName("FougereHoe");
	       FougereHelmet = new armureFougere(EGProperties.FougereHelmetID, armureFougere,0,0).setIconIndex(7).setUnlocalizedName("FougereHelmet");
	       FougereChestplate = new armureFougere(EGProperties.FougereChestplateID, armureFougere,1,1).setIconIndex(8).setUnlocalizedName("FougereChestplate");
	       FougereLeggings = new armureFougere(EGProperties.FougereLeggingsID, armureFougere,2,2).setIconIndex(9).setUnlocalizedName("FougereLeggings");
	       FougereBoots = new armureFougere(EGProperties.FougereBootsID, armureFougere,3,3).setIconIndex(10).setUnlocalizedName("FougereBoots");
	       PrehistoriqueCoalDust = new PrehistoriqueCoal(EGProperties.PrehistoriqueCoalDustID).setIconIndex(11).setUnlocalizedName("CoalDustPrehistorique");
	       CoalLiquidItem = new PrehistoriqueCoal(EGProperties.CoalLiquidItemID).setIconIndex(12).setUnlocalizedName("CoalLiquidItem");
	       PrehistoriqueCharCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCharCoalID).setIconIndex(13).setUnlocalizedName("PrehistoriqueCharCoal");
	       PrehistoriqueCokeCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCokeCoalID).setIconIndex(14).setUnlocalizedName("PrehistoriqueCokeCoal");
	       PrehistoriqueIronIngot = new PrehistoriqueIronIngot(EGProperties.PrehistoriqueIronIngotID).setIconIndex(15).setUnlocalizedName("PrehistoriqueIronIngot"); 
	       IvoryIngot = new IvoryIngot(EGProperties.IvoryIngotID).setIconIndex(16).setUnlocalizedName("IvoryIngot");
	       IvoryNugget = new IvoryIngot(EGProperties.IvoryNuggetID).setIconIndex(17).setUnlocalizedName("IvoryNugget");
	       IvoryGear = new IvoryIngot(EGProperties.IvoryGearID).setIconIndex(18).setUnlocalizedName("IvoryGear");
	       CoalLiquidBucket = new PrehistoriqueCoalBucket(EGProperties.CoalLiquidBucketID).setIconIndex(19).setUnlocalizedName("CoalLiquidbucket");
	       FruitFougere = new FruitFougere(EGProperties.FruitFougereID).setIconIndex(20).setUnlocalizedName("FruitFougere");
	       
	       LanguageRegistry.addName(PrehistoriqueCoal, "Charbon Prehistorique");
	       LanguageRegistry.addName(PrehistoriqueCoalDust, "Charbon Mac\351r\351 Prehistorique");
	       LanguageRegistry.addName(CoalLiquidItem, "Charbon Liquide");
	       LanguageRegistry.addName(CoalLiquidBucket, "Seau de Charbon Liquide");
	       LanguageRegistry.addName(PrehistoriqueCharCoal, "Charbon de Bois Prehistorique");
	       LanguageRegistry.addName(PrehistoriqueCokeCoal, "CokeCoal Prehistorique");
	       LanguageRegistry.addName(PrehistoriqueIronIngot, "Fer Prehistorique");
	       LanguageRegistry.addName(IvoryIngot, "Ivoire");
	       LanguageRegistry.addName(IvoryNugget, "P\351pite d'Ivoire");
	       LanguageRegistry.addName(IvoryGear, "Boulon d'Ivoire");
	       LanguageRegistry.addName(FougereSeeds, "Graines de Foug\350re");
	       LanguageRegistry.addName(FruitFougere, "Foug\350re Comestible");
	       LanguageRegistry.addName(FougereSword, "\351p\351e en Foug\350re");
	       LanguageRegistry.addName(FougerePickaxe, "Pioche en Foug\350re");
	       LanguageRegistry.addName(FougereAxe, "Hache en Foug\350re");
	       LanguageRegistry.addName(FougereShovel, "Pelle en Foug\350re");
	       LanguageRegistry.addName(FougereHoe, "Houe en Foug\350re");
	       LanguageRegistry.addName(FougereHelmet, "Casque de Foug\350re");
	       LanguageRegistry.addName(FougereChestplate, "Plastron de Foug\350re");
	       LanguageRegistry.addName(FougereLeggings, "Jambi\350re de Foug\350re");
	       LanguageRegistry.addName(FougereBoots, "Bottes de Foug\350re");
	       
	       CoalLiquid = LiquidDictionary.getOrCreateLiquid("CoalLiquid", new LiquidStack(CoalLiquidItem,1));
	       LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("CoalLiquid", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(CoalLiquidBucket), new ItemStack(Item.bucketEmpty)));
	}
}
