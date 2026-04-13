package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BulletPlusProp extends BaseProp{

    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void apply(HeroAircraft hero, BaseProp prop) {
        hero.changeTrajectory(prop);
    }
}
