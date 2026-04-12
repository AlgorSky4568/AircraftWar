package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ScatterShoot implements ShootStrategy{
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft, int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY() + direction*2;
        int speed = 5;
        int angle = 45;
        if(abstractAircraft instanceof HeroAircraft){
            for(int i = 0; i < 3; i++){
                BaseBullet bullet;
                int speedX = (int)(speed * Math.cos(i * angle));
                int speedY = abstractAircraft.getSpeedY() + direction*5;
                bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                res.add(bullet);
            }
            return res;
        }
        else{
            for(int i = 0; i < 3; i++){
                BaseBullet bullet;
                int speedX = (int)(speed * Math.cos(i * angle));
                int speedY = abstractAircraft.getSpeedY() + direction*5;
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                res.add(bullet);
            }
            return res;
        }

    }
}
