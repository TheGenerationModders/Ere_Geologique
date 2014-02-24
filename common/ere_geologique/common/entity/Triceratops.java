package ere_geologique.common.entity;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.IA.DinoAIEat;
import ere_geologique.common.entity.IA.DinoAIFollowOwner;
import ere_geologique.common.entity.IA.DinoAIRideGround;
import ere_geologique.common.entity.IA.DinoAIWander;

public class Triceratops extends Dinosaure
{
    private boolean looksWithInterest;
    public boolean Running = false;

    final EntityAIControlledByPlayer aiControlledByPlayer;

    public Triceratops(World var1)
    {
        super(var1, EnumDinoType.Triceratops);
        this.looksWithInterest = false;
        this.updateSize();
        this.setSubSpecies((new Random()).nextInt(3) + 1);
        /*
         * EDIT VARIABLES PER DINOSAUR TYPE
         */
        this.adultAge = EnumDinoType.Triceratops.AdultAge;
        // Set initial size for hitbox. (length/width, height)
        this.setSize(0.8F, 0.8F);
        // Size of dinosaur at day 0.
        this.minSize = 1.0F;
        // Size of dinosaur at age Adult.
        this.maxSize = 8.0F;
        this.healthModValue = 1;
        this.damageModValue = 1;
        this.speedModValue = 0;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.2F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 5.0F, 2.0F, 2.0F));
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(8, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
        tasks.addTask(1, new DinoAIRideGround(this, 1)); // mutex all
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
    }

    /**
     * Return the AI task for player control.
     */
    public EntityAIControlledByPlayer getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(21.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        if (this.isModelized())
        {
            return super.getModelTexture();
        }

        if (this.isAdult())
        {
            switch (this.getSubSpecies())
            {
                case 1:
                    return "ere_geologique:textures/entity/Triceratops_Adult_1.png";

                case 2:
                    return "ere_geologique:textures/entity/Triceratops_Adult_2.png";

                case 3:
                    return "ere_geologique:textures/entity/Triceratops_Adult_3.png";

                default:
                    return "ere_geologique:textures/entity/Triceratops_Adult_1.png";
            }
        }

        if (this.isTeen())
        {
            switch (this.getSubSpecies())
            {
                case 1:
                    return "ere_geologique:textures/entity/Triceratops_Teen_1.png";

                case 2:
                    return "ere_geologique:textures/entity/Triceratops_Teen_2.png";

                case 3:
                    return "ere_geologique:textures/entity/Triceratops_Teen_3.png";

                default:
                    return "ere_geologique:textures/entity/Triceratops_Teen_1.png";
            }
        }

        switch (this.getSubSpecies())
        {
            case 1:
                return "ere_geologique:textures/entity/Triceratops_Baby_1.png";

            case 2:
                return "ere_geologique:textures/entity/Triceratops_Baby_2.png";

            case 3:
                return "ere_geologique:textures/entity/Triceratops_Baby_3.png";

            default:
                return "ere_geologique:textures/entity/Triceratops_Baby_1.png";
        }
    }
    
    public String getDinosaurName()
    {
    	return EnumDinoType.Triceratops.name();
    }
    
    @Override
    protected String getLivingSound()
    {
        return "ere_geologique:triceratops_living";
    }
    
    @Override
    protected String getHurtSound()
    {
        return "ere_geologique:triceratops_hurt";
    }
    
    @Override
    protected String getDeathSound()
    {
        return "ere_geologique:triceratops_death";
    }

    /*public float getInterestedAngle(float var1)
    {
        return (this.field_25054_c + (this.field_25048_b - this.field_25054_c) * var1) * 0.15F * (float)Math.PI;
    }*/

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
        return this.isSitting();
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

    public boolean isSelfAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    /*public boolean isSelfSitting()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }*/

    public void setSelfAngry(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 2)));
            //        this.moveSpeed = 2.0F;
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -3)));
            //         this.moveSpeed = 0.5F;
        }
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
            this.riddenByEntity.setPosition(this.posX, this.posY + (double)this.height * 0.65D + 0.07D * (double)(12 - this.getDinoAge()), this.posZ);
        }
    }

    public Triceratops spawnBabyAnimal(EntityAgeable var1)
    {
        return new Triceratops(this.worldObj);
    }

    private boolean FindFren(int var1)
    {
        float var2 = (float)(var1 * 2);
        int var3 = 0;
        int var4 = 0;
        int var5 = 0;
        int var6;
        int var7;

        for (var6 = -var1; var6 <= var1; ++var6)
        {
            for (var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -var1; var8 <= var1; ++var8)
                {
                    if (this.worldObj.getBlock((int)Math.round(this.posX + (double)var6), (int)Math.round(this.posY + (double)var7), (int)Math.round(this.posZ + (double)var8)) == EGBlockList.sapling && var2 > this.GetDistanceWithXYZ(this.posX + (double)var6, this.posY + (double)var7, this.posZ + (double)var8))
                    {
                        var2 = this.GetDistanceWithXYZ(this.posX + (double)var6, this.posY + (double)var7, this.posZ + (double)var8);
                        var3 = var6;
                        var4 = var7;
                        var5 = var8;
                    }
                }
            }
        }

        if (var2 == (float)(var1 * 2))
        {
            return false;
        }
        else if (Math.sqrt((double)(var3 ^ 2 + var4 ^ 2 + var5 ^ 2)) >= 2.0D)
        {
            this.setPathToEntity(this.worldObj.getEntityPathToXYZ(this, (int)Math.round(this.posX + (double)var3), (int)Math.round(this.posY + (double)var4), (int)Math.round(this.posZ + (double)var5), 10.0F, true, false, true, false));
            return true;
        }
        else
        {
            this.FaceToCoord((int)(-(this.posX + (double)var3)), (int)(this.posY + (double)var4), (int)(-(this.posZ + (double)var5)));
            this.increaseHunger(10);

            for (var6 = -1; var6 <= 1; ++var6)
            {
                for (var7 = -1; var7 <= 1; ++var7)
                {
                    if (this.worldObj.getBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4), (int)Math.round(this.posZ + (double)var5 + (double)var7)) == EGBlockList.sapling)
                    {
                        this.worldObj.playAuxSFX(2001, (int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4), (int)Math.round(this.posZ + (double)var5 + (double)var7), Blocks.tallgrass);
                        this.worldObj.setBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4), (int)Math.round(this.posZ + (double)var5 + (double)var7), 0);

                        if (this.worldObj.getBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4) + 1, (int)Math.round(this.posZ + (double)var5 + (double)var7)) == EGBlockList.sapling)//fernUpper
                        {
                            this.worldObj.setBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4) + 1, (int)Math.round(this.posZ + (double)var5 + (double)var7), 0);
                        }

                        if (this.worldObj.getBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4) - 1, (int)Math.round(this.posZ + (double)var5 + (double)var7)) == Blocks.grass)
                        {
                            this.worldObj.setBlock((int)Math.round(this.posX + (double)var3 + (double)var6), (int)Math.round(this.posY + (double)var4) - 1, (int)Math.round(this.posZ + (double)var5 + (double)var7), Blocks.dirt);
                        }
                    }
                }

                this.heal(3);
                this.setPathToEntity((PathEntity)null);
            }

            return true;
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1)
    {
        return null;
    }

	@Override
	public void writeSpawnData(ByteBuf buffer) {}

	@Override
	public void readSpawnData(ByteBuf additionalData) {}
}