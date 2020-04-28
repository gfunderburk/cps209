/* --------------------------------------------------------------------------------------------- 
File:   .java
Desc.   
--------------------------------------------------------------------------------------------- */


package Util_model;

import java.util.Random;

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
        var ran = new Random();
        return ran.nextBoolean();
    }

    
    public static int genRandomPosNeg(){
        return genRandomBoolean() ? 1 : -1;
    }
}