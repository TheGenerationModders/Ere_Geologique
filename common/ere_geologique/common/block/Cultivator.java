package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.entity.Failuresaurus;
import ere_geologique.common.tileentity.TileEntityCultivator;

public class Cultivator extends BlockContainer
{
    private Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;
    @SideOnly(Side.CLIENT)
    private Icon Top;
    @SideOnly(Side.CLIENT)
    private Icon Bottom;

    public Cultivator(int var1, boolean var2)
    {
        super(var1, Material.glass);
        this.isActive = var2;
    }

    public int idDropped(int var1, Random var2, int var3)
    {
        return EGBlockList.CultivatorIdle.blockID;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        this.setDefaultDirection(var1, var2, var3, var4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.isActive? "ere_geologique:Culture_Sides_Active" : "ere_geologique:Culture_Sides_Idle");
        this.Bottom = par1IconRegister.registerIcon("ere_geologique:Culture_Bottom");
        this.Top = par1IconRegister.registerIcon("ere_geologique:Culture_Top");
    }

    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.Top : (par1 != 0 ? this.blockIcon : this.Bottom);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            return true;
        }
        else
        {
        	var5.openGui(EreGeologique.Instance, 3, var1, var2, var3, var4);
            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
        keepFurnaceInventory = true;

        if (var0)
        {
            var1.setBlock(var2, var3, var4, EGBlockList.CultivatorActive.blockID);
        }
        else
        {
            var1.setBlock(var2, var3, var4, EGBlockList.CultivatorIdle.blockID);
        }

        keepFurnaceInventory = false;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5,2);
        var6.validate();
        var1.setBlockTileEntity(var2, var3, var4, var6);
    }

    public TileEntity createNewTileEntity(World var1)
    {
    	return new TileEntityCultivator();
    }

    private void ReturnIron(World var1, int var2, int var3, int var4)
    {
        ItemStack var5 = new ItemStack(Item.ingotIron, 3);
        float var6 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
        float var7 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
        float var8 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

        while (var5.stackSize > 0)
        {
            int var9 = this.furnaceRand.nextInt(21) + 10;

            if (var9 > var5.stackSize)
            {
                var9 = var5.stackSize;
            }

            var5.stackSize -= var9;
            EntityItem var10 = new EntityItem(var1, (double)((float)var2 + var6), (double)((float)var3 + var7), (double)((float)var4 + var8), new ItemStack(var5.itemID, var9, var5.getItemDamage()));
            float var11 = 0.05F;
            var10.motionX = (double)((float)this.furnaceRand.nextGaussian() * var11);
            var10.motionY = (double)((float)this.furnaceRand.nextGaussian() * var11 + 0.2F);
            var10.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var11);
            var1.spawnEntityInWorld(var10);
        }
    }

    public void onBlockRemovalLost(World var1, int var2, int var3, int var4, boolean var5)
    {
        keepFurnaceInventory = false;
        String var6 = StatCollector.translateToLocal(LocalizationStrings.CULTIVATE_OUTBREAK);

        for (int var7 = 0; var7 < var1.playerEntities.size(); ++var7)
        {
        	EntityPlayer P=(EntityPlayer)var1.playerEntities.get(var7);
        	if(Math.pow(var2-P.posX,2D)+Math.pow(var3-P.posY,2D)+Math.pow(var4-P.posZ,2D)<10000)//Only for Players closer than 100 Metres
        		EreGeologique.ShowMessage(var6,P);
        }

        this.ReturnIron(var1, var2, var3, var4);
        var1.setBlock(var2, var3, var4, 0);
        var1.removeBlockTileEntity(var2, var3, var4);

        if (var5)
        {
            Object var9 = null;
            var1.playAuxSFX(2001, var2, var3, var4, Block.glass.blockID);
            var1.setBlock(var2, var3, var4, Block.waterMoving.blockID);

            if (var1.isRemote)
            {
                return;
            }

            int var8 = var1.rand.nextInt(100);

            if (var8 <= 5)
            {
                var9 = new EntityCreeper(var1);
            }

            if (var8 > 5 && var8 < 10)
            {
                var9 = new EntityPigZombie(var1);
            }

            if (var8 >= 10)
            {
                var9 = new Failuresaurus(var1);
            }

            ((EntityLiving)var9).setLocationAndAngles((double)var2, (double)var3, (double)var4, var1.rand.nextFloat() * 360.0F, 0.0F);
            var1.spawnEntityInWorld((Entity)var9);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepFurnaceInventory)
        {
            TileEntityCultivator var7 = (TileEntityCultivator)var1.getBlockTileEntity(var2, var3, var4);

            if(var7 instanceof TileEntityCultivator)
            {
		        for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
		        {
		            ItemStack var9 = var7.getStackInSlot(var8);
		
		            if (var9 != null)
		            {
		                float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		                float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		                float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		
		                while (var9.stackSize > 0)
		                {
		                    int var13 = this.furnaceRand.nextInt(21) + 10;
		
		                    if (var13 > var9.stackSize)
		                    {
		                        var13 = var9.stackSize;
		                    }
		
		                    var9.stackSize -= var13;
		                    EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));
		                    float var15 = 0.05F;
		                    var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
		                    var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
		                    var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
		                    var1.spawnEntityInWorld(var14);
		                }
		            }
		        }
            }
        }
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
    	return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
    	return EGBlockList.CultivatorIdle.blockID;
    }
}