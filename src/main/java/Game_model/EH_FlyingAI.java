package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;
import Util_model.myMovement;
import Util_model.myRandom;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public class EH_FlyingAI extends EntityHumanoid {


    //  Variables  //

    
    



    //  Constructor  //


    public EH_FlyingAI(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.imageDir = File.separator + "flying_terminators" + File.separator;
        this.imageState = "headOnViewWRecoil_Firing.png";
        this.width = FaiW;
        this.height = FaiH;
        this.speed = 1;
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.stateLife = StateLife.HEALTHY;
        this.stateAction = StateAction.MOVING;
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
        this.deSpawn();
    }

    @Override
    public void move() {
        this.sameMoveCount++;

        if(this.sameMoveCount > 20){
            this.sameMoveCount = 0;

            if(!this.standStill){
                this.vector = new Point3D(0,0,0);
            }
            else{
                Point3D newDest = Game.getIt().randomPoint3D();
                this.vector = myMovement.getHeading(newDest, this.location, this.speed);
            }

            this.standStill = this.standStill ? false : true;
        }

        if(this.standStill & myRandom.genRandomBoolean()){
            attack(EH_Avatar.getIt());
        }

        super.move();
    }

    @Override
    public void collideEvent(Entity otherEntity) {
        E_Projectile ent = (E_Projectile)otherEntity;

        if(ent.isAvatarsProjectile()){
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
            Game.getIt().setScore(Game.getIt().getScore() + 8);
            this.checkLife();    
        }
    }

    @Override
    public void hurtEvent() {
        this.imageState = "headOnViewWRecoil_Firing_hurt.png";

    }

    @Override
    public void recoverEvent() {
        this.imageState = "headOnViewWRecoil_Firing.png";

    }

    @Override
    public void spawn() {
        
        Game.getIt().setAI_Left_ToSpawn(Game.getIt().getAI_Left_ToSpawn() - 1);
        Game.getIt().setCurrentAIspawnCnt(Game.getIt().getCurrentAIspawnCnt() + 1);

        this.location = Game.getIt().randomPoint3D();
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.y, 0);
        this.vector = new Point3D(0, 0, 0);

        super.spawn();
    }

    @Override
    public void attack(Entity entity) {
        super.attack(entity);
    }

    @Override
    public void deSpawn() {
        Game.getIt().setAI_Left(Game.getIt().getAI_Left() - 1);
        Game.getIt().setCurrentAIspawnCnt(Game.getIt().getCurrentAIspawnCnt() - 1);
        Game.getIt().checkGameOver();

        super.deSpawn();
    }

    @Override
    public void reload() {
        super.reload();
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