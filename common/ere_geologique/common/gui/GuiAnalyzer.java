package ere_geologique.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.block.container.ContainerAnalyzer;
import ere_geologique.common.tileentity.TileEntityAnalyzer;

public class GuiAnalyzer extends GuiContainer
{
	private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/gui/Analyser.png");
    private TileEntityAnalyzer analyzerInventory;

    public GuiAnalyzer(InventoryPlayer var1, TileEntity var2)
    {
        super(new ContainerAnalyzer(var1, var2));
        this.analyzerInventory = (TileEntityAnalyzer)var2;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.field_146289_q.drawString(LocalizationStrings.ANALYZER_NAME, 19, 6, 4210752);
        this.field_146289_q.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    protected void func_146976_a(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        field_146297_k.getTextureManager().bindTexture(loc);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        int var7 = this.analyzerInventory.getCookProgressScaled(21);
        this.drawTexturedModalRect(var5 + 80, var6 + 22, 177, 18, var7 + 1, 9);
    }
}