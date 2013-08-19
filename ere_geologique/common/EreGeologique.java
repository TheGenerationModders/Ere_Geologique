package ere_geologique.common;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.entity.EGEntityList;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.recipe.EGRecipe;
import ere_geologique.proxy.EGCommonProxy;

@Mod(modid = "EreG\351ologique", name = "Ere G\351ologique", version = "1.0.0", dependencies = "required-after:Forge@[7.8.1,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class EreGeologique
{
	   @SidedProxy(clientSide = "ere_geologique.proxy.EGClientProxy", serverSide = "ere_geologique.proxy.EGCommonProxy")
	   public static EGCommonProxy proxy;
	   
	   @Instance("EreG\351ologique")
	   public static EreGeologique Instance;
	   public static Logger EGLog = Logger.getLogger("EreGeologique");
	   public static File ConfigFile;
	   
	   @PreInit
	   public void preload(FMLPreInitializationEvent event)
	   {
		   		EGLog.setParent(FMLLog.getLogger());
		   		ConfigFile = new File(event.getModConfigurationDirectory(), "EreGeologique.cfg");
		        Configuration cfg = new Configuration(ConfigFile);
		        cfg.load();
		        //Blocks
		        EGProperties.GrassID = cfg.getBlock("Block Grass", 254).getInt();
		        EGProperties.DirtID = cfg.getBlock("Block Dirt", 255).getInt();
		        EGProperties.LeavesID = cfg.getBlock("Leaves Foug\350re", 2506).getInt();
		        EGProperties.WoodID = cfg.getBlock("Wood Foug\350re", 2507).getInt();
		        EGProperties.SaplingID = cfg.getBlock("Sapling Foug\350re", 2508).getInt();
		        EGProperties.PlankID = cfg.getBlock("Plank Foug\350re", 2509).getInt();
		        EGProperties.SlabID = cfg.getBlock("Slab Foug\350re", 2510).getInt();
		        EGProperties.DoubleSlabID = cfg.getBlock("Double Slab Foug\350re", 2511).getInt();
		        EGProperties.StairID = cfg.getBlock("Stair Foug\350re", 2512).getInt();
		        EGProperties.PrehistoriqueBlockCoalID = cfg.getBlock("Prehistorique Block Coal", 2513).getInt();
		        EGProperties.PrehistoriqueBlockIronID = cfg.getBlock("Prehistorique Block Iron", 2514).getInt();
		        //Items
		        EGProperties.PrehistoriqueCoalID = cfg.getItem("Coal Prehistorique", 20150).getInt();
		        EGProperties.FougereSwordID = cfg.getItem("Sword Foug\350re", 20152).getInt();
		        EGProperties.FougerePickaxeID = cfg.getItem("Pickaxe Foug\350re", 20153).getInt();
		        EGProperties.FougereAxeID = cfg.getItem("Axe Foug\350re", 20154).getInt();
		        EGProperties.FougereShovelID = cfg.getItem("Shovel Foug\350re", 20155).getInt();
		        EGProperties.FougereHoeID = cfg.getItem("Hoe Foug\350re", 20156).getInt();
		        EGProperties.FougereHelmetID = cfg.getItem("Helmet Foug\350re", 20157).getInt();
		        EGProperties.FougereChestplateID = cfg.getItem("Chestplate Foug\350re", 20158).getInt();
		        EGProperties.FougereLeggingsID = cfg.getItem("Leggings Foug\350re", 20159).getInt();
		        EGProperties.FougereBootsID = cfg.getItem("Boots Foug\350re", 20160).getInt();
		        EGProperties.PrehistoriqueCoalDustID = cfg.getItem("Coal Dust Prehistorique", 20161).getInt();
		        EGProperties.CoalLiquidItemID = cfg.getItem("Coal Liquid", 20162).getInt();
		        EGProperties.PrehistoriqueCharCoalID = cfg.getItem("CharCoal Prehistorique", 20163).getInt();
		        EGProperties.PrehistoriqueCokeCoalID = cfg.getItem("CokeCoal Prehistorique", 20164).getInt();
		        EGProperties.PrehistoriqueIronIngotID = cfg.getItem("Iron Ingot Prehistorique", 20165).getInt();
		        EGProperties.IvoryIngotID = cfg.getItem("Ivory Ingot", 20166).getInt();
		        EGProperties.IvoryNuggetID = cfg.getItem("Ivory Nugget", 20167).getInt();
		        EGProperties.IvoryGearID = cfg.getItem("Ivory Gear", 20168).getInt();
		        EGProperties.CoalLiquidBucketID = cfg.getItem("Coal Liquid Bucket", 20169).getInt();
		        //Others
		        EGProperties.FCoalLiquidMJ = cfg.get("Forestry integration", "MJ produced by Coal Liquid", 4).getInt();
		        EGProperties.FCoalLiquidDurability = cfg.get("Forestry integration", "Durability of Coal Liquid", 200000).getInt();
		        cfg.save();
		        
		        EGCreativeTab.loadCreativeTab();//CreativeTab
				EGBlockList.loadBlock();//Block
				EGItemList.loadItem();//Item
	   }
	   
	   @Init
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
	       GameRegistry.registerWorldGenerator(new EreGeologiqueGeneration());
	       
	       //Other
		   proxy.registerRenderEntity();
		   proxy.registerRender();
	       MinecraftForge.EVENT_BUS.register(new FougereBoneMeal());
	   }
	   
	   @PostInit
	   public void postload(FMLPostInitializationEvent event)
	   {
	      LanguageRegistry.instance().loadLocalization("/mods/EreGeologique/lang/en_US.lang", "en_US", false);
	      LanguageRegistry.instance().loadLocalization("/mods/EreGeologique/lang/fr_FR.lang", "fr_FR", false);
	      EGRecipe.loadRecipe();//Recipe
	      EGRecipe.loadSmelting();//Smelting
	   }

}