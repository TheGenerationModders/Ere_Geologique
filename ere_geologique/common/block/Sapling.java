package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EGCreativeTab;

public class Sapling extends BlockSapling
{
	public static final String[] WOOD_TYPES = new String[] {"fougere"};
	 
    public Sapling(int i, int j)
    {
            super(i);
            float f = 0.4F;
            setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
            this.setCreativeTab(EGCreativeTab.EGCreativeTab);
    }


    public void growTree(World world, int i, int j, int k, Random random)
    {
            int l = world.getBlockMetadata(i, j, k) & 3;
            world.setBlock(i, j, k, 0);
            Object obj = null;
            obj = new WorldGenFougere(false);
            if(!((WorldGenerator) (obj)).generate(world, random, i, j, k))
            {
                    world.setBlockMetadataWithNotify(i, j, k, blockID, l);
            }
    }

    //chemin de texture
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }

    @SideOnly(Side.CLIENT)

    //Place le block dans la categorie voulue de l'inventaire creative
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));

    }

    //Ne pas toucher
    public void fertilize(World world, int x, int y, int z)
    {

    }
}
