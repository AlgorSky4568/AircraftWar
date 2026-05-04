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
        int tempx = this.speedX;
        int tempy = this.speedY;
        this.speedX = 0;
        this.speedY = 0;
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.speedX = tempx;
        this.speedY = tempy;
    }
}
