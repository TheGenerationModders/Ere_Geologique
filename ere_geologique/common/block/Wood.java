package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;

public class Wood extends BlockDirectional
{
	public static final String[] woodType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	public static final String[] treeTextureTypes = new String[] {"fougere_side", "cycas_side", "araucarias_side", "metasequoias_side", "ginkgos_side"};
	@SideOnly(Side.CLIENT)
	private Icon[] IconArray;
	@SideOnly(Side.CLIENT)
	private Icon[] wood_top;
	
    public Wood(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
    }
    
    public int quantityDropped(Random par1Random)
    {
    	return 1;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return EreGeologique.WoodID;
    }
 
    @SideOnly(Side.CLIENT)
    
    public Icon getIcon(int par1, int par2)
    {
    	int k = par2 & 12;
    	int l = par2 & 3;
    	return k == 0 && (par1 == 1 || par1 == 0) ? this.wood_top : (k == 4 && (par1 == 5 || par1 == 4) ? this.wood_top : (k == 8 && (par1 == 2 || par1 == 3) ? this.wood_top : this.IconArray[l]));
    }
    
    public int damageDropped(int par1)
    {
        return par1 & 4;
    }
    
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 4;
    }
 
    //Place le block dans la categorie voulue de l'inventaire creative
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
 
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
    }
 
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.wood_top = par1IconRegister.registerIcon("wood_top");
        this.IconArray = new Icon[treeTextureTypes.length];

        for (int i = 0; i < this.IconArray.length; ++i)
        {
            this.IconArray[i] = par1IconRegister.registerIcon(treeTextureTypes[i]);
        }
    }
}
