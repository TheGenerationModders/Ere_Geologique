package ere_geologique.common.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class EGGuiButton extends GuiButton
{

	private int IconIndexHeight;
	private int IconIndexWidth;

    public EGGuiButton(int par1, int par2, int par3, int par4)
    {
        super(par1, par2, par3, 32, 32, "");

        for(;par4>7;)
        {
        	par4-=8;
        	IconIndexHeight++;
        }
        IconIndexWidth = par4;
    }

    public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.field_146125_m)
        {
            boolean var4 = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture(new ResourceLocation("ere_geologique:textures/items/Whip0.png"));
            int var6 = IconIndexHeight*32;
            int var5 = IconIndexWidth*32;
            if(var4) var5+=32;       
            this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, var5, var6, 32, 32);
        }
    }
}