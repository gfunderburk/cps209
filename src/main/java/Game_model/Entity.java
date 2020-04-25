package Game_model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public abstract class Entity implements GameSave{


    //  Variables  //

    protected static double LaiH = 50;
    protected static double LaiW = 10; 
    protected static double HaiH = 55;
    protected static double HaiW = 12; 
    protected static double FaiH = 7;
    protected static double FaiW = 7; 
    protected static double BaiH = 80;
    protected static double BaiW = 20; 
    protected  int Id, stateInt, stateIntFactor, subStateInt; 
    protected  double height, width, speed;
    protected  int sameMoveCount;
    protected  boolean standStill = false;
    protected  Point3D location, vector;
    protected  String imageDir, imageState;
    protected  int collisionCode; 
    /*
    0 =  none, 
    1 =  <X, 
    2 =  >X, 
    3 =  <Y, 
    4 =  >Y, 
    5 =  <Z, 
    6 =  >Z
    */ 


    //  Methods  //

    public void stateIncrement(){
        this.stateInt++;
        if(this.stateInt == stateIntFactor){
            stateInt = 0;
            subStateInt++;
            subStateUpdate();
        }  
    };

    protected abstract void subStateUpdate();

    protected void move() {
        
        this.location = this.location.add(this.vector);
        // this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.z, 0);

        // Check X within borders
        if(this.location.getX() > Game.getIt().getGamePhysicsWidth()){
            outOfBoundsEvent(2);
        }
        else if(this.location.getX() < 0){
            outOfBoundsEvent(1);
        }

        // Check Y within borders
        if(this.location.getY() > Game.getIt().getGamePhysicsHeight()){
            outOfBoundsEvent(4);
        }
        else if(this.location.getY() < 0){
            outOfBoundsEvent(3);
        }

        // Check Z within borders
        if(this.location.getZ() > Game.getIt().getGamePhysicsDepth()){
            outOfBoundsEvent(6);
        }
        else if(this.location.getZ() < 0){
            outOfBoundsEvent(5);
        }
    };


    public abstract void collideEvent(Entity collidedEntity);


    public void outOfBoundsEvent(int boundsCode){
        if(this instanceof E_Projectile){
            this.deSpawn();  
        } 
        else{

            switch(boundsCode){

                case 1:
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.x, 0); 
                    break;

                case 2:   
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.x, Game.getIt().getGamePhysicsWidth());
                    break;

                case 3:
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.y, 0);
                    break;

                case 4:
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.y, Game.getIt().getGamePhysicsHeight());
                    break;

                case 5:
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.z, 0);
                    break;

                case 6:
                    this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.z, Game.getIt().getGamePhysicsDepth());
                    break;

                default:
            }
        }
    }

    
    public void spawn() {
        Game.getIt().setCurrentEnitity(Game.getIt().getCurrentEnitity() + 1);
        this.Id = Game.getIt().getCurrentEnitity();
        Game.getIt().getEntityList().add(this);
    }

    public void deSpawn(){
        Game.getIt().getDeadEntityList().add(this);
        var index = Game.getIt().getEntityList().indexOf(this);
        if(index != -1) {
            Game.getIt().getEntityList().remove(index);
        }
        Game.getIt().spawnerAdmin(false);
    };

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);


    //  Getter - Setters  //


    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point3D getLocation() {
        return location;
    }

    public void setLocation(Point3D location) {
        this.location = location;
    }

    public Point3D getVector() {
        return vector;
    }

    public void setVector(Point3D vector) {
        this.vector = vector;
    }

    public String getImageDir() {
        return imageDir;
    }

    public String getImageState() {
        return imageState;
    }

    public void setImageState(String image) {
        this.imageState = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImage(){
        return this.imageDir + this.imageState;
    }

    public static double getLaiH() {
        return LaiH;
    }

    public static void setLaiH(double laiH) {
        LaiH = laiH;
    }

    public static double getLaiW() {
        return LaiW;
    }

    public static void setLaiW(double laiW) {
        LaiW = laiW;
    }

    public static double getHaiH() {
        return HaiH;
    }

    public static void setHaiH(double haiH) {
        HaiH = haiH;
    }

    public static double getHaiW() {
        return HaiW;
    }

    public static void setHaiW(double haiW) {
        HaiW = haiW;
    }

    public static double getFaiH() {
        return FaiH;
    }

    public static void setFaiH(double faiH) {
        FaiH = faiH;
    }

    public static double getFaiW() {
        return FaiW;
    }

    public static void setFaiW(double faiW) {
        FaiW = faiW;
    }

    public static double getBaiH() {
        return BaiH;
    }

    public static void setBaiH(double baiH) {
        BaiH = baiH;
    }

    public static double getBaiW() {
        return BaiW;
    }

    public static void setBaiW(double baiW) {
        BaiW = baiW;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getSameMoveCount() {
        return sameMoveCount;
    }

    public void setSameMoveCount(int sameMoveCount) {
        this.sameMoveCount = sameMoveCount;
    }

    public boolean isStandStill() {
        return standStill;
    }

    public void setStandStill(boolean standStill) {
        this.standStill = standStill;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public int getCollisionCode() {
        return collisionCode;
    }

    public void setCollisionCode(int collisionCode) {
        this.collisionCode = collisionCode;
    }
}
