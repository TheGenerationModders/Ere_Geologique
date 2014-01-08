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
        if(i < 13) 	return EGItemList.legBone.itemID;
        if(i < 15)	return EGItemList.skull.itemID;
        if(i < 17)	return EGItemList.claw.itemID;
        if(i < 19)  return EGItemList.foot.itemID;
        if(i < 50)	return EGBlockList.skull.blockID;
        if(i < 250)	return EGItemList.bioFossil.itemID;
        if(i < 450)	return EGItemList.relic.itemID;
        if(i < 900)	return Item.bone.itemID;
        return Block.cobblestone.blockID;
    }  
}