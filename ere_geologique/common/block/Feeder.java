package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class Feeder extends BlockContainer
{
    private Random furnaceRand = new Random();
    private Icon Top1;//None
    private Icon Top2;//Herb
    private Icon Top3;//Carn
    private Icon Top4;//Both
    private Icon Front1;
    private Icon Front2;
    private Icon Front3;
    private Icon Front4;
    private Icon Bottom;
    
    private static final int NO_BIT = 0;
    private static final int HERB_BIT = 4;
    private static final int CARN_BIT = 8;
    private static final int BOTH_BITS = 12;
    
    private static final int DIRECTION_BITS = 3;
    
    public Feeder(int var1)
    {
        super(var1, Material.rock);
    }

    public int idDropped(int var1, Random var2, int var3)
    {
        return EGBlockList.FeederActive.blockID;
    }
    public int getRenderType()
    {
    	return 2303;
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

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])var9 = 3-2;

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])var9 = 2-2;

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])var9 = 5-2;

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])var9 = 4-2;

            var1.setBlockMetadataWithNotify(var2, var3, var4, var9,2);
        }
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
    	int var6 = var1.getBlockMetadata(var2, var3, var4);
        if ((var6&BOTH_BITS)!=0 && var5.nextInt(25)==0)
        {
            float var7 = (float)var2 + 0.5F;
            float var8 = (float)var3 + 0.0F + var5.nextFloat() * 6.0F / 16.0F;
            float var9 = (float)var4 + 0.5F;
            float var10 = 0.52F;
            float var11 = var5.nextFloat() * 0.6F - 0.3F;

            if ((var6&DIRECTION_BITS) == 4-2)
            {
                var1.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
            else if ((var6&DIRECTION_BITS) == 5-2)
            {
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
            else if ((var6&DIRECTION_BITS) == 2-2)
            {
                var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
            }
            else if ((var6&DIRECTION_BITS) == 3-2)
            {
                var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ere_geologique:Feeder_Sides");
        this.Bottom = par1IconRegister.registerIcon("ere_geologique:Feeder_Bottom");
        this.Top1 = par1IconRegister.registerIcon("ere_geologique:Feeder_Top1");
        this.Top2 = par1IconRegister.registerIcon("ere_geologique:Feeder_Top2");
        this.Top3 = par1IconRegister.registerIcon("ere_geologique:Feeder_Top3");
        this.Top4 = par1IconRegister.registerIcon("ere_geologique:Feeder_Top4");
        this.Front1 = par1IconRegister.registerIcon("ere_geologique:Feeder_Front1");
        this.Front2 = par1IconRegister.registerIcon("ere_geologique:Feeder_Front2");
        this.Front3 = par1IconRegister.registerIcon("ere_geologique:Feeder_Front3");
        this.Front4 = par1IconRegister.registerIcon("ere_geologique:Feeder_Front4");
    }

    public Icon getIcon(int par1, int par2)
    {
    	if (par1 != 1 && ((par2&DIRECTION_BITS)+2) != par1)//Not Top and not Front=>Side
        {
            return this.blockIcon;
        }
        else
        {
        	if(par1==0)//Bottom
        		return this.Bottom;
        	if(par1==1)//Top
        	{
        		switch(par2&BOTH_BITS)
        		{
        			case NO_BIT:return this.Top1;//no food
        			case HERB_BIT:return this.Top2;//herbivore
        			case CARN_BIT:return this.Top3;//carnivore
        			case BOTH_BITS:return this.Top4;//both
        		}
        	}
        	else//Front
        	{
        		switch(par2&BOTH_BITS)
        		{
        			case NO_BIT:return this.Front1;//no food
        			case HERB_BIT:return this.Front2;//herbivore
        			case CARN_BIT:return this.Front3;//carnivore
        			case BOTH_BITS:return this.Front4;//both
        		}
        	}
        }
    	return this.blockIcon;
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            return true;
        }
        else
        {
        	var5.openGui(EreGeologique.Instance, 0, var1, var2, var3, var4);
            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean herb,boolean carn, World var1, int var2, int var3, int var4)
    {
    	if(var1.getBlockId(var2, var3, var4)==EGBlockList.FeederIdle.blockID)//won't be used anymore
    		var1.setBlock(var2, var3, var4,EGBlockList.FeederActive.blockID,0,2);
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        if(herb)
        	var5|=HERB_BIT;
        else
        	var5&=~HERB_BIT;
        if(carn)
        	var5|=CARN_BIT;
        else
        	var5&=~CARN_BIT;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
    }

    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityFeeder();
    }

    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack par6ItemStack)
    {
        int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)var1.setBlockMetadataWithNotify(var2, var3, var4, 2-2,2);

        if (var6 == 1)var1.setBlockMetadataWithNotify(var2, var3, var4, 5-2,2);

        if (var6 == 2)var1.setBlockMetadataWithNotify(var2, var3, var4, 3-2,2);

        if (var6 == 3)var1.setBlockMetadataWithNotify(var2, var3, var4, 4-2,2);
    }

    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        {
            TileEntityFeeder var7 = (TileEntityFeeder)var1.getBlockTileEntity(var2, var3, var4);

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
}