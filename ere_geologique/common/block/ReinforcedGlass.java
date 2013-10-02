package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class ReinforcedGlass extends BlockBreakable
{

	public ReinforcedGlass(int id, Material material, boolean par3)
	{
		super(id, "ere_geologique:reinforced_glass", material, par3);
		this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }
}