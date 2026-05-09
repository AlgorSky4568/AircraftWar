package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

public abstract class PropObservation extends BaseProp{
    protected List<EnemyAircraft> enemyAircrafts = new ArrayList<>();
    protected List<BaseBullet> enemyBullets = new ArrayList<>();

    public PropObservation(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

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
