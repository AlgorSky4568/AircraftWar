package edu.hitsz.music;

public class MusicControl {
    private final String[] music = {"src/videos/bgm.wav","src/videos/bgm_boss.wav"};
    private final String[] sound = {"src/videos/bomb_explosion.wav","src/videos/bullet_hit.wav","src/videos/game_over.wav","src/videos/get_supply.wav"};


    private MusicThread musicThread = new MusicThread(music[0]);

    public void bossCreate(){
        // 切换Boss音乐：停止旧线程，创建并启动新线程
        musicThread.changeStopFlag();
        musicThread = new MusicThread(music[1]);
        musicThread.start();
    }

    public void bossDie(){
        // Boss被击败，切回普通背景音乐
        musicThread.changeStopFlag();
        musicThread = new MusicThread(music[0]);
        musicThread.start();
    }

    public void musicStart(){
        musicThread.start();
    }

    public void bulletCrash(){
        new SoundThread(sound[1]).start();
    }

    public void bombPropGet(){
        new SoundThread(sound[0]).start();
    }
    public void otherPropGet(){
        new SoundThread(sound[3]).start();
    }

    public void gameOver(){
        new SoundThread(sound[2]).start();
    }
}
