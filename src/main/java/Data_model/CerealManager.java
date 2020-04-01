package Data_model;

import java.util.ArrayList;
import java.util.Collections;

public class CerealManager {


    //  Variables  //


    static private ArrayList<Cereal> cerealList = new ArrayList<Cereal>();   // list of game saves
    
    
    //  Singleton  //


    private CerealManager() {
    }

    static private CerealManager It = new CerealManager();


    //  Methods  //



    static void loadCereal(){
    }

    static void saveCereal(){

    }

    static void addCereal(Cereal newCereal){
        cerealList.add(newCereal);
    }

    private void deleteCereal(Cereal cereal){
        cerealList.remove(cereal);
    }
    
    private void sortCereal(){
        cerealList.sort((o1, o2) -> o1.dt.compareTo(o2.dt));
    } 


    //  Getters-Setters  //


    public ArrayList<Cereal> getCerealList() {
        return cerealList;
    }

    public void setCerealList(ArrayList<Cereal> cerealList) {
        this.cerealList = cerealList;
    }

    public static CerealManager getIt() {
        return It;
    }
}