package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.EnemyManager;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import java.util.Random;

public class ElitePlusEnemyFactory implements EnemyManager{
    Random random = new Random();
    int[] randomList = {-3,3};
    @Override
    public AbstractAircraft createEnemy(){
        int randomNum = random.nextInt(randomList.length);
        return new ElitePlusEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                randomList[randomNum],
                10,
                60);
    }
}
