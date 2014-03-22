package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ere_geologique.common.creativetabs.EGCreativeTab;
import ere_geologique.common.item.EGItemList;

public class Fossil extends BlockStone
{
    public Fossil()
    {
        super();
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(EGCreativeTab.EGCreativeTabBlock);
    }

    public Item idDropped(int var1, Random var2, int var3)
    {
        int i = (new Random()).nextInt(1000);
        if(i < 1)	return EGItemList.gem;
        if(i < 13) 	return EGItemList.legBone;
        if(i < 15)	return EGItemList.skull;
        if(i < 17)	return EGItemList.claw;
        if(i < 19)  return EGItemList.foot;
        if(i < 50)	return Item.getItemFromBlock(EGBlockList.skull);
        if(i < 250)	return EGItemList.bioFossil;
        if(i < 450)	return EGItemList.relic;
        if(i < 900)	return Items.bone;
        return Item.getItemFromBlock(Blocks.cobblestone);
    }  
}