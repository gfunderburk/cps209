/* --------------------------------------------------------------------------------------------- 
File:   .java
Desc.   
--------------------------------------------------------------------------------------------- */


package Util_model;

import javafx.geometry.Point3D;

public class myMovement {

    //      Move Point in 3D Space        //

    public static Point3D getHeading(double targetX, double targetY, double sourceX, double sourceY, double movementSpeed){

        //  Get offset from sourcePoint to targetPoint
        double rawXoffset = targetX - sourceX;
        double rawYoffset = targetY - sourceY;

        //  Get the unit circle coords
        double xyAngle = Math.toDegrees(Math.atan(rawYoffset/rawXoffset));
        boolean Xpos = rawXoffset > 0;
        boolean Ypos = rawYoffset > 0;

        if(!Xpos & Ypos){
            xyAngle += 180;
        }
        else if(!Xpos & !Ypos ){
            xyAngle += 180;
        }
        else if(Xpos & !Ypos){
            xyAngle += 360;
        }

        double unitX = Math.cos(Math.toRadians(xyAngle));
        double unitY = Math.sin(Math.toRadians(xyAngle));
        double unitZ = 1.0;

        //  Get phi + theta from unit circle coords
        double r = Math.sqrt( square(unitX) + square(unitY) + square(unitZ) );
        double phi = Math.acos(1/r);
        double theta = xyAngle;

        //  Translate phi + theta to (x,y,z) coords
        double X = movementSpeed * Math.sin(phi) * Math.cos(Math.toRadians(theta));
        double Y = movementSpeed * Math.sin(phi) * Math.sin(Math.toRadians(theta));
        double Z = movementSpeed * Math.cos(phi);
        return new Point3D(X, Y, Z);

        // r = √(x2+y2+z2)
        // θ = atan2(y, x)
        // Φ = acos(z/r)

        // x = r sinϕ cosθ
        // y = r sinϕ sinθ
        // z = r cosϕ
    }

    public static Point3D getHeading(Point3D targetPoint, Point3D sourcePoint, double movementSpeed){

        //  Get offset from sourcePoint to targetPoint
        Point3D pt = new Point3D(targetPoint.getX()-sourcePoint.getX(), 
                                targetPoint.getY()-sourcePoint.getY(), 
                                targetPoint.getZ()-sourcePoint.getZ());

        //  Get phi + theta from new point
        double r = Math.sqrt( square(pt.getX()) + square(pt.getY()) + square(pt.getZ()) );
        double phi = Math.acos( pt.getZ() / r );
        double theta = Math.atan2(pt.getY(), pt.getX());
            
        //  Translate phi + theta to (x,y,z) coords
        double X = movementSpeed * Math.sin(phi) * Math.cos(theta);
        double Y = movementSpeed * Math.sin(phi) * Math.sin(theta);
        double Z = movementSpeed * Math.cos(phi);
        return new Point3D(X, Y, Z);
        
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