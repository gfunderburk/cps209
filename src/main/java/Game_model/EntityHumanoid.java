/* --------------------------------------------------------------------------------------------- 
File:   EnitityHumanoid.java
Desc.   This class is the third-tiered and final Abstract class for the physical world.
        Any entity that can assault other entities in the physical world 
        inheret related content in this class.
        This class admins possible action states and projectile creation during attacking states.
--------------------------------------------------------------------------------------------- */


package Game_model;

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