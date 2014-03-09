package ere_geologique.common.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Skull extends BlockDirectional
{
    private IIcon Front;
    private IIcon Back;

    public Skull()
    {
        super(Material.gourd);
        this.setTickRandomly(true);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ere_geologique:Skull_Side");
        this.Front = par1IconRegister.registerIcon("ere_geologique:Skull_Front");
        this.Back = par1IconRegister.registerIcon("ere_geologique:Skull_Back");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 || side == 0 || (side > 3 && metadata < 4) || (side < 4 && metadata > 3)? this.blockIcon : side != metadata ? this.Front : this.Back;
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
    {
        int direction = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        switch(direction)
        {
        case 0:direction=2;break;
        case 1:direction=5;break;
        case 2:direction=3;break;
        case 3:direction=4;break;
        }
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
        System.out.println(String.valueOf(direction));
    }
}