package edu.hitsz.application;

public class DifficultGame extends Game{
    public DifficultGame(){

        enemySpawnCycle = 10;
        speedY = 20;
        hp = 20;
        ifIncreaseBossHp = 1;
        hero_shootCycle = 10;
        enemy_shootCycle = 10;
        bossThreshold = 300;
    }

}
