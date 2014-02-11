package ere_geologique.common.entity.IA;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import ere_geologique.common.entity.Dinosaure;
import ere_geologique.proxy.network.ServerPacketHandler;

public abstract class DinoAIRide extends EntityAIBase
{
    protected final Dinosaure dinosaure;
    protected EntityPlayer rider;
    private ServerPacketHandler remoteKey = ServerPacketHandler.getInstance();

    public DinoAIRide(Dinosaure dinosaure)
    {
        this.dinosaure = dinosaure;
        setMutexBits(0xffffffff);
    }

    /*
    protected boolean isFlyUp() {
        return remoteKey.isKeyPressed(rider.username, "key.dragon.flyUp");
    }
    
    protected boolean isFlyDown() {
        return remoteKey.isKeyPressed(rider.username, "key.dragon.flyDown");
    }
    */
    
    protected boolean isRiderJumping()
    {
    	return remoteKey.isKeyPressed(rider.getDisplayName(), "key.jump");
    }
    
    @Override
    public boolean shouldExecute()
    {   
        rider = dinosaure.getRidingPlayer();
        return rider != null;
    }
}