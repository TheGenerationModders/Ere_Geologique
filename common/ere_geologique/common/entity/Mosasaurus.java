package ere_geologique.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.IA.WaterDinoAIAttack;

public class Mosasaurus extends SwimmingDino implements IMob
{
    private Entity targetedEntity;

    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;
    private double length;

    public Mosasaurus(World par1World)
    {
        super(par1World, EnumDinoType.Mosasaurus);
        /*
         * EDIT VARIABLES PER DINOSAUR TYPE
         */
        this.adultAge = EnumDinoType.Mosasaurus.AdultAge;
        // Set initial size for hitbox. (length/width, height)
        this.setSize(1.5F, 0.5F);
        // Size of dinosaur at day 0.
        this.minSize = 1.0F;
        // Size of dinosaur at age Adult.
        this.maxSize = 3.0F;
        this.experienceValue = 5;
        
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, 1, true));
        this.tasks.addTask(7, new WaterDinoAIAttack(this, 2.0D));
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }
    
    public String getTexture()
    {
        if (this.isModelized())
        {
            return super.getModelTexture();
        }

        if (this.isAdult())
        {
            return "ere_geologique:textures/entity/Mosasaurus.png";
        }

        return "ere_geologique:textures/entity/Mosasaurus.png";
    }
    
    public String getDinosaurName()
    {
    	return EnumDinoType.Mosasaurus.name();
    }
    
    @Override
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
    	if(this.isInWater())
        return "ere_geologique:mosasaurus_living";
    	else
    	return "ere_geologique:mosasaurus_outside";	
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "ere_geologique:mosasaurus_hurt";
    }
    @Override
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "ere_geologique:mosasaurus_death";
    }

    /**
     * Returns true if the Entity AI code should be run
     *
     * Overriding because Mosasaur are dumb.
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength() + 1))
        {
            this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
    }

    protected void updateEntityActionState()
    {

    }

    @Override
    public boolean attackEntityAsMob(Entity victim)
    {
        float attackDamage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;

        if (victim instanceof EntityLivingBase)
        {
            attackDamage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) victim);
            knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) victim);
        }

        boolean attacked = victim.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);

        if (attacked)
        {
            if (knockback > 0)
            {
                double vx = -Math.sin(Math.toRadians(rotationYaw)) * knockback * 0.5;
                double vy = 0.1;
                double vz = Math.cos(Math.toRadians(rotationYaw)) * knockback * 0.5;
                victim.addVelocity(vx, vy, vz);
                motionX *= 0.6;
                motionZ *= 0.6;
            }

            if (victim instanceof EntityLivingBase)
            {
                EnchantmentThorns.func_92096_a(this, (EntityLivingBase) victim, rand);
            }

            setLastAttacker(victim);
        }

        return attacked;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 1.0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting.getDifficultyId() > 0;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	@Override
	public void writeSpawnData(ByteBuf buffer) {}

	@Override
	public void readSpawnData(ByteBuf additionalData) {}
}