package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.application.PropManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//精英Pro敌人
public class EliteProEnemy extends EnemyAircraft{

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp", "FreezeProp"};

    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 3;
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
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        bullet = new EnemyBullet(x + (0*2 - 3 + 1)*10, y, -1, speedY, power);
        bullet = new EnemyBullet(x + (1*2 - 3 + 1)*10, y, 0, speedY, power);
        bullet = new EnemyBullet(x + (2*2 - 3 + 1)*10, y, 1, speedY, power);
        res.add(bullet);
        return res;
    }

    @Override
    public int addScore(){
        return 40;
    }

    @Override
    public BaseProp createProp(){
        Random random = new Random();
        int randomNum = random.nextInt(propList.length);
        return PropManager.createProp(propList[randomNum],getLocationX(),getLocationY(),0,0);
    }

}