package Util_model;

import javafx.geometry.Point3D;

public class myMovement {

    //      Move Point in 3D Space        //

    // public static T Max<T>(T a, T b) where T : IComparable<T> { 
    //     return a.CompareTo(b) > 0 ? a : b; 
    // } 



    public static Point3D getHeading(Point3D targetPoint, Point3D sourcePoint, double sourceSpeed){
        Point3D pt = new Point3D(targetPoint.getX()-sourcePoint.getX(), 
                                          targetPoint.getY()-sourcePoint.getY(), 
                                          targetPoint.getZ()-sourcePoint.getZ());

        double r = Math.sqrt( square(pt.getX()) + square(pt.getY()) + square(pt.getZ()) );
        double theta = Math.acos( pt.getZ() / r );
        double phi = Math.atan2(pt.getY(), pt.getX());
            

        double x = sourceSpeed * Math.sin(theta) * Math.cos(phi);
        double y = sourceSpeed * Math.sin(theta) * Math.sin(phi);
        double z = sourceSpeed * Math.cos(theta);

        return new Point3D(x, y, z);
        
        // x = r cos(Φ) sin(θ)
        // y = r sin(Φ) sin(θ)
        // z = r cos(θ)
        
        // r = √(x2+y2+z2)
        // θ = atan2(y, x)
        // Φ = acos(z/r)
    } 

    public static double square(double value){
        return value * value;
    }


    public static enum Point3D_Comp{x, y, z};
    public static Point3D setNewPointComp(Point3D point, Point3D_Comp component, double value){
        switch(component){

            case x:
                point = new Point3D(value, point.getY(), point.getZ());
                break;

            case y:
                point = new Point3D(point.getX(), value, point.getZ());
                break;

            case z:
                point = new Point3D(point.getX(), point.getY(), value); 
                break;
        }
        return point;
    }
    

}