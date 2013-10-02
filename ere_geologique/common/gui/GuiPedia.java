package ere_geologique.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
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
	

    
	int left;//counter for text added on the left side
	int right;//same for the right side
	int items;//counter for the minipics down
    public GuiPedia(/*InventoryPlayer var1*/)
    {
        super(new ContainerPedia());
        this.xSize = 244;
        this.ySize = 174;
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
    	this.fontRenderer.drawString(str0, 16+(left0? 0 : 81), 12*(line+1), 4210752);
    }
    public void PrintStringLR(String str0,boolean left0,int line,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, 16+(left0? 0 : 81), 12*(line+1), col);
    }
    
    /**
     * Add a String to the left or right side, starting with 0
     */
    public void AddStringLR(String str0,boolean left0)
    {
    	this.fontRenderer.drawString(str0, 16+(left0? 0 : 81), 12*((left0?this.left++:this.right++)+1), 4210752);
    }
    public void AddStringLR(String str0,boolean left0,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, 16+(left0? 0 : 81), 12*((left0?this.left++:this.right++)+1), col);
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

    	RenderItem r= new RenderItem();
    	ItemStack it=new ItemStack(it0,1);
    	Icon icon = it.getIconIndex();
 //   	this.mc.getTextureManager().bindTexture(new ResourceLocation("/gui/items.png"));
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.drawTexturedModelRectFromIcon(x0, y0, icon, i, i);
 //   	r.renderIcon(x0, y0, icon, i, i);
    	GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    /**
     * Places a half-sized item at the bottom of dinopedia
     */
    public void AddMiniItem(Item it0)
    {this.PrintItemXY(it0, 99+8*(items%8),100-8*(items/8),0);items++;}
    
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
//    	if(Fossil.ToPedia instanceof EntityPregnantCow)((EntityPregnantCow)Fossil.ToPedia).ShowPedia(this);
//    	if(Fossil.ToPedia instanceof EntityPregnantPig)((EntityPregnantPig)Fossil.ToPedia).ShowPedia(this);
//    	if(Fossil.ToPedia instanceof EntityPregnantSheep)((EntityPregnantSheep)Fossil.ToPedia).ShowPedia(this);
//    	if(Fossil.ToPedia instanceof EntityMammoth)((EntityMammoth)Fossil.ToPedia).ShowPedia(this);
//    	if(Fossil.ToPedia instanceof EntitySmilodon)((EntitySmilodon)Fossil.ToPedia).ShowPedia(this);
//        if(Fossil.ToPedia instanceof EntityDodo)((EntityDodo)Fossil.ToPedia).ShowPedia(this);
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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(loc);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    /*public void drawScreen(int var1, int var2, float var3)
    {
        super.drawScreen(var1, var2, var3);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }*/

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        //EntityDinosaur.pediaingDino = null;
        //Fossil.ToPedia=null;
        super.onGuiClosed();
    }
}