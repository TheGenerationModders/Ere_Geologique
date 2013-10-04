package ere_geologique.common.item;

import net.minecraft.item.Item;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class EGItemList
{
	public static Item IvoryNugget;
	public static Item IvoryIngot;
	public static Item IvoryGear;
	public static Item DinoPedia;
	public static Item ChickenEss;
	public static Item Whip;
	public static Item LegBone;
	public static Item Claw;
	public static Item Foot;
	public static Item Skull;
	public static Item BioFossil;
	public static Item SkullStick;
	public static Item gem;
	public static Item EmptyShell;
	public static Item MagicConch;
	public static Item sjl;
	public static Item cookedDinoMeat;
	public static Item BrokenSapling;
	public static Item SteelIngot;
	public static Item SteelPlate;
	public static Item Relic;
	public static Item rawChickenSoup;
	public static Item cookedChickenSoup;
	
	public static void loadItem()
	{
		try
		{
	       IvoryIngot = new IvoryIngot(EGProperties.IvoryIngotID).setUnlocalizedName("IvoryIngot").setTextureName("ere_geologique:IvoryIngot");
	       IvoryNugget = new IvoryIngot(EGProperties.IvoryNuggetID).setUnlocalizedName("IvoryNugget").setTextureName("ere_geologique:IvoryNugget");
	       IvoryGear = new IvoryIngot(EGProperties.IvoryGearID).setUnlocalizedName("IvoryGear").setTextureName("ere_geologique:IvoryGear");
	       DinoPedia = new DinoPedia(EGProperties.DinoPediaID).setUnlocalizedName("DinoPedia").setTextureName("ere_geologique:Dinopedia");
	       ChickenEss = new ChickenEss(EGProperties.ChickenEssID, 10, 0.0F, false, "Essence_Of_Chicken").setUnlocalizedName("ChickenEss").setContainerItem(Item.glassBottle);
	       Whip = new Whip(EGProperties.WhipID).setUnlocalizedName("Whip").setTextureName("ere_geologique:Whip");
	       LegBone = new EGItem(EGProperties.LegBoneID,"Leg_Bone").setUnlocalizedName("Leg_Bone");
	       Claw = new EGItem(EGProperties.ClawID,"Claw").setUnlocalizedName("Claw");
	       Foot = new EGItem(EGProperties.FootID,"Foot").setUnlocalizedName("Foot");
	       Skull = new EGItem(EGProperties.SkullID,"Skull").setUnlocalizedName("Skull");
	       BioFossil = new ItemBioFossil(EGProperties.BioFossilID).setUnlocalizedName("BioFossil").setTextureName("ere_geologique:Bio_Fossil");
	       SkullStick = new EGItem(EGProperties.SkullStickID, "Skull_Stick").setUnlocalizedName("SkullStick");
	       gem = new EGItem(EGProperties.gemID, "Scarab_Gem").setUnlocalizedName("gem");
	       EmptyShell = new EGItem(EGProperties.EmptyShellID, "Empty_Shell").setUnlocalizedName("EmptyShell");
	       MagicConch = new MagicConch(EGProperties.MagicConchID).setUnlocalizedName("MagicConch").setCreativeTab(EGCreativeTab.EGCreativeTab);
	       sjl = new EGFood(EGProperties.sjlID, 8, 2.0F, false, "Sio_Chiu_Le").setUnlocalizedName("sJL");
	       cookedDinoMeat = new EGFood(EGProperties.cookedDinoMeatID, 8, 0.8F, true,"Dino_Steak").setUnlocalizedName("dinoSteak");
	       BrokenSapling = new EGItem(EGProperties.BrokenSaplingID, "Broken_Sapling").setUnlocalizedName(LocalizationStrings.BROKEN_SAPLING_NAME);
	       SteelIngot = new EGItem(EGProperties.SteelIngotID, "SteelIngot").setUnlocalizedName("SteelIngot");
	       SteelPlate = new EGItem(EGProperties.SteelPlateID, "SteelPlate").setUnlocalizedName("SteelPlate");
	       Relic = new EGItem(EGProperties.RelicID, "Relic_Scrap").setUnlocalizedName("Relic_Srap");
	       cookedChickenSoup = new EGItem(EGProperties.cookedChickenSoupID, "Cooked_Chicken_Soup").setUnlocalizedName("cookedChickenSoup").setMaxStackSize(1).setContainerItem(Item.bucketEmpty);
	       rawChickenSoup = new EGItem(EGProperties.rawChickenSoupID, "Raw_Chicken_Soup").setUnlocalizedName("rawChickenSoup").setMaxStackSize(1).setContainerItem(Item.bucketEmpty);
	       for(int i=0;i<EnumDinoType.values().length;i++)
	       EnumDinoType.values()[i].EggItem = new Egg(EGProperties.EGGIDs[i], i).setUnlocalizedName("egg"+EnumDinoType.values()[i].name()).setCreativeTab(EGCreativeTab.EGCreativeTab);
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   EnumDinoType.values()[i].DropItem = new EGFood(EGProperties.RAWIDs[i], 3, 0.3F, true, EnumDinoType.values()[i].name() + "_Meat").setUnlocalizedName("raw" + EnumDinoType.values()[i].name());
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   EnumDinoType.values()[i].DNAItem = new EGItem(EGProperties.DNAIDs[i], EnumDinoType.values()[i].name() + "_DNA").setUnlocalizedName("dna" + EnumDinoType.values()[i].name());
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des items!");
		}
		EreGeologique.EGLog.info("Initialisation des items terminés!");
	}
}