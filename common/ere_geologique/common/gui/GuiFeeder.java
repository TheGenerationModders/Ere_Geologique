package ere_geologique.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.block.container.ContainerFeeder;
import ere_geologique.common.tileentity.TileEntityFeeder;

@SideOnly(Side.CLIENT)
public class GuiFeeder extends GuiContainer
{
	private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/gui/Feeder.png");
    private TileEntityFeeder FeederInventory;

    public GuiFeeder(InventoryPlayer var1, TileEntity var2)
    {
        super(new ContainerFeeder(var1, var2));
        this.FeederInventory = (TileEntityFeeder)var2;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.field_146289_q.drawString(StatCollector.translateToLocal("container." + LocalizationStrings.FEEDER_NAME), 8, 6, 4210752);
        this.field_146289_q.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        this.func_146276_q_();
        int var4 = this.field_147003_i;
        int var5 = this.field_147009_r;
        this.func_146976_a(var3, var1, var2);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        super.drawScreen(var1, var2, var3);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var4, (float)var5, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Object var6 = null;
        short var7 = 240;
        short var8 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var7 / 1.0F, (float)var8 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawGuiContainerForegroundLayer();
        InventoryPlayer var9 = this.field_146297_k.thePlayer.inventory;

        if (var9.getItemStack() != null)
        {
            GL11.glTranslatef(0.0F, 0.0F, 32.0F);
            this.zLevel = 200.0F;
            field_146296_j.zLevel = 200.0F;
            field_146296_j.renderItemAndEffectIntoGUI(this.field_146289_q, this.field_146297_k.renderEngine, var9.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
            field_146296_j.renderItemOverlayIntoGUI(this.field_146289_q, this.field_146297_k.renderEngine, var9.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
            this.zLevel = 0.0F;
            field_146296_j.zLevel = 0.0F;
        }

        this.field_146289_q.drawString("" + this.FeederInventory.getCurrentMeat(), 23, 32, 16711680);
        this.field_146289_q.drawString("" + this.FeederInventory.getCurreentVeg(), 120, 32, 243459);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void func_146976_a(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        field_146297_k.getTextureManager().bindTexture(loc);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        int var7 = this.FeederInventory.getMeatBarScaled(46);
        this.drawTexturedModalRect(var5 + 66, var6 + 55 - var7, 176, 46 - var7, 3, var7);
        int var8 = this.FeederInventory.getVegBarScaled(46);
        this.drawTexturedModalRect(var5 + 110, var6 + 55 - var8, 176, 46 - var8, 3, var8);
    }
}