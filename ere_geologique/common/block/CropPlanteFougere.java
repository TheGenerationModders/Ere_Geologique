package ere_geologique.common.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.proxy.EreGeologiqueClientProxy;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.config.EGProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CropPlanteFougere extends BlockCrops
{

	protected CropPlanteFougere(int par1)
	{
		super(par1);
		this.setTickRandomly(true);
		float var3 = 0.5F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
		this.setCreativeTab((CreativeTabs)null);
		this.setHardness(0.0F);
		this.disableStats();
		this.setRequiresSelfNotify();
	}
	
	protected boolean canthisPlantGrownOnThisBlockID(int par3)
	{
		return par3 == Block.tilledField.blockID;
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		
		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
		{
			int i = par1World.getBlockMetadata(par2, par3, par4);
			
			if (i < 7) {
				float f = getGrowthRate(par1World, par2, par3, par4);
				
				if (par5Random.nextInt((int) (20F / f) + 1) == 0)
				{
					i++;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 0);
				}
			}
		}
	}
	
	private float getGrowthRate(World par1World, int par2, int par3, int par4)
	{
		float f = 1.0F;
        int i = par1World.getBlockId(par2, par3, par4 - 1);
        int j = par1World.getBlockId(par2, par3, par4 + 1);
        int k = par1World.getBlockId(par2 - 1, par3, par4);
        int l = par1World.getBlockId(par2 + 1, par3, par4);
        int i1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
        int j1 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
        int l1 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
        boolean flag = k == blockID || l == blockID;
        boolean flag1 = i == blockID || j == blockID;
        boolean flag2 = i1 == blockID || j1 == blockID || k1 == blockID
                || l1 == blockID;
 
        for (int i2 = par2 - 1; i2 <= par2 + 1; i2++) {
            for (int j2 = par4 - 1; j2 <= par4 + 1; j2++) {
                int k2 = par1World.getBlockId(i2, par3 - 1, j2);
                float f1 = 0.0F;
 
                if (k2 == Block.tilledField.blockID)
                {
                    f1 = 1.0F;
 
                    if (par1World.getBlockMetadata(i2, par3 - 1, j2) > 0)
                    {
                        f1 = 3F;
                    }
                }
                if (i2 != par2 || j2 != par4)
                {
                    f1 /= 4F;
                }
 
                f += f1;
            }
        }
 
        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }
 
        return f;
	}
	protected int getCropItem()
    {
        return EGProperties.FruitFougereID;
    }
 
    protected int getSeedItem()
    {
        return EGProperties.FougereSeedsID;
    }
 
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par2 < 0)
        {
            par2 = 7;
        }
 
        return this.blockIndexInTexture + par2;
    }
 
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }
 
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
 
        if (metadata >= 7)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.getSeedItem(), 1, 0));
                }
            }
        }
 
        return ret;
    }
 
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 7 ? this.getCropItem() : this.getSeedItem();
    }
 
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
 
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        if (!(itemstack == null) && itemstack.itemID == Item.dyePowder.itemID && itemstack.getItemDamage() == 15)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, blockID, 7);
 
            if (!par5EntityPlayer.capabilities.isCreativeMode)
            {
                itemstack.stackSize--;
            }
 
            if (itemstack.stackSize <= 0)
            {
                par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
            }
            return true;
        } else {
            return false;
        }
    }
 
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.getSeedItem();
    }
 
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }
 
}
