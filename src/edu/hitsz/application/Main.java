package edu.hitsz.application;

import edu.hitsz.Swing.Start;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {
    //设置游戏画面大小
    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建难度选择对话框
        JDialog startDialog = new JDialog(frame, "选择难度", true);
        Start start = new Start(startDialog);
        startDialog.setContentPane(start.getMainPanel());
        startDialog.setSize(300, 200);
        startDialog.setLocationRelativeTo(frame);
        startDialog.setVisible(true);

        // 用户选择难度后，根据难度创建对应的 Game 子类对象
        int difficulty = start.getDifficulty();
        Game game;
        switch (difficulty) {
            case 1:
                game = new CommonGame();
                break;
            case 2:
                game = new DifficultGame();
                break;
            case 0:
            default:
                game = new SimpleGame();
                break;
        }

        // 启动游戏
        frame.add(game);
        frame.setVisible(true);
        game.action();
    }
}
