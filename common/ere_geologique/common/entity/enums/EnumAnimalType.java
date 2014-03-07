package ere_geologique.common.entity.enums;

import ere_geologique.common.command.CommandDino;

public enum EnumAnimalType
{
    Pig(3000),
    Sheep(3000),
    Cow(3000),
    Chicken(1000),
    Smilodon(4000),
    Mammoth(6000),
    Dodo(1500);
    
    public int growTime;
    
    private EnumAnimalType(int grow0)
    {
        if (CommandDino.debugMode)
        {
            growTime=100;
        }
        else
        {
        	growTime=grow0;
        }
    }
}