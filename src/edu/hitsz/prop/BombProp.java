package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;


public class BombProp extends BaseProp{
    private int heal;
    public BombProp(int locationX, int locationY, int speedX, int speedY, int heal) {
        super(locationX, locationY, speedX, speedY);
        this.heal = heal;
    }

    public void apply(HeroAircraft hero) {
        hero.addHp(heal);
    }
}
