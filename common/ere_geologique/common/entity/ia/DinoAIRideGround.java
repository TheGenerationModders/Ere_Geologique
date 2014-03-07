package ere_geologique.common.entity.ia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.item.EGItemList;

public class DinoAIRideGround extends DinoAIRide
{

	private static final float PLAYER_SPEED = 0.98f;
	private final double speed;

	public DinoAIRideGround(Dinosaure dinosaure, double speed)
	{
		super(dinosaure);
		this.speed = speed;
	}

	public static boolean hasEquipped(EntityPlayer player, Item item)
	{
		ItemStack itemStack = player.getCurrentEquippedItem();

		if (itemStack == null)
		{
			return false;
		}

		return itemStack.getItem() == item;
	}

	@Override
	public void startExecuting()
	{
		dinosaure.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		float speedX = rider.moveForward / PLAYER_SPEED;
		float speedY = rider.moveStrafing / PLAYER_SPEED;

		if (hasEquipped(rider, EGItemList.whip))
		{

			float speedPlayer = Math.max(Math.abs(speedX), Math.abs(speedY));
			Vec3 look = rider.getLookVec();
			float dir = Math.min(speedX, 0) * -1;
			dir += speedY / (speedX * 2 + (speedX < 0 ? -2 : 2));
			if (dir != 0)
			{
				look.rotateAroundY((float) Math.PI * dir);
			}

			if (speedPlayer > 0)
			{
				dinosaure.getMoveHelper().setMoveTo(dinosaure.posX + look.xCoord, dinosaure.posY, dinosaure.posZ + look.zCoord, speed * speedPlayer);
			}
		}
	}
}