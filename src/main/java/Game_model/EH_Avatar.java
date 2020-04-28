/* --------------------------------------------------------------------------------------------- 
File:   .java
Desc.   
--------------------------------------------------------------------------------------------- */


package Game_model;

import Game_model.E_Projectile.TypeRound;
import javafx.geometry.Point3D;

public class EH_Avatar extends EntityHumanoid {
 

    //  Singleton  //


    private EH_Avatar(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.setLocation(new Point3D(Game.getIt().getGamePhysicsWidth()/2, 0, -1));       
        this.maxHealth = 10;        
        this.currentHealth = this.maxHealth;
        this.mag = 30;
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
            EH_Avatar.getIt().typeRound = TypeRound.LIGHT_ROUND;
        }
    }


    @Override
    public void deathEvent() {
        Game.getIt().checkGameOver();
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


    public void attack(double targetX, double targetY, double paneX, double paneY) {

        if( Game.getIt().isCheatMode() | this.mag > 0){  
            targetX = (targetX*Game.getIt().getGamePhysicsWidth()) / paneX;
            targetY = (targetY*Game.getIt().getGamePhysicsHeight()) / paneY;
            E_Projectile.makeProjectile(targetX, targetY);
        }     
    }


    @Override
    public void reload() {
        if(this.mag < 30 && this.ammo > 0){
            super.reload();
        }
    }


    @Override
    public void hurtEvent() {}

    @Override
    public void recoverEvent() {}

    @Override
    public void attack(Entity entity){}

    @Override
    public void deSerialize(String data) {}

    @Override
    protected void subStateUpdate(){}
}