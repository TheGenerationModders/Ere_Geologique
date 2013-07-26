package ere_geologique.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ere_geologique.common.config.EGProperties;

public class Triceratops extends EntityTameable
{
	private final EntityAIControlledByPlayer aiControlledByPlayer;

	public Triceratops(World world)
	{
		super(world);
		this.texture = "/ere_geologique/textures/entity/Tric\351ratops.png";
		this.moveSpeed = 0.15F;
		this.getNavigator();
		this.tasks.addTask(0, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(1, new EntityAITempt(this, this.moveSpeed, EGProperties.SaplingID, false));
		this.tasks.addTask(2, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(3, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, this.moveSpeed));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, this.moveSpeed));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
	}

	public boolean isAIEnabled()
	{
	 return true;	
	}
	
	@Override
	public int getMaxHealth()
	{
		return 20;
	}
	
	protected void updateAITask()
	{
		super.updateAITasks();
	}
	
	public boolean interract(EntityPlayer par1EntityPlayer)
	{
		if (super.interact(par1EntityPlayer))
		{
		 return true;
		}
		else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer))
		{
			par1EntityPlayer.mountEntity(this);
			return true;
		}
		return false;
	}
	
	public boolean getSaddled()
	{

		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public Triceratops spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new Triceratops(this.worldObj);
	}
	
	public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.itemID == EGProperties.SaplingID;
    }
	
	public EntityAIControlledByPlayer getAIControlledByPlayer()
	{
		return this.aiControlledByPlayer;
	}
	
	public Triceratops createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}