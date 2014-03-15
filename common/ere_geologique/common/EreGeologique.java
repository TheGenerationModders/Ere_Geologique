package ere_geologique.common;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.api.food.EnumDinoFoodMob;
import ere_geologique.client.Localizations;
import ere_geologique.common.achievement.EGAchievement;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.config.ConfigFile;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.config.Version;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.entity.EGEntityList;
import ere_geologique.common.entity.enums.EnumDinoType;
import ere_geologique.common.event.CraftingHandler;
import ere_geologique.common.event.FougereBoneMeal;
import ere_geologique.common.event.PickupHandler;
import ere_geologique.common.event.PlayerTracker;
import ere_geologique.common.food.FoodList;
import ere_geologique.common.gui.GuiHandler;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.item.Gun;
import ere_geologique.common.recipe.EGRecipe;
import ere_geologique.common.recipe.Integration;
import ere_geologique.common.tileentity.EGTEntityList;
import ere_geologique.common.worldgenerator.FossilGenerator;
import ere_geologique.proxy.EGCommonProxy;
//import cpw.mods.fml.common.network.IChatListener;
//import ere_geologique.client.EGMessageHandler;
//import ere_geologique.common.dimension.EGDimensionList;
//import ere_geologique.proxy.network.ServerPacketHandler;
//import ere_geologique.proxy.network.TickHandlerClient;

@Mod(modid = "Ere_G\351ologique", name = "Ere G\351ologique", version = Version.VERSION, dependencies = "required-after:Forge@[9.10.1.870,)", acceptedMinecraftVersions=Version.MC_VERSION)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false, serverPacketHandlerSpec = @SidedPacketHandler(channels = {"EreGeologique"}, packetHandler = ServerPacketHandler.class))

public class EreGeologique
{
	@SidedProxy(clientSide = "ere_geologique.proxy.EGClientProxy", serverSide = "ere_geologique.proxy.EGCommonProxy")
	public static EGCommonProxy proxy;

	@Instance("Ere_G\351ologique")
	public static EreGeologique instance;
	public static Logger egLog = Logger.getLogger("EreGeologique");
	public static ConfigFile configFile;
	public static Object toPedia;
	public static GuiHandler guiHandler = new GuiHandler();
//	public static IChatListener messagerHandler = new EGMessageHandler();
//	public static TickHandlerClient tickHandlerClient = new TickHandlerClient();

	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		//EGLog.setParent((Logger) FMLLog.getLogger());
		configFile = new ConfigFile(new File(event.getModConfigurationDirectory(), "EreGeologique.cfg"));
		try
		{
			configFile.load();
			
			EGProperties.updateCheck = configFile.get(Configuration.CATEGORY_GENERAL, "update.check", true);
			EGProperties.updateCheck.comment = "set to true for version check on startup";
			if (EGProperties.updateCheck.getBoolean(true)) {
				Version.check();
			}
			
			/*//Blocks
			EGProperties.leavesID = configFile.getBlock("Leaves Foug\350re", 2500).getInt();
			EGProperties.woodID = configFile.getBlock("Wood Foug\350re", 2501).getInt();
			EGProperties.saplingID = configFile.getBlock("Sapling Foug\350re", 2502).getInt();
			EGProperties.plankID = configFile.getBlock("Plank Foug\350re", 2503).getInt();
			EGProperties.slabID = configFile.getBlock("Slab Foug\350re", 2504).getInt();
			EGProperties.doubleSlabID = configFile.getBlock("Double Slab Foug\350re", 2505).getInt();
			EGProperties.stairFougereID = configFile.getBlock("Stair Foug\350re", 2506).getInt();
			EGProperties.stairCycasID = configFile.getBlock("Stair Cycas", 2507).getInt();
			EGProperties.stairAraucariasID = configFile.getBlock("Stair Araucarias", 2508).getInt();
			EGProperties.stairMetasequoiasID = configFile.getBlock("Stair M\351tas\351quoias", 2509).getInt();
			EGProperties.stairGingkosID = configFile.getBlock("Stair Gingkos", 2510).getInt();
			EGProperties.glaciaPortalID = configFile.getBlock("GlaciaPortal", 2511).getInt();
			EGProperties.feederID = configFile.getBlock("Feeder", 2512).getInt();
			EGProperties.analyzerID = configFile.getBlock("AnalyzerIdle", 2513).getInt();
			EGProperties.cultivatorIdleID = configFile.getBlock("CultivatorIdle", 2514).getInt();
			EGProperties.cultivatorActiveID = configFile.getBlock("CultivatorActive", 2515).getInt();
			EGProperties.fossilID = configFile.getBlock("Fossil", 2516).getInt();
			EGProperties.reinforcedStoneID = configFile.getBlock("ReinforcedStone", 2517).getInt();
			EGProperties.reinforcedGlassID = configFile.getBlock("ReinforcedGlass", 2518).getInt();
			EGProperties.steelBlockID = configFile.getBlock("SteelBlock", 2519).getInt();
			EGProperties.fossilSkullID = configFile.getBlock("FossilSkull", 2520).getInt();
			EGProperties.blueFireID = configFile.getBlock("BlueFire", 2521).getInt();
			EGProperties.drumID = configFile.getBlock("Drum", 2522).getInt();
			
			//Items
			EGProperties.ivoryIngotID = configFile.getItem("Ivory Ingot", 4000).getInt();
			EGProperties.ivoryNuggetID = configFile.getItem("Ivory Nugget", 4001).getInt();
			EGProperties.ivoryGearID = configFile.getItem("Ivory Gear", 4002).getInt();
			EGProperties.dinoPediaID = configFile.getItem("DinoPedia", 4003).getInt();
			EGProperties.chickenEssID = configFile.getItem("ChickenEss", 4004).getInt();
			EGProperties.whipID = configFile.getItem("Whip", 4005).getInt();
			EGProperties.legBoneID = configFile.getItem("LegBone", 4006).getInt();
			EGProperties.clawID = configFile.getItem("Claw", 4007).getInt();
			EGProperties.footID = configFile.getItem("Foot", 4008).getInt();
			EGProperties.skullID = configFile.getItem("Skull", 4009).getInt();
			EGProperties.bioFossilID = configFile.getItem("BioFossil", 4010).getInt();
			EGProperties.skullStickID = configFile.getItem("SkullStick", 4011).getInt();
			EGProperties.gemID = configFile.getItem("Scarac_Gem", 4012).getInt();
			EGProperties.emptyShellID = configFile.getItem("EmptyShell", 4013).getInt();
			EGProperties.magicConchID = configFile.getItem("MagiConch", 4014).getInt();
			EGProperties.sjlID = configFile.getItem("sJL", 4015).getInt();
			EGProperties.cookedDinoMeatID = configFile.getItem("cookedDinoMeat", 4016).getInt();
			EGProperties.brokenSaplingID = configFile.getItem("BrokenSapling", 4017).getInt();
			EGProperties.steelIngotID = configFile.getItem("SteelIngot", 4018).getInt();
			EGProperties.steelPlateID = configFile.getItem("SteelPlate", 4019).getInt();
			EGProperties.relicID = configFile.getItem("Relic", 4020).getInt();
			EGProperties.cookedChickenSoupID = configFile.getItem("cookedChickenSoup", 4021).getInt();
			EGProperties.rawChickenSoupID = configFile.getItem("rawChickenSoup", 4022).getInt();
			EGProperties.flintAndSteelID = configFile.getItem("FlintAndSteel", 4023).getInt();
			EGProperties.archNotebookID = configFile.getItem("arckNotebook", 4024).getInt();
			EGProperties.tranquilizerDartID = configFile.getItem("tranquilizerDart", 4025).getInt();
			EGProperties.gunID = configFile.getItem("gun", 4026).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.eggIDs[i] = configFile.getItem("Egg" + EnumDinoType.values()[i].name(), 4027+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.rawIDs[i] = configFile.getItem("raw" + EnumDinoType.values()[i].name(), 4041+i).getInt();

			for(int i=0;i<EnumDinoType.values().length;i++)
			EGProperties.dnaIDs[i] = configFile.getItem("dna" + EnumDinoType.values()[i].name(), 4055+i).getInt();

			//Dimensions
			EGProperties.glaciaID = configFile.get("Dimension", "Glacia", 2).getInt();*/

		}
		catch(Exception ex)
		{
			EreGeologique.egLog.severe("Erreur lors de l'initialisation des ID's!");
		}
		finally
		{
			if(configFile.hasChanged())
			{
				configFile.save();
			}
			EreGeologique.egLog.info("Initialisation des ID's terminés!");
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
			egLog.info("IC2 integration loaded !");
		}
		//buildcraft integration
		if (Loader.isModLoaded("BuildCraft|Core"))
		{
			Integration.loadBuildCraft();
			egLog.info("BuilCraft integration loaded !");
		}
		//railcraft integration
		if (Loader.isModLoaded("Railcraft"))
		{
			Integration.loadRailCraft();
			egLog.info("RailCraft integration loaded !");
		}
		//forestry integration
		if (Loader.isModLoaded("Forestry"))
		{
			Integration.loadForestry();
			egLog.info("Forestry integration loaded !");
		}

		//buildcraft and industrialcraft
		if (Loader.isModLoaded("BuildCraft|Core") && Loader.isModLoaded("IC2"))
		{
			Integration.loadIC_BC();
		}

		//World Generator
		GameRegistry.registerWorldGenerator(new FossilGenerator(), 0);
		
		//EGDimensionList.loadDimension();//Dimension

		//Other
		proxy.registerRenderEntity();
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new FougereBoneMeal());
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new PlayerTracker());
		MinecraftForge.EVENT_BUS.register(new CraftingHandler());
		MinecraftForge.EVENT_BUS.register(new PickupHandler());

		EnumDinoType.init();
		EnumDinoFoodMob.init();

		EGTEntityList.loadTileEntity();
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
//		NetworkRegistry.INSTANCE.registerChatListener(messagerHandler);
//		TickEvent.registerTickHandler(this.tickHandlerClient, Side.CLIENT);
	}

	public static void ShowMessage(String string, EntityPlayer player)
	{
		if (player != null)
		{
			player.addChatComponentMessage(new ChatComponentText(string));
		}
	}

	public static void DebugMessage(String string)
	{
		if (CommandDino.debugMode)
		{
			egLog.severe(string);
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
	
	@SubscribeEvent
	public void preRenderPlayer(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack is = player.getCurrentEquippedItem();
		if ((is != null) && ((is.getItem() instanceof Gun)))
		{
			ModelBiped modelMain = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1);
			ModelBiped modelArmorChestplate = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 2);
			ModelBiped modelArmor = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 3);
			modelMain.aimedBow = modelArmorChestplate.aimedBow = modelArmor.aimedBow = true;
		}
	}
}