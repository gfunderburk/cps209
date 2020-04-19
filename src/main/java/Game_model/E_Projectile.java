package Game_model;

import java.io.File;

import Util_model.myMovement;
import javafx.geometry.Point3D;

public class E_Projectile extends Entity {


    //  Variables  //


    public static enum TypeRound {LIGHT_ROUND, HEAVY_ROUND, EXPLOSIVE_ROUND};
    private static double lightRoundSpeed = 1;
    private static double heavyRoundSpeed = 1;
    private static double explosiveRoundSpeed = 1;
    private TypeRound typeRound;    



    //  Constructor  //


    public static void makeProjectile(EntityHumanoid thisEntity, Entity thatEntity){

        E_Projectile bullet = new E_Projectile();
        bullet.typeRound = thisEntity.getTypeRound();
        bullet.imageDir = File.separator + "projectiles" + File.separator;
        bullet.imageState = "bulletRed.png";
        bullet.width = 2.5;
        bullet.height = 2.5;
        bullet.setLocation(thisEntity.getLocation());
        bullet.setVector(myMovement.getHeading(EH_Avatar.getIt().getLocation(), thisEntity.getLocation(), getRoundTypeSpeed(thisEntity.getTypeRound())));        
        bullet.spawn();
    }

    
    public static void makeProjectile(double targetX, double targetY){

        E_Projectile bullet = new E_Projectile();
        bullet.typeRound = EH_Avatar.getIt().getTypeRound();
        bullet.imageDir = File.separator + "projectiles" + File.separator;
        bullet.imageState = "bulletRed.png";
        bullet.width = 2.5;
        bullet.height = 2.5;
        bullet.setLocation(new Point3D(targetX, targetY, 0));
        bullet.setVector(new Point3D(0,0, getRoundTypeSpeed(EH_Avatar.getIt().getTypeRound()))); 
        bullet.spawn();
    }


    //  Methods  //


    @Override
    public String Serialize() {
        return ("P,"+typeRound+"");
        
       
    }


    @Override
    public  void deSerialize(String data) {
    String decereal=data.split(",")[1];
     if(decereal=="LIGHT_ROUND"){
         typeRound=TypeRound.LIGHT_ROUND;
     }
     if(decereal=="HEAVY_ROUND"){
         typeRound=TypeRound.HEAVY_ROUND;
     }
     if(decereal=="EXPLOSIVE_ROUND"){
        typeRound=TypeRound.EXPLOSIVE_ROUND;
    }
       
        

    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void collideEvent(Entity otherEntity) {
        // TODO Auto-generated method stub
        // otherEntity.setCurrentHealth();

    }

    @Override
    public void spawn() {
        // TODO Auto-generated method stub
        super.spawn();

    }

    @Override
    public void deSpawn() {
        // TODO Auto-generated method stub
        super.deSpawn();
    }

    
    //  Getters-Setters  //


    public TypeRound getTypeRound() {
        return typeRound;
    }

    public void setTypeRound(TypeRound typeRound) {
        this.typeRound = typeRound;
    }


	public static double getRoundTypeSpeed(TypeRound roundType) {
		switch(roundType){

            case LIGHT_ROUND:
                return lightRoundSpeed;
                
            case HEAVY_ROUND:
                return heavyRoundSpeed;
                
            case EXPLOSIVE_ROUND:
                return explosiveRoundSpeed;

            default:
                return -1;
        }
	}
}