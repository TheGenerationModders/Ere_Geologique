package ere_geologique.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.block.Leaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;

public class EGItemLeaves extends ItemBlock
{

	public EGItemLeaves(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	public int getMetadata(int par1)
    {
        return par1 | 5;
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return EGBlockList.Leaves.getIcon(0, par1);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        int j = par1ItemStack.getItemDamage();
        return (j & 1) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((j & 2) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = par1ItemStack.getItemDamage();

        if (i < 0 || i >= Leaves.leafType.length)
        {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + Leaves.leafType[i];
    }
}
