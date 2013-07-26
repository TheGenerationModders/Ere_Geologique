package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.config.EGProperties;

public class EGBlockList
{
	public static Block Leaves;
	public static Block Wood;
	public static Block Sapling;
	public static Block Plank;
	public static Block Slab;
	public static Block DoubleSlab;
	public static Block Stair;
	public static Block PrehistoriqueBlockCoal;
	public static Block PrehistoriqueBlockIron;
	public static Block Grass;
	public static Block Dirt;
	
	public static void loadBlock()
	{
		   Leaves = new Leaves(EGProperties.LeavesID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setResistance(1.0F).setLightOpacity(1).setUnlocalizedName("Leaves");
	       Wood = new Wood(EGProperties.WoodID).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Wood");
	       Sapling = new Sapling(EGProperties.SaplingID).setStepSound(Block.soundGrassFootstep).setHardness(0.1F).setResistance(1.0F).setUnlocalizedName("Sapling");
	       Plank = new Plank(EGProperties.PlankID).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Plank");
	       Slab = new Slab(EGProperties.SlabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       DoubleSlab = new Slab(EGProperties.DoubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("DoubleSlab");
	       Stair = new Stair(EGProperties.StairID, Plank,1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Stair");
	       PrehistoriqueBlockCoal = new PrehistoriqueBlockCoal(EGProperties.PrehistoriqueBlockCoalID).setStepSound(Block.soundStoneFootstep).setHardness(100F).setResistance(2500.0F).setUnlocalizedName("PrehistoriqueBlockCoal");
	       PrehistoriqueBlockIron = new PrehistoriqueBlockIron(EGProperties.PrehistoriqueBlockIronID).setStepSound(Block.soundStoneFootstep).setHardness(150F).setResistance(2550.0F).setUnlocalizedName("PrehistoriqueBlockIron");
	       Grass = new Grass(EGProperties.GrassID).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Grass");
	       Dirt = new Dirt(EGProperties.DirtID).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Dirt");
	       
	       GameRegistry.registerBlock(Leaves);
	       GameRegistry.registerBlock(Wood);
	       GameRegistry.registerBlock(Sapling);
	       GameRegistry.registerBlock(Plank);
	       GameRegistry.registerBlock(Slab);
	       GameRegistry.registerBlock(DoubleSlab);
	       GameRegistry.registerBlock(Stair);
	       GameRegistry.registerBlock(PrehistoriqueBlockCoal);
	       GameRegistry.registerBlock(PrehistoriqueBlockIron);
	       GameRegistry.registerBlock(Grass);
	       GameRegistry.registerBlock(Dirt);
	       
	       MinecraftForge.setBlockHarvestLevel(PrehistoriqueBlockCoal, "pickaxe", 4);
	}
}
