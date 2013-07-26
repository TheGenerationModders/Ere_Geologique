package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EGCreativeTab;

public class Sapling extends BlockSapling
{
	public static final String[] saplingtype = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	public static final String[] saplingTextureTypes = new String[] {"Sapling", "Sapling_cycas", "Sapling_araucarias", "Sapling_metasequoias", "Sapling_ginkgos"};
	@SideOnly(Side.CLIENT)
	private Icon[] SaplingIcon;
	
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
    
    public Icon getIcon(int side, int metadata)
    {
    	metadata &= 4;
    	return this.SaplingIcon[metadata];
    }
    
    public int damageDropped(int id)
    {
    	return id & 4;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.SaplingIcon = new Icon[saplingTextureTypes.length];
		
		for (int i = 0; i < this.saplingTextureTypes.length; ++i)
		{
		 this.SaplingIcon[i] = par1IconRegister.registerIcon("EreGeologique" + saplingTextureTypes[i]);
		}
    }

    //Ne pas toucher
    public void fertilize(World world, int x, int y, int z)
    {

    }
}
