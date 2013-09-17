package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Wood extends BlockRotatedPillar
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private Icon[] IconArray;
	@SideOnly(Side.CLIENT)
	private Icon[] wood_top;
	
    public Wood(int id)
    {
        super(id, Material.wood);
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
    }
    
    public int quantityDropped(Random rand)
    {
    	return 1;
    }
    
    public int idDropped(int id, Random rand, int fortune)
    {
        return EGProperties.WoodID;
    }
    
    @SideOnly(Side.CLIENT)
    protected Icon getSideIcon(int par1)
    {
        return this.IconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    protected Icon getEndIcon(int par1)
    {
        return this.wood_top[par1];
    }
    
    public int damageDropped(int par1)
    {
        return par1 & 4;
    }
    
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 4;
    }
    
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 1));
        list.add(new ItemStack(id, 1, 2));
        list.add(new ItemStack(id, 1, 3));
        list.add(new ItemStack(id, 1, 4));
 
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
    }
 
    public void registerIcons(IconRegister register)
    {
    	this.wood_top = new Icon[woodType.length];
        this.IconArray = new Icon[woodType.length];

        for (int i = 0; i < this.IconArray.length; ++i)
        {
            this.IconArray[i] = register.registerIcon(this.getTextureName() + "_" + woodType[i]);
            this.wood_top[i] = register.registerIcon(this.getTextureName() + "_" + woodType[i] + "_top");
        }
    }
    
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);

                        if (Block.blocksList[j2] != null)
                        {
                            Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return true;
    }
}