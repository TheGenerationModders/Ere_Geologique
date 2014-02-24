package ere_geologique.common.entity;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import ere_geologique.common.item.EGItemList;

public class Failuresaurus extends EntityZombie
{
    public Failuresaurus(World var1)
    {
        super(var1);
        this.experienceValue=4;
    }

    protected Item getDropItem()
    {
        return EGItemList.bioFossil;
    }

    protected void jump() {}
    
    public String getTexture()
    {
        return "ere_geologique:textures/entity/Failuresaurus.png";
    }
}