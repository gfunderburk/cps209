package Game_model;

import Game_model.E_Projectile.TypeRound;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.util.Duration;

public class EH_Avatar extends EntityHumanoid {


    //  Variables  //

    static TypeRound roundType = TypeRound.LIGHT_ROUND;

    // Constructor //


    //  Singleton  //


    private EH_Avatar(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.setLocation(new Point3D(Game.getIt().getGamePhysicsWidth()/2, 0, -1));       
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.mag = 300;
        this.ammo = 300;
    }

    private static EH_Avatar It = new EH_Avatar();

    public static EH_Avatar getIt(){
        return It;
    }


    public static void resetAvatarSingleton() {
        It = new EH_Avatar();
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
        Game.getIt().checkGameOver();
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideEvent(Entity otherEntity) { 
        E_Projectile ent = (E_Projectile)otherEntity;

        if(! ent.isAvatarsProjectile()){
            switch(ent.getTypeRound()){

                case LIGHT_ROUND:
                    this.currentHealth -= E_Projectile.getLightRoundDmg();
                    break;
                
                case HEAVY_ROUND:
                    this.currentHealth -= E_Projectile.getHeavyRoundDmg();
                    break;

                case EXPLOSIVE_ROUND:
                    this.currentHealth -= E_Projectile.getExplosiveRoundDmg();
                    break;

                default:
                    break;
            }
            this.checkLife();    
        }

    }

    @Override
    public void hurtEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void recoverEvent() {
        // TODO Auto-generated method stub
        //Experimented by adding a thread since it was freezing up while I was debugging.
        //wasn't sure if it was trying to access the currentHealth variable from multiple places at the same time.
        //this didn't seem to do anything however, so do as you please here!
        // Thread thread = new Thread( () -> {
        //     KeyFrame timer = new KeyFrame(Duration.seconds(1), e -> {
        //     currentHealth += .01;
        //     });
        //     var timeline = new Timeline(timer);
        //     timeline.play();
        // });
        // thread.start();

    }

    @Override
    public void spawn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void attack(Entity entity){
        
        // if(this.mag > 0){        
        //     //Point3D aimedVector = myMovement.getHeading(entity.getLocation(), this.getLocation(), E_Projectile.getRoundTypeSpeed(this.getTypeRound()));
        //     E_Projectile.makeProjectile(this, entity);   
        //     this.mag -= 1;
        // }     
    }

    public void attack(double targetX, double targetY, double paneX, double paneY) {

        if(this.mag > 0){  
            targetX = (targetX*Game.getIt().getGamePhysicsWidth()) / paneX;
            targetY = (targetY*Game.getIt().getGamePhysicsHeight()) / paneY;
            E_Projectile.makeProjectile(targetX, targetY);
            // this.mag -= 1;
        }     
    }

    @Override
    public void deSpawn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deSerialize(String data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reload() {
        if(this.mag < 300 & this.ammo > 0){
            super.reload();
        }
    }

    @Override
    public void stateIncrement() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void subStateUpdate() {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


   



}