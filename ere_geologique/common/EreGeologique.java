package ere_geologique.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
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
import ere_geologique.common.block.EreGeologiqueBlockList;
import ere_geologique.common.config.EGProperties;
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
	   
	   //Biomes
	   
	   @PreInit
	   public void preload(FMLPreInitializationEvent event)
	   {
		        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
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
		        EGProperties.CropPlanteFougereID = cfg.getBlock("Crop Plante Foug\350re", 2515).getInt();
		        //Items
		        EGProperties.PrehistoriqueCoalID = cfg.getItem("Coal Prehistorique", 20150).getInt();
		        EGProperties.FougereSeedsID = cfg.getItem("Seeds Foug\350re", 20151).getInt();
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
		        EGProperties.FruitFougereID = cfg.getItem("Fruit Fougere", 20170).getInt();
		        //Others
		        EGProperties.FCoalLiquidMJ = cfg.get("Forestry integration", "MJ produced by Coal Liquid", 4).getInt();
		        EGProperties.FCoalLiquidDurability = cfg.get("Forestry integration", "Durability of Coal Liquid", 200000).getInt();
		        cfg.save();
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