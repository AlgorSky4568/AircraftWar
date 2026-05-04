package edu.hitsz.bullet;

/**
 * 敌机子弹
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    public void getBombProp(){
        EnemyBullet.this.vanish();
    }

    public void getFreezeProp() {
        setFrozen(125); // 冰冻约5秒 (125帧 * 40ms)
    }
}
