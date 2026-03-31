package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class FreezeProp extends BaseProp{
    private int heal;
    public FreezeProp(int locationX, int locationY, int speedX, int speedY, int heal) {
        super(locationX, locationY, speedX, speedY);
        this.heal = heal;
    }

    public void apply(HeroAircraft hero) {
        hero.addHp(heal);
    }
}
