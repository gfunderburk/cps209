package Data_model;

import java.util.ArrayList;

public class CerealManager {


    //  Variables  //


    static private ArrayList<Cereal> cerealList = new ArrayList<Cereal>();   // list of game save file names in dir
    
    
    //  Singleton  //


    private CerealManager() {
    }

    static private CerealManager It = new CerealManager();


    //  Methods  //



    public static void loadCerealDir(){
        //TODO: read all filenames found in save dir db into cerealList.
    }

    public static void loadCerealFile(String fileName){
        //TODO: deserialize input filename IF found in dir.
    }

    public static void saveCerealFile(String userName){
        //TODO: fetch local DT and serialize game session to Local_DT, username.dat.
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
    
    public static String Serialize()
    {
        String result = "";
        for (Cereal cereal : cerealList) {
            result += " ____" + cereal;
        }
        return result;
    }

    public String toString()
    {
        return CerealManager.Serialize();
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