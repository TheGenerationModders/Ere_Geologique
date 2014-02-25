package ere_geologique.common.entity.ia;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import ere_geologique.api.food.DinoFood;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class DinoAIEat extends EntityAIBase
{
    private Dinosaure dinosaur;
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
    
    //the range in which the dinosaur is able to look for items
    private final int SEARCH_RANGE;
    
    //the range the dinosaur is able to get the item when in
    private final int USE_RANGE = 3;
    
    //The item the dinosaur is going to take
    private TileEntityFeeder targetFeeder;
    private EntityItem targetItem;
    private EntityLiving targetMob;
    private DinoAINearestAttackableTargetSorter targetSorter;

    /**
* Creates The AI, Input: dinosaur, Speed, searching range
*/
    public DinoAIEat(Dinosaure dinosaur0, int Range0)
    {
        this.targetItem = null;
        this.targetMob = null;
        this.targetFeeder = null;
        this.dinosaur = dinosaur0;
        this.setMutexBits(1);
        this.SEARCH_RANGE = Range0;
        this.targetSorter = new DinoAINearestAttackableTargetSorter(this, this.dinosaur);
        this.TimeAtThisTarget=0;
    }

    /**
* Returns whether the EntityAIBase should begin execution.
*/
    public boolean shouldExecute()
    {
        if (this.dinosaur.IsHungry() && this.typeofTarget==NO_TARGET && (!this.dinosaur.SelfType.dinoFood.isEmpty() || !this.dinosaur.SelfType.FoodMobList.IsEmpty() || this.dinosaur.SelfType.useFeeder()))
        {
            //System.out.println("TargetTypeShould:"+String.valueOf(this.typeofTarget));
            int Range=this.SEARCH_RANGE;//Current Searching range
            if(this.dinosaur.IsDeadlyHungry())
                Range*=2;
            
            if(!this.dinosaur.SelfType.dinoFood.isEmpty())// Can Find Items or ItemBlocks!
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
            if(this.dinosaur.SelfType.useFeeder())
            {
                this.targetFeeder = this.dinosaur.GetNearestFeeder(Range/2);
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
            if(!this.dinosaur.SelfType.dinoFood.isEmpty())//Hasn't found anything and has blocks it can look for
            {
                Vec3 var1 = this.dinosaur.getBlockToEat(Range/2);
                
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
            if(!this.dinosaur.SelfType.FoodMobList.IsEmpty())//Hasn't found anything and has blocks it can look for
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
            if(this.dinosaur.SelfType.canCarryItems())
            {//It can carry items
                int Range=3;//dinosaur just steps over an item
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
                {// The dinosaur is willing to (once every 4000-8000 ticks), but looks only in a small radius
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
        //System.out.println("Continue:"+String.valueOf(!this.dinosaur.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && this.dinosaur.FoodBlockList.CheckBlockById(this.dinosaur.worldObj.getBlockId((int)destX, (int)destY, (int)destZ))))));
        return !this.dinosaur.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && DinoFood.isDinoFoodByDino(this.dinosaur.SelfType, this.dinosaur.worldObj.getBlock((int)destX, (int)destY, (int)destZ), this.dinosaur.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ))));
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
                this.dinosaur.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, this.dinosaur.getAIMoveSpeed());
            }
            else
            {
                this.dinosaur.getNavigator().clearPathEntity();
                this.TimeAtThisTarget=0;
                this.typeofTarget=NO_TARGET;
            }
            return;
        }
        double Distance = Math.pow(this.dinosaur.posX - this.destX, 2.0D) + Math.pow(this.dinosaur.posZ - this.destZ, 2.0D);

        if (Distance < Math.pow(this.USE_RANGE, 2.0D))
        {
            switch(this.typeofTarget)
            {
                case ITEM:
                    if(this.targetItem!=null && !this.targetItem.isDead)
                    {
                        int i=this.dinosaur.PickUpItem(this.targetItem.getEntityItem());
                        if(i>0)
                            this.targetItem.getEntityItem().stackSize=i;
                        else
                            this.targetItem.setDead();
                    }
                break;
                case FEEDER:
                    if(!this.targetFeeder.isInvalid())
                    {
                        int healval=MathHelper.floor_double(this.targetFeeder.Feed(this.dinosaur, this.dinosaur.SelfType)/15D);
                        if(CommandDino.heal_Dinos)
                            this.dinosaur.heal(healval);
                    }
                break;
                case BLOCK:
                    if(CommandDino.heal_Dinos)
                        this.dinosaur.heal(DinoFood.getFoodByDino(this.dinosaur.SelfType, this.dinosaur.worldObj.getBlock((int)destX, (int)destY, (int)destZ), this.dinosaur.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ)).getHealValue());
                    this.dinosaur.increaseHunger(DinoFood.getFoodByDino(this.dinosaur.SelfType, this.dinosaur.worldObj.getBlock((int)destX, (int)destY, (int)destZ), this.dinosaur.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ)).getFoodValue());
                    this.dinosaur.worldObj.setBlockToAir((int)destX, (int)destY, (int)destZ);
                break;
            }
            
            this.dinosaur.getNavigator().clearPathEntity();
            this.TimeAtThisTarget=0;
            this.typeofTarget=NO_TARGET;
        }
        else
            this.dinosaur.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, this.dinosaur.getAIMoveSpeed());
    }

    private Vec3 getNearestItem(int SEARCH_RANGE)
    {
        List var1 = this.dinosaur.worldObj.getEntitiesWithinAABB(EntityItem.class, this.dinosaur.boundingBox.expand((double)SEARCH_RANGE, 4.0D, (double)SEARCH_RANGE));
        Collections.sort(var1, this.targetSorter);
        Iterator var2 = var1.iterator();
        Vec3 var3 = null;

        while (var2.hasNext())
        {
            EntityItem var4 = (EntityItem)var2.next();

            if (DinoFood.isDinoFoodByDino(this.dinosaur.SelfType, var4.getEntityItem().getItem(), var4.getEntityItem().getItemDamage()) || (this.dinosaur.SelfType.canCarryItems() && !this.dinosaur.IsHungry()))
            {//It's food or the dinosaur can carry things and is not hungry
                this.targetItem = var4;
                var3 = Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ);
                break;
            }
        }
        return var3;
    }
    private Vec3 getNearestPrey(int SEARCH_RANGE)
    {
        List var1 = this.dinosaur.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.dinosaur.boundingBox.expand((double)SEARCH_RANGE, 4.0D, (double)SEARCH_RANGE));
        Collections.sort(var1, this.targetSorter);
        Iterator var2 = var1.iterator();
        Vec3 var3 = null;

        while (var2.hasNext())
        {
            EntityLiving var4 = (EntityLiving)var2.next();

            if (this.dinosaur.SelfType.FoodMobList.CheckMobByClass(var4.getClass()))
            {//It's food
                if(!(var4 instanceof Dinosaure) || (var4 instanceof Dinosaure && ((Dinosaure) var4).isModelized()==false))
                {//No modelized dinosaurs for Lunch!
                    this.targetMob = var4;
                    this.dinosaur.setAttackTarget(var4);
                    var3 = Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ);
                    break;
                }
            }
        }
        return var3;
    }
}