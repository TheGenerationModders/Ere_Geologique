package ere_geologique.common.entity.IA;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
<<<<<<< HEAD
import net.minecraft.world.World;
import ere_geologique.common.EreGeologique;
=======
import ere_geologique.api.food.DinoFood;
import ere_geologique.common.command.CommandDino;
>>>>>>> 5b1f4448fa63e18a4820897a397df03bee9c7e00
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class DinoAIEat extends EntityAIBase
{    
    private Dinosaure dinosaur;
    private double destX;
    private double destY;
    private double destZ;

    protected EntityCreature taskOwner;
    
    private static final int NO_TARGET = -1;
    private static final int ITEM = 1;
    private static final int BLOCK = 2;
    private static final int MOB = 3;
    private static final int FEEDER = 4;
    private int typeofTarget = NO_TARGET;
    private int TimeAtThisTarget = 0;

    // the range in which the dino is able to look for items
    private final int SEARCH_RANGE;

    // the range the dino is able to get the item when in
    private final int USE_RANGE = 3;

    // The item the dino is going to take
    private TileEntityFeeder targetFeeder;
    private EntityItem targetItem;
    private EntityItem targetBlock;
    private EntityLiving targetMob;
    private EntityLivingBase targetEntity;
    private DinoAINearestAttackableTargetSorter targetSorter;

    private World theWorld;

    /**
     * Creates The AI, Input: Dino, Speed, searching range
     */
    public DinoAIEat(Dinosaure Dino0, int Range0)
    {
        this.targetBlock = null;
        this.targetItem = null;
        this.targetMob = null;
        this.targetFeeder = null;
        this.dinosaur = Dino0;
        this.setMutexBits(1);
        this.SEARCH_RANGE = Range0;
        this.targetSorter = new DinoAINearestAttackableTargetSorter(this, this.dinosaur);
        this.TimeAtThisTarget = 0;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
<<<<<<< HEAD
        int Range = this.SEARCH_RANGE;// Current Searching range

        if (!this.dinosaur.IsHungry() && !this.dinosaur.IsDeadlyHungry())
        {
            this.typeofTarget = NO_TARGET;
            return false;
        }

        targetFeeder = this.dinosaur.GetNearestFeeder(Range);

        //Feeder has priority over other food sources.
        if (this.dinosaur.SelfType.useFeeder() && (this.targetFeeder != null))
        {
        	EreGeologique.EGLog.log(Level.FINEST, "FOUND FEEDER.");
            this.destX = this.targetFeeder.xCoord;
            this.destY = this.targetFeeder.yCoord;
            this.destZ = this.targetFeeder.zCoord;
            this.typeofTarget = FEEDER;
            return targetFeeder != null;
        }
        //After Feeder, check if there are items, THEN blocks on the ground to eat.
        else if (!this.dinosaur.SelfType.FoodItemList.IsEmpty() || !this.dinosaur.SelfType.FoodBlockList.IsEmpty())
        {
            Vec3 targetItem = this.getNearestItem(Range);
            Vec3 targetBlock = this.dinosaur.getBlockToEat(Range);

            if (targetItem != null)//Found Item, go there and eat it
=======
        if (this.Dino.IsHungry() && this.typeofTarget==NO_TARGET && (!this.Dino.SelfType.dinoFood.isEmpty() || !this.Dino.SelfType.FoodMobList.IsEmpty() || this.Dino.SelfType.useFeeder()))
        {
            //System.out.println("TargetTypeShould:"+String.valueOf(this.typeofTarget));
            int Range=this.SEARCH_RANGE;//Current Searching range
            if(this.Dino.IsDeadlyHungry())
                Range*=2;
            
            if(!this.Dino.SelfType.dinoFood.isEmpty())// Can Find Items or ItemBlocks!
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
            if(!this.Dino.SelfType.dinoFood.isEmpty())//Hasn't found anything and has blocks it can look for
>>>>>>> 5b1f4448fa63e18a4820897a397df03bee9c7e00
            {
                this.destX = targetItem.xCoord;
                this.destY = targetItem.yCoord;
                this.destZ = targetItem.zCoord;
                this.typeofTarget = ITEM;
                EreGeologique.EGLog.log(Level.FINEST, "ITEM FOUND!");
                return targetItem != null;
            }
            else if (targetBlock != null)//Found Item, go there and eat it
            {
                this.destX = targetBlock.xCoord;
                this.destY = targetBlock.yCoord;
                this.destZ = targetBlock.zCoord;
                this.typeofTarget = BLOCK;
                EreGeologique.EGLog.log(Level.FINEST, "BLOCK FOUND!");
                return targetBlock != null;
            }
        }
        /*
        else if (!this.dinosaur.SelfType.FoodMobList.IsEmpty())
        {
        double d0 = this.SEARCH_RANGE;
        List list = this.dinosaur.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.dinosaur.boundingBox.expand(d0, 4.0D, d0));
        Collections.sort(list, this.targetSorter);
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            EntityLiving entity = (EntityLiving)iterator.next();

            if (this.dinosaur.SelfType.FoodMobList.CheckMobByClass(entity.getClass()))
            {//It's food
                if(!(entity instanceof EntityDinosaur) || (entity instanceof EntityDinosaur && ((EntityDinosaur) entity).isModelized()==false))
                {//No modelized Dinos for Lunch!
                    this.targetEntity = entity;
                	this.dinosaur.setAttackTarget(entity);
                    Log.log(Level.FINEST, "typeofTarget: " + this.targetEntity);
                	return true;
                }
            }
            

         //   this.targetEntity = (EntityLivingBase)list.get(0);
        //    return true;
        }
        }
        */
        this.targetEntity = null;
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
<<<<<<< HEAD
    	EreGeologique.EGLog.log(Level.FINEST, "typeofTarget: " + this.typeofTarget);
        return (this.dinosaur.IsHungry() || this.dinosaur.IsDeadlyHungry()) && (this.typeofTarget != -1);
=======
        //System.out.println("Continue:"+String.valueOf(!this.Dino.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && this.Dino.FoodBlockList.CheckBlockById(this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ))))));
        return !this.Dino.getNavigator().noPath() && ((this.typeofTarget==ITEM && this.targetItem.isEntityAlive()) || (this.typeofTarget==MOB && this.targetMob.isEntityAlive()) || (this.typeofTarget==FEEDER && !this.targetFeeder.isInvalid()) || (this.typeofTarget==BLOCK && DinoFood.isDinoFoodByDino(this.Dino.SelfType, this.Dino.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ), this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ))));
>>>>>>> 5b1f4448fa63e18a4820897a397df03bee9c7e00
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        int Range = this.SEARCH_RANGE;
        this.dinosaur.setSitting(false);
        double Distance = Math.sqrt(Math.pow(this.dinosaur.posX - this.destX, 2.0D) + Math.pow(this.dinosaur.posZ - this.destZ, 2.0D));

        if (Distance > Range)
        {
            endTask();
        }

        EreGeologique.EGLog.log(Level.FINEST, "MOVING to " + Distance);
        this.dinosaur.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, 1.0D);
        EreGeologique.EGLog.log(Level.FINEST, "Coords: " + this.destX + ", " + this.destY + ", " +  this.destZ);

        if (this.typeofTarget == FEEDER)
        {
            if (Distance < 2)
            {
                int healval = MathHelper.floor_double(this.targetFeeder.Feed(this.dinosaur, this.dinosaur.SelfType) / 15D);
                this.dinosaur.heal(healval);
                this.dinosaur.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, 1.5D);
                this.TimeAtThisTarget++;
                EreGeologique.EGLog.log(Level.FINEST, "TimeAtThisTarget " + this.TimeAtThisTarget);

                if (this.TimeAtThisTarget == 100)
                {
                    endTask();
                }
            }
        }

        if (this.typeofTarget == ITEM)
        {
            if (Distance < 2.5)
            {
                this.dinosaur.getNavigator().tryMoveToXYZ(this.destX, this.destY, this.destZ, 1.5D);

                if (this.targetItem != null && !this.targetItem.isDead)
                {
                    int i = this.dinosaur.PickUpItem(this.targetItem.getEntityItem());

                    if (i > 0)
                    {
                        this.targetItem.getEntityItem().stackSize = i;
                        endTask();
                    }
                    else
                    {
                        this.targetItem.setDead();
                        endTask();
                    }
<<<<<<< HEAD
                }
=======
                break;
                case BLOCK:
                    if(CommandDino.Heal_Dinos)
                        this.Dino.heal(DinoFood.getFoodByDino(this.Dino.SelfType, this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ), this.Dino.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ)).getHealValue());
                    this.Dino.increaseHunger(DinoFood.getFoodByDino(this.Dino.SelfType, this.Dino.worldObj.getBlockId((int)destX, (int)destY, (int)destZ), this.Dino.worldObj.getBlockMetadata((int)destX, (int)destY, (int)destZ)).getFoodValue());
                    this.Dino.worldObj.setBlock((int)destX, (int)destY, (int)destZ,0);
                break;
>>>>>>> 5b1f4448fa63e18a4820897a397df03bee9c7e00
            }
        }

        if (this.typeofTarget == BLOCK)
        {
            if (Distance < 2.5)
            {
                this.dinosaur.heal(this.dinosaur.SelfType.FoodBlockList.getBlockHeal(this.dinosaur.worldObj.getBlockId((int)destX, (int)destY, (int)destZ)));
                this.dinosaur.increaseHunger(this.dinosaur.SelfType.FoodBlockList.getBlockFood(this.dinosaur.worldObj.getBlockId((int)destX, (int)destY, (int)destZ)));
                this.dinosaur.worldObj.setBlock((int)destX, (int)destY, (int)destZ, 0);
                this.TimeAtThisTarget++;
                EreGeologique.EGLog.log(Level.FINEST, "TimeAtThisTarget " + this.TimeAtThisTarget);

<<<<<<< HEAD
                if (this.TimeAtThisTarget == 20)
                {
                    endTask();
                }
=======
            if (DinoFood.isDinoFoodByDino(this.Dino.SelfType, var4.getEntityItem().itemID, var4.getEntityItem().getItemDamage()) || (this.Dino.SelfType.canCarryItems() && !this.Dino.IsHungry()))
            {//It's food or the dino can carry things and is not hungry
                this.targetItem = var4;
                var3 = Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ);
                break;
>>>>>>> 5b1f4448fa63e18a4820897a397df03bee9c7e00
            }
        }
    }

    public void endTask()
    {
        this.dinosaur.getNavigator().clearPathEntity();
        this.TimeAtThisTarget = 0;
        targetItem = null;
        targetBlock = null;
        targetFeeder = null;
        this.targetEntity = null;
        this.typeofTarget = NO_TARGET;
        EreGeologique.EGLog.log(Level.FINEST, "CLEARING");
    }

    private TileEntityFeeder getNearbyFeeder()
    {
        double range = 36;
        List<TileEntity> nearbyEntities = theWorld.getEntitiesWithinAABB(TileEntityFeeder.class, this.dinosaur.boundingBox.expand(range, range, range));

        for (TileEntity entity : nearbyEntities)
        {
            TileEntityFeeder nearbyFeeder = (TileEntityFeeder) entity;

            if (this.dinosaur.SelfType.useFeeder())
            {
                return nearbyFeeder;
            }
        }

        return null;
    }

    private Vec3 getNearestItem(int SEARCH_RANGE)
    {
        List nearbyItems = this.dinosaur.worldObj.getEntitiesWithinAABB(EntityItem.class, this.dinosaur.boundingBox.expand(SEARCH_RANGE, SEARCH_RANGE, SEARCH_RANGE));
        Collections.sort(nearbyItems, this.targetSorter);
        Iterator iterateNearbyItems = nearbyItems.iterator();
        Vec3 itemlocation = null;

        while (iterateNearbyItems.hasNext())
        {
            EntityItem entityItem = (EntityItem) iterateNearbyItems.next();

            if (this.dinosaur.SelfType.FoodItemList.CheckItemById(entityItem.getEntityItem().itemID) || this.dinosaur.SelfType.FoodBlockList.CheckBlockById(entityItem.getEntityItem().itemID))
            {
                this.targetItem = entityItem;
                itemlocation = Vec3.createVectorHelper(entityItem.posX, entityItem.posY, entityItem.posZ);
                break;
            }
        }

        return itemlocation;
    }

}