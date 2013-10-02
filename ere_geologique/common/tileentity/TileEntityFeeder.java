package ere_geologique.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.block.Feeder;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.entity.Enums.EnumDinoFoodBlock;
import ere_geologique.common.entity.Enums.EnumDinoFoodItem;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class TileEntityFeeder extends TileEntity implements IInventory, ISidedInventory
{	
    private ItemStack[] feederItemStacks = new ItemStack[2];
    public int MeatCurrent = 0;
    public int MeatMax = 10000;
    public int VegCurrent = 0;
    public int VegMax = 10000;
    public boolean[] ContainType = new boolean[EnumDinoType.values().length];

    public TileEntityFeeder()
    {
        this.ClearTypeRecord();
    }

    public void ClearTypeRecord()
    {
        for (int var1 = 0; var1 < this.ContainType.length; ++var1)
        {
            this.ContainType[var1] = false;
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.feederItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.feederItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.feederItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.feederItemStacks[var1].stackSize <= var2)
            {
                var3 = this.feederItemStacks[var1];
                this.feederItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.feederItemStacks[var1].splitStack(var2);

                if (this.feederItemStacks[var1].stackSize == 0)
                {
                    this.feederItemStacks[var1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.feederItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Feeder";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.feederItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.feederItemStacks.length)
            {
                this.feederItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.MeatCurrent = var1.getInteger("MeatCurrent");
        this.VegCurrent = var1.getInteger("VegCurrent");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.feederItemStacks.length; ++var3)
        {
            if (this.feederItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.feederItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
        var1.setInteger("MeatCurrent", this.MeatCurrent);
        var1.setInteger("VegCurrent", this.VegCurrent);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getMeatBarScaled(int var1)
    {
        return this.MeatCurrent * var1 / this.MeatMax;
    }

    public int getVegBarScaled(int var1)
    {
        return this.VegCurrent * var1 / this.VegMax;
    }

    /*public boolean isFilled()
    {
        return this.MeatCurrent > 0 || this.VegCurrent > 0;
    }*/

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = false;
        int var2 = ((this.MeatCurrent > 0)?2:0) + ((this.VegCurrent > 0)?1:0);

        if (!this.worldObj.isRemote)
        {
            int var3;

            if (this.feederItemStacks[0] != null && this.MeatCurrent<this.MeatMax && EnumDinoFoodItem.foodtype(this.feederItemStacks[0].itemID)==EnumDinoFoodItem.ISCARNIVOROUS)//the carnivore part
            {//there is an item in, its carn. food and there is space
            	
            	int val=EnumDinoFoodItem.getItemFood(this.feederItemStacks[0].itemID);
            	
            	//if(this.feederItemStacks[0].getItem() instanceof fossil.items.ItemDinoMeat)
            	{// the feeder contains the raw food of the dino....he wont eat out of it anymore until it has been emptied!
            		/*if(this.feederItemStacks[0].getItem().itemID==Fossil.rawTriceratops.itemID)this.ContainType[EnumDinoType.Triceratops.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawVelociraptor.itemID)this.ContainType[EnumDinoType.Velociraptor.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawTRex.itemID)this.ContainType[EnumDinoType.TRex.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawStegosaurus.itemID)this.ContainType[EnumDinoType.Stegosaurus.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawPterosaur.itemID)this.ContainType[EnumDinoType.Pterosaur.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawPlesiosaur.itemID)this.ContainType[EnumDinoType.Plesiosaur.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawMosasaurus.itemID)this.ContainType[EnumDinoType.Mosasaurus.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawDilophosaurus.itemID)this.ContainType[EnumDinoType.Dilophosaurus.ordinal()]=true;
            		if(this.feederItemStacks[0].getItem().itemID==Fossil.rawBrachiosaurus.itemID)this.ContainType[EnumDinoType.Brachiosaurus.ordinal()]=true;
                    if(this.feederItemStacks[0].getItem().itemID==Fossil.rawSpinosaurus.itemID)this.ContainType[EnumDinoType.Spinosaurus.ordinal()]=true;*/
            		if(EnumDinoType.isDinoDrop(this.feederItemStacks[0].getItem()))
            			this.ContainType[EnumDinoType.getIndex(this.feederItemStacks[0].getItem())]=true;
            	}
            	if (val * this.feederItemStacks[0].stackSize + this.MeatCurrent < this.MeatMax)
                {//can take all of it
                    this.MeatCurrent += val * this.feederItemStacks[0].stackSize;
                    var1 = true;
                    this.feederItemStacks[0] = null;
                }
                else
                {
                    while (val + this.MeatCurrent < this.MeatMax && this.feederItemStacks[0] != null)
                    {
                        this.MeatCurrent += val;
                        var1 = true;
                        --this.feederItemStacks[0].stackSize;

                        if (this.feederItemStacks[0].stackSize == 0)
                        {
                            this.feederItemStacks[0] = null;
                        }
                    }
                }
            }

            if (this.feederItemStacks[1] != null && this.VegCurrent<this.VegMax && (EnumDinoFoodItem.foodtype(this.feederItemStacks[1].itemID)==EnumDinoFoodItem.ISHERBIVOROUS || EnumDinoFoodBlock.getBlockFood(this.feederItemStacks[1].itemID)>0))//herbivore part
            {
            	int val=EnumDinoFoodItem.getItemFood(this.feederItemStacks[1].itemID)+EnumDinoFoodBlock.getBlockFood(this.feederItemStacks[1].itemID);
                if (val * this.feederItemStacks[1].stackSize + this.VegCurrent < this.VegMax)
                {
                    this.VegCurrent += val * this.feederItemStacks[1].stackSize;
                    var1 = true;
                    this.feederItemStacks[1] = null;
                }
                else
                {
                    while (val + this.VegCurrent < this.VegMax && this.feederItemStacks[1] != null)
                    {
                        this.VegCurrent += val;
                        var1 = true;
                        --this.feederItemStacks[1].stackSize;

                        if (this.feederItemStacks[1].stackSize == 0)
                        {
                            this.feederItemStacks[1] = null;
                        }
                    }
                }
            }

            if (var2 != (((this.MeatCurrent > 0)?2:0) + ((this.VegCurrent > 0)?1:0)))
            {
            	Feeder.updateFurnaceBlockState(this.VegCurrent > 0, this.MeatCurrent > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                //BlockFeeder.updateFurnaceBlockState(var4, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }

            if (var1)
            {
                this.onInventoryChanged();
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}
    
    /**
     * takes a dino type and returns true, if the feeder is !!!EMPTY!!! for that Dino!
     * Returns false if the dino is a carnivore and its own meat is in the feeder
     */
    public boolean CheckIsEmpty(EnumDinoType t)
    {
        if (((!t.isHerbivore() || this.VegCurrent==0) && (!t.isCarnivore() || this.MeatCurrent==0)) || this.ContainType[t.ordinal()])
            return true;//the dino wont eat from the feeder if it can smell its own flesh (filled with raw flesh)
        return false;
    }

    public int Feed(Dinosaure var1, EnumDinoType t)
    {
    	int a=0;
    	int m=this.MeatCurrent;
        while (!this.CheckIsEmpty(t) && var1.increaseHunger(1))
        {
            if (t.isCarnivore() && this.MeatCurrent>0)//if meatcurrent=0 it eats veggie food and the dino can eat and there is food, see checkisempty
            	--this.MeatCurrent;
            else
                --this.VegCurrent;
            a++;
        }
        if(m>0 && this.MeatCurrent==0)//the carn. part is empty so it cant contain raw dino meat
        	this.ClearTypeRecord();
        Feeder.updateFurnaceBlockState(this.VegCurrent>0, this.MeatCurrent>0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        return a;//amount fed to the dino
    }

    /*@Deprecated
    public boolean CheckIsEmpty(EnumDinoEating var1)
    {
        if (var1 == EnumDinoEating.Herbivorous)
        {
            return this.VegCurrent == 0;
        }
        else if (this.MeatCurrent == 0)
        {
            this.ClearTypeRecord();
            return true;
        }
        else
        {
            return false;
        }
    }

    @Deprecated
    public void Feed(EntityDinosaur var1, EnumDinoEating var2)
    {
        while (var1.increaseHunger(1) && !this.CheckIsEmpty(var2))
        {
            if (var2 == EnumDinoEating.Herbivorous)
            {
                --this.VegCurrent;
            }
            else
            {
                --this.MeatCurrent;
            }
        }
    }*/

    /*public boolean GetIfEatingSameBreed(EnumDinoType var1)
    {//Seems to be completely senseless to me, will return true for all dinos
        EnumDinoType[] var2 = EnumDinoType.values();

        for (int var3 = 0; var3 < var2.length; ++var3)
        {
            if (var1.equals(var2[var3]))
            {
                return true;
            }
        }

        return false;
    }*/

    public int getSizeInventorySide(ForgeDirection var1)
    {
        return 1;
    }

    public int getStartInventorySide(ForgeDirection var1)
    {
        return var1 == ForgeDirection.DOWN ? 1 : (var1 == ForgeDirection.UP ? 0 : 2);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public int getCurrentMeat()
    {
        return this.MeatCurrent;
    }

    @SideOnly(Side.CLIENT)
    public int getCurreentVeg()
    {
        return this.VegCurrent;
    }

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}