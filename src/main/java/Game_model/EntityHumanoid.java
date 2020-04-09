package Game_model;

public abstract class EntityHumanoid extends EntityKillable{


    //  Variables  //

    
    int mag, ammo;
    public static enum positionState {CROUCHING, STANDING, JUMPING, WALKING};
    public static enum actionState {RELOADING, SHOOTING, THROWING, ENRAGED};


    //  Methods  //


    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);
}