package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.shoot.ShootStrategy;
import edu.hitsz.shoot.StraightShoot;

import java.util.LinkedList;
import java.util.List;

//精英敌人
public class EliteEnemy extends EnemyAircraft{

    private ShootStrategy shootStrategy = new StraightShoot();

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
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
        return shootStrategy.shoot(this,direction,shootNum,power);
    }

    @Override
    public int addScore(){
        return 20;
    }
}