package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class BombPropObservation extends PropObservation {

    @Override
    public void trigger() {
        for(EnemyAircraft enemyAircraft:enemyAircrafts){
            enemyAircraft.getBombProp();
        }
        for(BaseBullet enemyBullet:enemyBullets){
            enemyBullet.getBombProp();
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
