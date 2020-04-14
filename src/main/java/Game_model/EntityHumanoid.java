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
}