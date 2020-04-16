package Game_model;

public abstract class EntityHumanoid extends EntityKillable{


    //  Variables  //

    
    protected  static enum StatePosition {CROUCHING, STANDING, JUMPING, WALKING};
    protected  static enum StateAction {RELOADING, SHOOTING, THROWING, ENRAGED};
    protected  StatePosition statePosition;
    protected  StateAction stateAction;
    protected  int mag, ammo;
    protected  E_Projectile.TypeRound typeRound;

    //  Methods  //

    public void attack(Entity entity) {

    };

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);


    //  Getter - Setters  //


    public StatePosition getStatePosition() {
        return statePosition;
    }

    public void setStatePosition(StatePosition statePosition) {
        this.statePosition = statePosition;
    }

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