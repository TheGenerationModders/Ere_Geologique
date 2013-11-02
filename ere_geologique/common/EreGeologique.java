package ere_geologique.common;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IChatListener;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.client.EGMessageHandler;
import ere_geologique.client.Localizations;
import ere_geologique.client.RiderInput;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.command.CommandBlockBreak;
import ere_geologique.common.command.CommandHeal;
import ere_geologique.common.command.CommandStarve;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.dimension.EGDimensionList;
import ere_geologique.common.entity.EGEntityList;
import ere_geologique.common.entity.Enums.EnumDinoFoodMob;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.event.FougereBoneMeal;
import ere_geologique.common.event.PlayerTracker;
import ere_geologique.common.gui.GuiHandler;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.recipe.EGRecipe;
import ere_geologique.common.recipe.Integration;
import ere_geologique.common.tileentity.EGTEntityList;
import ere_geologique.common.worldgenerator.FossilGenerator;
import ere_geologique.proxy.EGCommonProxy;

@Mod(modid = "ere_geologique", name = "Ere G\351ologique", version = "1.0.0", dependencies = "required-after:Forge@[9.10.1.870,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class EreGeologique
{
	@SidedProxy(clientSide = "ere_geologique.proxy.EGClientProxy", serverSide = "ere_geologique.proxy.EGCommonProxy")
	public static EGCommonProxy proxy;

	@Instance("ere_geologique")
	public static EreGeologique Instance;
	public static Logger EGLog = Logger.getLogger("EreGeologique");
	public static File ConfigFile;
	public static boolean DebugMode = true;
	public static Object ToPedia;
	public static GuiHandler guiHandler = new GuiHandler();
	public static IChatListener messagerHandler = new EGMessageHandler();
	public static IPacketHandler RiderInput = new RiderInput();

	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		EGLog.setParent(FMLLog.getLogger());
		ConfigFile = new File(event.getModConfigurationDirectory(), "EreGeologique.cfg");
		Configuration cfg = new Configuration(ConfigFile);
		try
		{
			cfg.load();
			//Blocks
			EGProperties.LeavesID = cfg.getBlock("Leaves Foug\350re", 2500).getInt();
			EGProperties.WoodID = cfg.getBlock("Wood Foug\350re", 2501).getInt();
			EGProperties.SaplingID = cfg.getBlock("Sapling Foug\350re", 2502).getInt();
			EGProperties.PlankID = cfg.getBlock("Plank Foug\350re", 2503).getInt();
			EGProperties.SlabID = cfg.getBlock("Slab Foug\350re", 2504).getInt();
			EGProperties.DoubleSlabID = cfg.getBlock("Double Slab Foug\350re", 2505).getInt();
			EGProperties.StairID = cfg.getBlock("Stair Foug\350re", 2506).getInt();
			EGProperties.GlaciaPortalID = cfg.getBlock("GlaciaPortal", 2507).getInt();
			EGProperties.FeederIdleID = cfg.getBlock("FeederIdle", 2508).getInt();
			EGProperties.FeederActiveID = cfg.getBlock("FeederActive", 2509).getInt();
			EGProperties.AnalyzerIdleID = cfg.getBlock("AnalyzerIdle", 2510).getInt();
			EGProperties.AnalyzerActiveID = cfg.getBlock("AnalyzerActive", 2511).getInt();
			EGProperties.CultivatorIdleID = cfg.getBlock("CultivatorIdle", 2512).getInt();
			EGProperties.CultivatorActiveID = cfg.getBlock("CultivatorActive", 2513).getInt();
			EGProperties.FossilID = cfg.getBlock("Fossil", 2513).getInt();
			EGProperties.ReinforcedStoneID = cfg.getBlock("ReinforcedStone", 2514).getInt();
			EGProperties.ReinforcedGlassID = cfg.getBlock("ReinforcedGlass", 2515).getInt();
			EGProperties.SteelBlockID = cfg.getBlock("SteelBlock", 2516).getInt();
			EGProperties.FossilSkullID = cfg.getBlock("FossilSkull", 2517).getInt();
			EGProperties.BlueFireID = cfg.getBlock("BlueFire", 2518).getInt();

			//Items
			EGProperties.IvoryIngotID = cfg.getItem("Ivory Ingot", 4000).getInt();
			EGProperties.IvoryNuggetID = cfg.getItem("Ivory Nugget", 4001).getInt();
			EGProperties.IvoryGearID = cfg.getItem("Ivory Gear", 4002).getInt();
			EGProperties.DinoPediaID = cfg.getItem("DinoPedia", 4003).getInt();
			EGProperties.ChickenEssID = cfg.getItem("ChickenEss", 4004).getInt();
			EGProperties.WhipID = cfg.getItem("Whip", 4005).getInt();
			EGProperties.LegBoneID = cfg.getItem("LegBone", 4006).getInt();
			EGProperties.ClawID = cfg.getItem("Claw", 4007).getInt();
			EGProperties.FootID = cfg.getItem("Foot", 4008).getInt();
			EGProperties.SkullID = cfg.getItem("Skull", 4009).getInt();
			EGProperties.BioFossilID = cfg.getItem("BioFossil", 4010).getInt();
			EGProperties.SkullStickID = cfg.getItem("SkullStick", 4011).getInt();
			EGProperties.gemID = cfg.getItem("Scarac_Gem", 4012).getInt();
			EGProperties.EmptyShellID = cfg.getItem("EmptyShell", 4013).getInt();
			EGProperties.MagicConchID = cfg.getItem("MagiConch", 4014).getInt();
			EGProperties.sjlID = cfg.getItem("sJL", 4015).getInt();
			EGProperties.cookedDinoMeatID = cfg.getItem("cookedDinoMeat", 4016).getInt();
			EGProperties.BrokenSaplingID = cfg.getItem("BrokenSapling", 4017).getInt();
			EGProperties.SteelIngotID = cfg.getItem("SteelIngot", 4018).getInt();
			EGProperties.SteelPlateID = cfg.getItem("SteelPlate", 4019).getInt();
			EGProperties.RelicID = cfg.getItem("Relic", 4020).getInt();
			EGProperties.cookedChickenSoupID = cfg.getItem("cookedChickenSoup", 4021).getInt();
			EGProperties.rawChickenSoupID = cfg.getItem("rawChickenSoup", 4022).getInt();
			EGProperties.FlintAndSteelID = cfg.getItem("FlintAndSteel", 4023).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.EGGIDs[i] = cfg.getItem("Egg" + EnumDinoType.values()[i].name(), 4024+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.RAWIDs[i] = cfg.getItem("raw" + EnumDinoType.values()[i].name(), 4038+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.DNAIDs[i] = cfg.getItem("dna" + EnumDinoType.values()[i].name(), 4052+i).getInt();

			//Dimensions
			EGProperties.GlaciaID = cfg.get("Dimension", "Glacia", 2).getInt();

		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des ID's!");
		}
		finally
		{
			if(cfg.hasChanged())
			{
				cfg.save();
			}
			EreGeologique.EGLog.info("Initialisation des ID's terminés!");
		}

		if(event.getSide().isClient())
		{
			proxy.initSound();//Sounds
		}

		EGCreativeTab.loadCreativeTab();//CreativeTab
		EGBlockList.loadBlock();//Blocks
		EGItemList.loadItem();//Items
		EGAchievement.loadAchievement();//Achievements
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		EGEntityList.loadEntity();//Entity

		//IC2 integration
		if (Loader.isModLoaded("IC2"))
		{
			Integration.loadIndustrialCraft();
			EGLog.info("IC2 integration loaded !");
		}
		//buildcraft integration
		if (Loader.isModLoaded("BuildCraft|Core"))
		{
			Integration.loadBuildCraft();
			EGLog.info("BuilCraft integration loaded !");
		}
		//railcraft integration
		if (Loader.isModLoaded("Railcraft"))
		{
			Integration.loadRailCraft();
			EGLog.info("RailCraft integration loaded !");
		}
		//forestry integration
		if (Loader.isModLoaded("Forestry"))
		{
			Integration.loadForestry();
			EGLog.info("Forestry integration loaded !");
		}

		//buildcraft and industrialcraft
		if (Loader.isModLoaded("BuildCraft|Core") && Loader.isModLoaded("IC2"))
		{
			Integration.loadIC_BC();
		}

		//World Generator
		GameRegistry.registerWorldGenerator(new FossilGenerator());
		
		EGDimensionList.loadDimension();//Dimension

		//Other
		proxy.registerRenderEntity();
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new FougereBoneMeal());
		GameRegistry.registerPlayerTracker(new PlayerTracker());

		EnumDinoType.init();
		EnumDinoFoodMob.init();

		EGTEntityList.loadTileEntity();
		NetworkRegistry.instance().registerGuiHandler(this.Instance, new GuiHandler());
		NetworkRegistry.instance().registerChatListener(messagerHandler);
		NetworkRegistry.instance().registerChannel(RiderInput, "RiderInput");
		NetworkRegistry.instance().registerChannel(RiderInput, "PteroFlight");
	}

	public static void ShowMessage(String var0, EntityPlayer var1)
	{
		if (var1 != null)
		{
			var1.addChatMessage(var0);
		}
	}

	public static void DebugMessage(String string)
	{
		if (DebugMode)
		{
			EGLog.severe(string);
		}
	}

	@EventHandler
	public void postload(FMLPostInitializationEvent event)
	{
		Localizations.loadLanguages();
		EGRecipe.loadRecipe();//Recipe
		EGRecipe.loadSmelting();//Smelting
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandBlockBreak());
		event.registerServerCommand(new CommandHeal());
		event.registerServerCommand(new CommandStarve());
	}

}