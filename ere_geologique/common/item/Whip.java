package ere_geologique.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.entity.Dinosaure;

public class Whip extends ItemCarrotOnAStick
{
    public Whip(int var1)
    {
        super(var1);
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabItem);
    }
    @SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack I, World W, EntityPlayer P)
    {
    	if (P.isRiding() && P.ridingEntity instanceof Dinosaure)
        {
    		Dinosaure D = (Dinosaure)P.ridingEntity;

//            if (D.getRidingHandler().isControlledByPlayer() && I.getMaxDamage() - I.getItemDamage() >= 5)
    		 if (D.getRidingHandler().isControlledByPlayer())
            {
    		     
                D.getRidingHandler().boostSpeed();
                //System.out.println("SPEED BOOSTED!");
                //System.out.println("Damage before:"+String.valueOf(I.getItemDamage()));
                I.damageItem(1, P);
                //System.out.println("Damage after:"+String.valueOf(I.getItemDamage()));
                //I.setItemDamage(I.getItemDamage()+5);
                //W.playSoundEffect(P.posX, P.posY, P.posZ, "WhipCrack", 0.5F, 1.0F);
                P.swingItem();
                P.ridingEntity.playSound("ere_geologique:whip", 1.0F, 1.0F);
            }
        }
        else
        {
            /*
            if (!W.isRemote)
            {
                W.spawnEntityInWorld(new EntityWhipAttachment(W, P));
            }
            */

            P.swingItem();
            W.playSoundAtEntity(P, "ere_geologique:whip",1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }

        
        return I;
    }
    
    @Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("ere_geologique:Whip");
    }

}