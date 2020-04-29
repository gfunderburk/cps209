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


    public final AudioClip BTN_CLICK         = audioClip(this, "_btn_click.mp3");
    public final AudioClip THEME             = audioClip(this, "_theme.mp3");

    public final AudioClip LAI_attacking     = audioClip(this, "LAI_attack.mp3");
    public final AudioClip LAI_dying         = audioClip(this, "LAI_death.mp3");

    public final AudioClip HAI_attacking     = audioClip(this, "HAI_attack.mp3");
    public final AudioClip HAI_dying         = audioClip(this, "HAI_death.mp3");

    public final AudioClip FAI_attacking     = audioClip(this, "FAI_attack.mp3");
    public final AudioClip FAI_dying         = audioClip(this, "FAI_death.mp3");
    
    public final AudioClip BAI_attacking     = audioClip(this, "BAI_attack.mp3");
    public final AudioClip BAI_dying         = audioClip(this, "BAI_death.mp3");

    public final AudioClip Avatar_hurt                 = audioClip(this, "Ava_hurt.mp3");
    public final AudioClip Avatar_loses                = audioClip(this, "Ava_death.mp3");
    public final AudioClip Avatar_wins                 = audioClip(this, "Ava_win.mp3");
    public final AudioClip Avatar_reloading            = audioClip(this, "Ava_reload.mp3");
    public final AudioClip Avatar_attacking            = audioClip(this, "Ava_attack.mp3");
    public final AudioClip Avatar_attackingEmptyMag    = audioClip(this, "Ava_attack_emptymag.mp3");
    public final AudioClip Avatar_attackingCheatmode   = audioClip(this, "Ava_attack_cheatmode.mp3");

}