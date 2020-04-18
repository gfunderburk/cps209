package Game_model;

import Util_model.myMovement;
import javafx.geometry.Point3D;

public abstract class Entity implements GameSave{


    //  Variables  //

    
    protected  int Id; 
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

    
    public void move() {
        
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

        for (Entity otherEntity : Game.getIt().getEntityList()){            
            if(this != otherEntity){
                double dis = Util_model.myGeometry.getDistance3D(this.location, otherEntity.location);
                
                if(dis < 1) {
                    this.collideEvent(otherEntity);
                    //otherEntity.collideEvent(this);
                }
            }
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
        Game.getIt().getEntityList().remove(this);
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
        return imageDir + imageState;
    }
}
