package edu.hitsz.aircraft;

/**
 * 火力增强计时器，实现Runnable接口
 * 拾取火力道具后启动，持续10秒后恢复普通弹药
 * 期间拾取新道具会刷新计时
 */
public class PowerUpTimer implements Runnable {

    private final HeroAircraft hero;
    private volatile boolean running = true;

    public PowerUpTimer(HeroAircraft hero) {
        this.hero = hero;
    }
    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        try {
            // 等待10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {

            return;
        }
        if (running) {
            hero.recoverTrajectory();
        }
    }
}
