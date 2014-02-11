package ere_geologique.common.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPage extends GuiButton
{
    private final boolean nextPage;
    private int page;
    private int lastpage = 1;
    
    public GuiPage(int par1, int par2, int par3, boolean par4, int bookpage)
    {
        super(par1, par2, par3, 23, 13, "");
        this.nextPage = par4;
        page = bookpage;
    }

    public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.visible)
        {
            boolean var4 = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture(new ResourceLocation("ere_geologique:textures/gui/Arch_Notebook.png"));
            int var5 = 4;
            int var6 = 176;

            if ((var4)||(this.nextPage&&page==lastpage)||(!this.nextPage&&page==0))
            {
                var6 += 15;
            }

            if (!this.nextPage)
            {
                var5 += 30;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, var5, var6, 23, 13);
        }
    }
}