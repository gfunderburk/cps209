package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;
import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

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
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.stateLife = StateLife.HEALTHY;
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
        this.sameMoveCount++;

        if(this.sameMoveCount > 20){
            this.sameMoveCount = 0;

            if(this.standStill){
                this.vector = new Point3D(0,0,0);
            }
            else{
                Point3D newDest = Game.getIt().randomPoint3D();
                this.vector = myMovement.getHeading(newDest, this.location, this.speed);
                this.vector = myMovement.setNewPointComp(this.vector, Point3D_Comp.y, 0);
            }

            this.standStill = this.standStill ? false : true;
        }

        super.move();
    }

    @Override
    public void collideEvent(Entity otherEntity) {
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
        this.location = Game.getIt().randomPoint3D();
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.y, 0);
        this.vector = new Point3D(0, 0, 0);

        super.spawn();
    }

    @Override
    public void attack(Entity entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deSpawn() {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   



}