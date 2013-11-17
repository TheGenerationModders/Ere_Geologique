package ere_geologique.common.entity.Enums;

import net.minecraft.item.Item;
import ere_geologique.common.block.Fossil;
import ere_geologique.common.entity.Ankylosaurus;
import ere_geologique.common.entity.Brachiosaurus;
import ere_geologique.common.entity.Compsognathus;
import ere_geologique.common.entity.Dilophosaurus;
import ere_geologique.common.entity.Mosasaurus;
import ere_geologique.common.entity.Nautilus;
import ere_geologique.common.entity.Pachycephalosaurus;
import ere_geologique.common.entity.Plesiosaure;
import ere_geologique.common.entity.Pterosaure;
import ere_geologique.common.entity.Spinosaurus;
import ere_geologique.common.entity.Stegosaurus;
import ere_geologique.common.entity.TRex;
import ere_geologique.common.entity.Triceratops;
import ere_geologique.common.entity.Velociraptor;
import ere_geologique.common.entity.food.DinoFoodBlockList;
import ere_geologique.common.entity.food.DinoFoodItemList;
import ere_geologique.common.entity.food.DinoFoodMobList;
import ere_geologique.common.item.EGItemList;

interface C
{
	public static final int NOTHING = 0;
	
	public static final int NO_FEEDER = 0 << 0;//Bits 0+1:	Dinos Can't use Feeder at all
	public static final int HERBIVORE = 1 << 0;//Bit 0:		Dino can use Herbivore Part of Feeder 
	public static final int CARNIVORE = 2 << 0;//Bit 1: 	Dino can use Carnivore Part of Feeder 
	public static final int HERB_CARN = 3 << 0;//Bits 0+1: 	Dinos can use Herbivore and Carnivore Part of Feeder 
	
	public static final int MODEL =1 << 2;//Bit 2: Dino is Modelable
	public static final int TAME =1 << 3;//Bit 3: Dino is Tameable
	public static final int RIDE =1 << 4;//Bit 4: Dino is Rideable
	public static final int CARRY =1 << 5;//Bit 5: Dino can Carry Items
}

public enum EnumDinoType
{
	//											C.MODEL	| C.TAME	| C.RIDE	| C.HERBIVORE/CARNIVORE
    Triceratops(Triceratops.class, 				C.MODEL | C.TAME  	| C.RIDE 	| C.HERBIVORE),
    Velociraptor(Velociraptor.class,					  C.TAME  				| C.CARNIVORE),
    TRex(TRex.class, 							  		  C.TAME  	| C.RIDE  	| C.CARNIVORE),
    Pterosaure(Pterosaure.class, 				C.MODEL | C.TAME  	| C.RIDE 	| C.CARNIVORE),
    Plesiosaure(Plesiosaure.class, 				C.MODEL | C.TAME  	| C.RIDE 	| C.CARNIVORE),
    Stegosaurus(Stegosaurus.class, 				C.MODEL | C.TAME  				| C.HERBIVORE),
    Dilophosaurus(Dilophosaurus.class,					  C.TAME  	| C.CARRY 	| C.CARNIVORE),
    Brachiosaurus(Brachiosaurus.class,			C.MODEL | C.TAME  	| C.RIDE 	| C.HERBIVORE),
    Spinosaurus(Spinosaurus.class, 						  C.TAME				| C.CARNIVORE),
    Compsognathus(Compsognathus.class,					  C.TAME				| C.CARNIVORE),
    Ankylosaurus(Ankylosaurus.class,  					  C.TAME  	| C.RIDE  	| C.HERBIVORE),
    Pachycephalosaurus(Pachycephalosaurus.class,		  C.TAME 				| C.HERBIVORE),
    Mosasaurus(Mosasaurus.class, 						  		  				  C.CARNIVORE),
    Nautilus(Nautilus.class, 					C.NOTHING);
    
    private final Class dinoClass;

    public int Flags=0;
    public Item OrderItem;
    public Item DropItem;
    public Item DNAItem;
    public Item EggItem;
    
    //List of the eatable Items with the FoodValue and HealingValue belonging to
    public DinoFoodItemList FoodItemList;
    
    //List of the eatable Blocks with the FoodValue and HealingValue belonging to
    public DinoFoodBlockList FoodBlockList;
    
    //List of the eatable Mobs with the FoodValue and HealingValue belonging to
    public DinoFoodMobList FoodMobList;
    
    
    //Starting width and increase of the Dino
    public float Width0=0.5F;
    public float WidthInc=0.4F;
    
    //Starting length and increase of the Dino
    public float Length0=0.5F;
    public float LengthInc=0.2F;
    
    //Starting height and increase of the dino
    public float Height0=0.5F;
    public float HeightInc=0.2F;
    
    
    
    //Age Limit of The Dino, standard is 12
    public int MaxAge = 999;
    //Age When Dino gets adult, starts Breeding, is Ridable...
    public int AdultAge = 6;
    //Age When Dino gets teen..
    public int TeenAge = 3;
    
    
    
    //Health of the Dino when hatched
    public float Health0 = 20;
    public float HealthInc = 2;
    
    //The attacking strength of the Dino when hatched
    public int Strength0 = 2;
    public int StrengthInc = 1;
    
    //The speed of the dino when hatched
    public float Speed0 = 0.25F;
    public float SpeedInc = 0.015F;
    
    
    
    //The Breeding time of the dinosaur, standard value 3000 ticks
    public int BreedingTicks = 3000;
    //Ticks the Dino needs for aging, standard 12000
    public int AgingTicks = 12000;
    
    //Hunger Limit of the Dino, standard is 100
    public int MaxHunger = 100;
    //The Level below which the dino starts looking for food. Standard is 0.8 [times MaxHunger]
    public float HungryLevel = 0.8f;
    
    
    //Base Experience Points of the Dino
    public float Exp0 = 1.0f;
    //Experience increase per day for the dino
    public float ExpInc = 0.2f;

	private float DinoSizeMin = 1.0f;
	private float DinoSizeMax = 10.0f;
    
    
    /**
     * Params: Class, Flags
     */
    private EnumDinoType(Class class0, int f0)
    {
        this.dinoClass = class0;
        this.Flags=f0;
        this.FoodItemList = new DinoFoodItemList();
        this.FoodBlockList = new DinoFoodBlockList();
        this.FoodMobList = new DinoFoodMobList();
    }
    
    /**
     * sets the OrderItem,DropItem,DNAItem and EggItem for the Dino
     */
    private void setItems(Item order)//,Item drop,Item dna,Item egg)
    {
    	//this.DropItem = drop;
        //this.DNAItem = dna;
        //this.EggItem = egg;
        this.OrderItem = order;
    }
    
    /**
     * sets the TeenAge, AdultAge and MaxAge for the Dino
     */
    private void setAges(int Teen, int Adult, int Max)
    {
    	if(Teen>0)this.TeenAge=Teen;
    	if(Adult>0)this.AdultAge=Adult;
    	if(Max>0)this.MaxAge=Max;
    }
    
    private void setDinoSize(float SizeMin, float SizeMax)
    {
    	this.DinoSizeMin = SizeMin;
    	this.DinoSizeMax = SizeMax;
    }
    /**
     * sets the Dimensions for the Dino: starting width,width increase,same for length and height
     */
    private void setDimensions(float W0,float WInc,float L0,float LInc,float H0,float HInc)
    {
    	if(W0>0)this.Width0=W0;
    	if(WInc>0)this.WidthInc=WInc;
    	if(L0>0)this.Length0=L0;
    	if(LInc>0)this.LengthInc=LInc;
    	if(H0>0)this.Height0=H0;
    	if(HInc>0)this.HeightInc=HInc;
    }
    
    /**
     * sets the starting values and increase for Health,Attack Strength and Speed and the MaxHunger Value
     */
    private void setProperties(float H0,float HInc,int Str0,int StrInc,float Sp0,float SpInc, int Hunger)
    {
    	if(H0>0)this.Health0=H0;
    	if(Str0>0)this.Strength0=Str0;
    	if(Sp0>0)this.Speed0=Sp0;
    	
    	if(HInc>0)this.HealthInc=HInc;
    	if(StrInc>0)this.StrengthInc=StrInc;
    	if(SpInc>0)this.SpeedInc=SpInc;
    	
    	if(Hunger>0)this.MaxHunger=Hunger;
    }
    
    /**
     * sets the breeding time, the aging time and the hungry-level
     * Hungry level h:below h*MaxHunger, the dinos starts looking for food, below (1-h)*Maxhunger, the dino is starving
     */
    private void setAddProperties(int Breed,int Age, float HLevel)
    {
    	if(Breed>0)this.BreedingTicks=Breed;
    	if(Age>0)this.AgingTicks=Age;
    	
    	if(HLevel>0)this.HungryLevel=HLevel;
    }
    
    /**
     * sets the breeding time, the aging time and the hungry-level
     * Hungry level h:below h*MaxHunger, the dinos starts looking for food, below (1-h)*Maxhunger, the dino is starving
     */
    private void setExperience(float E0,float EInc)
    {
    	if(E0>0)this.Exp0=E0;
    	if(EInc>0)this.ExpInc=EInc;
    }
    
    /**
     * Initializes all individual Dino values
     */
    public static void init()
    {//								Order Item			Drop Item				DNA Item				Egg Item
    	Triceratops.setItems(		Item.stick);//,			Fossil.rawTriceratops, 	Fossil.dnaTriceratops, 	Fossil.eggTriceratops);
    	Triceratops.setDimensions(1.2F, 0.4F, 1.1F, 0.7F, 1.2F, 0.36F);
    	Triceratops.setAges(-1, -1, 13);
    	Triceratops.setProperties(21, 1, 3, -1, -1F, 0.016F, 500);
    	Triceratops.setExperience(0.5F, 0.2F);
    	
    	Triceratops.FoodItemList.addItem(EnumDinoFoodItem.Wheat);
    	Triceratops.FoodItemList.addItem(EnumDinoFoodItem.Melon);
    	Triceratops.FoodItemList.addItem(EnumDinoFoodItem.Apple);
    	Triceratops.FoodItemList.addItem(EnumDinoFoodItem.Bread);
    	Triceratops.FoodItemList.addItem(EnumDinoFoodItem.Potato);
    	
//    	Triceratops.FoodBlockList.addblock(EnumDinoFoodBlock.Ferns);
    	Triceratops.FoodBlockList.addblock(EnumDinoFoodBlock.Leaves);
//        Triceratops.FoodBlockList.addblock(EnumDinoFoodBlock.Palae);
        Triceratops.FoodBlockList.addblock(EnumDinoFoodBlock.RedFlower);
        Triceratops.FoodBlockList.addblock(EnumDinoFoodBlock.YellowFlower);
    	
        Pachycephalosaurus.setItems(       Item.stick);//,         Fossil.rawPachycephalosaurus,  Fossil.dnaPachycephalosaurus,  Fossil.eggPachycephalosaurus);
        Pachycephalosaurus.setDimensions(0.4F, 0.23F, 0.4F, 0.23F, 0.4F, 0.23F);
        Pachycephalosaurus.setAges(-1, -1, 13);
        Pachycephalosaurus.setProperties(22, 3, 3, -1, -1F, 0.016F, 500);
        Pachycephalosaurus.setExperience(0.5F, 0.2F);
        
        Pachycephalosaurus.FoodItemList.addItem(EnumDinoFoodItem.Wheat);
        Pachycephalosaurus.FoodItemList.addItem(EnumDinoFoodItem.Melon);
        Pachycephalosaurus.FoodItemList.addItem(EnumDinoFoodItem.Apple);
        Pachycephalosaurus.FoodItemList.addItem(EnumDinoFoodItem.Bread);
        Pachycephalosaurus.FoodItemList.addItem(EnumDinoFoodItem.Carrot);
        
//        Pachycephalosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Ferns);
        Pachycephalosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Leaves);
//        Pachycephalosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Palae);
        Pachycephalosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.RedFlower);
        Pachycephalosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.YellowFlower);
    	
        Ankylosaurus.setItems(       Item.stick);//,         Fossil.rawAnkylosaurus,  Fossil.dnaAnkylosaurus,  Fossil.eggAnkylosaurus);
        Ankylosaurus.setDinoSize(1.0F, 10.0F);
        Ankylosaurus.setDimensions(0.5F, 0.25F, 0.5F, 0.25F, 0.5F, 0.25F);
        Ankylosaurus.setAges(-1, 6, 13);
        Ankylosaurus.setProperties(21, 3, 4, 3, 0.17F, 0.016F, 500);
        Ankylosaurus.setExperience(0.5F, 0.2F);
        
        Ankylosaurus.FoodItemList.addItem(EnumDinoFoodItem.Wheat);
        Ankylosaurus.FoodItemList.addItem(EnumDinoFoodItem.Melon);
        Ankylosaurus.FoodItemList.addItem(EnumDinoFoodItem.Apple);
        Ankylosaurus.FoodItemList.addItem(EnumDinoFoodItem.Bread);
        Ankylosaurus.FoodItemList.addItem(EnumDinoFoodItem.Potato);
        
//        Ankylosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Ferns);
        Ankylosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Leaves);
//        Ankylosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Palae);
        Ankylosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.RedFlower);
        Ankylosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.YellowFlower);
    	
        Velociraptor.setItems(		Item.bone);//,			Fossil.rawVelociraptor, Fossil.dnaVelociraptor, Fossil.eggVelociraptor);
        Velociraptor.setDimensions(0.3F, 0.12F, 0.3F, 0.13F, 0.3F, 0.1F);
        Velociraptor.setAges(-1, -1, -1);
        Velociraptor.setProperties(21, 1, -1, -1, 0.3F, 0.025F, -1);
        Velociraptor.setExperience(0.7F, 0.7F);
        
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.PorkRaw);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.PorkCooked);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.BeefRaw);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.BeefCooked);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.DinoMeatCooked);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.Triceratops);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.Stegosaurus);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.Plesiosaure);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.Pterosaure);
        Velociraptor.FoodItemList.addItem(EnumDinoFoodItem.Brachiosaure);
        
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Cow);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Stegosaurus);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Plesiosaure);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Brachiosaurus);
//        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
        
        Compsognathus.setItems(      Item.bone);//,          Fossil.rawCompsognathus, Fossil.dnaCompsognathus, Fossil.eggCompsognathus);
        Compsognathus.setDimensions(0.5F, 0.05F, 0.5F, 0.05F, 0.5F, 0.05F);
        Compsognathus.setAges(-1, -1, 9);
        Compsognathus.setProperties(1, 1, 1, 0, 0.2F, 0.010F, -1);
        Compsognathus.setExperience(0.2F, 0.2F);
        
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.PorkRaw);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.PorkCooked);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.BeefRaw);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.BeefCooked);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.DinoMeatCooked);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.Triceratops);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.Stegosaurus);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.Plesiosaure);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.Pterosaure);
        Compsognathus.FoodItemList.addItem(EnumDinoFoodItem.Brachiosaure);
        
        Compsognathus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Compsognathus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
//        Compsognathus.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
        
        TRex.setItems(				EGItemList.SkullStick);//,	Fossil.rawTRex, 		Fossil.dnaTRex, 		Fossil.eggTRex);
        TRex.setDimensions(0.7F, 0.57F, 0.8F, 0.66F, 0.5F, 0.57F);
        TRex.setAges(-1, -1, 23);
        TRex.setProperties(-1, 5, 4, -1, 0.22F, 0.02F, 250);
        TRex.setExperience(1F, 1F);
        
        TRex.FoodItemList.addItem(EnumDinoFoodItem.PorkRaw);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.BeefRaw);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.ChickenRaw);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Triceratops);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Stegosaurus);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Dilophosaurus);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Plesiosaure);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Pterosaure);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Brachiosaure);
        TRex.FoodItemList.addItem(EnumDinoFoodItem.Velociraptor);
        
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Cow);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Sheep);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Stegosaurus);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Dilophosaurus);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Plesiosaure);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Brachiosaurus);
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Velociraptor);
//        TRex.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
        
        Pterosaure.setItems(			Item.arrow);//,			Fossil.rawPterosaur, 	Fossil.dnaPterosaur, 	Fossil.eggPterosaur);
        Pterosaure.setDimensions(1.2F, 0.3F, 1.0F, 0.4F, 0.8F, 0.2F);
        Pterosaure.setAges(-1, -1, 9);
        Pterosaure.setProperties(21, 1, -1, -1, -1F, -1F, -1);
        Pterosaure.setExperience(0.3F, 0.3F);
        
        Pterosaure.FoodItemList.addItem(EnumDinoFoodItem.FishRaw);
        Pterosaure.FoodItemList.addItem(EnumDinoFoodItem.FishCooked);
        Pterosaure.FoodItemList.addItem(EnumDinoFoodItem.Sjl);
        Pterosaure.FoodItemList.addItem(EnumDinoFoodItem.ChickenRaw);
        
        Pterosaure.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        
        Nautilus.setItems(			null);//,				Fossil.rawNautilus, 	Fossil.dnaNautilus, 	Fossil.shellNautilus);
        Nautilus.setExperience(1.0F, 0F);
        
        Plesiosaure.setItems(		EGItemList.MagicConch);//,	Fossil.rawPlesiosaur, 	Fossil.dnaPlesiosaur, 	Fossil.eggPlesiosaur);
        Plesiosaure.setDimensions(0.5F, 0.3F, 0.5F, 0.5F, 0.5F, 0.3F);
        Plesiosaure.setProperties(30, 10, 3, -1, 0.18F, 0.02F, 500);
        Plesiosaure.setExperience(0.5F, 0.25F);
        
        Plesiosaure.FoodItemList.addItem(EnumDinoFoodItem.FishRaw);
        Plesiosaure.FoodItemList.addItem(EnumDinoFoodItem.FishCooked);
        Plesiosaure.FoodItemList.addItem(EnumDinoFoodItem.Sjl);
        Plesiosaure.FoodItemList.addItem(EnumDinoFoodItem.ChickenRaw);
        
        Plesiosaure.FoodMobList.addMob(EnumDinoFoodMob.Nautilus);
        Plesiosaure.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
//        Plesiosaure.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
        
        Mosasaurus.setItems(		null);//,				Fossil.rawMosasaurus, 	Fossil.dnaMosasaurus, 	Fossil.eggMosasaurus);
        Mosasaurus.setDimensions(0.25F, 0.4F, 0.5F, 0.45F, 0.4F, 0.25F);
        Mosasaurus.setAges(-1, 8, 20);
        Mosasaurus.setProperties(50, 10, 4, 2, 0.3F, 0.4F, 500);
        Mosasaurus.setExperience(1F, 1F);
        
        Mosasaurus.FoodMobList.addMob(EnumDinoFoodMob.Squid);
        Mosasaurus.FoodMobList.addMob(EnumDinoFoodMob.Nautilus);
        
        Stegosaurus.setItems(		Item.stick);//,			Fossil.rawStegosaurus, 	Fossil.dnaStegosaurus, 	Fossil.eggStegosaurus);
        Stegosaurus.setDimensions(1.2F, 0.5F, 1.0F, 0.7F, 1.2F, 0.36F);
        Stegosaurus.setAges(-1, -1, 13);
        Stegosaurus.setProperties(21, 1, -1, -1, -1F, -1F, 500);
        Stegosaurus.setExperience(0.5F, 0.2F);
        
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Wheat);
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Melon);
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Carrot);
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Sugar);
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Cookie);
        Stegosaurus.FoodItemList.addItem(EnumDinoFoodItem.Bread);
        
//        Stegosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Ferns);
        Stegosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Leaves);
//        Stegosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Palae);
        Stegosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.RedFlower);
        Stegosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.YellowFlower);
        
        Dilophosaurus.setItems(		Item.bone);//,			Fossil.rawDilophosaurus,Fossil.dnaDilophosaurus,Fossil.eggDilophosaurus);
        Dilophosaurus.setDimensions(0.4F, 0.16F, 0.4F, 0.17F, 0.4F, 0.16F);
        Dilophosaurus.setAges(-1, -1, 9);
        Dilophosaurus.setProperties(-1, -1, -1, -1, 0.21F, 0.026F, -1);
        Dilophosaurus.setExperience(1F, 1F);
        
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.PorkRaw);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.PorkCooked);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.ChickenRaw);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.ChickenCooked);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.DinoMeatCooked);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.Pterosaure);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.Triceratops);
        Dilophosaurus.FoodItemList.addItem(EnumDinoFoodItem.Egg);
        
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
//        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
        
        Brachiosaurus.setItems(		Item.stick);//,			Fossil.rawBrachiosaurus,Fossil.dnaBrachiosaurus,Fossil.eggBrachiosaurus);
        Brachiosaurus.setDimensions(1.0F, 0.5F, 1.0F, 0.5F, 1.0F, 0.5F);
        Brachiosaurus.setAges(6, 12, 36);
        Brachiosaurus.setProperties(-1, 5, -1, -1, 0.3F, 0.012F, 500);
        Brachiosaurus.setExperience(0.6F, 0.15F);
        
        Brachiosaurus.FoodItemList.addItem(EnumDinoFoodItem.Sugar);
        Brachiosaurus.FoodItemList.addItem(EnumDinoFoodItem.Cookie);
        Brachiosaurus.FoodItemList.addItem(EnumDinoFoodItem.Apple);
        
//        Brachiosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Palae);
        Brachiosaurus.FoodBlockList.addblock(EnumDinoFoodBlock.Leaves);
		
		Spinosaurus.setItems(		EGItemList.SkullStick);//,	Fossil.rawSpinosaurus,	Fossil.dnaSpinosaurus,	Fossil.eggSpinosaurus);
//		Spinosaurus.setDimensions(0.5F,0.3F,0.5F,0.3F,0.5F,0.3F); New model dimensions
      Spinosaurus.setDimensions(0.8F,0.7F,0.5F,0.7F,0.5F,0.7F); // Dimensions for Dragonith's Spinosaur.
		Spinosaurus.setAges(-1, -1, 23);
		Spinosaurus.setProperties(-1, 5, 4, -1, 0.24F, 0.021F,550);
		Spinosaurus.setExperience(0F,0.9F);
		
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.PorkRaw);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.BeefRaw);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.ChickenRaw);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Triceratops);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Stegosaurus);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Dilophosaurus);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Plesiosaure);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Pterosaure);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Brachiosaure);
		Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.Velociraptor);
	    Spinosaurus.FoodItemList.addItem(EnumDinoFoodItem.FishRaw);

        
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Cow);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Stegosaurus);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Dilophosaurus);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Plesiosaure);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Velociraptor);
//	    Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Dodo);
    }
    
    /**
     *      * takes an item and returns if it is a Dino DNA
     */
    public static boolean isDinoDNA(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DNAItem.itemID == i0.itemID)
		    	return true;
		}
		return false;
    }
    
    /**
     * takes an Item and returns if it is a dino drop
     */
    public static boolean isDinoDrop(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DropItem.itemID == i0.itemID)
		    	return true;
		}
		return false;
    }
    
    /**
     * takes an item, if it is a dinos dropItem or EggItem, it returns the corresponding DNA Item
     */
    public static Item getDNA(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DropItem.itemID == i0.itemID || values()[i].EggItem.itemID == i0.itemID)
		    	return values()[i].DNAItem;
		}
		return null;
    }

    /**
     * takes an item, if it is a dinos DNAItem or EggItem, it returns the corresponding Drop Item
     */
    public static Item getDrop(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DNAItem.itemID == i0.itemID || values()[i].EggItem.itemID == i0.itemID)
		    	return values()[i].DropItem;
		}
		return null;
    }

    /**
     * takes an item, if it is a dinos DNAItem or DropItem, it returns the corresponding Egg Item
     */
    public static Item getEgg(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DNAItem.itemID == i0.itemID || values()[i].DropItem.itemID == i0.itemID)
		    	return values()[i].EggItem;
		}
		return null;
    }
    /**
     * takes an drop-,dna- or eggitem and gives the index, -1 means not found
     */
    public static int getIndex(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].DNAItem.itemID == i0.itemID || values()[i].DropItem.itemID == i0.itemID || values()[i].EggItem.itemID == i0.itemID)
		    	return i;
		}
		return -1;
    }
    public Class getDinoClass()
    {
        return this.dinoClass;
    }
    public boolean isModelable()
    {
        return (this.Flags & C.MODEL) != 0;
    }
    public boolean isTameable()
    {
        return (this.Flags & C.TAME) != 0;
    }
    public boolean isRideable()
    {
        return (this.Flags & C.RIDE) != 0;
    }
    public boolean canCarryItems()
    {
        return (this.Flags & C.CARRY) != 0;
    }
    public boolean useFeeder()
    {
    	return (this.Flags & C.HERB_CARN) != 0;
    }
    public boolean isHerbivore()
    {
        return (this.Flags & C.HERBIVORE) != 0;
    }
    public boolean isCarnivore()
    {
    	return (this.Flags & C.CARNIVORE) != 0;
    }
}