package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.creativetabs.EGCreativeTabBlock;

public class EGBlockList
{
	public static Block Leaves;
	public static Block Wood;
	public static Block Sapling;
	public static Block Plank;
	public static Block DoubleSlab;
	public static Block Slab;
	public static Block StairFougere;
    public static Block StairCycas;
    public static Block StairAraucarias;
    public static Block StairMetasequoias;
    public static Block StairGinkgos;
	public static GlaciaPortal GlaciaPortal;
	public static Block Feeder;
	public static Block Analyzer;
	public static Block CultivatorIdle;
	public static Block CultivatorActive;
	public static Block Fossil;
	public static Block ReinforcedStone;
	public static Block ReinforcedGlass;
	public static Block SteelBlock;
	public static Block Skull;
	public static BlueFire BlueFire;
	public static Block Drum;
	
	public static void loadBlock()
	{
		try
		{
		   Leaves = new Leaves(EGProperties.LeavesID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setLightOpacity(1).setUnlocalizedName("Leaves");
	       Wood = new Wood(EGProperties.WoodID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setUnlocalizedName("Wood").setTextureName("ere_geologique:Wood");
	       Sapling = new Sapling(EGProperties.SaplingID).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("Sapling").setTextureName("ere_geologique:Sapling");
	       Plank = new Plank(EGProperties.PlankID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("Plank").setTextureName("ere_geologique:Plank");
	       Slab = new Slab(EGProperties.SlabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       DoubleSlab = new Slab(EGProperties.DoubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       StairFougere = new Stair(EGProperties.StairFougereID, Plank, 0).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairFougere");
	       StairCycas = new Stair(EGProperties.StairCycasID, Plank, 1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairCycas");
	       StairAraucarias = new Stair(EGProperties.StairAraucariasID, Plank, 2).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairAraucarias");
	       StairMetasequoias = new Stair(EGProperties.StairMetasequoiasID, Plank, 3).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairMetasequoias");
	       StairGinkgos = new Stair(EGProperties.StairGingkosID, Plank, 5).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairGinkgos");
	       GlaciaPortal = (GlaciaPortal) new GlaciaPortal(EGProperties.GlaciaPortalID).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setUnlocalizedName("GlaciaPortal").setTextureName("ere_geologique:GlaciaPortal");
	       Feeder = new Feeder(EGProperties.FeederID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.FEEDER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       Analyzer = new Analyzer(EGProperties.AnalyzerID).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.ANALYZER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       CultivatorIdle = new Cultivator(EGProperties.CultivatorIdleID, false).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.CULTIVATE_IDLE_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       CultivatorActive = new Cultivator(EGProperties.CultivatorActiveID, true).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.CULTIVATE_ACTIVE_NAME);
	       Fossil = new Fossil(EGProperties.FossilID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Fossil").setTextureName("ere_geologique:Fossil");
	       ReinforcedStone = new ReinforcedStone(EGProperties.ReinforcedStoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ReinforcedStone").setTextureName("ere_geologique:reinforced_stone");
	       ReinforcedGlass = new ReinforcedGlass(EGProperties.ReinforcedGlassID, Material.glass, false).setHardness(0.3F).setResistance(10.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("ReinforcedGlass").setTextureName("ere_geologique:reinforced_glass");
	       SteelBlock = new ReinforcedStone(EGProperties.SteelBlockID).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelBlock").setTextureName("ere_geologique:steel_block");
	       Skull = new Skull(EGProperties.FossilSkullID).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("FossilSkull");
	       BlueFire = (BlueFire) new BlueFire(EGProperties.BlueFireID).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("BlueFire");
	       Drum = new Drum(EGProperties.DrumID).setHardness(0.8F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Drum").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       
	       GameRegistry.registerBlock(Leaves, ItemBlockLeaves.class, "Leaves");
	       GameRegistry.registerBlock(Wood, ItemBlockWood.class, "Wood");
	       GameRegistry.registerBlock(Sapling, ItemBlockSapling.class, "Sapling");
	       GameRegistry.registerBlock(Plank, ItemBlockPlank.class, "Plank");
	       GameRegistry.registerBlock(Slab, ItemBlockSlab.class, "Slab");
	       GameRegistry.registerBlock(DoubleSlab, ItemBlockSlab.class, "DoubleSlab");
	       GameRegistry.registerBlock(StairFougere, "StairFougere");
	       GameRegistry.registerBlock(StairCycas, "StairCycas");
	       GameRegistry.registerBlock(StairAraucarias, "StairAraucarias");
	       GameRegistry.registerBlock(StairMetasequoias, "StairMetasequoias");
	       GameRegistry.registerBlock(StairGinkgos, "StairGingkos");
	       GameRegistry.registerBlock(GlaciaPortal, "GlaciaPortal");
	       GameRegistry.registerBlock(Feeder, "Feeder");
	       GameRegistry.registerBlock(Analyzer, "Analyzer");
	       GameRegistry.registerBlock(CultivatorIdle, "CultivatorIdle");
	       GameRegistry.registerBlock(CultivatorActive, "CultivatorActive");
	       GameRegistry.registerBlock(Fossil, "Fossil");
	       GameRegistry.registerBlock(ReinforcedStone, "ReinforcedStone");
	       GameRegistry.registerBlock(ReinforcedGlass, "ReinforcedGlass");
	       GameRegistry.registerBlock(SteelBlock, "Steelblock");
	       GameRegistry.registerBlock(Skull, "Skull");
	       GameRegistry.registerBlock(BlueFire, "BlueFire");
	       GameRegistry.registerBlock(Drum, "Drum");

	       MinecraftForge.setBlockHarvestLevel(Fossil, 0, "pickaxe", 2);

		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des blocs!");
		}
		EreGeologique.EGLog.info("Initialisation des blocs terminés!");
	}
}