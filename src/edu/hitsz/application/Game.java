package edu.hitsz.application;

import edu.hitsz.DAO.DAO;
import edu.hitsz.DAO.Record;
import edu.hitsz.DAO.RecordDaoImpl;
import edu.hitsz.Swing.Marks;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.SoundThread;
import edu.hitsz.prop.*;
import edu.hitsz.enemyfactory.*;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;
import java.util.Random;

/**
 * 游戏主面板，游戏启动
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;

    //调度器, 用于定时任务调度
    private final Timer timer;
    //时间间隔(ms)，控制刷新频率
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<EnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<BaseProp> props;
    private final String[] music = {"src/videos/bgm.wav","src/videos/bgm_boss.wav"};
    private final String[] sound = {"src/videos/bomb_explosion.wav","src/videos/bullet_hit.wav","src/videos/game_over.wav","src/videos/get_supply.wav"};

    int[] randomCreateProp = {0,1};

    //屏幕中出现的敌机最大数量
    private final int enemyMaxNumber = 5;

    //敌机生成周期
    protected double enemySpawnCycle  =  20;
    private int enemySpawnCounter = 0;

    //英雄机和敌机射击周期
    protected double hero_shootCycle = 20;
    private int hero_shootCounter = 0;
    protected double enemy_shootCycle = 20;
    private int enemy_shootCounter = 0;
    protected int boss_count = 0;
    private int bossScoreCount = 1;
    protected int speedY = 10;
    protected int hp = 10;
    protected int boss_hp = 1000;
    protected int ifIncreaseBossHp = 0;
    protected int increaseHp = 0;
    protected int bossThreshold = 400;

    //不同难度标志，0为简单，1为普通，2为困难
    private int difficulty_flag = 0;

    //当前玩家分数
    private int score = 0;

    //玩家名称
    private String userName = "TestName";

    //游戏结束标志
    private boolean gameOverFlag = false;

    // 道具相关
    private final java.util.Random rand = new java.util.Random();
    private final double propSpawnRate = 0.25; // 25% 概率产生道具
    private final int maxPropsOnField = 5;
    private final int maxBossCount = 1;
    //敌机生成相关
    Random random = new Random();
    final String[] EnemyList = {"Mob", "Elite", "ElitePlus","ElitePro"};
    private final EnemyManager mobFactory = new ModEnemyFactory();
    private final EnemyManager eliteFactory = new EliteEnemyFactory();
    private final EnemyManager elitePlusFactory = new ElitePlusEnemyFactory();
    private final EnemyManager eliteProFactory = new EliteProEnemyFactory();
    private final EnemyManager bossFactory = new BossEnemyFactory();
    private MusicThread musicThread = new MusicThread(music[0]);
    private PropObservation bomPropObservation = new BombPropObservation();
    private PropObservation freezePropObservation = new FreezePropObservation();

    private final DAO recordDaoImpl = new RecordDaoImpl();


    public int getScore(){
        return score;
    }

    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

        this.timer = new Timer("game-action-timer", true);

    }

    public void setDifficulty(int difficulty_flag){
        this.difficulty_flag = difficulty_flag;
    }

    //更新用户姓名
    public void setUserName(String userName){
        this.userName = userName;
    }
    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public final void action() {

        // 启动背景音乐线程
        musicThread.start();

        // 定时任务：绘制、对象产生、碰撞判定、及结束判定
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //按周期随机产生敌机
                enemySpawnCounter++;
                if (enemySpawnCounter >=enemySpawnCycle) {
                    enemySpawnCounter = 0;
                    int randomNum = random.nextInt(EnemyList.length);
                    switch(EnemyList[randomNum]){
                        case "Mob":
                            enemyAircrafts.add(mobFactory.createEnemy(speedY,hp));
                            break;
                        case "Elite":
                            enemyAircrafts.add(eliteFactory.createEnemy(speedY,2*hp));
                            break;
                        case "ElitePlus":
                            enemyAircrafts.add(elitePlusFactory.createEnemy(speedY,3*hp));
                            break;
                        case "ElitePro":
                            enemyAircrafts.add(eliteProFactory.createEnemy(speedY,4*hp));
                            break;
                    }
                }

                if(score >= bossScoreCount * bossThreshold &&  boss_count < maxBossCount){
                    if(ifIncreaseBossHp == 0){
                        enemyAircrafts.add(bossFactory.createEnemy(0,boss_hp));
                    }
                    else{
                        enemyAircrafts.add(bossFactory.createEnemy(0,(1+increaseHp) * boss_hp));
                    }
                    boss_count++;
                    bossScoreCount+=2;
                    increaseHp++;
                    // 切换Boss音乐：停止旧线程，创建并启动新线程
                    musicThread.changeStopFlag();
                    musicThread = new MusicThread(music[1]);
                    musicThread.start();
                }
                // 飞机发射子弹
                shootAction();
                // 子弹移动
                bulletsMoveAction();
                // 飞机移动
                aircraftsMoveAction();
                // 撞击检测
                crashCheckAction();
                // 后处理
                postProcessAction();
                // 重绘界面
                repaint();
                // 游戏结束检查
                checkResultAction();
            }
        };
        // 以固定延迟时间进行执行：本次任务执行完成后，延迟 timeInterval 再执行下一次
        timer.schedule(task,0,timeInterval);

    }

    //***********************
    //      Action 各部分
    //***********************

    private void shootAction() {
        hero_shootCounter++;
        enemy_shootCounter++;
        if (hero_shootCounter >= hero_shootCycle) {
            hero_shootCounter = 0;
            //英雄机射击
            heroBullets.addAll(heroAircraft.shoot());
        }
        //敌机射击
        if(enemy_shootCounter >= enemy_shootCycle){
            enemy_shootCounter = 0;
            for(EnemyAircraft abstractAircraft : enemyAircrafts){
                enemyBullets.addAll(abstractAircraft.shoot());
            }
        }
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (EnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        // 道具下落
        for (BaseProp prop : props) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    new SoundThread(sound[1]).start();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // 获得分数
                        score += enemyAircraft.addScore();
                        // 道具由敌机自行决定是否产生
                        if(enemyAircraft instanceof BossEnemy){
                            boss_count--;
                            for(int i = 0; i < 3; i++){
                                BaseProp newProp = enemyAircraft.createProp();
                                props.add(newProp);
                            }
                            // Boss被击败，切回普通背景音乐
                            musicThread.changeStopFlag();
                            musicThread = new MusicThread(music[0]);
                            musicThread.start();
                        }
                        else{
                            BaseProp newProp = enemyAircraft.createProp();
                            int randomProp = rand.nextInt(randomCreateProp.length);
                            if (newProp != null && props.size() < maxPropsOnField && randomProp == 1) {
                                props.add(newProp);
                            }
                        }


                    }
                }

                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        //敌机子弹攻击英雄机
        for(BaseBullet bullet: enemyBullets){
            if (bullet.notValid()) {
                continue;
            }

            if (heroAircraft.notValid()) {
                continue;
            }

            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 我方获得道具，道具生效
        for (BaseProp prop : props) {
            if (prop.notValid()) continue;
            if (heroAircraft.crash(prop)) {
                prop.apply(heroAircraft,prop);
                prop.vanish();
                if(prop instanceof BombProp){
                    new SoundThread(sound[0]).start();
                    bomPropObservation.setEnemyAircrafts(enemyAircrafts);
                    bomPropObservation.setEnemyBullets(enemyBullets);
                    bomPropObservation.trigger();
                }
                else if(prop instanceof FreezeProp){
                    new SoundThread(sound[3]).start();
                    freezePropObservation.setEnemyAircrafts(enemyAircrafts);
                    freezePropObservation.setEnemyBullets(enemyBullets);
                    freezePropObservation.trigger();
                }
                else{
                    new SoundThread(sound[3]).start();
                }
            }
        }

    }
    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        // 删除无效道具
        props.removeIf(AbstractFlyingObject::notValid);
    }

    /**
     * 检查游戏是否结束，若结束：关闭线程池，弹出姓名输入框，再弹出排行榜
     */
    private void checkResultAction(){
        // 游戏结束检查英雄机是否存活
        if (heroAircraft.getHp() <= 0) {
            timer.cancel(); // 取消定时器并终止所有调度任务
            gameOverFlag = true;
            System.out.println("Game Over!");
            new SoundThread(sound[2]).start();

            // 弹出姓名输入对话框
            SwingUtilities.invokeLater(() -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                String inputName = JOptionPane.showInputDialog(topFrame, "请输入您的姓名：", "游戏结束", JOptionPane.PLAIN_MESSAGE);
                if (inputName != null && !inputName.trim().isEmpty()) {
                    userName = inputName.trim();
                }
                // 使用输入的姓名添加记录
                recordDaoImpl.doAdd(new Record(score, userName));

                // 弹出排行榜对话框
                JDialog rankingDialog = new JDialog(topFrame, "排行榜", true);
                Marks marks = new Marks(difficulty_flag);
                rankingDialog.setContentPane(marks.getMainPanel());
                rankingDialog.pack();
                rankingDialog.setLocationRelativeTo(topFrame);
                rankingDialog.setVisible(true);
            });
        }
    }

    //***********************
    //      Paint 各部分
    //***********************
    /**
     * 重写 paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        if(difficulty_flag == 0) {
            g.drawImage(ImageManager.BACKGROUND_IMAGE1, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE1, 0, this.backGroundTop, null);
        }
        else if(difficulty_flag == 1){
            g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop, null);
        }
        else if(difficulty_flag == 2){
            g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop, null);
        }
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);

        // 绘制道具
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.isEmpty()) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE: " + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE: " + this.heroAircraft.getHp(), x, y);
    }

}