package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.tileentity.TileEntityDrum;

public class Drum extends BlockContainer
{
    IIcon Top1;
    IIcon Top2;
    IIcon Top3;
    IIcon Bottom;
    public Drum()
    {
        super(Material.field_151575_d);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item idDropped(int var1, Random var2, int var3)
    {
        return Item.func_150898_a(EGBlockList.drum);
    }

    
    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("ere_geologique:Drum_Side");
        this.Top1 = par1IconRegister.registerIcon("ere_geologique:Drum_Top1");
        this.Top2 = par1IconRegister.registerIcon("ere_geologique:Drum_Top2");
        this.Top3 = par1IconRegister.registerIcon("ere_geologique:Drum_Top3");
        this.Bottom = par1IconRegister.registerIcon("ere_geologique:Drum_Bottom");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 != 1 && par1 != 0)
        {
            return field_149761_L;
        }
        else
        {
            if (par1 == 0)
            {
                return Bottom;
            }

            switch (par2)
            {
                case 0:
                default:
                    return Top1;

                case 1:
                    return Top2;

                case 2:
                    return Top3;
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            TileEntityDrum var10 = (TileEntityDrum)world.func_147438_o(x, y, z);
            var10.TriggerOrder(player);
            world.setBlockMetadataWithNotify(x, y, z, var10.Order.ordinal(), side);
        }

        return true;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            TileEntityDrum var6 = (TileEntityDrum)world.func_147438_o(x, y, z);

            if (player.inventory.getCurrentItem().getItem() != null)
            {
                var6.SendOrder(player.inventory.getCurrentItem().getItem(), player);
            }
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity func_149915_a(World world, int par2)
    {
        return new TileEntityDrum();
    }
}