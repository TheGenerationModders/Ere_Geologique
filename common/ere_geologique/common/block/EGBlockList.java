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
		   leaves = new Leaves().func_149672_a(Block.field_149779_h).func_149711_c(0.2F).func_149663_c("Leaves");
	       wood = new Wood().func_149672_a(Block.field_149766_f).func_149711_c(2.0F).func_149663_c("Wood").func_149658_d("ere_geologique:Wood");
	       sapling = new Sapling().func_149672_a(Block.field_149779_h).func_149711_c(0.0F).func_149663_c("Sapling").func_149658_d("ere_geologique:Sapling");
	       plank = new Plank().func_149672_a(Block.field_149766_f).func_149711_c(2.0F).func_149752_b(5.0F).func_149663_c("Plank").func_149658_d("ere_geologique:Plank");
	       slab = new Slab(false).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("Slab").func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	       doubleSlab = new Slab(true).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("Slab");
	       stairFougere = new Stair(plank, 0).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("StairFougere");
	       stairCycas = new Stair(plank, 1).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("StairCycas");
	       stairAraucarias = new Stair(plank, 2).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("StairAraucarias");
	       stairMetasequoias = new Stair(plank, 3).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("StairMetasequoias");
	       stairGinkgos = new Stair(plank, 5).func_149672_a(Block.field_149766_f).func_149711_c(3F).func_149752_b(1.0F).func_149663_c("StairGinkgos");
	       glaciaPortal = (GlaciaPortal) new GlaciaPortal().func_149711_c(-1.0F).func_149672_a(Block.field_149778_k).func_149715_a(0.75F).func_149663_c("GlaciaPortal").func_149658_d("ere_geologique:GlaciaPortal");
	       feeder = new Feeder().func_149711_c(3.5F).func_149672_a(Block.field_149780_i).func_149663_c(LocalizationStrings.FEEDER_NAME).func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	       analyzer = new Analyzer().func_149711_c(3.0F).func_149672_a(Block.field_149777_j).func_149663_c(LocalizationStrings.ANALYZER_NAME).func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorIdle = new Cultivator(false).func_149715_a(0.9375F).func_149711_c(0.3F).func_149672_a(Block.field_149778_k).func_149663_c(LocalizationStrings.CULTIVATE_IDLE_NAME).func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	       cultivatorActive = new Cultivator(true).func_149715_a(0.9375F).func_149711_c(0.3F).func_149672_a(Block.field_149778_k).func_149663_c(LocalizationStrings.CULTIVATE_ACTIVE_NAME);
	       fossil = new Fossil().func_149711_c(3.0F).func_149752_b(5.0F).func_149672_a(Block.field_149780_i).func_149663_c("Fossil").func_149658_d("ere_geologique:Fossil");
	       reinforcedStone = new ReinforcedStone().func_149711_c(1.5F).func_149752_b(10.0F).func_149672_a(Block.field_149780_i).func_149663_c("ReinforcedStone").func_149658_d("ere_geologique:reinforced_stone");
	       reinforcedGlass = new ReinforcedGlass(Material.field_151592_s, false).func_149711_c(0.3F).func_149752_b(10.0F).func_149672_a(Block.field_149778_k).func_149663_c("ReinforcedGlass").func_149658_d("ere_geologique:reinforced_glass");
	       steelBlock = new ReinforcedStone().func_149711_c(5.0F).func_149752_b(10.0F).func_149672_a(Block.field_149777_j).func_149663_c("SteelBlock").func_149658_d("ere_geologique:steel_block");
	       skull = new Skull().func_149711_c(1.0F).func_149672_a(Block.field_149780_i).func_149663_c("FossilSkull");
	       blueFire = (BlueFire) new BlueFire().func_149711_c(0.0F).func_149715_a(1.0F).func_149672_a(Block.field_149766_f).func_149663_c("BlueFire");
	       drum = new Drum().func_149711_c(0.8F).func_149672_a(Block.field_149766_f).func_149663_c("Drum").func_149647_a(EGCreativeTab.EGCreativeTabBlock);
	       
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

	       //MinecraftForge.setBlockHarvestLevel(fossil, 0, "pickaxe", 2);
		}
		catch(Exception ex)
		{
			EreGeologique.EGLog.severe("Erreur lors de l'initialisation des blocs!");
		}
		EreGeologique.EGLog.info("Initialisation des blocs terminés!");
	}
}