package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.shoot.ShootStrategy;

/**
 * 敌机父类：统一管理敌机的道具掉落行为，默认不掉落，特殊敌机可覆盖 createProp()
 */
public abstract class EnemyAircraft extends AbstractAircraft {

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootStrategy);
    }

    public BaseProp createProp() {
        // 默认不掉落，道具由子类决定（如 ElitePlusEnemy/EliteProEnemy）
        return null;
    }

    public void forward_x(){
        if(getLocationX() >= Main.WINDOW_WIDTH - 5 || getLocationX() <= 5){
            speedX = -speedX;
        }
    }
    public abstract  int addScore();

    public abstract void getBombProp();

    public abstract void getFreezeProp();
}
