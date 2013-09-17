package ere_geologique.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ere_geologique.common.block.EGBlockList;

public class Triceratops extends EntityMob
{
	private int angerLevel;
	private int randomSoundDelay;

	public Triceratops(World world)
	{
		super(world);
		this.setSize(1.0F, 1.0F);
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.69);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(10.0D);
    }
	
	protected Entity findPlayerToAttack()
    {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = par1DamageSource.getEntity();

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof Triceratops)
                    {
                        Triceratops triceratops = (Triceratops)entity1;
                        triceratops.becomeAngryAt(entity);
                    }
                }

                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
	
	private void becomeAngryAt(Entity par1Entity)
    {
        this.entityToAttack = par1Entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);
    }

	public Triceratops spawnBabyAnimal(EntityAgeable entityageable)
	{
		return new Triceratops(this.worldObj);
	}
	
	public boolean isBreedingItem(ItemStack itemstack)
    {
        return itemstack != null && itemstack.itemID == EGBlockList.Sapling.blockID;
    }
	
	public Triceratops createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}