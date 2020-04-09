package Game_model;

public abstract class EntityKillable extends Entity {


    //  Variables  //

    
    public int maxHealth, currentHealth;
    public static enum StateLife {HEALTHY, HURT, DEAD}
    private StateLife stateLife;


    //  Methods  //
    

    public void checkLife() {

        if(this.stateLife != StateLife.DEAD)
        {

            if(currentHealth >= (maxHealth / 2))
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
}