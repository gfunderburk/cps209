package Data_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Game_model.GameSave;

public class CerealManager {


    //  Variables  //


    static private ArrayList<Cereal> cerealList = new ArrayList<Cereal>();   // list of game save file names in dir
    
    
    //  Singleton  //


    private CerealManager() {}

    static private CerealManager It = new CerealManager();


    //  Methods  //


    public static void loadCerealDir(){

        //  Go to save files' directory
        String x = System.getProperty("user.dir") + File.pathSeparator + "SavedData";
        System.out.println(x);

        File folder = new File(x);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {

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


    public static void loadCerealFile(Cereal loadCereal){

        try(BufferedReader rd = new BufferedReader( new FileReader(loadCereal.toString()))) {
                       
            String line = rd.readLine();
            while (line != null) { 

                loadCereal.deSerialize(line);
                System.out.println(line); 
                line = rd.readLine(); 
            } 
            rd.close(); 
            //TODO: Game.loadCereal(loadCereal);
        } 
        catch (IOException e) { 
            System.out.println("Problem loading " + loadCereal.toString()); 
        }
    }

    
    public static void saveCerealFile(Cereal gameCereal){
        try(var wr = new PrintWriter( new FileWriter(gameCereal.toString())); ) { 
            wr.println(gameCereal.SerializeGame()); 
            wr.close(); 
        } 
        catch (IOException e) {
            System.out.println("Problem saving " + gameCereal.toString()); 
        }
    }


    public static void addCerealFile(Cereal newCereal){
        cerealList.add(newCereal);
    }


    public static void deleteCereal(Cereal cereal){
        cerealList.remove(cereal);
    }
    

    public static void sortList(){
        cerealList.sort((o1, o2) -> o1.dt.compareTo(o2.dt));
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


    public static ArrayList<Cereal> getList() {
        return cerealList;
    }

    public static void setList(ArrayList<Cereal> testList) {
        CerealManager.cerealList = testList;
    }

    public static CerealManager getIt() {
        return It;
    }
}