package Game_model;

import java.io.File;

import Game_model.E_Projectile.TypeRound;
import Util_model.myMovement;
import Util_model.myRandom;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public class EH_LightAI extends EntityHumanoid {


    //  Variables  //




    //  Constructor  //


    public EH_LightAI(){
        this.typeRound = TypeRound.LIGHT_ROUND;
        this.imageDir = File.separator + "light_terminators" + File.separator;
        this.imageState = "lightRobotRifleFrontFacing_Shooting.png";
        this.width = LaiW;
        this.height = LaiH;
        this.speed = 1;
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.stateLife = StateLife.HEALTHY;
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
        imageDir=deCereal[2];
        imageState=deCereal[3];
        width=Integer.parseInt(deCereal[4]);
        height=Integer.parseInt(deCereal[5]);
        speed=Double.parseDouble(deCereal[6]);
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
                this.vector = myMovement.setNewPointComp(this.vector, Point3D_Comp.y, 0);
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
            Game.getIt().setScore(Game.getIt().getScore() + 10);
            this.checkLife();    
        }
    }

    @Override
    public void hurtEvent() {
        this.imageState = "lightRobotRifleFrontFacing_Shooting_hurt.png";

    }

    @Override
    public void recoverEvent() {
        this.imageState = "lightRobotRifleFrontFacing_Shooting.png";

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


    //  Getters-Setters  //


   



}