package ere_geologique.common;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.entity.EreGeologiqueEntityList;
import ere_geologique.common.item.EreGeologiqueItemList;
import ere_geologique.common.recipe.EreGeologique_recipe;
import ere_geologique.proxy.EreGeologiqueCommonProxy;

@Mod(modid = "EreG\351ologique", name = "Ere G\351ologique", version = "1.0.0", dependencies = "required-after:Forge@[7.8.1,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class EreGeologique
{
	   @SidedProxy(clientSide = "ere_geologique.proxy.EreGeologiqueClientProxy", serverSide = "ere_geologique.proxy.EreGeologiqueCommonProxy")
	   public static EreGeologiqueCommonProxy proxy;
	   
	   @Instance("EreG\351ologique")
	   public static EreGeologique Instance;
	   
	   //Crafting Table
	   public static CreativeTabs EreGeologiqueCreativeTab = new EreGeologiqueCreativeTab("EreGeologiqueCreativeTab");
	   
	   //Block
	   public static int FougereLeavesID;
	   public static int FougereWoodID;
	   public static int FougereSaplingID;
	   public static int FougerePlankID;
	   public static int FougereSlabID;
	   public static int FougereDoubleSlabID;
	   public static int FougereStairID;
	   public static int PrehistoriqueBlockCoalID;
	   public static int PrehistoriqueBlockIronID;
	   public static int GrassID;
	   public static int DirtID;
	   public static int CropPlanteFougereID;
	   
	   //Items
	   public static int PrehistoriqueCoalID;
	   public static int PrehistoriqueCoalDustID;
	   public static int PrehistoriqueCokeCoalID;
	   public static int PrehistoriqueCharCoalID;
	   public static int PrehistoriqueIronIngotID;
	   public static int IvoryIngotID;
	   public static int IvoryNuggetID;
	   public static int IvoryGearID;
	   public static int CoalLiquidItemID;
	   public static int CoalLiquidBucketID;
	   public static int FougereSeedsID;
	   public static int FruitFougereID;
	   public static int FougereSwordID;
	   public static int FougerePickaxeID;
	   public static int FougereAxeID;
	   public static int FougereShovelID;
	   public static int FougereHoeID;
	   public static int FougereHelmetID;
	   public static int FougereChestplateID;
	   public static int FougereLeggingsID;
	   public static int FougereBootsID;
	   //Others
	   public static int FCoalLiquidMJ;
	   public static int FCoalLiquidDurability;
	   
	   //Biomes
	   
	   @PreInit
	   public void preload(FMLPreInitializationEvent event)
	   {
		        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		        config.load();
		        //Blocks
		        GrassID = config.getBlock("Block Grass", 254).getInt();
		        DirtID = config.getBlock("Block Dirt", 255).getInt();
		        FougereLeavesID = config.getBlock("Leaves Foug\350re", 2506).getInt();
		        FougereWoodID = config.getBlock("Wood Foug\350re", 2507).getInt();
		        FougereSaplingID = config.getBlock("Sapling Foug\350re", 2508).getInt();
		        FougerePlankID = config.getBlock("Plank Foug\350re", 2509).getInt();
		        FougereSlabID = config.getBlock("Slab Foug\350re", 2510).getInt();
		        FougereDoubleSlabID = config.getBlock("Double Slab Foug\350re", 2511).getInt();
		        FougereStairID = config.getBlock("Stair Foug\350re", 2512).getInt();
		        PrehistoriqueBlockCoalID = config.getBlock("Prehistorique Block Coal", 2513).getInt();
		        PrehistoriqueBlockIronID = config.getBlock("Prehistorique Block Iron", 2514).getInt();
		        CropPlanteFougereID = config.getBlock("Crop Plante Foug\350re", 2515).getInt();
		        //Items
		        PrehistoriqueCoalID = config.getItem("Coal Prehistorique", 20150).getInt();
		        FougereSeedsID = config.getItem("Seeds Foug\350re", 20151).getInt();
		        FougereSwordID = config.getItem("Sword Foug\350re", 20152).getInt();
		        FougerePickaxeID = config.getItem("Pickaxe Foug\350re", 20153).getInt();
		        FougereAxeID = config.getItem("Axe Foug\350re", 20154).getInt();
		        FougereShovelID = config.getItem("Shovel Foug\350re", 20155).getInt();
		        FougereHoeID = config.getItem("Hoe Foug\350re", 20156).getInt();
		        FougereHelmetID = config.getItem("Helmet Foug\350re", 20157).getInt();
		        FougereChestplateID = config.getItem("Chestplate Foug\350re", 20158).getInt();
		        FougereLeggingsID = config.getItem("Leggings Foug\350re", 20159).getInt();
		        FougereBootsID = config.getItem("Boots Foug\350re", 20160).getInt();
		        PrehistoriqueCoalDustID = config.getItem("Coal Dust Prehistorique", 20161).getInt();
		        CoalLiquidItemID = config.getItem("Coal Liquid", 20162).getInt();
		        PrehistoriqueCharCoalID = config.getItem("CharCoal Prehistorique", 20163).getInt();
		        PrehistoriqueCokeCoalID = config.getItem("CokeCoal Prehistorique", 20164).getInt();
		        PrehistoriqueIronIngotID = config.getItem("Iron Ingot Prehistorique", 20165).getInt();
		        IvoryIngotID = config.getItem("Ivory Ingot", 20166).getInt();
		        IvoryNuggetID = config.getItem("Ivory Nugget", 20167).getInt();
		        IvoryGearID = config.getItem("Ivory Gear", 20168).getInt();
		        CoalLiquidBucketID = config.getItem("Coal Liquid Bucket", 20169).getInt();
		        FruitFougereID = config.getItem("Fruit Fougere", 20170).getInt();
		        //Others
		        FCoalLiquidMJ = config.get("Forestry integration", "MJ produced by Coal Liquid", 4).getInt();
		        FCoalLiquidDurability = config.get("Forestry integration", "Durability of Coal Liquid", 200000).getInt();
		        config.save();
	   }
	   
	   @Init
	   public void load(FMLInitializationEvent event)
	   {
		   EreGeologiqueBlockList.loadEreGeologiqueBlock();//Block
		   EreGeologiqueItemList.loadEreGeologiqueItem();//Item
		   EreGeologiqueEntityList.loadEreGeologiqueEntity();//Entity
		   
		   //Creative Table
	       LanguageRegistry.instance().addStringLocalization("itemGroup.EreGeologiqueCreativeTab", "en_US", "Ere G\351ologique");
	       
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
	       
	       EreGeologique_recipe.loadrecipe();//Recipe
	       EreGeologique_recipe.loadSmelting();//Smelting
	       //Other
	       proxy.registerTextures();
		   proxy.registerRenderEntity();
		   proxy.registerRenderThings();
	       MinecraftForge.EVENT_BUS.register(new FougereBoneMeal());
	   }
	   
	   @PostInit
	   public void postload(FMLPostInitializationEvent event)
	   {
	      
	   }

}