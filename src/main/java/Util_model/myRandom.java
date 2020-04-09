package Util_model;


//---------------------------------------------------------------------------------------
// Class:  selfRandom
// Desc:  This class streamlines use of Math.Random features,   
//        It randomly generates and returns int, double, boolean, and positive/negative.
//--------------------------------------------------------------------------------------- 
public class myRandom {

    public static int genRandomInt(int min, int max){
        
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min; 
        return rand;
    }

    
    public static double genRandomDouble(double min, double max){
        
        double range = max - min + 1;
        double rand = (Math.random() * range) + min; 
        return rand;
    }


    public static boolean genRandomBoolean(){

        int range = 1000 - 1 + 1;
        int rand = (int)(Math.random() * range) + 1; 

        if(rand%2 == 0){
            return true;
        }
        else{
            return false;
        }
    }

    
    public static int genRandomPosNeg(){

        int range = 1000 - 1 + 1;
        int rand = (int)(Math.random() * range) + 1; 

        if(rand%2 == 0){
            return 1;
        }
        else{
            return -1;
        }
    }
}