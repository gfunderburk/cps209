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
    private static double lightRoundDmg = 1;
    private static double heavyRoundDmg = 2;
    private static double explosiveRoundDmg = 3;
    
    private TypeRound typeRound;    
    private boolean AvatarsProjectile;
    private double visualYoffset, visualXoffset;



    //  Constructor  //


    public static void makeProjectile(EntityHumanoid thisEntity, Entity thatEntity){

        E_Projectile bullet = new E_Projectile();
        if(thisEntity == EH_Avatar.getIt()) bullet.AvatarsProjectile = true;
        bullet.typeRound = thisEntity.getTypeRound();
        bullet.imageDir = File.separator + "projectiles" + File.separator;
        bullet.imageState = "bulletRed.png";
        bullet.width = 2.5;
        bullet.height = 2.5;
        bullet.calcOffsets(thisEntity);
        bullet.setLocation(thisEntity.getLocation());
        bullet.setVector(myMovement.getHeading(EH_Avatar.getIt().getLocation(), thisEntity.getLocation(), getRoundTypeSpeed(thisEntity.getTypeRound())));        
        bullet.spawn();
    }

    
    public static void makeProjectile(double targetX, double targetY){

        E_Projectile bullet = new E_Projectile();
        bullet.AvatarsProjectile = true;
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

    public void calcOffsets(Entity thisEntity){
        if(thisEntity instanceof EH_LightAI){
            EH_LightAI ai = (EH_LightAI)thisEntity;
            
            this.visualYoffset = 35;
            this.visualXoffset = -3;

        }else 
        if(thisEntity instanceof EH_HeavyAI){
            EH_HeavyAI ai = (EH_HeavyAI)thisEntity;

            this.visualYoffset = 44;
            this.visualXoffset = -2;

        }else 
        if(thisEntity instanceof EH_FlyingAI){
            EH_FlyingAI ai = (EH_FlyingAI)thisEntity;

            this.visualYoffset = 2;
            this.visualXoffset = 2;

        }else 
        if(thisEntity instanceof EH_BossAI){
            EH_BossAI ai = (EH_BossAI)thisEntity;

            this.visualYoffset = 68;
            this.visualXoffset = 2;

        }
    }

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
        this.deSpawn();
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

    public static double getLightRoundSpeed() {
        return lightRoundSpeed;
    }

    public static void setLightRoundSpeed(double lightRoundSpeed) {
        E_Projectile.lightRoundSpeed = lightRoundSpeed;
    }

    public static double getHeavyRoundSpeed() {
        return heavyRoundSpeed;
    }

    public static void setHeavyRoundSpeed(double heavyRoundSpeed) {
        E_Projectile.heavyRoundSpeed = heavyRoundSpeed;
    }

    public static double getExplosiveRoundSpeed() {
        return explosiveRoundSpeed;
    }

    public static void setExplosiveRoundSpeed(double explosiveRoundSpeed) {
        E_Projectile.explosiveRoundSpeed = explosiveRoundSpeed;
    }

    public static double getLightRoundDmg() {
        return lightRoundDmg;
    }

    public static void setLightRoundDmg(double lightRoundDmg) {
        E_Projectile.lightRoundDmg = lightRoundDmg;
    }

    public static double getHeavyRoundDmg() {
        return heavyRoundDmg;
    }

    public static void setHeavyRoundDmg(double heavyRoundDmg) {
        E_Projectile.heavyRoundDmg = heavyRoundDmg;
    }

    public static double getExplosiveRoundDmg() {
        return explosiveRoundDmg;
    }

    public static void setExplosiveRoundDmg(double explosiveRoundDmg) {
        E_Projectile.explosiveRoundDmg = explosiveRoundDmg;
    }

    public boolean isAvatarsProjectile() {
        return AvatarsProjectile;
    }

    public void setAvatarsProjectile(boolean avatarsProjectile) {
        AvatarsProjectile = avatarsProjectile;
    }

    public double getVisualYoffset() {
        return visualYoffset;
    }

    public void setVisualYoffset(int visualYoffset) {
        this.visualYoffset = visualYoffset;
    }

    public double getVisualXoffset() {
        return visualXoffset;
    }

    public void setVisualXoffset(int visualXoffset) {
        this.visualXoffset = visualXoffset;
    }
}