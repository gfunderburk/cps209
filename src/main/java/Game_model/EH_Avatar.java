package Game_model;

import Game_model.E_Projectile.TypeRound;

public class EH_Avatar extends EntityHumanoid {


    //  Variables  //

    static TypeRound roundType = TypeRound.LIGHT_ROUND;

    // Constructor //

    public EH_Avatar() {
        this.typeRound = TypeRound.LIGHT_ROUND;
    }

    // Methods //

    @Override
    public String Serialize() {
        return ("U ," + typeRound + "");
    }

    public static void DeSerialize(String data) {
        String deceral = data.split(",")[1];
        if (deceral.equals("LIGHT_ROUND")) {
            roundType = TypeRound.LIGHT_ROUND;
        }
        

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

    @Override
    public void attack(Entity entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deSerialize(String data) {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   



}