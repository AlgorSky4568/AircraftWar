package edu.hitsz.application;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

public abstract class PropObservation {
    protected List<EnemyAircraft> enemyAircrafts = new ArrayList<>();
    protected List<BaseBullet> enemyBullets = new ArrayList<>();

    public void addEnemyAircraft(EnemyAircraft enemyAircraft){
        enemyAircrafts.add(enemyAircraft);
    }

    public void addEnemyBullet(BaseBullet bullet){
        enemyBullets.add(bullet);
    }

    public abstract void trigger();

    public abstract void setEnemyAircrafts(List<EnemyAircraft> enemyAircrafts);
    public abstract void setEnemyBullets(List<BaseBullet> enemyBullets);
}
