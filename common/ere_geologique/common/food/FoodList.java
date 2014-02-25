package ere_geologique.common.food;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import cpw.mods.fml.common.Loader;
import ere_geologique.api.food.DinoFood;
import ere_geologique.api.food.DinoFood.DinoFoodEntry;
import ere_geologique.api.food.EnumFoodType;
import ere_geologique.common.EreGeologique;
import ere_geologique.common.block.EGBlockList;
import ere_geologique.common.entity.enums.EnumDinoType;
import ere_geologique.common.item.EGItemList;

public class FoodList
{
	// Block
	public static DinoFoodEntry CakeBlock = new DinoFoodEntry(Blocks.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry CarrotBlock = new DinoFoodEntry(Blocks.carrots, 0, 25, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Crops = new DinoFoodEntry(Blocks.wheat, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Leaves = new DinoFoodEntry(Blocks.leaves, 0, 15, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry MelonBlock = new DinoFoodEntry(Blocks.melon_stem, 0, 60, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry BrownMushroom = new DinoFoodEntry(Blocks.brown_mushroom, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry RedMushroom = new DinoFoodEntry(Blocks.red_mushroom, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry RedFlower = new DinoFoodEntry(Blocks.red_flower, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry YellowFlower = new DinoFoodEntry(Blocks.yellow_flower, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry PotatoBlock = new DinoFoodEntry(Blocks.potatoes, 0, 25, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Pumpkin = new DinoFoodEntry(Blocks.pumpkin, 0, 20, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Reed = new DinoFoodEntry(Blocks.reeds, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Sapling = new DinoFoodEntry(Blocks.sapling, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry TallGrass = new DinoFoodEntry(Blocks.tallgrass, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry LeavesFougere = new DinoFoodEntry(EGBlockList.leaves, 0, 50, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry SaplingFougere = new DinoFoodEntry(EGBlockList.sapling, 0, 15, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry nanoLeaves = null;
	public static DinoFoodEntry nanoSaplings = null;

	// Item
	public static DinoFoodEntry Wheat = new DinoFoodEntry(Items.wheat, 0, 10, 2, EnumFoodType.HERBIVOROUS);// Veggie Foods
	public static DinoFoodEntry Melon = new DinoFoodEntry(Items.melon, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Apple = new DinoFoodEntry(Items.apple, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Potato = new DinoFoodEntry(Items.potato, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry BakedPotato = new DinoFoodEntry(Items.baked_potato, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Cake = new DinoFoodEntry(Items.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Carrot = new DinoFoodEntry(Items.carrot, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Cookie = new DinoFoodEntry(Items.cookie, 0, 15, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry PumpkinPie = new DinoFoodEntry(Items.pumpkin_pie, 0, 20, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Sugar = new DinoFoodEntry(Items.sugar, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry Bread = new DinoFoodEntry(Items.bread, 0, 25, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry FishRaw = new DinoFoodEntry(Items.fish, 0, 30, 3, EnumFoodType.CARNIVOROUS);// this MUST BE the first carnivore food!
	public static DinoFoodEntry FishCooked = new DinoFoodEntry(Items.cooked_fished, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry BeefCooked = new DinoFoodEntry(Items.cooked_beef, 0, 50, 5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry BeefRaw = new DinoFoodEntry(Items.beef, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenCooked = new DinoFoodEntry(Items.cooked_chicken, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenRaw = new DinoFoodEntry(Items.chicken, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry PorkRaw = new DinoFoodEntry(Items.porkchop, 0, 30, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry PorkCooked = new DinoFoodEntry(Items.cooked_porkchop, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Egg = new DinoFoodEntry(Items.egg, 0, 10, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Sjl = new DinoFoodEntry(EGItemList.sjl, 0, 30, 3, EnumFoodType.CARNIVOROUS);// SioChiuLe
	public static DinoFoodEntry Nautilus = new DinoFoodEntry(EnumDinoType.Nautilus.dropItem, 0, 20, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenSoupRaw = new DinoFoodEntry(EGItemList.rawChickenSoup, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ChickenSoupCooked = new DinoFoodEntry(EGItemList.cookedChickenSoup, 0, 40, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Triceratops = new DinoFoodEntry(EnumDinoType.Triceratops.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Velociraptor = new DinoFoodEntry(EnumDinoType.Velociraptor.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry TRex = new DinoFoodEntry(EnumDinoType.TRex.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pterosaure = new DinoFoodEntry(EnumDinoType.Pterosaure.dropItem, 0, 15, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Plesiosaure = new DinoFoodEntry(EnumDinoType.Plesiosaure.dropItem, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Mosasaurus = new DinoFoodEntry(EnumDinoType.Mosasaurus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Stegosaurus = new DinoFoodEntry(EnumDinoType.Stegosaurus.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Dilophosaurus = new DinoFoodEntry(EnumDinoType.Dilophosaurus.dropItem, 0, 25, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Brachiosaure = new DinoFoodEntry(EnumDinoType.Brachiosaurus.dropItem, 0, 50, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Spinosaurus = new DinoFoodEntry(EnumDinoType.Spinosaurus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Compsognathus = new DinoFoodEntry(EnumDinoType.Compsognathus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Ankylosaurus = new DinoFoodEntry(EnumDinoType.Ankylosaurus.dropItem, 50, 0, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry Pachycephalosaurus = new DinoFoodEntry(EnumDinoType.Pachycephalosaurus.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
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
	
	public static void load()
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
		    Block nanoLeavesBlock, nanoSaplingsBlock;
    		try
    		{
    			nanoLeavesBlock = (Block)Class.forName("fr.mcnanotech.kevin_68.nanotech_mod.main.blocks.NanotechBlock").getField("nanoLeaves").get(null);
    			nanoSaplingsBlock = (Block)Class.forName("fr.mcnanotech.kevin_68.nanotech_mod.main.blocks.NanotechBlock").getField("nanoSaplings").get(null);
    			nanoLeaves = new DinoFoodEntry(nanoLeavesBlock, 0, 50, 3, EnumFoodType.HERBIVOROUS);
    			nanoSaplings = new DinoFoodEntry(nanoSaplingsBlock, 0, 15, 2, EnumFoodType.HERBIVOROUS);
    		}
    		catch(Exception ex)
    		{
    			EreGeologique.EGLog.severe("Erreur lors de l'initialisation du Nanotech_mod");
    		}
    		EreGeologique.EGLog.info("Initialisation de Nanotech_mod terminé");
		}
	}
}