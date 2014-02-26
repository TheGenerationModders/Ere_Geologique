package ere_geologique.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Leaves extends BlockLeaves
{
	public static final String[] leafType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	public static final String[][] leafTextureTypes = new String[][] {{"leaves", "leaves_cycas", "leaves_araucarias", "leaves_metasequoias", "leaves_ginkgos"},{"leaves_opaque", "leaves_cycas_opaque", "leaves_araucarias_opaque", "leaves_metasequoias_opaque", "leaves_ginkgos_opaque"}};

 
    public Leaves()
    {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return (par1 & 4) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 4) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        return (l & 4) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((l & 4) == 2 ? ColorizerFoliage.getFoliageColorBirch() : super.colorMultiplier(world, x, y, z));
    }
    
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(EGBlockList.sapling);
    }
 
    public int damageDropped(int par1)
    {
        return par1 & 4;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return (metadata & 4) == 1 ? this.field_150129_M[this.field_150127_b][1] : ((metadata & 4) == 3 ? this.field_150129_M[this.field_150127_b][3] : this.field_150129_M[this.field_150127_b][0]);
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
 
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	for (int i = 0; i < leafTextureTypes.length; ++i)
        {
            this.field_150129_M[i] = new IIcon[leafTextureTypes[i].length];

            for (int j = 0; j < leafTextureTypes[i].length; ++j)
            {
                this.field_150129_M[i][j] = par1IconRegister.registerIcon("ere_geologique:" + leafTextureTypes[i][j]);
            }
        }
    }
    
	@Override
	public String[] func_150125_e()
	{
		return leafType;
	}
}