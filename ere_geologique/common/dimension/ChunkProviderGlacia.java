package ere_geologique.common.dimension;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderGlacia implements IChunkProvider
{

	public ChunkProviderGlacia(World worldObj, long seed, boolean b)
	{
		
	}

	@Override
	public boolean chunkExists(int i, int j)
	{
		return false;
	}

	@Override
	public Chunk provideChunk(int i, int j)
	{
		return null;
	}

	@Override
	public Chunk loadChunk(int i, int j)
	{
		return null;
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j)
	{

	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
	{
		return false;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return false;
	}

	@Override
	public String makeString()
	{
		return null;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k)
	{
		return null;
	}

	@Override
	public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k)
	{
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j)
	{

	}

	@Override
	public void saveExtraData()
	{

	}
}