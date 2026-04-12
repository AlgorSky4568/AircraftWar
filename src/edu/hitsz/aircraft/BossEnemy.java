package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.shoot.ShootStrategy;
import edu.hitsz.shoot.StraightShoot;
import edu.hitsz.shoot.CircleShoot;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.application.PropManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//精英敌人
public class BossEnemy extends EnemyAircraft{

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp", "FreezeProp"};

    private ShootStrategy shootStrategy = new CircleShoot();

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward(){
        super.forward();
        super.forward_x();
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
        return 100;
    }

    public BaseProp createProp(){
        Random random = new Random();
        int randomNum = random.nextInt(propList.length);
        return PropManager.createProp(propList[randomNum],getLocationX(),getLocationY() + 10,0,0);
    }
}