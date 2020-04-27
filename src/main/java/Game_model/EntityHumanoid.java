package Game_model;

import javafx.scene.image.Image;

public abstract class EntityHumanoid extends EntityKillable {


    //  Variables  //

    
    protected  static enum StateAction {MOVING, RELOADING, ATTACKING, SPECIAL_ATTACK, DYING, DEAD};
    protected  StateAction stateAction;
    protected  int mag, ammo;
    protected  E_Projectile.TypeRound typeRound;

    //  Methods  //


    public void enterState(StateAction newState){
        this.subStateInt = 0;
        this.stateAction = newState;
    };


    public void attack(Entity entity) {
        E_Projectile.makeProjectile(this, entity);
    };

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
}