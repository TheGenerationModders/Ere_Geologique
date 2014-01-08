package ere_geologique.common.entity.IA;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import ere_geologique.api.food.DinoFood;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.entity.Enums.EnumOrderType;
import ere_geologique.common.entity.Enums.EnumSituation;

public class DinoAIStarvation extends EntityAIBase
{
    Dinosaure mover = null;

    public DinoAIStarvation(Dinosaure var1)
    {
        this.mover = var1;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        //if (EGOptions.DinoHunger)
        //{
            this.mover.decreaseHungerTick();
            return this.mover.getHungerTick() <= 0 && CommandDino.dinos_Starve;// && this.mover.worldObj.difficultySetting > 0;
        //}
        //return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        //this.mover.getClass();
        this.mover.setHungerTick(300);
        this.mover.decreaseHunger();
        if(!this.mover.worldObj.isRemote)
        {
        	if(this.mover.IsDeadlyHungry())
        	{

    			if(this.mover.OrderStatus!=EnumOrderType.FreeMove)
    	        {
    	        	this.mover.OrderStatus=EnumOrderType.FreeMove;
    	        	this.mover.SendStatusMessage(EnumSituation.StarveEsc);
    	        }
    			else
    				this.mover.SendStatusMessage(EnumSituation.Hungry);
        	}
	        if(this.mover.ItemInMouth != null)//The Dino has something in its mouth and gets hungry
	        {
	        	if(DinoFood.getFoodByDino(this.mover.SelfType, this.mover.ItemInMouth.itemID, this.mover.ItemInMouth.itemID) != null)
	        	{//its food
	        		if(this.mover.IsHungry() || this.mover.SelfType.MaxHunger-this.mover.getHunger() > DinoFood.getFoodByDino(this.mover.SelfType, this.mover.ItemInMouth.itemID, this.mover.ItemInMouth.getItemDamage()).getFoodValue())
	            	{//it's hungry or there is enough place in the stomach free
	            		this.mover.setHunger(DinoFood.getFoodByDino(this.mover.SelfType, this.mover.ItemInMouth.itemID, this.mover.ItemInMouth.getItemDamage()).getFoodValue());
	            		this.mover.ItemInMouth = null;
	            	}
	        	}
	        	else
	        	{//no food
	        		if(this.mover.IsHungry())
	        		{//The Dino gets hungry and because of that spits the object out of the mouth
	        			this.mover.entityDropItem(new ItemStack(this.mover.ItemInMouth.itemID, 1, 0), 0.5F);
	        			this.mover.ItemInMouth=null;
	        		}
	        	}
	        }
	
	        if (this.mover.getHunger() <= 0)
	        {
	            this.handleStarvation();
	        }
        }
    }

    private void handleStarvation()
    {
    	if(this.mover.getHealth()<=5)
    		this.mover.SendStatusMessage(EnumSituation.Starve);
        this.mover.attackEntityFrom(DamageSource.starve, 5);
    }
}
