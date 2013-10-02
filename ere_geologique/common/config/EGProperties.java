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
	public static int StairID;
	public static int PortalID;
	public static int FeederIdleID;
	public static int FeederActiveID;
	public static int AnalyzerIdleID;
	public static int AnalyzerActiveID;
	public static int CultivatorIdleID;
	public static int CultivatorActiveID;
	public static int FossilID;
	public static int ReinforcedStoneID;
	public static int ReinforcedGlassID;
	public static int SteelBlockID;
	
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

	//Dimensions
	public static int GlaciaID;
	
	//Other
	public static int[] EGGIDs = new int[EnumDinoType.values().length];
	public static int[] RAWIDs = new int[EnumDinoType.values().length];
	public static int[] DNAIDs = new int[EnumDinoType.values().length];
}