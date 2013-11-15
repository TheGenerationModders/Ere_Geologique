package ere_geologique.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.common.entity.Pterosaure;

public class RiderInput implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if("RiderInput".equals(packet.channel))
		{
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.data));
			try
	        {
				int EntityID = in.readInt();
				float Strafe = in.readFloat();
				float Forward = in.readFloat();
				boolean Jump = in.readBoolean();
				boolean Sneak = in.readBoolean();
				Entity E0 =((EntityPlayerMP)player).worldObj.getEntityByID(EntityID);
				if(E0 instanceof Dinosaure)
				{
					((Dinosaure) E0).RiderForward=Forward;
					((Dinosaure) E0).RiderJump=Jump;
					((Dinosaure) E0).RiderStrafe=Strafe;
					((Dinosaure) E0).RiderSneak=Sneak;
				}
	        }
	        catch (IOException e)
	        {
	            throw new RuntimeException(e);
	        }
		}
		if("PteroFlight".equals(packet.channel))
		{
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.data));
			try
	        {
				int EntityID = in.readInt();
				float Angle = in.readFloat();
				float Pitch = in.readFloat();
				float Wing = in.readFloat();
				
				Entity E0 =((EntityClientPlayerMP)player).worldObj.getEntityByID(EntityID);
				if(E0 instanceof Pterosaure)
				{
					((Pterosaure)E0).AirAngle=Angle;
					((Pterosaure)E0).AirPitch=Pitch;
					((Pterosaure)E0).WingState=Wing;
					//System.out.println("CLIENT:"+String.valueOf(Wing));
				}
	        }
	        catch (IOException e)
	        {
	            throw new RuntimeException(e);
	        }
		}
	}

}