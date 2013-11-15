package ere_geologique.common.event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;
import ere_geologique.common.EreGeologique;

public class KeyHandler
{
	private String name;
	private boolean down;

	public KeyHandler(String name, boolean down)
	{
		this.name = name;
		this.down = down;
	}

	public KeyHandler(KeyBinding kb)
	{
		this(kb.keyDescription, kb.pressed);
	}

	public KeyHandler(Packet250CustomPayload packet)
	{
		read(packet.data);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isDown()
	{
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public final byte[] write()
	{
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(bos);
         
         try {
             dos.writeUTF(name);
             dos.writeBoolean(down);
             dos.close();
         } catch (IOException ex)
         {
             EreGeologique.EGLog.severe("Can't write packet ");
         }
 
         return bos.toByteArray();
     }

	public final void read(byte[] data)
	{
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         DataInputStream dis = new DataInputStream(bis);
         
         try {
             name = dis.readUTF();
             down = dis.readBoolean();
             dis.close();
         } catch (IOException ex)
         {
        	 EreGeologique.EGLog.severe("Can't read packet");
         }
     }
}