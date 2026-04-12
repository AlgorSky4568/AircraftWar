package edu.hitsz.shoot;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.aircraft.AbstractAircraft;

import java.util.LinkedList;
import java.util.List;

public class StraightShoot implements ShootStrategy{
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft, int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = abstractAircraft.getSpeedY() + direction*5;
        BaseBullet bullet;
        if(abstractAircraft instanceof HeroAircraft){
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                res.add(bullet);
            }
            return res;
        }
        else{
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                res.add(bullet);
            }
            return res;
        }

    }
}
