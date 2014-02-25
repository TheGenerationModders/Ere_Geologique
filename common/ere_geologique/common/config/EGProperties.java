package ere_geologique.common.config;

import net.minecraftforge.common.config.Property;
import ere_geologique.common.entity.enums.EnumDinoType;

public class EGProperties
{
	//Block
	public static int leavesID;
	public static int woodID;
	public static int saplingID;
	public static int plankID;
	public static int slabID;
	public static int doubleSlabID;
	public static int stairFougereID;
	public static int stairCycasID;
	public static int stairAraucariasID;
	public static int stairMetasequoiasID;
	public static int stairGingkosID;
	public static int glaciaPortalID;
	public static int feederID;
	public static int analyzerID;
	public static int cultivatorIdleID;
	public static int cultivatorActiveID;
	public static int fossilID;
	public static int reinforcedStoneID;
	public static int reinforcedGlassID;
	public static int steelBlockID;
	public static int fossilSkullID;
	public static int blueFireID;
	public static int drumID;
	
	//Items
	public static int ivoryIngotID;
	public static int ivoryNuggetID;
	public static int ivoryGearID;
	public static int dinoPediaID;
	public static int chickenEssID;
	public static int whipID;
	public static int legBoneID;
	public static int clawID;
	public static int footID;
	public static int skullID;
	public static int bioFossilID;
	public static int skullStickID;
	public static int gemID;
	public static int emptyShellID;
	public static int magicConchID;
	public static int sjlID;
	public static int cookedDinoMeatID;
	public static int brokenSaplingID;
	public static int steelIngotID;
	public static int steelPlateID;
	public static int relicID;
	public static int cookedChickenSoupID;
	public static int rawChickenSoupID;
	public static int flintAndSteelID;
	public static int archNotebookID;
	public static int tranquilizerDartID;
	public static int gunID;

	//Dimensions
	public static int glaciaID;
	public static int prehistoriaID;
	public static int primitiveID;
	
	//Other
	public static int[] eggIDs = new int[EnumDinoType.values().length];
	public static int[] rawIDs = new int[EnumDinoType.values().length];
	public static int[] dnaIDs = new int[EnumDinoType.values().length];
	public static final String mod = "EreGeologique";
	public static Property updateCheck;
}