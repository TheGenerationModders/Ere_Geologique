package ere_geologique.common.block;

import ere_geologique.common.creativetabs.EGCreativeTab;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Skull extends BlockDirectional
{
    private Icon Front;
    private Icon Back;

    public Skull(int var1)
    {
        super(var1, Material.pumpkin);
        this.setTickRandomly(true);
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ere_geologique:Skull_Side");
        this.Front = par1IconRegister.registerIcon("ere_geologique:Skull_Front");
        this.Back = par1IconRegister.registerIcon("ere_geologique:Skull_Back");//Bottom!
    }

    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 || par1 == 0 || (par1>3 && par2<4) || (par1<4 && par2>3)? this.blockIcon : par1!=par2 ? this.Front : this.Back;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
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