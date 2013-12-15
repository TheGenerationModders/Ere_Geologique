package ere_geologique.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.IA.DinoAIAttackOnCollide;
import ere_geologique.common.entity.IA.DinoAIEat;
import ere_geologique.common.entity.IA.DinoAIFollowOwner;
import ere_geologique.common.entity.IA.DinoAIWander;

public class Pachycephalosaurus extends Dinosaure
{
    private boolean looksWithInterest;
    /*public final float HuntLimit = (float)this.getHungerLimit() * 0.9F;
    private float field_25048_b;
    private float field_25054_c;
    private boolean isWolfShaking;
    private boolean field_25052_g;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    public int SubSpecies = 1;
    public boolean isBaby = true;*/
    public int RushTick = 0;
    //public int BreedTick = 3000;
    public boolean Running = false;
    private int attackTimer;

    public Pachycephalosaurus(World var1)
    {
        super(var1,EnumDinoType.Pachycephalosaurus);
        this.looksWithInterest = false;
        this.updateSize();
        
        
        /*
         * EDIT VARIABLES PER DINOSAUR TYPE
         */
        
        this.adultAge = EnumDinoType.Pachycephalosaurus.AdultAge;
        
        // Set initial size for hitbox. (length/width, height)
        this.setSize(1.0F, 1.5F);
        
        // Size of dinosaur at day 0.
        this.minSize = 1.0F;
        
        // Size of dinosaur at age Adult.
        this.maxSize = 3.0F;
        
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, TRex.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, Spinosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, Brachiosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(3, new DinoAIAttackOnCollide(this, 1.0D, true));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(4, new DinoAIFollowOwner(this, 1.0D, 5.0F, 2.0F));
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(21.0D);

    }
    
    @Override
    public boolean attackEntityAsMob(Entity var1)
    {
        this.attackTimer = 10;
        this.worldObj.setEntityState(this, (byte)4);
        if (this.rand.nextInt(16) < 9 && var1 instanceof EntityLiving)
        {
            this.headButt();
        }
        return super.attackEntityAsMob(var1);
    }
    
    public void knockBack(Entity var1, int var2, double var3, double var5)
    {
            super.knockBack(var1, var2, var3, var5);
    }
    
    
    private void headButt()
    {
        List var1 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D));

        if (!var1.isEmpty())
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.irongolem.throw", 6.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 1.0F) * 0.7F);

            for (int var2 = 0; var2 < var1.size(); ++var2)
            {
                EntityLiving var3 = (EntityLiving)var1.get(var2);
                double var4 = this.posX - var3.posX;
                double var6;

                for (var6 = this.posZ - var3.posZ; var4 * var4 + var6 * var6 < 1.0E-4D; var6 = (Math.random() - Math.random()) * 0.01D)
                {
                    var4 = (Math.random() - Math.random()) * 0.01D;
                }

                if (var3 != this)
                {
                    var3.knockBack(this, 0, var4 * 25.0D, var6 * 25.0D);
                }


            }
        }
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return this.riddenByEntity == null;
    }
    
    public String getTexture()
    {
        switch (this.getSubSpecies())
        {
            case 1:
                return "ere_geologique:textures/entity/Pachy-Lime.png";

            case 2:
                return "ere_geologique:textures/entity/Pachy-Monochrome.png";
                
            case 3:
                return "ere_geologique:textures/entity/Pachy-Pumpkin.png";

            default:
            	
                return "ere_geologique:textures/entity/Pachy-Pumpkin.png";
        }
    }
    
    public String getDinosaurName()
    {
          return EnumDinoType.Pachycephalosaurus.name();
    }
    
    @Override
    protected String getLivingSound()
    {
        return "ere_geologique:pachycephalosaurus_living";
    }
    
    @Override
    protected String getHurtSound()
    {
        return "ere_geologique:pachycephalosaurus_hurt";
    }
    
    @Override
    protected String getDeathSound()
    {
        return "ere_geologique:pachycephalosaurus_death";
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    /*public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        //var1.setInteger("SubSpecies", this.SubSpecies);
        //var1.setBoolean("Angry", this.isSelfAngry());
        //var1.setBoolean("isBaby", this.isBaby);
    }*/

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    /*public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        //this.SubSpecies = var1.getInteger("SubSpecies");
        //this.isBaby = var1.getBoolean("isBaby");
        this.CheckSkin();
        //this.setSelfAngry(var1.getBoolean("Angry"));
        //this.setSelfSitting(var1.getBoolean("Sitting"));
        //this.InitSize();
    }*/

    protected void updateEntityActionState()
    {
        if (this.riddenByEntity == null)
        {
            super.updateEntityActionState();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        if (this.attackTimer > 0)
        {
            --this.attackTimer;
        }
        
        if (this.looksWithInterest)
        {
            this.numTicksToChaseTarget = 10;
        }
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    /**
     * Disables a mob's ability to move on its own while true.
     */
    protected boolean isMovementCeased()
    {
        return this.isSitting();// || this.field_25052_g;
    }

   
    
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        Entity var3 = var1.getEntity();
        this.setSitting(false);


        if (var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow))
        {
            var2 = (var2 + 1) / 2;
        }

        if (!super.attackEntityFrom(var1, var2))
        {
            return false;
        }
        else
        {
            if (!this.isTamed() && !this.isSelfAngry())
            {
                if (var3 instanceof EntityPlayer)
                {
                    this.setSelfAngry(true);
                    this.entityToAttack = var3;
                }

                if (var3 instanceof EntityArrow && ((EntityArrow)var3).shootingEntity != null)
                {
                    var3 = ((EntityArrow)var3).shootingEntity;
                }

                if (var3 instanceof EntityLiving)
                {
                    ;
                }
            }
            else if (var3 != this && var3 != null)
            {
                if (this.isTamed() && var3 instanceof EntityPlayer && ((EntityPlayer)var3).username.equalsIgnoreCase(this.getOwnerName()))
                {
                    return true;
                }

                this.entityToAttack = var3;
            }

            return true;
        }
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.isSelfAngry() ? this.worldObj.getClosestPlayerToEntity(this, 16.0D) : null;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
    	//Add special item interaction code here
        return super.interact(var1);
    }
    public void handleHealthUpdate(byte var1)
    {
        if (var1 == 4)
        {
            this.attackTimer = 10;
        }
        if (var1 == 7)
        {
            this.showHeartsOrSmokeFX(true, true);
        }
        else if (var1 == 6)
        {
            this.showHeartsOrSmokeFX(false, false);
        }
        else if (var1 == 8)
        {
            //this.field_25052_g = true;
            //this.timeWolfIsShaking = 0.0F;
            //this.prevTimeWolfIsShaking = 0.0F;
        }
        else
        {
            super.handleHealthUpdate(var1);
        }
    }

    public boolean isSelfAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setSelfAngry(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 2)));
//            this.moveSpeed = 2.0F;
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -3)));
//            this.moveSpeed = 0.5F;
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    public boolean CheckSpace()
    {
        return !this.isEntityInsideOpaqueBlock();
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

    /**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity var1)
    {
        if (var1 instanceof EntityLiving && !(var1 instanceof EntityPlayer) && this.riddenByEntity != null && this.onGround)
        {
            this.onKillEntity((EntityLiving)var1);
            ((EntityLiving)var1).attackEntityFrom(DamageSource.causeMobDamage(this), 10);
        }
        else
        {

            super.applyEntityCollision(var1);

        }
    }
    
    

    public Pachycephalosaurus spawnBabyAnimal(EntityAgeable var1)
    {
        return new Pachycephalosaurus(this.worldObj);
    }

    /*public float getGLX()
    {
        return (float)(1.5D + 0.3D * (double)this.getDinoAge());
    }

    public float getGLY()
    {
        return (float)(1.5D + 0.3D * (double)this.getDinoAge());
    }*/

    /*public EntityAgeable func_90011_a(EntityAgeable var1)
    {
        return this.spawnBabyAnimal(var1);
    }*/
    
    @SideOnly(Side.CLIENT)
    public int getAttackTimer()
    {
        return this.attackTimer;
    }
    

	@Override
	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return null;
	}
}