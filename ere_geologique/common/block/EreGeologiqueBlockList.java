package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;

public class EreGeologiqueBlockList
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
	public static Block CropPlanteFougere;
	
	public static void loadEreGeologiqueBlock()
	{
		   Leaves = new Leaves(EGProperties.LeavesID, 7, false).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setResistance(1.0F).setLightOpacity(1).setUnlocalizedName("Leaves");
	       Wood = new Wood(EGProperties.WoodID).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Wood");
	       Sapling = new Sapling(EGProperties.SaplingID, 10).setStepSound(Block.soundGrassFootstep).setHardness(0.1F).setResistance(1.0F).setUnlocalizedName("Sapling");
	       Plank = new Plank(EGProperties.PlankID, 0).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Plank");
	       Slab = new Slab(EGProperties.SlabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       DoubleSlab = new Slab(EGProperties.DoubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("DoubleSlab");
	       Stair = new Stair(EGProperties.StairID, Plank,1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Stair");
	       PrehistoriqueBlockCoal = new PrehistoriqueBlockCoal(EGProperties.PrehistoriqueBlockCoalID, 0).setStepSound(Block.soundStoneFootstep).setHardness(100F).setResistance(2500.0F).setUnlocalizedName("PrehistoriqueBlockCoal");
	       PrehistoriqueBlockIron = new PrehistoriqueBlockIron(EGProperties.PrehistoriqueBlockIronID,0).setStepSound(Block.soundStoneFootstep).setHardness(150F).setResistance(2550.0F).setUnlocalizedName("PrehistoriqueBlockIron");
	       Grass = new Grass(EGProperties.GrassID, 0).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Grass");
	       Dirt = new Dirt(EGProperties.DirtID, 0).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Dirt");
	       CropPlanteFougere = new CropPlanteFougere(EGProperties.CropPlanteFougereID).setUnlocalizedName("CropPlanteFougere").setStepSound(Block.soundGrassFootstep);
	       
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
	       GameRegistry.registerBlock(CropPlanteFougere);
	       
	       LanguageRegistry.addName(Leaves, "Feuilles de Foug\350re");
	       LanguageRegistry.addName(Wood, "Bois de Foug\350re");
	       LanguageRegistry.addName(Sapling, "Pousse de Foug\350re");
	       LanguageRegistry.addName(Plank, "Planche de Foug\350re");
	       LanguageRegistry.addName(Slab, "Dalle de Foug\350re");
	       LanguageRegistry.addName(DoubleSlab, "Double dalle de Foug\350re");
	       LanguageRegistry.addName(Stair, "Escalier de Foug\350re");
	       LanguageRegistry.addName(PrehistoriqueBlockCoal, "Bloc de Charbon Prehistorique");
	       LanguageRegistry.addName(PrehistoriqueBlockIron, "Bloc de Fer Prehistorique");
	       LanguageRegistry.addName(Grass, "Bouse chevelue");
	       LanguageRegistry.addName(Dirt, "Bouse");
	       LanguageRegistry.addName(CropPlanteFougere, "Plante Foug\350re");
	       
	       MinecraftForge.setBlockHarvestLevel(PrehistoriqueBlockCoal, "pickaxe", 4);
	}
}
