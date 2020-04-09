package Game_model;

import Util_model.myMovement;
import Util_model.myRandom;
import javafx.geometry.Point3D;

public abstract class Entity implements GameSave{


    //  Variables  //

    
    int height, imgHeight, width, imgWidth;
    Point3D location;
    Point3D vector;
    String image;


    //  Methods  //

    
    public void move() {
        this.location = this.location.add(vector);

        for (Entity item : Game.getIt().entityList) 
        {            
            compareDist(item);
        }
    };
    

    public void compareDist(Entity otherOne) {
        double dis = Util_model.myGeometry.getDistance3D(this.location, otherOne.location);
        
        if(dis < 1) { //TODO: compareDist might be buggy!
            this.collideEvent();
            otherOne.collideEvent();
        }
    };
    

    public abstract void collideEvent();

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);
}
