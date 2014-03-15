package ere_geologique.common.food;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
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
	public static DinoFoodEntry cakeBlock = new DinoFoodEntry(Blocks.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry carrotBlock = new DinoFoodEntry(Blocks.carrots, 0, 25, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry crops = new DinoFoodEntry(Blocks.wheat, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry leaves = new DinoFoodEntry(Blocks.leaves, 0, 15, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry melonBlock = new DinoFoodEntry(Blocks.melon_stem, 0, 60, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry brownMushroom = new DinoFoodEntry(Blocks.brown_mushroom, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry redMushroom = new DinoFoodEntry(Blocks.red_mushroom, 0, 15, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry redFlower = new DinoFoodEntry(Blocks.red_flower, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry yellowFlower = new DinoFoodEntry(Blocks.yellow_flower, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry potatoBlock = new DinoFoodEntry(Blocks.potatoes, 0, 25, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry pumpkin = new DinoFoodEntry(Blocks.pumpkin, 0, 20, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry reed = new DinoFoodEntry(Blocks.reeds, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry sapling = new DinoFoodEntry(Blocks.sapling, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry tallGrass = new DinoFoodEntry(Blocks.tallgrass, 0, 10, 1, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry leavesFougere = new DinoFoodEntry(EGBlockList.leaves, 0, 50, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry saplingFougere = new DinoFoodEntry(EGBlockList.sapling, 0, 15, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry nanoLeaves = null;
	public static DinoFoodEntry nanoSaplings = null;
	
	public static DinoFoodEntry grimwoodSapling = null;
	public static DinoFoodEntry grimwoodLeaves = null;

	// Item
	public static DinoFoodEntry wheat = new DinoFoodEntry(Items.wheat, 0, 10, 2, EnumFoodType.HERBIVOROUS);// Veggie Foods
	public static DinoFoodEntry melon = new DinoFoodEntry(Items.melon, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry apple = new DinoFoodEntry(Items.apple, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry potato = new DinoFoodEntry(Items.potato, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry bakedPotato = new DinoFoodEntry(Items.baked_potato, 0, 15, 3, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry cake = new DinoFoodEntry(Items.cake, 0, 25, 5, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry carrot = new DinoFoodEntry(Items.carrot, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry cookie = new DinoFoodEntry(Items.cookie, 0, 15, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry pumpkinPie = new DinoFoodEntry(Items.pumpkin_pie, 0, 20, 4, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry sugar = new DinoFoodEntry(Items.sugar, 0, 10, 2, EnumFoodType.HERBIVOROUS);
	public static DinoFoodEntry bread = new DinoFoodEntry(Items.bread, 0, 25, 2, EnumFoodType.HERBIVOROUS);

	public static DinoFoodEntry fishRaw = new DinoFoodEntry(Items.fish, 0, 30, 3, EnumFoodType.CARNIVOROUS);// this MUST BE the first carnivore food!
	public static DinoFoodEntry fishCooked = new DinoFoodEntry(Items.cooked_fished, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry beefCooked = new DinoFoodEntry(Items.cooked_beef, 0, 50, 5, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry beefRaw = new DinoFoodEntry(Items.beef, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry chickenCooked = new DinoFoodEntry(Items.cooked_chicken, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry chickenRaw = new DinoFoodEntry(Items.chicken, 0, 40, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry porkRaw = new DinoFoodEntry(Items.porkchop, 0, 30, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry porkCooked = new DinoFoodEntry(Items.cooked_porkchop, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry egg = new DinoFoodEntry(Items.egg, 0, 10, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry sjl = new DinoFoodEntry(EGItemList.sjl, 0, 30, 3, EnumFoodType.CARNIVOROUS);// SioChiuLe
	public static DinoFoodEntry nautilus = new DinoFoodEntry(EnumDinoType.Nautilus.dropItem, 0, 20, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry chickenSoupRaw = new DinoFoodEntry(EGItemList.rawChickenSoup, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry chickenSoupCooked = new DinoFoodEntry(EGItemList.cookedChickenSoup, 0, 40, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry triceratops = new DinoFoodEntry(EnumDinoType.Triceratops.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry velociraptor = new DinoFoodEntry(EnumDinoType.Velociraptor.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry tRex = new DinoFoodEntry(EnumDinoType.TRex.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry pterosaure = new DinoFoodEntry(EnumDinoType.Pterosaure.dropItem, 0, 15, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry plesiosaure = new DinoFoodEntry(EnumDinoType.Plesiosaure.dropItem, 0, 30, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry mosasaurus = new DinoFoodEntry(EnumDinoType.Mosasaurus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry stegosaurus = new DinoFoodEntry(EnumDinoType.Stegosaurus.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry dilophosaurus = new DinoFoodEntry(EnumDinoType.Dilophosaurus.dropItem, 0, 25, 2, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry brachiosaure = new DinoFoodEntry(EnumDinoType.Brachiosaurus.dropItem, 0, 50, 4, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry spinosaurus = new DinoFoodEntry(EnumDinoType.Spinosaurus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry compsognathus = new DinoFoodEntry(EnumDinoType.Compsognathus.dropItem, 0, 20, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry ankylosaurus = new DinoFoodEntry(EnumDinoType.Ankylosaurus.dropItem, 50, 0, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry pachycephalosaurus = new DinoFoodEntry(EnumDinoType.Pachycephalosaurus.dropItem, 0, 50, 3, EnumFoodType.CARNIVOROUS);
	public static DinoFoodEntry dinoMeatCooked = new DinoFoodEntry(EGItemList.cookedDinoMeat, 0, 50, 5, EnumFoodType.CARNIVOROUS);
	
	
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
		DinoFood.globalDinoFood.add(cakeBlock);
		DinoFood.globalDinoFood.add(carrotBlock);
		DinoFood.globalDinoFood.add(crops);
		DinoFood.globalDinoFood.add(leaves);
		DinoFood.globalDinoFood.add(melonBlock);
		DinoFood.globalDinoFood.add(brownMushroom);
		DinoFood.globalDinoFood.add(redMushroom);
		DinoFood.globalDinoFood.add(redFlower);
		DinoFood.globalDinoFood.add(yellowFlower);
		DinoFood.globalDinoFood.add(potatoBlock);
		DinoFood.globalDinoFood.add(pumpkin);
		DinoFood.globalDinoFood.add(reed);
		DinoFood.globalDinoFood.add(sapling);
		DinoFood.globalDinoFood.add(tallGrass);
		DinoFood.globalDinoFood.add(leavesFougere);
		DinoFood.globalDinoFood.add(saplingFougere);
		DinoFood.globalDinoFood.add(wheat);
		DinoFood.globalDinoFood.add(melon);
		DinoFood.globalDinoFood.add(apple);
		DinoFood.globalDinoFood.add(potato);
		DinoFood.globalDinoFood.add(bakedPotato);
		DinoFood.globalDinoFood.add(cake);
		DinoFood.globalDinoFood.add(carrot);
		DinoFood.globalDinoFood.add(cookie);
		DinoFood.globalDinoFood.add(pumpkinPie);
		DinoFood.globalDinoFood.add(sugar);
		DinoFood.globalDinoFood.add(bread);

		DinoFood.globalDinoFood.add(fishRaw);// this MUST BE the first carnivore food!
		DinoFood.globalDinoFood.add(fishCooked);
		DinoFood.globalDinoFood.add(beefCooked);
		DinoFood.globalDinoFood.add(beefRaw);
		DinoFood.globalDinoFood.add(chickenCooked);
		DinoFood.globalDinoFood.add(chickenRaw);
		DinoFood.globalDinoFood.add(porkRaw);
		DinoFood.globalDinoFood.add(porkCooked);
		DinoFood.globalDinoFood.add(egg);
		DinoFood.globalDinoFood.add(sjl);// SioChiuLe
		DinoFood.globalDinoFood.add(nautilus);
		DinoFood.globalDinoFood.add(chickenSoupRaw);
		DinoFood.globalDinoFood.add(chickenSoupCooked);
		DinoFood.globalDinoFood.add(triceratops);
		DinoFood.globalDinoFood.add(velociraptor);
		DinoFood.globalDinoFood.add(tRex);
		DinoFood.globalDinoFood.add(pterosaure);
		DinoFood.globalDinoFood.add(plesiosaure);
		DinoFood.globalDinoFood.add(mosasaurus);
		DinoFood.globalDinoFood.add(stegosaurus);
		DinoFood.globalDinoFood.add(dilophosaurus);
		DinoFood.globalDinoFood.add(brachiosaure);
		DinoFood.globalDinoFood.add(spinosaurus);
		DinoFood.globalDinoFood.add(compsognathus);
		DinoFood.globalDinoFood.add(ankylosaurus);
		DinoFood.globalDinoFood.add(pachycephalosaurus);
		DinoFood.globalDinoFood.add(dinoMeatCooked);

		if(Loader.isModLoaded("NanotechMod"))
		{
		    Block nanoLeavesBlock, nanoSaplingsBlock;
    		try
    		{
    			nanoLeavesBlock = (Block)Class.forName("fr.mcnanotech.kevin_68.nanotech_mod.main.blocks.NanotechBlock").getField("nanoLeaves").get(null);
    			nanoSaplingsBlock = (Block)Class.forName("fr.mcnanotech.kevin_68.nanotech_mod.main.blocks.NanotechBlock").getField("nanoSaplings").get(null);
    			nanoLeaves = new DinoFoodEntry(nanoLeavesBlock, 0, 50, 3, EnumFoodType.HERBIVOROUS);
    			nanoSaplings = new DinoFoodEntry(nanoSaplingsBlock, 0, 15, 2, EnumFoodType.HERBIVOROUS);
    			DinoFood.globalDinoFood.add(nanoLeaves);
    			DinoFood.globalDinoFood.add(nanoSaplings);
    		}
    		catch(Exception ex)
    		{
    			EreGeologique.egLog.severe("Erreur lors de l'initialisation de Nanotech_mod");
    		}
    		EreGeologique.egLog.info("Initialisation de Nanotech_mod terminé");
		}
		
		if(Loader.isModLoaded("nether_plus"))
		{
			Block grimwoodSaplingsBlock, grimwoodLeavesBlock;
			try
			{
				grimwoodSaplingsBlock = (Block)Class.forName("nether_plus.common.block.NPBlockList").getField("grimwoodSapling").get(null);
				grimwoodLeavesBlock = (Block)Class.forName("nether_plus.common.block.NPBlockList").getField("grimwoodLeaves").get(null);
				grimwoodSapling = new DinoFoodEntry(grimwoodSaplingsBlock, 0, 15, 2, EnumFoodType.HERBIVOROUS);
				grimwoodLeaves = new DinoFoodEntry(grimwoodLeavesBlock, 0, 50, 3, EnumFoodType.HERBIVOROUS);
    			DinoFood.globalDinoFood.add(grimwoodSapling);
    			DinoFood.globalDinoFood.add(grimwoodLeaves);
			}
			catch(Exception ex)
			{
				EreGeologique.egLog.severe("Erreur lors de l'initialisation de Nether_Plus");
			}
			EreGeologique.egLog.info("Initialisation de Nether_Plus terminé");
		}
	}
}