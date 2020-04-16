package Data_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Game_model.*;

public class CerealManager {


    //  Variables  //


    private ArrayList<Cereal> cerealList = new ArrayList<Cereal>();   // list of game save file names in dir
    
    
    //  Singleton  //


    private CerealManager() {}

    private static CerealManager It = new CerealManager();


    //  Methods  //


    public void loadCerealDir(){

        //  Go to save files' directory
        String x = System.getProperty("user.dir") + File.pathSeparator + "SavedData";
        System.out.println(x);

        File folder = new File(x);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) 
        {
            if (file.isFile()) 
            {
                // Check IF file ext is .dat
                String[] item = file.getName().split(".");
                if(item[item.length - 1].equals(".dat")){

                    //  split file title for cereal obj data
                    String[] itemName = item[0].split("_");
                    cerealList.add(
                        new Cereal( null, 
                                    LocalDateTime.parse(itemName[0]), 
                                    itemName[1])
                    );
                }
            }
        }
    }


    public void loadCerealFile(int cerealIndex){

        var loadCereal = cerealList.get(cerealIndex);
        Game.getIt().loadGame();

        try(BufferedReader rd = new BufferedReader( new FileReader(loadCereal.toString())))
        {                       
            String line = rd.readLine();
            while (line != null) { 

                Game.getIt().deSerialize(line);
                System.out.println(line); 
                line = rd.readLine(); 
            } 
            rd.close(); 
            Game.getIt().play();
        } 
        catch (IOException e) 
        { 
            System.out.println("Problem loading " + loadCereal.toString()); 
        }
    }

    

    
    public void saveCerealFile(Game gameSession){
        try(var wr = new PrintWriter( new FileWriter(gameSession.toString())); ) 
        { 
            wr.println(gameSession.Serialize());   

            for (Entity item : gameSession.getEntityList()) 
            {
                wr.println(item.Serialize());
            }            
            wr.close(); 
        } 
        catch(IOException e) 
        {
            System.out.println("Problem saving " + gameSession.toString()); 
        }
    }


    public void addCerealFile(Cereal newCereal){
        cerealList.add(newCereal);
        sortList();
    }


    public void deleteCereal(Cereal cereal){
        cerealList.remove(cereal);
    }
    

    public void sortList(){
        Comparator<Cereal> compareByDT = (Cereal o1, Cereal o2) -> o1.dt.compareTo( o2.dt ); 
        Collections.sort(cerealList, compareByDT.reversed());
    } 

    //  ---  //
    
    public String toString()
    {
        String result = "";
        for (Cereal cereal : cerealList) {
            result += " ____" + cereal.toString();
        }
        return result;        
    }


    //  Getters-Setters  //


    public ArrayList<Cereal> getList() {
        return CerealManager.getIt().getList();
    }

    public void setList(ArrayList<Cereal> testList) {
        cerealList = testList;
    }

    public static CerealManager getIt() {
        return It;
    }
}