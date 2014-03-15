package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
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
		   leaves = new Leaves().setStepSound(Block.soundTypeGrass).setHardness(0.2F).setBlockName("Leaves");
	       wood = new Wood().setStepSound(Block.soundTypeWood).setHardness(2.0F).setBlockName("Wood").setBlockTextureName("ere_geologique:Wood");
	       sapling = new Sapling().setStepSound(Block.soundTypeGrass).setHardness(0.0F).setBlockName("Sapling").setBlockTextureName("ere_geologique:Sapling");
	       plank = new Plank().setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(5.0F).setBlockName("Plank").setBlockTextureName("ere_geologique:Plank");
	       slab = new Slab(false).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("Slab").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       doubleSlab = new Slab(true).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("Slab");
	       stairFougere = new Stair(plank, 0).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("StairFougere");
	       stairCycas = new Stair(plank, 1).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("StairCycas");
	       stairAraucarias = new Stair(plank, 2).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("StairAraucarias");
	       stairMetasequoias = new Stair(plank, 3).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("StairMetasequoias");
	       stairGinkgos = new Stair(plank, 4).setStepSound(Block.soundTypeWood).setHardness(3F).setResistance(1.0F).setBlockName("StairGinkgos");
	       glaciaPortal = (GlaciaPortal) new GlaciaPortal().setHardness(-1.0F).setStepSound(Block.soundTypeGlass).setLightLevel(0.75F).setBlockName("GlaciaPortal").setBlockTextureName("ere_geologique:GlaciaPortal");
	       feeder = new Feeder().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName(LocalizationStrings.FEEDER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       analyzer = new Analyzer().setHardness(3.0F).setStepSound(Block.soundTypeStone).setBlockName(LocalizationStrings.ANALYZER_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorIdle = new Cultivator(false).setLightLevel(0.9375F).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName(LocalizationStrings.CULTIVATE_IDLE_NAME).setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorActive = new Cultivator(true).setLightLevel(0.9375F).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName(LocalizationStrings.CULTIVATE_ACTIVE_NAME);
	       fossil = new Fossil().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("Fossil").setBlockTextureName("ere_geologique:Fossil");
	       reinforcedStone = new ReinforcedStone().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("ReinforcedStone").setBlockTextureName("ere_geologique:reinforced_stone");
	       reinforcedGlass = new ReinforcedGlass(Material.glass, false).setHardness(0.3F).setResistance(10.0F).setStepSound(Block.soundTypeGlass).setBlockName("ReinforcedGlass").setBlockTextureName("ere_geologique:reinforced_glass");
	       steelBlock = new ReinforcedStone().setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("SteelBlock").setBlockTextureName("ere_geologique:steel_block");
	       skull = new Skull().setHardness(1.0F).setStepSound(Block.soundTypeStone).setBlockName("FossilSkull");
	       blueFire = (BlueFire) new BlueFire().setHardness(0.0F).setLightLevel(1.0F).setStepSound(Block.soundTypeWood).setBlockName("BlueFire");
	       drum = new Drum().setHardness(0.8F).setStepSound(Block.soundTypeWood).setBlockName("Drum").setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
	       
	       GameRegistry.registerBlock(leaves, ItemBlockLeaves.class, "Leaves");
	       GameRegistry.registerBlock(wood, ItemBlockWood.class, "Wood");
	       GameRegistry.registerBlock(sapling, ItemBlockSapling.class, "Sapling");
	       GameRegistry.registerBlock(plank, ItemBlockPlank.class, "Plank");
	       //TODO Fix Slab
	       /*GameRegistry.registerBlock(slab, ItemBlockSlab.class, "Slab");
	       GameRegistry.registerBlock(doubleSlab, ItemBlockSlab.class, "DoubleSlab");*/
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

	       //MinecraftForge.setBlockHarvestLevel(fossil, 0, "pickaxe", 2);
		}
		catch(Exception ex)
		{
			EreGeologique.egLog.severe("Erreur lors de l'initialisation des blocs!");
		}
		EreGeologique.egLog.info("Initialisation des blocs terminés!");
	}
}