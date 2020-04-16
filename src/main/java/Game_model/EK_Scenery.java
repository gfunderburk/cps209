package Game_model;

import javafx.geometry.Point3D;

public class EK_Scenery extends EntityKillable {


    //  Variables  //

    
    public static enum Type {WALL_WOOD, WALL_STONE, CRATE, BARREL};
    private Type type;
    public static enum Item {LIGHT_AMMO, HEAVY_AMMO, EXPLOSIVES_AMMO, BOMB};
    private Item item;



    //  Constructor  //


    public EK_Scenery(int x, int y, int z, Type type, Item item){
        this.type = type;
        if(item != null) this.item = item;
        this.location = new Point3D(x, y, z);
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


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
   


}