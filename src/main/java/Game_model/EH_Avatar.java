/* --------------------------------------------------------------------------------------------- 
File:   EH_Avatar.java
Desc.   EntityHumaniod_Avatar is a singleton class that governs the game player's 
        action events in the in-game.
--------------------------------------------------------------------------------------------- */


package Game_model;

import Game_model.E_Projectile.TypeRound;
import javafx.geometry.Point3D;

public class EH_Avatar extends EntityHumanoid {
 
    private boolean gotHurt;

    //  Singleton  //


    private EH_Avatar(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.setLocation(new Point3D(Game.getIt().getGamePhysicsWidth()/2, 0, -1));       
        this.maxHealth = 100;        
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
                    this.currentHealth -= E_Projectile.getLightRoundDmg()/10;
                    break;
                
                case HEAVY_ROUND:
                    this.currentHealth -= E_Projectile.getHeavyRoundDmg()/10;
                    break;

                case EXPLOSIVE_ROUND:
                    this.currentHealth -= E_Projectile.getExplosiveRoundDmg()/10;
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
    public void hurtEvent() {
        this.gotHurt = true;
    }

    @Override
    public void recoverEvent() {}

    @Override
    public void attack(Entity entity){}

    @Override
    public void deSerialize(String data) {}

    @Override
    protected void subStateUpdate(){}

    public boolean isGotHurt() {
        return gotHurt;
    }

    public void setGotHurt(boolean gotHurt) {
        this.gotHurt = gotHurt;
    }
}