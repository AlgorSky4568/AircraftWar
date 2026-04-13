package edu.hitsz.application;
import edu.hitsz.prop.*;

//这是道具的简单工厂
public class PropManager {
    public static BaseProp createProp(String type,int locationX, int locationY, int speedX, int speedY){
        switch (type){
            case "BloodProp":
                return new BloodProp(locationX,locationY,speedX,speedY,30);

            case "BombProp":
                return new BombProp(locationX,locationY,speedX,speedY);

            case "BulletProp":
                return new BulletProp(locationX,locationY,speedX,speedY);

            case "BulletPlusProp":
                return new BulletPlusProp(locationX,locationY,speedX,speedY);

            case "FreezeProp":
                return new FreezeProp(locationX,locationY,speedX,speedY);

            default:
                throw  new IllegalArgumentException("Unknown product type!");
        }
    }
}
