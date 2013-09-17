package ere_geologique.common;

import java.io.File;
import java.util.logging.Logger;

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
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.entity.EGEntityList;
import ere_geologique.common.event.FougereBoneMeal;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.recipe.EGRecipe;
import ere_geologique.common.recipe.Integration;
import ere_geologique.proxy.EGCommonProxy;

@Mod(modid = "ere_geologique", name = "Ere G\351ologique", version = "1.0.0", dependencies = "required-after:Forge@[7.8.1,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class EreGeologique
{
	   @SidedProxy(clientSide = "ere_geologique.proxy.EGClientProxy", serverSide = "ere_geologique.proxy.EGCommonProxy")
	   public static EGCommonProxy proxy;
	   
	   @Instance("ere_geologique")
	   public static EreGeologique Instance;
	   public static Logger EGLog = Logger.getLogger("EreGeologique");
	   public static File ConfigFile;
	   
	   @EventHandler
	   public void preload(FMLPreInitializationEvent event)
	   {
		   		EGLog.setParent(FMLLog.getLogger());
		   		ConfigFile = new File(event.getModConfigurationDirectory(), "EreGeologique.cfg");
		        Configuration cfg = new Configuration(ConfigFile);
		        cfg.load();
		        //Blocks
		        EGProperties.LeavesID = cfg.getBlock("Leaves Foug\350re", 2506).getInt();
		        EGProperties.WoodID = cfg.getBlock("Wood Foug\350re", 2507).getInt();
		        EGProperties.SaplingID = cfg.getBlock("Sapling Foug\350re", 2508).getInt();
		        EGProperties.PlankID = cfg.getBlock("Plank Foug\350re", 2509).getInt();
		        EGProperties.SlabID = cfg.getBlock("Slab Foug\350re", 2510).getInt();
		        EGProperties.DoubleSlabID = cfg.getBlock("Double Slab Foug\350re", 2511).getInt();
		        EGProperties.StairID = cfg.getBlock("Stair Foug\350re", 2512).getInt();
		        EGProperties.PortalID = cfg.getBlock("Portal", 2513).getInt();

		        //Items
		        EGProperties.IvoryIngotID = cfg.getItem("Ivory Ingot", 4000).getInt();
		        EGProperties.IvoryNuggetID = cfg.getItem("Ivory Nugget", 4001).getInt();
		        EGProperties.IvoryGearID = cfg.getItem("Ivory Gear", 4002).getInt();
		        
		        //Dimensions
		        EGProperties.GlaciaID = cfg.get("Dimension", "Glacia",-2).getInt();
		        cfg.save();
		        
		        EGCreativeTab.loadCreativeTab();//CreativeTab
				EGBlockList.loadBlock();//Block
				EGItemList.loadItem();//Item
	   }
	   
	   @EventHandler
	   public void load(FMLInitializationEvent event)
	   {
		   EGEntityList.loadEntity();//Entity
	       
	       //IC2 integration
	       if (Loader.isModLoaded("IC2"))
	       {
	    	   Integration.loadIndustrialCraft();
	    	   System.out.println("IC2 macerator recipe enabled");
	       }
	       //buildcraft integration
	       if (Loader.isModLoaded("BuildCraft|Core"))
	       {
	    	   Integration.loadBuildCraft();
	    	   System.out.println("BuilCraft integration loaded !");
	       }
	       //railcraft integration
	       if (Loader.isModLoaded("Railcraft"))
	       {
	    	   Integration.loadRailCraft();
	    	   System.out.println("RailCraft integration loaded !");
	       }
	       //forestry integration
	       if (Loader.isModLoaded("Forestry"))
	       {
	    	   Integration.loadForestry();
	    	   System.out.println("Forestry integration loaded !");
	       }
	       
	       //builcraft and industrialcraft
	       if (Loader.isModLoaded("BuildCraft|Core") && Loader.isModLoaded("IC2"))
	       {
	    	   Integration.loadIC_BC();
	       }
	       
	       //World Generator
	       
	       //Other
		   proxy.registerRenderEntity();
		   proxy.registerRender();
	       MinecraftForge.EVENT_BUS.register(new FougereBoneMeal());
	   }
	   
	   @EventHandler
	   public void postload(FMLPostInitializationEvent event)
	   {
	      LanguageRegistry.instance().loadLocalization("/assets/ere_geologique/lang/en_US.lang", "en_US", false);
	      LanguageRegistry.instance().loadLocalization("/assets/ere_geologique/lang/fr_FR.lang", "fr_FR", false);
	      EGRecipe.loadRecipe();//Recipe
	      EGRecipe.loadSmelting();//Smelting
	   }

}