package ere_geologique.common.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
	private int direction;
	private boolean active;

	private final int[] slots_top = new int[] {};
	private final int[] slots_bottom = new int[] {10, 11, 12};
	private final int[] slots_sides = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};

	public TileEntityAnalyzer()
	{
		analyzerItemStacks = new ItemStack[13];
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, nbttagcompound);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.data);
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
		if(this.analyzerItemStacks[var1] != null)
		{
			ItemStack var3;

			if(this.analyzerItemStacks[var1].stackSize <= var2)
			{
				var3 = this.analyzerItemStacks[var1];
				this.analyzerItemStacks[var1] = null;
				return var3;
			}
			else
			{
				var3 = this.analyzerItemStacks[var1].splitStack(var2);

				if(this.analyzerItemStacks[var1].stackSize == 0)
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

		if(var2 != null && var2.stackSize > this.getInventoryStackLimit())
		{
			var2.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInvName()
	{
		return "Analyzer";
	}

	public int getDirection()
	{
		return this.direction;
	}

	public void setDirection(int i)
	{
		this.direction = i;
	}

	public void setActive(boolean b)
	{
		this.active = b;
	}

	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		NBTTagList var2 = nbtTag.getTagList("Items", 0);
		this.analyzerItemStacks = new ItemStack[this.getSizeInventory()];

		for(int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound)var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte("Slot");

			if(var5 >= 0 && var5 < this.analyzerItemStacks.length)
			{
				this.analyzerItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

		this.analyzerBurnTime = nbtTag.getInteger("BurnTime");
		this.analyzerCookTime = nbtTag.getInteger("CookTime");
		this.direction = nbtTag.getInteger("direction");
		this.active = nbtTag.getBoolean("active");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		NBTTagList var2 = new NBTTagList();

		for(int var3 = 0; var3 < this.analyzerItemStacks.length; ++var3)
		{
			if(this.analyzerItemStacks[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				this.analyzerItemStacks[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		nbtTagCompound.setTag("Items", var2);
		
		nbtTagCompound.setInteger("BurnTime", (short)this.analyzerBurnTime);
		nbtTagCompound.setInteger("CookTime", (short)this.analyzerCookTime);
		nbtTagCompound.setInteger("direction", this.direction);
		nbtTagCompound.setBoolean("active", this.active);
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
		if(this.currentItemBurnTime == 0)
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

		if(this.analyzerBurnTime > 0)
		{
			--this.analyzerBurnTime;
		}

		if(!this.worldObj.isRemote)
		{
			if(this.analyzerBurnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.analyzerBurnTime = 100;

				if(this.analyzerBurnTime > 0)
				{
					var2 = true;
				}
			}

			if(this.isBurning() && this.canSmelt())
			{
				++this.analyzerCookTime;

				if(this.analyzerCookTime == 200)
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

			if(var1 != this.analyzerBurnTime > 0)
			{
				var2 = true;
				this.setActive(!this.isActive());
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if(var2)
		{
			this.onInventoryChanged();
		}
	}

	private boolean canSmelt()
	{
		this.SpaceIndex = -1;
		this.RawIndex = -1;
		int var1;

		for(var1 = 0; var1 < 9; ++var1)
		{
			if(this.analyzerItemStacks[var1] != null)
			{
				int var2 = this.analyzerItemStacks[var1].getItem().itemID;

				if(EnumDinoType.isDinoDrop(this.analyzerItemStacks[var1].getItem()) || (var2 == EGItemList.bioFossil.itemID) || /*( var2 == Fossil.rawDinoMeat . itemID ) ||*/(var2 == Items.porkchop.itemID) || (var2 == Items.beef.itemID) || (var2 == Items.egg.itemID) || (var2 == Items.chicken.itemID) || (var2 == Blocks.wool.blockID))
				{
					this.RawIndex = var1;
					break;
				}
			}
		}

		if(this.RawIndex == -1)
		{
			return false;
		}
		else
		{
			for(var1 = 12; var1 > 8; --var1)
			{
				if(this.analyzerItemStacks[var1] == null)
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
		if(this.canSmelt())
		{
			ItemStack itemStack = null;
			int var2 = (new Random()).nextInt(100);
			int var3;

			if(this.analyzerItemStacks[this.RawIndex].getItem() == EGItemList.bioFossil)
			{
				if(var2 < 1)
				{
					itemStack = new ItemStack(EGItemList.brokenSapling, 1);
				}
				if(var2 > 1 && var2 <= 45)
				{
					itemStack = new ItemStack(Items.dye, 3, 15);
				}
				if(var2 > 45 && var2 <= 85)
				{
					itemStack = new ItemStack(Blocks.sand, 3);
				}
				if(var2 > 85)
				{
					int i = (new Random()).nextInt(EnumDinoType.values().length + 1);// +1 for the sapling
					Item i0 = null;
					if(i == 0)
						i0 = EGItemList.brokenSapling;
					else
						i0 = EnumDinoType.values()[i - 1].dnaItem;
					itemStack = new ItemStack(i0, 1);
				}
			}

			if(EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem()) != null)
				itemStack = new ItemStack(EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem()), 1);
			if(this.analyzerItemStacks[this.RawIndex].getItem() == EGItemList.relic)
			{
				if(var2 <= 40)
					itemStack = new ItemStack(Blocks.gravel, 2);

				if(var2 > 40)
					itemStack = new ItemStack(Items.flint, 2);
			}
			if(itemStack != null)
			{
				if(itemStack.getItem().equals(Items.dye) || itemStack.getItem().equals(Items.flint) || itemStack.getItem().equals(Blocks.gravel) || itemStack.getItem().equals(EGItemList.relic) || itemStack.getItem().equals(EGItemList.brokenSapling) || itemStack.getItem().equals(Blocks.sand))
				{
					for(var3 = 12; var3 > 8; --var3)
					{
						if(this.analyzerItemStacks[var3] != null && itemStack.getItem().equals(this.analyzerItemStacks[var3]))
						{
							if(this.analyzerItemStacks[var3].stackSize + itemStack.stackSize <= this.analyzerItemStacks[var3].getMaxStackSize())
							{
								this.analyzerItemStacks[var3].stackSize += itemStack.stackSize;
								itemStack.stackSize = 0;
								break;
							}

							itemStack.stackSize -= this.analyzerItemStacks[var3].getMaxStackSize() - this.analyzerItemStacks[var3].stackSize;
							this.analyzerItemStacks[var3].stackSize = this.analyzerItemStacks[var3].getMaxStackSize();
						}
					}
				}

				if(itemStack.stackSize != 0 && this.analyzerItemStacks[this.SpaceIndex] == null)
				{
					this.analyzerItemStacks[this.SpaceIndex] = itemStack.copy();
				}

				--this.analyzerItemStacks[this.RawIndex].stackSize;

				if(this.analyzerItemStacks[this.RawIndex].stackSize == 0)
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
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 > 8 ? false : (par1 < 8 ? isItemFuel(par2ItemStack) : true);
	}

	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return null;
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
	}

	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.getItem().equals(Items.bucket);
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