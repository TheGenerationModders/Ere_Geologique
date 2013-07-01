package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.EreGeologique;

public class EreGeologiqueBlockList
{
	public static Block FougereLeaves;
	public static Block FougereWood;
	public static Block FougereSapling;
	public static Block FougerePlank;
	public static Block FougereSlab;
	public static Block FougereDoubleSlab;
	public static Block FougereStair;
	public static Block PrehistoriqueBlockCoal;
	public static Block PrehistoriqueBlockIron;
	public static Block Grass;
	public static Block Dirt;
	public static Block CropPlanteFougere;
	
	public static void loadEreGeologiqueBlock()
	{
		   FougereLeaves = new FougereLeaves(EreGeologique.FougereLeavesID, 7, false).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setResistance(1.0F).setLightOpacity(1).setUnlocalizedName("FougereLeaves");
	       FougereWood = new FougereWood(EreGeologique.FougereWoodID).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("FougereWood");
	       FougereSapling = new FougereSapling(EreGeologique.FougereSaplingID, 10).setStepSound(Block.soundGrassFootstep).setHardness(0.1F).setResistance(1.0F).setUnlocalizedName("FougereSapling");
	       FougerePlank = new FougerePlank(EreGeologique.FougerePlankID, 0).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("FougerePlank");
	       FougereSlab = new FougereSlab(EreGeologique.FougereSlabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("FougereSlab");
	       FougereDoubleSlab = new FougereSlab(EreGeologique.FougereDoubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("FougereDoubleSlab");
	       FougereStair = new FougereStair(EreGeologique.FougereStairID, FougerePlank,1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("FougereStair");
	       PrehistoriqueBlockCoal = new PrehistoriqueBlockCoal(EreGeologique.PrehistoriqueBlockCoalID, 0).setStepSound(Block.soundStoneFootstep).setHardness(100F).setResistance(2500.0F).setUnlocalizedName("PrehistoriqueBlockCoal");
	       PrehistoriqueBlockIron = new PrehistoriqueBlockIron(EreGeologique.PrehistoriqueBlockIronID,0).setStepSound(Block.soundStoneFootstep).setHardness(150F).setResistance(2550.0F).setUnlocalizedName("PrehistoriqueBlockIron");
	       Grass = new Grass(EreGeologique.GrassID, 0).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Grass");
	       Dirt = new Dirt(EreGeologique.DirtID, 0).setStepSound(Block.soundGrassFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Dirt");
	       CropPlanteFougere = new CropPlanteFougere(EreGeologique.CropPlanteFougereID,16).setUnlocalizedName("CropPlanteFougere").setStepSound(Block.soundGrassFootstep);
	       
	       GameRegistry.registerBlock(FougereLeaves);
	       GameRegistry.registerBlock(FougereWood);
	       GameRegistry.registerBlock(FougereSapling);
	       GameRegistry.registerBlock(FougerePlank);
	       GameRegistry.registerBlock(FougereSlab);
	       GameRegistry.registerBlock(FougereDoubleSlab);
	       GameRegistry.registerBlock(FougereStair);
	       GameRegistry.registerBlock(PrehistoriqueBlockCoal);
	       GameRegistry.registerBlock(PrehistoriqueBlockIron);
	       GameRegistry.registerBlock(Grass);
	       GameRegistry.registerBlock(Dirt);
	       GameRegistry.registerBlock(CropPlanteFougere);
	       
	       LanguageRegistry.addName(FougereLeaves, "Feuilles de Foug\350re");
	       LanguageRegistry.addName(FougereWood, "Bois de Foug\350re");
	       LanguageRegistry.addName(FougereSapling, "Pousse de Foug\350re");
	       LanguageRegistry.addName(FougerePlank, "Planche de Foug\350re");
	       LanguageRegistry.addName(FougereSlab, "Dalle de Foug\350re");
	       LanguageRegistry.addName(FougereDoubleSlab, "Double dalle de Foug\350re");
	       LanguageRegistry.addName(FougereStair, "Escalier de Foug\350re");
	       LanguageRegistry.addName(PrehistoriqueBlockCoal, "Bloc de Charbon Prehistorique");
	       LanguageRegistry.addName(PrehistoriqueBlockIron, "Bloc de Fer Prehistorique");
	       LanguageRegistry.addName(Grass, "Bouse chevelue");
	       LanguageRegistry.addName(Dirt, "Bouse");
	       LanguageRegistry.addName(CropPlanteFougere, "Plante Foug\350re");
	       
	       MinecraftForge.setBlockHarvestLevel(PrehistoriqueBlockCoal, "pickaxe", 4);
	}
}
