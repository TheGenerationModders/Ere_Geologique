package ere_geologique.common.entity.Enums;

import ere_geologique.common.EreGeologique;

public enum EnumAnimalType
{
    Pig(3000),
    Sheep(3000),
    Cow(3000),
    Chicken(1000),
    Smilodon(4000),
    Mammoth(6000),
    Dodo(1500);
    
    public int GrowTime;
    
    private EnumAnimalType(int grow0)
    {
        if (EreGeologique.DebugMode)
        {
            GrowTime=100;
        }
        else
        {
    	GrowTime=grow0;
        }
    }
}