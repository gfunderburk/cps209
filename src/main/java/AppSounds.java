import javafx.scene.media.AudioClip;

public class AppSounds {
 

    //  Singleton  //


    private AppSounds(){
    }

    private static AppSounds It = new AppSounds();

    public static AppSounds it(){
        return It;
    }
 

    //  Methods  //
    

    public static AudioClip audioClip(Object parentClass, String audioURL){
        return new AudioClip(parentClass.getClass().getResource("/media/"+audioURL).toString());
    }
 

    //  Media Elements  //   


    final AudioClip SHOOT_FOOTSOLDIER = audioClip(this, "footsoldiergun.wav");
    final AudioClip SHOOT_50CAL       = audioClip(this, "50cal.mp3");
    final AudioClip SHOOT_M16         = audioClip(this, "m16.mp3");
    final AudioClip SHOOT_SHOTGUN     = audioClip(this, "shotgun.mp3");

    final AudioClip BTN_CLICK = audioClip(this, "btnClick_seatBelt.mp3");
    final AudioClip THEME     = audioClip(this, "maintheme.mp3");

    final AudioClip LAI_attacking   = audioClip(this, "pistol.mp3");
    final AudioClip LAI_dying       = audioClip(this, "lightAIHit.mp3");

    final AudioClip HAI_attacking   = audioClip(this, "heavyMachineGun.mp3");
    final AudioClip HAI_dying       = audioClip(this, "heavyAIHit.mp3");

    final AudioClip FAI_attacking   = audioClip(this, "footsoldiergun.wav");
    final AudioClip FAI_dying       = audioClip(this, ".mp3");
    
    final AudioClip BAI_attacking   = audioClip(this, "heavyMachineGun.mp3");
    final AudioClip BAI_dying       = audioClip(this, "bossAIHit.mp3");

    final AudioClip Avatar_hurt                 = audioClip(this, "avatarHit.mp3");
    final AudioClip Avatar_dying                = audioClip(this, "avatarDying.mp3");
    final AudioClip Avatar_wins                 = audioClip(this, "avatarWin.mp3");
    final AudioClip Avatar_reloading            = audioClip(this, "reload.mp3");
    final AudioClip Avatar_attacking            = audioClip(this, "pistol.mp3");
    final AudioClip Avatar_hurtCheatmode   = audioClip(this, "cheatModeHit.mp3");
}