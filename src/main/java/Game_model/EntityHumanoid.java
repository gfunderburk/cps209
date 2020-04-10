package Game_model;

public abstract class EntityHumanoid extends EntityKillable{


    //  Variables  //

    
    public static enum StatePosition {CROUCHING, STANDING, JUMPING, WALKING};
    public static enum StateAction {RELOADING, SHOOTING, THROWING, ENRAGED};
    public StatePosition statePosition;
    public StateAction stateAction;
    public int mag, ammo;
    public E_Projectile.TypeRound typeRound;

    //  Methods  //

    public void attack(Entity entity) {

    };

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);
}