package edu.hitsz.Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JPanel mainPanel;
    private JButton simpleButton;
    private JButton commonButton;
    private JButton difficultButton;

    private final JDialog dialog;
    private int difficulty = 0; // 0:简单, 1:普通, 2:困难

    public Start(JDialog dialog) {
        this.dialog = dialog;
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 0;
                dialog.dispose();
            }
        });
        commonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 1;
                dialog.dispose();
            }
        });
        difficultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 2;
                dialog.dispose();
            }
        });
    }

    public int getDifficulty() {
        return difficulty;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
