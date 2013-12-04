package ere_geologique.api.food;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import ere_geologique.common.entity.Ankylosaurus;
import ere_geologique.common.entity.Brachiosaurus;
import ere_geologique.common.entity.Compsognathus;
import ere_geologique.common.entity.Dilophosaurus;
import ere_geologique.common.entity.Mosasaurus;
import ere_geologique.common.entity.Plesiosaure;
import ere_geologique.common.entity.Pterosaure;
import ere_geologique.common.entity.Spinosaurus;
import ere_geologique.common.entity.Stegosaurus;
import ere_geologique.common.entity.TRex;
import ere_geologique.common.entity.Triceratops;
import ere_geologique.common.entity.Velociraptor;

public enum EnumDinoFoodMob
{
        Player(EntityPlayer.class,30,2),
        Chicken(EntityChicken.class,20,2),
        Cow(EntityCow.class,50,5),
        Pig(EntityPig.class,30,3),
        Sheep(EntitySheep.class,35,3),
        Squid(EntitySquid.class,30,3),
        Mob(EntityMob.class,20,1),
        Nautilus(null,100,5),
        Triceratops(null,50,3),
        Velociraptor(null,20,3),
        TRex(null,70,5),
        Pterosaure(null,35,2),
        Plesiosaure(null,50,3),
        Mosasaurus(null,50,3),
        Stegosaurus(null,50,3),
        Dilophosaurus(null,25,2),
        Brachiosaurus(null,80,5),
        Spinosaurus(null,70,5),
        Compsognathus(null,20,3),
        Ankylosaurus(null,50,3),
        ;
    public Class preyClass;
    public int FoodValue;
    public int HealValue;

    private EnumDinoFoodMob(Class pClass, int Food, int Heal)
    {
        preyClass = pClass;
        FoodValue = Food;
        HealValue = Heal;
    }
    private EnumDinoFoodMob(EnumDinoFoodMob mob0)
    {
        preyClass = mob0.preyClass;
        FoodValue = mob0.FoodValue;
        HealValue = mob0.HealValue;
    }
    public void setDetails(Class pClass)
    {
            preyClass=pClass;
    }
    public static void init()
    {
            Triceratops.setDetails(Triceratops.class);
            Velociraptor.setDetails(Velociraptor.class);
            TRex.setDetails(TRex.class);
            Pterosaure.setDetails(Pterosaure.class);
            Plesiosaure.setDetails(Plesiosaure.class);
            Mosasaurus.setDetails(Mosasaurus.class);
            Stegosaurus.setDetails(Stegosaurus.class);
            Dilophosaurus.setDetails(Dilophosaurus.class);
            Brachiosaurus.setDetails(Brachiosaurus.class);
            Spinosaurus.setDetails(Spinosaurus.class);
        Compsognathus.setDetails(Compsognathus.class);
        Ankylosaurus.setDetails(Ankylosaurus.class);
    }
}