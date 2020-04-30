/* --------------------------------------------------------------------------------------------- 
File:   EntityKillable.java
Desc.   This class is the second-tiered Abstract class for the physical world. 
        Any destructable entity in the physical world will inheret their health related 
        attributes and methods in this class.
        This class admins current health status and health related events. 
--------------------------------------------------------------------------------------------- */


package Game_model;

public abstract class EntityKillable extends Entity {


    //  Variables  //

    
    protected static enum StateLife {HEALTHY, HURT, DEAD}
    protected StateLife stateLife;                          //  the current state of the given entity        
    protected double maxHealth, currentHealth;              //  the maximum and current health value of the given entity


    //  Methods  //


    /** 
     * checks and updates a given entity's current life state 
     *     and triggers respective events of a given entity.
     */
    public void checkLife() {

        if(this.stateLife != StateLife.DEAD)
        {
            if(this.currentHealth > (this.maxHealth / 2))
            {
                if(this.stateLife != StateLife.HEALTHY)
                {
                    this.stateLife = StateLife.HEALTHY;
                    this.recoverEvent();
                }
            }

            else if(this.currentHealth != 0 &
                    this.currentHealth <= (this.maxHealth / 2))
            {
                if(this.stateLife != StateLife.HURT)
                {
                    this.stateLife = StateLife.HURT;
                    this.hurtEvent();
                }
            }

            else
            {
                this.stateLife = StateLife.DEAD;
                this.deathEvent();                
            }
        }
    };
    
    public abstract void hurtEvent();
    
    public abstract void recoverEvent();

    public abstract void deathEvent();

    @Override
    public abstract String Serialize();

    @Override
    public abstract void deSerialize(String data);


    //  Getter - Setters  //


    public double getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public StateLife getStateLife() {
        return this.stateLife;
    }

    public void setStateLife(StateLife stateLife) {
        this.stateLife = stateLife;
    }
}