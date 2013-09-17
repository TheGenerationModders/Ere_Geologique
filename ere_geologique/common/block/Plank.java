package ere_geologique.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Plank extends Block
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	@SideOnly(Side.CLIENT)
	private Icon[] IconArray;
	
	public Plank(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
        this.setBurnProperties(this.blockID, 5, 20);
    }
	
    private void setBurnRate(int par1, int par2, int par3)
    {
            Block.setBurnProperties(par1,  par2, par3);
    }
    
    public static void setBurnProperties(int id, int encouragement, int flammability)
    {
            blockFireSpreadSpeed[id] = encouragement;
            blockFlammability[id] = flammability;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
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
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 1));
        list.add(new ItemStack(id, 1, 2));
        list.add(new ItemStack(id, 1, 3));
        list.add(new ItemStack(id, 1, 4));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.IconArray = new Icon[woodType.length];

        for (int i = 0; i < this.IconArray.length; ++i)
        {
            this.IconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + woodType[i]);
        }
    }
}