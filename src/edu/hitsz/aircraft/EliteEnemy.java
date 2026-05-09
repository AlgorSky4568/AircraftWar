package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.shoot.StraightShoot;

import java.util.List;

//精英敌人
public class EliteEnemy extends EnemyAircraft{

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, new StraightShoot());
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.shoot(this);
    }

    @Override
    public int addScore(){
        return 20;
    }

    @Override
    public void getBombProp() {
        decreaseHp(100);
    }

    @Override
    public void getFreezeProp() {
        setFrozen(100); // 冰冻约4秒 (100帧 * 40ms)
    }
}