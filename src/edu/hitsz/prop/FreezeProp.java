package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class FreezeProp extends PropObservation{
    public FreezeProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void apply(HeroAircraft hero, BaseProp prop) {}

    @Override
    public void trigger() {
        for(EnemyAircraft enemyAircraft:enemyAircrafts){
            enemyAircraft.getFreezeProp();
        }
        for(BaseBullet enemyBullet:enemyBullets){
            enemyBullet.getFreezeProp();
        }
    }

    @Override
    public void setEnemyAircrafts(List<EnemyAircraft> enemyAircrafts) {
        this.enemyAircrafts = enemyAircrafts;
    }

    @Override
    public void setEnemyBullets(List<BaseBullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }
}
