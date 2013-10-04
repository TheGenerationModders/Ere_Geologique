package ere_geologique.common.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import ere_geologique.common.block.Analyzer;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;

public class TileEntityAnalyzer extends TileEntity implements IInventory, ISidedInventory
{
    private ItemStack[] analyzerItemStacks;
    public int analyzerBurnTime = 0;
    public int currentItemBurnTime = 100;
    public int analyzerCookTime = 0;
    private int RawIndex = -1;
    private int SpaceIndex = -1;
    
    private static final int[] field_102010_d = new int[] {0};
    private static final int[] field_102011_e = new int[] {2, 1};
    private static final int[] field_102009_f = new int[] {1};

    public TileEntityAnalyzer ()
    {
     analyzerItemStacks = new ItemStack[13];
    }

    public int getSizeInventory()
    {
        return this.analyzerItemStacks.length;
    }

    public ItemStack getStackInSlot(int var1)
    {
        return this.analyzerItemStacks[var1];
    }

    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.analyzerItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.analyzerItemStacks[var1].stackSize <= var2)
            {
                var3 = this.analyzerItemStacks[var1];
                this.analyzerItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.analyzerItemStacks[var1].splitStack(var2);

                if (this.analyzerItemStacks[var1].stackSize == 0)
                {
                    this.analyzerItemStacks[var1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.analyzerItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "Analyzer";
    }

    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.analyzerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.analyzerItemStacks.length)
            {
                this.analyzerItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.analyzerBurnTime = var1.getShort("BurnTime");
        this.analyzerCookTime = var1.getShort("CookTime");
        this.currentItemBurnTime = 100;
    }

    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("BurnTime", (short)this.analyzerBurnTime);
        var1.setShort("CookTime", (short)this.analyzerCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.analyzerItemStacks.length; ++var3)
        {
            if (this.analyzerItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.analyzerItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int var1)
    {
        return this.analyzerCookTime * var1 / 200;
    }

    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 100;
        }

        return this.analyzerBurnTime * var1 / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.analyzerBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean var1 = this.analyzerBurnTime > 0;
        boolean var2 = false;

        if (this.analyzerBurnTime > 0)
        {
            --this.analyzerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.analyzerBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.analyzerBurnTime = 100;

                if (this.analyzerBurnTime > 0)
                {
                    var2 = true;
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.analyzerCookTime;

                if (this.analyzerCookTime == 200)
                {
                    this.analyzerCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.analyzerCookTime = 0;
            }

            if (var1 != this.analyzerBurnTime > 0)
            {
                var2 = true;
                Analyzer.updateFurnaceBlockState(this.analyzerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        this.SpaceIndex = -1;
        this.RawIndex = -1;
        int var1;

        for (var1 = 0; var1 < 9; ++var1)
        {
            if (this.analyzerItemStacks[var1] != null)
            {
                int var2 = this.analyzerItemStacks[var1].getItem().itemID;
                
                if (EnumDinoType.isDinoDrop(this.analyzerItemStacks[var1].getItem()) || (var2 == EGItemList.BioFossil.itemID ) || /*(var2 == Fossil.rawDinoMeat.itemID) ||*/ (var2 == Item.porkRaw.itemID) || (var2 == Item.beefRaw.itemID) || (var2 == Item.egg.itemID) || (var2 == Item.chickenRaw.itemID) || (var2 == Block.cloth.blockID))
                {	
                    this.RawIndex = var1;
                    break;
                }
            }
        }

        if (this.RawIndex == -1)
        {
            return false;
        }
        else
        {
            for (var1 = 12; var1 > 8; --var1)
            {
                if (this.analyzerItemStacks[var1] == null)
                {
                    this.SpaceIndex = var1;
                    break;
                }
            }

            return this.SpaceIndex != -1 && this.RawIndex != -1;
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = null;
            int var2 = (new Random()).nextInt(100);
            int var3;

            if (this.analyzerItemStacks[this.RawIndex].getItem() == EGItemList.BioFossil)
            {
            	if (var2 < 1)
                {
                    var1 = new ItemStack(EGItemList.BrokenSapling, 1);
                }
                if (var2 > 1 && var2 <= 45)
                {
                    var1 = new ItemStack(Item.dyePowder, 3, 15);
                }
                if (var2 > 45 && var2 <= 85)
                {
                    var1 = new ItemStack(Block.sand,3);
                }
                if (var2 > 85)
                {
                	int i=(new Random()).nextInt(EnumDinoType.values().length+1);//+1 for the sapling
                	Item i0=null;
                	if(i==0)i0=EGItemList.BrokenSapling;
                	else
                		i0=EnumDinoType.values()[i-1].DNAItem;
                    var1 = new ItemStack(i0, 1);
                }
            }

            if(EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem())!=null)
            	var1= new ItemStack(EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem()),1);
            if (this.analyzerItemStacks[this.RawIndex].getItem() == EGItemList.Relic)
            {
                if (var2 <= 40)
                    var1 = new ItemStack(Block.gravel, 2);

                if (var2 > 40)
                    var1 = new ItemStack(Item.flint, 2);
            }
            if (var1 != null)
            {
                if (var1.itemID == Item.dyePowder.itemID || var1.itemID == Item.flint.itemID || var1.itemID == Block.gravel.blockID || var1.itemID == EGItemList.Relic.itemID || var1.itemID == EGItemList.BrokenSapling.itemID || var1.itemID == Block.sand.blockID)
                {
                    for (var3 = 12; var3 > 8; --var3)
                    {
                        if (this.analyzerItemStacks[var3] != null && var1.itemID == this.analyzerItemStacks[var3].itemID)
                        {
                            if (this.analyzerItemStacks[var3].stackSize + var1.stackSize <= this.analyzerItemStacks[var3].getMaxStackSize())
                            {
                                this.analyzerItemStacks[var3].stackSize += var1.stackSize;
                                var1.stackSize = 0;
                                break;
                            }

                            var1.stackSize -= this.analyzerItemStacks[var3].getMaxStackSize() - this.analyzerItemStacks[var3].stackSize;
                            this.analyzerItemStacks[var3].stackSize = this.analyzerItemStacks[var3].getMaxStackSize();
                        }
                    }
                }

                if (var1.stackSize != 0 && this.analyzerItemStacks[this.SpaceIndex] == null)
                {
                    this.analyzerItemStacks[this.SpaceIndex] = var1.copy();
                }

                --this.analyzerItemStacks[this.RawIndex].stackSize;

                if (this.analyzerItemStacks[this.RawIndex].stackSize == 0)
                {
                    this.analyzerItemStacks[this.RawIndex] = null;
                }
            }
        }
    }
    
    private static int getItemBurnTime(ItemStack var1)
    {
        return 100;
    }

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 > 8 ? false : (par1 < 8 ? isItemFuel(par2ItemStack) : true);
    }

    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? field_102011_e : (par1 == 1 ? field_102010_d : field_102009_f);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isStackValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return false;
    }

    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }
    
	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
        return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
	}
	
}