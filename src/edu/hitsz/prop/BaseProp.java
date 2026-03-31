package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class BaseProp extends AbstractFlyingObject {

    public BaseProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward(){
        super.forward();
        // 道具向下掉落，落出屏幕则消失
        if (this.getLocationY() >= Main.WINDOW_HEIGHT){
            vanish();
        }
    }

    // 道具效果应用到英雄机
    public abstract void apply(HeroAircraft hero);
}
