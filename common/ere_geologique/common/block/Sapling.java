package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;
//import ere_geologique.common.worldgenerator.WorldGenFougere;

public class Sapling extends BlockSapling
{
	public static final String[] saplingtype = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private IIcon[] SaplingIcon;
	
    public Sapling()
    {
    	super();
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
    }

    public void growTree(World world, int x, int y, int z, Random random)
    {
        int l = world.getBlockMetadata(x, y, z) & 5;
        world.setBlock(x, y, z, null, 0, l);
        Object obj = null;
        //obj = new WorldGenFougere(false);
        if(!((WorldGenerator) (obj)).generate(world, random, x, y, z))
        {
        	world.setBlockMetadataWithNotify(x, y, z, l, l);
        }
    }
    
    public IIcon getIcon(int side, int metadata)
    {
    	metadata &= 5;
    	return this.SaplingIcon[metadata];
    }
    
    public int damageDropped(int id)
    {
    	return id & 5;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
        list.add(new ItemStack(item, 1, 4));
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