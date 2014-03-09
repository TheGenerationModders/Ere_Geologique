package ere_geologique.common.entity.enums;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ere_geologique.api.food.DinoFood.DinoFoodEntry;
import ere_geologique.api.food.DinoFoodMobList;
import ere_geologique.api.food.EnumDinoFoodMob;
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
import ere_geologique.common.food.FoodList;
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
    TRex(TRex.class, 							  		              C.RIDE  	| C.CARNIVORE),
    Pterosaure(Pterosaure.class, 				C.MODEL | C.TAME  	| C.RIDE 	| C.CARNIVORE),
    Plesiosaure(Plesiosaure.class, 				C.MODEL | C.TAME  	| C.RIDE 	| C.CARNIVORE),
    Stegosaurus(Stegosaurus.class, 				C.MODEL | C.TAME  				| C.HERBIVORE),
    Dilophosaurus(Dilophosaurus.class,					  C.TAME  	        	| C.CARNIVORE),
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
    public Item dropItem;
    public Item dnaItem;
    public Item eggItem;
    
    //List of the eatable Items and block with the FoodValue and HealingValue belonging to
	public List<DinoFoodEntry> dinoFood = new ArrayList();
    
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
    	Triceratops.setItems(		Items.stick);//,			Fossil.rawTriceratops, 	Fossil.dnaTriceratops, 	Fossil.eggTriceratops);
    	Triceratops.setDimensions(1.2F, 0.4F, 1.1F, 0.7F, 1.2F, 0.36F);
    	Triceratops.setAges(-1, -1, 13);
    	Triceratops.setProperties(21, 1, 3, -1, -1F, 0.016F, 500);
    	Triceratops.setExperience(0.5F, 0.2F);
    	
    	Triceratops.dinoFood.add(FoodList.wheat);
    	Triceratops.dinoFood.add(FoodList.melon);
    	Triceratops.dinoFood.add(FoodList.apple);
    	Triceratops.dinoFood.add(FoodList.bread);
    	Triceratops.dinoFood.add(FoodList.potato);
    	
    	Triceratops.dinoFood.add(FoodList.leaves);
    	Triceratops.dinoFood.add(FoodList.leavesFougere);
        Triceratops.dinoFood.add(FoodList.redFlower);
        Triceratops.dinoFood.add(FoodList.yellowFlower);
    	
        Pachycephalosaurus.setItems(Items.stick);//,         Fossil.rawPachycephalosaurus,  Fossil.dnaPachycephalosaurus,  Fossil.eggPachycephalosaurus);
        Pachycephalosaurus.setDimensions(0.4F, 0.23F, 0.4F, 0.23F, 0.4F, 0.23F);
        Pachycephalosaurus.setAges(-1, -1, 13);
        Pachycephalosaurus.setProperties(22, 3, 3, -1, -1F, 0.016F, 500);
        Pachycephalosaurus.setExperience(0.5F, 0.2F);
        
        Pachycephalosaurus.dinoFood.add(FoodList.wheat);
        Pachycephalosaurus.dinoFood.add(FoodList.melon);
        Pachycephalosaurus.dinoFood.add(FoodList.apple);
        Pachycephalosaurus.dinoFood.add(FoodList.bread);
        Pachycephalosaurus.dinoFood.add(FoodList.carrot);
        
        Pachycephalosaurus.dinoFood.add(FoodList.leaves);
    	Pachycephalosaurus.dinoFood.add(FoodList.leavesFougere);
        Pachycephalosaurus.dinoFood.add(FoodList.redFlower);
        Pachycephalosaurus.dinoFood.add(FoodList.yellowFlower);
    	
        Ankylosaurus.setItems(Items.stick);//,         Fossil.rawAnkylosaurus,  Fossil.dnaAnkylosaurus,  Fossil.eggAnkylosaurus);
        Ankylosaurus.setDinoSize(1.0F, 10.0F);
        Ankylosaurus.setDimensions(0.5F, 0.25F, 0.5F, 0.25F, 0.5F, 0.25F);
        Ankylosaurus.setAges(-1, 6, 13);
        Ankylosaurus.setProperties(21, 3, 4, 3, 0.17F, 0.016F, 500);
        Ankylosaurus.setExperience(0.5F, 0.2F);
        
        Ankylosaurus.dinoFood.add(FoodList.wheat);
        Ankylosaurus.dinoFood.add(FoodList.melon);
        Ankylosaurus.dinoFood.add(FoodList.apple);
        Ankylosaurus.dinoFood.add(FoodList.bread);
        Ankylosaurus.dinoFood.add(FoodList.potato);
        
        Ankylosaurus.dinoFood.add(FoodList.leaves);
    	Ankylosaurus.dinoFood.add(FoodList.leavesFougere);
        Ankylosaurus.dinoFood.add(FoodList.redFlower);
        Ankylosaurus.dinoFood.add(FoodList.yellowFlower);
    	
        Velociraptor.setItems(Items.bone);//,			Fossil.rawVelociraptor, Fossil.dnaVelociraptor, Fossil.eggVelociraptor);
        Velociraptor.setDimensions(0.3F, 0.12F, 0.3F, 0.13F, 0.3F, 0.1F);
        Velociraptor.setAges(-1, 6, -1);
        Velociraptor.setProperties(21, 1, -1, -1, 0.3F, 0.025F, -1);
        Velociraptor.setExperience(0.7F, 0.7F);
        
        Velociraptor.dinoFood.add(FoodList.porkRaw);
        Velociraptor.dinoFood.add(FoodList.porkCooked);
        Velociraptor.dinoFood.add(FoodList.beefRaw);
        Velociraptor.dinoFood.add(FoodList.beefCooked);
        Velociraptor.dinoFood.add(FoodList.dinoMeatCooked);
        Velociraptor.dinoFood.add(FoodList.triceratops);
        Velociraptor.dinoFood.add(FoodList.stegosaurus);
        Velociraptor.dinoFood.add(FoodList.plesiosaure);
        Velociraptor.dinoFood.add(FoodList.pterosaure);
        Velociraptor.dinoFood.add(FoodList.brachiosaure);
        
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Cow);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Stegosaurus);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Plesiosaure);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
        Velociraptor.FoodMobList.addMob(EnumDinoFoodMob.Brachiosaurus);
        
        Compsognathus.setItems(      Items.bone);//,          Fossil.rawCompsognathus, Fossil.dnaCompsognathus, Fossil.eggCompsognathus);
        Compsognathus.setDimensions(0.5F, 0.05F, 0.5F, 0.05F, 0.5F, 0.05F);
        Compsognathus.setAges(-1, -1, 9);
        Compsognathus.setProperties(1, 1, 1, 0, 0.2F, 0.010F, -1);
        Compsognathus.setExperience(0.2F, 0.2F);
        
        Compsognathus.dinoFood.add(FoodList.porkRaw);
        Compsognathus.dinoFood.add(FoodList.porkCooked);
        Compsognathus.dinoFood.add(FoodList.beefRaw);
        Compsognathus.dinoFood.add(FoodList.beefCooked);
        Compsognathus.dinoFood.add(FoodList.dinoMeatCooked);
        Compsognathus.dinoFood.add(FoodList.triceratops);
        Compsognathus.dinoFood.add(FoodList.stegosaurus);
        Compsognathus.dinoFood.add(FoodList.plesiosaure);
        Compsognathus.dinoFood.add(FoodList.pterosaure);
        Compsognathus.dinoFood.add(FoodList.brachiosaure);
        
        Compsognathus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Compsognathus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        
        TRex.setItems(				EGItemList.skullStick);//,	Fossil.rawTRex, 		Fossil.dnaTRex, 		Fossil.eggTRex);
        TRex.setDimensions(0.7F, 0.57F, 0.8F, 0.66F, 0.5F, 0.57F);
        TRex.setAges(-1, -1, 23);
        TRex.setProperties(-1, 5, 4, -1, 0.22F, 0.02F, 250);
        TRex.setExperience(1F, 1F);
        
        TRex.dinoFood.add(FoodList.porkRaw);
        TRex.dinoFood.add(FoodList.beefRaw);
        TRex.dinoFood.add(FoodList.chickenRaw);
        TRex.dinoFood.add(FoodList.triceratops);
        TRex.dinoFood.add(FoodList.stegosaurus);
        TRex.dinoFood.add(FoodList.dilophosaurus);
        TRex.dinoFood.add(FoodList.plesiosaure);
        TRex.dinoFood.add(FoodList.pterosaure);
        TRex.dinoFood.add(FoodList.brachiosaure);
        TRex.dinoFood.add(FoodList.velociraptor);
        
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
        TRex.FoodMobList.addMob(EnumDinoFoodMob.Player);
        
        Pterosaure.setItems(			Items.arrow);//,			Fossil.rawPterosaur, 	Fossil.dnaPterosaur, 	Fossil.eggPterosaur);
        Pterosaure.setDimensions(1.2F, 0.3F, 1.0F, 0.4F, 0.8F, 0.2F);
        Pterosaure.setAges(-1, -1, 9);
        Pterosaure.setProperties(21, 1, -1, -1, -1F, -1F, -1);
        Pterosaure.setExperience(0.3F, 0.3F);
        
        Pterosaure.dinoFood.add(FoodList.fishRaw);
        Pterosaure.dinoFood.add(FoodList.fishCooked);
        Pterosaure.dinoFood.add(FoodList.sjl);
        Pterosaure.dinoFood.add(FoodList.chickenRaw);
        
        Pterosaure.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        
        Nautilus.setItems(			null);//,				Fossil.rawNautilus, 	Fossil.dnaNautilus, 	Fossil.shellNautilus);
        Nautilus.setExperience(1.0F, 0F);
        
        Plesiosaure.setItems(		EGItemList.magicConch);//,	Fossil.rawPlesiosaur, 	Fossil.dnaPlesiosaur, 	Fossil.eggPlesiosaur);
        Plesiosaure.setDimensions(0.5F, 0.3F, 0.5F, 0.5F, 0.5F, 0.3F);
        Plesiosaure.setProperties(30, 10, 3, -1, 0.18F, 0.02F, 500);
        Plesiosaure.setExperience(0.5F, 0.25F);
        
        Plesiosaure.dinoFood.add(FoodList.fishRaw);
        Plesiosaure.dinoFood.add(FoodList.fishCooked);
        Plesiosaure.dinoFood.add(FoodList.sjl);
        Plesiosaure.dinoFood.add(FoodList.chickenRaw);
        
        Plesiosaure.FoodMobList.addMob(EnumDinoFoodMob.Nautilus);
        Plesiosaure.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        
        Mosasaurus.setItems(null);//,				Fossil.rawMosasaurus, 	Fossil.dnaMosasaurus, 	Fossil.eggMosasaurus);
        Mosasaurus.setDimensions(0.25F, 0.4F, 0.5F, 0.45F, 0.4F, 0.25F);
        Mosasaurus.setAges(-1, 8, 20);
        Mosasaurus.setProperties(50, 10, 4, 2, 0.3F, 0.4F, 500);
        Mosasaurus.setExperience(1F, 1F);
        
        Mosasaurus.FoodMobList.addMob(EnumDinoFoodMob.Squid);
        Mosasaurus.FoodMobList.addMob(EnumDinoFoodMob.Nautilus);
        Mosasaurus.FoodMobList.addMob(EnumDinoFoodMob.Player);
        
        Stegosaurus.setItems(		Items.stick);//,			Fossil.rawStegosaurus, 	Fossil.dnaStegosaurus, 	Fossil.eggStegosaurus);
        Stegosaurus.setDimensions(1.2F, 0.5F, 1.0F, 0.7F, 1.2F, 0.36F);
        Stegosaurus.setAges(-1, -1, 13);
        Stegosaurus.setProperties(21, 1, -1, -1, -1F, -1F, 500);
        Stegosaurus.setExperience(0.5F, 0.2F);
        
        Stegosaurus.dinoFood.add(FoodList.wheat);
        Stegosaurus.dinoFood.add(FoodList.melon);
        Stegosaurus.dinoFood.add(FoodList.carrot);
        Stegosaurus.dinoFood.add(FoodList.sugar);
        Stegosaurus.dinoFood.add(FoodList.cookie);
        Stegosaurus.dinoFood.add(FoodList.bread);
        
        Stegosaurus.dinoFood.add(FoodList.leaves);
    	Stegosaurus.dinoFood.add(FoodList.leavesFougere);
        Stegosaurus.dinoFood.add(FoodList.redFlower);
        Stegosaurus.dinoFood.add(FoodList.yellowFlower);
        
        Dilophosaurus.setItems(		Items.bone);//,			Fossil.rawDilophosaurus,Fossil.dnaDilophosaurus,Fossil.eggDilophosaurus);
        Dilophosaurus.setDimensions(0.4F, 0.16F, 0.4F, 0.17F, 0.4F, 0.16F);
        Dilophosaurus.setAges(-1, -1, 9);
        Dilophosaurus.setProperties(-1, -1, -1, -1, 0.21F, 0.026F, -1);
        Dilophosaurus.setExperience(1F, 1F);
        
        Dilophosaurus.dinoFood.add(FoodList.porkRaw);
        Dilophosaurus.dinoFood.add(FoodList.porkCooked);
        Dilophosaurus.dinoFood.add(FoodList.chickenRaw);
        Dilophosaurus.dinoFood.add(FoodList.chickenCooked);
        Dilophosaurus.dinoFood.add(FoodList.dinoMeatCooked);
        Dilophosaurus.dinoFood.add(FoodList.pterosaure);
        Dilophosaurus.dinoFood.add(FoodList.triceratops);
        Dilophosaurus.dinoFood.add(FoodList.egg);
        
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
        Dilophosaurus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
        
        Brachiosaurus.setItems(		Items.stick);//,			Fossil.rawBrachiosaurus,Fossil.dnaBrachiosaurus,Fossil.eggBrachiosaurus);
        Brachiosaurus.setDimensions(1.0F, 0.5F, 1.0F, 0.5F, 1.0F, 0.5F);
        Brachiosaurus.setAges(6, 12, 36);
        Brachiosaurus.setProperties(-1, 5, -1, -1, 0.3F, 0.012F, 500);
        Brachiosaurus.setExperience(0.6F, 0.15F);
        
        Brachiosaurus.dinoFood.add(FoodList.sugar);
        Brachiosaurus.dinoFood.add(FoodList.cookie);
        Brachiosaurus.dinoFood.add(FoodList.apple);
        
    	Brachiosaurus.dinoFood.add(FoodList.leavesFougere);
        Brachiosaurus.dinoFood.add(FoodList.leaves);
		
		Spinosaurus.setItems(		EGItemList.skullStick);//,	Fossil.rawSpinosaurus,	Fossil.dnaSpinosaurus,	Fossil.eggSpinosaurus);
//		Spinosaurus.setDimensions(0.5F,0.3F,0.5F,0.3F,0.5F,0.3F); New model dimensions
		Spinosaurus.setDimensions(0.8F,0.7F,0.5F,0.7F,0.5F,0.7F); // Dimensions for Dragonith's Spinosaur.
		Spinosaurus.setAges(-1, -1, 23);
		Spinosaurus.setProperties(-1, 5, 4, -1, 0.24F, 0.021F,550);
		Spinosaurus.setExperience(0F,0.9F);
		
		Spinosaurus.dinoFood.add(FoodList.porkRaw);
		Spinosaurus.dinoFood.add(FoodList.beefRaw);
		Spinosaurus.dinoFood.add(FoodList.chickenRaw);
		Spinosaurus.dinoFood.add(FoodList.triceratops);
		Spinosaurus.dinoFood.add(FoodList.stegosaurus);
		Spinosaurus.dinoFood.add(FoodList.dilophosaurus);
		Spinosaurus.dinoFood.add(FoodList.plesiosaure);
		Spinosaurus.dinoFood.add(FoodList.pterosaure);
		Spinosaurus.dinoFood.add(FoodList.brachiosaure);
		Spinosaurus.dinoFood.add(FoodList.velociraptor);
	    Spinosaurus.dinoFood.add(FoodList.fishRaw);

        
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pig);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Cow);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Chicken);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Triceratops);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Stegosaurus);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Dilophosaurus);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Plesiosaure);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Pterosaure);
		Spinosaurus.FoodMobList.addMob(EnumDinoFoodMob.Velociraptor);
    }
    
    /**
     *      * takes an item and returns if it is a Dino DNA
     */
    public static boolean isDinoDNA(Item i0)
    {
		for(int i=0;i<values().length;i++)
		{
		    if(values()[i].dnaItem == i0)
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
		    if(values()[i].dropItem == i0)
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
		    if(values()[i].dropItem == i0 || values()[i].eggItem == i0)
		    	return values()[i].dnaItem;
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
		    if(values()[i].dnaItem == i0 || values()[i].eggItem == i0)
		    	return values()[i].dropItem;
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
		    if(values()[i].dnaItem == i0 || values()[i].dropItem == i0)
		    	return values()[i].eggItem;
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
		    if(values()[i].dnaItem == i0 || values()[i].dropItem == i0 || values()[i].eggItem == i0)
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