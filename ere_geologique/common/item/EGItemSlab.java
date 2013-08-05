package ere_geologique.common.item;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.block.EGBlockList;

public class EGItemSlab extends ItemBlock
{
	private final boolean isFullBlock;
	
	private final BlockHalfSlab Slab;
	
	private final BlockHalfSlab DoubleSlab;

	public EGItemSlab(int par1, BlockHalfSlab par2BlockHalfSlab, BlockHalfSlab par3BlockHalfSlab, boolean par4)
	{
		super(par1);
		this.Slab = par2BlockHalfSlab;
		this.DoubleSlab = par3BlockHalfSlab;
		this.isFullBlock = par4;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return EGBlockList.Slab.getIcon(0, par1);
	}
	
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.Slab.getFullSlabName(par1ItemStack.getItemDamage());
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (this.isFullBlock)
        {
            return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
        }
        else if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            int i1 = par3World.getBlockId(par4, par5, par6);
            int j1 = par3World.getBlockMetadata(par4, par5, par6);
            int k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;

            if ((par7 == 1 && !flag || par7 == 0 && flag) && i1 == this.Slab.blockID && k1 == par1ItemStack.getItemDamage())
            {
                if (par3World.checkNoEntityCollision(this.DoubleSlab.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlock(par4, par5, par6, this.DoubleSlab.blockID, k1, 3))
                {
                    par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), this.DoubleSlab.stepSound.getPlaceSound(), (this.DoubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.DoubleSlab.stepSound.getPitch() * 0.8F);
                    --par1ItemStack.stackSize;
                }

                return true;
            }
            else
            {
                return this.func_77888_a(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7) ? true : super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
            }
        }
    }
	
	@SideOnly(Side.CLIENT)
	public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack)
    {
        int i1 = par2;
        int j1 = par3;
        int k1 = par4;
        int l1 = par1World.getBlockId(par2, par3, par4);
        int i2 = par1World.getBlockMetadata(par2, par3, par4);
        int j2 = i2 & 7;
        boolean flag = (i2 & 8) != 0;

        if ((par5 == 1 && !flag || par5 == 0 && flag) && l1 == this.Slab.blockID && j2 == par7ItemStack.getItemDamage())
        {
            return true;
        }
        else
        {
            if (par5 == 0)
            {
                --par3;
            }

            if (par5 == 1)
            {
                ++par3;
            }

            if (par5 == 2)
            {
                --par4;
            }

            if (par5 == 3)
            {
                ++par4;
            }

            if (par5 == 4)
            {
                --par2;
            }

            if (par5 == 5)
            {
                ++par2;
            }

            l1 = par1World.getBlockId(par2, par3, par4);
            i2 = par1World.getBlockMetadata(par2, par3, par4);
            j2 = i2 & 7;
            flag = (i2 & 8) != 0;
            return l1 == this.Slab.blockID && j2 == par7ItemStack.getItemDamage() ? true : super.canPlaceItemBlockOnSide(par1World, i1, j1, k1, par5, par6EntityPlayer, par7ItemStack);
        }
    }
	
	private boolean func_77888_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (par7 == 0)
        {
            --par5;
        }

        if (par7 == 1)
        {
            ++par5;
        }

        if (par7 == 2)
        {
            --par6;
        }

        if (par7 == 3)
        {
            ++par6;
        }

        if (par7 == 4)
        {
            --par4;
        }

        if (par7 == 5)
        {
            ++par4;
        }

        int i1 = par3World.getBlockId(par4, par5, par6);
        int j1 = par3World.getBlockMetadata(par4, par5, par6);
        int k1 = j1 & 7;

        if (i1 == this.Slab.blockID && k1 == par1ItemStack.getItemDamage())
        {
            if (par3World.checkNoEntityCollision(this.DoubleSlab.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlock(par4, par5, par6, this.DoubleSlab.blockID, k1, 3))
            {
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), this.DoubleSlab.stepSound.getPlaceSound(), (this.DoubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.DoubleSlab.stepSound.getPitch() * 0.8F);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
