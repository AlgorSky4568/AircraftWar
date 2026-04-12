package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BloodProp extends BaseProp{

    private int heal;

    public BloodProp(int locationX, int locationY, int speedX, int speedY, int heal) {
        super(locationX, locationY, speedX, speedY);
        this.heal = heal;
    }

    @Override
    public void apply(HeroAircraft hero, BaseProp prop) {
        hero.addHp(heal);
    }
}
