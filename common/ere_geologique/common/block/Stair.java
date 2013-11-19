package ere_geologique.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Stair extends BlockStairs
{	
	public Stair(int par1, Block par2Block, int par2)
    {
        super(par1, par2Block, par2);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
        this.setLightOpacity(0);
    }

	public int idDropped(int id, Random rand, int fortune)
	{
		return EGBlockList.Stair.blockID;
	}

	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
	{
		if (id != EGBlockList.Stair.blockID)
		{

			for (int i = 0; i < 5; i++)
			{
				list.add(new ItemStack(id, 1, i));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {}
}