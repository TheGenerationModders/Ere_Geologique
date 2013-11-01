package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.item.Item;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.item.EGItemList;

public class Fossil extends BlockStone
{
    public Fossil(int id)
    {
        super(id);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
    }

    public int idDropped(int var1, Random var2, int var3)
    {
        int i = (new Random()).nextInt(1000);
        if(i < 1)	return EGItemList.gem.itemID;
        if(i < 13) 	return EGItemList.LegBone.itemID;
        if(i < 15)	return EGItemList.Skull.itemID;
        if(i < 17)	return EGItemList.Claw.itemID;
        if(i < 19)  return EGItemList.Foot.itemID;
        if(i < 50)	return EGBlockList.Skull.blockID;
        if(i < 250)	return EGItemList.BioFossil.itemID;
        if(i < 450)	return EGItemList.Relic.itemID;
        if(i < 900)	return Item.bone.itemID;
        return Block.cobblestone.blockID;
    }  
}