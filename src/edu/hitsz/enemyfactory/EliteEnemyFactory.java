package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.EnemyManager;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory implements EnemyManager{
    @Override
    public EnemyAircraft createEnemy(){
        return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                50);
    }
}
