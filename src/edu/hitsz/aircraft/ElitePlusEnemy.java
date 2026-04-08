package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.application.PropManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//精英敌人
public class ElitePlusEnemy extends EnemyAircraft{

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp"};

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
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    @Override
    public int addScore(){
        return 30;
    }

    @Override
    public BaseProp createProp(){
        Random random = new Random();
        int randomNum = random.nextInt(propList.length);
        return PropManager.createProp(propList[randomNum],getLocationX(),getLocationY(),0,0);
    }

}