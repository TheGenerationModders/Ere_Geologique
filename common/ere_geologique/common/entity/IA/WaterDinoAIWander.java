package ere_geologique.common.entity.IA;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

public class WaterDinoAIWander extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private float randomMotionSpeed;

    public WaterDinoAIWander(EntityCreature par1EntityCreature, double par2)
    {
        this.entity = par1EntityCreature;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
         if (this.entity.getRNG().nextInt(120) != 0)
        {
            return false;
        }
		return true;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {

                this.entity.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.entity.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.entity.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);

        this.entity.moveEntity(this.entity.motionX, this.entity.motionY, this.entity.motionZ);
    }
    
}