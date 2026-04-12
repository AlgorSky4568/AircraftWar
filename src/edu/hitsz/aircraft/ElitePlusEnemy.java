package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.application.PropManager;
import edu.hitsz.shoot.ShootStrategy;
import edu.hitsz.shoot.StraightShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//精英敌人
public class ElitePlusEnemy extends EnemyAircraft{

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp"};
    private ShootStrategy shootStrategy = new StraightShoot();

    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 2;
    }

    @Override
    public void forward() {
        super.forward();
        super.forward_x();
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
        return 30;
    }

    @Override
    public BaseProp createProp(){
        Random random = new Random();
        int randomNum = random.nextInt(propList.length);
        return PropManager.createProp(propList[randomNum],getLocationX(),getLocationY(),0,2);
    }

}