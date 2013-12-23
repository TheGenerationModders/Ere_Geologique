package ere_geologique.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import ere_geologique.common.tileentity.TileEntityAnalyzer;

public class ContainerAnalyzer extends Container
{
    private TileEntityAnalyzer analyzer;
    private int cookTime = 0;
    private int burnTime = 0;
    private int itemBurnTime = 0;

    public ContainerAnalyzer(InventoryPlayer var1, TileEntity var2)
    {
        this.analyzer = (TileEntityAnalyzer)var2;
        int var3;
        int var4;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 3; ++var4)
            {
                this.addSlotToContainer(new Slot(this.analyzer, var4 + var3 * 3, 20 + var4 * 18, 17 + var3 * 18));
            }
        }

        this.addSlotToContainer(new SlotFurnace(var1.player, this.analyzer, 9, 116, 21));

        for (var3 = 0; var3 < 3; ++var3)
        {
            this.addSlotToContainer(new SlotFurnace(var1.player, this.analyzer, 10 + var3, 111 + 18 * var3, 53));
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting var1)
    {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, this.analyzer.analyzerCookTime);
        var1.sendProgressBarUpdate(this, 1, this.analyzer.analyzerBurnTime);
        var1.sendProgressBarUpdate(this, 2, this.analyzer.currentItemBurnTime);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.cookTime != this.analyzer.analyzerCookTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.analyzer.analyzerCookTime);
            }

            if (this.burnTime != this.analyzer.analyzerBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.analyzer.analyzerBurnTime);
            }

            if (this.itemBurnTime != this.analyzer.currentItemBurnTime)
            {
                var2.sendProgressBarUpdate(this, 2, this.analyzer.currentItemBurnTime);
            }
        }

        this.cookTime = this.analyzer.analyzerCookTime;
        this.burnTime = this.analyzer.analyzerBurnTime;
        this.itemBurnTime = this.analyzer.currentItemBurnTime;
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.analyzer.analyzerCookTime = var2;
        }

        if (var1 == 1)
        {
            this.analyzer.analyzerBurnTime = var2;
        }

        if (var1 == 2)
        {
            this.analyzer.currentItemBurnTime = var2;
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.analyzer.isUseableByPlayer(var1);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 9 || (par2 > 9 && par2 < 13))
            {
            	if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}