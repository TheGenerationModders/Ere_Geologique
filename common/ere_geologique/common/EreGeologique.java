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
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.api.food.EnumDinoFoodMob;
import ere_geologique.client.EGMessageHandler;
import ere_geologique.client.Localizations;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.config.ConfigFile;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.config.Version;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.dimension.EGDimensionList;
import ere_geologique.common.entity.EGEntityList;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.event.CraftingHandler;
import ere_geologique.common.event.FougereBoneMeal;
import ere_geologique.common.event.PickupHandler;
import ere_geologique.common.event.PlayerTracker;
import ere_geologique.common.food.FoodList;
import ere_geologique.common.gui.GuiHandler;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.recipe.EGRecipe;
import ere_geologique.common.recipe.Integration;
import ere_geologique.common.tileentity.EGTEntityList;
import ere_geologique.common.worldgenerator.FossilGenerator;
import ere_geologique.proxy.EGCommonProxy;
import ere_geologique.proxy.network.ServerPacketHandler;

@Mod(modid = "Ere G\351ologique", name = "Ere G\351ologique", version = Version.VERSION, dependencies = "required-after:Forge@[9.10.1.870,)", acceptedMinecraftVersions=Version.MC_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, serverPacketHandlerSpec = @SidedPacketHandler(channels = {"EreGeologique"}, packetHandler = ServerPacketHandler.class))

public class EreGeologique
{
	@SidedProxy(clientSide = "ere_geologique.proxy.EGClientProxy", serverSide = "ere_geologique.proxy.EGCommonProxy")
	public static EGCommonProxy proxy;

	@Instance("Ere G\351ologique")
	public static EreGeologique Instance;
	public static Logger EGLog = Logger.getLogger("EreGeologique");
	public static ConfigFile ConfigFile;
	public static Object ToPedia;
	public static GuiHandler guiHandler = new GuiHandler();
	public static IChatListener messagerHandler = new EGMessageHandler();

	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		EGLog.setParent(FMLLog.getLogger());
		ConfigFile = new ConfigFile(new File(event.getModConfigurationDirectory(), "EreGeologique.cfg"));
		try
		{
			ConfigFile.load();
			
			EGProperties.updateCheck = ConfigFile.get(Configuration.CATEGORY_GENERAL, "update.check", true);
			EGProperties.updateCheck.comment = "set to true for version check on startup";
			if (EGProperties.updateCheck.getBoolean(true)) {
				Version.check();
			}
			
			//Blocks
			EGProperties.LeavesID = ConfigFile.getBlock("Leaves Foug\350re", 2500).getInt();
			EGProperties.WoodID = ConfigFile.getBlock("Wood Foug\350re", 2501).getInt();
			EGProperties.SaplingID = ConfigFile.getBlock("Sapling Foug\350re", 2502).getInt();
			EGProperties.PlankID = ConfigFile.getBlock("Plank Foug\350re", 2503).getInt();
			EGProperties.SlabID = ConfigFile.getBlock("Slab Foug\350re", 2504).getInt();
			EGProperties.DoubleSlabID = ConfigFile.getBlock("Double Slab Foug\350re", 2505).getInt();
			EGProperties.StairFougereID = ConfigFile.getBlock("Stair Foug\350re", 2506).getInt();
			EGProperties.StairCycasID = ConfigFile.getBlock("Stair Cycas", 2507).getInt();
			EGProperties.StairAraucariasID = ConfigFile.getBlock("Stair Araucarias", 2508).getInt();
			EGProperties.StairMetasequoiasID = ConfigFile.getBlock("Stair M\351tas\351quoias", 2509).getInt();
			EGProperties.StairGingkosID = ConfigFile.getBlock("Stair Gingkos", 2510).getInt();
			EGProperties.GlaciaPortalID = ConfigFile.getBlock("GlaciaPortal", 2511).getInt();
			EGProperties.FeederID = ConfigFile.getBlock("Feeder", 2512).getInt();
			EGProperties.AnalyzerID = ConfigFile.getBlock("AnalyzerIdle", 2513).getInt();
			EGProperties.CultivatorIdleID = ConfigFile.getBlock("CultivatorIdle", 2514).getInt();
			EGProperties.CultivatorActiveID = ConfigFile.getBlock("CultivatorActive", 2515).getInt();
			EGProperties.FossilID = ConfigFile.getBlock("Fossil", 2516).getInt();
			EGProperties.ReinforcedStoneID = ConfigFile.getBlock("ReinforcedStone", 2517).getInt();
			EGProperties.ReinforcedGlassID = ConfigFile.getBlock("ReinforcedGlass", 2518).getInt();
			EGProperties.SteelBlockID = ConfigFile.getBlock("SteelBlock", 2519).getInt();
			EGProperties.FossilSkullID = ConfigFile.getBlock("FossilSkull", 2520).getInt();
			EGProperties.BlueFireID = ConfigFile.getBlock("BlueFire", 2521).getInt();
			EGProperties.DrumID = ConfigFile.getBlock("Drum", 2522).getInt();
			
			//Items
			EGProperties.IvoryIngotID = ConfigFile.getItem("Ivory Ingot", 4000).getInt();
			EGProperties.IvoryNuggetID = ConfigFile.getItem("Ivory Nugget", 4001).getInt();
			EGProperties.IvoryGearID = ConfigFile.getItem("Ivory Gear", 4002).getInt();
			EGProperties.DinoPediaID = ConfigFile.getItem("DinoPedia", 4003).getInt();
			EGProperties.ChickenEssID = ConfigFile.getItem("ChickenEss", 4004).getInt();
			EGProperties.WhipID = ConfigFile.getItem("Whip", 4005).getInt();
			EGProperties.LegBoneID = ConfigFile.getItem("LegBone", 4006).getInt();
			EGProperties.ClawID = ConfigFile.getItem("Claw", 4007).getInt();
			EGProperties.FootID = ConfigFile.getItem("Foot", 4008).getInt();
			EGProperties.SkullID = ConfigFile.getItem("Skull", 4009).getInt();
			EGProperties.BioFossilID = ConfigFile.getItem("BioFossil", 4010).getInt();
			EGProperties.SkullStickID = ConfigFile.getItem("SkullStick", 4011).getInt();
			EGProperties.gemID = ConfigFile.getItem("Scarac_Gem", 4012).getInt();
			EGProperties.EmptyShellID = ConfigFile.getItem("EmptyShell", 4013).getInt();
			EGProperties.MagicConchID = ConfigFile.getItem("MagiConch", 4014).getInt();
			EGProperties.sjlID = ConfigFile.getItem("sJL", 4015).getInt();
			EGProperties.cookedDinoMeatID = ConfigFile.getItem("cookedDinoMeat", 4016).getInt();
			EGProperties.BrokenSaplingID = ConfigFile.getItem("BrokenSapling", 4017).getInt();
			EGProperties.SteelIngotID = ConfigFile.getItem("SteelIngot", 4018).getInt();
			EGProperties.SteelPlateID = ConfigFile.getItem("SteelPlate", 4019).getInt();
			EGProperties.RelicID = ConfigFile.getItem("Relic", 4020).getInt();
			EGProperties.cookedChickenSoupID = ConfigFile.getItem("cookedChickenSoup", 4021).getInt();
			EGProperties.rawChickenSoupID = ConfigFile.getItem("rawChickenSoup", 4022).getInt();
			EGProperties.FlintAndSteelID = ConfigFile.getItem("FlintAndSteel", 4023).getInt();
			EGProperties.archNotebookID = ConfigFile.getItem("arckNotebook", 4024).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.EGGIDs[i] = ConfigFile.getItem("Egg" + EnumDinoType.values()[i].name(), 4025+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.RAWIDs[i] = ConfigFile.getItem("raw" + EnumDinoType.values()[i].name(), 4039+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.DNAIDs[i] = ConfigFile.getItem("dna" + EnumDinoType.values()[i].name(), 4053+i).getInt();

			//Dimensions
			EGProperties.GlaciaID = ConfigFile.get("Dimension", "Glacia", 2).getInt();

		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des ID's!");
		}
		finally
		{
			if(ConfigFile.hasChanged())
			{
				ConfigFile.save();
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
		FoodList.load();

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
		GameRegistry.registerCraftingHandler(new CraftingHandler());
		GameRegistry.registerPickupHandler(new PickupHandler());

		EnumDinoType.init();
		EnumDinoFoodMob.init();

		EGTEntityList.loadTileEntity();
		NetworkRegistry.instance().registerGuiHandler(this.Instance, new GuiHandler());
		NetworkRegistry.instance().registerChatListener(messagerHandler);
	}

	public static void ShowMessage(String string, EntityPlayer player)
	{
		if (player != null)
		{
			player.addChatMessage(string);
		}
	}

	public static void DebugMessage(String string)
	{
		if (CommandDino.Debugmode)
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
		event.registerServerCommand(new CommandDino());
	}
}