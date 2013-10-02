package ere_geologique.common.entity.IA;

import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;
import ere_geologique.common.entity.Dinosaure;

public class DinoAITargetNonTamedExceptSelfClass extends EntityAITargetNonTamed
{
    public DinoAITargetNonTamedExceptSelfClass(EntityTameable var1, Class var2, float var3, int var4, boolean var5)
    {
        super(var1, var2, var4, var5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        boolean var1 = super.shouldExecute();
        return var1 && this.taskOwner instanceof Dinosaure && ((Dinosaure)this.taskOwner).SelfType == ((Dinosaure)this.taskOwner).SelfType ? false : var1;
    }
}