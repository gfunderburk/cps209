package Util_model;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

//---------------------------------------------------------------------------------------------
// Class:  myMath
// Desc:  Discretely handles trigonometric calculations.
//--------------------------------------------------------------------------------------------- 
public class myGeometry {


    //      Get Angle        //


    public static double getAngle(Point2D sourcePt, Point2D targetPt) {
        double angle = (double) Math.toDegrees(Math.atan2(targetPt.getY() - sourcePt.getY(), targetPt.getX() - sourcePt.getX()));
    
        if(angle < 0){
            angle += 360;
        }
        else if(angle > 360){
            angle -= 360;
        }
    
        return angle;
    }

    public static double getAngle(double sourcePtX, double sourcePtY, double targetPtX, double targetPtY){
        Point2D targetPt = new Point2D((int)targetPtX, (int)targetPtY);
        Point2D sourcePt = new Point2D((int)sourcePtX, (int)sourcePtY);
        return getAngle(sourcePt, targetPt);
    }

    public static int getAngle(int sourcePtX, int sourcePtY, int targetPtX, int targetPtY){
        Point2D targetPt = new Point2D(targetPtX, targetPtY);
        Point2D sourcePt = new Point2D(sourcePtX, sourcePtY);
        return (int)getAngle(sourcePt, targetPt);
    }


    //      Get Distance 1D        //

    
    public static double getDistance1D(double sourcePt, double targetPt){
        return Math.abs(sourcePt - targetPt);
    }

    public static int getDistance1D(int sourcePt, int targetPt){
        return Math.abs(sourcePt - targetPt);
    }


    //      Get Distance 2D        //


    public static double getDistance2D(Point2D sourcePt, Point2D targetPt){

        var xSqr = (targetPt.getX()-sourcePt.getX())*(targetPt.getX()-sourcePt.getX());
        var ySqr = (targetPt.getY()-sourcePt.getY())*(targetPt.getY()-sourcePt.getY());
        var result = Math.sqrt( xSqr + ySqr );
        return Math.abs(result);
    }
    
    public static double getDistance2D(double sourcePtX, double sourcePtY, double targetPtX, double targetPtY){
        Point2D targetPt = new Point2D((int)targetPtX, (int)targetPtY);
        Point2D sourcePt = new Point2D((int)sourcePtX, (int)sourcePtY);
        return getDistance2D(sourcePt, targetPt);
    }
    
    public static int getDistance2D(int sourcePtX, int sourcePtY, int targetPtX, int targetPtY){
        Point2D targetPt = new Point2D(targetPtX, targetPtY);
        Point2D sourcePt = new Point2D(sourcePtX, sourcePtY);
        return (int)getDistance2D(sourcePt, targetPt);
    }


    //      Get Distance 3D        //


    public static double getDistance3D(Point3D sourcePt, Point3D targetPt){

        var xSqr = (targetPt.getX()-sourcePt.getX())*(targetPt.getX()-sourcePt.getX());
        var ySqr = (targetPt.getY()-sourcePt.getY())*(targetPt.getY()-sourcePt.getY());
        var ZSqr = (targetPt.getZ()-sourcePt.getZ())*(targetPt.getZ()-sourcePt.getZ());
        var result = Math.sqrt( xSqr + ySqr + ZSqr);
        return Math.abs(result);
    }
    
    public static double getDistance3D(double sourcePtX, double sourcePtY, double sourcePtZ, double targetPtX, double targetPtY, double targetPtZ){
        Point3D targetPt = new Point3D((int)targetPtX, (int)targetPtY, (int)targetPtZ);
        Point3D sourcePt = new Point3D((int)sourcePtX, (int)sourcePtY, (int)sourcePtZ);
        return getDistance3D(sourcePt, targetPt);
    }
    
    public static int getDistance3D(int sourcePtX, int sourcePtY, int sourcePtZ, int targetPtX, int targetPtY, int targetPtZ){
        Point3D targetPt = new Point3D(targetPtX, targetPtY, targetPtZ);
        Point3D sourcePt = new Point3D(sourcePtX, sourcePtY, sourcePtZ);
        return (int)getDistance3D(sourcePt, targetPt);
    }
}