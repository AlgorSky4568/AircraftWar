package edu.hitsz.application;

import java.awt.*;

public class SimpleGame extends Game{
    public SimpleGame(){
        difficulty_flag = 0;
        boss_count = 1;
    }

    @Override
    public void paintBackgroundGraph(Graphics g){
        paintBackground(g,ImageManager.BACKGROUND_IMAGE1);
    }
}
