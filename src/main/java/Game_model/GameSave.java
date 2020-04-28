/* --------------------------------------------------------------------------------------------- 
File:   GameSave.java
Desc.   Every Class within the Game_Model should inheret this interface.
        This interface ensures that every in-game component can be saved and loaded.
--------------------------------------------------------------------------------------------- */


package Game_model;

public interface GameSave {
    String Serialize();
    void deSerialize(String data);
}