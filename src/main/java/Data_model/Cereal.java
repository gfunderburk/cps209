package Data_model;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Game_model.*;

public class Cereal {

    // Variables //

    Game game;
    String name;
    LocalDateTime dt;
    
    // Constructor //

    public Cereal(Game game, LocalDateTime dt, String name) {
        this.game = game;
        this.name = name;
        this.dt = java.time.LocalDateTime.now();
    }

    
    // Methods //

    public String toString() {
        return this.dt + "_" + this.name + ".dat";
    }

    public void SerializeGame() throws FileNotFoundException
    {
       
        ArrayList<Entity> cerealArray= Game.getIt().getEntityList();
        

        try(DataOutputStream writer=new DataOutputStream(new FileOutputStream("cereal.dat"))){

            for(Entity ent:cerealArray){
                writer.writeUTF(ent.Serialize()+"\n");
            }
                

        }catch(IOException e){
            
                System.out.println("An Error has Occured During Serialization    (SerializeGame() Line:36-)");
        }
        
        
    }


	public void deSerialize(String line) {
    
        String[] attrList = line.split(",");
        switch(attrList[0]){

            case "G":
                game.deSerialize(line);
                break;
                
            case "U":
             
                EH_Avatar.DeSerialize(line);
                break;

            case "H":
                EH_LightAI dude=new EH_LightAI();
                dude.deSerialize(line);
                dude.spawn();
                break;

            case "S":
                
                break;
            
            case "P":
               E_Projectile bull=new E_Projectile();
              bull.deSerialize(line);
              bull.spawn();
                break;
        }
	};


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

}
