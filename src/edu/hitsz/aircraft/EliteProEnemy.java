package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

//精英Pro敌人
public class EliteProEnemy extends AbstractAircraft{
    public EliteProEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward(){

    }

    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }
}