package ere_geologique.api.food;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ere_geologique.common.entity.Enums.EnumDinoType;

public class DinoFood
{
	public static List<DinoFoodEntry> globalDinoFood = new ArrayList();

	public static class DinoFoodEntry
	{
		private int id;
		//private Class preyClass;
		private int metadata;
		private int foodValue;
		private int healValue;
		private EnumFoodType foodType;

		public DinoFoodEntry(Block block, int metadata, int food, int heal, EnumFoodType type)
		{
			this(block.blockID, metadata, food, heal, type);
		}

		public DinoFoodEntry(Item item, int metadata, int food, int heal, EnumFoodType type)
		{
			this(item.itemID, metadata, food, heal, type);
		}
		
		/*public DinoFoodEntry(Class pclass, int food, int heal, EnumFoodType type)
		{
			this.preyClass = pclass;
			this.foodValue = food;
			this.healValue = heal;
			this.foodType = type;
		}*/

		public DinoFoodEntry(int id, int metadata, int food, int heal, EnumFoodType type)
		{
			this.id = id;
			this.foodValue = food;
			this.healValue = heal;
			this.metadata = metadata;
			this.foodType = type;
		}
		
		public int getId()
		{
			return this.id;
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
			return(dinoFood.id == this.id && dinoFood.metadata == this.metadata);
		}
	}

	public static boolean isFood(EnumDinoType enumDino, int id, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.id == id && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDinoFoodByDino(EnumDinoType enumDino, int id, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.id == id && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}

	public static DinoFoodEntry getFoodByDino(EnumDinoType enumDino, int id, int metadata)
	{
		for(DinoFoodEntry validFood : enumDino.dinoFood)
		{
			if(validFood.id == id && validFood.getMetadata() == metadata)
			{
				return validFood;
			}
		}
		return null;
	}

	public static DinoFoodEntry getGlobalFood(int id, int metadata)
	{
		for(DinoFoodEntry validFood : globalDinoFood)
		{
			if(validFood.id == id && validFood.getMetadata() == metadata)
			{
				return validFood;
			}
		}
		return null;
	}
	
	public static boolean isGlobalDinoFood(int id, int metadata)
	{
		for(DinoFoodEntry validFood : globalDinoFood)
		{
			if(validFood.id == id && validFood.getMetadata() == metadata)
			{
				return true;
			}
		}
		return false;
	}
}