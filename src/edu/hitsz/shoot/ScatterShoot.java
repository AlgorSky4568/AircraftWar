package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class ScatterShoot implements ShootStrategy{
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft, int direction, int power, int shootNum) {
        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY() + direction*2;
        int speedY = abstractAircraft.getSpeedY() + direction*5;
        for(int i = 0; i < 3; i++){
            BaseBullet bullet;
            bullet = new EnemyBullet(x + (i*2 - 3 + 1)*10, y, -1, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}
