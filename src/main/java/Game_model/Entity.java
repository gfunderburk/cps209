/* --------------------------------------------------------------------------------------------- 
File:   Entity.java
Desc.   This class is the foundatinal Abstract class that sets up the physical world 
        (vs. the visual world). This physical world is 3D, and its entity objects 
        inheret their core physical specs in this class.  
        This class sets up, movement, collision detection, boundary detection, and physical sizes.

Note:   This class is equally foundational to the in-game state as the Game class.
--------------------------------------------------------------------------------------------- */


package Game_model;

import java.io.File;
import Util_model.myMovement;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;

public abstract class Entity implements GameSave{


    //  Variables  //


    protected static double LaiH    = 50;       //    the given physical Height and Width dimensions of the entities of the in-game
    protected static double LaiW    = 10;       //  L = LightAI
    protected static double HaiH    = 55;
    protected static double HaiW    = 12;       //  H = HeaveyAI
    protected static double FaiH    = 6;
    protected static double FaiW    = 6;        //  F = FlyingAI
    protected static double BaiH    = 80;
    protected static double BaiW    = 20;       //  B = BossAI
    protected static double AmmoH   = 6; 
    protected static double AmmoW   = 8;        //  Ammo Power-up
    protected static double HealthH = 6; 
    protected static double HealthW = 8;        //  Health Power-up
    protected static double PointsH = 8; 
    protected static double PointsW = 6;        //  Point Power-up
    protected  int Id;                          //  the ID number of the given entity
    protected  int stateInt;                    //  the value that gets incremented by the timerAnimate method in the View
    protected  int subStateInt;                 //  the value that triggers entity actions during a given entity stateAction
    protected  int stateIntFactor;              //  the value that sets how many stateInt ticks must occur before subStateInt increments
    protected  Point3D location;                //  the physical location of the given entity
    protected  Point3D vector;                  //  the physical velocity vector of a given entity's movement
    protected  Image imageState;                //  the image of a given entity's stateAction
    protected  double height, width, speed;     //  the height, width and speed values of a given entity
    protected  int collisionCode;               //  the code fed to outOfBoundsEvent(). this triggers the appropriate response 
    /*                                          //      for an entity who travels out of a given boundary
    0 =  none, 
    1 =  <X, 
    2 =  >X, 
    3 =  <Y, 
    4 =  >Y, 
    5 =  <Z, 
    6 =  >Z
    */ 


    //  Methods  //


    /** 
     * is called by the View's timerAnimate
     *  this method is to allow for projectiles and some entities to move and act more quickly than other entities
     *  if a given entity's stateInt equal the entity's stateIntFactor, then its subStateInt will increment.
     *  finally, checks if the newly incremented subStateInt value should trigger any actions by calling subStateUpdate()
     */
    public void stateIncrement(){
        this.stateInt++;
        if(this.stateInt == stateIntFactor){
            stateInt = 0;
            subStateInt++;
            subStateUpdate();
        }  
    };

    
    /** 
     * @param imageDir
     * @param imageState
     * @return String
     */
    public static String initChildImage(String imageDir, String imageState){
        String vsDir = System.getProperty("user.dir");
        String recourcesDir = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"icons";
        var it = new File(vsDir + recourcesDir + imageDir + imageState).toURI().toString();
        return it;
    }

    
    /** 
     * children inheret this method. Triggers whenever this given entity collides with another entity
     */
    public abstract void collideEvent(Entity collidedEntity);

    
    /** 
     * children inheret this method. Checks the current subStateInt value of a given entity
     *  and triggers actions accordingly
     */
    protected abstract void subStateUpdate();


    /** 
     * moves a given entity forwards based off of its velocity vector
     *  and checks if the entity is out of bounds
     *      IF is outOfBounds, calls the outOfBoundsMethod
     */
    protected void move() {
        
        this.location = this.location.add(this.vector);

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


    /** 
     * @param boundsCode the code sent from move()
     * IF entity is a projectile, the entity is merely deSpawned straightaway
     * ELSE the entity is relocated to remain within the physical boundaries of the in-game
     */
    public void outOfBoundsEvent(int boundsCode){
        if(this instanceof E_Projectile)
        {
            this.deSpawn();  
        } 
        else
        {
            switch(boundsCode)
            {
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
                    default:
            }
        }
    }

    
    /** 
     * increments the entity ID value, currentEntity,
     * applies this new currentEntity ID value to the newly created entity,
     * and add this entity to the living entityList
     */
    public void spawn() {
        Game.getIt().setCurrentEnitity(Game.getIt().getCurrentEnitity() + 1);
        this.Id = Game.getIt().getCurrentEnitity();
        Game.getIt().getEntityList().add(this);
    }


    /** 
     * removes the given entity from the living entityList 
     *      and moves the entity to the deadEntityList to have its imageView removed from the View's pane
     */
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

    public Image getImageState() {
        return imageState;
    }

    public void setImageState(Image image) {
        this.imageState = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Image getImage(){
        return this.imageState;
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

    public int getCollisionCode() {
        return collisionCode;
    }

    public void setCollisionCode(int collisionCode) {
        this.collisionCode = collisionCode;
    }
}
