/*package ere_geologique.proxy.network;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import ere_geologique.common.event.KeyHandler;

public class ServerPacketHandler implements IPacketHandler
{
	private static ServerPacketHandler instance;
	private Map<String, Map<String, Boolean>> playerKeys = new HashMap<String, Map<String, Boolean>>();

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (player instanceof EntityPlayerMP)
		{
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			KeyHandler rk = new KeyHandler(packet);

			Map<String, Boolean> playerKeyMap;
			if (!playerKeys.containsKey(playerMP.username))
			{
				playerKeyMap = new HashMap<String, Boolean>();
				playerKeys.put(playerMP.username, playerKeyMap);
			} else {
				playerKeyMap = playerKeys.get(playerMP.username);
			}
			playerKeyMap.put(rk.getName(), rk.isDown());
		}
	}

	public boolean isKeyPressed(String username, String keyname)
	{
		Map<String, Boolean> playerKeyMap = playerKeys.get(username);

		if (playerKeyMap == null)
		{
			return false;
		}

		Boolean pressed = playerKeyMap.get(keyname);

		return pressed != null && pressed.booleanValue();
	}

	public void clearKeyMapping()
	{
		playerKeys.clear();
	}

	public static ServerPacketHandler getInstance()
	{
		if (instance == null)
		{
			instance = new ServerPacketHandler();
		}
		return instance;
	}
}*/