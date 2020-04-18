package Game_model;

import Game_model.E_Projectile.TypeRound;
import javafx.geometry.Point3D;

public class EH_Avatar extends EntityHumanoid {


    //  Variables  //

    



    //  Singleton  //


    private EH_Avatar(){
        this.typeRound = TypeRound.LIGHT_ROUND;  
        this.setLocation(new Point3D(Game.getIt().getGamePhysicsWidth()/2, Game.getIt().getGamePhysicsHeight()/2, 0));       
    }

    private static EH_Avatar It = new EH_Avatar();

    public static EH_Avatar getIt(){
        return It;
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
        // TODO Auto-generated method stub

    }

    @Override
    public void attack(Entity entity){
        //Point3D aimedVector = myMovement.getHeading(entity.getLocation(), this.getLocation(), E_Projectile.getRoundTypeSpeed(this.getTypeRound()));
        E_Projectile.makeProjectile(this, entity);        
    }

    public void attack(double targetX, double targetY, double paneX, double paneY) {

        targetX = (targetX*Game.getIt().getGamePhysicsWidth()) / paneX;
        targetY = (targetY*Game.getIt().getGamePhysicsHeight()) / paneY;
        E_Projectile.makeProjectile(targetX, targetY);
    }

    @Override
    public void deSpawn() {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   



}