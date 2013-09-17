package ere_geologique.common.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import ere_geologique.common.config.EGProperties;

public class EGBlockList
{
	public static Block Leaves;
	public static Block Wood;
	public static Block Sapling;
	public static Block Plank;
	public static Block DoubleSlab;
	public static Block Slab;
	public static Block Stair;
	public static Block Portal;
	
	public static void loadBlock()
	{
		   Leaves = new Leaves(EGProperties.LeavesID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setLightOpacity(1).setUnlocalizedName("Leaves").setTextureName("ere_geologique:Leaves");
	       Wood = new Wood(EGProperties.WoodID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setUnlocalizedName("Wood").setTextureName("ere_geologique:Wood");
	       Sapling = new Sapling(EGProperties.SaplingID).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("Sapling").setTextureName("ere_geologique:Sapling");
	       Plank = new Plank(EGProperties.PlankID).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("Plank").setTextureName("ere_geologique:Plank");
	       Slab = new Slab(EGProperties.SlabID, false).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       DoubleSlab = new Slab(EGProperties.DoubleSlabID, true).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Slab");
	       Stair = new Stair(EGProperties.StairID, Plank,1).setStepSound(Block.soundWoodFootstep).setHardness(3F).setResistance(1.0F).setUnlocalizedName("Stair");
	       Portal = new Portal(EGProperties.PortalID).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("Portal").setTextureName("Portal");
	       
	       GameRegistry.registerBlock(Leaves, ItemBlockLeaves.class, "Leaves");
	       GameRegistry.registerBlock(Wood, ItemBlockWood.class, "Wood");
	       GameRegistry.registerBlock(Sapling, ItemBlockSapling.class, "Sapling");
	       GameRegistry.registerBlock(Plank, ItemBlockPlank.class, "Plank");
	       GameRegistry.registerBlock(Slab, ItemBlockSlab.class, "Slab");
	       GameRegistry.registerBlock(DoubleSlab, ItemBlockSlab.class, "DoubleSlab");
	       GameRegistry.registerBlock(Stair, "Stair");
	       GameRegistry.registerBlock(Portal, "Portal");
	}
}