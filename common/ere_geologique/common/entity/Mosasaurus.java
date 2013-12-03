package ere_geologique.common.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import ere_geologique.common.entity.Enums.EnumDinoType;

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
    }
    
    public String getTexture()
    {
        if (this.isModelized())
            return super.getModelTexture();
        if(this.isAdult())
            return "ere_geologique:textures/entity/Mosasaurus.png";
		return "ere_geologique:textures/entity/Mosasaurus.png";
    }
    
    /**
     * Returns true if the Entity AI code should be run
     * 
     * Overriding because Mosasaur are dumb.
     */
    @Override
    public boolean isAIEnabled()
    {
    	return false;
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
            if (par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength() +1))
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
    }

    
    
    protected void updateEntityActionState()
    {
    	super.updateEntityActionState();
        double d4 = 64.0D;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
        	if (this.isInWater())
        	{
                this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.waypointY = this.posY - (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
                this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        	}
        	else
        	{
        		this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        		this.waypointY = this.posY - (double)((this.rand.nextFloat() * 2.0F) * 1.0F);
        		this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        	}
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.motionX += d0 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }

            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);


        if (this.isInWater() && this.targetedEntity != null && this.targetedEntity.isInWater() 
        		&&  this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
        {
        	// Simple "pathfinding" to attack closest player.
        	this.deltaX = this.targetedEntity.posX - this.posX;
        	this.deltaY = this.targetedEntity.posY - this.posY;
        	this.deltaZ = this.targetedEntity.posZ - this.posZ;
        	this.length = Math.sqrt( deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
        	
        	//Normalize?
        	deltaX /= length + 1.5D;
        	deltaY /= length + 1.5D;
        	deltaZ /= length + 1.5D;
        	
        	//Set waypoint for player's current location.
            this.waypointX += deltaX;
            this.waypointY += deltaY;
            this.waypointZ += deltaZ;
            
            //Now move.
            double d5 = this.targetedEntity.posX - this.posX;
            double d6 = this.targetedEntity.posY - this.posY;
            double d7 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

            if (this.canEntityBeSeen(this.targetedEntity))
            {
           // 	this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            	this.worldObj.playSoundAtEntity((EntityPlayer)null, "ere_geologique:mosasaurus_attack", 1F, 1F);
            	Vec3 vec3 = this.getLook(1.0F);
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
        }
    }

    /**
     * True if the Mosasaur has an unobstructed line of travel to the waypoint.
     */
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (this.waypointX - this.posX) / par7;
        double d5 = (this.waypointY - this.posY) / par7;
        double d6 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double)i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty() || !this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "ere_geologique:mosasaurus_living";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "ere_geologique:mosasaurus_hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "ere_geologique:mosasaurus_death";
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
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting > 0;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
}