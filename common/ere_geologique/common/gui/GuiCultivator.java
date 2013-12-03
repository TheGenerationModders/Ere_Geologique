package ere_geologique.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.block.container.ContainerCultivator;
import ere_geologique.common.tileentity.TileEntityCultivator;

public class GuiCultivator extends GuiContainer
{
	private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/gui/Cultivate.png");
    private TileEntityCultivator furnaceInventory;

    public GuiCultivator(InventoryPlayer var1, TileEntity var2)
    {
        super(new ContainerCultivator(var1, var2));
        this.furnaceInventory = (TileEntityCultivator)var2;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString(LocalizationStrings.CULTIVATE_IDLE_NAME, 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(loc);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        if (this.furnaceInventory.isBurning())
        {
            var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 82, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 18, 176, 14, var7 + 1, 16);
    }
}