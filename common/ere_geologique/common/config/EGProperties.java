package ere_geologique.common.config;

import ere_geologique.common.entity.Enums.EnumDinoType;

public class EGProperties
{
	//Block
	public static int LeavesID;
	public static int WoodID;
	public static int SaplingID;
	public static int PlankID;
	public static int SlabID;
	public static int DoubleSlabID;
	public static int StairFougereID;
	public static int StairCycasID;
	public static int StairAraucariasID;
	public static int StairMetasequoiasID;
	public static int StairGingkosID;
	public static int GlaciaPortalID;
	public static int FeederID;
	public static int AnalyzerIdleID;
	public static int AnalyzerActiveID;
	public static int CultivatorIdleID;
	public static int CultivatorActiveID;
	public static int FossilID;
	public static int ReinforcedStoneID;
	public static int ReinforcedGlassID;
	public static int SteelBlockID;
	public static int FossilSkullID;
	public static int BlueFireID;
	
	//Items
	public static int IvoryIngotID;
	public static int IvoryNuggetID;
	public static int IvoryGearID;
	public static int DinoPediaID;
	public static int ChickenEssID;
	public static int WhipID;
	public static int LegBoneID;
	public static int ClawID;
	public static int FootID;
	public static int SkullID;
	public static int BioFossilID;
	public static int SkullStickID;
	public static int gemID;
	public static int EmptyShellID;
	public static int MagicConchID;
	public static int sjlID;
	public static int cookedDinoMeatID;
	public static int BrokenSaplingID;
	public static int SteelIngotID;
	public static int SteelPlateID;
	public static int RelicID;
	public static int cookedChickenSoupID;
	public static int rawChickenSoupID;
	public static int FlintAndSteelID;
	public static int archNotebookID;

	//Dimensions
	public static int GlaciaID;
	public static int PrehistoriaID;
	public static int PrimitiveID;
	
	//Other
	public static int[] EGGIDs = new int[EnumDinoType.values().length];
	public static int[] RAWIDs = new int[EnumDinoType.values().length];
	public static int[] DNAIDs = new int[EnumDinoType.values().length];
	public static final String MOD = "EreGeologique";
}