package ere_geologique.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.entity.Enums.EnumOrderType;
import ere_geologique.common.entity.IA.DinoAIAttackOnCollide;
import ere_geologique.common.entity.IA.DinoAIControlledByPlayer;
import ere_geologique.common.entity.IA.DinoAIEat;
import ere_geologique.common.entity.IA.DinoAIFollowOwner;
import ere_geologique.common.entity.IA.DinoAIWander;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class Brachiosaurus extends Dinosaure
{
    public boolean isTamed = false;
    
    final float PUSHDOWN_HARDNESS = 5.0F;

    public Brachiosaurus(World var1)
    {
        super(var1,EnumDinoType.Brachiosaurus);
        
        this.updateSize();
        
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.ridingHandler = new DinoAIControlledByPlayer(this));//, 0.34F));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new DinoAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 5.0F, 2.0F, 2.0F));
        this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(7, new DinoAIEat(this, 24));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
    }
    
    
    @Override
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "ere_geologique:brachiosaurus_living";
    }
    @Override
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
    	return "ere_geologique:brachiosaurus_hurt";
    }
    @Override
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "ere_geologique:brachiosaurus_death";
    } 


    /**
     * Returns true if the Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return !this.isModelized();
    }

    public Vec3 getBlockToEat(int SEARCH_RANGE)
    {
    	Vec3 pos = null;
    	
    	for(int r=1;r<=SEARCH_RANGE;r++)
    	{
	    	for (int ds = -r; ds <=r; ds++)
	        {
	            for (int dy = (int)this.getEyeHeight()+2; dy >= (int)this.getEyeHeight()-2; dy--)
	            {
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ-r))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ-r));
                    	return pos;
                    }
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+r))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+ds), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+r));
                    	return pos;
                    }
	            }
	        }
	    	for (int ds = -r+1; ds <=r-1; ds++)
	        {
	    		for (int dy = (int)this.getEyeHeight()+2; dy >= (int)this.getEyeHeight()-2; dy--)
	            {
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX-r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX-r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds));
                    	return pos;
                    }
                    if(this.posY+dy >= 0 && this.posY+dy <= this.worldObj.getHeight() && this.SelfType.FoodBlockList.CheckBlockById(this.worldObj.getBlockId(MathHelper.floor_double(this.posX+r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds))))
                    {
                    	pos = Vec3.createVectorHelper(MathHelper.floor_double(this.posX+r), MathHelper.floor_double(this.posY+dy), MathHelper.floor_double(this.posZ+ds));
                    	return pos;
                    }
	            }
	        }
    	}
    	return null;
    }
    public TileEntityFeeder GetNearestFeeder(int SEARCH_RANGE)
    {
        double var10 = 0.0D;
        double var12 = (double)(SEARCH_RANGE * SEARCH_RANGE * 2);

        for (int var15 = (int)(this.posX - (double)SEARCH_RANGE); var15 < (int)(this.posX + (double)SEARCH_RANGE); ++var15)
        {
            for (int var16 = (int)(this.posY + (double)this.getEyeHeight() - 2.0D); var16 < (int)(this.posY + (double)this.getEyeHeight() + 2.0D); ++var16)
            {
                for (int var17 = (int)(this.posZ - (double)SEARCH_RANGE); var17 < (int)(this.posZ + (double)SEARCH_RANGE); ++var17)
                {
                    if (var16 >= 0 && var16 <= this.worldObj.getHeight())
                    {
                        TileEntity var14 = this.worldObj.getBlockTileEntity(var15, var16, var17);

                        if (var14 != null && var14 instanceof TileEntityFeeder && !((TileEntityFeeder)var14).CheckIsEmpty(this.SelfType)/*isFilled()*/)
                        {
                            var10 = ((double)var15 - this.posX) * ((double)var15 - this.posX) + ((double)var17 - this.posZ) * ((double)var17 - this.posZ);

                            if (var10 < var12)
                            {
                                var12 = var10;
                                return (TileEntityFeeder)var14;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
    	//Add special item interaction code here
        return super.interact(var1);
    }

    
    public Brachiosaurus spawnBabyAnimal(EntityAgeable var1)
    {
        return new Brachiosaurus(this.worldObj);
    }
    
    @Override
	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return this.spawnBabyAnimal(var1);
	}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        if ((this.isTeen() || this.isAdult())&& !this.isModelized() && EreGeologique.EGOptions.Dino_Block_Breaking == true && this.riddenByEntity == null )//this.getDinoAge() >= 4)
        {
            this.BlockInteractive();
        }
    }

    /**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity var1)
    {
        if (!this.isModelized())
        {
            if (var1 instanceof EntityLiving && !(var1 instanceof EntityPlayer) && this.onGround && ((EntityLiving)var1).getEyeHeight() < this.getHalfHeight())
            {
                this.onKillEntity((EntityLiving)var1);
                ((EntityLiving)var1).attackEntityFrom(DamageSource.causeMobDamage(this), 10);
            }
            else
            {
                super.applyEntityCollision(var1);
            }
        }
    }

    public float getEyeHeight()
    {
        return 4.0F + (float)this.getDinoAge() / 1.8F;
    }

    public float getHalfHeight()
    {
        return this.getEyeHeight() / 2.0F;
    }

    public int BlockInteractive()
    {

        for (int var5 = (int)Math.round(this.boundingBox.minX) - 1; var5 <= (int)Math.round(this.boundingBox.maxX) + 1; ++var5)
        {
            for (int var9 = 0; var9 <= (int)this.getHalfHeight(); ++var9)
            {
                for (int var6 = (int)Math.round(this.boundingBox.minZ) - 1; var6 <= (int)Math.round(this.boundingBox.maxZ) + 1; ++var6)
                {
                    int var10 = (int)Math.round(this.boundingBox.minY) + var9;
                    int var8 = this.worldObj.getBlockId(var5, var10, var6);

                    if (Block.blocksList[var8] != null)
                    {
                        float var10000 = Block.blocksList[var8].getBlockHardness(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);

                        if (var10000 < 0.5F || (this.RiderSneak && (var10000<2.0F || var8 == Block.wood.blockID || var8 == Block.planks.blockID || var8 == Block.woodDoubleSlab.blockID || var8 == Block.woodSingleSlab.blockID)))
                        {
                            int var7 = this.GetObjectTall(var5, var10, var6);

                            if (var7 > 0 && !this.isObjectTooTall(var7 + var9))
                            {
                                this.DestroyTower(var5, var10, var6, var7);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean isObjectTooTall(int var1, int var2, int var3)
    {
        return (float)this.GetObjectTall(var1, var2, var3) > this.getHalfHeight();
    }

    private boolean isObjectTooTall(int var1)
    {
        float var2 = this.getHalfHeight();
        return (float)var1 > var2;
    }

    private int GetObjectTall(int var1, int var2, int var3)
    {
        int var4;

        for (var4 = 0; !this.worldObj.isAirBlock(var1, var2 + var4, var3); ++var4)
        {
            ;
        }

        return var4;
    }

    private void DestroyTower(int var1, int var2, int var3, int var4)
    {
        boolean var5 = false;

        for (int var6 = var2; var6 <= var2 + var4; ++var6)
        {
            int var7 = this.worldObj.getBlockId(var1, var6, var3);
            this.worldObj.playAuxSFX(2001, var1, var6, var3, var7);
            this.worldObj.setBlock(var1, var6, var3, 0);
        }
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.setPosition(this.posX, this.posY + (double)this.getHalfHeight() * 1.5D, this.posZ);
        }
    }

    /**
     * Time remaining during which the Animal is sped up and flees.
     */
    protected void updateWanderPath()
    {
        boolean var1 = false;
        int var2 = -1;
        int var3 = -1;
        int var4 = -1;
        float var5 = -99999.0F;

        if (this.OrderStatus == EnumOrderType.FreeMove || !this.isTamed())
        {
            for (int var6 = 0; var6 < 10 + this.getDinoAge(); ++var6)
            {
                int var7 = MathHelper.floor_double(this.posX + (double)this.rand.nextInt(24 + (int)(this.width * this.width * 4.0F)) - (12.0D + (double)(this.width * this.width * 2.0F)));
                int var8 = MathHelper.floor_double(this.posY + (double)this.rand.nextInt(7) - 3.0D);
                int var9 = MathHelper.floor_double(this.posZ + (double)this.rand.nextInt(24 + (int)(this.width * this.width * 4.0F)) - (12.0D + (double)(this.width * this.width * 2.0F)));
                float var10 = this.getBlockPathWeight(var7, var8, var9);

                if (var10 > var5)
                {
                    var5 = var10;
                    var2 = var7;
                    var3 = var8;
                    var4 = var9;
                    var1 = true;
                }
            }

            if (var1)
            {
                this.setPathToEntity(this.worldObj.getEntityPathToXYZ(this, var2, var3, var4, 10.0F, true, false, true, false));
            }
        }
    }
}