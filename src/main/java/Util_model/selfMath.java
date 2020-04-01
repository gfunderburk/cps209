package Util_model;
import java.awt.Point;

//---------------------------------------------------------------------------------------------
// Class:  myMath
// Desc:  Discretely handles trigonometric calculations.
//--------------------------------------------------------------------------------------------- 
class selfMath {


    //      Get Angle        //


    public static double getAngle(Point sourcePt, Point targetPt) {
        double angle = (double) Math.toDegrees(Math.atan2(targetPt.y - sourcePt.y, targetPt.x - sourcePt.x));
    
        if(angle < 0){
            angle += 360;
        }
    
        return angle;
    }

    public static double getAngle(double sourcePtX, double sourcePtY, double targetPtX, double targetPtY){
        Point targetPt = new Point((int)targetPtX, (int)targetPtY);
        Point sourcePt = new Point((int)sourcePtX, (int)sourcePtY);
        return getAngle(sourcePt, targetPt);
    }

    public static int getAngle(int sourcePtX, int sourcePtY, int targetPtX, int targetPtY){
        Point targetPt = new Point(targetPtX, targetPtY);
        Point sourcePt = new Point(sourcePtX, sourcePtY);
        return (int)getAngle(sourcePt, targetPt);
    }


    //      Get Distance        //


    public static double getDistance(Point sourcePt, Point targetPt){

        var xSqr = (targetPt.getX()-sourcePt.getX())*(targetPt.getX()-sourcePt.getX());
        var ySqr = (targetPt.getY()-sourcePt.getY())*(targetPt.getY()-sourcePt.getY());
        var result = Math.sqrt( xSqr + ySqr );
        return Math.abs(result);
    }
    
    public static double getDistance(double sourcePtX, double sourcePtY, double targetPtX, double targetPtY){
        Point targetPt = new Point((int)targetPtX, (int)targetPtY);
        Point sourcePt = new Point((int)sourcePtX, (int)sourcePtY);
        return getDistance(sourcePt, targetPt);
    }
    
    public static int getDistance(int sourcePtX, int sourcePtY, int targetPtX, int targetPtY){
        Point targetPt = new Point(targetPtX, targetPtY);
        Point sourcePt = new Point(sourcePtX, sourcePtY);
        return (int)getDistance(sourcePt, targetPt);
    }
}