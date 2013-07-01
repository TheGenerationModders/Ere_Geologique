package ere_geologique.common.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;

public class FougereWood extends BlockDirectional
{
	public static final String[] woodType = new String[] {"Fougere"};
    public FougereWood(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
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
 
    // Ce code permet de dropper le block quand il est detruit par une explosion. Peut etre changer
    // (comme de la stone donne de la cobblestone )
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return EreGeologique.FougereWoodID;
    }
 
    //Important: Ce code separe votre texture
    //les nombres a changer sont suivi d'un /**/
    //replacez 9 par le numero de la case de la texture du dessus et d'en dessous, et 8 par la texture des faces.
 
    public int getBlockTextureFromSide(int par1)
    {
        return par1 == 0 ? this.blockIndexInTexture + 1 : (par1 == 1 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture);
    }
    public int getBlockTextureFromSide1(int par1)
    {
        return par1 == 1 ? this.blockIndexInTexture + 1: (par1 == 0 ? this.blockIndexInTexture + 1: (par1 == 3 ? this.blockIndexInTexture: this.blockIndexInTexture + 1));
    }
 
    @SideOnly(Side.CLIENT)
 
    //Place le block dans la categorie voulue de l'inventaire creative
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
 
    }
 
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
}
