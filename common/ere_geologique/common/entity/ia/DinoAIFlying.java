package ere_geologique.common.entity.ia;

import java.util.Random;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import ere_geologique.common.entity.FlyingDino;

public class DinoAIFlying extends EntityAIBase
{

	// =============
	// DEBUG VALUES
	// =============
	boolean g_bDebugFlying = true;

	// =============
	// CONSTANTS
	// =============
	public enum eFlyingState
	{
		FS_ON_GROUND, FS_IN_AIR, FS_ASCENDING, FS_DESCENDING
	};

	private final float DESTINATION_HEIGHT_MULTIPLIER = 0.25f;
	private final float MAX_DESTINATION_HEIGHT = 30.0f;
	private final float SPEED_ASCENDING = 0.05f;
	private final float SPEED_IN_AIR = 0.1f;
	private final int REASSESS_THRESHOLD = 3;
	private final int REASSESS_BASE = 1;
	private final float MOTION_VECTOR_SCALE = 100.0f;
	private final double WANDER_RADIUS = 10.0;

	// =============
	// CLASS MEMBERS
	// =============
	protected FlyingDino m_edDinoAgent = null;
	protected eFlyingState m_fsCurrentState = eFlyingState.FS_ON_GROUND;
	protected eFlyingState m_fsPreviousState = eFlyingState.FS_ON_GROUND;
	protected boolean m_bIsAscending = false;
	private double m_dTakeOffInitialHeight = 0;
	private double m_dTakeOffDestinationHeight = 0;
	private Vec3 m_vWanderDestination = null;
	private static Vec3 m_vMotionVector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);;
	private int m_nReassessAt = 0;
	private int m_nReassessTicker = 0;

	// =============
	// CONSTRUCTOR
	// =============
	public DinoAIFlying(FlyingDino _Owner)
	{
		m_edDinoAgent = _Owner;
		m_nReassessTicker = REASSESS_THRESHOLD;
	}

	// =============
	// OVERRIDES
	// =============
	/**
	 * Determines if we need to execute this task.
	 */
	@Override
	public boolean shouldExecute()
	{
		// Always execute if we're in debug mode.
		if (g_bDebugFlying)
		{
			return true;
		}

		// Only fly if we're well fed.
		if (m_edDinoAgent.IsHungry() == false)
		{
			return true;
		}

		// If we're hungry, do not fly.
		return false;
	}

	/**
	 * Updates the task.
	 */
	public void updateTask()
	{
		// ========================================================
		// There are four states which the dino can be in:
		// 1. On Ground
		// 2. In Air
		// 3. Ascending
		// 4. Descending
		// ========================================================

		// Find out which state we're in and process that one.
		switch (m_fsCurrentState)
		{

		case FS_ASCENDING:
		{
			ProcessAscending();
		}
			break;

		case FS_DESCENDING:
		{
			ProcessDescending();
		}
			break;

		case FS_IN_AIR:
		{
			ProcessInAir();
		}
			break;

		case FS_ON_GROUND:
		{
			ProcessOnGround();
		}
			break;

		default:
			break;

		}

	}

	// =============
	// INTERNAL
	// =============
	private boolean SetTakeOffData()
	{
		if (m_fsCurrentState == eFlyingState.FS_IN_AIR)
		{
			// We're already in the air, so this is an
			// unsuccessful take off.
			return false;
		}

		// Check to see if there are n num blocks above me blocking my path.
		/*
		 * for( int i = 0; i < c_nTakeOffHeightInBlocks; ++i ) { if(
		 * g_bDebugFlying == false ) { // Find out if there are blocks above us.
		 * return false; } }
		 */

		// Since we've made it this far, we can start our ascent.
		if (m_fsCurrentState != eFlyingState.FS_ASCENDING) // Really just a
															// paranoid check.
		{
			// Save our take off height, and set a destination height.
			m_dTakeOffInitialHeight = m_edDinoAgent.posY;
			m_dTakeOffDestinationHeight = m_edDinoAgent.posY + DESTINATION_HEIGHT_MULTIPLIER * m_edDinoAgent.getAge();

			if (m_dTakeOffDestinationHeight > MAX_DESTINATION_HEIGHT)
			{
				m_dTakeOffDestinationHeight = MAX_DESTINATION_HEIGHT;
			}

			m_fsPreviousState = m_fsCurrentState;
			m_fsCurrentState = eFlyingState.FS_ASCENDING;

			m_bIsAscending = true;

			m_edDinoAgent.SetAirborne(true);
			m_edDinoAgent.SetInWater(true);
		}

		return false;
	}

	private boolean ProcessOnGround()
	{
		if (g_bDebugFlying)
		{
			this.SetTakeOffData();
			return true;
		}

		return false;
	}

	// Basically a wander
	private boolean ProcessInAir()
	{
		// m_edDinoAgent.setPosition(m_edDinoAgent.posX, m_edDinoAgent.posY,
		// m_edDinoAgent.posZ);

		// If we're hungry, start our descent.
		if (g_bDebugFlying == false)
		{
			if (m_edDinoAgent.IsHungry())
			{
				m_fsPreviousState = m_fsCurrentState;
				m_fsCurrentState = eFlyingState.FS_DESCENDING;
				m_edDinoAgent.motionY = 0;
			}
		}

		// Every 20 ticks or so, get a new destination or skip moving.
		++m_nReassessTicker;

		if (m_nReassessTicker >= m_nReassessAt)
		{

			Random rand = new Random();

			// Pick a wander position in the air.
			m_vMotionVector.xCoord = m_edDinoAgent.motionX * MOTION_VECTOR_SCALE;
			m_vMotionVector.yCoord = m_edDinoAgent.motionY * MOTION_VECTOR_SCALE;
			m_vMotionVector.zCoord = m_edDinoAgent.motionZ * MOTION_VECTOR_SCALE;

			m_vWanderDestination = RandomPositionGenerator.findRandomTargetBlockTowards(this.m_edDinoAgent, 3, 4, m_vMotionVector);

			if (m_vWanderDestination == null)
			{
				// We'll just chill here for now.
				return false;
			}

			// Make sure the block is not on the ground.
			if (m_edDinoAgent.worldObj.getHeightValue((int) m_vWanderDestination.xCoord, (int) m_vWanderDestination.zCoord) + 1 > m_vWanderDestination.yCoord)
			{
				m_vWanderDestination.yCoord += 3;
			}

			m_nReassessAt = rand.nextInt(REASSESS_THRESHOLD) + REASSESS_BASE;
			m_nReassessTicker = 0;
		}

		// Always try to move to your new destination
		m_edDinoAgent.FlyToPoint(m_vWanderDestination, SPEED_IN_AIR);

		return true;
	}

	private boolean ProcessAscending()
	{
		// Fly
		if (m_edDinoAgent.posY < m_dTakeOffDestinationHeight)
		{
			m_edDinoAgent.FlyToPoint((float) m_edDinoAgent.posX, (float) m_dTakeOffDestinationHeight, (float) m_edDinoAgent.posZ, m_edDinoAgent.getAge() * SPEED_ASCENDING);
			// m_edDinoAgent.motionY = SPEED_ASCENDING;
			// Update our position maybe?
		}
		else
		{
			// Switch to the hover state since
			// we've already reached our destination height.
			m_fsPreviousState = m_fsCurrentState;
			m_fsCurrentState = eFlyingState.FS_IN_AIR;
			m_edDinoAgent.motionY = 0;
		}

		m_edDinoAgent.isAirBorne = true;
		// m_edDinoAgent
		return true;
	}

	private boolean ProcessDescending()
	{

		return false;
	}

	// ==============
	// ACCESSORS
	// ==============
	public eFlyingState GetCurrentState()
	{
		return m_fsCurrentState;
	}

}