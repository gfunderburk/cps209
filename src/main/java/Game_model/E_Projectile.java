package Game_model;

import Game_model.EK_Scenery.Type;

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
        return ("P,"+typeRound+"");
        
       
    }


    @Override
    public  void deSerialize(String data) {
    String decereal=data.split(",")[1];
     if(decereal=="LIGHT_ROUND"){
         typeRound=TypeRound.LIGHT_ROUND;
     }
     if(decereal=="HEAVY_ROUND"){
         typeRound=TypeRound.HEAVY_ROUND;
     }
     if(decereal=="EXPLOSIVE_ROUND"){
        typeRound=TypeRound.EXPLOSIVE_ROUND;
    }
       
        

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