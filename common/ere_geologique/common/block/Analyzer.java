package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.tileentity.TileEntityAnalyzer;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class Analyzer extends BlockContainer
{
    private Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;
    @SideOnly(Side.CLIENT)
    private Icon Top;
    @SideOnly(Side.CLIENT)
    private Icon Front;
    

    public Analyzer(int var1, boolean var2)
    {
        super(var1, Material.iron);
        this.isActive = var2;
    }

    public int getRenderType()
    {
    	return 2303;
    }

    public int idDropped(int var1, Random var2, int var3)
    {
        return EGBlockList.AnalyzerIdle.blockID;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        this.setDefaultDirection(var1, var2, var3, var4);
    }

    private void setDefaultDirection(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            int var5 = var1.getBlockId(var2, var3, var4 - 1);
            int var6 = var1.getBlockId(var2, var3, var4 + 1);
            int var7 = var1.getBlockId(var2 - 1, var3, var4);
            int var8 = var1.getBlockId(var2 + 1, var3, var4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])var9 = 3;

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])var9 = 2;

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])var9 = 5;

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])var9 = 4;

            var1.setBlockMetadataWithNotify(var2, var3, var4, var9,2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ere_geologique:Analyser_Sides");
        this.Top = par1IconRegister.registerIcon("ere_geologique:Analyser_Top");
        this.Front = this.isActive? par1IconRegister.registerIcon("ere_geologique:Analyser_Front_Active") : par1IconRegister.registerIcon("ere_geologique:Analyser_Front_Idle");
    }

    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.Top : ((par1 == par2 && par1!=0) || (par2==3 && par1==0) ? this.Front : this.blockIcon);
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            return true;
        }
        else
        {
            var5.openGui(EreGeologique.Instance, 2, var1, var2, var3, var4);
            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);

        if (var6 != null)
        {
            keepFurnaceInventory = true;

            if (var0)
            {
                var1.setBlock(var2, var3, var4, EGBlockList.AnalyserActive.blockID);
            }
            else
            {
                var1.setBlock(var2, var3, var4, EGBlockList.AnalyzerIdle.blockID);
            }

            keepFurnaceInventory = false;
            var1.setBlockMetadataWithNotify(var2, var3, var4, var5,2);
            var6.validate();
            var1.setBlockTileEntity(var2, var3, var4, var6);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityFeeder)
		{
			((TileEntityAnalyzer)te).setDirection(direction);
			world.markBlockForUpdate(x, y, z);
		}
    }

    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepFurnaceInventory)
        {
            TileEntityAnalyzer var7 = (TileEntityAnalyzer)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
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

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

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

    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityAnalyzer();
    }
}