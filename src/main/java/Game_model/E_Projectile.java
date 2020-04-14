package Game_model;

public class E_Projectile extends Entity{


    //  Variables  //


    public static enum TypeRound {LIGHT_ROUND, HEAVY_ROUND, EXPLOSIVE_ROUND};
    private TypeRound typeRound;    



    //  Constructor  //


    public E_Projectile(){
        
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
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawn() {
        // TODO Auto-generated method stub

    }

    
    //  Getters-Setters  //


    public TypeRound getTypeRound() {
        return typeRound;
    }

    public void setTypeRound(TypeRound typeRound) {
        this.typeRound = typeRound;
    }
}