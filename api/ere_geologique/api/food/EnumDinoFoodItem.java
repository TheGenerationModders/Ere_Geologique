package ere_geologique.api.food;

import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;
import net.minecraft.item.Item;

public enum EnumDinoFoodItem
{
	Wheat(Item.wheat,10,2),//Veggie Foods
	Melon(Item.melon,10,2),
	Apple(Item.appleRed,15,3),
	Potato(Item.potato,10,2),
	BakedPotato(Item.bakedPotato,15,3),	
	Cake(Item.cake,25,5),
	Carrot(Item.carrot,10,2),
	Cookie(Item.cookie,15,4),
	PumpkinPie(Item.pumpkinPie,20,4),
	Sugar(Item.sugar,10,2),
	Bread(Item.bread,25,2),
	
	FishRaw(Item.fishRaw,30,3),//this MUST BE the first carnivore food!
	FishCooked(Item.fishCooked,40,4),
	BeefCooked(Item.beefCooked,50,5),
	BeefRaw(Item.beefRaw,40,4),
	ChickenCooked(Item.chickenCooked,30,3),
	ChickenRaw(Item.chickenRaw,40,4),
	PorkRaw(Item.porkRaw,30,2),
	PorkCooked(Item.porkCooked,50,3),
	Egg(Item.egg,10,2),
	Sjl(EGItemList.sjl,30,3),//SioChiuLe
	Nautilus(EnumDinoType.Nautilus.DropItem,20,2),
	ChickenSoupRaw(EGItemList.rawChickenSoup,30,3),
	ChickenSoupCooked(EGItemList.cookedChickenSoup,40,3),
	Triceratops(EnumDinoType.Triceratops.DropItem,50,3),
	Velociraptor(EnumDinoType.Velociraptor.DropItem,20,3),
	TRex(EnumDinoType.TRex.DropItem,20,3),
	Pterosaure(EnumDinoType.Pterosaure.DropItem,15,2),
	Plesiosaure(EnumDinoType.Plesiosaure.DropItem,30,3),
	Mosasaurus(EnumDinoType.Mosasaurus.DropItem,20,3),
	Stegosaurus(EnumDinoType.Stegosaurus.DropItem,50,3),
	Dilophosaurus(EnumDinoType.Dilophosaurus.DropItem,25,2),
	Brachiosaure(EnumDinoType.Brachiosaurus.DropItem,50,4),
	Spinosaurus(EnumDinoType.Spinosaurus.DropItem,20,3),
	Compsognathus(EnumDinoType.Compsognathus.DropItem,20,3),
	Ankylosaurus(EnumDinoType.Ankylosaurus.DropItem,50,3),
	Pachycephalosaurus(EnumDinoType.Pachycephalosaurus.DropItem,50,3),
	DinoMeatCooked(EGItemList.cookedDinoMeat,50,5),
	;
    public Item item;
    public int FoodValue;
    public int HealValue;
    
    public static final int ISHERBIVOROUS=1;
    public static final int ISCARNIVOROUS=2;
    public static final int ISNOFOOD=0;

    private EnumDinoFoodItem(Item item0, int Food, int Heal)
    {
        this.item = item0;
        this.FoodValue = Food;
        this.HealValue = Heal;
    }
    
    /**
     * 
     * Takes the itemid and tells if its herbivore, carnivore or no food 
     */
    public static int foodtype(int i0)
    {
		for (int i=0;i<EnumDinoFoodItem.values().length;i++)//check all entries
		{
			if (EnumDinoFoodItem.values()[i].item.itemID==i0)//found it in the list
			{
				if(i<EnumDinoFoodItem.FishRaw.ordinal())//its before SJL, the first carn. food
					return ISHERBIVOROUS;
				return ISCARNIVOROUS;
			}
		}
		return ISNOFOOD;//wasnt found at all
    }
    
    /**
     * 
     * Takes the itemid and gives the food value
     */
    public static int getItemFood(int i0)
	{
		for (int i=0;i<EnumDinoFoodItem.values().length;i++)
		{
			if (EnumDinoFoodItem.values()[i].item.itemID==i0)
				return EnumDinoFoodItem.values()[i].FoodValue;
		}
		return 0;	
	}
}