package ere_geologique.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import ere_geologique.api.food.DinoFood;
import ere_geologique.api.food.EnumFoodType;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class TileEntityFeeder extends TileEntity implements IInventory, ISidedInventory
{
	private ItemStack[] feederItemStacks = new ItemStack[2];
	public int MeatCurrent = 0;
	public int MeatMax = 10000;
	public int VegCurrent = 0;
	public int VegMax = 10000;
	private int direction;
	public boolean[] ContainType = new boolean[EnumDinoType.values().length];
	private static final int[] slot_herb = new int[] {1};
	private static final int[] slot_carn = new int[] {0};

	public TileEntityFeeder()
	{
		this.ClearTypeRecord();
	}

	public void ClearTypeRecord()
	{
		for(int var1 = 0; var1 < this.ContainType.length; ++var1)
		{
			this.ContainType[var1] = false;
		}
	}

	public int getSizeInventory()
	{
		return this.feederItemStacks.length;
	}

	public ItemStack getStackInSlot(int var1)
	{
		return this.feederItemStacks[var1];
	}

	public ItemStack decrStackSize(int var1, int var2)
	{
		if(this.feederItemStacks[var1] != null)
		{
			ItemStack var3;

			if(this.feederItemStacks[var1].stackSize <= var2)
			{
				var3 = this.feederItemStacks[var1];
				this.feederItemStacks[var1] = null;
				this.onInventoryChanged();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				return var3;
			}
			else
			{
				var3 = this.feederItemStacks[var1].splitStack(var2);

				if(this.feederItemStacks[var1].stackSize == 0)
				{
					this.feederItemStacks[var1] = null;
				}
				this.onInventoryChanged();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
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
		this.feederItemStacks[var1] = var2;

		if(var2 != null && var2.stackSize > this.getInventoryStackLimit())
		{
			var2.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	public String getInvName()
	{
		return "Feeder";
	}

	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

	public int getDirection()
	{
		return this.direction;
	}

	public void setDirection(int i)
	{
		this.direction = i;
	}

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		NBTTagList var2 = nbtTag.getTagList("Items", 10);
		this.feederItemStacks = new ItemStack[this.getSizeInventory()];

		for(int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound)var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte("Slot");

			if(var5 >= 0 && var5 < this.feederItemStacks.length)
			{
				this.feederItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

		this.MeatCurrent = nbtTag.getInteger("MeatCurrent");
		this.VegCurrent = nbtTag.getInteger("VegCurrent");
		this.direction = nbtTag.getInteger("Direction");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		NBTTagList var2 = new NBTTagList();

		for(int var3 = 0; var3 < this.feederItemStacks.length; ++var3)
		{
			if(this.feederItemStacks[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				this.feederItemStacks[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		nbtTag.setTag("Items", var2);
		nbtTag.setInteger("MeatCurrent", this.MeatCurrent);
		nbtTag.setInteger("VegCurrent", this.VegCurrent);
		nbtTag.setInteger("Direction", this.direction);
	}

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

	public void updateEntity()
	{
		boolean var1 = false;
		int var2 = ((this.MeatCurrent > 0) ? 2 : 0) + ((this.VegCurrent > 0) ? 1 : 0);

		if(!this.worldObj.isRemote)
		{
			int var3;

			if(this.feederItemStacks[0] != null && this.MeatCurrent < this.MeatMax && DinoFood.getGlobalFood(this.feederItemStacks[0].getItem(), this.feederItemStacks[0].getItemDamage()) != null && DinoFood.getGlobalFood(this.feederItemStacks[0].getItem(), this.feederItemStacks[0].getItemDamage()).getFoodType() == EnumFoodType.CARNIVOROUS)
			{
				int val = DinoFood.getGlobalFood(this.feederItemStacks[0].getItem(), this.feederItemStacks[0].getItemDamage()).getFoodValue();
				if(EnumDinoType.isDinoDrop(this.feederItemStacks[0].getItem()))
					this.ContainType[EnumDinoType.getIndex(this.feederItemStacks[0].getItem())] = true;
				if(val * this.feederItemStacks[0].stackSize + this.MeatCurrent < this.MeatMax)
				{
					this.MeatCurrent += val * this.feederItemStacks[0].stackSize;
					var1 = true;
					this.feederItemStacks[0] = null;
				}
				else
				{
					while(val + this.MeatCurrent < this.MeatMax && this.feederItemStacks[0] != null)
					{
						this.MeatCurrent += val;
						var1 = true;
						--this.feederItemStacks[0].stackSize;

						if(this.feederItemStacks[0].stackSize == 0)
						{
							this.feederItemStacks[0] = null;
						}
					}
				}
			}

			if(this.feederItemStacks[1] != null && this.VegCurrent < this.VegMax && DinoFood.getGlobalFood(this.feederItemStacks[1].getItem(), this.feederItemStacks[1].getItemDamage()) != null && (DinoFood.getGlobalFood(this.feederItemStacks[1].getItem(), this.feederItemStacks[1].getItemDamage()).getFoodType() == EnumFoodType.HERBIVOROUS || DinoFood.getGlobalFood(this.feederItemStacks[1].getItem(), this.feederItemStacks[1].getItemDamage()).getFoodValue() > 0))// herbivore
			// part
			{
				int val = DinoFood.getGlobalFood(this.feederItemStacks[1].getItem(), this.feederItemStacks[1].getItemDamage()).getFoodValue();
				if(val * this.feederItemStacks[1].stackSize + this.VegCurrent < this.VegMax)
				{
					this.VegCurrent += val * this.feederItemStacks[1].stackSize;
					var1 = true;
					this.feederItemStacks[1] = null;
				}
				else
				{
					while(val + this.VegCurrent < this.VegMax && this.feederItemStacks[1] != null)
					{
						this.VegCurrent += val;
						var1 = true;
						--this.feederItemStacks[1].stackSize;

						if(this.feederItemStacks[1].stackSize == 0)
						{
							this.feederItemStacks[1] = null;
						}
					}
				}
			}

			if(var1)
			{
				this.onInventoryChanged();
			}
		}
	}

	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest()
	{}

	public void closeChest()
	{}

	public boolean CheckIsEmpty(EnumDinoType t)
	{
		if(((!t.isHerbivore() || this.VegCurrent == 0) && (!t.isCarnivore() || this.MeatCurrent == 0)) || this.ContainType[t.ordinal()])
			return true;
		return false;
	}

	public int Feed(Dinosaure var1, EnumDinoType t)
	{
		int a = 0;
		int m = this.MeatCurrent;
		while(!this.CheckIsEmpty(t) && var1.increaseHunger(1))
		{
			if(t.isCarnivore() && this.MeatCurrent > 0)// if meatcurrent=0 it
														// eats veggie food and
														// the dino can eat and
														// there is food, see
														// checkisempty
				--this.MeatCurrent;
			else
				--this.VegCurrent;
			a++;
		}
		if(m > 0 && this.MeatCurrent == 0)// the carn. part is empty so it cant
											// contain raw dino meat
			this.ClearTypeRecord();
		return a;// amount fed to the dino
	}

	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return null;
	}

	public int getCurrentMeat()
	{
		return this.MeatCurrent;
	}

	public int getCurreentVeg()
	{
		return this.VegCurrent;
	}

	public int[] getAccessibleSlotsFromSide(int var1)
	{
		if(var1 == 4)
		{
			return slot_herb;
		}
		else if(var1 == 5)
		{
			return slot_carn;
		}
		return null;
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(i, itemstack);
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return false;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return null;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public void openInventory()
	{
		
	}

	@Override
	public void closeInventory()
	{
		
	}
}