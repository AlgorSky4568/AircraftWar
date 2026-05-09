package edu.hitsz.application;

import java.awt.*;

public class DifficultGame extends Game{
    public DifficultGame(){
        difficulty_flag = 2;
        enemySpawnCycle = 10;
        speedY = 20;
        hp = 20;
        ifIncreaseBossHp = 1;
        hero_shootCycle = 10;
        enemy_shootCycle = 10;
        bossThreshold = 300;
        enemyMaxNumber = 10;
        probabilities = new double[]{0.1,0.1,0.4,0.4};
        maxHp = 30;
    }
    @Override
    protected void updateDifficulty() {
        if (gameCycleCount % 500 == 0 && enemySpawnCycle > minEnemySpawnCycle) {
            enemySpawnCycle--;
        }
        if (gameCycleCount % 500 == 0 && speedY < maxEnemySpeedY) {
            speedY++;
        }
        if (gameCycleCount % 500 == 0 && hp < maxHp) {
            hp++;
        }
        if (gameCycleCount % 500 == 0 && hero_shootCycle > minHeroShootCycle) {
            hero_shootCycle--;
        }
        if (gameCycleCount % 500 == 0 && enemy_shootCycle > minEnemyShootCycle) {
            enemy_shootCycle--;
        }
    }
    @Override
    public void paintBackgroundGraph(Graphics g){
        paintBackground(g,ImageManager.BACKGROUND_IMAGE3);
    }
}
