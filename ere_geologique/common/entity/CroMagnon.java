package ere_geologique.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import ere_geologique.common.config.EGProperties;
 
public class CroMagnon extends EntityMob
    {
    public CroMagnon(World par1World)
    {
        super(par1World);
        this.texture = "/ere_geologique/textures/entity/Homme.png";
        this.moveSpeed = 0.25F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
    }
 
    public int getMaxHealth()
    {
        return 20;
    }
 
    public int getAttackStrength(Entity par1Entity)
    {
    return 4;
    }
 
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    protected boolean isAIEnabled()
    {
        return true;
    }
    protected int getDropItemId()
    {
        return EGProperties.FougereSwordID;
    }
 
    protected void dropRareDrop(int par1)
    {
        switch (this.rand.nextInt(2))
        {
            case 0:
                this.dropItem(EGProperties.PrehistoriqueCoalID, 1);
                break;
            case 1:
                this.dropItem(EGProperties.FougereLeggingsID, 1);
                break;
            case 2:
                this.dropItem(EGProperties.FougereChestplateID, 1);
                break;
        }
    }
}