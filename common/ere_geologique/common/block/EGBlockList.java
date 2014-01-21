package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class EGBlockList
{
	public static Block leaves;
	public static Block wood;
	public static Block sapling;
	public static Block plank;
	public static Block doubleSlab;
	public static Block slab;
	public static Block stairFougere;
    public static Block stairCycas;
    public static Block stairAraucarias;
    public static Block stairMetasequoias;
    public static Block stairGinkgos;
	public static GlaciaPortal glaciaPortal;
	public static Block feeder;
	public static Block analyzer;
	public static Block cultivatorIdle;
	public static Block cultivatorActive;
	public static Block fossil;
	public static Block reinforcedStone;
	public static Block reinforcedGlass;
	public static Block steelBlock;
	public static Block skull;
	public static BlueFire blueFire;
	public static Block drum;
	
	public static void loadBlock()
	{
		try
		{
		   leaves = new Leaves(EGProperties.leavesID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setLightOpacity(1).setUnlocalizedName("Leaves");
	       wood = new Wood(EGProperties.woodID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setUnlocalizedName("Wood").setTextureName("ere_geologique:Wood");
	       sapling = new Sapling(EGProperties.saplingID).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("Sapling").setTextureName("ere_geologique:Sapling");
	       plank = new Plank(EGProperties.plankID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("Plank").setTextureName("ere_geologique:Plank");
	       slab = new Slab(EGProperties.slabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       doubleSlab = new Slab(EGProperties.doubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       stairFougere = new Stair(EGProperties.stairFougereID, plank, 0).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairFougere");
	       stairCycas = new Stair(EGProperties.stairCycasID, plank, 1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairCycas");
	       stairAraucarias = new Stair(EGProperties.stairAraucariasID, plank, 2).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairAraucarias");
	       stairMetasequoias = new Stair(EGProperties.stairMetasequoiasID, plank, 3).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairMetasequoias");
	       stairGinkgos = new Stair(EGProperties.stairGingkosID, plank, 5).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("StairGinkgos");
	       glaciaPortal = (GlaciaPortal) new GlaciaPortal(EGProperties.glaciaPortalID).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setUnlocalizedName("GlaciaPortal").setTextureName("ere_geologique:GlaciaPortal");
	       feeder = new Feeder(EGProperties.feederID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.FEEDER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       analyzer = new Analyzer().setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.ANALYZER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorIdle = new Cultivator(false).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.CULTIVATE_IDLE_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorActive = new Cultivator(true).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.CULTIVATE_ACTIVE_NAME);
	       fossil = new Fossil(EGProperties.fossilID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Fossil").setTextureName("ere_geologique:Fossil");
	       reinforcedStone = new ReinforcedStone(EGProperties.reinforcedStoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ReinforcedStone").setTextureName("ere_geologique:reinforced_stone");
	       reinforcedGlass = new ReinforcedGlass(EGProperties.reinforcedGlassID, Material.glass, false).setHardness(0.3F).setResistance(10.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("ReinforcedGlass").setTextureName("ere_geologique:reinforced_glass");
	       steelBlock = new ReinforcedStone(EGProperties.steelBlockID).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelBlock").setTextureName("ere_geologique:steel_block");
	       skull = new Skull(EGProperties.fossilSkullID).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("FossilSkull");
	       blueFire = (BlueFire) new BlueFire(EGProperties.blueFireID).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("BlueFire");
	       drum = new Drum().setHardness(0.8F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Drum").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       
	       GameRegistry.registerBlock(leaves, ItemBlockLeaves.class, "Leaves");
	       GameRegistry.registerBlock(wood, ItemBlockWood.class, "Wood");
	       GameRegistry.registerBlock(sapling, ItemBlockSapling.class, "Sapling");
	       GameRegistry.registerBlock(plank, ItemBlockPlank.class, "Plank");
	       GameRegistry.registerBlock(slab, ItemBlockSlab.class, "Slab");
	       GameRegistry.registerBlock(doubleSlab, ItemBlockSlab.class, "DoubleSlab");
	       GameRegistry.registerBlock(stairFougere, "StairFougere");
	       GameRegistry.registerBlock(stairCycas, "StairCycas");
	       GameRegistry.registerBlock(stairAraucarias, "StairAraucarias");
	       GameRegistry.registerBlock(stairMetasequoias, "StairMetasequoias");
	       GameRegistry.registerBlock(stairGinkgos, "StairGingkos");
	       GameRegistry.registerBlock(glaciaPortal, "GlaciaPortal");
	       GameRegistry.registerBlock(feeder, "Feeder");
	       GameRegistry.registerBlock(analyzer, "Analyzer");
	       GameRegistry.registerBlock(cultivatorIdle, "CultivatorIdle");
	       GameRegistry.registerBlock(cultivatorActive, "CultivatorActive");
	       GameRegistry.registerBlock(fossil, "Fossil");
	       GameRegistry.registerBlock(reinforcedStone, "ReinforcedStone");
	       GameRegistry.registerBlock(reinforcedGlass, "ReinforcedGlass");
	       GameRegistry.registerBlock(steelBlock, "Steelblock");
	       GameRegistry.registerBlock(skull, "Skull");
	       GameRegistry.registerBlock(blueFire, "BlueFire");
	       GameRegistry.registerBlock(drum, "Drum");

	       MinecraftForge.setBlockHarvestLevel(fossil, 0, "pickaxe", 2);
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des blocs!");
		}
		EreGeologique.EGLog.info("Initialisation des blocs terminés!");
	}
}