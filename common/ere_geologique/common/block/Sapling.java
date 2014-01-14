package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.worldgenerator.WorldGenFougere;

public class Sapling extends BlockSapling
{
	public static final String[] saplingtype = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private IIcon[] SaplingIcon;
	
    public Sapling(int id)
    {
            super(id);
            float f = 0.4F;
            setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
            this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
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
    
    public IIcon getIcon(int side, int metadata)
    {
    	metadata &= 4;
    	return this.SaplingIcon[metadata];
    }
    
    public int damageDropped(int id)
    {
    	return id & 4;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 1));
        list.add(new ItemStack(id, 1, 2));
        list.add(new ItemStack(id, 1, 3));
        list.add(new ItemStack(id, 1, 4));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.SaplingIcon = new IIcon[saplingtype.length];

        for (int i = 0; i < this.SaplingIcon.length; ++i)
        {
            this.SaplingIcon[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + saplingtype[i]);
        }
    }

    //Ne pas toucher
    public void fertilize(World world, int x, int y, int z)
    {

    }
}