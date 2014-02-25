package ere_geologique.api.food;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ere_geologique.common.entity.enums.EnumDinoType;

public class DinoFood
{
	public static List<DinoFoodEntry> globalDinoFood = new ArrayList();

	public static class DinoFoodEntry
	{
		private Item item;
		//private Class preyClass;
		private int metadata;
		private int foodValue;
		private int healValue;
		private EnumFoodType foodType;
		
		/*public DinoFoodEntry(Class pclass, int food, int heal, EnumFoodType type)
		{
			this.preyClass = pclass;
			this.foodValue = food;
			this.healValue = heal;
			this.foodType = type;
		}*/
		
		public DinoFoodEntry(Block block, int metadata, int food, int heal, EnumFoodType type)
		{
			this(Item.getItemFromBlock(block), metadata, food, heal, type);
		}

		public DinoFoodEntry(Item item, int metadata, int food, int heal, EnumFoodType type)
		{
			this.item = item;
			this.foodValue = food;
			this.healValue = heal;
			this.metadata = metadata;
			this.foodType = type;
		}
		
		public Item getItem()
		{
			return this.item;
		}

		public int getMetadata()
		{
			return this.metadata;
		}

		public int getFoodValue()
		{
			return this.foodValue;
		}

		public int getHealValue()
		{
			return this.healValue;
		}
		
		public EnumFoodType getFoodType()
		{
			return this.foodType;
		}

		@Override
		public boolean equals(Object o)
		{
			if(o == null)
			{
				return false;
			}
			else if(!(o instanceof DinoFoodEntry))
			{
				return false;
			}
			DinoFoodEntry dinoFood = (DinoFoodEntry)o;
			return(dinoFood.item == this.item && dinoFood.metadata == this.metadata);
		}
	}

	public static boolean isFood(EnumDinoType enumDino, Item item, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.item == item && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDinoFoodByDino(EnumDinoType enumDino, Item item, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.item == item && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDinoFoodByDino(EnumDinoType enumDino, Block block, int metadata)
	{
		return isDinoFoodByDino(enumDino, Item.getItemFromBlock(block), metadata);
	}

	public static DinoFoodEntry getFoodByDino(EnumDinoType enumDino, Item item, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.item == item && validFood.getMetadata() == metadata)
			{
				return validFood;
			}
		}
		return null;
	}
	
	public static DinoFoodEntry getFoodByDino(EnumDinoType enumDino, Block block, int metadata)
	{
		return getFoodByDino(enumDino, Item.getItemFromBlock(block), metadata);
	}

	public static DinoFoodEntry getGlobalFood(Item item, int metadata)
	{
		for(DinoFoodEntry validFood : globalDinoFood)
		{
			if(validFood.item == item && validFood.getMetadata() == metadata)
			{
				return validFood;
			}
		}
		return null;
	}
	
	public static boolean isGlobalDinoFood(Item item, int metadata)
	{
		for(DinoFoodEntry validFood : globalDinoFood)
		{
			if(validFood.item == item && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}
}