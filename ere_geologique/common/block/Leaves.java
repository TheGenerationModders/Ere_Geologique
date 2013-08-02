package ere_geologique.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EGCreativeTab;
import ere_geologique.common.config.EGProperties;

public class Leaves extends BlockLeavesBase implements IShearable
{
	public static final String[] leafType = new String[] {"fougere", "cycas", "araucarias", "metasequoias", "ginkgos"};
	public static final String[][] leafTextureTypes = new String[][] {{"leaves", "leaves_cycas", "leaves_araucarias", "leaves_metasequoias", "leaves_ginkgos"},{"leaves_opaque", "leaves_cycas_opaque", "leaves_araucarias_opaque", "leaves_metasequoias_opaque", "leaves_ginkgos_opaque"}};
	@SideOnly(Side.CLIENT)
	private int LEAF;
	private Icon[][] IconArray = new Icon[2][];
    int[] adjacentTreeBlocks;
 
    public Leaves(int par1)
    {
        super(par1, Material.leaves, false);
        this.setTickRandomly(true);
        this.setCreativeTab(EGCreativeTab.EGCreativeTab);
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerFoliage.getFoliageColor(d0, d1);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return (par1 & 4) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 4) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
 
        if ((var5 & 4) == 1)
        {
            return ColorizerFoliage.getFoliageColorPine();
        }
        else if ((var5 & 4) == 2)
        {
            return ColorizerFoliage.getFoliageColorBirch();
        }
        else
        {
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
 
            for (int var9 = -1; var9 <= 1; ++var9)
            {
                for (int var10 = -1; var10 <= 1; ++var10)
                {
                    int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
                    var6 += (var11 & 16711680) >> 16;
                    var7 += (var11 & 65280) >> 8;
                    var8 += var11 & 255;
                }
            }
 
            return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
        }
    }
 
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte var7 = 1;
        int var8 = var7 + 1;
 
        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
 
                        if (Block.blocksList[var12] != null)
                        {
                            Block.blocksList[var12].beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
                        }
                    }
                }
            }
        }
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
 
            if ((var6 & 8) != 0 && (var6 & 4) == 0)
            {
                byte var7 = 4;
                int var8 = var7 + 1;
                byte var9 = 32;
                int var10 = var9 * var9;
                int var11 = var9 / 2;
 
                if (this.adjacentTreeBlocks == null)
                {
                    this.adjacentTreeBlocks = new int[var9 * var9 * var9];
                }
 
                int var12;
 
                if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
                {
                    int var13;
                    int var14;
                    int var15;
 
                    for (var12 = -var7; var12 <= var7; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                var15 = par1World.getBlockId(par2 + var12, par3 + var13, par4 + var14);
 
                                Block block = Block.blocksList[var15];
 
                                if (block != null && block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                                }
                                else if (block != null && block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                                }
                                else
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                                }
                            }
                        }
                    }
 
                    for (var12 = 1; var12 <= 4; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                for (var15 = -var7; var15 <= var7; ++var15)
                                {
                                    if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
                                    {
                                        if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }
 
                                        if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }
 
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                        }
 
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                        }
 
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
                                        }
 
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
 
                var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
 
                if (var12 >= 0)
                {
                    par1World.setBlock(par2, par3, par4, var6 & -9);
                }
                else
                {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)par3 - 0.05D;
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            par1World.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    
    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlock(par2, par3, par4, 0);
    }
 
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(20) == 0 ? 1 : 0;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return EGProperties.SaplingID;
    }
    
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }
 
    public int damageDropped(int par1)
    {
        return par1 & 4;
    }
    
    public boolean isOpaqueCube()
    {
        return !this.graphicsLevel;
    }
    
    public Icon getIcon(int par1, int par2)
    {
        return (par2 & 4) == 1 ? this.IconArray[this.LEAF][1] : ((par2 & 4) == 3 ? this.IconArray[this.LEAF][3] : this.IconArray[this.LEAF][0]);
    }
    
    public void setGraphicsLevel(boolean par1)
    {
        this.graphicsLevel = par1;
        this.LEAF = par1 ? 0 : 1;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List par3List)
    {
    	par3List.add(new ItemStack(id, 1, 0));
    	par3List.add(new ItemStack(id, 1, 1));
    	par3List.add(new ItemStack(id, 1, 2));
    	par3List.add(new ItemStack(id, 1, 3));
    	par3List.add(new ItemStack(id, 1, 4));
    }
 
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1 & 4);
    }
 
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < leafTextureTypes.length; ++i)
        {
            this.IconArray[i] = new Icon[leafTextureTypes[i].length];

            for (int j = 0; j < leafTextureTypes[i].length; ++j)
            {
                this.IconArray[i][j] = par1IconRegister.registerIcon("EreGeologique" + leafTextureTypes[i][j]);
            }
        }    }
 
    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }
 
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 4));
        return ret;
    }
 
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
    }
 
    @Override
    public boolean isLeaves(World world, int x, int y, int z)
    {
        return true;
    }
}