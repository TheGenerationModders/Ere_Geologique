package ere_geologique.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class FougereSeeds extends Item implements IPlantable
{

	public FougereSeeds(int par1)
	{
		super(par1);
		maxStackSize = 64;
		this.setCreativeTab(EreGeologique.EreGeologiqueCreativeTab);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("EreGeologique:Items");
    }

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z)
	{
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z)
	{
		return EreGeologique.CropPlanteFougereID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z)
	{
		return 0;
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            int var11 = par3World.getBlockId(par4, par5, par6);
            Block soil = Block.blocksList[var11];
 
            if (soil != null && soil.blockID == Block.tilledField.blockID && par3World.isAirBlock(par4, par5 + 1, par6))
            {
                 par3World.setBlock(par4, par5 + 1, par6, EreGeologique.CropPlanteFougereID);
                --par1ItemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
	}
}