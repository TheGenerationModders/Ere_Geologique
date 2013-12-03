package ere_geologique.client.audio;

import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import ere_geologique.common.EreGeologique;

public class DinoSoundHandler
{    
	
    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event)
    {
    	SoundManager manager = event.manager;
        try
        {
        	manager.addSound("ere_geologique:triceratops_death.wav");
    		manager.addSound("ere_geologique:triceratops_living1.wav");
    		manager.addSound("ere_geologique:triceratops_living2.wav");
    		manager.addSound("ere_geologique:triceratops_living3.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_death1.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_death2.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_hurt.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_living1.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_living2.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_living3.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_scream1.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_scream2.wav");
    		manager.addSound("ere_geologique:tyrannosaurus_scream3.wav");
    		manager.addSound("ere_geologique:mosasaurus_attack1.wav");
    		manager.addSound("ere_geologique:mosasaurus_death1.wav");
    		manager.addSound("ere_geologique:mosasaurus_death2.wav");
    		manager.addSound("ere_geologique:mosasaurus_hurt.wav");
    		manager.addSound("ere_geologique:mosasaurus_living1.wav");
    		manager.addSound("ere_geologique:mosasaurus_living2.wav");
    		manager.addSound("ere_geologique:mosasaurus_outside1.wav");
    		manager.addSound("ere_geologique:mosasaurus_outside2.wav");
    		manager.addSound("ere_geologique:mosasaurus_outside3.wav");
    		manager.addSound("ere_geologique:mosasaurus_outside4.wav");
    		manager.addSound("ere_geologique:plesiosaurus_hurt.wav");
    		manager.addSound("ere_geologique:plesiosaurus_living.wav");
    		manager.addSound("ere_geologique:ankylosaurus_death.wav");
    		manager.addSound("ere_geologique:ankylosaurus_hurt1.wav");
    		manager.addSound("ere_geologique:ankylosaurus_hurt2.wav");
    		manager.addSound("ere_geologique:ankylosaurus_living1.wav");
    		manager.addSound("ere_geologique:ankylosaurus_living2.wav");
    		manager.addSound("ere_geologique:ankylosaurus_living3.wav");
    		manager.addSound("ere_geologique:brachiosaurus_death1.wav");
    		manager.addSound("ere_geologique:brachiosaurus_living2.wav");
    		manager.addSound("ere_geologique:brachiosaurus_living1.wav");
    		manager.addSound("ere_geologique:brachiosaurus_hurt1.wav");
    		manager.addSound("ere_geologique:dilophosaurus_call1.wav");
    		manager.addSound("ere_geologique:dilophosaurus_call2.wav");
    		manager.addSound("ere_geologique:dilophosaurus_death.wav");
    		manager.addSound("ere_geologique:dilophosaurus_hurt.wav");
    		manager.addSound("ere_geologique:dilophosaurus_living.wav");
    		manager.addSound("ere_geologique:pterosaurus_hurt.wav");
    		manager.addSound("ere_geologique:pterosaurus_living1.wav");
    		manager.addSound("ere_geologique:pterosaurus_living2.wav");
    		manager.addSound("ere_geologique:stegosaurus_death.wav");
    		manager.addSound("ere_geologique:stegosaurus_hurt.wav");
    		manager.addSound("ere_geologique:stegosaurus_living1.wav");
    		manager.addSound("ere_geologique:stegosaurus_living2.wav");
    		manager.addSound("ere_geologique:stegosaurus_living3.wav");
    		manager.addSound("ere_geologique:velociraptor_attack1.wav");
    		manager.addSound("ere_geologique:velociraptor_attack2.wav");
    		manager.addSound("ere_geologique:velociraptor_hurt1.wav");
    		manager.addSound("ere_geologique:velociraptor_hurt2.wav");
    		manager.addSound("ere_geologique:velociraptor_hurt3.wav");
    		manager.addSound("ere_geologique:velociraptor_hurt4.wav");
    		manager.addSound("ere_geologique:velociraptor_living_tame1.wav");
    		manager.addSound("ere_geologique:velociraptor_living_tame2.wav");
    		manager.addSound("ere_geologique:velociraptor_living_wild1.wav");
    		manager.addSound("ere_geologique:velociraptor_living_wild2.wav");
    		manager.addSound("ere_geologique:whip.wav");
        }
        catch(Exception e)
        {
            EreGeologique.EGLog.severe("Failed loading sound file: ");
        }
    }
}