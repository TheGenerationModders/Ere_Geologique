package ere_geologique.common.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenTaiga;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.command.CommandDino;
import ere_geologique.common.entity.enums.EnumDinoType;
import ere_geologique.common.gui.GuiPedia;
import ere_geologique.common.item.EGItemList;

public class DinoEgg extends Entity implements IEntityAdditionalSpawnData
{
    private static final String HEAD = "Dinoegg.";
    private static final String MSGHEAD = "Dinoegg.msghead";
    private static final String MSGTAIL = ".msgtail";
    private static final String COLD = "cold";
    private static final String DRY = "dry";
    private static final String PEDIA = "PediaText.egg.";
    public int damageTaken;
    public int timeSinceHit;
    public EnumDinoType DinoInside;
    //public int BirthTick;
    public String ParentOwner;
    private int HatchTime;
    public final int HatchingNeedTime;
    public static final int HATCHING_INDEX = 18;

    public DinoEgg(World world, EnumDinoType DinoType)
    {
        super(world);
        //this.BirthTick = 0;
        this.ParentOwner = "";
        this.HatchingNeedTime = this.HatchTime;
        this.damageTaken = 0;
        this.timeSinceHit = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.5F, 1.5F);
        this.yOffset = this.height;
        this.DinoInside = DinoType;
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        int var1 = this.DinoInside.ordinal();
        return var1 < 4 ? "ere_geologique:textures/entity/eggTexture" + (var1 + 1) + ".png" : "ere_geologique:textures/entity/eggTexture" + var1 + ".png";
    }

    public DinoEgg(World world)
    {
        this(world, (EnumDinoType)null);
    }
    private void setPedia()
    {
    	EreGeologique.toPedia = (Object)this;
    }

    public DinoEgg(World world, EnumDinoType dinoType, Dinosaure dinosaure)
    {
        this(world, dinoType);
        this.ParentOwner = dinosaure.getOwnerName();
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        if (CommandDino.debugMode)
        {
            this.HatchTime = 100;
        }
        else
        {
            this.HatchTime = 3000;
        }
        this.dataWatcher.addObject(HATCHING_INDEX, new Integer(0));
    }
    public int getBirthTick()
    {return this.dataWatcher.getWatchableObjectInt(HATCHING_INDEX);}

    public void setBirthTick(int i)
    {this.dataWatcher.updateObject(HATCHING_INDEX, Integer.valueOf(i));}

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return true;
    }

    public DinoEgg(World world, double x, double y, double z, EnumDinoType dinoType)
    {
        this(world, dinoType);
        this.setPosition(x, y + (double)this.yOffset, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.0D - 0.30000001192092896D;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (!this.worldObj.isRemote && !this.isDead)
        {
            this.timeSinceHit = 10;
            this.damageTaken += var2 * 10;
            this.setBeenAttacked();

            if (this.damageTaken > 40)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                this.setDead();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    public void performHurtAnimation()
    {
        this.timeSinceHit = 10;
        this.damageTaken += this.damageTaken * 10;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	
        super.onUpdate();
        //if(!this.worldObj.isRemote)
        this.HandleHatching();
        //super.onUpdate();

        if (this.timeSinceHit > 0)
        {
            --this.timeSinceHit;
        }

        if (this.damageTaken > 0)
        {
            --this.damageTaken;
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        byte var1 = 5;
        double var2 = 0.0D;

        for (int var4 = 0; var4 < var1; ++var4)
        {
            double var5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 0) / (double)var1 - 0.125D;
            double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 1) / (double)var1 - 0.125D;
            AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, var5, this.boundingBox.minZ, this.boundingBox.maxX, var7, this.boundingBox.maxZ);
        }

        double var21;

        if (var2 < 1.0D)
        {
            var21 = var2 * 2.0D - 1.0D;
            this.motionY += 0.03999999910593033D * var21;
        }
        else
        {
            if (this.motionY < 0.0D)
            {
                this.motionY /= 2.0D;
            }

            this.motionY += 0.007000000216066837D;
        }

        if (this.riddenByEntity != null)
        {
            this.motionX += this.riddenByEntity.motionX * 0.2D;
            this.motionZ += this.riddenByEntity.motionZ * 0.2D;
        }

        var21 = 0.4D;

        if (this.motionX < -var21)
        {
            this.motionX = -var21;
        }

        if (this.motionX > var21)
        {
            this.motionX = var21;
        }

        if (this.motionZ < -var21)
        {
            this.motionZ = -var21;
        }

        if (this.motionZ > var21)
        {
            this.motionZ = var21;
        }

        if (this.onGround)
        {
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        double var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double var8;
        double var10;

        if (var6 > 0.15D)
        {
            var8 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
            var10 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

            for (int var12 = 0; (double)var12 < 1.0D + var6 * 60.0D; ++var12)
            {
                double var13 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
                double var15 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
                double var17;
                double var19;

                if (this.rand.nextBoolean())
                {
                    var17 = this.posX - var8 * var13 * 0.8D + var10 * var15;
                    var19 = this.posZ - var10 * var13 * 0.8D - var8 * var15;
                }
                else
                {
                    var17 = this.posX + var8 + var10 * var13 * 0.7D;
                    var19 = this.posZ + var10 - var8 * var13 * 0.7D;
                }
            }
        }

        if (this.isCollidedHorizontally && var6 > 0.15D)
        {
            if (!this.worldObj.isRemote)
            {
                this.setDead();
            }
        }
        else
        {
            this.motionX *= 0.9900000095367432D;
            this.motionY *= 0.949999988079071D;
            this.motionZ *= 0.9900000095367432D;
        }

        this.rotationPitch = 0.0F;
        var8 = (double)this.rotationYaw;
        var10 = this.prevPosX - this.posX;
        double var22 = this.prevPosZ - this.posZ;

        if (var10 * var10 + var22 * var22 > 0.001D)
        {
            var8 = (double)((float)(Math.atan2(var22, var10) * 180.0D / Math.PI));
        }

        double var14;

        for (var14 = var8 - (double)this.rotationYaw; var14 >= 180.0D; var14 -= 360.0D)
        {
            ;
        }

        while (var14 < -180.0D)
        {
            var14 += 360.0D;
        }

        if (var14 > 20.0D)
        {
            var14 = 20.0D;
        }

        if (var14 < -20.0D)
        {
            var14 = -20.0D;
        }

        this.rotationYaw = (float)((double)this.rotationYaw + var14);
        this.setRotation(this.rotationYaw, this.rotationPitch);
        List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.0D, 0.0D, 0.0D));
        int var23;

        if (var16 != null && var16.size() > 0)
        {
            for (var23 = 0; var23 < var16.size(); ++var23)
            {
                Entity var18 = (Entity)var16.get(var23);

                if (var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat)
                {
                    var18.applyEntityCollision(this);
                }
            }
        }

        for (var23 = 0; var23 < 4; ++var23)
        {
            int var24 = MathHelper.floor_double(this.posX + ((double)(var23 % 2) - 0.5D) * 0.8D);
            int var25 = MathHelper.floor_double(this.posY);
            int var20 = MathHelper.floor_double(this.posZ + ((double)(var23 / 2) - 0.5D) * 0.8D);

            if (this.worldObj.getBlock(var24, var25, var20) == Blocks.snow)
            {
                this.worldObj.setBlockToAir(var24, var25, var20);
            }
        }

        if (this.riddenByEntity != null && this.riddenByEntity.isDead)
        {
            this.riddenByEntity = null;
        }
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            double var1 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            double var3 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
        }
    }
    private void HandleHatching()
    {
    	//this.getClass();//needed to set which is the actual instance using this function
        float var2 = this.getBrightness(1.0F);
        EntityPlayer player = null;

        if ((this.ParentOwner == "" || this.worldObj.getPlayerEntityByName(this.ParentOwner) == null) && this.worldObj.getClosestPlayerToEntity(this, 16.0D) != null)
        {
            player = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
        }

        if (this.DinoInside == EnumDinoType.Mosasaurus)
        {
            if (this.inWater)
                this.setBirthTick(this.getBirthTick()+1);
            else
            	this.setBirthTick(this.getBirthTick()-1);
        }
        else if ((double)var2 >= 0.5D && !this.inWater)
        	this.setBirthTick(this.getBirthTick()+1);
        else 
        {
        	BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
            float var6 = var5.getFloatTemperature(0, 0, 0);
        	//if (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            if((var6<=0.15F && var2 < 0.5) || this.inWater)
            	this.setBirthTick(this.getBirthTick()-1);
        }
        if (this.getBirthTick() <= -this.HatchingNeedTime)
        {
        	if(player!=null)
        	{
	            String var6;
	
	            if (this.DinoInside == EnumDinoType.Mosasaurus)
	            {
	                var6 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_DRY);
	            }
	            else
	            {
	                var6 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_WET);
	            }
	
	            String var1 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_HEAD);
	            if(FMLCommonHandler.instance().getSide().isServer())
	            	EreGeologique.ShowMessage(var1 + StatCollector.translateToLocal("Dino."+this.DinoInside.toString())/*EntityDinosaur.GetNameByEnum(this.DinoInside, false)*/ + var6, player);
        	}
        	this.setDead();
        }
        else
        {
            if (this.getBirthTick() >= this.HatchingNeedTime)
            {
                if (this.worldObj.isRemote)return;

                BiomeGenBase var3 = this.worldObj.provider.worldChunkMgr.getBiomeGenAt((int)Math.floor(this.posX), (int)Math.floor(this.posZ));
                Object var5 = null;
                switch (this.DinoInside)
                {
                    case Triceratops:var5 = new Triceratops(this.worldObj);break;
                    case Velociraptor:var5 = new Velociraptor(this.worldObj);
                        				if (var3 instanceof BiomeGenForest)
                        					((Velociraptor)var5).setSubSpecies(2);
                        				else if (var3 instanceof BiomeGenSnow || var3 instanceof BiomeGenTaiga)
                        					((Velociraptor)var5).setSubSpecies(1);
                        				else
                        					((Velociraptor)var5).setSubSpecies(3);break;
                    case TRex:var5 = new TRex(this.worldObj);break;
                    case Pterosaure:var5 = new Pterosaure(this.worldObj);break;
                    case Plesiosaure:var5 = new Plesiosaure(this.worldObj);break;
                    case Mosasaurus:var5 = new Mosasaurus(this.worldObj);break;
                    case Stegosaurus:var5 = new Stegosaurus(this.worldObj);break;
                    case Dilophosaurus:var5 = new Dilophosaurus(this.worldObj);break;
                    case Brachiosaurus:var5 = new Brachiosaurus(this.worldObj);break;
                    case Spinosaurus:var5 = new Spinosaurus(this.worldObj);break;
                    case Pachycephalosaurus:var5 = new Pachycephalosaurus(this.worldObj);
                    if (var3 instanceof BiomeGenForest)
                        ((Pachycephalosaurus)var5).setSubSpecies(1);
                    else if (var3 instanceof BiomeGenSnow || var3 instanceof BiomeGenTaiga)
                        ((Pachycephalosaurus)var5).setSubSpecies(2);
                    else
                        ((Pachycephalosaurus)var5).setSubSpecies(3);break;
                        
                    case Compsognathus:var5 = new Compsognathus(this.worldObj);
                        if (var3 instanceof BiomeGenSnow || var3 instanceof BiomeGenDesert)
                        ((Compsognathus)var5).setSubSpecies(1);
                        else
                        ((Compsognathus)var5).setSubSpecies(2);break;
                    case Ankylosaurus:var5 = new Ankylosaurus(this.worldObj);break;

                    default:
                        EreGeologique.ShowMessage("Bug: Impossible result.", player);
                        this.setDead();
                        return;
                }
                if(((Dinosaure)var5).SelfType.isTameable() && player != null)
                {// Tameable and player next to it
                	((Dinosaure)var5).setOwner(player.getDisplayName());
                    ((Dinosaure)var5).setTamed(true);
                }

                ((EntityLiving)var5).setLocationAndAngles((double)((int)Math.floor(this.posX)), (double)((int)Math.floor(this.posY) + 1), (double)((int)Math.floor(this.posZ)), this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

                if (this.worldObj.checkNoEntityCollision(((EntityLiving)var5).boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)var5, ((EntityLiving)var5).boundingBox).size() == 0 && (!this.worldObj.isAnyLiquid(((EntityLiving)var5).boundingBox) || this.DinoInside == EnumDinoType.Mosasaurus))
                {
                    //if (!this.worldObj.isRemote)
                    {
                        this.worldObj.spawnEntityInWorld((Entity)var5);
                        if (player!=null)
                            EreGeologique.ShowMessage(StatCollector.translateToLocal(LocalizationStrings.DINOEGG_HATCHED), player);
                        
                    }
                    this.setDead();
                }
                else
                {
                    EreGeologique.ShowMessage(StatCollector.translateToLocal(LocalizationStrings.DINOEGG_NOSPACE), player);
                    this.setBirthTick(this.getBirthTick()-500);
                }
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setInteger("BirthTick", this.getBirthTick());
        var1.setInteger("DinoType", this.EnumToInt(this.DinoInside));
        var1.setString("ParentOwner", this.ParentOwner);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        EnumDinoType[] var2 = EnumDinoType.values();
        this.setBirthTick(var1.getInteger("BirthTick"));
        this.DinoInside = var2[var1.getInteger("DinoType")];
        this.ParentOwner = var1.getString("ParentOwner");
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interactFirst(EntityPlayer player)
    {
    	ItemStack itemStack = player.inventory.getCurrentItem();
        if (itemStack == null)
        {
        	Item egg = this.DinoInside.eggItem;

            ItemStack itemStack2 = new ItemStack(egg/*this.DinoInside.EggItem/*var7*/, 1, 1);
            if (player.inventory.addItemStackToInventory(itemStack2))
            {
                this.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                this.setDead();
            }
            return true;
        }
        else if (FMLCommonHandler.instance().getSide().isClient() && itemStack.getItem().equals(EGItemList.dinoPedia))
        {
        	this.setPedia();
        	player.openGui(EreGeologique.instance, 1, worldObj, (int)posX, (int)posY, (int)posZ);
            return true;
        }
        return false;
    }

    private int EnumToInt(EnumDinoType dinoType)
    {
        return this.DinoInside.ordinal();
    }
    
    @SideOnly(Side.CLIENT)
    public void ShowPedia(GuiPedia pedia)
    {
    	Item egg = this.DinoInside.eggItem;

    	pedia.reset();
    	pedia.PrintItemXY(egg, 140, 7);
    	pedia.PrintStringLR(StatCollector.translateToLocal("Dino."+this.DinoInside.toString()), false, 1,40,90,245);
    	int quot = (int)Math.floor(((float)this.getBirthTick() / (float)this.HatchingNeedTime * 100.0F));
    	String stat;
    	if (this.DinoInside == EnumDinoType.Mosasaurus)
        {
            if (this.getBirthTick() >= 0)
                stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_WET);
            else
                stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_DRY);
        }
        else 
        {
        	if (this.getBirthTick() >= 0)
	            stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_WARM);
	        else
	            stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_COLD);
        }
        pedia.PrintStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_STATUS), false, 2,40,90,245);
        pedia.PrintStringLR(stat, false, 3);
        if (this.getBirthTick() >= 0)
        {
        	pedia.PrintStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_PROGRESS), false, 4,40,90,245);
        	pedia.PrintStringLR(String.valueOf(quot) + "/100", false, 5);
        }
    }

    public void writeSpawnData(ByteArrayDataOutput var1)
    {
        var1.writeInt(this.getBirthTick());
        var1.writeInt(this.EnumToInt(this.DinoInside));
    }

    public void readSpawnData(ByteArrayDataInput var1)
    {
        this.setBirthTick(var1.readInt());
        this.DinoInside = EnumDinoType.values()[var1.readInt()];
    }

	@Override
	public void writeSpawnData(ByteBuf buffer) {}

	@Override
	public void readSpawnData(ByteBuf additionalData) {}
}