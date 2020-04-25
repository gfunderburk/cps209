package Game_model;

import java.io.File;

import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public class E_Projectile extends Entity {


    //  Variables  //


    protected static double lazerRoundDmg = .0075;
    protected static double lazerRound_width = 1.5;
    protected static double lazerRoundMoveFactor = 1;
    protected static String lazerRound_FireImg = "round_lazer_fire.png";
    protected static String lazerRound_AIImg = "round_lazer.png";

    protected static double lightRoundDmg = 1;
    protected static double lightRound_width = 1;
    protected static double lightRoundMoveFactor = 1;
    protected static String lightRound_FireImg = "round_light_fire.png";
    protected static String lightRound_AIImg = "round_light_in.png";
    protected static String lightRound_PlayerImg = "round_light_out.png";

    protected static double heavyRoundDmg = 2;
    protected static double heavyRound_width = 2.5;
    protected static double heavyRoundMoveFactor = 2;
    protected static String heavyRound_FireImg = "round_heavy_fire.png";
    protected static String heavyRound_AIImg = "round_heavy_in.png";
    protected static String heavyRound_PlayerImg = "round_heavy_out.png";

    protected static double explosiveRoundDmg = 3;
    protected static double explosiveRound_width = 3;
    protected static double explosiveRoundMoveFactor = 3;
    protected static String explosiveRound_FireImg = "round_explosive_fire.png";
    protected static String explosiveRound_AIImg = "round_explosive_in.png";
    protected static String explosiveRound_PlayerImg = "round_explosive_out.png";

    protected static String projImageDir = File.separator + "projectiles" + File.separator;
    public static enum TypeRound {LIGHT_ROUND, HEAVY_ROUND, EXPLOSIVE_ROUND, LAZER_ROUND};
    
    private TypeRound typeRound;    
    private boolean AvatarsProjectile;
    private double visualYoffset, visualXoffset;
    private double moveFactor, damage;
    private boolean roundFired;



    //  Constructor  //


    public static void makeProjectile(EntityHumanoid thisEntity, Entity thatEntity){

        E_Projectile bullet = new E_Projectile();
        bullet.AvatarsProjectile = false;
        bullet.setTypeRound(thisEntity.getTypeRound());
        bullet.imageDir = projImageDir;
        bullet.stateIntFactor = 1;
        bullet.calcOffsets(thisEntity);
        bullet.setLocation(thisEntity.getLocation());

        Point3D target = new Point3D(0, 0, 0);        
        if(EH_Avatar.getIt().location.getX() > thisEntity.getLocation().getX()){
            target = myMovement.setNewPointComp(target, Point3D_Comp.x, thisEntity.getLocation().getX() + 1);
        }
        else {
            target = myMovement.setNewPointComp(target, Point3D_Comp.x, thisEntity.getLocation().getX() - 1);
        }
        bullet.setVector(myMovement.getHeading(target, thisEntity.getLocation(), getRoundTypeSpeed(thisEntity.getTypeRound())));        
        bullet.spawn();
    }

    
    public static void makeProjectile(double targetX, double targetY){

        E_Projectile bullet = new E_Projectile();
        bullet.AvatarsProjectile = true;
        bullet.setTypeRound(EH_Avatar.getIt().getTypeRound());
        bullet.imageDir = projImageDir;
        bullet.stateIntFactor = 1;
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
            this.visualXoffset = 0;

        }else 
        if(thisEntity instanceof EH_BossAI){
            EH_BossAI ai = (EH_BossAI)thisEntity;

            this.visualYoffset = 68;
            this.visualXoffset = 0;

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
        
        if(this.isAvatarsProjectile()){

            Entity otherEntity = Game.getIt().getEntityList()
                                .stream()
                                .filter(ent -> !(ent instanceof E_Projectile) )
                                .filter(ent -> (ent.location.getX() + ent.width * .5) > this.location.getX())
                                .filter(ent -> (ent.location.getX() - ent.width * .5) < this.location.getX())
                                .filter(ent -> (ent.location.getX() + ent.height * .5) > this.location.getY())
                                .filter(ent -> (ent.location.getX() - ent.height * .5) < this.location.getY())
                                .findAny()
                                .orElse(null);      // .collect(Collectors.toList()));
                                
            if(otherEntity != null){
                Game.getIt().setScore(Game.getIt().getScore() + 1);
                this.collideEvent(otherEntity);
                otherEntity.collideEvent(this);
            }
        }
        else if(this.location.getZ() <= 0){
            this.collideEvent(null);
            EH_Avatar.getIt().collideEvent(this);
        }
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

        switch(this.typeRound){
            case LIGHT_ROUND:
                this.moveFactor = lightRoundMoveFactor;
                this.damage = lightRoundDmg;

                if(this.isAvatarsProjectile()){
                    this.imageState = lightRound_FireImg;
                    this.width = lightRound_width;
                    this.height = lightRound_width;
                }else{
                    this.imageState = lightRound_FireImg;
                    this.width = lightRound_width * 5;
                    this.height = lightRound_width * 5;
                }
                break;

            case HEAVY_ROUND:
                this.moveFactor = heavyRoundMoveFactor;
                this.damage = heavyRoundDmg;

                if(this.isAvatarsProjectile()){
                    this.imageState = heavyRound_FireImg;
                    this.width = heavyRound_width;
                    this.height = heavyRound_width;
                }else{
                    this.imageState = heavyRound_FireImg;
                    this.width = heavyRound_width * 1.5;
                    this.height = heavyRound_width * 1.5;
                }
                break;

            case EXPLOSIVE_ROUND:
                this.moveFactor = explosiveRoundMoveFactor;
                this.damage = explosiveRoundDmg;

                if(this.isAvatarsProjectile()){
                    this.imageState = explosiveRound_FireImg;
                    this.width = explosiveRound_width;
                    this.height = explosiveRound_width;
                }else{
                    this.imageState = explosiveRound_FireImg;
                    this.width = explosiveRound_width * 1.5;
                    this.height = explosiveRound_width * 1.5;
                }
                break;
            
            case LAZER_ROUND:
                this.moveFactor = lazerRoundMoveFactor;
                this.damage = lazerRoundDmg;

                if(this.isAvatarsProjectile()){
                    this.imageState = lazerRound_FireImg;
                    this.width = lazerRound_width;
                    this.height = lazerRound_width;
                }else{
                    this.imageState = lazerRound_FireImg;
                    this.width = lazerRound_width * 1.5;
                    this.height = lazerRound_width * 1.5;
                }
                break;
            
            default:
        }
    }


	public static double getRoundTypeSpeed(TypeRound roundType) {
		switch(roundType){

            case LIGHT_ROUND:
                return lightRoundMoveFactor;
                
            case HEAVY_ROUND:
                return heavyRoundMoveFactor;
                
            case EXPLOSIVE_ROUND:
                return explosiveRoundMoveFactor;
            
            case LAZER_ROUND:
                return lazerRoundMoveFactor;

            default:
                return -1;
        }
	}

    public static double getLightRoundSpeed() {
        return lightRoundMoveFactor;
    }

    public static void setLightRoundSpeed(double lightRoundSpeed) {
        E_Projectile.lightRoundMoveFactor = lightRoundSpeed;
    }

    public static double getHeavyRoundSpeed() {
        return heavyRoundMoveFactor;
    }

    public static void setHeavyRoundSpeed(double heavyRoundSpeed) {
        E_Projectile.heavyRoundMoveFactor = heavyRoundSpeed;
    }

    public static double getExplosiveRoundSpeed() {
        return explosiveRoundMoveFactor;
    }

    public static void setExplosiveRoundSpeed(double explosiveRoundSpeed) {
        E_Projectile.explosiveRoundMoveFactor = explosiveRoundSpeed;
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

    @Override
    protected void subStateUpdate() {
        if(this.subStateInt >= this.moveFactor){
            this.subStateInt = 0;
            this.move();

            if(!this.roundFired & !this.isAvatarsProjectile()){
                this.roundFired = true;

                switch(this.typeRound){
                    case LIGHT_ROUND:
                        this.imageState = lightRound_AIImg;
                        this.width = lightRound_width;
                        this.height = lightRound_width;
                        break;
        
                    case HEAVY_ROUND:
                        this.imageState = heavyRound_AIImg;
                        this.width = heavyRound_width;
                        this.height = heavyRound_width;
                        break;
        
                    case EXPLOSIVE_ROUND:
                        this.imageState = explosiveRound_AIImg;
                        this.width = explosiveRound_width;
                        this.height = explosiveRound_width;
                        break;
                    
                    case LAZER_ROUND:
                        this.imageState = lazerRound_AIImg;
                        this.width = lazerRound_width;
                        this.height = lazerRound_width;
                        break;
                    
                    default:
                }
            }
        }
    }
}