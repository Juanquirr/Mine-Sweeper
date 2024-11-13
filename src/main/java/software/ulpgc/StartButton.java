package software.ulpgc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton {
    public static JButton initialToggle() {
        JButton button = new JButton("toggle");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBoard();
            }
        });
        return button;
    }
    public static Component createBoard(){
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Square " + (i + 1));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel label = new JLabel("X");
                }
            });
        }
        return null;
    }
}
