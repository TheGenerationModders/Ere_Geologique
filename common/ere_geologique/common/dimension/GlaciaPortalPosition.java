package ere_geologique.common.dimension;

import net.minecraft.util.ChunkCoordinates;

public class GlaciaPortalPosition extends ChunkCoordinates
{
	public long field_85087_d;
	final TeleporterGlacia field_85088_e;

	public GlaciaPortalPosition(TeleporterGlacia teleporterGlacia, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = teleporterGlacia;
		this.field_85087_d = par5;
	}
}