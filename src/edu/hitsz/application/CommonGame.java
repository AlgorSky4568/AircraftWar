package edu.hitsz.application;

public class CommonGame extends Game{
    public CommonGame(){
        hero_shootCycle = 15;
        enemy_shootCycle = 15;
        enemySpawnCycle = 15;
        speedY = 15;
        hp = 15;
        enemyMaxNumber = 7;
        probabilities = new double[]{0.2,0.2,0.3,0.3};
        maxEnemySpeedY = 20;
    }

    /**
     * 敌机产生周期随时间动态缩短，使游戏难度逐渐增加
     * 每经过约500个游戏周期（约20秒），敌机产生周期减少1
     * 但不会低于最小周期 minEnemySpawnCycle（默认值为5）
     */
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
    }
}
