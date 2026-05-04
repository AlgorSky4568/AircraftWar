package edu.hitsz.Swing;

import edu.hitsz.application.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JPanel mainPanel;
    private JButton simpleButton;
    private JButton commonButton;
    private JButton difficultButton;

    private final Game game;
    private final JDialog dialog;

    public Start(Game game, JDialog dialog) {
        this.game = game;
        this.dialog = dialog;
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setDifficulty(0);
                dialog.dispose();
            }
        });
        commonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setDifficulty(1);
                dialog.dispose();
            }
        });
        difficultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setDifficulty(2);
                dialog.dispose();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
