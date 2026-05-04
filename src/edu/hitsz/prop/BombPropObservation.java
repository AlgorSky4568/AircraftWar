package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.PropObservation;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

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
