package ere_geologique.client;

import net.minecraftforge.common.Configuration;

public class EGOptions
{
	public static boolean Heal_Dinos;
	public static boolean Dinos_Starve;
	public static boolean Dino_Block_Breaking;
	
	public void Load(Configuration config)
	{
		Heal_Dinos = config.get("option", "Heal_Dinos", true).getBoolean(true);
		Dinos_Starve = config.get("option", "Dinos_Starve", true).getBoolean(true);
		Dino_Block_Breaking = config.get("option", "Dino_Block_Breaking", true).getBoolean(true);
	}
}