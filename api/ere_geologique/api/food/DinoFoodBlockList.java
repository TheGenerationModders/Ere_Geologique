package ere_geologique.api.food;

import net.minecraft.block.Block;

public class DinoFoodBlockList
{
	EnumDinoFoodBlock [] Blocks;
	
	public int index;	
	
	public DinoFoodBlockList()
	{
		index=0;
		this.Blocks = new EnumDinoFoodBlock[100];
	}
	public void addblock(EnumDinoFoodBlock block)
	{
		this.Blocks[index]=block;
		index++;
	}
	public boolean CheckBlockById(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Blocks[i].block.blockID == ID)
				return true;
		}
		return false;
	}
	public int getBlockFood(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Blocks[i].block.blockID == ID)
				return Blocks[i].FoodValue;
		}
		return 0;	
	}
	public int getBlockHeal(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Blocks[i].block.blockID == ID)
				return Blocks[i].HealValue;
		}
		return 0;	
	}
	public Block getBlock(int ID)
	{
		if (ID>=0 && ID<index)
			return Blocks[ID].block;
		return null;	
	}
	public boolean IsEmpty()
	{
		return index==0;
	}

}