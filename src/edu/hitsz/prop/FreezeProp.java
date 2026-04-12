package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class FreezeProp extends BaseProp{
    public FreezeProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void apply(HeroAircraft hero, BaseProp prop) {
        System.out.println("Freeze!");
    }
}
