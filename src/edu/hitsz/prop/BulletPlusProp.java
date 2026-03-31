package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BulletPlusProp extends BaseProp{

    private int extraPower;
    private int durationMs;

    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY, int extraPower, int durationMs) {
        super(locationX, locationY, speedX, speedY);
        this.extraPower = extraPower;
        this.durationMs = durationMs;
    }

    @Override
    public void apply(HeroAircraft hero) {
        System.out.println("FireSupply active!");
    }
}
