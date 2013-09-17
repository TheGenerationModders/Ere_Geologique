package ere_geologique.common.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import ere_geologique.common.config.EGProperties;
 
public class CroMagnon extends EntityMob
    {
    public CroMagnon(World par1World)
    {
        super(par1World);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.69);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(8.0D);
    }
}