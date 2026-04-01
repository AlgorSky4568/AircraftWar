package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;


public class BombProp extends BaseProp{
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void apply(HeroAircraft hero) {
        System.out.println("Bomb!");
    }
}
