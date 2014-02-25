package ere_geologique.common.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.entity.enums.EnumDinoType;

public class EGItemList
{
	public static Item ivoryNugget;
	public static Item ivoryIngot;
	public static Item ivoryGear;
	public static Item dinoPedia;
	public static Item chickenEss;
	public static Item whip;
	public static Item legBone;
	public static Item claw;
	public static Item foot;
	public static Item skull;
	public static Item bioFossil;
	public static Item skullStick;
	public static Item gem;
	public static Item emptyShell;
	public static Item magicConch;
	public static Item sjl;
	public static Item cookedDinoMeat;
	public static Item brokenSapling;
	public static Item steelIngot;
	public static Item steelPlate;
	public static Item relic;
	public static Item rawChickenSoup;
	public static Item cookedChickenSoup;
	public static Item flintAndSteel;
	public static Item archNotebook;
	public static Item tranquilizerDart;
	public static Item gun;
	
	public static void loadItem()
	{
		try
		{
	       ivoryIngot = new IvoryIngot().setUnlocalizedName("IvoryIngot").setTextureName("ere_geologique:IvoryIngot");
	       ivoryNugget = new IvoryIngot().setUnlocalizedName("IvoryNugget").setTextureName("ere_geologique:IvoryNugget");
	       ivoryGear = new IvoryIngot().setUnlocalizedName("IvoryGear").setTextureName("ere_geologique:IvoryGear");
	       dinoPedia = new DinoPedia().setUnlocalizedName("DinoPedia").setTextureName("ere_geologique:Dinopedia");
	       chickenEss = new ChickenEss(10, 0.0F, false, "Essence_Of_Chicken").setUnlocalizedName("ChickenEss").setContainerItem(Items.glass_bottle);
	       whip = new Whip().setUnlocalizedName("Whip").setTextureName("ere_geologique:Whip");
	       legBone = new EGItem("Leg_Bone").setUnlocalizedName("Leg_Bone");
	       claw = new EGItem("Claw").setUnlocalizedName("Claw");
	       foot = new EGItem("Foot").setUnlocalizedName("Foot");
	       skull = new EGItem("Skull").setUnlocalizedName("DinoSkull");
	       bioFossil = new ItemBioFossil().setUnlocalizedName("BioFossil").setTextureName("ere_geologique:Bio_Fossil");
	       skullStick = new EGItem("Skull_Stick").setUnlocalizedName("SkullStick");
	       gem = new EGItem("Scarab_Gem").setUnlocalizedName("gem");
	       emptyShell = new EGItem("Empty_Shell").setUnlocalizedName("EmptyShell");
	       magicConch = new MagicConch().setUnlocalizedName("MagicConch").setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	       sjl = new EGFood(8, 2.0F, false, "Sio_Chiu_Le").setUnlocalizedName("sJL");
	       cookedDinoMeat = new EGFood(8, 0.8F, true,"Dino_Steak").setUnlocalizedName("dinoSteak");
	       brokenSapling = new EGItem("Broken_Sapling").setUnlocalizedName(LocalizationStrings.BROKEN_SAPLING_NAME);
	       steelIngot = new EGItem("SteelIngot").setUnlocalizedName("SteelIngot");
	       steelPlate = new EGItem("SteelPlate").setUnlocalizedName("SteelPlate");
	       relic = new EGItem("Relic_Scrap").setUnlocalizedName("Relic_Srap");
	       cookedChickenSoup = new EGItem("Cooked_Chicken_Soup").setUnlocalizedName("cookedChickenSoup").setMaxStackSize(1).setContainerItem(Items.bucket);
	       rawChickenSoup = new EGItem("Raw_Chicken_Soup").setUnlocalizedName("rawChickenSoup").setMaxStackSize(1).setContainerItem(Items.bucket);
	       flintAndSteel = new FlintAndSteel().setUnlocalizedName("FlintAndSteel");
	       archNotebook = new EGItem("Arch_Notebook").setUnlocalizedName("archNotebook");
	       tranquilizerDart = new TranquilizerDart();
	       gun = new Gun();
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
	       EnumDinoType.values()[i].eggItem = new Egg(i).setUnlocalizedName("egg" + EnumDinoType.values()[i].name()).setCreativeTab(EGCreativeTab.EGCreativeTabItem);
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   EnumDinoType.values()[i].dropItem = new EGFood(3, 0.3F, true, EnumDinoType.values()[i].name() + "_Meat").setUnlocalizedName("raw" + EnumDinoType.values()[i].name());
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   EnumDinoType.values()[i].dnaItem = new EGItem(EnumDinoType.values()[i].name() + "_DNA").setUnlocalizedName("dna" + EnumDinoType.values()[i].name());
	       
	       GameRegistry.registerItem(ivoryIngot, "IvoryIngot", "Ere G\351ologique");
	       GameRegistry.registerItem(ivoryNugget, "IvoryNugget", "Ere G\351ologique");
	       GameRegistry.registerItem(ivoryGear, "IvoryGear", "Ere G\351ologique");
	       GameRegistry.registerItem(dinoPedia, "DinoPedia", "Ere G\351ologique");
	       GameRegistry.registerItem(chickenEss, "ChickenEss", "Ere G\351ologique");
	       GameRegistry.registerItem(whip, "Whip", "Ere G\351ologique");
	       GameRegistry.registerItem(legBone, "LegBone", "Ere G\351ologique");
	       GameRegistry.registerItem(claw, "Claw", "Ere G\351ologique");
	       GameRegistry.registerItem(foot, "Foot", "Ere G\351ologique");
	       GameRegistry.registerItem(skull, "DinoSkull", "Ere G\351ologique");
	       GameRegistry.registerItem(bioFossil, "BioFossil", "Ere G\351ologique");
	       GameRegistry.registerItem(skullStick, "SkullStick", "Ere G\351ologique");
	       GameRegistry.registerItem(gem, "gem", "Ere G\351ologique");
	       GameRegistry.registerItem(emptyShell, "EmptyShell", "Ere G\351ologique");
	       GameRegistry.registerItem(magicConch, "MagicConch", "Ere G\351ologique");
	       GameRegistry.registerItem(sjl, "sjl", "Ere G\351ologique");
	       GameRegistry.registerItem(cookedDinoMeat, "cookedDinoMeat", "Ere G\351ologique");
	       GameRegistry.registerItem(brokenSapling, "BrokenSapling", "Ere G\351ologique");
	       GameRegistry.registerItem(steelIngot, "SteelIngot", "Ere G\351ologique");
	       GameRegistry.registerItem(steelPlate, "SteelPlate", "Ere G\351ologique");
	       GameRegistry.registerItem(relic, "Relic", "Ere G\351ologique");
	       GameRegistry.registerItem(rawChickenSoup, "rawChickenSoup", "Ere G\351ologique");
	       GameRegistry.registerItem(cookedChickenSoup, "cookedChickenSoup", "Ere G\351ologique");
	       GameRegistry.registerItem(flintAndSteel, "FlindAndSteel", "Ere G\351ologique");
	       GameRegistry.registerItem(archNotebook, "archNotebook", "Ere G\351ologique");
	       GameRegistry.registerItem(tranquilizerDart, "tranquilizertDart", "Ere G\351ologique");
	       GameRegistry.registerItem(gun, "gun", "Ere G\351ologique");

	       for(int i=0;i<EnumDinoType.values().length;i++)
	       GameRegistry.registerItem(EnumDinoType.values()[i].eggItem, "egg" + EnumDinoType.values()[i].name(), "Ere G\351ologique");
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   GameRegistry.registerItem(EnumDinoType.values()[i].dropItem, "raw" + EnumDinoType.values()[i].name(), "Ere G\351ologique");
	       
	       for(int i=0;i<EnumDinoType.values().length;i++)
		   GameRegistry.registerItem(EnumDinoType.values()[i].dnaItem, "dna" + EnumDinoType.values()[i].name(), "Ere G\351ologique");
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des items!");
		}
		EreGeologique.EGLog.info("Initialisation des items terminés!");
	}
}