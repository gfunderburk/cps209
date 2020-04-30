/* --------------------------------------------------------------------------------------------- 
File:   EK_Scenery.java
Desc.   This EntityKillable_Scenery class governs the action events of the 
        power-ups scattered throughout all the levels.
--------------------------------------------------------------------------------------------- */


package Game_model;

import java.io.File;
import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.scene.image.Image;

public class EK_Scenery extends EntityKillable {


    //  Variables  //

    
    private static final String imageDir =  File.separator + "scenery" + File.separator;
    private static final Image ammoImg =    new Image(initChildImage(imageDir, "ammo_powerup.png"));
    private static final Image healthImg =  new Image(initChildImage(imageDir, "health_powerup.png"));
    private static final Image pointsImg1 = new Image(initChildImage(imageDir, "point_powerup_1.png"));
    private static final Image pointsImg2 = new Image(initChildImage(imageDir, "point_powerup_2.png")); 
    public static enum Type {AMMO, HEALTH, POINTS};
    private Type type;      //  the type of power-up that the given entity_scenery is



    //  Constructor  //


    public EK_Scenery(Type type){
        this.speed = 0;
        this.type = type;
        this.maxHealth = 10;
        this.stateIntFactor = 1;
        this.currentHealth = this.maxHealth;
        this.location = Game.getIt().randomPoint3D();
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.y, 0);
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.z, 0);

        switch(this.type){
            
            case AMMO:
                this.imageState = ammoImg;
                this.width =      AmmoW;
                this.height =     AmmoH;
                break;
            
            case HEALTH:
                this.imageState = healthImg;
                this.width =      HealthW;
                this.height =     HealthH;
                break;
                
            case POINTS:
                this.imageState = pointsImg1;
                this.width =      PointsW;
                this.height =     PointsH;
                break;
            
            default:
        }
    }


    //  Methods  //
    
    //------------------------------------------------------------------------------//
    //  The methods below are inhereted and described in the parent abstract class  //
    //------------------------------------------------------------------------------//


    @Override
    public String Serialize() {
       return "S,"+this.type;
    }


    @Override
    public void deSerialize(String data) {
        String[] deCereal=data.split(",");
        if(deCereal[1].equals(("AMMO"))){
          this.type=Type.AMMO;
        }
        if(deCereal[1].equals(("HEALTH"))){
            this.type=Type.HEALTH;
          }
          if(deCereal[1].equals(("POINTS"))){
            this.type=Type.POINTS;
          }
    }


    @Override
    public void deathEvent() {

        switch(this.type){
            
            case AMMO:
                EH_Avatar.getIt().setAmmo(EH_Avatar.getIt().getAmmo() + 150);
                break;
            
            case HEALTH:
                EH_Avatar.getIt().setCurrentHealth(EH_Avatar.getIt().getCurrentHealth() + 100);
                break;
                
            case POINTS:
                Game.getIt().setScore(Game.getIt().getScore() + 300);
                break;
            
            default:
        }
        this.deSpawn();
    }


    @Override
    public void collideEvent(Entity otherEntity) {
        if(this.type == Type.POINTS & this.currentHealth == 10){
            this.imageState = pointsImg2;
            this.currentHealth--;
        }
        else{
            this.deathEvent();
        }
    }
    

    @Override
    public void spawn() {
        super.spawn();
    }


    @Override
    public void deSpawn() {
        super.deSpawn();
    }


    @Override
    protected void subStateUpdate(){}
   
    @Override
    public void recoverEvent(){}

    @Override
    public void hurtEvent(){}


    //  Getters-Setters  //


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}