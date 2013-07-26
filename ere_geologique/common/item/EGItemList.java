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
import ere_geologique.common.config.EGProperties;

public class EGItemList
{
	public static Item PrehistoriqueCoal;
	public static Item PrehistoriqueCoalDust;
	public static Item PrehistoriqueCokeCoal;
	public static Item PrehistoriqueCharCoal;
	public static Item PrehistoriqueIronIngot;
	public static Item IvoryNugget;
	public static Item IvoryIngot;
	public static Item IvoryGear;
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
	   
	
	public static void loadItem()
	{
		   PrehistoriqueCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCoalID).setUnlocalizedName("CoalPrehistorique");
	       FougereSword = new FougereSword(EGProperties.FougereSwordID, materielFougere).setUnlocalizedName("FougereSword");
	       FougerePickaxe = new FougerePickaxe(EGProperties.FougerePickaxeID, materielFougere).setUnlocalizedName("FougerePickaxe");
	       FougereAxe = new FougereAxe(EGProperties.FougereAxeID, materielFougere).setUnlocalizedName("FougereAxe");
	       FougereShovel = new FougereShovel(EGProperties.FougereShovelID, materielFougere).setUnlocalizedName("FougereShovel");
	       FougereHoe = new FougereHoe(EGProperties.FougereHoeID, materielFougere).setUnlocalizedName("FougereHoe");
	       FougereHelmet = new armureFougere(EGProperties.FougereHelmetID, armureFougere,0,0).setUnlocalizedName("FougereHelmet");
	       FougereChestplate = new armureFougere(EGProperties.FougereChestplateID, armureFougere,1,1).setUnlocalizedName("FougereChestplate");
	       FougereLeggings = new armureFougere(EGProperties.FougereLeggingsID, armureFougere,2,2).setUnlocalizedName("FougereLeggings");
	       FougereBoots = new armureFougere(EGProperties.FougereBootsID, armureFougere,3,3).setUnlocalizedName("FougereBoots");
	       PrehistoriqueCoalDust = new PrehistoriqueCoal(EGProperties.PrehistoriqueCoalDustID).setUnlocalizedName("CoalDustPrehistorique");
	       CoalLiquidItem = new PrehistoriqueCoal(EGProperties.CoalLiquidItemID).setUnlocalizedName("CoalLiquidItem");
	       PrehistoriqueCharCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCharCoalID).setUnlocalizedName("PrehistoriqueCharCoal");
	       PrehistoriqueCokeCoal = new PrehistoriqueCoal(EGProperties.PrehistoriqueCokeCoalID).setUnlocalizedName("PrehistoriqueCokeCoal");
	       PrehistoriqueIronIngot = new PrehistoriqueIronIngot(EGProperties.PrehistoriqueIronIngotID).setUnlocalizedName("PrehistoriqueIronIngot"); 
	       IvoryIngot = new IvoryIngot(EGProperties.IvoryIngotID).setUnlocalizedName("IvoryIngot");
	       IvoryNugget = new IvoryIngot(EGProperties.IvoryNuggetID).setUnlocalizedName("IvoryNugget");
	       IvoryGear = new IvoryIngot(EGProperties.IvoryGearID).setUnlocalizedName("IvoryGear");
	       CoalLiquidBucket = new PrehistoriqueCoalBucket(EGProperties.CoalLiquidBucketID).setUnlocalizedName("CoalLiquidbucket");
	       
	       CoalLiquid = LiquidDictionary.getOrCreateLiquid("CoalLiquid", new LiquidStack(CoalLiquidItem,1));
	       LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("CoalLiquid", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(CoalLiquidBucket), new ItemStack(Item.bucketEmpty)));
	}
}
