package edu.hitsz.application;

import edu.hitsz.aircraft.*;

public interface EnemyManager {
    EnemyAircraft createEnemy(int speedY,int hp);
}
