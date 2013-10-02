package ere_geologique.common.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.Enums.EnumOrderType;
import ere_geologique.common.entity.IA.DinoAIEat;
import ere_geologique.common.entity.IA.DinoAIFollowOwner;
import ere_geologique.common.entity.IA.DinoAIWander;

public class Ankylosaurus extends Dinosaure
{
    private int angerLevel;
    

	public Ankylosaurus(World world) {
        super(world, EnumDinoType.Ankylosaurus);
        
        this.OrderStatus = EnumOrderType.FreeMove;
        this.updateSize();
        this.setSubSpecies((new Random()).nextInt(3) + 1);
        
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}
	
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(21.0D);
    }
	
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return !this.isModelized();
    }
	
    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick()
    {

    }

    protected void entityInit()
    {
        super.entityInit();

    }
    
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }
   
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = par1DamageSource.getEntity();

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof Ankylosaurus)
                    {
                    	Ankylosaurus entityankylosaurus = (Ankylosaurus)entity1;
                    	entityankylosaurus.becomeAngryAt(entity);
                    }
                }

                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    /**
     * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
     */
    private void becomeAngryAt(Entity par1Entity)
    {
        this.entityToAttack = par1Entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
    }
    
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    
    
    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.setPosition(this.posX, this.posY + (double)this.getDinoHeight() * 0.65D + 0.07D * (double)(12 - this.getDinoAge()), this.posZ);
        }
    }
    
    /**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity var1)
    {
        if (var1 instanceof EntityLiving && this.riddenByEntity != null && this.onGround && this.RiderSneak==true)
        {//you can hurt others with the dino while you ride it
            //this.onKillEntity((EntityLiving)var1);
            ((EntityLiving)var1).attackEntityFrom(DamageSource.causeMobDamage(this), 10);
        }
        else
        {
            super.applyEntityCollision(var1);
        }
    }
    
    public Ankylosaurus spawnBabyAnimal(EntityAgeable var1)
    {
        return new Ankylosaurus(this.worldObj);
    }
    
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

}