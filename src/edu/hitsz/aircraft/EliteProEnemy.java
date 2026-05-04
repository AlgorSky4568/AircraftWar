package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.application.PropManager;
import edu.hitsz.shoot.ScatterShoot;
import edu.hitsz.shoot.ShootStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//精英Pro敌人
public class EliteProEnemy extends EnemyAircraft{
    int bombFlag = 0;

    String[] propList = {"BloodProp", "BombProp", "BulletProp", "BulletPlusProp", "FreezeProp"};

    private ShootStrategy shootStrategy = new ScatterShoot();

    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 3;
    }

    @Override
    public void forward() {
        if (frozen) {
            freezeCountdown--;
            if (freezeCountdown <= 0) {
                frozen = false;
            }
            locationX += (speedX/3);
            locationY += (speedY/2);
            return;
        }
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            // 横向超出边界后反向
            speedX = -speedX;
        }

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
        return 40;
    }

    @Override
    public BaseProp createProp(){
        if(bombFlag == 1){
            return null;
        }
        Random random = new Random();
        int randomNum = random.nextInt(propList.length);
        return PropManager.createProp(propList[randomNum],getLocationX(),getLocationY(),0,2);
    }

    @Override
    public void getBombProp() {
        this.hp -= 20;
        if(this.hp <= 0){
            bombFlag = 1;
        }
    }

    @Override
    public void getFreezeProp() {
        setFrozen(125); // 冰冻约5秒 (125帧 * 40ms)
    }

}