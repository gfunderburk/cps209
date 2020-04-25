package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;
import Util_model.myMovement;
import Util_model.myRandom;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public class EH_HeavyAI extends EntityHumanoid {


    //  Variables  //

    



    //  Constructor  //


    public EH_HeavyAI(){
        this.imageDir = File.separator + "heavy_terminators" + File.separator;
        this.imgDying1 = "heavyRobotDying1.png";
        this.imgDying2 = "heavyRobotDying2.png";
        this.imgDying3 = "heavyRobotDying3.png";
        this.imgMovingL = "heavyRobot_Moving_LeftFoot";
        this.imgMovingR = "heavyRobot_Moving_RightFoot";
        this.imgReloading = "heavyRobot_Reloading";
        this.imgAttacking = "heavyRobot_Shooting";    
        this.imgSpecialAttack = "heavyRobot_Shooting_Special";    
        this.imageState = imgMovingL+ending();
        this.stateAction = StateAction.MOVING;
        this.stateLife = StateLife.HEALTHY;
        this.typeRound = TypeRound.HEAVY_ROUND;
        this.width = HaiW;
        this.height = HaiH;
        this.speed = 1;
        this.maxHealth = 10;
        this.stateIntFactor = 2;
        this.currentHealth = this.maxHealth;
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
        this.enterState(StateAction.DYING);
    }

    // @Override
    // public void move() {
    //     this.sameMoveCount++;

    //     if(this.sameMoveCount > 20){
    //         this.sameMoveCount = 0;

    //         if(!this.standStill){
    //             this.vector = new Point3D(0,0,0);
    //         }
    //         else{
    //             Point3D newDest = Game.getIt().randomPoint3D();
    //             this.vector = myMovement.getHeading(newDest, this.location, this.speed);
    //             this.vector = myMovement.setNewPointComp(this.vector, Point3D_Comp.y, 0);
    //         }

    //         this.standStill = this.standStill ? false : true;
    //     }

    //     if(this.standStill & myRandom.genRandomBoolean()){
    //         attack(EH_Avatar.getIt());
    //     }

    //     super.move();
    // }
    
    @Override
    public void move() {
        super.move();
    }

    public void newDirection(){
        Point3D newDest = Game.getIt().randomPoint3D();
        this.vector = myMovement.getHeading(newDest, this.location, this.speed);
        this.vector = myMovement.setNewPointComp(this.vector, Point3D_Comp.y, 0);
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
            Game.getIt().setScore(Game.getIt().getScore() + 15);
            this.checkLife();    
        }
    }

    @Override
    public void hurtEvent() {
        // this.imageState = "heavyRobot_Shooting_Ready_hurt.png";

    }

    @Override
    public void recoverEvent() {
        // this.imageState = "heavyRobot_Shooting_Ready.png";

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
    protected void subStateUpdate() {
        switch(this.stateAction){

            case MOVING:                
                switch(this.subStateInt){

                    case 20:
                        if(myRandom.genRandomInt(1, 4) != 4){
                            enterState(StateAction.ATTACKING);
                        }
                        else{
                            enterState(StateAction.SPECIAL_ATTACK);
                        }
                        break;
                        
                    case 1:
                        this.newDirection();
                        this.imageState = this.imgMovingL+ending(); 

                    default:
                        if(this.subStateInt%3==0) this.imageState = (this.subStateInt%2==0) ? this.imgMovingL+ending() : this.imgMovingR+ending(); 
                        this.move();
                }
                break;

            case ATTACKING:
                switch(this.subStateInt){

                    case 20:
                        enterState(StateAction.MOVING);
                        this.imageState = this.imgMovingL+ending(); 
                        break;

                    default:
                        if(this.mag <= 0) enterState(StateAction.RELOADING); 
                        if(myRandom.genRandomInt(1, 3) != 3) attack(EH_Avatar.getIt());
                }
                break;

            case SPECIAL_ATTACK:
                switch(this.subStateInt){

                    case 30:
                        enterState(StateAction.MOVING);
                        setTypeRound(TypeRound.HEAVY_ROUND);
                        break;

                    default:
                        setTypeRound(TypeRound.EXPLOSIVE_ROUND);
                        if(myRandom.genRandomInt(1, 3) != 3) attack(EH_Avatar.getIt());
                }
                break;

            case RELOADING:
                switch(this.subStateInt){

                    case 10:
                        this.mag = 30;
                        enterState(StateAction.ATTACKING);

                    default:
                }
                break;

            case DYING:
                switch(this.subStateInt){

                    case 1: 
                        this.imageState = this.imgDying1;
                        break;

                    case 7: 
                        this.imageState = this.imgDying2;
                        break;

                    case 14: 
                        this.imageState = this.imgDying3;
                        break;

                    case 21:
                        enterState(StateAction.DEAD);

                    default:                        
                }
                break;


            case DEAD:
                Game.getIt().setScore(Game.getIt().getScore() + 10);
                this.deSpawn();
                break;

            default:
                enterState(StateAction.MOVING);
                this.imageState = this.imgMovingL+ending(); 
                break;
        }

    }



    //  Getters-Setters  //


   



}