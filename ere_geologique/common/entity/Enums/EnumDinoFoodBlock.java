package ere_geologique.common.entity.Enums;

import ere_geologique.common.block.EGBlockList;
import net.minecraft.block.Block;

public enum EnumDinoFoodBlock
{
	Cake(Block.cake,25,5),
	Carrot(Block.carrot,25,3),
	Crops(Block.crops,10,2),
	Leaves(Block.leaves,15,2),
	Melon(Block.melon,60,4),
	BrownMushroom(Block.mushroomBrown,15,1),
	RedMushroom(Block.mushroomRed,15,1),
	RedFlower(Block.plantRed,10,1),
	YellowFlower(Block.plantYellow,10,1),
	Potato(Block.potato,25,2),
	Pumpkin(Block.pumpkin,20,1),
	Reed(Block.reed,10,1),
	Sapling(Block.sapling,10,1),
	TallGrass(Block.tallGrass,10,1),
	LeavesFougere(EGBlockList.Leaves,50,3),
	SaplingFougere(EGBlockList.Sapling,15,2)
	;
    public Block block;
    public int FoodValue;
    public int HealValue;

    private EnumDinoFoodBlock(Block Block, int Food, int Heal)
    {
        block = Block;
        FoodValue = Food;
        HealValue = Heal;
    }
    /**
     * 
     * Takes the itemid and gives the food value
     */
    public static int getBlockFood(int i0)
	{
		for (int i=0;i<EnumDinoFoodBlock.values().length;i++)
		{
			if (EnumDinoFoodBlock.values()[i].block.blockID==i0)
				return EnumDinoFoodBlock.values()[i].FoodValue;
		}
		return 0;	
	}
}