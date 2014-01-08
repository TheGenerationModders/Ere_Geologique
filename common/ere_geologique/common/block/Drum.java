package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.config.EGProperties;
import ere_geologique.common.tileentity.TileEntityDrum;

public class Drum extends BlockContainer
{
    Icon Top1;
    Icon Top2;
    Icon Top3;
    Icon Bottom;
    public Drum(int var1)
    {
        super(var1, Material.wood);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return EGProperties.drumID;
    }

    
    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ere_geologique:Drum_Side");
        this.Top1 = par1IconRegister.registerIcon("ere_geologique:Drum_Top1");
        this.Top2 = par1IconRegister.registerIcon("ere_geologique:Drum_Top2");
        this.Top3 = par1IconRegister.registerIcon("ere_geologique:Drum_Top3");
        this.Bottom = par1IconRegister.registerIcon("ere_geologique:Drum_Bottom");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par1 != 1 && par1 != 0)
        {
            return blockIcon;
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
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote)
        {
            TileEntityDrum var10 = (TileEntityDrum)var1.getBlockTileEntity(var2, var3, var4);
            var10.TriggerOrder(var5);
            var1.setBlockMetadataWithNotify(var2, var3, var4, var10.Order.ordinal(), var6);
        }

        return true;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        if (!var1.isRemote)
        {
            TileEntityDrum var6 = (TileEntityDrum)var1.getBlockTileEntity(var2, var3, var4);

            if (var5.inventory.getCurrentItem() != null)
            {
                var6.SendOrder(var5.inventory.getCurrentItem().itemID, var5);
            }
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityDrum();
    }
}