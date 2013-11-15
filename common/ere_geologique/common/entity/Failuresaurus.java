package ere_geologique.common.entity;

import ere_geologique.common.item.EGItemList;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class Failuresaurus extends EntityZombie
{
    public Failuresaurus(World var1)
    {
        super(var1);
        this.experienceValue=4;
    }

    protected int getDropItemId()
    {
        return EGItemList.BioFossil.itemID;
    }

    protected void jump() {}
    
    public String getTexture()
    {
        return "ere_geologique:textures/entity/Failuresaurus.png";
    }
}