package ere_geologique.common.entity.IA;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class DinoAIEat extends EntityAIBase
{
    private Dinosaure Dino;
    private double destX;
    private double destY;
    private double destZ;
    
    private static final int NO_TARGET = -1;
    private static final int ITEM = 1;
    private static final int BLOCK = 2;
    private static final int MOB = 3;
    private static final int FEEDER = 4;
    private int typeofTarget = NO_TARGET;
    private int TimeAtThisTarget = 0;
    
    //the range in which the dino is able to look for items
    private final int SEARCH_RANGE;
    
    //the range the dino is able to get the item when in
    private final int USE_RANGE = 3;
    
    //The item the dino is going to take
    private TileEntityFeeder targetFeeder;
    private EntityItem targetItem;
    private EntityLiving targetMob;
    private DinoAINearestAttackableTargetSorter targetSorter;

    /**
     * Creates The AI, Input: Dino, Speed, searching range
     */
    public DinoAIEat(Dinosaure Dino0, int Range0)
    {
        this.targetItem = null;
        this.targetMob = null;
        this.targetFeeder = null;
        this.Dino = Dino0;
        this.setMutexBits(1);
        this.SEARCH_RANGE = Range0;
        this.targetSorter = new DinoAINearestAttackableTargetSorter(this, this.Dino);
        this.TimeAtThisTarget=0;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.Dino.IsHungry() && this.typeofTarget==NO_TARGET && (!this.Dino.SelfType.FoodItemList.IsEmpty() || !this.Dino.SelfType.FoodBlockList.IsEmpty() || !this.Dino.SelfType.FoodMobList.IsEmpty() || this.Dino.SelfType.useFeeder()))
        {
            //System.out.println("TargetTypeShould:"+String.valueOf(this.typeofTarget));
            int Range=this.SEARCH_RANGE;//Current Searching range
            if(this.Dino.IsDeadlyHungry())
                Range*=2;
            
            if(!this.Dino.SelfType.FoodItemList.IsEmpty() || !this.Dino.SelfType.FoodBlockList.IsEmpty())// Can Find Items or ItemBlocks!
            {
                Vec3 var1 = this.getNearestItem(Range);
    
                if (var1 != null)//Found Item, go there and eat it
                {
                    this.destX = var1.xCoord;
                    this.destY = var1.yCoord;
                    this.destZ = var1.zCoord;
                    this.typeofTarget=ITEM;
                    //System.out.println("ITEM FOUND!");
                    return true;
                }
            }
            if(this.Dino.SelfType.useFeeder())
            {
                this.targetFeeder = this.Dino.GetNearestFeeder(Range/2);
                if (this.targetFeeder != null)//Found Item, go there and eat it
                {
                    this.destX = this.targetFeeder.xCoord;
                    this.destY = this.targetFeeder.yCoord;
                    this.destZ = this.targetFeeder.zCoord;
                    this.typeofTarget=FEEDER;
                    //System.out.println("FEEDER FOUND!");
                    return true;
                }
            }
            if(!this.Dino.SelfType.FoodBlockList.IsEmpty())//Hasn't found anything and has blocks it can look for
            {
                Vec3 var1 = this.Dino.getBlockToEat(Range/2);
                
                if (var1 != null)//Found Item, go there and eat it
                {
                    this.destX = var1.xCoord;
                    this.destY = var1.yCoord;
                    this.destZ = var1.zCoord;
                    this.typeofTarget=BLOCK;
                    //System.out.println("BLOCK FOUND!");
                    return true;
                }
            }
            if(!this.Dino.SelfType.FoodMobList.IsEmpty())//Hasn't found anything and has blocks it can look for
            {
                Vec3 var1 = this.getNearestPrey(Range);
                
                if (var1 != null)//Found Item, go there and eat it
                {
                    this.destX = var1.xCoord;
                    this.destY = var1.yCoord;
                    this.destZ = var1.zCoord;
                    this.typeofTarget=MOB;
                    //System.out.println("MOB FOUND!");
                    return true;
                }
            }
        }
        else
        {
            if(this.Dino.SelfType.canCarryItems())
            {//It can carry items
                int Range=3;//Dino just steps over an item
                Vec3 var1 = this.getNearestItem(Range);

                if (var1 != null)
                {
                    this.destX = var1.xCoord;
                    this.destY = var1.yCoord;
                    this.destZ = var1.zCoord;
                    this.typeofTarget=ITEM;
                    return true;
                }
                if((new Random()).nextInt((new Random()).nextInt(4000)+4000)==1)
                {// The Dino is willing to (once every 4000-8000 ticks), but looks only in a small radius
                    Range=10;
                    var1 = this.getNearestItem(Range);
    
                    if (var1 != null)
                    {
                        this.destX = var1.xCoord;
                        this.destY = var1.yCoord;
                        this.destZ = var1.zCoord;
                        this.typeofTarget=ITEM;
                        return true;
                    }
                }
            }
            if(this.typeofTarget!=NO_TARGET)
                return true;
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        //System.out.println("Continue:"+String.valueOf(!this.Dino.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && this.Dino.FoodBlockList.CheckBlockById(this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ))))));
        return !this.Dino.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && this.Dino.SelfType.FoodBlockList.CheckBlockById(this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ))));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.TimeAtThisTarget++;
        if(this.TimeAtThisTarget==500)
        {
            this.TimeAtThisTarget=0;
            this.typeofTarget=NO_TARGET;
            return;
        }
        //System.out.println("TargetType:"+String.valueOf(this.typeofTarget));
        if(this.typeofTarget==MOB)
        {
            if(!this.targetMob.isDead)
            {
                this.destX=this.targetMob.posX;
                this.destY=this.targetMob.posY;
                this.destZ=this.targetMob.posZ;
                this.Dino.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, this.Dino.getAIMoveSpeed());
            }
            else
            {
                this.Dino.getNavigator().clearPathEntity();
                this.TimeAtThisTarget=0;
                this.typeofTarget=NO_TARGET;
            }
            return;
        }
        double Distance = Math.pow(this.Dino.posX - this.destX, 2.0D) + Math.pow(this.Dino.posZ - this.destZ, 2.0D);

        if (Distance < Math.pow(this.USE_RANGE, 2.0D))
        {
            switch(this.typeofTarget)
            {
                case ITEM:
                    if(this.targetItem!=null && !this.targetItem.isDead)
                    {
                        int i=this.Dino.PickUpItem(this.targetItem.getEntityItem());
                        if(i>0)
                            this.targetItem.getEntityItem().stackSize=i;
                        else
                            this.targetItem.setDead();
                    }
                break;
                case FEEDER:
                    if(!this.targetFeeder.isInvalid())
                    {
                        int healval=MathHelper.floor_double(this.targetFeeder.Feed(this.Dino, this.Dino.SelfType)/15D);
                        if(EreGeologique.EGOptions.Heal_Dinos)
                            this.Dino.heal(healval);
                    }
                break;
                case BLOCK:
                    if(EreGeologique.EGOptions.Heal_Dinos)
                        this.Dino.heal(this.Dino.SelfType.FoodBlockList.getBlockHeal(this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ)));
                    this.Dino.increaseHunger(this.Dino.SelfType.FoodBlockList.getBlockFood(this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ)));
                    this.Dino.worldObj.setBlock((int)destX, (int)destY, (int)destZ,0);
                break;
            }
            
            this.Dino.getNavigator().clearPathEntity();
            this.TimeAtThisTarget=0;
            this.typeofTarget=NO_TARGET;
        }
        else
            this.Dino.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, this.Dino.getAIMoveSpeed());
    }

    private Vec3 getNearestItem(int SEARCH_RANGE)
    {
        List var1 = this.Dino.worldObj.getEntitiesWithinAABB(EntityItem.class, this.Dino.boundingBox.expand((double)SEARCH_RANGE, 4.0D, (double)SEARCH_RANGE));
        Collections.sort(var1, this.targetSorter);
        Iterator var2 = var1.iterator();
        Vec3 var3 = null;

        while (var2.hasNext())
        {
            EntityItem var4 = (EntityItem)var2.next();

            if (this.Dino.SelfType.FoodItemList.CheckItemById(var4.getEntityItem().itemID) || this.Dino.SelfType.FoodBlockList.CheckBlockById(var4.getEntityItem().itemID) || (this.Dino.SelfType.canCarryItems() && !this.Dino.IsHungry()))
            {//It's food or the dino can carry things and is not hungry
                this.targetItem = var4;
                var3 = Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ);
                break;
            }
        }
        return var3;
    }
    private Vec3 getNearestPrey(int SEARCH_RANGE)
    {
        List var1 = this.Dino.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.Dino.boundingBox.expand((double)SEARCH_RANGE, 4.0D, (double)SEARCH_RANGE));
        Collections.sort(var1, this.targetSorter);
        Iterator var2 = var1.iterator();
        Vec3 var3 = null;

        while (var2.hasNext())
        {
            EntityLiving var4 = (EntityLiving)var2.next();

            if (this.Dino.SelfType.FoodMobList.CheckMobByClass(var4.getClass()))
            {//It's food
                if(!(var4 instanceof Dinosaure) || (var4 instanceof Dinosaure && ((Dinosaure) var4).isModelized()==false))
                {//No modelized Dinos for Lunch!
                    this.targetMob = var4;
                    this.Dino.setAttackTarget(var4);
                    var3 = Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ);
                    break;
                }
            }
        }
        return var3;
    }

    /*protected boolean checkTargetReachable(Entity var1, boolean var2)
    {
        if (var1 == null)
        {
            return false;
        }
        else if (!var1.isEntityAlive())
        {
            return false;
        }
        else if (var1.boundingBox.maxY > this.Dino.boundingBox.minY && var1.boundingBox.minY < this.Dino.boundingBox.maxY)
        {
            if (this.Dino.isTamed())
            {
                if (var1 instanceof EntityTameable && ((EntityTameable)var1).isTamed())
                {
                    return false;
                }

                if (var1 == this.entityVar.getOwner())
                {
                    return false;
                }
            }
            else if (var1 instanceof EntityPlayer && !var2 && ((EntityPlayer)var1).capabilities.disableDamage)
            {
                return false;
            }

            return this.Dino.isWithinHomeDistance(MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ));
        }
        else
        {
            return false;
        }
    }*/
}