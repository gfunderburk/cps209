package Game_model;

public abstract class EntityKillable extends Entity {


    //  Variables  //

    
    protected  double maxHealth, currentHealth;
    protected  static enum StateLife {HEALTHY, HURT, DEAD}
    protected   StateLife stateLife;


    //  Methods  //


    public void checkLife() {

        if(this.stateLife != StateLife.DEAD)
        {
            if(currentHealth > (maxHealth / 2))
            {
                if(this.stateLife != StateLife.HEALTHY)
                {
                    this.stateLife = StateLife.HEALTHY;
                    this.recoverEvent();
                }
            }

            else if(currentHealth != 0 &
                    currentHealth <= (maxHealth / 2))
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
        return maxHealth;
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
        return stateLife;
    }

    public void setStateLife(StateLife stateLife) {
        this.stateLife = stateLife;
    }
}