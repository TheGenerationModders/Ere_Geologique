package ere_geologique.common.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.Enums.EnumOrderType;
import ere_geologique.common.entity.Enums.EnumSituation;
import ere_geologique.common.entity.IA.DinoAIGrowup;
import ere_geologique.common.entity.IA.DinoAIStarvation;
import ere_geologique.common.gui.GuiPedia;
import ere_geologique.common.item.EGItemList;
import ere_geologique.common.tileentity.TileEntityFeeder;

public abstract class Dinosaure extends EntityTameable implements IEntityAdditionalSpawnData
{
	//public static final int OWNER_NAME_DATA_INDEX = 17;
    public static final int HUNGER_TICK_DATA_INDEX = 18;
    public static final int HUNGER_DATA_INDEX = 19;
    public static final int AGE_TICK_DATA_INDEX = 20;
    public static final int AGE_DATA_INDEX = 21;
    public static final int SUBSPECIES_INDEX = 22;
    public static final int MODELIZED_INDEX = 23;
//    public static final int HEALTH_INDEX = 24;
    
    public static final byte HEART_MESSAGE = 35;
    public static final byte SMOKE_MESSAGE = 36;
    public static final byte AGING_MESSAGE = 37;
    
    public float RiderStrafe= 0.0F;
    public float RiderForward=0.0F;
    public boolean RiderJump=false;
    public boolean RiderSneak=false;
    
    public float minSize;
    public float maxSize;
    public int adultAge;
    
    public EnumDinoType SelfType = null;
    
    //Breed Tick at the moment, 0=breed, BreedingTime=timer just started
    public int BreedTick;
    
    //Variable for the thing the dino can hold in it's mouth
    public ItemStack ItemInMouth = null;
    
    //public static EntityDinosaur pediaingDino = null;
    public EnumOrderType OrderStatus;
	private EntityAIControlledByPlayer aiControlledByPlayer;


    
    private static final ResourceLocation pediaclock = new ResourceLocation("ere_geologique:textures/gui/PediaClock.png");
    private static final ResourceLocation pediafood = new ResourceLocation("ere_geologique:textures/gui/PediaFood.png");
    private static final ResourceLocation pediaheart = new ResourceLocation("ere_geologique:textures/gui/PediaHeart.png");
    


    
	// EntityDinosaur Constructor
    public Dinosaure(World var1,EnumDinoType T0)
    {
        super(var1);
        this.SelfType=T0;
        this.OrderStatus = EnumOrderType.FreeMove;
        this.tasks.addTask(0, new DinoAIGrowup(this));
        this.tasks.addTask(0, new DinoAIStarvation(this));
        this.BreedTick = this.SelfType.BreedingTicks;
        this.setHunger(this.SelfType.MaxHunger/2);
        this.setHealth( this.SelfType.Health0 );
     //   this.setSize(this.FuckinAy,this.FuckinAy);
        // MC Entity calls setPosition which expects the bounding box to be set,
        // so unfortunately since we're storing our width/length in getDinoWidth (etc),
        // we have to set this again since now SelfType is set to something.
     //   this.setBoundingBox();
    }
    
    /**
     * Interpolate the size of the dinosaur based on the day 0 value and the max adult value.
     * If the dinosaur is past Adult age, continue to grow but have diminished growth.
     */
    public float getDinosaurSize()
    {
    	float step;

    	
		step = (this.maxSize - this.minSize)/(this.adultAge+1);

		
    	// If the dinosaur is past "Adult" age, slow down growth.
    	if(this.getDinoAge() > this.adultAge)
    	{
        return this.minSize + (step*this.adultAge);
    	}

        return this.minSize + (step*this.getDinoAge());
    }
    /**
     * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
     */
    public void setScaleForAge(boolean par1)
    {
            this.setScale(this.getDinosaurSize());
    }
	
    /*
    public void setPosition(double par1, double par3, double par5)
    {
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        float w_2 = this.width / 2.0F * this.HitboxZfactor;
		float l_2 = this.width / 2.0F * this.HitboxXfactor;
        this.boundingBox.setBounds(this.posX - (double)w_2, this.posY - (double)this.yOffset + (double)this.ySize, this.posZ - (double)l_2, this.posX + (double)w_2, this.posY - (double)this.yOffset + (double)this.ySize + (double)this.height*this.HitboxYfactor, this.posZ + (double)l_2);      
    
     }
    
    protected void setBoundingBox()
    {
        float w_2 = this.width / 2.0F * this.HitboxZfactor;
      float l_2 = this.width / 2.0F * this.HitboxXfactor;
        this.boundingBox.setBounds(this.posX - (double)w_2, this.posY - (double)this.yOffset + (double)this.ySize, this.posZ - (double)l_2, this.posX + (double)w_2, this.posY - (double)this.yOffset + (double)this.ySize + (double)this.height*this.HitboxYfactor, this.posZ + (double)l_2);      
    }
    */
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
        return MathHelper.floor_float(this.SelfType.Exp0+(float)this.getDinoAge()*this.SelfType.ExpInc);
    }
	
    public void updateSize()
    {
  
    	/*
    this.Step = ((this.SizeMax - this.SizeMin)/(this.SelfType.AdultAge + 1));
    this.FuckinAy = this.SizeMin + (this.Step * this.getDinoAge());
	
    	setSize(this.FuckinAy, this.FuckinAy);
*/
    	
    	//setSize(this.getDinoWidth(),this.getDinoHeight());
 //   	setPosition(this.posX, this.posY, this.posZ);
    	//this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.getHealth() + this.getDinoAge()*this.SelfType.HealthInc);
    }
	
    /*
    public void InitSize()//Necessary to overload existing
    {
	this.updateSize();
	}
	*/
	
    /*
    public float getDinoWidth()
    {
    	if( this.SelfType != null )
    	{
    		return this.SelfType.Width0 + this.SelfType.WidthInc * (float)this.getDinoAge();
    	}
    	else
    	{
    		return 1.0F;
    	}
    }
	
    public float getDinoHeight()
    {
    	if( this.SelfType != null )
    	{
    		return this.SelfType.Height0 + this.SelfType.HeightInc * (float)this.getDinoAge();
    	}
    	else
    	{
    		return 1.0F;
    	}
    }
	
    public float getDinoLength()
    {
    	if( this.SelfType != null )
    	{
    		return this.SelfType.Length0 + this.SelfType.LengthInc * (float)this.getDinoAge();
    	}
    	else
    	{
    		return 1.0F;
    	}
    }
        */
    private void setPedia()
    {
	EreGeologique.ToPedia = (Object)this;
	}

    /**
     * Tells if the Dino is a Adult
     */
    public boolean isAdult()
    {
	return this.getDinoAge() >= this.SelfType.AdultAge;
	}
	
    /**
     * Tells if the Dino is a Teen
     */
    public boolean isTeen()
    {
	return this.getDinoAge() >= this.SelfType.TeenAge;
	}
        	
    /**
     * Returns the MaxHunger of the Dino
     */
    public int getMaxHunger()
    {
    	return this.SelfType.MaxHunger;
    }

    public boolean isModelized()
    {
        return this.dataWatcher.getWatchableObjectByte(MODELIZED_INDEX) >= 0;
    }

    public void setModelized(boolean var1)
    {
        if (this.SelfType.isModelable())
        {
            this.dataWatcher.updateObject(MODELIZED_INDEX, Byte.valueOf((byte)(var1 ? 0 : -1)));

//            if (var1)
//                this.getTexture = this.getModelTexture();
        }
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(AGE_DATA_INDEX, new Integer(0));
        this.dataWatcher.addObject(AGE_TICK_DATA_INDEX, new Integer(0));
        this.dataWatcher.addObject(HUNGER_DATA_INDEX, new Integer(30));
        this.dataWatcher.addObject(HUNGER_TICK_DATA_INDEX, new Integer(300));
        this.dataWatcher.addObject(SUBSPECIES_INDEX, new Integer(1));
        this.dataWatcher.addObject(MODELIZED_INDEX, new Byte((byte) - 1));
    }

    public int getSubSpecies()
    {
    	return this.dataWatcher.getWatchableObjectInt(SUBSPECIES_INDEX);
    }

    public void setSubSpecies(int var1)
    {
    	this.dataWatcher.updateObject(SUBSPECIES_INDEX, Integer.valueOf(var1));
    }
    
    public int getDinoAge()
    {
    	return this.dataWatcher.getWatchableObjectInt(AGE_DATA_INDEX);
    }

    public void setDinoAge(int var1)
    {
    	this.dataWatcher.updateObject(AGE_DATA_INDEX, Integer.valueOf(var1));
    }

    /**
     * Tries to increase the dino age, returns if successful
     */
    public boolean increaseDinoAge()
    {
    	if(this.getDinoAge()<this.SelfType.MaxAge)
    	{
    		this.setDinoAge(this.getDinoAge() + 1);
    		return true;
    	}
    	return false;
    }
    
    public int getDinoAgeTick()
    {return this.dataWatcher.getWatchableObjectInt(AGE_TICK_DATA_INDEX);}

    public void setDinoAgeTick(int var1)
    {this.dataWatcher.updateObject(AGE_TICK_DATA_INDEX, Integer.valueOf(var1));}
    
    public void increaseDinoAgeTick()
    {this.setDinoAgeTick(this.getDinoAgeTick() + 1);}
    

    public int getHunger()
    {return this.dataWatcher.getWatchableObjectInt(HUNGER_DATA_INDEX);}
    
    public void setHunger(int var1)
    {this.dataWatcher.updateObject(HUNGER_DATA_INDEX, Integer.valueOf(var1));}
    
    public boolean increaseHunger(int var1)
    {
    	if(this.getHunger()>=this.getMaxHunger())
    		return false;
    	this.setHunger(this.getHunger() + var1);
    	if(this.getHunger()>this.getMaxHunger())
    		this.setHunger(this.getMaxHunger());
    	return true;
    }
    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving var1)
    {
        super.onKillEntity(var1);
        this.increaseHunger(this.SelfType.FoodMobList.getMobFood(var1.getClass()));
        if(CommandDino.Heal_Dinos)
        	this.heal(this.SelfType.FoodMobList.getMobHeal(var1.getClass()));
    }
    
    public void decreaseHunger()
    {
        if (this.getHunger() > 0)
        	this.setHunger(this.getHunger() - 1);
    }
    
    public boolean IsHungry()
    {return this.getHunger()<this.getMaxHunger()*this.SelfType.HungryLevel;}
    
    public boolean IsDeadlyHungry()
    {return this.getHunger()<this.getMaxHunger()*(1-this.SelfType.HungryLevel);}

    
    public int getHungerTick()
    { return this.dataWatcher.getWatchableObjectInt(HUNGER_TICK_DATA_INDEX);}

    public void setHungerTick(int var1)
    {this.dataWatcher.updateObject(HUNGER_TICK_DATA_INDEX, Integer.valueOf(var1));}

    public void decreaseHungerTick()
    {
        if (this.getHungerTick() > 0)
            this.setHungerTick(this.getHungerTick() - 1);
    }
 
    /**
     * Placeholder, returns the attack strength, should be customized for every Dino
     */
    public int getAttackStrength()
    {
    	return this.SelfType.Strength0+this.getDinoAge()*this.SelfType.StrengthInc;
    }
    
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
    	//when modelized just drop the model else handle normal attacking
        return this.modelizedDrop() ? true : super.attackEntityFrom(var1, var2);
    }

    protected String getModelTexture()
    {
        return "ere_geologique:textures/entity/DinoModel" + this.SelfType.toString() + ".png";
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        return this.isModelized() ? this.getModelTexture() : "ere_geologique:textures/entity/DinoModel" + this.SelfType.toString() + ".png";
    }

    public void moveEntityWithHeading(float par1, float par2)
    {
    	if(!isModelized())
    		super.moveEntityWithHeading(par1, par2);
    	else
    	{
            this.motionX *= 0.0D;
            this.motionZ *= 0.0D;
    	}
    	
    	if (this.riddenByEntity != null || this.isAdult())
    	{
    		this.stepHeight = 1.0F;
    	}
    }
    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 360;
    }
    
    @SideOnly(Side.CLIENT)
    public void ShowPedia(GuiPedia p0)
    {		
    	p0.reset();
    	p0.PrintPictXY(new ResourceLocation("ere_geologique:textures/items/"+ this.SelfType.toString() +"_DNA.png"), 185, 7, 16, 16);
    	
    	if (this.hasCustomNameTag())
    	p0.PrintStringXY(this.getCustomNameTag(), 140, 24,40,90,245);
    	
    	
    	p0.PrintStringXY(StatCollector.translateToLocal("Dino."+this.SelfType.toString()), 140, 34,0,0,0);
    	p0.PrintPictXY(pediaclock, 140, 46,8,8);
    	p0.PrintPictXY(pediaheart, 140, 58,9,9);
    	p0.PrintPictXY(pediafood, 140, 70,9,9);
    	
    	//Print "Day" after age
    	if(this.getDinoAge()==1)
    		p0.PrintStringXY(String.valueOf(this.getDinoAge()) +" "+ StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_DAY), 152, 46);
    	else
    		p0.PrintStringXY(String.valueOf(this.getDinoAge()) +" "+ StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_DAYS), 152, 46);
 
    	//Display Health
    	p0.PrintStringXY(String.valueOf(this.getHealth()) + '/' + this.getMaxHealth(), 152, 58);
    	
    	//Display Hunger
    	p0.PrintStringXY(String.valueOf(this.getHunger()) + '/' + this.getMaxHunger(), 152, 70);
 
    	//Display owner name
    	if(this.SelfType.isTameable() && this.isTamed())
    	{
    		p0.AddStringLR( StatCollector.translateToLocal(LocalizationStrings.PEDIA_TEXT_OWNER), true);
    		String s0=this.getOwnerName();
    		if(s0.length()>11)
    			s0=this.getOwnerName().substring(0, 11);
    		p0.AddStringLR(s0, true);
    	}
    	
    	//Display if Rideable
    	if(this.SelfType.isRideable() && this.isAdult())
    		p0.AddStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_TEXT_RIDEABLE), true);

    	
    	p0.AddStringLR(StatCollector.translateToLocal("Order: " + this.SelfType.OrderItem.getStatName()), true);
    	
    	for(int i=0; i<this.SelfType.FoodItemList.index;i++)
    	{
    		if(this.SelfType.FoodItemList.getItem(i)!=null)
    			p0.AddMiniItem(this.SelfType.FoodItemList.getItem(i));
    	}
    	
    	
    	//show all blocks the dino can eat
    }
    
    /**
     * retrieves the itemstack it can eat and returns the number of items not used
     */
    public int Eat(ItemStack item0)
    {
    	int i=item0.stackSize;
    	//it looks like the blocks are missing here...cant be eaten
    	if(this.IsHungry() && this.SelfType.FoodItemList.CheckItemById(item0.itemID))
    	{	//The Dino is Hungry and it can eat the item
    		//this.showHeartsOrSmokeFX(false);
    		this.worldObj.setEntityState(this, SMOKE_MESSAGE);
    		while(i > 0 && this.getHunger() < this.getMaxHunger())
    		{
    			this.setHunger(this.getHunger()+ this.SelfType.FoodItemList.getItemFood(item0.itemID));
    			if(CommandDino.Heal_Dinos && !this.worldObj.isRemote)//!this.worldObj.isRemote)
    				this.heal(this.SelfType.FoodItemList.getItemHeal(item0.itemID));
    			i--;
    		}	
    		if(this.getHunger() > this.getMaxHunger())
    		{
    			if(this.isTamed())
    				this.SendStatusMessage(EnumSituation.Full);
    			this.setHunger(this.getMaxHunger());
    		}
    	}
    	return i;
    }
    
    /**
     * The dino grabs an item from this stack with its monstrous teeth
     */
    public void HoldItem(ItemStack var1)
    {this.ItemInMouth = new ItemStack(var1.getItem(), 1, var1.getItemDamage());}
    
    /**
     * Handles an Itemstack the dinos gets his fangs on
     */
    public int PickUpItem(ItemStack var1)
    {
    	int i=Eat(var1);
    	if(i>0 && (this.SelfType.canCarryItems() || this.SelfType.FoodItemList.CheckItemById(var1.getItem().itemID)) && this.ItemInMouth == null)
    	{//if there are items left after trying to eat and he has nothing atm and the dino is able to carry things or its his food he takes it in the mouth
    		this.HoldItem(var1);
    		i--;
    	}
        return i;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return !this.isModelized();
    }
    
    /**
     * Tells if the dino is sitting
     */
    public boolean isSitting()
    {
    	return this.OrderStatus == EnumOrderType.Stay;
    }
    
    /**
     * Disables a mob's ability to move on its own while true.
     */
    protected boolean isMovementCeased()
    {
    	return this.OrderStatus == EnumOrderType.Stay;
    }
    
    public boolean attackEntityAsMob(Entity var1)
    {
        int var2 = this.getAttackStrength();

        if (this.isPotionActive(Potion.damageBoost))
        {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return var1.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
    }
    
    public Vec3 getBlockToEat(int SEARCH_RANGE)
    {
    	Vec3 pos = null;

    	for(int r=1;r<=SEARCH_RANGE;r++)
    	{
	    	for (int ds = -r; ds <=r; ds++)
	        {
	            for (int dy = 4; dy > -5; dy--)
	            {
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ-r))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ-r));
                    	return pos;
                    }
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+r))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+r));
                    	return pos;
                    }
	            }
	        }
	    	for (int ds = -r+1; ds <=r-1; ds++)
	        {
	            for (int dy = 4; dy > -5; dy--)
	            {
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX-r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX-r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds));
                    	return pos;
                    }
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds));
                    	return pos;
                    }
	            }
	        }
    	}
    	return null;
    }

    public TileEntityFeeder GetNearestFeeder(int SEARCH_RANGE)
    {
    	for (int dx = -2; dx != -(SEARCH_RANGE+1); dx+=(dx<0)?(dx*-2):(-(2*dx+1)))//Creates the following order: -2,2,-3,3,-4,1,....,10, stops with 10. looks at near places, first
        {
            for (int dy = -5; dy < 4; dy++)
            {
                for (int dz = -2; dz != -(SEARCH_RANGE+1); dz+=(dz<0)?(dz*-2):(-(2*dz+1)))//Creates the following order: -2,2,-3,3,-4,1,....,10, stops with 10. looks at near places, first
                {
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight())
                    {
                    	TileEntity fed = this.worldObj.getBlockTileEntity(MathHelper.floor_double(this.posX+dx), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+dz));
                        if (fed != null && fed instanceof TileEntityFeeder && !((TileEntityFeeder)fed).CheckIsEmpty(this.SelfType))
                        	return (TileEntityFeeder)fed;
                    }
                }
            }
        }
    	return null;
    }
    
    public float HandleRiding(float Speed, float SpeedBoosted)
    {
    	//EntityPlayer P = (EntityPlayer)this.riddenByEntity;
    	if(this.RiderForward>0)
    		Speed += (2.0F - Speed) * 0.1F*this.RiderForward;
    	else
    		if(Speed>0)
    		{
    			Speed += (2.0F - Speed) * 0.4F*this.RiderForward;//Break faster
    			if(Speed<0)Speed=0;
    		}
    		//else//No more Backward movement when ridden!
    		//	Speed += (this.getSpeed()*2.0F - Speed) * 0.06F*this.RiderForward;
    	
    	//System.out.println(String.valueOf("Forward:"+this.RiderForward));
    	/*if(this.riddenByEntity instanceof EntityPlayerMP)
    		System.out.println("Is MP "+String.valueOf(this.worldObj.isRemote));
    	else
    	{
    		if(this.riddenByEntity instanceof EntityPlayerSP)
        		System.out.println("Is SP "+String.valueOf(this.worldObj.isRemote));
    		{
    			if(this.riddenByEntity instanceof EntityClientPlayerMP)
    				System.out.println("Is Client MP "+String.valueOf(this.worldObj.isRemote));
    		}
    	}*/
        /*float var3 = MathHelper.wrapAngleTo180_float(P.rotationYaw - this.rotationYaw) * 0.5F;

        if (var3 > 5.0F)
        {
            var3 = 5.0F;
        }

        if (var3 < -5.0F)
        {
            var3 = -5.0F;
        }

        this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw + var3);*/
    	this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.RiderStrafe*5.0F);
        

        /*int var4 = MathHelper.floor_double(this.posX);
        int var5 = MathHelper.floor_double(this.posY);
        int var6 = MathHelper.floor_double(this.posZ);
        

        float var8 = 0.91F;

        if (this.onGround)
        {
            var8 = 0.54600006F;
            int var9 = this.worldObj.getBlockId(MathHelper.floor_float((float)var4), MathHelper.floor_float((float)var5) - 1, MathHelper.floor_float((float)var6));

            if (var9 > 0)
            {
                var8 = Block.blocksList[var9].slipperiness * 0.91F;
            }
        }*/

        //float var20 = 0.16277136F / (var8 * var8 * var8);
        //float var10 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
        //float var11 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
        //float var12 = /*this.getAIMoveSpeed()*this.getSpeed() * var20;
        //float var13 = Math.max(Speed, 1.0F);
        //var13 = var12 / var13;
        //float var14 = Speed;// * var12/Speed; //Speed * var13;
        //float var15 = -(var14 * var10);
        //float var16 = var14 * var11;
        //float var15 = -(Speed * var10);
        //float var16 = Speed * var11;

        /*if (MathHelper.abs(var15) > MathHelper.abs(var16))
        {
            if (var15 < 0.0F)
            {
                var15 -= this.width / 2.0F;
            }

            if (var15 > 0.0F)
            {
                var15 += this.width / 2.0F;
            }

            var16 = 0.0F;
        }
        else
        {
            var15 = 0.0F;

            if (var16 < 0.0F)
            {
                var16 -= this.width / 2.0F;
            }

            if (var16 > 0.0F)
            {
                var16 += this.width / 2.0F;
            }
        }*/

        /*int var17 = MathHelper.floor_double(this.posX + (double)var15);
        int var18 = MathHelper.floor_double(this.posZ + (double)var16);
        PathPoint var19 = new PathPoint(MathHelper.floor_float(this.width + 1.0F), MathHelper.floor_float(this.height + P.height + 1.0F), MathHelper.floor_float(this.width + 1.0F));

        if ((var4 != var17 || var6 != var18) && PathFinder.func_82565_a(this, var17, var5, var18, var19, false, false, true) == 0 && PathFinder.func_82565_a(this, var4, var5 + 1, var6, var19, false, false, true) == 1 && PathFinder.func_82565_a(this, var17, var5 + 1, var18, var19, false, false, true) == 1)
        {
            this.getJumpHelper().setJumping();
        }*/
        if(this.RiderJump)
        {
        	this.getJumpHelper().setJumping();
        	this.RiderJump=false;
        }
        //currentSpeed += currentSpeed * 1.15F * MathHelper.sin((float)this.speedBoostTime / (float)this.maxSpeedBoostTime * (float)Math.PI);
        //System.out.println("SPEED:"+String.valueOf(Speed+Speed*1.15F*MathHelper.sin(SpeedBoosted*(float)Math.PI)));
        this.moveEntityWithHeading(0.0F, Speed+Speed*(0.3F+0.85F*MathHelper.sin(SpeedBoosted*(float)Math.PI)));
        return Speed;
    }
    public void HandleBreed()
    {
        if (this.isAdult())
        {
            --this.BreedTick;

            if (this.BreedTick == 0)
            {
                int PartnerCount = 0;
                List var2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int var3 = 0; var3 < var2.size(); var3++)
                {
                    if (var2.get(var3) instanceof Dinosaure)
                    {
                        Dinosaure var4 = (Dinosaure)var2.get(var3);

                        if (var4.SelfType == this.SelfType && var4.isAdult())//only adults mate
                            ++PartnerCount;
                        if (PartnerCount > 30)//There are too many already
                            return;
                    }
                }

                if (PartnerCount > 20)//More won't help
                	PartnerCount = 20;

                if ((new Random()).nextInt(60) < PartnerCount)
                {
                    DinoEgg var5 = null;
                    var5 = new DinoEgg(this.worldObj, this.SelfType);
                    ((Entity)var5).setLocationAndAngles(this.posX + (double)((new Random()).nextInt(3) - 1), this.posY, this.posZ + (double)((new Random()).nextInt(3) - 1), this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

                    if (this.worldObj.checkNoEntityCollision(var5.boundingBox) && this.worldObj.getCollidingBoundingBoxes(var5, var5.boundingBox).size() == 0)
                    {
                        this.worldObj.spawnEntityInWorld((Entity)var5);
                    }

                    //this.showHeartsOrSmokeFX(true);
                    this.worldObj.setEntityState(this, HEART_MESSAGE);
                }

                this.BreedTick = this.SelfType.BreedingTicks;
            }
        }
    }

    public boolean CheckSpace()
    {
        return !this.isEntityInsideOpaqueBlock();
    }

    protected void getPathOrWalkableBlock(Entity var1, float var2)
    {
        PathEntity var3 = this.worldObj.getPathEntityToEntity(this, var1, 16.0F, true, false, true, false);
        this.setPathToEntity(var3);
    }

    public void SendOrderMessage(EnumOrderType var1)
    {

        String S = StatCollector.translateToLocal(LocalizationStrings.ORDER_HEAD)+ StatCollector.translateToLocal("order." + var1.toString());
        EreGeologique.ShowMessage(S, (EntityPlayer)this.getOwner());
    }

    public void SendStatusMessage(EnumSituation var1)
    {
		if(this.getOwner()!=null && this.getDistanceToEntity(this.getOwner())<50.0F);
		{
			String Status1=StatCollector.translateToLocal("status." + var1.toString()+".head");
			String Dino=this.SelfType.toString();
			String Status2=StatCollector.translateToLocal("status." + var1.toString());
			EreGeologique.ShowMessage(Status1 + Dino + Status2, (EntityPlayer)this.getOwner());
		}
    }

    /**
     * Shows hearts or smoke, true=heart, false=smoke
     */
    public void showHeartsOrSmokeFX(boolean hearts, boolean poof)
    {
        String var2 = "heart";

        if (!hearts && !poof)
            var2 = "smoke";
        
        if (!hearts && poof)
        	var2 = "cloud";

        for (int var3 = 0; var3 < 7; ++var3)
        {
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var2, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
        }
    }

    public float GetDistanceWithXYZ(double var1, double var3, double var5)
    {
        return (float)Math.sqrt(Math.pow(this.posX - var1, 2.0D) + Math.pow(this.posY - var3, 2.0D) + Math.pow(this.posZ - var5, 2.0D));
    }

    public void FaceToCoord(int var1, int var2, int var3)
    {
        double var4 = (double)var1;
        double var6 = (double)var3;
        float var8 = (float)(Math.atan2(var6, var4) * 180.0D / Math.PI) - 90.0F;
        this.rotationYaw = this.updateRotation(this.rotationYaw, var8, 360.0F);
    }

    private float updateRotation(float var1, float var2, float var3)
    {
        float var4;

        for (var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F)
        {
            ;
        }

        while (var4 >= 180.0F)
        {
            var4 -= 360.0F;
        }

        if (var4 > var3)
        {
            var4 = var3;
        }

        if (var4 < -var3)
        {
            var4 = -var3;
        }

        return var1 + var4;
    }

    public float GetDistanceWithTileEntity(TileEntity var1)
    {
        return var1 != null ? (float)Math.sqrt(Math.pow(this.posX - (double)var1.xCoord, 2.0D) + Math.pow(this.posY - (double)var1.yCoord, 2.0D) + Math.pow(this.posZ - (double)var1.zCoord, 2.0D)) : -1.0F;
    }

    public float GetDistanceWithEntity(Entity var1)
    {
        return (float)Math.sqrt(Math.pow(this.posX - var1.posX, 2.0D) + Math.pow(this.posY - var1.posY, 2.0D) + Math.pow(this.posZ - var1.posZ, 2.0D));
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
    	if(this.isModelized())
    		return Item.bone.itemID;
    	return this.SelfType.DropItem.itemID;
    }
    
    /**
     * Drops the rare drops
     */
    protected int DropRareDrop()
    {
    	if(this.isModelized() || !this.isAdult())
    		return 0;
    	int j=(new Random()).nextInt(4);
    	//int var4 = this.isModelized() ? 0 : this.SelfType.ordinal();
    	int id=0;
    	switch(j)
    	{
    		case 0:id=EGItemList.LegBone.itemID;break;
    		case 1:id=EGItemList.Claw.itemID;break;
    		case 2:id=EGItemList.Foot.itemID;break;
    		case 3:id=EGItemList.Skull.itemID;break;
    	}
    	this.entityDropItem(new ItemStack(id, 1,0/*, var4*/), 0.5F);
    	if(!this.isAdult())
    		return 0;
    	j=(new Random()).nextInt(4);
    	switch(j)
    	{
    		case 0:id=EGItemList.LegBone.itemID;break;
    		case 1:id=EGItemList.Claw.itemID;break;
    		case 2:id=EGItemList.Foot.itemID;break;
    		case 3:id=EGItemList.Skull.itemID;break;
    	}
    	if((new Random()).nextInt(10000)<500)
    		this.entityDropItem(new ItemStack(id, 1, 0/*var4*/), 0.5F);
        return 0;
    }
    
    /**
     Strange function Handling some additional effect when healing, but parameter is absolute unclear
      */
    public void handleHealthUpdate(byte var1)
    {
    	if(var1 == HEART_MESSAGE)
    	{
    		this.showHeartsOrSmokeFX(true, true);
    	}
    	else if(var1 == SMOKE_MESSAGE)
    	{
    		this.showHeartsOrSmokeFX(false, false);
    	}
    	else if(var1 == AGING_MESSAGE)
    	{
    		this.showHeartsOrSmokeFX(false, true);
    		//System.out.println("AGING RECEIVED!");
    		this.updateSize();
    	} 
    	else
        {
            super.handleHealthUpdate(var1);
        }
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.getDropItemId();
        if (var3 > 0)
        {
                this.entityDropItem(new ItemStack(var3, MathHelper.ceiling_float_int(this.getDinoAge()/2.0F)/*1*/,0/* var4*/), 0.5F);
            this.DropRareDrop();
        }
    }

    public boolean isAngry()
    {return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;}
    
    public void setAngry(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 2)));
        else
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -3)));
    }

    protected boolean modelizedInteract(EntityPlayer var1)
    {
        this.faceEntity(var1, 360.0F, 360.0F);
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null)
        {
            if (var2.itemID == Item.bone.itemID)
            {
                this.increaseDinoAge();
                --var2.stackSize;
                if (var2.stackSize <= 0)
                {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
        }
        return false;
    }

    protected void updateEntityActionState()
    {
        if (!this.isModelized())
        {
            super.updateEntityActionState();
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
    	float temp=this.isModelized() ? 0.0F : 0.2F + 0.5F * (float)this.getDinoAge()/(float)this.SelfType.MaxAge+this.rand.nextFloat()*0.3F;
        return temp;
    }
    
    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return 4.0F-3.0F * (float)this.getDinoAge()/(float)this.SelfType.MaxAge+this.rand.nextFloat()*0.2F;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setBoolean("isModelized", this.isModelized());
        var1.setBoolean("Angry", this.isAngry());
        var1.setInteger("Hunger", this.getHunger());
        var1.setInteger("HungerTick", this.getHungerTick());
        var1.setInteger("DinoAge", this.getDinoAge());
        var1.setInteger("AgeTick", this.getDinoAgeTick());
        var1.setInteger("SubSpecies", this.getSubSpecies());
        var1.setByte("OrderStatus", (byte)this.OrderStatus.ordinal()/*(byte)Fossil.EnumToInt(this.OrderStatus)*/);
        
        if (this.ItemInMouth != null)
        {
            var1.setShort("Itemid", (short)this.ItemInMouth.itemID);
            var1.setByte("ItemCount", (byte)this.ItemInMouth.stackSize);
            var1.setShort("ItemDamage", (short)this.ItemInMouth.getItemDamage());
        }
        else
        {
            var1.setShort("Itemid", (short) - 1);
            var1.setByte("ItemCount", (byte)0);
            var1.setShort("ItemDamage", (short)0);
        }

        if (this.getOwnerName() == null)
        {
            var1.setString("Owner", "");
        }
        else
        {
            var1.setString("Owner", this.getOwnerName());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        this.setModelized(var1.getBoolean("isModelized"));
        this.setAngry(var1.getBoolean("Angry"));
        this.setDinoAge(var1.getInteger("DinoAge"));
        this.setDinoAgeTick(var1.getInteger("AgeTick"));
        this.setHunger(var1.getInteger("Hunger"));
        this.setHungerTick(var1.getInteger("HungerTick"));
        this.setSubSpecies(var1.getInteger("SubSpecies"));

        short var3 = var1.getShort("Itemid");
        byte var4 = var1.getByte("ItemCount");
        short var5 = var1.getShort("ItemDamage");

        if (var3 != -1)
        {
            this.ItemInMouth = new ItemStack(var3, var4, var5);
        }
        else
        {
            this.ItemInMouth = null;
        }
        
        /*if (this.getHunger() <= 0)
        {
            this.setHunger(this.getMaxHunger());
        }*/

        this.OrderStatus = EnumOrderType.values()[var1.getByte("OrderStatus")];
        String var2 = var1.getString("Owner");

        if (var2.length() > 0)
        {
            this.setOwner(var2);
            this.setTamed(true);
        }
        this.updateSize();
        //this.worldObj.setEntityState(this, this.AGING_MESSAGE);//This seems to be in client-> senseless
    }

    protected boolean modelizedDrop()
    {
        if (this.isModelized())
        {
            if (!this.worldObj.isRemote)
            {
                this.entityDropItem(new ItemStack(EGItemList.BioFossil, 1), 0.0F);
                this.dropFewItems(false, 0);
                this.setDead();
            }
            return true;
        }
        return false;
    }

    //protected abstract int foodValue(Item var1);

    /**
     * returns the dinos Order status
     */
    public EnumOrderType getOrderType()
    {return this.OrderStatus;}

    public boolean canBePushed()
    {
        return false;
    }
    
    public void onLivingUpdate()
    {

    	
    	if(this.worldObj.isRemote)
    	{
    		/*System.out.println(String.valueOf(this.ticksExisted%100));
        	System.out.println(String.valueOf("   "+this.entityId));
    		System.out.println(String.valueOf("   "+this.posX));
    		System.out.println(String.valueOf("   "+this.posY));
    		System.out.println(String.valueOf("   "+this.posZ));*/
        	//this.getHealthData();
        	if(FMLCommonHandler.instance().getSide().isClient() && this.riddenByEntity!=null && this.riddenByEntity instanceof EntityPlayerSP)
        	{
        		boolean NeedsTransfer=false;
        		EntityPlayerSP P = (EntityPlayerSP)this.riddenByEntity;
        		//System.out.println("RIDDEN!!!"+String.valueOf(P.movementInput.moveStrafe));
        		if(P.movementInput.jump!=this.RiderJump)
        		{
        			this.RiderJump=P.movementInput.jump;
        			NeedsTransfer=true;
        			//this.setRiderJump(this.RiderJump);
        		}
        		if(P.movementInput.sneak!=this.RiderSneak)
        		{
        			this.RiderSneak=P.movementInput.sneak;
        			NeedsTransfer=true;
        			//this.setRiderJump(this.RiderJump);
        		}
        		if(P.movementInput.moveForward!=this.RiderForward)
        		{
        			this.RiderForward=P.movementInput.moveForward;
        			NeedsTransfer=true;
        			//this.setRiderForward(this.RiderForward);
        		}
        		if(P.movementInput.moveStrafe!=this.RiderStrafe)
        		{
        			this.RiderStrafe=P.movementInput.moveStrafe;
        			NeedsTransfer=true;
        			/*this.setRiderStrafe(this.RiderStrafe);
        			System.out.println(String.valueOf(this.entityId)+":Input "+String.valueOf(P.movementInput.moveStrafe));*/
        		}
        		if(NeedsTransfer)
        		{
        			ByteArrayOutputStream var3 = new ByteArrayOutputStream();
        	        DataOutputStream var4 = new DataOutputStream(var3);
        	        try
        	        {
        	        	var4.writeInt(this.entityId);
        	        	var4.writeFloat(this.RiderStrafe);
        	        	var4.writeFloat(this.RiderForward);
        	        	var4.writeBoolean(this.RiderJump);
        	        	var4.writeBoolean(this.RiderSneak);
        	        }
        	        catch(Exception e)
        	        {
        	        	System.err.println("ERROR WHILE WRITING Rider Input Data to Packet");
        	        }
        			Minecraft.getMinecraft().getNetHandler().addToSendQueue(new Packet250CustomPayload("RiderInput",var3.toByteArray()));
        			//System.out.println("Client has sent Rider Input data!");
        		}
        	}
        	//System.out.println("Client has set Rider Input data!");
    	}

        //System.out.println("Data:"+String.valueOf(this.dataWatcher.getWatchableObjectInt(HEALTH_INDEX)));
        this.HandleBreed();
        super.onLivingUpdate();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer player)
    {
    	//this.getClass();//needed to set which is the actual instance using this function
        if (this.isModelized())
        {
            return this.modelizedInteract(player);
        }
        else
        {
            ItemStack var2 = player.inventory.getCurrentItem();

            if (var2 != null)
            {
            	if (var2.itemID == EGItemList.ChickenEss.itemID && !player.worldObj.isRemote)
            	{// Be grown up by chicken essence
            		if (this.getDinoAge() < this.SelfType.AdultAge && this.getHunger() > 0)
                	if (this.getHunger() > 0)
            	    {
            			--var2.stackSize;
            	        if (var2.stackSize <= 0)
            	        {
            	        	player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            	        }
            	        player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle, 1));
            	        this.setDinoAgeTick(/*this.AgingTicks*/this.getDinoAgeTick()+2000);
            	        this.setHunger(1 + (new Random()).nextInt(this.getHunger()));
            	        return true;
            	     }
                    if (!this.worldObj.isRemote)
                        EreGeologique.ShowMessage(StatCollector.translateToLocal(LocalizationStrings.STATUS_ESSENCE_FAIL), player);
            	     return false;
            	}
            	
            	if (this.SelfType.FoodItemList.CheckItemById(var2.itemID) || this.SelfType.FoodBlockList.CheckBlockById(var2.itemID))
            	{//Item is one of the dinos food items
            		if(!player.worldObj.isRemote)
            		{
	            		if(this.getMaxHunger()>this.getHunger())
	                	{	//The Dino is Hungry and it can eat the item
	                		//this.showHeartsOrSmokeFX(false);
	            			this.worldObj.setEntityState(this, SMOKE_MESSAGE);
	                		this.increaseHunger(this.SelfType.FoodItemList.getItemFood(var2.itemID)+this.SelfType.FoodBlockList.getBlockFood(var2.itemID));
	                		if(CommandDino.Heal_Dinos)
	                		{
	                			//System.out.println("Hbefore:"+String.valueOf(this.health));
	                			this.heal(this.SelfType.FoodItemList.getItemHeal(var2.itemID)+this.SelfType.FoodBlockList.getBlockHeal(var2.itemID));
	                			//System.out.println("ItemHealValueInDino:"+String.valueOf(this.FoodItemList.getItemHeal(var2.itemID)+this.FoodBlockList.getBlockHeal(var2.itemID)));
	                			//System.out.println("Hafter:"+String.valueOf(this.health));
	                		}
	                		if(this.getHunger() >= this.getMaxHunger())
	                		{
	                			if(this.isTamed())
	                				this.SendStatusMessage(EnumSituation.Full);
	                			//this.setHunger(this.getMaxHunger());
	                		}
	                		--var2.stackSize;
		                    /*if (var2.stackSize <= 0)
		                    {
		                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
		                    }*/
		                    if(!this.isTamed() && this.SelfType.isTameable() && (new Random()).nextInt(10)==1)//taming probability 10% (not TREX!)
		                    {
		                    	this.setTamed(true);
		                    	this.setOwner(player.username);
		                    	//showHeartsOrSmokeFX(true);
		                    	this.worldObj.setEntityState(this, HEART_MESSAGE);
		                    }
		                    return true;
	                	}
	            		else
	            		{//the dino is not hungry but takes the food for later, carrying it in the mouth
	            			if(this.ItemInMouth == null)
	            			{//It has nothing in it's mouth
	            				this.HoldItem(var2);
	            				--var2.stackSize;
	    	                    /*if (var2.stackSize <= 0)
	    	                    {
	    	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	    	                    }*/
	    	                    return true;
	            			}
	            			else
	            			{
	            				if(this.SelfType.FoodItemList.getItemFood(ItemInMouth.itemID)+this.SelfType.FoodBlockList.getBlockFood(ItemInMouth.itemID)<this.SelfType.FoodItemList.getItemFood(var2.itemID)+this.SelfType.FoodBlockList.getBlockFood(var2.itemID))
	            				{//The item given is better food for the dino
	            					entityDropItem(new ItemStack(this.ItemInMouth.itemID, 1, 0), 0.5F);//Spit out the old item
	            					this.HoldItem(var2);
	            					--var2.stackSize;
	        	                    /*if (var2.stackSize <= 0)
	        	                    {
	        	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	        	                    }*/
	            				}
	            			}
	            		}
            		}
            		return false;
            	}
            	else//no food, but not nothing
            	{
            		if (FMLCommonHandler.instance().getSide().isClient() && var2.itemID == EGItemList.DinoPedia.itemID)
    	            {//DINOPEDIA
    	                //EntityDinosaur.pediaingDino = this;
            			this.setPedia();
    	                player.openGui(EreGeologique.Instance/*player*/, 1, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
    	                return true;
    	            }
            		if (var2.itemID == EGItemList.Whip.itemID && this.isTamed() && this.SelfType.isRideable() 
            				&& this.isAdult() && !this.worldObj.isRemote && this.riddenByEntity == null
            				&& player.username.equalsIgnoreCase(this.getOwnerName()))
    	            {
    	              //  player.rotationYaw = this.rotationYaw;
    	              //  player.mountEntity(this);
    	              //  this.setPathToEntity((PathEntity)null);
    	               // this.renderYawOffset = this.rotationYaw;
    	                setRidingPlayer(player);
    	            //    return true;
    	            }
            		if (this.SelfType.OrderItem!= null && var2.itemID == this.SelfType.OrderItem.itemID && this.isTamed() && player.username.equalsIgnoreCase(this.getOwnerName()))
                	{//THIS DINOS ITEM TO BE CONTROLLED WITH
    	                if (!this.worldObj.isRemote)
    	                {
    	                    this.isJumping = false;
    	                    this.setPathToEntity((PathEntity)null);
    	                    this.OrderStatus = EnumOrderType.values()[(this.OrderStatus.ordinal()+1) % 3/*(Fossil.EnumToInt(this.OrderStatus) + 1) % 3*/];
    	                    this.SendOrderMessage(this.OrderStatus);
    	                    if(this.OrderStatus==EnumOrderType.Stay)
    	                    {
    	                    	this.getNavigator().clearPathEntity();
    	                    	this.setPathToEntity(null);
    	                    }
    	                }
    	                return true;
                	}
            		if(this.SelfType.canCarryItems()&& var2.itemID != EGItemList.DinoPedia.itemID && this.ItemInMouth == null && ((this.isTamed() && player.username.equalsIgnoreCase(this.getOwnerName())) || (new Random()).nextInt(40)==1 ))
        			{//The dino takes the item if: able to, has nothing now and is tamed by the user or willingly(2.5%)
        				this.HoldItem(var2);
        				--var2.stackSize;
	                    if (var2.stackSize <= 0)
	                    {
	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	                    }
	                    return true;
        			}
            	}
            }
            else
            {// Klicked with bare hands
            	if(this.ItemInMouth != null && this.isTamed() && player.username.equalsIgnoreCase(this.getOwnerName()))
            	{//Give the Item to the Player, but only if it's the owner           		
                    if (player.inventory.addItemStackToInventory(this.ItemInMouth))
                    {
                        this.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        this.ItemInMouth = null;
                        return true;
                    }
            	}
            	if(this.SelfType.OrderItem == null && this.isTamed() && player.username.equalsIgnoreCase(this.getOwnerName()))
            	{//This dino is controlled without a special item
            		if (!this.worldObj.isRemote)
	                {
	                    this.isJumping = false;
	                    this.setPathToEntity((PathEntity)null);
	                    this.OrderStatus = EnumOrderType.values()[(this.OrderStatus.ordinal()+1) % 3/*(Fossil.EnumToInt(this.OrderStatus) + 1) % 3*/];
	                    this.SendOrderMessage(this.OrderStatus);
	                }
	                return true;
            	}
            	/*
            	if (this.isTamed() && this.SelfType.isRideable() && this.isAdult() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == player))
	            {
	                player.rotationYaw = this.rotationYaw;
	                player.mountEntity(this);
	                this.setPathToEntity((PathEntity)null);
	                this.renderYawOffset = this.rotationYaw;
	                return true;
	            }
	            */
            }
            return super.interact(player);
        }
    }

    //public void CheckSkin() {}

    public int BlockInteractive() {return 0;}

    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    public boolean canBeSteered()
    {
        ItemStack var1 = ((EntityPlayer)this.riddenByEntity).getHeldItem();
        return var1 != null && var1.itemID == EGItemList.Whip.itemID;
    }

    public void SetOrder(EnumOrderType var1)
    {
        this.OrderStatus = var1;
    }

    public void writeSpawnData(ByteArrayDataOutput var1) {}

    public void readSpawnData(ByteArrayDataInput var1) {}

    
    @Override
	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return null;
	}
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }
    

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
            par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
            Random random = new Random();

            this.setSubSpecies(random.nextInt(3) + 1);
            
        	this.setDinoAge(this.SelfType.AdultAge);

            return par1EntityLivingData;
    }

    /**
     * Return the AI task for player control.
     */
    public EntityAIControlledByPlayer getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
    }

    public EntityPlayer getRidingPlayer() {
        if (riddenByEntity instanceof EntityPlayer) {
            return (EntityPlayer) riddenByEntity;
        } else {
            return null;
        }
    }
    
    public void setRidingPlayer(EntityPlayer player) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        player.mountEntity(this);
    }
    
    public void riderJump() {
    	EreGeologique.DebugMessage("isRiderJumping");
        motionY += 0.5;
    }
}