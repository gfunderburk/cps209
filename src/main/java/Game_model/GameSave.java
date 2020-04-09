package Game_model;

public interface GameSave {
    String Serialize();
    void deSerialize(String data);
}