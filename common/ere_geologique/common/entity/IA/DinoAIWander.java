package ere_geologique.common.entity.ia;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.entity.enums.EnumOrderType;

public class DinoAIWander extends EntityAIBase
{
    private Dinosaure entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;

    public DinoAIWander(Dinosaure var1, double par2)
    {
        this.entity = var1;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.entity.OrderStatus == null)
        {
            this.entity.OrderStatus = EnumOrderType.FreeMove;
        }

        if (this.entity.getRNG().nextInt(20) != 0)
        {
            return false;
        }
        else if (this.entity.getOwnerName() != null && this.entity.worldObj.getPlayerEntityByName(this.entity.getOwnerName()) != null && this.entity.OrderStatus != EnumOrderType.FreeMove)
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.xPosition = vec3.xCoord;
                this.yPosition = vec3.yCoord;
                this.zPosition = vec3.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}
