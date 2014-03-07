package ere_geologique.common.entity.ia;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import ere_geologique.common.entity.Dinosaure;

public class WaterDinoAIWander extends EntityAIBase
{
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    
    private Dinosaure entity;
    
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private float randomMotionSpeed;
    
    private Entity targetedEntity;
    
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;
    private double length;
    
    private Random rand = new Random();
    
    private World worldObj;

    public WaterDinoAIWander(Dinosaure dinosaure, double speed)
    {
        this.entity = dinosaure;
        this.speed = speed;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	return !this.entity.isDead;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entity.isDead;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void updateTask()
    {
        
        double d4 = 64.0D;
        double d0 = this.waypointX - this.entity.posX;
        double d1 = this.waypointY - this.entity.posY;
        double d2 = this.waypointZ - this.entity.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.waypointX = this.entity.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.waypointY = this.entity.posY - (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 4.0F);
                this.waypointZ = this.entity.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            }
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.entity.motionX += d0 / d3 * 0.1D;
                this.entity.motionZ += d2 / d3 * 0.1D;
                this.entity.motionY += d1 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.entity.posX;
                this.waypointY = this.entity.posY;
                this.waypointZ = this.entity.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }

        //this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
        this.targetedEntity = this.entity.worldObj.getClosestVulnerablePlayerToEntity(this.entity, 20.0D);

        if (this.entity.isInWater() && this.targetedEntity != null && this.targetedEntity.isInWater()
                &&  this.targetedEntity.getDistanceSqToEntity(this.entity) < d4 * d4)
        {
            // Simple "pathfinding" to attack closest player.
            this.deltaX = this.targetedEntity.posX - this.entity.posX;
            this.deltaY = this.targetedEntity.posY - this.entity.posY;
            this.deltaZ = this.targetedEntity.posZ - this.entity.posZ;
            this.length = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            //Normalize?
            deltaX /= length + 1.0D;
            deltaY /= length + 1.0D;
            deltaZ /= length + 1.0D;
            //Set waypoint for player's current location.
            this.waypointX += deltaX;
            this.waypointY += deltaY;
            this.waypointZ += deltaZ;
            //Now move.
            double d5 = this.targetedEntity.posX - this.entity.posX;
            double d6 = this.targetedEntity.posY - this.entity.posY;
            double d7 = this.targetedEntity.posZ - this.entity.posZ;
            this.entity.renderYawOffset = this.entity.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

            if (this.entity.canEntityBeSeen(this.targetedEntity))
            {
                // 	this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.entity.posY, (int)this.entity.posZ, 0);
                this.entity.worldObj.playSoundAtEntity((EntityPlayer)null, "fossil:mosasaurus_attack", 1F, 1F);
                Vec3 vec3 = this.entity.getLook(1.0F);
            }
        }
        else
        {
            this.entity.renderYawOffset = this.entity.rotationYaw = -((float)Math.atan2(this.entity.motionX, this.entity.motionZ)) * 180.0F / (float)Math.PI;
        }
    }
    

    /**
     * True if the Mosasaur has an unobstructed line of travel to the waypoint.
     */
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (this.waypointX - this.entity.posX) / par7;
        double d5 = (this.waypointY - this.entity.posY) / par7;
        double d6 = (this.waypointZ - this.entity.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.entity.boundingBox.copy();
        axisalignedbb.offset(d4, d5, d6);

    	while (this.entity.worldObj.isAABBInMaterial(axisalignedbb, Material.air))
    	{
        axisalignedbb.offset(d4, d5, d6);
        d5 -= 1;      		
    	}
    	
        for (int i = 1; (double)i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);
            if (!this.entity.worldObj.getCollidingBoundingBoxes(entity, axisalignedbb).isEmpty())
            {
                return false;
            }
        }
        return true;
    }
}