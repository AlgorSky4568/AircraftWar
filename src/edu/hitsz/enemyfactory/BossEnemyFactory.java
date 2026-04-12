package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.EnemyManager;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.Random;

public class BossEnemyFactory implements EnemyManager{
    Random random = new Random();
    int[] randomList = {-3,3};
    @Override
    public EnemyAircraft createEnemy(){
        int randomNum = random.nextInt(randomList.length);
        return new BossEnemy((int) (0.5 * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                0,
                randomList[randomNum],
                0,
                100);
    }
}
