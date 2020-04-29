/* --------------------------------------------------------------------------------------------- 
File:   GameSounds.java
Desc.   GameSounds acts as the central storage place for all program sounds.
--------------------------------------------------------------------------------------------- */


package Game_model;

import javafx.scene.media.AudioClip;

public class GameSounds {
 

    //  Singleton  //


    private GameSounds(){
    }

    private static GameSounds It = new GameSounds();

    public static GameSounds it(){
        return It;
    }
 

    //  Methods  //
    

    public static AudioClip audioClip(Object parentClass, String audioURL){
        return new AudioClip(parentClass.getClass().getResource("/media/"+audioURL).toString());
    }
 

    //  Media Elements  //   


    public final AudioClip SHOOT_FOOTSOLDIER = audioClip(this, "footsoldiergun.wav");
    public final AudioClip SHOOT_50CAL       = audioClip(this, "50cal.mp3");
    public final AudioClip SHOOT_M16         = audioClip(this, "m16.mp3");
    public final AudioClip SHOOT_SHOTGUN     = audioClip(this, "shotgun.mp3");

    public final AudioClip BTN_CLICK        = audioClip(this, "btnClick_seatBelt.mp3");
    public final AudioClip THEME            = audioClip(this, "maintheme.mp3");

    public final AudioClip LAI_attacking   = audioClip(this, "pistol.mp3");
    public final AudioClip LAI_dying       = audioClip(this, "lightAIHit.mp3");

    public final AudioClip HAI_attacking   = audioClip(this, "heavyMachineGun.mp3");
    public final AudioClip HAI_dying       = audioClip(this, "heavyAIHit.mp3");

    public final AudioClip FAI_attacking   = audioClip(this, "footsoldiergun.wav");
    public final AudioClip FAI_dying       = audioClip(this, "planeCrash.mp3");
    
    public final AudioClip BAI_attacking   = audioClip(this, "heavyMachineGun.mp3");
    public final AudioClip BAI_dying       = audioClip(this, "bossAIHit.mp3");

    public final AudioClip Avatar_hurt                 = audioClip(this, "avatarHit.mp3");
    public final AudioClip Avatar_loses                = audioClip(this, "avatarDying.mp3");
    public final AudioClip Avatar_wins                 = audioClip(this, "avatarWin.mp3");
    public final AudioClip Avatar_reloading            = audioClip(this, "reload.mp3");
    public final AudioClip Avatar_attacking            = audioClip(this, "pistol.mp3");
    public final AudioClip Avatar_attackingEmptyMag    = audioClip(this, "btnClick_seatBelt.mp3");
    public final AudioClip Avatar_attackingCheatmode   = audioClip(this, "rayGun.mp3");
    public final AudioClip Avatar_hurtCheatmode   = audioClip(this, "cheatModeHit.mp3");


    
}