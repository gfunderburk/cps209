package Game_model;

public abstract class Entity implements GameSave{


    //  Variables  //

    
    int x, y, z, height, imgHeight, width, imgWidth, heading;
    String image;


    //  Methods  //

    
    public void move() {
        // TODO Auto-generated method stub
    };
    
    public void compareDist(Entity otherOne) {
        int dis = Util_model.myGeometry.getDistance3D(this.x, this.y, this.z, otherOne.x, otherOne.y, otherOne.z);
    
        if(dis == 0) {
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
