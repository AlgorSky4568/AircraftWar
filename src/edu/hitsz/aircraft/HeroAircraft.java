package edu.hitsz.aircraft;

import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BaseProp;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    //每次射击发射子弹数量
    private int shootNum = 1;

    //子弹威力
    private int power = 30;

    //临时buff的额外威力，持续一段时间
    private int extraPower = 0;
    private long powerBuffEndTime = 0;

    //子弹射击方向 (向上发射：-1，向下发射：1)
    private int direction = -1;

    private volatile static HeroAircraft heroAircraft;
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
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

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        int currentPower = power + (System.currentTimeMillis() < powerBuffEndTime ? extraPower : 0);
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, currentPower);
            res.add(bullet);
        }
        return res;
    }

    // 供道具使用：回血
    public void addHp(int heal){
        this.hp = Math.min(this.maxHp, this.hp + heal);
    }

    // 供道具使用：临时增加攻击力
    public void addTemporaryPower(int extraPower, int durationMs){
        this.extraPower = extraPower;
        this.powerBuffEndTime = System.currentTimeMillis() + durationMs;
    }

    @Override
    public int addScore() {
        return 0;
    }

}
