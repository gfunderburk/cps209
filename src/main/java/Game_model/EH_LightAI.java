package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;
import Util_model.myMovement;
import Util_model.myRandom;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;

public class EH_LightAI extends EntityHumanoid {


    //  Variables  //


    final static String imageDir = File.separator + "light_terminators" + File.separator;
    final static Image imgDying1 = new Image(initChildImage(imageDir, "lightRobotDying1.png"));
    final static Image imgDying2 = new Image(initChildImage(imageDir, "lightRobotDying2.png"));
    final static Image imgDying3 = new Image(initChildImage(imageDir, "lightRobotDying3.png"));

    final static Image imgMovingL = new Image(initChildImage(imageDir, "lightRobot_Moving_LeftFoot.png"));
    final static Image imgMovingR = new Image(initChildImage(imageDir, "lightRobot_Moving_RightFoot.png"));
    final static Image imgReloading = new Image(initChildImage(imageDir, "lightRobot_Reloading.png"));
    final static Image imgAttacking = new Image(initChildImage(imageDir, "lightRobot_Shooting.png"));
    final static Image imgSpecialAttack = new Image(initChildImage(imageDir, "lightRobot_Shooting_special.png"));

    final static Image imgMovingL_hurt = new Image(initChildImage(imageDir, "lightRobot_Moving_LeftFoot_hurt.png"));
    final static Image imgMovingR_hurt = new Image(initChildImage(imageDir, "lightRobot_Moving_RightFoot_hurt.png"));
    final static Image imgReloading_hurt = new Image(initChildImage(imageDir, "lightRobot_Reloading_hurt.png"));
    final static Image imgAttacking_hurt = new Image(initChildImage(imageDir, "lightRobot_Shooting_hurt.png"));
    final static Image imgSpecialAttack_hurt = new Image(initChildImage(imageDir, "lightRobot_Shooting_special_hurt.png"));

    
    //  Constructor  //


    public EH_LightAI(){ 
        this.imageState = imgMovingL;
        this.stateAction = StateAction.MOVING;
        this.stateLife = StateLife.HEALTHY;
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.width = LaiW;
        this.height = LaiH;
        this.speed = 1;
        this.maxHealth = 2;
        this.stateIntFactor = 2;
        this.currentHealth = this.maxHealth;
    }


    //  Methods  //


    @Override
    public String Serialize() {
        return "H,"+typeRound+","+imageDir+","+imageState+","+width+","+height+","+speed;
    }


    @Override
    public  void deSerialize(String data) {
        String[] deCereal=data.split(",");
        if(deCereal[1].equals("LIGHT_ROUND")){
            typeRound=TypeRound.LIGHT_ROUND;
        }
        if(deCereal[1].equals("HEAVY_ROUND")){
            typeRound=TypeRound.HEAVY_ROUND;
        }
        if(deCereal[1].equals("EXPLOSIVE_ROUND")){
            typeRound=TypeRound.EXPLOSIVE_ROUND;
        }
        // imageDir=deCereal[2];
        // imageState=deCereal[3];
        width=Integer.parseInt(deCereal[4]);
        height=Integer.parseInt(deCereal[5]);
        speed=Double.parseDouble(deCereal[6]);
    }


    @Override
    public void deathEvent() {
        this.enterState(StateAction.DYING);
    }


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
            this.checkLife();    
        }
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
        this.mag--;
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
                        this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgMovingL : EH_LightAI.imgMovingL_hurt;

                    default:
                        if(this.subStateInt%3==0) 
                        if(this.subStateInt%2==0){
                            this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgMovingL : EH_LightAI.imgMovingL_hurt;
                        }  
                        else{
                            this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgMovingR : EH_LightAI.imgMovingR_hurt;
                        }
                        this.move();
                }
                break;

            case ATTACKING:
                switch(this.subStateInt){

                    case 20:
                        enterState(StateAction.MOVING);
                        break;

                    default:
                        if(this.mag <= 0) {
                            enterState(StateAction.RELOADING);
                        } 
                        if(myRandom.genRandomInt(1, 3) != 3) attack(EH_Avatar.getIt());
                        this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgAttacking : EH_LightAI.imgAttacking_hurt;
                }
                break;

            case SPECIAL_ATTACK:
                switch(this.subStateInt){

                    case 30:
                        enterState(StateAction.MOVING);
                        setTypeRound(TypeRound.LIGHT_ROUND);
                        break;

                    default:
                        setTypeRound(TypeRound.HEAVY_ROUND);
                        if(myRandom.genRandomInt(1, 3) != 3) attack(EH_Avatar.getIt());
                        this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgSpecialAttack : EH_LightAI.imgSpecialAttack_hurt;
                }
                break;

            case RELOADING:
                switch(this.subStateInt){

                    case 10:
                        this.mag = 30;
                        enterState(StateAction.ATTACKING);

                    default:
                        this.imageState = (this.stateLife != StateLife.HURT) ? EH_LightAI.imgAttacking : EH_LightAI.imgAttacking_hurt;
                }
                break;

            case DYING:
                switch(this.subStateInt){

                    case 1: 
                        this.imageState = EH_LightAI.imgDying1;
                        break;

                    case 7: 
                        this.imageState = EH_LightAI.imgDying2;
                        break;

                    case 14: 
                        this.imageState = EH_LightAI.imgDying3;
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
                break;
        }
    }

    @Override
    public void hurtEvent() {}

    @Override
    public void recoverEvent() {}
}