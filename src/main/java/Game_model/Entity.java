package Game_model;

import Util_model.myMovement;
import Util_model.myMovement.Point3D_Comp;
import javafx.geometry.Point3D;

public abstract class Entity implements GameSave{


    //  Variables  //

    
    protected  int height, width, Id; 
    protected  double speed;
    protected  int sameMoveCount;
    protected  boolean standStill = true;
    protected  Point3D location, vector;
    protected  String imageDir, imageState;


    //  Methods  //

    
    public void move() {
        this.sameMoveCount++;

        if(this.sameMoveCount > 20){
            this.sameMoveCount = 0;

            if(this.standStill){
                this.vector = new Point3D(0,0,0);
            }
            else{
                Point3D newDest = Game.getIt().randomPoint3D();
                this.vector = myMovement.getHeading(newDest, this.location, this.speed);
                this.vector = myMovement.setNewPointComp(this.vector, Point3D_Comp.y, 0);
            }

            this.standStill = this.standStill ? false : true;
        }

        this.location = this.location.add(this.vector);

        // Check X within borders
        if(this.location.getX() > Game.getIt().getGamePhysicsWidth()){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.x, Game.getIt().getGamePhysicsWidth());
        }
        else if(this.location.getX() < 0){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.x, 0);
        }

        // Check X within borders
        if(this.location.getY() > Game.getIt().getGamePhysicsHeight()){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.y, Game.getIt().getGamePhysicsHeight());
        }
        else if(this.location.getY() < 0){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.y, 0);
        }

        // Check X within borders
        if(this.location.getZ() > Game.getIt().getGamePhysicsDepth()){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.z, Game.getIt().getGamePhysicsDepth());
        }
        else if(this.location.getZ() < 0){
            this.location = myMovement.setNewPointComp(this.location, myMovement.Point3D_Comp.z, 0);
        }



        for (Entity item : Game.getIt().getEntityList()) 
        {            
            compareDist(item);
        }
    };
    

    public void compareDist(Entity otherOne) {

        if(this != otherOne){
            double dis = Util_model.myGeometry.getDistance3D(this.location, otherOne.location);
            
            if(dis < 1) { //TODO: compareDist might be buggy!
                this.collideEvent();
                otherOne.collideEvent();
            }
        }
    };
    
    public void spawn() {
        Game.getIt().setCurrentEnitity(Game.getIt().getCurrentEnitity() + 1);
        this.Id = Game.getIt().getCurrentEnitity();

        this.location = Game.getIt().randomPoint3D();
        this.location = myMovement.setNewPointComp(this.location, Point3D_Comp.y, 0);

        this.vector = myMovement.getHeading(Game.getIt().randomPoint3D(), this.location, this.speed);

        Game.getIt().getEntityList().add(this);
    }

    public abstract void collideEvent();

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);


    //  Getter - Setters  //


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
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
