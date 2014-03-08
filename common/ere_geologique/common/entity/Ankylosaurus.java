package ere_geologique.common.entity;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ere_geologique.common.entity.enums.EnumDinoType;
import ere_geologique.common.entity.ia.DinoAIEat;
import ere_geologique.common.entity.ia.DinoAIFollowOwner;
import ere_geologique.common.entity.ia.DinoAIRideGround;
import ere_geologique.common.entity.ia.DinoAIWander;

public class Ankylosaurus extends Dinosaure
{
    private int angerLevel;

    final EntityAIControlledByPlayer aiControlledByPlayer;

    public Ankylosaurus(World world)
    {
        super(world, EnumDinoType.Ankylosaurus);
        this.updateSize();
        this.setSubSpecies((new Random()).nextInt(3) + 1);
        /*
         * EDIT VARIABLES PER DINOSAUR TYPE
         */
        this.adultAge = EnumDinoType.Ankylosaurus.AdultAge;
        // Set initial size for hitbox. (length/width, height)
        this.setSize(1.5F, 1.0F);
        // Size of dinosaur at day 0.
        this.minSize = 1.0F;
        // Size of dinosaur at age Adult.
        this.maxSize = 3.0F;
        this.healthModValue = 1;
        this.damageModValue = 1;
        this.speedModValue = 0;
        this.getNavigator().setAvoidsWater(true);
        tasks.addTask(1, new DinoAIRideGround(this, 1)); // mutex all
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
        this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.2F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, TRex.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, Spinosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, Brachiosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, 1, true));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(4, new DinoAIFollowOwner(this, 1.0D, 5.0F, 2.0F));
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    }

    /**
     * Return the AI task for player control.
     */
    public EntityAIControlledByPlayer getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(21.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

    /**
     * Returns the texture's file path as a String.
     */
    @Override
    public String getTexture()
    {
        if (this.isModelized())
        {
            return super.getTexture();
        }

        switch (this.getSubSpecies())
        {
            default:
                return "ere_geologique:textures/entity/Ankylosaurus.png";
        }
    }
    
    public String getDinosaurName()
    {
    	return EnumDinoType.Ankylosaurus.name();
    }
    
    @Override
    protected String getLivingSound()
    {
        return "ere_geologique:mob.ankylosaurus.living";
    }
    
    @Override
    protected String getHurtSound()
    {
        return "ere_geologique:mob.ankylosaurus.hurt";
    }
    
    @Override
    protected String getDeathSound()
    {
        return "ere_geologique:mob.ankylosaurus.death";
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }

    public boolean interact(EntityPlayer var1)
    {
        //Add special item interaction code here
        return super.interact(var1);
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

    public Ankylosaurus spawnBabyAnimal(EntityAgeable var1)
    {
        return new Ankylosaurus(this.worldObj);
    }

    public float getEyeHeight()
    {
        return (float)this.getDinoAge() / 1.9F;
    }

    public float getRidingHeight()
    {
        return this.getEyeHeight() + 0.3F;
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.setPosition(this.posX, this.posY + (double)this.getRidingHeight(), this.posZ);
        }
    }

	@Override
	public void writeSpawnData(ByteBuf buffer){}

	@Override
	public void readSpawnData(ByteBuf additionalData){}
}