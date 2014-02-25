package ere_geologique.common.entity;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.entity.enums.EnumDinoType;
import ere_geologique.common.entity.enums.EnumSituation;
import ere_geologique.common.entity.ia.DinoAIAttackOnCollide;
import ere_geologique.common.entity.ia.DinoAIEat;
import ere_geologique.common.entity.ia.DinoAIFollowOwner;
import ere_geologique.common.entity.ia.DinoAIRideGround;
import ere_geologique.common.entity.ia.DinoAIWander;
import ere_geologique.common.gui.GuiPedia;

public class Pterosaure extends Dinosaure
{
    //protected final int AGE_LIMIT = 8;
    //public final float HuntLimit = (float)(this.getHungerLimit() * 4 / 5);
    private boolean looksWithInterest;
    /*private float field_25048_b;
    private float field_25054_c;
    private boolean isWolfShaking;
    private boolean field_25052_g;
    public ItemStack ItemInMouth = null;*/
    public int LearningChestTick = 900;
    // public int SubType = 0;
    //public int BreedTick = 3000;
    public float AirSpeed = 0.0F;//Speed of the DinoAIControlledByPlayer instance
    public float AirAngle = 0.0F;
    public float AirPitch = 0.0F;
    public float WingState = 0.0F;
    public int wingpause = 0;
    public boolean Landing = false;

    final EntityAIControlledByPlayer aiControlledByPlayer;

    //public static final int AIR_ANGLE_INDEX = 26;
    //public static final int AIR_PITCH_INDEX = 27;
    //public static final int WING_STATE_INDEX = 25;

    public Pterosaure(World var1)
    {
        super(var1, EnumDinoType.Pterosaure);
        this.looksWithInterest = false;
        this.updateSize();
        /*
         * EDIT VARIABLES PER DINOSAUR TYPE
         */
        this.adultAge = EnumDinoType.Pterosaure.AdultAge;
        // Set initial size for hitbox. (length/width, height)
        this.setSize(1.0F, 1.0F);
        // Size of dinosaur at day 0.
        this.minSize = 1.0F;
        // Size of dinosaur at age Adult.
        this.maxSize = 4.0F;
        //  this.tasks.addTask( 0, new DinoAIFlying( this ));
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(0, new DinoAIGrowup(this, 8));
        //this.tasks.addTask(0, new DinoAIStarvation(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        //this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityTRex.class, 8.0F, 0.3F, 0.35F));
        //this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityBrachiosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(3, new DinoAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 5.0F, 2.0F, 2.0F));
        //this.tasks.addTask(6, new DinoAIUseFeeder(this, 24/*, this.HuntLimit*/, EnumDinoEating.Carnivorous));
        /*this.tasks.addTask(6, new DinoAIPickItem(this, Item.fishRaw, this.moveSpeed, 24, this.HuntLimit));
        this.tasks.addTask(6, new DinoAIPickItem(this, Item.fishCooked, this.moveSpeed, 24, this.HuntLimit));
        this.tasks.addTask(6, new DinoAIPickItem(this, Fossil.sjl, this.moveSpeed * 2.0F, 24, this.HuntLimit));*/
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        tasks.addTask(1, new DinoAIRideGround(this, 1)); // mutex all
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
        this.inWater = true;
    }

    /**
     * Return the AI task for player control.
     */
    public EntityAIControlledByPlayer getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
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
                return "ere_geologique:textures/entity/Pterosaure.png";
        }
    }
    
    public String getDinosaurName()
    {
    	return EnumDinoType.Pterosaure.name();
    }
    
    @Override
    protected String getLivingSound()
    {
        return "ere_geologique:pterosaurus_living";
    }
    
    @Override
    protected String getHurtSound()
    {
        return "ere_geologique:pterosaurus_hurt";
    }
    
    @Override
    protected String getDeathSound()
    {
        return "ere_geologique:pterosaurus_death";
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(21.0D);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /*protected void entityInit()
    {
        super.entityInit();
        //this.dataWatcher.addObject(WING_STATE_INDEX, new Integer(0));
        //this.dataWatcher.addObject(AIR_ANGLE_INDEX, new Integer(0));
        //this.dataWatcher.addObject(AIR_PITCH_INDEX, new Integer(0));
    }*/

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("LearningChestTick", this.LearningChestTick);
        //var1.setBoolean("Angry", this.isSelfAngry());
        var1.setFloat("Airspeed", this.AirSpeed);
        var1.setFloat("AirAngle", this.AirAngle);
        var1.setFloat("AirPitch", this.AirPitch);
        var1.setFloat("WingState", this.WingState);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        //this.setSelfAngry(var1.getBoolean("Angry"));
        //this.setSelfSitting(var1.getBoolean("Sitting"));
        /*if (var1.hasKey("SubType"))
        {
            this.SubType = var1.getInteger("SubType");
        }*/
        //this.InitSize();
        this.LearningChestTick = var1.getInteger("LearningChestTick");
        this.AirSpeed = var1.getFloat("Airspeed");
        this.AirAngle = var1.getFloat("AirAngle");
        this.AirPitch = var1.getFloat("AirPitch");
        this.WingState = var1.getFloat("WingState");
    }

    /*public float getAirAngle()
    {return (float)(this.dataWatcher.getWatchableObjectInt(AIR_ANGLE_INDEX)/10000.0F);}

    public void setAirAngle(float var1)
    {this.dataWatcher.updateObject(AIR_ANGLE_INDEX, Integer.valueOf((int)(var1*10000F)));}

    public float getAirPitch()
    {return (float)(this.dataWatcher.getWatchableObjectInt(AIR_PITCH_INDEX)/10000.0F);}

    public void setAirPitch(float var1)
    {this.dataWatcher.updateObject(AIR_PITCH_INDEX, Integer.valueOf((int)(var1*10000F)));}

    public float getWingState()
    {return (float)(this.dataWatcher.getWatchableObjectInt(WING_STATE_INDEX)/10000.0F);}

    public void setWingState(float var1)
    {this.dataWatcher.updateObject(WING_STATE_INDEX, Integer.valueOf((int)(var1*10000F)));}*/

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        //if(this.riddenByEntity!=null && !this.onGround && !this.inWater)
        //this.getWingState();
        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.LearningChestTick > 0 && this.isNearbyChest() && this.isAdult())
        {
            this.LearningChestTick--;

            if (this.LearningChestTick == 0)
            {
                this.SendStatusMessage(EnumSituation.LearningChest);    //, this.SelfType);
            }
        }

        /*this.field_25054_c = this.field_25048_b;

        if (this.looksWithInterest)
        {
            this.field_25048_b += (1.0F - this.field_25048_b) * 0.4F;
        }
        else
        {
            this.field_25048_b += (0.0F - this.field_25048_b) * 0.4F;
        }*/

        if (this.looksWithInterest)
        {
            this.numTicksToChaseTarget = 10;
        }
    }
    public boolean hasLearnedChest()
    {
        return this.LearningChestTick == 0;
    }
    private boolean isNearbyChest()
    {
        TileEntity var5 = null;

        for (int var6 = -10; var6 <= 10; ++var6)
        {
            for (int var7 = 0; var7 <= 3; ++var7)
            {
                for (int var8 = -10; var8 <= 10; ++var8)
                {
                    var5 = this.worldObj.getTileEntity((int)(this.posX + (double)var6), (int)(this.posY + (double)var7), (int)(this.posZ + (double)var8));

                    if (var5 instanceof TileEntityChest)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
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
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.isAngry() ? this.worldObj.getClosestPlayerToEntity(this, 16.0D) : null;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        return this.modelizedDrop() ? true : super.attackEntityFrom(var1, var2);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity var1, float var2)
    {
        if (var2 > 2.0F && var2 < 5.0F && this.rand.nextInt(10) == 0)
        {
            if (this.onGround)
            {
                double var3 = var1.posX - this.posX;
                double var5 = var1.posZ - this.posZ;
                float var7 = MathHelper.sqrt_double(var3 * var3 + var5 * var5);
                this.motionX = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.jump();
            }
        }
        else if ((double)var2 < 1.899999976158142D && var1.boundingBox.maxY > this.boundingBox.minY && var1.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            var1.attackEntityFrom(DamageSource.causeMobDamage(this), 2 + this.getDinoAge());
        }
    }

    public void updateRiderPosition()
    {
        float var1 = -this.height;

        if (this.riddenByEntity != null)
        {
            int heightt = 10;

            for (int i = 1; i < 10; i++)
            {
                if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - i, MathHelper.floor_double(this.posZ)).isBlockNormalCube())
                {
                    heightt = i;
                    break;
                }
            }

            boolean onlyAjump = heightt < 3;

            if (this.onGround || this.isInWater() || onlyAjump)
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1 * 1.1D, this.posZ);
            }
            else if (this.Landing)
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1, this.posZ);
            }
            else
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1 * 0.6D, this.posZ);
                //this.riddenByEntity.setAngles(this.rotationYaw, this.AirPitch);
            }
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    /*public boolean interact(EntityPlayer var1)
    {
    	//Add special item interaction code here
        return super.interact(var1);
    }*/

    /**
     * Called when the mob is falling. Calculates and applies fall damage.

    protected void fall(float var1)
    {
        if (this.riddenByEntity != null && !this.Landing)
        {
            this.riddenByEntity.fallDistance = var1;
        }

        int var2 = (int)Math.ceil((double)(var1 - 3.0F));

        if (!this.worldObj.isRemote)
        {
            if (var2 > 0)
            {
                int var3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset), MathHelper.floor_double(this.posZ));

                if (var3 > 0)
                {
                    StepSound var4 = Block.blocksList[var3].stepSound;
                    this.worldObj.playSoundAtEntity(this, var4.getBreakSound(), var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
                }
            }
        }
    } */

    public boolean CheckSpace()
    {
        return !this.isEntityInsideOpaqueBlock();
    }

    @SideOnly(Side.CLIENT)
    public void ShowPedia(GuiPedia p0)
    {
        super.ShowPedia(p0);

        if (this.LearningChestTick == 0)
        {
            p0.AddStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_TEXT_CHEST), true);
        }

        if (this.isAdult())
        {
            p0.AddStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_TEXT_FLY), true);
        }
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    public void jump()
    {
        this.motionY = 0.5D;
    }

    public float HandleRiding(float Speed, float SpeedBoosted)
    {
        //if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityClientPlayerMP)
        //{
        //    EntityClientPlayerMP var1 = (EntityClientPlayerMP)this.riddenByEntity;
        int height = 10;

        for (int i = 1; i < 10; i++)
        {
            if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - i, MathHelper.floor_double(this.posZ)).isBlockNormalCube())
            {
                height = i;
                break;
            }
        }

        boolean onlyAjump = height < 3;
        //System.out.println("H: "+String.valueOf(heightt));
        //System.out.println("L: "+String.valueOf(this.Landing));

        if (!this.onGround && !this.inWater && !onlyAjump && Speed > 0F)
        {
            if (this.AirSpeed == -1.0F)
            {
                this.AirPitch = -10.0F;
                this.AirSpeed = Speed * 0.12F;
            }

            this.Landing = this.RiderSneak;
            /*if (!this.isCollidedVertically)
                if (!this.Landing && this.AirPitch == 40.0F)
                    this.Landing = true;
            else
                this.Landing = false;*/

            if (this.RiderStrafe != 0.0F)
            {
                this.AirAngle -= this.RiderStrafe;
            }
            else
            {
                if (this.AirAngle > 0.0)
                {
                    this.AirAngle -= 0.5F;
                }

                if (this.AirAngle < 0.0)
                {
                    this.AirAngle += 0.5F;
                }

                if (this.AirAngle > -0.5F && this.AirAngle < 0.5F)
                {
                    this.AirAngle = 0.0F;
                }
            }

            if (this.AirAngle > 30.0F)
            {
                this.AirAngle = 30.0F;
            }

            if (this.AirAngle < -30.0F)
            {
                this.AirAngle = -30.0F;
            }

            //if (Math.abs(this.AirAngle) > 10.0F)
            //this.rotationYaw += (float)(this.AirAngle > 0.0F ? 1 : -1);
            this.rotationYaw += this.AirAngle / 20.0F;
            /*while (this.rotationYaw < -180.0F)
                this.rotationYaw += 360.0F;

            while (this.rotationYaw >= 180.0F)
                this.rotationYaw -= 360.0F;*/
            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

            if (this.Landing)
            {
                this.AirPitch = 10.0F;
                this.AirSpeed = 1.5F * this.getAIMoveSpeed();

                if (!this.isCollidedVertically)
                {
                    this.motionY = -0.2D;
                }
                else
                {
                    this.motionY = 0.0D;
                }

                this.motionX = -this.AirSpeed * MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F);
                this.motionZ = this.AirSpeed * MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F);
                this.posX += this.motionX;
                this.posY += this.motionY;
                this.posZ += this.motionZ;
                //this.setMoveForward(this.AirSpeed);
            }
            else
            {
                if ((this.isCollidedHorizontally || this.isCollidedVertically) && this.AirSpeed != 0.0F)
                {
                    this.AirSpeed = 0.0F;
                    this.motionY = -0.2F;
                    this.posY = -0.2F;
                    //this.setMoveForward(0.0F);
                    return 0;
                }

                /*if (this.AirSpeed == 0.0F && this.moveForward != 0.0F)
                {
                    this.AirSpeed = this.moveForward * this.moveSpeed;
                }*/

                if (this.RiderForward != 0.0F)
                {
                    this.AirPitch -= this.RiderForward * 0.5F;
                }
                else
                {
                    if (this.AirPitch < 0.0F)
                    {
                        this.AirPitch += 0.5F;
                    }

                    if (this.AirPitch > 0.0F)
                    {
                        this.AirPitch -= 0.5F;
                    }

                    if (this.AirPitch > -0.5F && this.AirPitch < 0.5F)
                    {
                        this.AirPitch = 0.0F;
                    }
                }

                if (this.AirPitch < -25.0F || (this.AirSpeed > this.getAIMoveSpeed() * 2.5 / MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F) && this.AirPitch < 0F) || this.wingpause > 0)
                {
                    if (this.wingpause > 0)
                    {
                        this.wingpause--;
                    }

                    if (this.WingState != 90.0F)
                    {
                        if (this.WingState >= 90.0F)
                        {
                            this.WingState -= 1.0F;
                        }
                        else
                        {
                            this.WingState += 1.0F;
                        }

                        if (this.WingState < 92.0F || this.WingState > 88F)
                        {
                            this.WingState = 90.0F;
                        }
                    }
                }
                else
                {
                    if (this.wingpause > 0 && this.AirPitch > 5.0F)
                    {
                        this.wingpause = 0;
                    }

                    if (this.wingpause == 0)
                    {
                        this.WingState += 3.0F + 7.0F * MathHelper.sin((this.AirPitch + 30.0F) * (float)Math.PI / 180.0F) * (SpeedBoosted > 0F ? 2F : 1F);

                        if (this.WingState > 85.0F && SpeedBoosted == 0.0F && this.WingState < 95.0F && this.AirPitch <= 5.0F && (new Random()).nextInt(10000) < MathHelper.floor_float(1000F - 400F / 3F * (this.AirPitch - 5F)))
                        {
                            this.wingpause = 25 + (new Random()).nextInt(MathHelper.floor_float(10F - 4F / 3F * (this.AirPitch - 5F)));
                        }

                        if (this.WingState > 180.0F)
                        {
                            this.WingState -= 180.0F;
                        }
                    }
                    else
                    {
                        this.wingpause--;
                    }
                }

                if (this.AirPitch > 40.0F)
                {
                    this.AirPitch = 40.0F;
                }

                if (this.AirPitch < -60.0F)
                {
                    this.AirPitch = -60.0F;
                }

                float var5 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
                float var6 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);

                //this.AirSpeed=this.getSpeed()*2.5F*MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F);

                if (this.AirSpeed > this.getAIMoveSpeed() * 2.5 * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F) && this.AirPitch >= 0F) //slow down when rising
                {
                    this.AirSpeed *= 0.99F;
                }

                if (this.AirSpeed > this.getAIMoveSpeed() * 2.5 / MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F) && this.AirPitch < 0F) //slow down when too fast
                {
                    this.AirSpeed *= 0.995F;
                }

                if (this.AirSpeed < this.getAIMoveSpeed() * 2.5 * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F))
                {
                    this.AirSpeed *= 1.01F;
                }

                if (this.AirPitch < 0F)
                {
                    this.AirSpeed -= MathHelper.sin(this.AirPitch * (float)Math.PI / 180.0F) * 0.005F;    //get faster
                }

                this.motionX = -this.AirSpeed * (SpeedBoosted > 0 ? 1.5F : 1F) * MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F);
                this.motionZ = this.AirSpeed * (SpeedBoosted > 0 ? 1.5F : 1F) * MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(this.AirPitch * (float)Math.PI / 180.0F);
                this.motionY =/*0.20000000298023224D+*/this.AirSpeed * (SpeedBoosted > 0 ? 1.5F : 1F) * MathHelper.sin(this.AirPitch * (float)Math.PI / 180.0F);
                //System.out.println("AirSpeed: "+String.valueOf(this.AirSpeed));
                /*System.out.println("AirAngle: "+String.valueOf(this.AirAngle));
                System.out.println("AirPitch: "+String.valueOf(this.AirPitch));
                System.out.println("W: "+String.valueOf(this.rotationYaw));
                System.out.println("X: "+String.valueOf(this.motionX));*/
                //System.out.println("Y: "+String.valueOf(this.motionY));
                /*System.out.println("Z: "+String.valueOf(this.motionZ));
                System.out.println("T: "+String.valueOf(this.ticksExisted));*/
                this.posX += this.motionX;
                this.posY += this.motionY;
                this.posZ += this.motionZ;
                /*if (this.AirPitch < 60.0F && this.moveForward > 0.1F)
                {
                    this.motionY = Math.sin((double)var2) * 0.4D;
                }*/
            }

            ByteArrayOutputStream var3 = new ByteArrayOutputStream();
            DataOutputStream var4 = new DataOutputStream(var3);

            try
            {
                var4.writeInt(this.getEntityId());
                var4.writeFloat(this.AirAngle);
                var4.writeFloat(this.AirPitch);
                var4.writeFloat(this.WingState);
                //System.out.println("WingPause: "+String.valueOf(this.wingpause));
            }
            catch (Exception e)
            {
                System.err.println("ERROR WHILE WRITING Ptero Flying Data to Packet");
            }

            //((EntityPlayerMP)this.riddenByEntity).playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("PteroFlight",var3.toByteArray()));
            //System.out.println("SERVER:"+String.valueOf(this.WingState));
            ((WorldServer)this.riddenByEntity.worldObj).getEntityTracker().func_151247_a(this.riddenByEntity, new C17PacketCustomPayload("PteroFlight", var3.toByteArray()));
        }
        else
        {
            this.AirPitch = 0;
            this.AirAngle = 0;
            this.AirSpeed = -1F; //Indicates that the dino was on the ground last

            /*if (this.AirSpeed != 0.0F)
            {
                this.AirSpeed = 0.0F;
            }

            if (this.AirAngle != 0.0F)
            {
                this.AirAngle = 0.0F;
            }

            if (this.AirPitch != 0.0F)
            {
                this.AirPitch = 0.0F;
            }

            for (this.rotationYaw -= this.RiderStrafe * 5.0F; this.rotationYaw < -180.0F; this.rotationYaw += 360.0F)
            {
                ;
            }

            while (this.rotationYaw >= 180.0F)
            {
                this.rotationYaw -= 360.0F;
            }

            this.setMoveForward(this.RiderForward * this.moveSpeed);*/
            if (this.RiderForward > 0)
            {
                Speed += (this.getAIMoveSpeed() * 2.0F - Speed) * 0.3F * this.RiderForward;
            }
            else if (Speed > 0)
            {
                Speed += (this.getAIMoveSpeed() * 2.0F - Speed) * 0.8F * this.RiderForward; //Break faster

                if (Speed < 0)
                {
                    Speed = 0;
                }
            }

            //else
            //	Speed += (this.getSpeed()*2.0F - Speed) * 0.3F*this.RiderForward;
            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.RiderStrafe * 5.0F);

            if (this.RiderJump)
            {
                this.getJumpHelper().setJumping();
            }

            //this.motionY-=0.08;
            this.moveEntityWithHeading(0.0F, Speed + Speed * (0.3F + 0.85F * MathHelper.sin(SpeedBoosted * (float)Math.PI)));
        }

        //}
        //this.setAirAngle(this.AirAngle);
        //this.setAirPitch(this.AirPitch);
        //this.setWingState(this.WingState);
        return Speed;
    }
    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround

    protected void updateFallState(double par1, boolean par3) {}
      */

    public Pterosaure spawnBabyAnimal(EntityAgeable var1)
    {
        return new Pterosaure(this.worldObj);
    }

    /* public void updateSize()
     {
         this.setSize((float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge())), (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge())));
     }

     public float getGLX()
     {
         return (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge()));
     }

     public float getGLY()
     {
         return (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge()));
     }*/

    /*public EntityAgeable func_90011_a(EntityAgeable var1)
    {
        return this.spawnBabyAnimal(var1);
    }*/

    @Override
    public EntityAgeable createChild(EntityAgeable var1)
    {
        return this.spawnBabyAnimal(var1);
    }

	@Override
	public void writeSpawnData(ByteBuf buffer) {}

	@Override
	public void readSpawnData(ByteBuf additionalData) {}
}