package edu.hitsz.aircraft;

import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.BulletPlusProp;
import edu.hitsz.prop.BulletProp;
import edu.hitsz.shoot.CircleShoot;
import edu.hitsz.shoot.ScatterShoot;
import edu.hitsz.shoot.ShootStrategy;
import edu.hitsz.shoot.StraightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {


    //控制弹道，默认是0，散射是1，环射是2
    private int trajectoryFlag = 0;

    // 火力增强计时器线程
    private Thread powerUpThread = null;

    private volatile static HeroAircraft heroAircraft;
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp,shootStrategy);
        this.direction = -1;
        this.power = 50;
    }


    public static HeroAircraft getHeroAircraft(){
        if(heroAircraft == null){
            synchronized (HeroAircraft.class){
                if(heroAircraft == null){
                    heroAircraft = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 100,new StraightShoot());
                }
            }
        }
        return heroAircraft;
    }
    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void changeTrajectory(BaseProp prop){
        // 中断之前的计时器（如果有）
        if (powerUpThread != null && powerUpThread.isAlive()) {
            powerUpThread.interrupt();
        }

        if(prop instanceof BulletProp){
            trajectoryFlag = 1;
            shootNum = 3;
        }
        else if(prop instanceof BulletPlusProp){
            trajectoryFlag = 2;
            shootNum = 20;
        }

        // 启动新的10秒计时器
        PowerUpTimer timer = new PowerUpTimer(this);
        powerUpThread = new Thread(timer);
        powerUpThread.start();
    }

    public void recoverTrajectory(){
        trajectoryFlag = 0;
        shootNum = 1;
        powerUpThread = null;
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        if(trajectoryFlag == 1){
            shootStrategy = new ScatterShoot();
            return shootStrategy.shoot(this);
        }
        else if(trajectoryFlag == 2){
            shootStrategy = new CircleShoot();
            return shootStrategy.shoot(this);
        }
        else{
            shootStrategy = new StraightShoot();
            return shootStrategy.shoot(this);
        }
    }

    // 供道具使用：回血
    public void addHp(int heal){
        this.hp = Math.min(this.maxHp, this.hp + heal);
    }

}