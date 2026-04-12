package edu.hitsz.aircraft;

import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.BulletPlusProp;
import edu.hitsz.prop.BulletProp;
import edu.hitsz.shoot.ShootStrategy;
import edu.hitsz.shoot.StraightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    private ShootStrategy shootStrategy1 = new StraightShoot();
    private ShootStrategy shootStrategy2 = new StraightShoot();
    private ShootStrategy shootStrategy3 = new StraightShoot();

    //控制弹道，默认是0，散射是1，环射是2
    private int trajectoryFlag = 0;

    private volatile static HeroAircraft heroAircraft;
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.direction = -1;
        this.power = 50;
    }


    public static HeroAircraft getHeroAircraft(){
        if(heroAircraft == null){
            synchronized (HeroAircraft.class){
                if(heroAircraft == null){
                    heroAircraft = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 100);
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
        if(prop instanceof BulletProp){
            trajectoryFlag = 1;
        }
        else if(prop instanceof BulletPlusProp){
            trajectoryFlag = 2;
        }
    }

    public void recoverTrajectory(){
        trajectoryFlag = 0;
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        if(trajectoryFlag == 1){
            return shootStrategy2.shoot(this, direction, 3,power);
        }
        else if(trajectoryFlag == 2){
            return shootStrategy3.shoot(this, direction, 20,power);
        }
        else{
            return shootStrategy1.shoot(this, direction, shootNum,power);
        }
    }

    // 供道具使用：回血
    public void addHp(int heal){
        this.hp = Math.min(this.maxHp, this.hp + heal);
    }

}