package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;

public class EH_LightAI extends EntityHumanoid {


    //  Variables  //




    //  Constructor  //


    public EH_LightAI(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.imageDir = File.separator + "light_terminators" + File.separator;
        this.imageState = "lightRobotRifleFrontFacing_Shooting.png";
        this.width = 10;
        this.height = 50;
        this.speed = 1;
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
        super.move();

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
        super.spawn();
    }

    @Override
    public void attack(Entity entity) {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   



}