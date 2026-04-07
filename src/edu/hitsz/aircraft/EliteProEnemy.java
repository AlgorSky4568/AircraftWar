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
    //每次射击发射子弹数量
    private int shootNum = 3;

    //子弹威力
    private int power = 1;

    //子弹射击方向 (向上发射：-1，向下发射：1)
    private int direction = 1;

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp", "FreezeProp"};

    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
        // 判定x轴左右飞行出界
        if (locationX >= Main.WINDOW_WIDTH || locationX < 0 ) {
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