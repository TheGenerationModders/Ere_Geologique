package ere_geologique.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.block.container.ContainerPedia;
import ere_geologique.common.entity.DinoEgg;
import ere_geologique.common.entity.Dinosaure;

@SideOnly(Side.CLIENT)
public class GuiPedia extends GuiContainer
{	
	private static final ResourceLocation loc = new ResourceLocation("ere_geologique:textures/gui/Dinopedia.png");
	

//    public static final int rightLeftAlign = 140; //Left aligntment position for text on the RIGHT page of the pedia
//    public int leftLeftAlign = 140; //Left aligntment position for text on the LEFT page of the pedia
	int left;//counter for text added on the left side
	int right;//same for the right side
	int items;//counter for the minipics down
    public GuiPedia(/*InventoryPlayer var1*/)
    {
        super(new ContainerPedia());
        this.xSize = 256;
        this.ySize = 180;
        left=0;
        right=0;
        items=0;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
    }
    /**
     * Resets the y-offset for left and right side
     */
    public void reset()
    {
    	this.left=0;
    	this.right=0;
    	this.items=0;
    }
    /**
     * Print a String to left or Right, starting with line 0
     */
    public void PrintStringLR(String str0,boolean left0,int line)
    {
    	this.fontRenderer.drawString(str0, 59+(left0? 0 : 81), 12*(line+1), 4210752);
    }
    public void PrintStringLR(String str0,boolean left0,int line,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, 59+(left0? 0 : 81), 12*(line+1), col);
    }
    
    /**
     * Add a String to the left or right side, starting with 0
     */
    public void AddStringLR(String str0,boolean left0)
    {
    	this.fontRenderer.drawString(str0, 24+(left0? 0 : 81), 12*((left0?this.left++:this.right++)+1), 4210752);
    }
    public void AddStringLR(String str0,boolean left0,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, 24+(left0? 0 : 81), 12*((left0?this.left++:this.right++)+1), col);
    }
    /**
     * Print a String to X,Y
     */
    public void PrintStringXY(String str0,int x0,int y0)
    {
    	this.fontRenderer.drawString(str0, x0, y0, 4210752);
    }
    public void PrintStringXY(String str0,int x0,int y0,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, x0, y0, col);
    }
    
    /**
     * Print a Symbol at X,Y with zoom factor zoom: x*16 pixels. 0 means 8,-1 means 4
     */
    public void PrintItemXY(Item it0,int x0,int y0)
    {
    	this.PrintItemXY(it0, x0, y0,1);
    }
    public void PrintItemXY(Item it0,int x0,int y0, int zoom)
    {
    	TextureManager r0 = Minecraft.getMinecraft().renderEngine;
    	
    	int i=zoom*16;
    	if(i<0)i=4;
    	if(i==0)i=8;
    	if(i>160)i=160;
    	GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
    	RenderItem r= new RenderItem();
    	ItemStack it=new ItemStack(it0,1);
    	IIcon icon = it.getIconIndex();
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModelRectFromIcon(x0, y0, icon, i, i);
    	GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    /**
     * Places a half-sized item at the bottom of dinopedia
     */
    public void AddMiniItem(Item it0)
    {this.PrintItemXY(it0, 140+8*(items%8),130-8*(items/8),0);items++;}
    
    /**
     * Print a Picture at X,Y
     */
    public void PrintPictXY(ResourceLocation str0,int x0,int y0,int width,int height)
    {
    	this.mc.getTextureManager().bindTexture(str0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)x0				, (double)(y0 + height)	, 0D, 0D, 1D);
        var9.addVertexWithUV((double)(x0 + width)	, (double)(y0 + height)	, 0D, 1D, 1D);
        var9.addVertexWithUV((double)(x0 + width)	, (double)y0			, 0D, 1D, 0D);
        var9.addVertexWithUV((double)x0				, (double)y0			, 0D, 0D, 0D);
        var9.draw();
    }
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
    	if(EreGeologique.ToPedia instanceof Dinosaure)((Dinosaure)EreGeologique.ToPedia).ShowPedia(this);	
    	if(EreGeologique.ToPedia instanceof DinoEgg)((DinoEgg)EreGeologique.ToPedia).ShowPedia(this);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
    	int x = var2;
    	int y = var3;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(loc);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);

    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }
}