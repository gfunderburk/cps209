package Game_model;

public class EK_Scenery extends EntityKillable {


    //  Variables  //

    
    public static enum Type {WALL_WOOD, WALL_STONE, CRATE, BARREL};
    public Type type;
    public static enum Item {LIGHT_AMMO, HEAVY_AMMO, EXPLOSIVES_AMMO, BOMB};
    public Item item;



    //  Constructor  //


    public EK_Scenery(){
        
    }


    //  Methods  //


    @Override
    public String Serialize() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void deSerialize(String data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deathEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hurtEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void recoverEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawn() {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   


}