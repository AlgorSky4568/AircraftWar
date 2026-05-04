package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.PropObservation;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class FreezePropObservation extends PropObservation {

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
