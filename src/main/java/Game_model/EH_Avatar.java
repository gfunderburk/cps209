/* --------------------------------------------------------------------------------------------- 
File:   EH_Avatar.java
Desc.   EntityHumaniod_Avatar is a singleton class that governs the game player's 
        action events in the in-game.
--------------------------------------------------------------------------------------------- */


package Game_model;

import Game_model.E_Projectile.TypeRound;
import javafx.geometry.Point3D;

public class EH_Avatar extends EntityHumanoid {
 
    private boolean gotHurt;    //  is true momentarily if player.currentHealth falls below 50% of its maxHealth
                                //      is used to trigger the Ava_hurt.mp3 sounds in the View


    //  Singleton  //


    private EH_Avatar(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.setLocation(new Point3D(Game.getIt().getGamePhysicsWidth()/2, 0, -1)); // sets the player's location to be (center, 0, -1)    
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


    /** 
     * @param targetX   the raw X-coord of the user's mouse when he fired the attack event
     * @param targetY   the raw Y-coord of the user's mouse when he fired the attack event
     * @param paneX     the raw width of the View's pane. used to properly scale the projectile coords from visual to physical worlds
     * @param paneY     the raw height of the View's pane. used to properly scale the projectile coords from visual to physical worlds
     * creates a projectile heading from the player towards wherever he clicked his mouse on the View's pane
     */
    public void attack(double targetX, double targetY, double paneX, double paneY) 
    {
        if( Game.getIt().isCheatMode() | this.mag > 0)
        {  
            targetX = (targetX*Game.getIt().getGamePhysicsWidth()) / paneX;
            targetY = (targetY*Game.getIt().getGamePhysicsHeight()) / paneY;
            E_Projectile.makeProjectile(targetX, targetY);
        }     
    }


    //------------------------------------------------------------------------------//
    //  The methods below are inhereted and described in the parent abstract class  //
    //------------------------------------------------------------------------------//


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