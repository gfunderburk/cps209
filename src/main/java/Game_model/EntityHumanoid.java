/* --------------------------------------------------------------------------------------------- 
File:   EnitityHumanoid.java
Desc.   This class is the third-tiered and final Abstract class for the physical world.
        Any entity that can assault other entities in the physical world 
        inheret related content in this class.
        This class admins possible action states and projectile creation during attacking states.
--------------------------------------------------------------------------------------------- */


package Game_model;

import javafx.scene.media.AudioClip;

public abstract class EntityHumanoid extends EntityKillable {


    //  Variables  //

    
    protected static enum StateAction {MOVING, RELOADING, ATTACKING, SPECIAL_ATTACK, DYING, DEAD};
    protected StateAction stateAction;
    protected int mag, ammo;
    protected boolean dying, attacking;
    protected E_Projectile.TypeRound typeRound;
    protected AudioClip audio_dying, audio_attacking;

    
    //  Methods  //


    /** 
     * @param newState
     * resets the given entity's substate count and sets the given entity's stateaction to the input state
     */
    public void enterState(StateAction newState){
        this.subStateInt = 0;
        this.stateAction = newState;
    };


    /** 
     * @param entity the tageted entity
     * creates a projectile traveling from a given entity towards the input entity
     */
    public void attack(Entity entity) {
        E_Projectile.makeProjectile(this, entity);
    };


    /** 
     * resets the round count to 30 for a given entity
     */
    public void reload(){
        this.mag = 30;
        this.ammo -= 30;
    };


    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);


    //  Getter - Setters  //


    public StateAction getStateAction() {
        return stateAction;
    }

    public void setStateAction(StateAction stateAction) {
        this.stateAction = stateAction;
    }

    public int getMag() {
        return mag;
    }

    public void setMag(int mag) {
        this.mag = mag;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public E_Projectile.TypeRound getTypeRound() {
        return typeRound;
    }

    public void setTypeRound(E_Projectile.TypeRound typeRound) {
        this.typeRound = typeRound;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public AudioClip getAudio_dying() {
        return audio_dying;
    }

    public void setAudio_dying(AudioClip audio_dying) {
        this.audio_dying = audio_dying;
    }

    public AudioClip getAudio_attacking() {
        return audio_attacking;
    }

    public void setAudio_attacking(AudioClip audio_attacking) {
        this.audio_attacking = audio_attacking;
    }
}