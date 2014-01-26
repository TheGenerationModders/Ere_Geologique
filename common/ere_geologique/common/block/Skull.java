package ere_geologique.common.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ere_geologique.common.creativetabs.EGCreativeTab;

public class Skull extends BlockDirectional
{
    private IIcon Front;
    private IIcon Back;

    public Skull()
    {
        super(Material.field_151572_C);
        this.setTickRandomly(true);
        this.func_149647_a(EGCreativeTab.EGCreativeTabBlock);
    }
    
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("ere_geologique:Skull_Side");
        this.Front = par1IconRegister.registerIcon("ere_geologique:Skull_Front");
        this.Back = par1IconRegister.registerIcon("ere_geologique:Skull_Back");//Bottom!
    }

    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 || par1 == 0 || (par1>3 && par2<4) || (par1<4 && par2>3)? this.field_149761_L : par1!=par2 ? this.Front : this.Back;
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack par6ItemStack)
    {
        int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        switch(var6)
        {
        case 0:var6=2;break;
        case 1:var6=5;break;
        case 2:var6=3;break;
        case 3:var6=4;break;
        }
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
        System.out.println(String.valueOf(var6));
    }
}