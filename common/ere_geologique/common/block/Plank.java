package ere_geologique.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Plank extends Block
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private IIcon[] IconArray;
	
	public Plank()
    {
        super(Material.field_151575_d);
        this.func_149647_a(EGCreativeTab.EGCreativeTabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata < 0 || metadata >= this.IconArray.length)
        {
            metadata = 0;
        }

        return this.IconArray[metadata];
    }
    
    public int damageDropped(int par1)
    {
        return par1;
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
        this.IconArray = new IIcon[woodType.length];

        for (int i = 0; i < this.IconArray.length; ++i)
        {
            this.IconArray[i] = par1IconRegister.registerIcon(this.func_149641_N() + "_" + woodType[i]);
        }
    }
}