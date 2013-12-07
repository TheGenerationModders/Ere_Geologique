package ere_geologique.common.food;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import ere_geologique.api.food.DinoFood;
import ere_geologique.api.food.DinoFood.DinoFoodEntry;
import ere_geologique.api.food.EnumFoodType;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.Enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;

public class FoodList
{
	// Block
	public static DinoFoodEntry CakeBlock = new DinoFoodEntry(Block.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry CarrotBlock = new DinoFoodEntry(Block.carrot, 0, 25, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Crops = new DinoFoodEntry(Block.crops, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Leaves = new DinoFoodEntry(Block.leaves, 0, 15, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry MelonBlock = new DinoFoodEntry(Block.melon, 0, 60, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry BrownMushroom = new DinoFoodEntry(Block.mushroomBrown, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry RedMushroom = new DinoFoodEntry(Block.mushroomRed, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry RedFlower = new DinoFoodEntry(Block.plantRed, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry YellowFlower = new DinoFoodEntry(Block.plantYellow, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry PotatoBlock = new DinoFoodEntry(Block.potato, 0, 25, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Pumpkin = new DinoFoodEntry(Block.pumpkin, 0, 20, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Reed = new DinoFoodEntry(Block.reed, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Sapling = new DinoFoodEntry(Block.sapling, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry TallGrass = new DinoFoodEntry(Block.tallGrass, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry LeavesFougere = new DinoFoodEntry(EGBlockList.Leaves, 0, 50, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry SaplingFougere = new DinoFoodEntry(EGBlockList.Sapling, 0, 15, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry nanoLeaves = null;
	public static DinoFoodEntry nanoSaplings = null;

	// Item
	public static DinoFoodEntry Wheat = new DinoFoodEntry(Item.wheat, 0, 10, 2, EnumFoodType.HERBIVOROUS);// Veggie Foods
	public static DinoFoodEntry Melon = new DinoFoodEntry(Item.melon, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Apple = new DinoFoodEntry(Item.appleRed, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Potato = new DinoFoodEntry(Item.potato, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry BakedPotato = new DinoFoodEntry(Item.bakedPotato, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Cake = new DinoFoodEntry(Item.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Carrot = new DinoFoodEntry(Item.carrot, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Cookie = new DinoFoodEntry(Item.cookie, 0, 15, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry PumpkinPie = new DinoFoodEntry(Item.pumpkinPie, 0, 20, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Sugar = new DinoFoodEntry(Item.sugar, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Bread = new DinoFoodEntry(Item.bread, 0, 25, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry FishRaw = new DinoFoodEntry(Item.fishRaw, 0, 30, 3, EnumFoodType.CARNIVOROUS);// this MUST BE the first carnivore food!
	public static DinoFoodEntry FishCooked = new DinoFoodEntry(Item.fishCooked, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry BeefCooked = new DinoFoodEntry(Item.beefCooked, 0, 50, 5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry BeefRaw = new DinoFoodEntry(Item.beefRaw, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenCooked = new DinoFoodEntry(Item.chickenCooked, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenRaw = new DinoFoodEntry(Item.chickenRaw, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry PorkRaw = new DinoFoodEntry(Item.porkRaw, 0, 30, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry PorkCooked = new DinoFoodEntry(Item.porkCooked, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Egg = new DinoFoodEntry(Item.egg, 0, 10, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Sjl = new DinoFoodEntry(EGItemList.sjl, 0, 30, 3, EnumFoodType.CARNIVOROUS);// SioChiuLe
	public static DinoFoodEntry Nautilus = new DinoFoodEntry(EnumDinoType.Nautilus.DropItem, 0, 20, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenSoupRaw = new DinoFoodEntry(EGItemList.rawChickenSoup, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenSoupCooked = new DinoFoodEntry(EGItemList.cookedChickenSoup, 0, 40, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Triceratops = new DinoFoodEntry(EnumDinoType.Triceratops.DropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Velociraptor = new DinoFoodEntry(EnumDinoType.Velociraptor.DropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry TRex = new DinoFoodEntry(EnumDinoType.TRex.DropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pterosaure = new DinoFoodEntry(EnumDinoType.Pterosaure.DropItem, 0, 15, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Plesiosaure = new DinoFoodEntry(EnumDinoType.Plesiosaure.DropItem, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Mosasaurus = new DinoFoodEntry(EnumDinoType.Mosasaurus.DropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Stegosaurus = new DinoFoodEntry(EnumDinoType.Stegosaurus.DropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Dilophosaurus = new DinoFoodEntry(EnumDinoType.Dilophosaurus.DropItem, 0, 25, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Brachiosaure = new DinoFoodEntry(EnumDinoType.Brachiosaurus.DropItem, 0, 50, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Spinosaurus = new DinoFoodEntry(EnumDinoType.Spinosaurus.DropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Compsognathus = new DinoFoodEntry(EnumDinoType.Compsognathus.DropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Ankylosaurus = new DinoFoodEntry(EnumDinoType.Ankylosaurus.DropItem, 50, 0, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pachycephalosaurus = new DinoFoodEntry(EnumDinoType.Pachycephalosaurus.DropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry DinoMeatCooked = new DinoFoodEntry(EGItemList.cookedDinoMeat, 0, 50, 5, EnumFoodType.CARNIVOROUS);
	
	
	/*//Entity
	public static DinoFoodEntry Player = new DinoFoodEntry(EntityPlayer.class,30,2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Chicken = new DinoFoodEntry(EntityChicken.class,20,2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Cow = new DinoFoodEntry(EntityCow.class,50,5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pig = new DinoFoodEntry(EntityPig.class,30,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Sheep = new DinoFoodEntry(EntitySheep.class,35,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Squid = new DinoFoodEntry(EntitySquid.class,30,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Mob = new DinoFoodEntry(EntityMob.class,20,1, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Nautilus_mob = new DinoFoodEntry(Nautilus.class,100,5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Triceratops_mob = new DinoFoodEntry(Triceratops.class,50,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Velociraptor_mob = new DinoFoodEntry(Velociraptor.class,20,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry TRex_mob = new DinoFoodEntry(TRex.class,70,5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pterosaure_mob = new DinoFoodEntry(Pterosaure.class,35,2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Plesiosaure_mob = new DinoFoodEntry(Plesiosaure.class,50,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Mosasaurus_mob = new DinoFoodEntry(Mosasaurus.class,50,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Stegosaurus_mob = new DinoFoodEntry(Stegosaurus.class,50,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Dilophosaurus_mob = new DinoFoodEntry(Dilophosaurus.class,25,2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Brachiosaurus_mob = new DinoFoodEntry(Brachiosaurus.class,80,5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Spinosaurus_mob = new DinoFoodEntry(Spinosaurus.class,70,5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Compsognathus_mob = new DinoFoodEntry(Compsognathus.class,20,3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Ankylosaurus_mob = new DinoFoodEntry(Ankylosaurus.class,50,3, EnumFoodType.CARNIVOROUS);*/
	
	public FoodList()
	{
		DinoFood.globalDinoFood.add(CakeBlock);
		DinoFood.globalDinoFood.add(CarrotBlock);
		DinoFood.globalDinoFood.add(Crops);
		DinoFood.globalDinoFood.add(Leaves);
		DinoFood.globalDinoFood.add(MelonBlock);
		DinoFood.globalDinoFood.add(BrownMushroom);
		DinoFood.globalDinoFood.add(RedMushroom);
		DinoFood.globalDinoFood.add(RedFlower);
		DinoFood.globalDinoFood.add(YellowFlower);
		DinoFood.globalDinoFood.add(PotatoBlock);
		DinoFood.globalDinoFood.add(Pumpkin);
		DinoFood.globalDinoFood.add(Reed);
		DinoFood.globalDinoFood.add(Sapling);
		DinoFood.globalDinoFood.add(TallGrass);
		DinoFood.globalDinoFood.add(LeavesFougere);
		DinoFood.globalDinoFood.add(SaplingFougere);
		DinoFood.globalDinoFood.add(Wheat);
		DinoFood.globalDinoFood.add(Melon);
		DinoFood.globalDinoFood.add(Apple);
		DinoFood.globalDinoFood.add(Potato);
		DinoFood.globalDinoFood.add(BakedPotato);
		DinoFood.globalDinoFood.add(Cake);
		DinoFood.globalDinoFood.add(Carrot);
		DinoFood.globalDinoFood.add(Cookie);
		DinoFood.globalDinoFood.add(PumpkinPie);
		DinoFood.globalDinoFood.add(Sugar);
		DinoFood.globalDinoFood.add(Bread);

		DinoFood.globalDinoFood.add(FishRaw);// this MUST BE the first carnivore food!
		DinoFood.globalDinoFood.add(FishCooked);
		DinoFood.globalDinoFood.add(BeefCooked);
		DinoFood.globalDinoFood.add(BeefRaw);
		DinoFood.globalDinoFood.add(ChickenCooked);
		DinoFood.globalDinoFood.add(ChickenRaw);
		DinoFood.globalDinoFood.add(PorkRaw);
		DinoFood.globalDinoFood.add(PorkCooked);
		DinoFood.globalDinoFood.add(Egg);
		DinoFood.globalDinoFood.add(Sjl);// SioChiuLe
		DinoFood.globalDinoFood.add(Nautilus);
		DinoFood.globalDinoFood.add(ChickenSoupRaw);
		DinoFood.globalDinoFood.add(ChickenSoupCooked);
		DinoFood.globalDinoFood.add(Triceratops);
		DinoFood.globalDinoFood.add(Velociraptor);
		DinoFood.globalDinoFood.add(TRex);
		DinoFood.globalDinoFood.add(Pterosaure);
		DinoFood.globalDinoFood.add(Plesiosaure);
		DinoFood.globalDinoFood.add(Mosasaurus);
		DinoFood.globalDinoFood.add(Stegosaurus);
		DinoFood.globalDinoFood.add(Dilophosaurus);
		DinoFood.globalDinoFood.add(Brachiosaure);
		DinoFood.globalDinoFood.add(Spinosaurus);
		DinoFood.globalDinoFood.add(Compsognathus);
		DinoFood.globalDinoFood.add(Ankylosaurus);
		DinoFood.globalDinoFood.add(Pachycephalosaurus);
		DinoFood.globalDinoFood.add(DinoMeatCooked);
		if(Loader.isModLoaded("Nanotech_mod"))
		{
			nanoLeaves = new DinoFoodEntry(EGBlockList.nanoLeavesBlock, 0, 50, 3, EnumFoodType.HERBIVOROUS);
			nanoSaplings = new DinoFoodEntry(EGBlockList.nanoSaplingsBlock, 0, 15, 2, EnumFoodType.HERBIVOROUS);
		}
	}
}