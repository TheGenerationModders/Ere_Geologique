package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Wood extends BlockRotatedPillar
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private IIcon[] IconArray;
	@SideOnly(Side.CLIENT)
	private IIcon[] wood_top;
	
    public Wood()
    {
        super(Material.field_151575_d);
        this.func_149647_a(EGCreativeTab.EGCreativeTabBlock);
    }
    
    public int quantityDropped(Random rand)
    {
    	return 1;
    }
    
    public Item idDropped(Item item, Random rand, int fortune)
    {
        return Item.func_150898_a(EGBlockList.wood);
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon func_150163_b(int par1)
    {
        return this.IconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getEndIcon(int par1)
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
    
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
        list.add(new ItemStack(item, 1, 4));
 
    }
 
    public void registerIcons(IIconRegister register)
    {
    	this.wood_top = new IIcon[woodType.length];
        this.IconArray = new IIcon[woodType.length];

        for (int i = 0; i < this.IconArray.length; ++i)
        {
            this.IconArray[i] = register.registerIcon(this.func_149641_N() + "_" + woodType[i]);
            this.wood_top[i] = register.registerIcon(this.func_149641_N() + "_" + woodType[i] + "_top");
        }
    }
}