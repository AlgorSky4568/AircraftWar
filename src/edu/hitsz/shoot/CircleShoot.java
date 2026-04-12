package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class CircleShoot implements ShootStrategy {
    @Override
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft, int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY();
        int speed = 5; // 子弹速度大小
        for (int i = 0; i < 20; i++) {
            double angle = Math.toRadians(i * 18); // 360 / 20 = 18 degrees
            int speedX = (int) (speed * Math.cos(angle));
            int speedY = (int) (speed * Math.sin(angle));
            BaseBullet bullet = new EnemyBullet(x, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}