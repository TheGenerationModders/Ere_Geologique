package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
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
    private IIcon Top;
    @SideOnly(Side.CLIENT)
    private IIcon Bottom;

    public Cultivator(boolean var2)
    {
        super(Material.field_151592_s);
        this.isActive = var2;
    }

    public Item idDropped(int var1, Random var2, int var3)
    {
        return Item.func_150898_a(EGBlockList.cultivatorIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.func_149726_b(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block l = world.func_147439_a(x, y, z - 1);
            Block i1 = world.func_147439_a(x, y, z + 1);
            Block j1 = world.func_147439_a(x - 1, y, z);
            Block k1 = world.func_147439_a(x + 1, y, z);
            byte b0 = 3;

            if (l.func_149730_j() && !i1.func_149730_j())
            {
                b0 = 3;
            }

            if (i1.func_149730_j() && !l.func_149730_j())
            {
                b0 = 2;
            }

            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                b0 = 5;
            }

            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon(this.isActive? "ere_geologique:Culture_Sides_Active" : "ere_geologique:Culture_Sides_Idle");
        this.Bottom = par1IconRegister.registerIcon("ere_geologique:Culture_Bottom");
        this.Top = par1IconRegister.registerIcon("ere_geologique:Culture_Top");
    }

    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.Top : (par1 != 0 ? this.field_149761_L : this.Bottom);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	player.openGui(EreGeologique.Instance, 3, world, x, y, z);
            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World world, int x, int y, int z)
    {
        int var5 = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        keepFurnaceInventory = true;

        if (var0)
        {
            world.func_147449_b(x, y, z, EGBlockList.cultivatorActive);
        }
        else
        {
            world.func_147449_b(x, y, z, EGBlockList.cultivatorIdle);
        }

        keepFurnaceInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, var5,2);
        if(tileEntity != null)
        {
            tileEntity.func_145829_t();
            world.func_147455_a(x, y, z, tileEntity);
        }
    }
    /*
    public TileEntity createNewTileEntity(World world)
    {
    	return new TileEntityCultivator();
    }*/

    private void ReturnIron(World world, int x, int y, int z)
    {
        ItemStack itemStack = new ItemStack(Items.iron_ingot, 3);
        float var6 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
        float var7 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
        float var8 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

        while (itemStack.stackSize > 0)
        {
            int var9 = this.furnaceRand.nextInt(21) + 10;

            if (var9 > itemStack.stackSize)
            {
                var9 = itemStack.stackSize;
            }

            itemStack.stackSize -= var9;
            EntityItem var10 = new EntityItem(world, (double)((float)x + var6), (double)((float)y + var7), (double)((float)z + var8), new ItemStack(itemStack.getItem(), var9, itemStack.getItemDamage()));
            float var11 = 0.05F;
            var10.motionX = (double)((float)this.furnaceRand.nextGaussian() * var11);
            var10.motionY = (double)((float)this.furnaceRand.nextGaussian() * var11 + 0.2F);
            var10.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var11);
            world.spawnEntityInWorld(var10);
        }
    }

    public void onBlockRemovalLost(World world, int x, int y, int z, boolean var5)
    {
        keepFurnaceInventory = false;
        String var6 = StatCollector.translateToLocal(LocalizationStrings.CULTIVATE_OUTBREAK);

        for (int var7 = 0; var7 < world.playerEntities.size(); ++var7)
        {
        	EntityPlayer P=(EntityPlayer)world.playerEntities.get(var7);
        	if(Math.pow(x-P.posX,2D)+Math.pow(y-P.posY,2D)+Math.pow(z-P.posZ,2D)<10000)//Only for Players closer than 100 Metres
        		EreGeologique.ShowMessage(var6,P);
        }

        this.ReturnIron(world, x, y, z);
        world.setBlock(x, y, z, 0);
        world.removeBlockTileEntity(x, y, z);

        if (var5)
        {
            Object var9 = null;
            world.playAuxSFX(2001, x, y, z, Blocks.glass.blockID);
            world.func_147449_b(x, y, z, Blocks.water);

            if (world.isRemote)
            {
                return;
            }

            int var8 = world.rand.nextInt(100);

            if (var8 <= 5)
            {
                var9 = new EntityCreeper(world);
            }

            if (var8 > 5 && var8 < 10)
            {
                var9 = new EntityPigZombie(world);
            }

            if (var8 >= 10)
            {
                var9 = new Failuresaurus(world);
            }

            ((EntityLiving)var9).setLocationAndAngles((double)x, (double)y, (double)z, world.rand.nextFloat() * 360.0F, 0.0F);
            world.spawnEntityInWorld((Entity)var9);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, Block var5, int var6)
    {
        if (!keepFurnaceInventory)
        {
            TileEntityCultivator tileEntity = (TileEntityCultivator)world.func_147438_o(x, y, z);

            if(tileEntity instanceof TileEntityCultivator)
            {
		        for (int var8 = 0; var8 < tileEntity.getSizeInventory(); ++var8)
		        {
		            ItemStack itemStack = tileEntity.getStackInSlot(var8);
		
		            if (itemStack != null)
		            {
		                float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		                float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		                float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
		
		                while (itemStack.stackSize > 0)
		                {
		                    int var13 = this.furnaceRand.nextInt(21) + 10;
		
		                    if (var13 > itemStack.stackSize)
		                    {
		                        var13 = itemStack.stackSize;
		                    }
		
		                    itemStack.stackSize -= var13;
		                    EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(itemStack.getItem(), var13, itemStack.getItemDamage()));
		                    float var15 = 0.05F;
		                    var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
		                    var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
		                    var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
		                    world.spawnEntityInWorld(var14);
		                }
		            }
		        }
            }
        }
        super.func_149749_a(world, x, y, z, var5, var6);
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
    	return Container.calcRedstoneFromInventory((IInventory)par1World.func_147438_o(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public Item idPicked(World world, int x, int y, int z)
    {
    	return Item.func_150898_a(EGBlockList.cultivatorIdle);
    }

	@Override
	public TileEntity func_149915_a(World world, int var2)
	{
		return new TileEntityCultivator();
	}
}