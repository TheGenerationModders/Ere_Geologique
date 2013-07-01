package ere_geologique.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class FougerePlank extends Block
{
	public FougerePlank(int par1, int par2)
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
 
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
}
