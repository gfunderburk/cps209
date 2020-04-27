package Game_model;

import java.io.File;
import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.scene.image.Image;

public class EK_Scenery extends EntityKillable {


    //  Variables  //

    
    public static enum Type {AMMO, HEALTH, POINTS};
    private static final String imageDir = File.separator + "scenery" + File.separator;
    private static final Image ammoImg = new Image(initChildImage(imageDir, "ammo_powerup.png"));
    private static final Image healthImg = new Image(initChildImage(imageDir, "health_powerup.png"));
    private static final Image pointsImg1 = new Image(initChildImage(imageDir, "point_powerup_1.png"));
    private static final Image pointsImg2 = new Image(initChildImage(imageDir, "point_powerup_2.png")); 
    private Type type;



    //  Constructor  //


    public EK_Scenery(Type type){
        this.speed = 0;
        this.stateIntFactor = 1;
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.location = Game.getIt().randomPoint3D();
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.y, 0);
        this.type = type;

        switch(this.type){
            
            case AMMO:
                this.imageState = ammoImg;
                this.width = 8;
                this.height = 6;
                break;
            
            case HEALTH:
                this.imageState = healthImg;
                this.width = 8;
                this.height = 6;
                break;
                
            case POINTS:
                this.imageState = pointsImg1;
                this.width = 6;
                this.height = 8;
                this.currentHealth = 10;
                break;
            
            default:
        }
    }


    //  Methods  //


    @Override
    public String Serialize() {
        return null;
    }


    @Override
    public void deSerialize(String data) {

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
                Game.getIt().setScore(Game.getIt().getScore() + 50);
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