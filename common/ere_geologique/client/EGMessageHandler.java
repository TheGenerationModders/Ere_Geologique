package ere_geologique.client;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import cpw.mods.fml.common.network.IChatListener;

public class EGMessageHandler implements IChatListener
{
	@Override
	public Packet3Chat serverChat(NetHandler handler, Packet3Chat message)
	{
		return message;
	}

	@Override
	public Packet3Chat clientChat(NetHandler handler, Packet3Chat message)
	{
		return message;
	}
}